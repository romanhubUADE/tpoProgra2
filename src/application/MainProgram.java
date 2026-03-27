package application;
import list.ListExercise;

import java.util.*;

public class MainProgram {
    protected boolean running = true;
    private Exercise exercise;

    public static void main (String[] args){
            new MainProgram().run();

    }
    private void run(){
        Scanner scanner = new Scanner(System.in);
        while(running)
        {
            selectExercise(scanner);
            if(exercise!=null){
                exercise.run();
            }
        }
        scanner.close();
    }

    private void selectExercise(Scanner scanner){
        System.out.println("Select an option: "+
                "\n  0 - Terminate Program"+
                "\n  1 - Test Exercise"+
                "\n  2 - List Exercise");

        String userInput= scanner.nextLine();

        switch(userInput){
            case "0":
                System.out.println("Program terminated.");
                running=false;
                break;
            case "1":
                exercise=new TestExercise(scanner);
                break;
            case "2":
                exercise=new ListExercise(scanner);
                break;
            default:
                System.out.println("Invalid input, try again.");
                selectExercise(scanner);
                break;

        }
    }
}
