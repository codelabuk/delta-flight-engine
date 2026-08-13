package io.github.codelabuk.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtocolAction {
    @JsonProperty("minReaderVersion")
    private int minReaderVersion;
    @JsonProperty("minWriterVersion")
    private int minWriterVersion;

    public int getMinReaderVersion() {
        return minReaderVersion;
    }

    public int getMinWriterVersion() {
        return minWriterVersion;
    }
}
