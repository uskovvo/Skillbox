import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    private static Path source;
    private static Path destination;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        source = Paths.get(sourceDirectory);
        destination = Paths.get(destinationDirectory);
        try {
            if (!Files.exists(destination)) {
                Files.createDirectory(destination);
            }

            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path copy = destination.resolve(source.relativize(dir));
                    if (!Files.exists(copy)) {
                        Files.createDirectory(copy);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path copy = destination.resolve(source.relativize(file));
                    if (!Files.exists(copy)) {
                        Files.copy(file, copy);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // TODO: write code copy content of sourceDirectory to destinationDirectory
    }
}
