import java.util.List;
import java.util.Scanner;

public class ATMOperations {

    public void showTransactionHistory(Account account) {
        System.out.println("\n--- Transaction History ---");
        List<String> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
        System.out.println("---------------------------");
    }

    public void withdraw(Account account, Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    public void deposit(Account account, Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    public void transfer(Account senderAccount, List<User> allUsers, Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.next();

        User recipientUser = null;
        for (User user : allUsers) {
            if (user.getUserId().equals(recipientId)) {
                recipientUser = user;
                break;
            }
        }

        if (recipientUser == null) {
            System.out.println("Recipient User ID not found.");
            return;
        }

        Account recipientAccount = recipientUser.getAccount();
        if (senderAccount.getAccountNumber().equals(recipientAccount.getAccountNumber())) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        System.out.print("Enter amount to transfer: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        if (senderAccount.getBalance() >= amount) {
            if (senderAccount.withdraw(amount)) { // withdraw will also add to history
                recipientAccount.deposit(amount); // deposit will also add to history
                senderAccount.addTransaction("Transferred $" + amount + " to " + recipientId);
                recipientAccount.addTransaction("Received $" + amount + " from " + senderAccount.getAccountNumber());
                System.out.println("Transfer successful.");
            }
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Choose an option: ");
    }
}