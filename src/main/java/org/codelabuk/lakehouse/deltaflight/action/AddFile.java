package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddFile {

    private String path;
    private Map<String, String> partitionValues;
    private long size;
    private long modificationTime;
    private boolean dataChange;
    private String stats;

    public String getPath() {
        return path;
    }

    public Map<String, String> getPartitionValues() {
        return partitionValues == null ? Map.of() : partitionValues;
    }

    public long getSize() {
        return size;
    }

    public long getModificationTime() {
        return modificationTime;
    }


    public boolean isDataChange() {
        return dataChange;
    }

    public String getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "AddFile{" +
                "path='" + path + '\'' +
                ", partitionValues=" + partitionValues +
                ", size=" + size +
                '}';
    }
}
