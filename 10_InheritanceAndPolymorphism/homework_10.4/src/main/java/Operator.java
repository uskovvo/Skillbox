import java.util.ArrayList;
import java.util.List;

public class Operator extends Company implements Employee {
    private final int FIX_SALARY = 50_000;
    private int salaryOperator = FIX_SALARY;

    @Override
    public int getMonthSalary() {
        return salaryOperator;
    }
}
