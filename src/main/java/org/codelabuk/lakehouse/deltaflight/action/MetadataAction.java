package org.codelabuk.lakehouse.deltaflight.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Table level MetaData
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataAction {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("schemaString")
    private String schemaString;
    @JsonProperty("partitionColumns")
    private List<String> partitionColumns;
    @JsonProperty("configuration")
    private Map<String, String> configuration;
    @JsonProperty("createdTime")
    private Long createdTime;

    public String getId() {
        return id;
    }

    public String getSchemaString() {
        return schemaString;
    }

    public List<String> getPartitionColumns() {
        return partitionColumns == null ? List.of() : partitionColumns;
    }

    public Map<String, String> getConfiguration() {
        return configuration == null ? Map.of() : configuration;
    }

    public Long getCreatedTime() {
        return createdTime;
    }
}
