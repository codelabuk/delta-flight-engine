package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleAction {
    @JsonProperty("add")
    private AddFile add;
    @JsonProperty("remove")
    private RemoveFile removeFile;


}
