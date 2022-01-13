import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        new ForkJoinPool().invoke(new Links("https://skillbox.ru/"));
//        Links link = new Links("https://lenta.ru/");
//        link.compute();
    }
}
