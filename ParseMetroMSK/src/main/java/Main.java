import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    private static final String WEB_SITE = "https://infotables.ru/strany-i-goroda/1066-metro-moskvy-spisok-stantsij";

    public static void main(String[] args) {
        try{
            Document doc = Jsoup.connect(WEB_SITE).maxBodySize(0).get();
            Elements elements = doc.select("div.item-page");
            FullSubwayMSK.createSubwayMap(elements);
//            SubwayMSK.createSubwayMap(elements);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
