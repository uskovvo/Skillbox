import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccount cardAccount = new CardAccount();
        DepositAccount depositAccount = new DepositAccount();
        bankAccount.put(500);
        bankAccount.send(depositAccount, 250);
        depositAccount.setLastIncome(LocalDate.now().plusMonths(2));
        depositAccount.take(100);
        depositAccount.setLastIncome(LocalDate.now().minusMonths(1));
        depositAccount.take(50);
    }
}
