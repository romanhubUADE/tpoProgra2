package authModule;

import application.Exercise;
import authModule.AuthService.LoginResult;
import authModule.AuthService.RegisterResult;
import java.util.Scanner;

public class AuthExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private final AuthService authService;
    private String currentUser;

    public AuthExercise(Scanner scanner) {
        super(scanner);
        authService = new AuthService();
        currentUser = null;
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();      break;
            case 1: registerLogic();  break;
            case 2: loginLogic();     break;
            case 3: loggedInLogic();  break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Auth Exercise.");
        }

        System.out.println("\nChoose an option:"
            + "\nregister - Register a new user."
            + "\nlogin    - Sign in."
            + "\nmm       - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "register": currentPhase = 1; break;
            case "login":    currentPhase = 2; break;
            case "mm":       running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void registerLogic() {
        System.out.println("\n=== REGISTER ===");
        String username = readNonBlank("Username: ");
        String password = readNonBlank("Password: ");

        RegisterResult result = authService.register(username, password);
        switch (result) {
            case SUCCESS:
                System.out.println("User '" + username + "' registered successfully.");
                break;
            case USERNAME_ALREADY_EXISTS:
                System.out.println("Username '" + username + "' is already taken.");
                break;
            case INVALID_INPUT:
                System.out.println("Username and password cannot be empty.");
                break;
        }

        currentPhase = 0;
    }

    private void loginLogic() {
        System.out.println("\n=== LOGIN ===");
        String username = readNonBlank("Username: ");
        String password = readNonBlank("Password: ");

        LoginResult result = authService.login(username, password);
        switch (result) {
            case SUCCESS:
                System.out.println("Welcome, " + username + "!");
                currentUser = username;
                currentPhase = 3;
                break;
            case ACCOUNT_BLOCKED:
                System.out.println("Account '" + username + "' is BLOCKED due to too many failed attempts.");
                currentPhase = 0;
                break;
            case WRONG_PASSWORD:
                int remaining = authService.getRemainingAttempts(username);
                if (authService.isBlocked(username)) {
                    System.out.println("Wrong password. Account BLOCKED.");
                } else {
                    System.out.println("Wrong password. Remaining attempts: " + remaining + ".");
                }
                currentPhase = 0;
                break;
            case USER_NOT_FOUND:
                System.out.println("User not found.");
                currentPhase = 0;
                break;
            case INVALID_INPUT:
                System.out.println("Username and password cannot be empty.");
                currentPhase = 0;
                break;
        }
    }

    private void loggedInLogic() {
        System.out.println("\nLogged in as " + currentUser + ". Press ENTER to logout.");
        scanner.nextLine();
        System.out.println("Logged out successfully.");
        currentUser = null;
        currentPhase = 0;
    }

    private String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }
}
