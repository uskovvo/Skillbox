import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static String site = "https://skillbox.ru/";

    public static void main(String[] args) {
        Links link = new Links(site);
        new ForkJoinPool(Runtime.getRuntime().availableProcessors()).invoke(link);
        link.writeSiteMap();

//        Links link = new Links("https://lenta.ru/");
//        link.compute();
    }
}
