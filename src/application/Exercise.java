package application;

import java.util.Scanner;

/**
 * Clase base abstracta para todos los ejercicios del TP.
 * Cada ejercicio concreto hereda de acá e implementa su propia lógica en exerciseLogic().
 */
public abstract class Exercise {
    // Controla si el ejercicio sigue corriendo o debe volver al menú principal
    protected boolean running = true;
    protected Scanner scanner;

    /** Recibe el scanner compartido desde el programa principal. */
    public Exercise(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Bucle principal del ejercicio: ejecuta la lógica repetidamente
     * hasta que running sea false, y luego resetea el flag para la próxima vez.
     */
    public void run() {
        while (running) {
            exerciseLogic();
        }
        // Resetea el flag para poder reutilizar el ejercicio si se vuelve a seleccionar
        running = true;
    }

    /** Cada subclase implementa acá su menú y acciones específicas. */
    protected abstract void exerciseLogic();
}
