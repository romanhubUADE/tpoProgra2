package list;

import application.Exercise;

import java.util.*;

public class ListExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private List<String> list;

    public ListExercise(Scanner scnr){
        super(scnr);
        list=new ArrayList<>();
    }

    @Override
    protected void exerciseLogic(){
        switch(currentPhase){
            case 0:
                menuLogic();
                break;
            case 1:
                addLogic();
                break;
            case 2:
                removeByIndexLogic();
                break;
            case 3:
                removeByReferenceLogic();
                break;
            case 4:
                clearLogic();
                break;

        }
    }
    private void menuLogic(){

        if(firstTime){
            firstTime=false;
            System.out.println("\nWelcome to the list Exercise");
        }
        else{
            printList();
            //se puede agregar numeros, letras y bla bla bla?
            //printStatus(); no se podria poner un .size?
            // o la funcion sirve para ver el tamano y si esta vacia todo junto?
        }
        System.out.println(
                "\n Choose an option"+
                "\n add: add element"+
                "\n removeIndex: Remove element by index"+
                "\n removeRef: Remove element by reference"+
                "\n clear: Clear list" +
                "\n mm: Return to Main Menu");
        String userInput=scanner.nextLine().toLowerCase();
        //aca no podria usar toLowerCase porque las opciones tienen mayusculas?
        switch (userInput){
            case "add":
                currentPhase=1;
                break;
            case "removeindex":
                currentPhase=2;
                break;
            case "removeref":
                currentPhase=3;
                break;
            case "clear":
                currentPhase=4;
                break;
            case "mm":
                running=false;
                break;
            default:
                System.out.println("Invalid choice, try again.");
                menuLogic();
                break;
        }
    }
    private void printList(){
        String fullList="";
        if(list.size() == 0){
            System.out.println("La lista esta vacia");
        }
        else{
        for(int i=0; i<list.size();i++) {
            fullList += list.get(i);
            if (i < list.size() - 1) {
                fullList += ", ";
            }
        }
        }
        System.out.println("\nCurrent list: "+fullList);
        System.out.println("\nCurrent size: "+list.size());
    }
    private void addLogic(){
        System.out.println("\n Enter a String to add: ");
        list.add(scanner.nextLine());
        printList();

        boolean validInput=false;
        while(!validInput){
            System.out.println("\nAdd another element? y/n");
            String userInput=scanner.nextLine().toLowerCase();
            switch (userInput){
                case "y":
                    validInput=true;
                    break;
                case "n":
                    validInput=true;
                    currentPhase=0;
                    break;
                default:
            }
        }

    }

    private void removeByIndexLogic(){
        System.out.print("Enter index to remove: ");
        int indice=scanner.nextInt();
        if (indice<list.size()) {
            System.out.print("a");
            list.remove(indice);
            scanner.nextLine();
            printList();

            boolean validInput = false;
            while (!validInput) {
                System.out.println("\nQuere borrar otro?????? y/n");
                String userInput = scanner.nextLine().toLowerCase();
                switch (userInput) {
                    case "y":
                        validInput = true;
                        break;
                    case "n":
                        validInput = true;
                        currentPhase = 0;
                        break;
                    default:
                }
            }

        }
        else{
            System.out.print("El indice no se encuentra en la lista\n");

        }

    }
    private void removeByReferenceLogic(){
        // preguntar al profe si esta bien que borre solo un dato o todos
        System.out.print("Enter element to remove: ");
        String referencia = scanner.nextLine();
        list.remove(referencia);
        printList();

        boolean validInput=false;
        while(!validInput){
            System.out.println("\n Quere borrar otro por refe? y/n");
            String userInput=scanner.nextLine().toLowerCase();
            switch (userInput){
                case "y":
                    validInput=true;
                    break;
                case "n":
                    validInput=true;
                    currentPhase=0;
                    break;
                default:
            }

        }

    }
    private void clearLogic(){
        list.clear();
        currentPhase = 0;


    }
}
