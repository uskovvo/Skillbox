
public class Main {
    private static final String movement = "D:\\Valera\\SkillBox\\java_basics\\13_FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        System.out.println(new Movements(movement).getIncomeSum());
        System.out.println(new Movements(movement).getExpenseSum());
    }
}
