import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static final int coreCount = Runtime.getRuntime().availableProcessors();
    private static String srcFolder = "D://Valera/SkillBox/images";
    private static String dstFolder = "data/images";

    public static void main(String[] args) {
        File srcDir = new File(srcFolder);
        File dstDir = new File(dstFolder);
        if(!dstDir.exists()){
            dstDir.mkdirs();
        }

        int countThread = coreCount <= srcDir.length() ? coreCount : (int) srcDir.length();

        File[] files = srcDir.listFiles();
        File[] fileThread;
        int arrayLength;
        int lastArrayLength;
        int currentSrcPosition;
        int countThreads = 0;

        if (files.length % 2 == 0) {
            arrayLength = files.length / countThread;
            lastArrayLength = arrayLength;
        } else {
            arrayLength = (files.length + 1) / countThread;
            lastArrayLength = arrayLength - 1;
        }
        for (int a = 0; a < countThread; a++) {
            countThreads++;
            if (a != arrayLength) {
                fileThread = new File[arrayLength];
                currentSrcPosition = arrayLength * a;
                System.arraycopy(files, currentSrcPosition, fileThread, 0, arrayLength);
                new ImageResizer(300, dstFolder, fileThread, countThreads).start();
            } else {
                fileThread = new File[lastArrayLength];
                currentSrcPosition = arrayLength * a;
                System.arraycopy(files, currentSrcPosition, fileThread, 0, lastArrayLength);
                ImageResizer imageResizer = new ImageResizer(300, dstFolder, fileThread, countThreads);
                try {
                    imageResizer.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageResizer.start();

            }
        }

    }
}
