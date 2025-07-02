package models;

public class CurrentAccount extends Accounts {
    private static final double OVERDRAFT_LIMIT = 1000;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public void calculateInterest() {
        System.out.println("Current Account doesn't earn interest.");
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
