package io.github.codelabuk.deltaflight;

import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Ensure Delta commit files are name
 */
final class CommitFileNames {
    private static final int VERSION_WIDTH = 20;
    private static final String REGEX_PATTERN = "\\d{20}\\.json"; // 20 digit zero padded json regex
    private static final Pattern COMMIT_JSON = Pattern.compile(REGEX_PATTERN);

    private CommitFileNames() {
    }

    static String jsonFileName(long version) {
        return String.format("%0" + VERSION_WIDTH + "d.json", version);
    }

    static boolean isCommitJson(Path path) {
        return COMMIT_JSON.matcher(path.getFileName().toString()).matches();
    }

    static long parseVersion(Path path) {
        String name = path.getFileName().toString();
        return Long.parseLong(name.substring(0, VERSION_WIDTH));
    }

}
