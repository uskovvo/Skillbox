import java.util.*;

public class Company {
    private final int MAX_INCOME = 12_000_000;
    private final int MIN_INCOME = 6_000_000;

    private int income = 0;

    private final List<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(int count, Employee employee) {
        for (int a = 0; a < count; a++) {
            employees.add(employee);
        }
    }

    public void fire(int count) {
        count = (employees.size() / 100) * count;
        for(int a = 0; a < count; a++){
            employees.remove(a);
        }
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getList(count, Comparator.reverseOrder());
    }

    public List<Employee> getLowestSalaryStaff(int count){
        return getList(count, Employee::compareTo);
    }

    public int getIncome() {
        return income += ((Math.random() * (MAX_INCOME - MIN_INCOME)) + MIN_INCOME);
    }

    private List<Employee> getList(int count, Comparator<Employee> comparator) {
        if(count > employees.size()){
            System.out.println("В вашей компании нет столько сотрудников!\n" +
                               "В данный момент вы имеете " + employees.size() + " сотрудников");
            return Collections.emptyList();
        }
        if (count <= 0) {
            return Collections.emptyList();
        }
        employees.sort(comparator);
        return new ArrayList<>(employees.subList(0, count));
    }
}
