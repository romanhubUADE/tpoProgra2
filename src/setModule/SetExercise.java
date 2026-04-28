package setModule;

import application.Exercise;
import java.util.Scanner;

public class SetExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;

    private SimpleSet<String> setA;
    private SimpleSet<String> setB;
    private SimpleSet<String> selectedSet;
    private String selectedSetName;

    public SetExercise(Scanner scanner) {
        super(scanner);
        setA = new SimpleArraySet<>();
        setB = new SimpleArraySet<>();
        selectedSet = null;
        selectedSetName = "";
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: setMenuLogic(); break;
            case 2: addLogic(); break;
            case 3: removeLogic(); break;
            case 4: unionLogic(); break;
            case 5: intersectLogic(); break;
            case 6: differenceABLogic(); break;
            case 7: differenceBALogic(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Set Exercise.");
        } else {
            printSetsStatus();
        }

        System.out.println("\nChoose an option:"
            + "\nsa - Work with Set A"
            + "\nsb - Work with Set B"
            + "\nunion - Show A union B"
            + "\nintersect - Show A intersect B"
            + "\ndiffab - Show A difference B"
            + "\ndiffba - Show B difference A"
            + "\nmm - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "sa":
                selectedSet = setA;
                selectedSetName = "A";
                currentPhase = 1;
                break;
            case "sb":
                selectedSet = setB;
                selectedSetName = "B";
                currentPhase = 1;
                break;
            case "union":
                currentPhase = 4;
                break;
            case "intersect":
                currentPhase = 5;
                break;
            case "diffab":
                currentPhase = 6;
                break;
            case "diffba":
                currentPhase = 7;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void setMenuLogic() {
        System.out.println("\nWorking on Set " + selectedSetName + ": " + formatSet(selectedSet));
        System.out.println("Choose an option:"
            + "\nadd - Add element"
            + "\nremove - Remove element"
            + "\nback - Back to Set main menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "add":
                currentPhase = 2;
                break;
            case "remove":
                currentPhase = 3;
                break;
            case "back":
                currentPhase = 0;
                break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void addLogic() {
        System.out.println("\nEnter a String to add into Set " + selectedSetName + ":");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 1;
            return;
        }
        boolean added = selectedSet.add(value);

        if (added) {
            System.out.println("Added successfully.");
        } else {
            System.out.println("Element already exists in Set " + selectedSetName + ".");
        }

        System.out.println("Set " + selectedSetName + ": " + formatSet(selectedSet));
        repeatOperationQuestion("add");
    }

    private void removeLogic() {
        if (selectedSet.isEmpty()) {
            System.out.println("\nSet " + selectedSetName + " is empty.");
            currentPhase = 1;
            return;
        }

        System.out.println("\nEnter a String to remove from Set " + selectedSetName + ":");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("Value cannot be empty.");
            currentPhase = 1;
            return;
        }
        boolean removed = selectedSet.remove(value);

        if (removed) {
            System.out.println("Removed successfully.");
        } else {
            System.out.println("Element not found in Set " + selectedSetName + ".");
        }

        System.out.println("Set " + selectedSetName + ": " + formatSet(selectedSet));
        repeatOperationQuestion("remove");
    }

    private void unionLogic() {
        SimpleSet<String> result = setA.unionWith(setB);
        System.out.println("\nA union B: " + formatSet(result));
        currentPhase = 0;
    }

    private void intersectLogic() {
        SimpleSet<String> result = setA.intersectWith(setB);
        System.out.println("\nA intersect B: " + formatSet(result));
        currentPhase = 0;
    }

    private void differenceABLogic() {
        SimpleSet<String> result = setA.differenceWith(setB);
        System.out.println("\nA difference B: " + formatSet(result));
        currentPhase = 0;
    }

    private void differenceBALogic() {
        SimpleSet<String> result = setB.differenceWith(setA);
        System.out.println("\nB difference A: " + formatSet(result));
        currentPhase = 0;
    }

    private void repeatOperationQuestion(String operation) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\n" + capitalize(operation) + " another element in Set " + selectedSetName + "? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y":
                    validInput = true;
                    currentPhase = operation.equals("add") ? 2 : 3;
                    break;
                case "n":
                    validInput = true;
                    currentPhase = 1;
                    break;
                default:
                    break;
            }
        }
    }

    private void printSetsStatus() {
        System.out.println("\nSet A: " + formatSet(setA));
        System.out.println("Size: " + setA.size() + " | Empty: " + setA.isEmpty());
        System.out.println("Set B: " + formatSet(setB));
        System.out.println("Size: " + setB.size() + " | Empty: " + setB.isEmpty());
    }

    private String formatSet(SimpleSet<String> set) {
        String result = "{";
        Object[] values = set.toArray();
        for (int i = 0; i < values.length; i++) {
            result += values[i];
            if (i < values.length - 1) result += ", ";
        }
        result += "}";
        return result;
    }

    private String capitalize(String text) {
        if (text.length() == 0) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
