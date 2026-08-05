package org.codelabuk.lakehouse.deltaflight;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codelabuk.lakehouse.deltaflight.action.SingleAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CommitLogReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<SingleAction> read(Path commitFilePath) {
        List<SingleAction> actions = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(commitFilePath)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                try {
                    actions.add(mapper.readValue(line, SingleAction.class));
                } catch (IOException e) {
                    throw new UncheckedIOException("Malformed action at " + commitFilePath + ":" + lineNumber, e);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read commit file" + commitFilePath, e);
        }
        return actions;
    }

}
