package services;

import models.Accounts;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public void deposit(Accounts account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited ₹" + amount + ". New Balance: ₹" + account.getBalance());
    }

    @Override
    public boolean withdraw(Accounts account, double amount) {
        if (account instanceof models.CurrentAccount) {
            return ((models.CurrentAccount) account).withdraw(amount);
        }

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn ₹" + amount + ". New Balance: ₹" + account.getBalance());
            return true;
        } else {
            System.out.println("Insufficient balance. Withdrawal of ₹" + amount + " failed.");
            return false;
        }
    }

    @Override
    public void transfer(Accounts from, Accounts to, double amount) {
        if (withdraw(from, amount)) {
            deposit(to, amount);
            System.out.println("Transferred ₹" + amount + " to account " + to.getAccountNumber());
        } else {
            System.out.println("Transfer failed due to insufficient funds.");
        }
    }
}
