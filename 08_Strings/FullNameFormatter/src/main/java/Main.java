import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String s = "";

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            s = input;
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
        }
        int count = 0;
        for (int a = 0; a < s.length(); a++) {
            if (Character.isSpaceChar(s.charAt(a))) {
                count++;
            }
        }
        String surname = getSurname(s);
        String name = getName(s);
        String patronymic = getPatronymic(s);

        if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || count != 2) {
            System.out.println("Введенная строка не является ФИО");
        } else {
            System.out.println("Фамилия: " + surname);
            System.out.println("Имя: " + name);
            System.out.println("Отчество: " + patronymic);
        }
    }

    public static String getSurname(String text) {
        int ind = text.indexOf(" ");
        if (ind == -1) {
            return "";
        }
        String str = text.substring(0, ind);
        for (int a = 0; a < str.length(); a++) {
            if (Character.UnicodeBlock.of(str.charAt(a)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return str.trim();
            }
        }
        return "";
    }

    public static String getName(String text) {
        if (getSurname(text).isEmpty()) {
            return "";
        }
        String next = getSurname(text);
        int ind = text.indexOf(" ", next.length() + 1);
        if (ind == -1) {
            return "";
        }
        String str = text.substring(next.length() + 1, ind).trim();
        for (int a = 0; a < str.length(); a++) {
            if (Character.UnicodeBlock.of(str.charAt(a)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return str;
            }
        }
        return "";
    }

    public static String getPatronymic(String text) {
        if (getName(text).isEmpty()) {
            return "";
        }
        int ind = text.lastIndexOf(" ", text.length());
        String str = text.substring(ind, text.length()).trim();
        for (int a = 0; a < str.length(); a++) {
            if (Character.UnicodeBlock.of(str.charAt(a)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return str;
            }
        }
        return "";
    }

}