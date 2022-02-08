public class Loader{

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            Numbers numbers = new Numbers(regionCode, start);
            numbers.start();
        }
    }
}
