import org.checkerframework.checker.units.qual.C;

public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль
        String encoding = "";
        int sum = 0;
        String charset = " ";
        int start = text.indexOf(charset);
        int end = text.indexOf("рубл", start);
        for(int a = start; a < end; a++) {
            encoding = text.substring(start, end);
        }

        System.out.println(encoding.trim());
    }
}