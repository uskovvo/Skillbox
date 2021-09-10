import java.util.*;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";
    private static final int DATE = 1900;

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        return staff.stream()
                .filter(employee -> employee.getWorkStart().getYear() + DATE == (year))
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }
}