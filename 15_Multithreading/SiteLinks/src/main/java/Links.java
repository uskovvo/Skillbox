import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Links extends RecursiveAction {
//    private final Logger logger = LogManager.getRootLogger();
//    private final Marker marker = MarkerManager.getMarker("EXCEPTIONS");
    private final String mainLink;
    private final Set<Links> links = new LinkedHashSet<>();
    private PrintWriter printWriter;
//    private String exceptionLink;

    public Links(String mainLink) {
        this.mainLink = mainLink;
    }

    @Override
    protected void compute() {
        try {
            printWriter = new PrintWriter("links.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            logger.fatal(marker, "Fatal read file", e);
        }
        Document document = null;
        try {
            document = Jsoup.connect(mainLink).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
//            logger.fatal(marker, "Fatal read main link: ", e);
        }

        assert document != null;
        Elements elements = document.select("a");
        elements.forEach(element -> {
            String newLink = element.absUrl("href");
            Pattern pattern = Pattern.compile(mainLink);
            Matcher matcher = pattern.matcher(newLink);
            if (matcher.find() && !links.contains(newLink)) {
//                    Links link = new Links(newLink);
                printWriter.write(newLink + "\n\t");
                links.add(new Links(newLink));

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    logger.error(marker, "Error sleep: ", e);
//                    logger.fatal(marker, "Fatal sleep: ", e);
                }

            }
        });
//            links.forEach(Links::join);

        printWriter.flush();
        printWriter.close();
        ForkJoinTask.invokeAll(links);
    }
}
