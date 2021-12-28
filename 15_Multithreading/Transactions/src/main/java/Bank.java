import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    @Getter
    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private Account account;
    private final long LIMIT_TRANSFER = 50_000;

    @Getter
    private boolean isSecurity;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if((amount > LIMIT_TRANSFER && amount > 0) &&
                (!fromAccount.isBlockedAcc() && !toAccount.isBlockedAcc())){
            isSecurity = isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount);
        }
        if(isSecurity){
            fromAccount.setBlockedAcc(true);
            toAccount.setBlockedAcc(true);
        }
        else {
            long profit = toAccount.getMoney() + amount;
            toAccount.setMoney(profit);
            profit = fromAccount.getMoney() - amount;
            fromAccount.setMoney(profit);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for(Map.Entry<String, Account> a: accounts.entrySet()){
            sum += a.getValue().getMoney();
        }
        return sum;
    }
}


