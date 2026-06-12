package application;

import java.util.Scanner;
import listModule.ListExercise;
import stackModule.StackExercise;
import queueModule.QueueExercise;
import setModule.SetExercise;
import priorityQueueModule.PriorityQueueExercise;
import dictionaryModule.DictionaryExercise;
import emailModule.EmailExercise;
import authModule.AuthExercise;
import graphModule.FlightExercise;
import treeModule.ContactsExercise;

/**
 * Punto de entrada del programa. Muestra el menú principal y delega
 * la ejecución al ejercicio que elija el usuario.
 */
public class MainProgram {
    private boolean running = true;
    // Referencia al ejercicio activo en cada iteración del menú
    private Exercise exercise;

    /** Arranca la aplicación. */
    public static void main(String[] args) {
        new MainProgram().run();
    }

    /**
     * Bucle principal: muestra el menú de selección y corre el ejercicio elegido.
     * Termina cuando el usuario elige la opción 0.
     */
    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            selectExercise(scanner);
            if (exercise != null && running) {
                exercise.run();
            }
        }
        scanner.close();
        System.out.println("Program terminated.");
    }

    /**
     * Muestra el menú de opciones y crea el ejercicio correspondiente
     * según la elección del usuario. Se llama recursivamente ante entrada inválida.
     */
    private void selectExercise(Scanner scanner) {
        System.out.println("\nSelect an option:"
            + "\n0 - Terminate Program"
            + "\n1 - Test Exercise"
            + "\n2 - List Exercise"
            + "\n3 - Stack Exercise"
            + "\n4 - Queue Exercise"
            + "\n5 - Set Exercise"
            + "\n6 - Priority Queue Exercise"
            + "\n7 - Dictionary Exercise"
            + "\n8 - Email Exercise (TP6)"
            + "\n9 - Auth Exercise (TP7)"
            + "\n10 - Flight Route Planner (TP10 - Graph + Dijkstra)"
            + "\n11 - Contacts Exercise (TP8/TP9 - AVL)"
        );
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "0":
                running = false;
                break;
            case "1":
                exercise = new TestExercise(scanner);
                break;
            case "2":
                exercise = new ListExercise(scanner);
                break;
            case "3":
                exercise = new StackExercise(scanner);
                break;
            case "4":
                exercise = new QueueExercise(scanner);
                break;
            case "5":
                exercise = new SetExercise(scanner);
                break;
            case "6":
                exercise = new PriorityQueueExercise(scanner);
                break;
            case "7":
                exercise = new DictionaryExercise(scanner);
                break;
            case "8":
                exercise = new EmailExercise(scanner);
                break;
            case "9":
                exercise = new AuthExercise(scanner);
                break;
            case "10":
                exercise = new FlightExercise(scanner);
                break;
            case "11":
                exercise = new ContactsExercise(scanner);
                break;
            default:
                System.out.println("Invalid input, try again.");
                // Vuelve a pedir entrada hasta que el usuario ingrese una opción válida
                selectExercise(scanner);
                break;
        }
    }
}
