import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.TreeSet;

public class Pages {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Marker infoMarker = MarkerManager.getMarker("INFO");
    private static final Marker errorMarker = MarkerManager.getMarker("EXCEPTIONS");
    private final String url;
    private static String baseURL;
    @Getter
    private final TreeSet<String> links = new TreeSet<>();

    public Pages(String url) {
        if (baseURL == null) baseURL = url;
        this.url = url;
        this.findLink();
    }

    private void findLink() {
        try {
            Document document = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = document.select("a");
            elements.forEach(element -> {
                String newUrl = element.attr("href");
                if (!newUrl.contains(".pdf") &&
                        !newUrl.contains(".jpg") &&
                        !newUrl.contains(".png") &&
                        !newUrl.contains("=")) {
                    if (newUrl.contains(baseURL))
                        links.add((newUrl.replace(baseURL, "")));
                    if (newUrl.matches("/.+"))
                        links.add(newUrl.substring(1));
                }
            });
        } catch (IOException e) {
            logger.info(infoMarker, "Error info: ", e);
            logger.error(errorMarker, "Exception: ", e);
        }
    }
}
