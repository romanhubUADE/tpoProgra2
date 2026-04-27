package service;

import exception.DuplicateKeyException;
import exception.KeyNotFoundException;
import model.User;
import tda.Dictionary;
import tda.LinkedDictionary;

public class AuthService {

    private final Dictionary<String, User> users;

    public AuthService() {
        this.users = new LinkedDictionary<>();
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

        try {
            users.put(username, new User(username, password));
            return RegisterResult.SUCCESS;
        } catch (DuplicateKeyException e) {
            return RegisterResult.USERNAME_ALREADY_EXISTS;
        }
    }

    public LoginResult login(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return LoginResult.INVALID_INPUT;
        }

        if (!users.containsKey(username)) {
            return LoginResult.USER_NOT_FOUND;
        }

        try {
            User user = users.get(username);

            if (user.isBlocked()) {
                return LoginResult.ACCOUNT_BLOCKED;
            }

            if (!user.getPassword().equals(password)) {
                user.registerFailedAttempt();
                users.update(username, user);
                if (user.isBlocked()) {
                    return LoginResult.ACCOUNT_BLOCKED;
                }
                return LoginResult.WRONG_PASSWORD;
            }

            user.resetFailedAttempts();
            users.update(username, user);
            return LoginResult.SUCCESS;

        } catch (KeyNotFoundException e) {
            return LoginResult.USER_NOT_FOUND;
        }
    }

    public int getRemainingAttempts(String username) {
        if (!users.containsKey(username)) {
            return -1;
        }
        try {
            return users.get(username).getRemainingAttempts();
        } catch (KeyNotFoundException e) {
            return -1;
        }
    }

    public boolean isBlocked(String username) {
        if (!users.containsKey(username)) {
            return false;
        }
        try {
            return users.get(username).isBlocked();
        } catch (KeyNotFoundException e) {
            return false;
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
