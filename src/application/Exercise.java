package application;
import java.util.*;


public abstract class Exercise {
    protected boolean running = true;
    protected Scanner scanner;

    public Exercise(Scanner scnr){
        scanner=scnr;
    }

    public void run(){
        while(running){
            exerciseLogic();
        }
    }

    protected abstract void exerciseLogic();
}
