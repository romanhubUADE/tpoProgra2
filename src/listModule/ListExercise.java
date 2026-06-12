package listModule;

import application.Exercise;
import java.util.Scanner;

public class ListExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleList<String> list;

    public ListExercise(Scanner scanner) {
        super(scanner);
        list = new SimpleArrayList<>(); // cambiar por SimpleLinkedList para comparar
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();             break;
            case 1: addLogic();              break;
            case 2: removeByIndexLogic();    break;
            case 3: removeByReferenceLogic(); break;
            case 4: clearLogic();            break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the List Exercise.");
        } else {
            printList();
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\nadd - Add element."
            + "\nremoveIndex - Remove element by index."
            + "\nremoveRef - Remove element by reference."
            + "\nclear - Clear list."
            + "\nmm - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "add":         currentPhase = 1; break;
            case "removeindex": currentPhase = 2; break;
            case "removeref":   currentPhase = 3; break;
            case "clear":       currentPhase = 4; break;
            case "mm":          running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void addLogic() {
        System.out.println("\nEnter a String to add:");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 0;
            return;
        }
        list.add(value);
        printList();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nAdd another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void removeByIndexLogic() {
        if (list.isEmpty()) {
            System.out.println("\nList is empty.");
            currentPhase = 0;
            return;
        }

        System.out.println("\nEnter index to remove (0 to " + (list.size() - 1) + "):");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index < 0 || index >= list.size()) {
                System.out.println("Invalid index.");
                currentPhase = 0;
                return;
            }
            list.remove(index);
            printList();

            boolean validInput = false;
            while (!validInput) {
                System.out.println("\nRemove another element by index? y/n");
                String userInput = scanner.nextLine().toLowerCase();
                switch (userInput) {
                    case "y": validInput = true;                    break;
                    case "n": validInput = true; currentPhase = 0; break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            currentPhase = 0;
        }
    }

    private void removeByReferenceLogic() {
        if (list.isEmpty()) {
            System.out.println("\nList is empty.");
            currentPhase = 0;
            return;
        }

        System.out.println("\nEnter element to remove:");
        String element = scanner.nextLine();
        boolean removed = list.remove(element);
        if (!removed) System.out.println("Element not found.");
        printList();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nRemove another element by reference? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    private void clearLogic() {
        list.clear();
        System.out.println("\nList cleared.");
        currentPhase = 0;
    }

    private void printList() {
        String fullList = "";
        for (int i = 0; i < list.size(); i++) {
            fullList += list.get(i);
            if (i < list.size() - 1) fullList += ", ";
        }
        System.out.println("\nCurrent List: " + fullList);
    }

    private void printStatus() {
        System.out.println("Size: " + list.size() + " | Empty: " + list.isEmpty());
    }
}
