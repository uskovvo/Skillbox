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
        LocalDate now = LocalDate.now();
        if(now.isAfter(getLastIncome())){
            return super.take(amountToTake);
        }
        else {
            System.out.println("Снятие не возможно, прошло меньше месяца после пополнения.");
            return false;
        }
    }
}
