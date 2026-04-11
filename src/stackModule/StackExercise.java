package stackModule;

import application.Exercise;

import java.util.Scanner;

public class StackExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleStack<Integer> stack;

    public StackExercise(Scanner scnr) {
        super(scnr);
        stack = new SimpleArrayStack<>();
        // stack = new SimpleLinkedStack<>(); // para probar la otra implementación
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                pushLogic();
                break;
            case 2:
                popLogic();
                break;
            case 3:
                peekLogic();
                break;
            case 4:
                clearLogic();
                break;
        }
    }

    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Stack Exercise");
        } else {
            printStatus();
        }

        System.out.println(
                "\nChoose an option" +
                        "\npush: Add element" +
                        "\npop: Remove top element" +
                        "\npeek: View top element" +
                        "\nsize: Show size" +
                        "\nisempty: Check if empty" +
                        "\nclear: Clear stack" +
                        "\nmm: Return to Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "push":
                currentPhase = 1;
                break;
            case "pop":
                currentPhase = 2;
                break;
            case "peek":
                currentPhase = 3;
                break;
            case "clear":
                currentPhase = 4;
                break;
            case "size":
                System.out.println("Size: " + stack.size());
                break;
            case "isempty":
                System.out.println("Is empty: " + stack.isEmpty());
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Invalid choice, try again.");
                break;
        }
    }

    private void printStatus() {
        System.out.println("\nCurrent size: " + stack.size());
        System.out.println("Is empty: " + stack.isEmpty());
    }

    private void pushLogic() {
        System.out.print("Enter a number to push: ");
        int value = Integer.parseInt(scanner.nextLine());
        stack.push(value);

        System.out.println("Elemento agregado");

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPush another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y":
                    validInput = true;
                    break;
                case "n":
                    validInput = true;
                    currentPhase = 0;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void popLogic() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            currentPhase = 0;
            return;
        }

        System.out.println("Removed: " + stack.pop());

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPop again? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y":
                    validInput = true;
                    break;
                case "n":
                    validInput = true;
                    currentPhase = 0;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void peekLogic() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Top: " + stack.peek());
        }
        currentPhase = 0;
    }

    private void clearLogic() {
        if (stack.isEmpty()) {
            System.out.println("Stack already empty");
        } else {
            stack.clear();
            System.out.println("Stack cleared");
        }
        currentPhase = 0;
    }
}
