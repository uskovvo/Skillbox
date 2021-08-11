import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccount bankAccount1 = new BankAccount();
        BankAccount cardAccount = new CardAccount();
        bankAccount.put(100.2);
        bankAccount.put(554.9);
        bankAccount.take(1000);
        bankAccount.take(500);
        bankAccount.send(cardAccount, 100);
        bankAccount.getAmount();
        bankAccount1.getAmount();

        bankAccount.put(455);
        bankAccount.take(0.1);
        cardAccount.getAmount();
        cardAccount.take(10);
    }
}
