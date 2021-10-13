public class BankAccount {
    private String typeAccount;
    private String accountNumber;
    private String currency;

    public BankAccount(String typeAccount, String accountNumber, String currency){
        this.typeAccount = typeAccount;
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
