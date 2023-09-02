import java.io.*;
import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String accountNo;
    private String accountName;
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount(String accountNo, String accountName, double balance) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    public double getBalance() {
        return balance;
    }
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
            System.out.println("Current balance: Rs " + balance);
            recordTransaction("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance && amount <= 50000) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
            System.out.println("Remaining balance: Rs " + balance);
            recordTransaction("Withdrawal", amount);
        }
        else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
    public void checkBalance() {
        System.out.println("Account Number: " + accountNo);
        System.out.println("Account Holder Name: " + accountName);
        balance--;
        System.out.println("Current Balance: Rs " + balance);
        System.out.println("Service charges: Rs 1.00");
    }
    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNo);
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
    private void recordTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactionHistory.add(transaction);
    }
    public void saveTransactionHistoryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction transaction : transactionHistory) {
                writer.write(transaction.toString());
            }
            System.out.println("Transaction history saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving transaction history: " + e.getMessage());
        }
    }
}