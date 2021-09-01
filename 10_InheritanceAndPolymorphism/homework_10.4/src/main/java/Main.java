import java.util.*;

public class Main {
    private static int count;
    private static String position;
    private static String questionPosition = "Какая должность?";
    private static String questionCount = "Какое количество сотрудников?";

    public static void main(String[] args) {
        Company company = new Company();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду: \n" +
                               "- Нанять\n" +
                               "- Уволить\n" +
                               "- Распечатать");
            String str = scanner.nextLine();
            str = str.toLowerCase(Locale.ROOT);
            if (str.isEmpty()) {
                return;
            }
            if(str.equals("нанять")){
                System.out.println(questionCount);
                str = scanner.nextLine();
                if (str.isEmpty()){
                    return;
                }
                count = Integer.parseInt(str);
                System.out.println(questionPosition);
                str = scanner.nextLine();
                if(str.isEmpty()){
                    return;
                }
                position = str;
                Employee employee = getEmployee(position);
                if(count == 1){
                    company.hire(employee);
                }else if(count > 1){
                    company.hireAll(count, employee);
                }
            }
            if(str.equals("уволить")){
                System.out.println("Какое количество сотрудников вы хотите уволить? (в процентном соотношении)");
                str = scanner.nextLine();
                if(str.isEmpty()){
                    return;
                }
                int countFire = Integer.parseInt(str);
                company.fire(countFire);
            }
            if(str.equals("распечатать")){
                System.out.println("Что вы хотите распечатать: \n" +
                                   "- Список самых высоких зарплат, введите \"1\"\n" +
                                   "- Список самых маленьких зарплат, введите \"2\"");
                str = scanner.nextLine();
                if(str.isEmpty()){
                    return;
                }
                int a = Integer.parseInt(str);
                System.out.println(questionCount);
                str = scanner.nextLine();
                if(str.isEmpty()){
                    return;
                }
                int countList = Integer.parseInt(str);
                if(a == 1){
                    List<Employee> topSalaryStaff = company.getTopSalaryStaff(countList);
                    for(Employee employee : topSalaryStaff){
                        System.out.println(employee.getMonthSalary());
                    }
                }else if(a == 2){
                    List<Employee> lowSalaryStaff = company.getLowestSalaryStaff(countList);
                    for(Employee employee : lowSalaryStaff){
                        System.out.println(employee.getMonthSalary());
                    }
                }
            }
        }
    }

    private static Employee getEmployee(String position) {
        position = position.toLowerCase(Locale.ROOT);
        return switch (position) {
            case "менеджер" -> new Manager();
            case "топ менеджер" -> new TopManager(new Company());
            case "оператор" -> new Operator();
            default -> throw new IllegalStateException("Ошибка!!!");
        };
    }
}
