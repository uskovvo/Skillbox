import org.checkerframework.checker.units.qual.C;

public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль
        int sum = 0;
        String charset = " руб";

        for(int a = 0; a < text.length(); a++) {
            int start = text.lastIndexOf(charset);
            int end = text.lastIndexOf(" ", start - 1);
            String encod = text.substring(end, start);
            sum += Integer.parseInt(encod.trim());
        }

        System.out.println(sum);
    }
}