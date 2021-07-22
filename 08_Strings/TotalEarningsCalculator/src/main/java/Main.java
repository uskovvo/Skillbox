import org.checkerframework.checker.units.qual.C;

public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль
        int sum = 0;

        for(int a = 0; a < text.length(); a++) {
            String charset = " руб";
            int start = text.lastIndexOf(charset);
            int end = text.indexOf(" ", start);
            String encod = text.substring(start, end);
            System.out.println(encod);
            sum += Integer.parseInt(encod);
        }

        System.out.println(sum);
    }
}