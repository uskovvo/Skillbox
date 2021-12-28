import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class BankTest extends TestCase {

    Map<String, Account> accountMap;
    Bank bank;
    Account account1;
    Account account2;
    Account account3;
    Account account4;
    @Override
    protected void setUp() throws Exception {
        bank = new Bank();
        account1 = new Account();
        account2 = new Account();
        account3 = new Account();
        account4 = new Account();
        accountMap = bank.getAccounts();

        account1.setAccNumber("1");
        account1.setMoney(50_000);
        account2.setAccNumber("2");
        account2.setMoney(100_000);
        account3.setAccNumber("3");
        account3.setMoney(150_000);
        account4.setAccNumber("4");
        account4.setMoney(200_000);

        accountMap.put(account1.getAccNumber(), account1);
        accountMap.put(account2.getAccNumber(), account2);
        accountMap.put(account3.getAccNumber(), account3);
        accountMap.put(account4.getAccNumber(), account4);
    }

    public void testTransfer(){
        try {
            bank.transfer(accountMap.get("1").getAccNumber(), accountMap.get("2").getAccNumber(), 30_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(20_000, account1.getMoney());
        assertEquals(130_000, account2.getMoney());
    }

    public void testTransferWithCheckFraud(){
        try {
            bank.transfer(accountMap.get("4").getAccNumber(), accountMap.get("3").getAccNumber(), 100_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(bank.isSecurity()){
            assertFalse(accountMap.containsKey("4"));
            assertFalse(accountMap.containsKey("3"));
        }else{
            assertEquals(100_000, accountMap.get("4").getMoney());
            assertEquals(250_000, accountMap.get("3").getMoney());
        }
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
