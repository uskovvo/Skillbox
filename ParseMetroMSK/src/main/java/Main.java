import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    private static final String WEB_SITE = "https://www.moscowmap.ru/metro.html#lines";

    public static void main(String[] args) {
        try{
            Document doc = Jsoup.connect(WEB_SITE).maxBodySize(0).get();
            Elements elements = doc.select("div.js-toggle-depend");
            SubwayMSK.createSubwayMap(elements);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
