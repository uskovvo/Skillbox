import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Numbers extends Thread {
    private final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private final int regionCode;
    private final long startTime;
    PrintWriter writer = null;

    public Numbers(int regionCode, long startTime) throws FileNotFoundException {
        this.regionCode = regionCode;
        this.startTime = startTime;
        writer = new PrintWriter("res/numbers" + regionCode + ".txt");
    }

    @Override
    public void run() {

        for (int number = 1; number < 1000; number++) {
            StringBuffer buffer = new StringBuffer();
            for (char firstLetter : LETTERS) {
                for (char secondLetter : LETTERS) {
                    for (char thirdLetter : LETTERS) {
                        buffer.append(firstLetter);
                        buffer.append(padNumber(number, 3));
                        buffer.append(secondLetter);
                        buffer.append(thirdLetter);
                        buffer.append(padNumber(regionCode, 2));
                        buffer.append("\n");
                    }
                }
            }
            writer.write(buffer.toString());
        }
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            builder.append("0");
            builder.append(numberStr);
        }

        return builder.toString();
    }
}
