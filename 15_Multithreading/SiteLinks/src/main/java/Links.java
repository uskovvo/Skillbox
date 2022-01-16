import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.*;
import java.util.concurrent.RecursiveAction;

public class Links extends RecursiveAction {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Marker infoMarker = MarkerManager.getMarker("INFO");
    private static final Marker errorMarker = MarkerManager.getMarker("EXCEPTIONS");
    private final String mainLink;
    @Getter
    private static final TreeSet<String> pages = new TreeSet();

    public Links(String mainLink) {
        this.mainLink = mainLink;
        pages.add("");
    }

    @Override
    protected void compute() {
        List<Links> linksList = new ArrayList<>();
        try {
            pages.forEach(page -> {
                if (!mainLink.equals(page)) {
                    pages.add(mainLink);
                    System.out.println("Add: " + mainLink);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        logger.info(infoMarker, "Error info: ", e);
                        logger.error(errorMarker, "Exception: ", e);
                    }

                    Pages findPages = new Pages(mainLink);
                    findPages.getLinks().forEach(newUrl -> {
                        Links link = new Links(mainLink + newUrl);
                        link.fork();
                        linksList.add(link);
                    });
                }
                System.out.println("Skipped: " + mainLink);
            });
            linksList.forEach(Links::join);
        }
        catch (Exception e){
            logger.info(infoMarker, "Error info: ", e);
            logger.error(errorMarker, "Exception: ", e);
        }
    }
}
