import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    private static Path source;
    private static Path destination;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            List<Path> files = Files
                    .walk(Paths.get(sourceDirectory))
                    .collect(Collectors.toList());
            List<Path> destination = files.stream()
                    .map(Paths.get(sourceDirectory)::relativize)
                    .map(Paths.get(destinationDirectory)::resolve)
                    .collect(Collectors.toList());
            for (int a = 1; a < files.size(); a++) {
                Files.copy(files.get(a), destination.get(a));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // TODO: write code copy content of sourceDirectory to destinationDirectory
    }
}
