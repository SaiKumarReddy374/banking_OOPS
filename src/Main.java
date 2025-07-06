import models.*;
import services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionService transactionService = new TransactionServiceImpl();

        System.out.println("=== Welcome to the Banking App ===");

        // Account Setup
        Accounts userAccount = createAccount(sc);

        // Optional secondary account for transfer
        Accounts otherAccount = new CurrentAccount("TEMP1001", "TransferAccount", 2000.0);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Banking Operations Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer to TEMP1001");
            System.out.println("4. Show Balance");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    double depAmt = sc.nextDouble();
                    transactionService.deposit(userAccount, depAmt);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withAmt = sc.nextDouble();
                    boolean success = transactionService.withdraw(userAccount, withAmt);
                    if (!success) {
                        System.out.println("Transaction failed due to insufficient funds.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to transfer to TEMP1001: ₹");
                    double transAmt = sc.nextDouble();
                    transactionService.transfer(userAccount, otherAccount, transAmt);
                    break;

                case 4:
                    System.out.printf("Current Balance: ₹%.2f%n", userAccount.getBalance());
                    break;

                case 5:
                    userAccount.calculateInterest();
                    break;

                case 6:
                    exit = true;
                    System.out.println("Thank you for banking with us. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
                    break;
            }
        }

        sc.close();
    }

    private static Accounts createAccount(Scanner sc) {
        System.out.print("Enter account type (1 for Savings, 2 for Current): ");
        int accType = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ₹");
        double balance = sc.nextDouble();

        if (accType == 1) {
            return new SavingsAccount(accNum, name, balance);
        } else if (accType == 2) {
            return new CurrentAccount(accNum, name, balance);
        } else {
            System.out.println("Invalid account type. Defaulting to Savings Account.");
            return new SavingsAccount(accNum, name, balance);
        }
    }
}
