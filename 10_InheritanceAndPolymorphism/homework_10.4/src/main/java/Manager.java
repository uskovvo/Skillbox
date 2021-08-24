public class Manager implements Employee {
    private final int MIN_EMPLOYEE_INCOME = 115_000;
    private final int MAX_EMPLOYEE_INCOME = 140_000;
    private final int FIX_SALARY = 50_000;
    private final double PERCENT = 0.05;
    private int salaryManager = FIX_SALARY;

    @Override
    public int getMonthSalary() {
        return (int) (salaryManager + getSalesPercent());
    }

    private double getSalesPercent (){
        return (Math.random() * (MAX_EMPLOYEE_INCOME - MIN_EMPLOYEE_INCOME) +
                MIN_EMPLOYEE_INCOME) * PERCENT;
    }
}
