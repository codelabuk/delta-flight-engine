package org.codelabuk.lakehouse.deltaflight.action;

import java.util.List;
import java.util.Map;

public class MetadataAction {
    private String id;
    private String name;
    private String description;
    private String schemaStrng;
    private List<String> partitionColumns;
    private Map<String, String> configuration;
    private Long createdTime;

    public String getId() {
        return id;
    }

    public String getSchemaStrng() {
        return schemaStrng;
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
