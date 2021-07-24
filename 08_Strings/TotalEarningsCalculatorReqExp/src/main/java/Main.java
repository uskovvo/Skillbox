import net.sf.saxon.expr.Component;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String text = "Коля заработал 50000 рублей, Федя - 45800 рубля, а Саша - 23000 рублей";
        System.out.println(calculateSalarySum(text));

    }

    public static int calculateSalarySum(String text) {
        String alphabet = "[^";
        for (char a = 'А'; a <= 'я'; a++) {
            alphabet += a;
        }
        alphabet = alphabet + 'ё' + 'Ё' + "]";
        int summ = 0;
        String regex = alphabet + "[^,.:;\\-\\s][0-9]*[^,.:;\\-\\s]" + alphabet;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            for(int a = 0; a < text.substring(start, end).trim().length(); a++){
                if (!Character.isDigit(text.substring(start, end).trim().charAt(a))) {
                    return 0;
                }
            }
            summ += Integer.parseInt(text.substring(start, end).trim());
        }
        return summ;
    }

}