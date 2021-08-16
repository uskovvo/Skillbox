import java.util.ArrayList;
import java.util.List;

public class Company {
    private final int MAX_INCOME = 12_000_000;
    private final int MIN_INCOME = 6_000_000;

    private int income = 0;
    List<Object> employee = new ArrayList<>();

    public int getIncome(){
        return income += ((Math.random() * (MAX_INCOME - MIN_INCOME)) + MIN_INCOME);
    }

    public void hire(){

    }

    public void hireAll(){

    }

    public void fire(){

    }
}
