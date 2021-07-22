import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String charsetStr = "";
      int start = input.indexOf(charsetStr) + input.lastIndexOf(" ");
      for (int a = 0; a < input.length(); a++) {
        if (a > 'А' && a < 'я' && a != 'Ё' && a !='ё') {

        }
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

}