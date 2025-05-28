import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null; // To keep track of the logged-in user
    private static ATMOperations atmOperations = new ATMOperations();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize some dummy users and accounts
        initializeUsers();

        System.out.println("Welcome to the ATM Interface!");

        // User authentication loop
        while (currentUser == null) {
            System.out.print("Enter User ID: ");
            String userId = scanner.next();
            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            authenticateUser(userId, pin);

            if (currentUser == null) {
                System.out.println("Invalid User ID or PIN. Please try again.");
            }
        }

        // ATM operations loop
        int choice;
        do {
            atmOperations.displayMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atmOperations.showTransactionHistory(currentUser.getAccount());
                    break;
                case 2:
                    atmOperations.withdraw(currentUser.getAccount(), scanner);
                    break;
                case 3:
                    atmOperations.deposit(currentUser.getAccount(), scanner);
                    break;
                case 4:
                    atmOperations.transfer(currentUser.getAccount(), users, scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void initializeUsers() {
        // Create some sample accounts
        Account acc1 = new Account("1234567890", 1000.00);
        Account acc2 = new Account("0987654321", 500.00);
        Account acc3 = new Account("1122334455", 2000.00);


        // Create some sample users
        users.add(new User("john", "1234", acc1));
        users.add(new User("jane", "5678", acc2));
        users.add(new User("bob", "9999", acc3));
    }

    private static void authenticateUser(String userId, String pin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.authenticate(pin)) {
                currentUser = user;
                System.out.println("Authentication successful. Welcome, " + currentUser.getUserId() + "!");
                return;
            }
        }
        currentUser = null; // Authentication failed
    }
}