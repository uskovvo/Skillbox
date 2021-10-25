
public class Main {
    private static final String movement = "D:\\Valera\\SkillBox\\java_basics\\13_FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(movement);
        System.out.printf("\nОбщая сумма прихода: %,.02f\nОбщая сумма расхода: %,.02f\n",
                            movements.getIncomeSum(), movements.getExpenseSum());
        System.out.println("\nСуммы расходов по организациям:");
        movements.expenseByOrg();
    }
}