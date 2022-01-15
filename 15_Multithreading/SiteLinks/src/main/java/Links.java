
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Marker;
//import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Links extends RecursiveAction {
//    private final Logger logger = LogManager.getRootLogger();
//    private final Marker marker = MarkerManager.getMarker("EXCEPTIONS");
    private final String mainLink;
    private final Set<Links> links = new LinkedHashSet<>();
    private final TreeSet<String> pages = new TreeSet<>();

    public Links(String mainLink) {
        this.mainLink = mainLink;
        pages.add("");
    }

    @Override
    protected void compute() {

        try{
            pages.forEach(page -> {
                if(!mainLink.equals(page)){
                    pages.add(mainLink);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Document document = Jsoup.connect(mainLink).maxBodySize(0).get();
                        Elements elements = document.select("a");
                        elements.forEach(element -> {
                            String url = element.absUrl("href");
                            Pattern pattern = Pattern.compile(mainLink);
                            Matcher matcher = pattern.matcher(url);
                            if(matcher.find() && !links.contains(url)){
                                if(!url.contains(".pdf") && !url.contains(".jpg")
                                        && !url.contains(".png") && !url.contains("=")) {
                                    Links link = new Links(url);
                                    link.fork();
                                    links.add(link);
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            links.forEach(Links::join);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ForkJoinTask.invokeAll(links);
    }

    public void writeSiteMap (){
        try {
            FileWriter fileWriter = new FileWriter("data/links.txt");
            pages.forEach(page -> {
                try {
                    fileWriter.write(page + "\t" + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
