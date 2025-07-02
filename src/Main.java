import models.*;
import services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionService transactionService = new TransactionServiceImpl();

        System.out.println("Welcome to the Banking App!");

        // Create an account
        System.out.print("Enter account type (1 for Savings, 2 for Current): ");
        int accType = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        Accounts userAccount;
        if (accType == 1) {
            userAccount = new SavingsAccount(accNum, name, balance);
        } else {
            userAccount = new CurrentAccount(accNum, name, balance);
        }

        // Optionally create another account for transfers
        Accounts otherAccount = new CurrentAccount("TEMP1001", "Other", 2000);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer to another account");
            System.out.println("4. Show balance");
            System.out.println("5. Calculate interest");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depAmt = sc.nextDouble();
                    transactionService.deposit(userAccount, depAmt);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withAmt = sc.nextDouble();
                    transactionService.withdraw(userAccount, withAmt);
                    break;
                case 3:
                    System.out.print("Enter transfer amount to TEMP1001: ");
                    double transAmt = sc.nextDouble();
                    transactionService.transfer(userAccount, otherAccount, transAmt);
                    break;
                case 4:
                    System.out.println("Current Balance: ₹" + userAccount.getBalance());
                    break;
                case 5:
                    userAccount.calculateInterest();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Banking App!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
