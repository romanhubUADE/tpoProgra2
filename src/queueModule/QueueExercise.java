package queueModule;

import application.Exercise;
import java.util.Scanner;

/**
 * Ejercicio interactivo para probar el TDA Cola por consola.
 * Permite encolar, desencolar, ver el frente y limpiar la cola.
 * Usa un sistema de fases para navegar entre pantallas sin bucles anidados.
 */
public class QueueExercise extends Exercise {
    private int currentPhase = 0; // fase actual (0 = menú principal)
    private boolean firstTime = true; // para mostrar el saludo solo la primera vez
    private SimpleQueue<String> queue;

    /** Inicializa el ejercicio con la implementación elegida de la cola. */
    public QueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayQueue<>(); // cambiar por SimpleLinkedQueue o SimpleArrayQueue para comparar
    }

    /** Despacha la ejecución a la lógica de la fase actual. */
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

    /** Muestra el menú principal y lee la opción del usuario para cambiar de fase. */
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

    /** Lee un String del usuario y lo encola. Pregunta si quiere agregar otro. */
    private void enqueueLogic() {
        System.out.println("\nEnter a String to enqueue:");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 0;
            return;
        }
        queue.enqueue(value);
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

    /** Desencola el elemento del frente y lo muestra. Pregunta si quiere desencolar otro. */
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

    /** Muestra el elemento del frente sin sacarlo de la cola. */
    private void peekLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nFront element: " + queue.peek());
        }
        currentPhase = 0; // peek siempre vuelve al menú
    }

    /** Limpia la cola si no está vacía; avisa al usuario en ambos casos. */
    private void clearLogic() {
        if (!queue.isEmpty()) {
            queue.clear();
            System.out.println("\nQueue cleared.");
        } else {
            System.out.println("\nQueue is already empty.");
        }
        currentPhase = 0;
    }

    /** Imprime el tamaño actual y si la cola está vacía. */
    private void printStatus() {
        System.out.println("Size: " + queue.size() + " | Empty: " + queue.isEmpty());
    }
}
