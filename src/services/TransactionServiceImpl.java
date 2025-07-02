package services;

import models.Accounts;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public void deposit(Accounts account, double amount) {
        account.deposit(amount);
        System.out.println("Deposited ₹" + amount + " into " + account.getAccountNumber());
    }

    @Override
    public boolean withdraw(Accounts account, double amount) {
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Withdrew ₹" + amount + " from " + account.getAccountNumber());
        } else {
            System.out.println("Insufficient funds to withdraw ₹" + amount + " from " + account.getAccountNumber());
        }
        return success;
    }

    @Override
    public void transfer(Accounts from, Accounts to, double amount) {
        if (withdraw(from, amount)) {
            deposit(to, amount);
            System.out.println("Transferred ₹" + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
        } else {
            System.out.println("Transfer failed: Insufficient balance.");
        }
    }
}
