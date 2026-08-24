package io.github.codelabuk.deltaflight;

import io.github.codelabuk.deltaflight.action.AddFile;
import io.github.codelabuk.deltaflight.action.MetadataAction;
import io.github.codelabuk.deltaflight.action.ProtocolAction;
import io.github.codelabuk.deltaflight.action.SingleAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SnapshotManager {
    private final Path logDir;
    private final CommitLogReader commitLogReader = new CommitLogReader();
    private long cachedVersion = -1;
    private final Map<String, AddFile> cachedActiveFIles = new ConcurrentHashMap<>();
    private MetadataAction cachedMetadata;
    private ProtocolAction cachedProtocol;

    public SnapshotManager(Path tableRoot) {
        this.logDir = tableRoot.resolve("_delta_log");
    }

    public synchronized Snapshot latest() {
        List<Long> versions = listCommitVersions();
        if (versions.isEmpty()) {
            throw new IllegalStateException("No commits found under " + logDir);
        }
        return atVersion(versions.get(versions.size() - 1));
    }

    public synchronized Snapshot atVersion(long targetVersion) {
        if (targetVersion < 0) {
            throw new IllegalStateException("No commits found under " + logDir);
        }
        List<Long> commitedVersions = listCommitVersions();
        if (commitedVersions.isEmpty()) {
            throw new IllegalStateException("No commit found under " + logDir);
        }

        long maxAvailableVersion = commitedVersions.get(commitedVersions.size() - 1);
        if (targetVersion > maxAvailableVersion) {
            throw new IllegalArgumentException("Requested Version " + targetVersion + "has not been commited yet; latest version is " + maxAvailableVersion);
        }

        if (targetVersion < cachedVersion) {
            resetCache();
        }

        for (long cv = cachedVersion + 1; cv <= targetVersion; cv++) {
            applyCommit(cv);
        }
        cachedVersion = targetVersion;

        return buildSnapshot(targetVersion);
    }

    private void applyCommit(long version) {
        Path commitFile = logDir.resolve(CommitFileNames.jsonFileName(version));
        List<SingleAction> actions = commitLogReader.read(commitFile);
        for (SingleAction action : actions) {
            if (action.getAdd() != null) {
                cachedActiveFIles.put(action.getAdd().getPath(), action.getAdd());
            }
            if (action.getRemove() != null) {
                cachedActiveFIles.remove(action.getRemove().getPath());
            }
            if (action.getMetadata() != null) {
                cachedMetadata = action.getMetadata();
            }

            if (action.getProtocol() != null) {
                cachedProtocol = action.getProtocol();
            }
        }
    }


    private Snapshot buildSnapshot(long targetVersion) {
        return new Snapshot(targetVersion, cachedActiveFIles, cachedMetadata, cachedProtocol);
    }


    private void resetCache() {
        cachedVersion = -1;
        cachedActiveFIles.clear();
        cachedMetadata = null;
        cachedProtocol = null;
    }


    private List<Long> listCommitVersions() {
        try (Stream<Path> stream = Files.list(logDir)) {
            return stream.filter(CommitFileNames::isCommitJson)
                    .map(CommitFileNames::parseVersion)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
