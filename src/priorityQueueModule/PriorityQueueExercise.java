package priorityQueueModule;

import application.Exercise;
import java.util.Scanner;

public class PriorityQueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimplePriorityQueue<String> queue;

    public PriorityQueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleLinkedPriorityQueue<>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();    break;
            case 1: enqueueLogic(); break;
            case 2: dequeueLogic(); break;
            case 3: peekLogic();    break;
            case 4: highestLogic(); break;
            case 5: clearLogic();   break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Priority Queue Exercise.");
            System.out.println("Note: lower priority value = higher priority.");
        } else {
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\nenqueue - Add element with priority."
            + "\ndequeue - Remove and return highest priority element."
            + "\npeek    - View highest priority element."
            + "\nhigh    - Show highest priority value."
            + "\nclear   - Clear the queue."
            + "\nmm      - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "enqueue": currentPhase = 1; break;
            case "dequeue": currentPhase = 2; break;
            case "peek":    currentPhase = 3; break;
            case "high":    currentPhase = 4; break;
            case "clear":   currentPhase = 5; break;
            case "mm":      running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void enqueueLogic() {
        System.out.println("\nEnter a String to enqueue:");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 0;
            return;
        }

        int priority = readPriority();
        queue.enqueue(value, priority);
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nEnqueue another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void dequeueLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
            currentPhase = 0;
            return;
        }
        System.out.println("\nDequeued: " + queue.dequeue());
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nDequeue another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void peekLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nFront element: " + queue.peek());
        }
        currentPhase = 0;
    }

    private void highestLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nHighest priority value: " + queue.getHighestPriority());
        }
        currentPhase = 0;
    }

    private void clearLogic() {
        if (!queue.isEmpty()) {
            queue.clear();
            System.out.println("\nQueue cleared.");
        } else {
            System.out.println("\nQueue is already empty.");
        }
        currentPhase = 0;
    }

    private int readPriority() {
        while (true) {
            System.out.println("Enter priority (integer):");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    private void printStatus() {
        System.out.println("Size: " + queue.size() + " | Empty: " + queue.isEmpty());
    }
}
