package application;

import java.util.Scanner;

public class TestExercise extends Exercise {

    public TestExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("\nWelcome to Test Exercise."
            + "\n0 - Back to Main Menu"
        );
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "0":

                running = false;
                break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }
}
