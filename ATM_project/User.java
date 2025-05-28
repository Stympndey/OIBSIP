public class User {
    private String userId;
    private String userPin;
    private Account account; // Each user has one account

    public User(String userId, String userPin, Account account) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public Account getAccount() {
        return account;
    }

    public boolean authenticate(String enteredPin) {
        return this.userPin.equals(enteredPin);
    }
}