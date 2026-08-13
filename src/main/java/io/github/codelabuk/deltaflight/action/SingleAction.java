package io.github.codelabuk.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleAction {
    @JsonProperty("add")
    private AddFile add;
    @JsonProperty("remove")
    private RemoveFile remove;
    @JsonProperty("commitInfo")
    private CommitInfoAction commitInfo;
    @JsonProperty("metadata")
    private MetadataAction metadata;
    @JsonProperty("protocol")
    private ProtocolAction protocol;


    public AddFile getAdd() {
        return add;
    }

    public RemoveFile getRemove() {
        return remove;
    }

    public CommitInfoAction getCommitInfo() {
        return commitInfo;
    }

    public MetadataAction getMetadata() {
        return metadata;
    }

    public ProtocolAction getProtocol() {
        return protocol;
    }
}
