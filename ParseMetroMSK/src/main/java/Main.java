import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    private static final String WEB_SITE = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
    private static final String path = "src/main/resources/subway.json";


    public static void main(String[] args) {
        try{
            Document doc = Jsoup.connect(WEB_SITE).maxBodySize(0).get();
            Elements elements = doc.select(".standard.sortable tr");
            MapSubway.createSubwayMap(elements);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        ReadJsonFile.readJsonFile(path);
    }
}
