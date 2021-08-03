import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private static final EmailList emailList = new EmailList();
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String command = findCommand(input);
            String email = emailText(input);
            if (command.equals("ADD")) {
                emailList.add(email);
            }
            if (command.equals("LIST")) {
                emailList.getSortedEmails();
            }
            //TODO: write code here

        }
    }

    public static String findCommand(String request) {
        String command;
        String regex = "[\\W+0-9]";
        request = request.replaceAll(regex, " ").trim();
        int start = request.indexOf("");
        int end = request.indexOf(" ");
        if (end == -1) {
            command = request.substring(start);
        } else {
            command = request.substring(start, end);
        }
        command = command.toUpperCase(Locale.ROOT);

        return command;
    }

    public static String emailText(String request) {
        int start = request.indexOf(" ");
        if (start == -1) {
            return "";
        }

        return request.substring(start).trim();
    }
}
