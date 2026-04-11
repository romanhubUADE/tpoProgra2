package queueModule;

import application.Exercise;

import java.util.Scanner;

public class QueueExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleQueue<Integer> queue;

    public QueueExercise(Scanner scnr) {
        super(scnr);
        queue = new SimpleArrayQueue<>();
        // queue = new SimpleLinkedQueue<>(); // para probar implementación con nodos
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                enqueueLogic();
                break;
            case 2:
                dequeueLogic();
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
            System.out.println("\nWelcome to the Queue Exercise");
        } else {
            printStatus();
        }

        System.out.println(
                "\nChoose an option" +
                        "\nenqueue: Add element" +
                        "\ndequeue: Remove first element" +
                        "\npeek: View first element" +
                        "\nsize: Show size" +
                        "\nisempty: Check if empty" +
                        "\nclear: Clear queue" +
                        "\nmm: Return to Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "enqueue":
                currentPhase = 1;
                break;
            case "dequeue":
                currentPhase = 2;
                break;
            case "peek":
                currentPhase = 3;
                break;
            case "clear":
                currentPhase = 4;
                break;
            case "size":
                System.out.println("Size: " + queue.size());
                break;
            case "isempty":
                System.out.println("Is empty: " + queue.isEmpty());
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Invalid choice, try again.");
        }
    }

    private void printStatus() {
        System.out.println("\nCurrent size: " + queue.size());
        System.out.println("Is empty: " + queue.isEmpty());
    }

    private void enqueueLogic() {
        System.out.print("Enter a number to enqueue: ");
        int value = Integer.parseInt(scanner.nextLine());
        queue.enqueue(value);

        System.out.println("Elemento agregado");

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nEnqueue another element? y/n");
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

    private void dequeueLogic() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            currentPhase = 0;
            return;
        }

        System.out.println("Removed: " + queue.dequeue());

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nDequeue again? y/n");
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
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("First: " + queue.peek());
        }
        currentPhase = 0;
    }

    private void clearLogic() {
        if (queue.isEmpty()) {
            System.out.println("Queue already empty");
        } else {
            queue.clear();
            System.out.println("Queue cleared");
        }
        currentPhase = 0;
    }
}