import java.util.ArrayList;
import java.util.List;

public class TopManager extends Company implements Employee {
    private final int FIX_SALARY = 60_000;
    private int monthSalary = FIX_SALARY;

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }

}
