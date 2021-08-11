public class BankAccount {
    double money = 0;

    public double getAmount() {
        return money;
    }

    public void put(double amountToPut) {
        if(amountToPut < 0){
            return;
        }
        money += amountToPut;
    }

    public void take(double amountToTake) {
        if(amountToTake > money) {
            return;
        }
        money -= amountToTake;
    }
}
