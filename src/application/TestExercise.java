package application;

import java.util.Scanner;

/**
 * Ejercicio de prueba (sandbox). Sirve como plantilla de referencia
 * para implementar nuevos ejercicios a partir de Exercise.
 */
public class TestExercise extends Exercise {

    /** Recibe el scanner compartido y lo pasa a la clase base. */
    public TestExercise(Scanner scanner) {
        super(scanner);
    }

    /**
     * Muestra el menú del ejercicio de prueba.
     * Por ahora solo tiene la opción de volver al menú principal.
     */
    @Override
    protected void exerciseLogic() {
        System.out.println("\nWelcome to Test Exercise."
            + "\n0 - Back to Main Menu"
        );
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "0":
                // Pone running en false para salir del bucle de Exercise.run()
                running = false;
                break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }
}
