public class CardAccount extends BankAccount {
    @Override
    public void take(double amountToTake) {
        super.take(amountToTake + (amountToTake * 0.01));
    }
}
