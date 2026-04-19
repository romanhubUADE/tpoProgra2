package application;

import email.EmailSystem;
import ui.ConsoleUI;

/**
 * Punto de entrada de la aplicacion.
 *
 * Crea el sistema de correo y la interfaz de consola,
 * luego delega el control a la UI.
 *
 * No contiene logica de negocio ni de presentacion.
 */
public class MainProgram {

    public static void main(String[] args) {
        EmailSystem system = new EmailSystem();
        ConsoleUI ui = new ConsoleUI(system);
        ui.start();
    }
}
