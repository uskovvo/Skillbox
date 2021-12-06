import lombok.AllArgsConstructor;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@AllArgsConstructor
public class ImageResizer extends Thread{
    private int newWidth;
    private String destPath;
    private File[] files;

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.AUTOMATIC,
                        Scalr.Mode.AUTOMATIC, 300, 300);
//                        Scalr.resize(image,300, Scalr.THRESHOLD_QUALITY_BALANCED);

                File newFile = new File(destPath + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
            System.out.println("Запущен поток");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
