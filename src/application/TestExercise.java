package application;
import java.util.*;


public class TestExercise extends Exercise{

    public TestExercise(Scanner scnr){
        super(scnr);
    }

    @Override
    protected void exerciseLogic(){
        System.out.println("Welcome to the test exercise"+
                "\nmm: Main Menu");

        String userInput=scanner.nextLine().toLowerCase();
        if (userInput.equals("mm")){
            running =false;
        }
    }

}
