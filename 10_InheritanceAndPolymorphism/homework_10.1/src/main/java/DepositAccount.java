import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;

    public void setLastIncome(LocalDate lastIncome) {
        this.lastIncome = lastIncome;
    }

    public LocalDate getLastIncome() {
        return lastIncome;
    }

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }

    @Override
    public boolean take(double amountToTake) {
        lastIncome = getLastIncome();
        if(lastIncome.isAfter(LocalDate.now().plusMonths(1))){
            return super.take(amountToTake);
        }
        System.out.println("Снятие не возможно, прошло меньше месяца после пополнения.");
        return false;
    }
}
