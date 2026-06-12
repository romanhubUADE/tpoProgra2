package dictionaryModule;

import application.Exercise;
import java.util.Scanner;
import listModule.SimpleList;

/**
 * Ejercicio interactivo para probar el TDA Diccionario por consola.
 * Permite al usuario agregar, buscar, eliminar y listar entradas.
 * Usa un sistema de fases para manejar el flujo de pantallas sin bucles anidados.
 */
public class DictionaryExercise extends Exercise {
    private int currentPhase = 0; // fase actual del ejercicio (0 = menú principal)
    private boolean firstTime = true; // para mostrar el saludo solo la primera vez
    private SimpleDictionary<String, String> dictionary;

    /** Inicializa el ejercicio con la implementación elegida del diccionario. */
    public DictionaryExercise(Scanner scanner) {
        super(scanner);
        dictionary = new SimpleArrayDictionary<>(); // cambiar por SimpleLinkedDictionary o SimpleArrayDictionary para comparar
    }

    /** Despacha la ejecución a la lógica de la fase actual. */
    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();        break;
            case 1: putLogic();         break;
            case 2: getLogic();         break;
            case 3: removeLogic();      break;
            case 4: containsKeyLogic(); break;
            case 5: keysLogic();        break;
            case 6: valuesLogic();      break;
            case 7: clearLogic();       break;
        }
    }

    /** Muestra el menú principal y lee la opción del usuario para cambiar de fase. */
    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Dictionary Exercise.");
        } else {
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\nput      - Add or replace a key-value pair."
            + "\nget      - Get the value associated to a key."
            + "\nremove   - Remove a key-value pair."
            + "\ncontains - Check if a key exists."
            + "\nkeys     - Show all keys."
            + "\nvalues   - Show all values."
            + "\nclear    - Clear the dictionary."
            + "\nmm       - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "put":      currentPhase = 1; break;
            case "get":      currentPhase = 2; break;
            case "remove":   currentPhase = 3; break;
            case "contains": currentPhase = 4; break;
            case "keys":     currentPhase = 5; break;
            case "values":   currentPhase = 6; break;
            case "clear":    currentPhase = 7; break;
            case "mm":       running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    /** Lee clave y valor del usuario, los inserta en el diccionario y pregunta si quiere agregar otro. */
    private void putLogic() {
        System.out.println("\nEnter a key:");
        String key = scanner.nextLine().trim();
        if (key.isEmpty()) {
            System.out.println("Key cannot be empty.");
            currentPhase = 0;
            return;
        }
        System.out.println("Enter a value:");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 0;
            return;
        }

        String previous = dictionary.put(key, value);
        if (previous == null) {
            System.out.println("Added: (" + key + " -> " + value + ")");
        } else {
            System.out.println("Replaced: (" + key + " -> " + value + "). Previous value: " + previous);
        }
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPut another pair? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    /** Busca una clave ingresada por el usuario y muestra su valor (o avisa si no existe). */
    private void getLogic() {
        if (dictionary.isEmpty()) {
            System.out.println("\nDictionary is empty.");
            currentPhase = 0;
            return;
        }

        System.out.println("\nEnter a key:");
        String key = scanner.nextLine();
        String value = dictionary.get(key);

        if (value == null) {
            System.out.println("Key not found.");
        } else {
            System.out.println("Value: " + value);
        }
        currentPhase = 0;
    }

    /** Lee una clave del usuario y la elimina del diccionario. Pregunta si quiere eliminar otra. */
    private void removeLogic() {
        if (dictionary.isEmpty()) {
            System.out.println("\nDictionary is empty.");
            currentPhase = 0;
            return;
        }

        System.out.println("\nEnter a key to remove:");
        String key = scanner.nextLine();
        boolean removed = dictionary.remove(key);

        if (removed) {
            System.out.println("Removed successfully.");
        } else {
            System.out.println("Key not found.");
        }
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nRemove another key? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    /** Informa si una clave existe en el diccionario. */
    private void containsKeyLogic() {
        System.out.println("\nEnter a key:");
        String key = scanner.nextLine();
        System.out.println(dictionary.containsKey(key) ? "Key exists." : "Key not found.");
        currentPhase = 0;
    }

    /** Muestra todas las claves del diccionario formateadas. */
    private void keysLogic() {
        System.out.println("\nKeys: " + formatList(dictionary.keys()));
        currentPhase = 0;
    }

    /** Muestra todos los valores del diccionario formateados. */
    private void valuesLogic() {
        System.out.println("\nValues: " + formatArray(dictionary.values()));
        currentPhase = 0;
    }

    /** Limpia el diccionario si no está vacío; avisa al usuario en ambos casos. */
    private void clearLogic() {
        if (!dictionary.isEmpty()) {
            dictionary.clear();
            System.out.println("\nDictionary cleared.");
        } else {
            System.out.println("\nDictionary is already empty.");
        }
        currentPhase = 0;
    }

    /** Imprime el tamaño actual y si el diccionario está vacío. */
    private void printStatus() {
        System.out.println("Size: " + dictionary.size() + " | Empty: " + dictionary.isEmpty());
    }

    /** Convierte un arreglo en formato "{val1, val2, ...}" para mostrar por pantalla. */
    private String formatArray(Object[] array) {
        String result = "{";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (i < array.length - 1) result += ", ";
        }
        result += "}";
        return result;
    }

    /** Convierte una SimpleList en formato "{val1, val2, ...}" para mostrar por pantalla. */
    private String formatList(SimpleList<?> list) {
        String result = "{";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
            if (i < list.size() - 1) result += ", ";
        }
        result += "}";
        return result;
    }
}
