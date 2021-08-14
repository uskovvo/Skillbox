public abstract class Client {

    private double money = 0;

    public double getAmount() {
        System.out.println("Сумма на счете: " + money + " руб.\n");

        return money;
    }

    public void put(double amountToPut) {
        if (amountToPut < 0) {
            System.out.println("Введите корректную сумму.\n");

            return;
        }
        money += amountToPut;
        System.out.println("На счет поступило: " + amountToPut + " руб.");
        System.out.println("Сумма на счете: " + money + " руб.\n");
    }

    public void take(double amountToTake) {
        if (amountToTake > money) {
            System.out.println("Не хватает денежных средств.\n");

            return;
        }
        money -= amountToTake;
        System.out.println("Со счета списано: " + amountToTake + " руб.");
        System.out.println("Сумма на счете: " + money + " руб.\n");
    }

}
