import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.Integer.MAX_VALUE;

public class FileUtils {
    private static long pathSize;
    private static long size;
    private static int maxDepth = MAX_VALUE;

    public static long calculateFolderSize(String path) throws IOException {
        try {
            Stream<Path> files = Files.walk(Path.of(path));
            size = files
                    .filter(Files::isRegularFile)
                    .mapToLong(path1 -> {
                        try {
                            return Files.size(path1);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return 0L;
                    }).sum();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return size;
    }
//        File folder = new File(path);
//        File[] files = folder.listFiles();
//
//        try {
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    pathSize += getFileSize(file);
//                    size = 0;
//                } else {
//                    pathSize += file.length();
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return pathSize;
//    }
//
//    public static long getFileSize(File file) {
//        if (file.isDirectory()) {
//            ArrayList<File> path = new ArrayList<>(Arrays.asList(file.listFiles()));
//            for (File f : path) {
//                if (f.isDirectory()) {
//                    getFileSize(f);
//                } else {
//                    size += f.length();
//                }
//            }
//        } else {
//            size += file.length();
//        }
//        return size;
//    }
}
