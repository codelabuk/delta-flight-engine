package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Operational Info about a commit
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class CommitInfoAction {

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("operation")
    private String operation;

    private final Map<String, Object> other = new HashMap<>();

    @JsonAnySetter
    public void setOther(String key, Object value) {
        other.put(key, value);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public Map<String, Object> getOther() {
        return other;
    }
}
