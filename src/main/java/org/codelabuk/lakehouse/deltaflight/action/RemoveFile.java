package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveFile {
    @JsonProperty("path")
    private String path;
    @JsonProperty("deletionTimestamp")
    private Long deletionTimestamp;
    @JsonProperty("dataChange")
    private boolean dataChange;


    public String getPath() {
        return path;
    }

    public Long getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public boolean isDataChange() {
        return dataChange;
    }

    @Override
    public String toString() {
        return "RemoveFile{" +
                "path='" + path + '\'' +
                '}';
    }
}
