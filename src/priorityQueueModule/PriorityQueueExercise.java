package priorityQueueModule;

import application.Exercise;
import java.util.Scanner;

/**
 * Ejercicio interactivo para probar la cola de prioridad desde la consola.
 * Permite encolar y desencolar strings asignándoles un valor de prioridad,
 * y observar el comportamiento del TDA en tiempo real.
 */
public class PriorityQueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimplePriorityQueue<String> queue;

    public PriorityQueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayPriorityQueue<>(); // cambiar por SimpleLinkedPriorityQueue o SimpleArrayPriorityQueue para comparar
    }

    /** Delega en el método correspondiente según la fase actual del flujo de menús. */
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

    /** Muestra el menú principal y deriva a la fase elegida por el usuario. */
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

    /** Pide un string y una prioridad, y los encola. Ofrece encolar otro sin volver al menú. */
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

    /** Desencola el elemento de mayor urgencia y lo muestra. Ofrece desencolar otro. */
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

    /** Muestra el elemento de mayor urgencia sin sacarlo. */
    private void peekLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nFront element: " + queue.peek());
        }
        currentPhase = 0;
    }

    /** Muestra el valor numérico de la prioridad más urgente que hay en la cola. */
    private void highestLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nHighest priority value: " + queue.getHighestPriority());
        }
        currentPhase = 0;
    }

    /** Vacía la cola completa si el usuario confirma. */
    private void clearLogic() {
        if (!queue.isEmpty()) {
            queue.clear();
            System.out.println("\nQueue cleared.");
        } else {
            System.out.println("\nQueue is already empty.");
        }
        currentPhase = 0;
    }

    /** Lee un entero desde la consola; repite hasta que el usuario ingrese algo válido. */
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

    /** Imprime en consola el tamaño actual de la cola y si está vacía. */
    private void printStatus() {
        System.out.println("Size: " + queue.size() + " | Empty: " + queue.isEmpty());
    }
}
