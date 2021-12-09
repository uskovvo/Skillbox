import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    private static final Map<String, Account> accounts = new HashMap<String, Account>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        for(int a = 0; a < 10; a++) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int b = 0; b < 16; b++) {
                if (b % 4 == 0) {
                    stringBuffer.append(" ");
                }
                int randomNum = random.nextInt(10);
                stringBuffer.append(randomNum);
            }
            accounts.put(String.valueOf(stringBuffer), new Account());
        }

        for(Map.Entry<String, Account> a: accounts.entrySet()){
            System.out.println(a.getKey() + " " + a.getValue());
        }
    }
}
