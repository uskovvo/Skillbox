import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Transfer extends Thread{

    private Map<String, Account> list;
    private String fromAccount;
    private String toAccount;
    private long amount;
    private Bank bank;

    @Override
    public void run() {
        try {
            bank.transfer(list.get(fromAccount).getAccNumber(), list.get(toAccount).getAccNumber(), amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
