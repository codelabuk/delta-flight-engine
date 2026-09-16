package io.github.codelabuk.deltaflight.log;

import io.github.codelabuk.deltaflight.log.action.SingleAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommitLogReaderTest {
    private final CommitLogReader reader = new CommitLogReader();

    private Path fixture(String name) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(name).toURI());
    }

    @Test
    public void parsesFirst_commit_createTableAdd() throws  Exception{
        List<SingleAction> actions = reader.read(fixture("00000000000000000000.json"));
        Assertions.assertEquals(5, actions.size(), "commitInfo + protocol");
    }

    @Test
    public void firstcommit_hasexpected_Action_types() throws Exception{
        List<SingleAction> actions = reader.read(fixture("00000000000000000000.json"));
        Assertions.assertNotNull(actions.get(0).getCommitInfo(), "line 1 should be committed");
        Assertions.assertNotNull(actions.get(1).getProtocol(), "line 2 should be protocol");
        Assertions.assertNotNull(actions.get(2).getMetadata(), "line 3 should be metadata");
        Assertions.assertNotNull(actions.get(3).getAdd(), "line 4 should be add");
        Assertions.assertNotNull(actions.get(4).getAdd(), "line 5 should be add");
    }

    @Test
    public void process_metadata_schema_partition() throws Exception {
        List<SingleAction> actions = reader.read(fixture("00000000000000000000.json"));
        var metaData = actions.get(2).getMetadata();
        Assertions.assertEquals(List.of("level"), metaData.getPartitionColumns());
        Assertions.assertTrue(metaData.getSchemaString().contains("event_time"),
                "schemaString should not dropped or reparsed");
    }
}
