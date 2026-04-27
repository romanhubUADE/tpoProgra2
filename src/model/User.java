package model;

public class User {

    public static final int MAX_FAILED_ATTEMPTS = 3;

    private final String username;
    private String password;
    private int failedAttempts;
    private boolean blocked;

    public User(String username, String password) {
        this.username       = username;
        this.password       = password;
        this.failedAttempts = 0;
        this.blocked        = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void registerFailedAttempt() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            blocked = true;
        }
    }

    public void resetFailedAttempts() {
        failedAttempts = 0;
    }

    public int getRemainingAttempts() {
        return MAX_FAILED_ATTEMPTS - failedAttempts;
    }
}
