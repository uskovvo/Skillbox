import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            System.out.println(formatPhoneNumber(input));
        }
    }

    public static String formatPhoneNumber(String input) {
        String fullNumber = "7";
        input = input.replaceAll("[^0-9]", "");
//        System.out.println(input);
        if (input.length() > 11 || input.length() < 10) {
            return "Неверный формат номера";
        } else if (input.length() == 10) {
            fullNumber += input;
            return fullNumber;
        } else if (input.charAt(0) == '8' || input.charAt(0) == '7') {
            if (input.charAt(0) == '8') {
                return input.replace(input.charAt(0), '7');
            }
            return input;
        } else {
            return "Неверный формат номера";
        }
    }
}

//else if (input.charAt(0) != '7' || input.charAt(0) != '8') {
//        return "Неверный формат номера";
//        }