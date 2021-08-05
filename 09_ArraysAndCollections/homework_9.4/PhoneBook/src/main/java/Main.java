import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        String name = "";
        String phone = "";

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if(input.equals("LIST")) {
                for(String phones : phoneBook.getAllContacts()){
                    System.out.println(phones);
                }
            }

            if (!input.isEmpty() && Character.isDigit(input.charAt(0))) {
                phone = input;
                name = scanner.nextLine();
            }else {
                name = input;
                phone = scanner.nextLine();
            }
            phoneBook.addContact(phone, name);

        }
    }
}
