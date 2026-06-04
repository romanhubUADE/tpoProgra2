package application;

import bstModule.ContactsExercise;
import dictionaryModule.DictionaryExercise;
import list.ListExercise;
import priorityQueueModule.PriorityQueueExercise;
import queueModule.QueueExercise;
import setModule.SetExercise;
import stackModule.StackExercise;

import java.util.Scanner;

public class MainProgram {
    protected boolean running = true;
    private Exercise exercise;

    public static void main(String[] args) {
        new MainProgram().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            exercise = null;
            selectExercise(scanner);
            if (exercise != null) {
                exercise.run();
            }
        }
        scanner.close();
    }

    private void selectExercise(Scanner scanner) {
        boolean selected = false;
        while (!selected) {
            System.out.println("\nSelect an option:"
                + "\n0 - Terminate Program"
                + "\n1 - Test Exercise"
                + "\n2 - List Exercise"
                + "\n3 - Stack Exercise"
                + "\n4 - Queue Exercise"
                + "\n5 - Set Exercise"
                + "\n6 - Priority Queue Exercise"
                + "\n7 - Dictionary Exercise"
                + "\n8 - Contacts Application (TP8)"
            );

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "0":
                    System.out.println("Program terminated.");
                    running = false;
                    selected = true;
                    break;
                case "1":
                    exercise = new TestExercise(scanner);
                    selected = true;
                    break;
                case "2":
                    exercise = new ListExercise(scanner);
                    selected = true;
                    break;
                case "3":
                    exercise = new StackExercise(scanner);
                    selected = true;
                    break;
                case "4":
                    exercise = new QueueExercise(scanner);
                    selected = true;
                    break;
                case "5":
                    exercise = new SetExercise(scanner);
                    selected = true;
                    break;
                case "6":
                    exercise = new PriorityQueueExercise(scanner);
                    selected = true;
                    break;
                case "7":
                    exercise = new DictionaryExercise(scanner);
                    selected = true;
                    break;
                case "8":
                    exercise = new ContactsExercise(scanner);
                    selected = true;
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }
}
