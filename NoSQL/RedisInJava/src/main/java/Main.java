import java.util.Random;

import static java.lang.System.out;

public class Main {
    private static final int USERS = 20;
    private static final RedisStorage redis = new RedisStorage();
    private static String log;
    private static Random random = new Random();
    private static int userWithPrem;

    private static void log(int UsersOnline) {
        log = String.format("На главной странице показываем пользователя: %d", UsersOnline);
        out.println(log);
    }

    private static void vipLog(int buyPrem) {
        log = String.format("Пользователь %d оплатил платную услугу", buyPrem);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        redis.init();

        for(int a = 1; a <= USERS; a++){
            redis.addUser(a, a);
        }

        for(;;) {
            for(int j = 1; j <= USERS; j++) {
                int a = redis.getUsersValue(j);
                log(a);
                if(isBuyPrem()){
                    vipLog(userWithPrem);
                    log(userWithPrem);
                }
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }
    }

    public static boolean isBuyPrem(){
        userWithPrem = random.nextInt(redis.calculateUsersNumber());
        return random.nextInt(10) == 0;
    }
}
