import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Links extends RecursiveAction {

    private final Logger logger = LogManager.getRootLogger();
    private final Marker marker = MarkerManager.getMarker("EXCEPTIONS");
    private String mainLink;
    private Set<Links> links = new LinkedHashSet<>();
    private PrintWriter printWriter;

    public Links (String mainLink){
        this.mainLink = mainLink;
    }

    @Override
    protected void compute() {
        try{
            printWriter = new PrintWriter("links.txt");
            Document document = Jsoup.connect(mainLink).ignoreContentType(true).get();
            Elements elements = document.select("a");
            elements.forEach(element -> {
                String newLink = element.absUrl("href");
                Pattern pattern = Pattern.compile(mainLink);
                Matcher matcher = pattern.matcher(newLink);
                if(matcher.find() && !links.contains(newLink)){
                    Links link = new Links(newLink);
                    link.fork();
                    links.add(link);
                    printWriter.write(newLink + "\n\t");

                    try {
                        Thread.sleep(125);
                    } catch (InterruptedException e) {
                        logger.error(marker, "Error sleep: ", e);
                        logger.fatal(marker, "Fatal sleep: ", e);
                    }
                }
            });
            links.forEach(Links::join);
        }
        catch (Exception e){
            logger.error(marker, "Error: ", e);
            logger.fatal(marker, "Fatal error: ", e);
        }

        printWriter.flush();
        printWriter.close();
    }
}
