package stackModule;

import application.Exercise;
import java.util.Scanner;

public class StackExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleStack<String> stack;

    public StackExercise(Scanner scanner) {
        super(scanner);
        stack = new SimpleArrayStack<>(); // cambiar por SimpleLinkedStack para comparar
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();  break;
            case 1: pushLogic();  break;
            case 2: popLogic();   break;
            case 3: peekLogic();  break;
            case 4: clearLogic(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Stack Exercise.");
        } else {
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\npush  - Add element to the top."
            + "\npop   - Remove and return top element."
            + "\npeek  - View top element."
            + "\nclear - Clear the stack."
            + "\nmm    - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "push":  currentPhase = 1; break;
            case "pop":   currentPhase = 2; break;
            case "peek":  currentPhase = 3; break;
            case "clear": currentPhase = 4; break;
            case "mm":    running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void pushLogic() {
        System.out.println("\nEnter a String to push:");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 0;
            return;
        }
        stack.push(value);
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPush another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void popLogic() {
        if (stack.isEmpty()) {
            System.out.println("\nStack is empty.");
            currentPhase = 0;
            return;
        }
        System.out.println("\nPopped: " + stack.pop());
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPop another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void peekLogic() {
        if (stack.isEmpty()) {
            System.out.println("\nStack is empty.");
        } else {
            System.out.println("\nTop element: " + stack.peek());
        }
        currentPhase = 0; // peek siempre vuelve al menú
    }

    private void clearLogic() {
        if (!stack.isEmpty()) {
            stack.clear();
            System.out.println("\nStack cleared.");
        } else {
            System.out.println("\nStack is already empty.");
        }
        currentPhase = 0;
    }

    private void printStatus() {
        System.out.println("Size: " + stack.size() + " | Empty: " + stack.isEmpty());
    }
}
