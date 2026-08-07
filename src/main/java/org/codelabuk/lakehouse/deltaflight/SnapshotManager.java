package org.codelabuk.lakehouse.deltaflight;

import org.codelabuk.lakehouse.deltaflight.action.MetadataAction;
import org.codelabuk.lakehouse.deltaflight.action.ProtocolAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SnapshotManager {
    private final Path logDir;
    private final CommitLogReader commitLogReader = new CommitLogReader();
    private long cachedVersion = -1;
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
        return atVersion(versions.get(versions.size() -1));
    }

    public synchronized Snapshot atVersion(long targetVersion){
        if(targetVersion < 0){
            throw new IllegalStateException("No commits found under "+ logDir);
        }
        List<Long> versions = listCommitVersions();
        if(versions.isEmpty()){
            throw new IllegalStateException("No commit found under "+ logDir);
        }
        long maxAvailableVersion = versions.get(versions.size() -1);
        if(targetVersion > maxAvailableVersion){
            throw new IllegalArgumentException("Requested Version " + targetVersion);
        }
        if (targetVersion < cachedVersion){
            // reset Cache
        }

        return null; // buildSnapshot
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
