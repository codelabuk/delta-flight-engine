package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleAction {
    @JsonProperty("add")
    private AddFile add;
    @JsonProperty("remove")
    private RemoveFile removeFile;
    @JsonProperty("commit")
    private CommitInfoAction commitInfo;


}
