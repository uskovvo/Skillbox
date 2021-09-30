import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtils {
    private static long pathSize;
    private static long size;

    public static long calculateFolderSize(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();

        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    pathSize += getFileSize(file);
                    size = 0;
                } else {
                    pathSize += file.length();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pathSize;
    }

    public static long getFileSize(File file) {
        if (file.isDirectory()) {
            ArrayList<File> path = new ArrayList<>(Arrays.asList(file.listFiles()));
            for (File f : path) {
                if (f.isDirectory()) {
                    getFileSize(f);
                } else {
                    size += f.length();
                }
            }
        } else {
            size += file.length();
        }
        return size;
    }
}
