import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String WEB_SITE = "https://lenta.ru";
    private static final File DIR = new File("data/img");
    private static Path destDir;
    private static final Map<URL, String> linkImg = new HashMap<>();

    public static void main(String[] args) {
        if(!DIR.exists()){
            DIR.mkdir();
        }
        parseLinkImg();
        copyImg(linkImg);
    }

    private static void parseLinkImg(){
        try{
            Document doc = Jsoup.connect(WEB_SITE).get();
            Elements elements = doc.select("img");
            elements.forEach(element -> {
                try {
                    String url = element.attr("abs:src");
                    if(url.contains(".jpg")) {
                        String fileName = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf(".jpg"));
                        String suffix = url.substring(url.lastIndexOf('.'));
                        String file = fileName + suffix;
                        linkImg.put(new URL(url), file);
                    }else {
                        return;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private static void copyImg(Map<URL, String> urls){
        InputStream input;
        try {
            for (Map.Entry<URL, String> url : urls.entrySet()) {
                input = url.getKey().openStream();
                destDir = Paths.get(DIR + "/" + url.getValue());
                Files.copy(input, destDir, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(url.getValue());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
