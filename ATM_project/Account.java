import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private String accountNumber;
    private List<String> transactionHistory; // Simple string for history for now

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Initial balance: $" + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit: $" + amount);
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal: $" + amount);
            System.out.println("Withdrawal successful. New balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    public void addTransaction(String transaction) {
        this.transactionHistory.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}