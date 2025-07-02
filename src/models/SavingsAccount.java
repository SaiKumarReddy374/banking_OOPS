package models;

public class SavingsAccount extends Accounts {
    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        System.out.println("Savings Account Interest: " + interest);
    }

}
