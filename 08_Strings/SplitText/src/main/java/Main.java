import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

    }

    public static String splitTextIntoWords(String text) {
        if (!text.isEmpty()) {
            String encod = "";
            StringBuilder stringBuilder = new StringBuilder();
            String regex = "[,.:;\\-0-9]";
            encod = text.replaceAll(regex, " ");
            String[] word = encod.split("[\\s]+");
            for (int a = 0; a < word.length; a++) {
                stringBuilder.append(word[a]).append("\n");
            }
            return stringBuilder.toString().trim();
        }
        return "";
    }
}
