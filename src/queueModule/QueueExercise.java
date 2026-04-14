package queueModule;

import application.Exercise;
import java.util.Scanner;

public class QueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleQueue<String> queue;

    public QueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayQueue<>(); // cambiar por SimpleLinkedQueue o SimpleArrayQueue para comparar
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();    break;
            case 1: enqueueLogic(); break;
            case 2: dequeueLogic(); break;
            case 3: peekLogic();    break;
            case 4: clearLogic();   break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Queue Exercise.");
        } else {
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\nenqueue - Add element to the back."
            + "\ndequeue - Remove and return front element."
            + "\npeek    - View front element."
            + "\nclear   - Clear the queue."
            + "\nmm      - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "enqueue": currentPhase = 1; break;
            case "dequeue": currentPhase = 2; break;
            case "peek":    currentPhase = 3; break;
            case "clear":   currentPhase = 4; break;
            case "mm":      running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void enqueueLogic() {
        System.out.println("\nEnter a String to enqueue:");
        queue.enqueue(scanner.nextLine());
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
        currentPhase = 0; // peek siempre vuelve al menú
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

    private void printStatus() {
        System.out.println("Size: " + queue.size() + " | Empty: " + queue.isEmpty());
    }
}
