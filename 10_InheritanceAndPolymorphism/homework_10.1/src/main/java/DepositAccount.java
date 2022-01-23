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
        LocalDate minusMonths = lastIncome.minusMonths(1);
        LocalDate minusYear = lastIncome.minusYears(1);
        if(lastIncome.isAfter(minusMonths)){
            return super.take(amountToTake);
        }
        else if(lastIncome.isAfter(minusYear)) {
            return super.take(amountToTake);
        }else {
            System.out.println("Снятие не возможно, прошло меньше месяца после пополнения.");
            return false;
        }
    }
}
