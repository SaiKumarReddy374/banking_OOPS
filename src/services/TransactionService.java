package services;

import models.Accounts;

public interface TransactionService {
    void deposit(Accounts account, double amount);
    boolean withdraw(Accounts account, double amount);
    void transfer(Accounts from, Accounts to, double amount);
}
