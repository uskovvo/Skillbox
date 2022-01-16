import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Marker infoMarker = MarkerManager.getMarker("INFO");
    private static final Marker errorMarker = MarkerManager.getMarker("EXCEPTIONS");
    private static String site = "https://lenta.ru/";
    private static String path = "links.txt";

    public static void main(String[] args) {
        Links link = new Links(site);
        new ForkJoinPool().invoke(link);
        writeSiteMap(path, link.getPages());
        System.out.println("Work completed");
    }

    public static void writeSiteMap(String path, TreeSet<String> pages) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            pages.forEach(page -> {
                try {
                    fileWriter.write(formatLinkView(page) + "\n");
                } catch (IOException e) {
                    logger.info(infoMarker, "Error info: ", e);
                    logger.error(errorMarker, "Exception: ", e);
                }
            });
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.info(infoMarker, "Error info: ", e);
            logger.error(errorMarker, "Exception: ", e);
        }
    }

    public static String formatLinkView(String link) {
        long slashCount = (link.chars().filter(ch -> ch == '/').count()) - 3;
        if (slashCount > 0) {
            link = String.join("", Collections.nCopies((int) slashCount, "\t")) + link;
        }
        return link;
    }
}
