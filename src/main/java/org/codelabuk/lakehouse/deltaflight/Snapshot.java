package org.codelabuk.lakehouse.deltaflight;

import org.codelabuk.lakehouse.deltaflight.action.AddFile;
import org.codelabuk.lakehouse.deltaflight.action.MetadataAction;
import org.codelabuk.lakehouse.deltaflight.action.ProtocolAction;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public final class Snapshot {
    private final long version;
    private final Map<String, AddFile> activeFilesByPath;
    private final MetadataAction metadata;
    private final ProtocolAction protocol;

    public Snapshot(long version, Map<String, AddFile> activeFilesByPath,
                    MetadataAction metadata, ProtocolAction protocol) {
        this.version = version;
        this.activeFilesByPath = Collections.unmodifiableMap(new LinkedHashMap<>(activeFilesByPath));  // defensive copy
        this.metadata = metadata;
        this.protocol = protocol;
    }

    public long getVersion() {
        return version;
    }

    public MetadataAction getMetadata() {
        return metadata;
    }

    public int getActiveFileCount() {
        return activeFilesByPath.size();
    }

    public Collection<AddFile> getActiveFiles() {
        return activeFilesByPath.values();
    }


    public ProtocolAction getProtocol() {
        return protocol;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "version=" + version +
                ", activeFiles=" + activeFilesByPath.size() +
                '}';
    }
}
