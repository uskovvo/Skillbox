public class Operator implements Employee {
    private final int FIX_SALARY = 50_000;

    @Override
    public int getMonthSalary() {
        return FIX_SALARY;
    }
}
