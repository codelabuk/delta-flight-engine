package io.github.codelabuk.deltaflight.log;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SnapshotManagerTest {

    private Path tableRoot() throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource("sample-table").toURI());
    }

    @Test
    public void versionZero_hasBoth_Files() throws Exception {
        SnapshotManager manager = new SnapshotManager(tableRoot());
        Snapshot snapshot = manager.atVersion(0);
        Assertions.assertEquals(0, snapshot.getVersion());
        Assertions.assertEquals(2, snapshot.getActiveFileCount());
    }

    @Test
    public void latest_To_Highest_version_resolves() throws URISyntaxException {
        SnapshotManager manager = new SnapshotManager(tableRoot());
        Snapshot snapshot = manager.latest();
        Assertions.assertEquals(1, snapshot.getVersion());
        Assertions.assertEquals(1, snapshot.getActiveFileCount());
    }
}
