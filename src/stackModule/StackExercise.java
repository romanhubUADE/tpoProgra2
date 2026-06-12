package stackModule;

import application.Exercise;
import java.util.Scanner;

/**
 * Ejercicio interactivo para probar las operaciones de una SimpleStack.
 * El usuario puede apilar (push), desapilar (pop), ver el tope (peek) y limpiar la pila.
 */
public class StackExercise extends Exercise {
    private int currentPhase = 0; // controla en qué paso del flujo estamos
    private boolean firstTime = true;
    private SimpleStack<String> stack;

    /** Inicializa el ejercicio con una SimpleArrayStack (se puede cambiar por SimpleLinkedStack). */
    public StackExercise(Scanner scanner) {
        super(scanner);
        stack = new SimpleArrayStack<>(); // cambiar por SimpleLinkedStack para comparar
    }

    /**
     * Punto de entrada del bucle del ejercicio.
     * Delega a la lógica correspondiente según la fase actual.
     */
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

    /** Muestra el menú principal y lee la opción del usuario. */
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

    /** Pide un String al usuario y lo apila en el tope. Permite apilar varios seguidos. */
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

    /** Retira el tope de la pila y lo muestra. Permite desapilar varios seguidos. */
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

    /** Muestra el elemento del tope sin retirarlo y vuelve al menú. */
    private void peekLogic() {
        if (stack.isEmpty()) {
            System.out.println("\nStack is empty.");
        } else {
            System.out.println("\nTop element: " + stack.peek());
        }
        currentPhase = 0; // peek siempre vuelve al menú
    }

    /** Vacía la pila si tiene elementos y vuelve al menú. */
    private void clearLogic() {
        if (!stack.isEmpty()) {
            stack.clear();
            System.out.println("\nStack cleared.");
        } else {
            System.out.println("\nStack is already empty.");
        }
        currentPhase = 0;
    }

    /** Muestra el tamaño actual y si la pila está vacía. */
    private void printStatus() {
        System.out.println("Size: " + stack.size() + " | Empty: " + stack.isEmpty());
    }
}
