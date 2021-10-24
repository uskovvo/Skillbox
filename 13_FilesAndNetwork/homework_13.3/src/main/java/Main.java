
public class Main {
    private static final String movement = "D:\\Valera\\SkillBox\\java_basics\\13_FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(movement);
        System.out.println("Сумма доходов: " + movements.getIncomeSum() + " руб.");
        System.out.println("Сумма расходов: " + movements.getExpenseSum() + " руб.");
        movements.expenseByOrg();
    }
}
