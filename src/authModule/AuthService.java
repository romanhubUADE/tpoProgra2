package authModule;

import dictionaryModule.SimpleDictionary;
import dictionaryModule.SimpleLinkedDictionary;

public class AuthService {

    private final SimpleDictionary<String, User> users;

    public AuthService() {
        users = new SimpleLinkedDictionary<>();
    }

    public enum RegisterResult {
        SUCCESS,
        USERNAME_ALREADY_EXISTS,
        INVALID_INPUT
    }

    public enum LoginResult {
        SUCCESS,
        ACCOUNT_BLOCKED,
        WRONG_PASSWORD,
        USER_NOT_FOUND,
        INVALID_INPUT
    }

    public RegisterResult register(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return RegisterResult.INVALID_INPUT;
        }
        if (users.containsKey(username)) {
            return RegisterResult.USERNAME_ALREADY_EXISTS;
        }
        users.put(username, new User(username, password));
        return RegisterResult.SUCCESS;
    }

    public LoginResult login(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return LoginResult.INVALID_INPUT;
        }
        if (!users.containsKey(username)) {
            return LoginResult.USER_NOT_FOUND;
        }

        User user = users.get(username);

        if (user.isBlocked()) {
            return LoginResult.ACCOUNT_BLOCKED;
        }

        if (!user.getPassword().equals(password)) {
            user.registerFailedAttempt();
            if (user.isBlocked()) {
                return LoginResult.ACCOUNT_BLOCKED;
            }
            return LoginResult.WRONG_PASSWORD;
        }

        user.resetFailedAttempts();
        return LoginResult.SUCCESS;
    }

    public int getRemainingAttempts(String username) {
        if (!users.containsKey(username)) return -1;
        return users.get(username).getRemainingAttempts();
    }

    public boolean isBlocked(String username) {
        if (!users.containsKey(username)) return false;
        return users.get(username).isBlocked();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
