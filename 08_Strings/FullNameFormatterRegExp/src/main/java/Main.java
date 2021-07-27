import org.checkerframework.checker.units.qual.C;

import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            int count = 0;
            String result = "";
            if (input.equals("0")) {
                break;
            }
            if (input.isEmpty()) {
                break;
            }

            for (int a = 0; a < input.length(); a++) {
                if (Character.isSpaceChar(input.charAt(a))) {
                    count++;
                }
            }

            result += getSurname(input);
            if (count != 2 || result.isEmpty()) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }

            int a = result.indexOf("");
            int b = result.indexOf("\n");
            int c = result.lastIndexOf("\n");
            int d = result.lastIndexOf("");
            String surname = "Фамилия: " + result.substring(a, b);
            String name = "Имя: " + result.substring(b + 1, c);
            String patronymic = "Отчество: " + result.substring(c + 1, d);
            System.out.println(surname + "\n" + name + "\n" + patronymic);
        }
    }

    public static String getSurname(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex = "[*+%,.:;!?'0-9]";
        String encod = input.replaceAll(regex, "");
        String[] word = encod.split("[\\s+]");
        for (int a = 0; a < word.length; a++) {
            stringBuilder.append(word[a]).append("\n");
        }
        for (int a = 0; a < stringBuilder.length(); a++) {
            if (Character.UnicodeBlock.of(stringBuilder.charAt(a)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return stringBuilder.toString().trim();
            }
        }
        return "";
    }
}