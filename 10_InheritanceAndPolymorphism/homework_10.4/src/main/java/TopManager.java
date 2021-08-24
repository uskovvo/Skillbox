public class TopManager implements Employee {
    private final int FIX_SALARY = 100_000;
    private final int MIN_INCOME = 10_000_000;
    private final double PERCENT_TOP_MANAGER = 1.5 * FIX_SALARY;
    private Company company;
    private int salaryTop_Manager = FIX_SALARY;

    public TopManager (Company company){
        setCompany(company);
    }

    @Override
    public int getMonthSalary() {
        if(company.getIncome() > MIN_INCOME){
            return salaryTop_Manager = (int) (salaryTop_Manager + PERCENT_TOP_MANAGER);
        }
        return salaryTop_Manager;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
