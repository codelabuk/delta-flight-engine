package io.github.codelabuk.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * A file that becomes a part of live set of the commit.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddFile {
    @JsonProperty("path")
    private String path;
    @JsonProperty("partitionValues")
    private Map<String, String> partitionValues;
    @JsonProperty("size")
    private long size;
    @JsonProperty("modificationTime")
    private long modificationTime;
    @JsonProperty("dataChange")
    private boolean dataChange;
    @JsonProperty("stats")
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
