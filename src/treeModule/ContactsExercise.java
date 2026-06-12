package treeModule;

import application.Exercise;
import java.util.Scanner;

public class ContactsExercise extends Exercise {

    private static final int PHASE_MENU        = 0;
    private static final int PHASE_ADD         = 1;
    private static final int PHASE_SEARCH      = 2;
    private static final int PHASE_EDIT_MENU   = 3;
    private static final int PHASE_EDIT_NAME   = 4;
    private static final int PHASE_EDIT_PHONE  = 5;
    private static final int PHASE_EDIT_EMAIL  = 6;
    private static final int PHASE_DELETE      = 7;
    private static final int PHASE_SHOW_ALL    = 8;

    private int currentPhase = PHASE_MENU;
    private boolean firstTime = true;

    private final SimpleAVL<Contact> avl;
    private Contact selectedContact;

    public ContactsExercise(Scanner scanner) {
        super(scanner);
        avl = new SimpleAVL<>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case PHASE_MENU:       menuLogic();      break;
            case PHASE_ADD:        addLogic();       break;
            case PHASE_SEARCH:     searchLogic();    break;
            case PHASE_EDIT_MENU:  editMenuLogic();  break;
            case PHASE_EDIT_NAME:  editNameLogic();  break;
            case PHASE_EDIT_PHONE: editPhoneLogic(); break;
            case PHASE_EDIT_EMAIL: editEmailLogic(); break;
            case PHASE_DELETE:     deleteLogic();    break;
            case PHASE_SHOW_ALL:   showAllLogic();   break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Contacts Application.");
        }
        System.out.println("\nTotal contacts: " + avl.size());
        System.out.println("\nChoose an option:"
            + "\nadd    - Add a new contact"
            + "\nsearch - Search contact by name"
            + "\nall    - Show all contacts"
            + "\nload   - Load sample data"
            + "\nmm     - Main Menu"
        );

        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "add":    currentPhase = PHASE_ADD;      break;
            case "search": currentPhase = PHASE_SEARCH;   break;
            case "all":    currentPhase = PHASE_SHOW_ALL; break;
            case "load":   loadSampleData();               break;
            case "mm":     running = false;                break;
            default:       System.out.println("Invalid input, try again.");
        }
    }

    private void addLogic() {
        System.out.println("\n-- Add Contact --");

        String name = readNonEmpty("Name");
        if (name == null) { currentPhase = PHASE_MENU; return; }

        if (avl.contains(new Contact(name))) {
            System.out.println("A contact named \"" + name + "\" already exists.");
            currentPhase = PHASE_MENU;
            return;
        }

        String phone = readNonEmpty("Phone");
        if (phone == null) { currentPhase = PHASE_MENU; return; }

        String email = readNonEmpty("Email");
        if (email == null) { currentPhase = PHASE_MENU; return; }

        try {
            Contact c = new Contact(name, phone, email);
            avl.insert(c);
            System.out.println("Contact added successfully:\n  " + c);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = PHASE_MENU;
    }

    private void searchLogic() {
        System.out.println("\n-- Search Contact --");

        String name = readNonEmpty("Contact name");
        if (name == null) { currentPhase = PHASE_MENU; return; }

        Contact found = avl.search(new Contact(name));
        if (found == null) {
            System.out.println("No contact found with name \"" + name + "\".");
            currentPhase = PHASE_MENU;
            return;
        }

        System.out.println("Found:\n  " + found);
        selectedContact = found;

        boolean chosen = false;
        while (!chosen) {
            System.out.println("\nWhat do you want to do?"
                + "\nedit   - Edit this contact"
                + "\ndelete - Delete this contact"
                + "\nback   - Go back"
            );
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "edit":   currentPhase = PHASE_EDIT_MENU; chosen = true; break;
                case "delete": currentPhase = PHASE_DELETE;    chosen = true; break;
                case "back":   currentPhase = PHASE_MENU;      chosen = true; break;
                default:       System.out.println("Invalid input, try again.");
            }
        }
    }

    private void editMenuLogic() {
        System.out.println("\n-- Edit Contact --");
        System.out.println("Current:\n  " + selectedContact);
        System.out.println("\nWhat to edit?"
            + "\nname  - Name"
            + "\nphone - Phone"
            + "\nemail - Email"
            + "\nback  - Cancel"
        );

        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "name":  currentPhase = PHASE_EDIT_NAME;  break;
            case "phone": currentPhase = PHASE_EDIT_PHONE; break;
            case "email": currentPhase = PHASE_EDIT_EMAIL; break;
            case "back":  currentPhase = PHASE_MENU;       break;
            default:      System.out.println("Invalid input, try again.");
        }
    }

    private void editNameLogic() {
        String newName = readNonEmpty("New name (current: " + selectedContact.getName() + ")");
        if (newName == null) { currentPhase = PHASE_EDIT_MENU; return; }

        if (newName.equalsIgnoreCase(selectedContact.getName())) {
            System.out.println("Name unchanged.");
            currentPhase = PHASE_MENU;
            return;
        }

        if (avl.contains(new Contact(newName))) {
            System.out.println("A contact named \"" + newName + "\" already exists.");
            currentPhase = PHASE_EDIT_MENU;
            return;
        }

        try {

            avl.remove(selectedContact);
            selectedContact.setName(newName);
            avl.insert(selectedContact);
            System.out.println("Name updated:\n  " + selectedContact);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = PHASE_MENU;
    }

    private void editPhoneLogic() {
        String newPhone = readNonEmpty("New phone (current: " + selectedContact.getPhone() + ")");
        if (newPhone == null) { currentPhase = PHASE_EDIT_MENU; return; }

        try {
            selectedContact.setPhone(newPhone);
            System.out.println("Phone updated:\n  " + selectedContact);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = PHASE_MENU;
    }

    private void editEmailLogic() {
        String newEmail = readNonEmpty("New email (current: " + selectedContact.getEmail() + ")");
        if (newEmail == null) { currentPhase = PHASE_EDIT_MENU; return; }

        try {
            selectedContact.setEmail(newEmail);
            System.out.println("Email updated:\n  " + selectedContact);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        currentPhase = PHASE_MENU;
    }

    private void deleteLogic() {
        System.out.println("\nAre you sure you want to delete \"" + selectedContact.getName() + "\"? (y/n)");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            try {
                avl.remove(selectedContact);
                System.out.println("Contact deleted.");
                selectedContact = null;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Delete cancelled.");
        }
        currentPhase = PHASE_MENU;
    }

    private void showAllLogic() {
        if (avl.isEmpty()) {
            System.out.println("\nNo contacts saved.");
        } else {
            System.out.println("\n-- All Contacts (" + avl.size() + ") – alphabetical order --");
            Object[] contacts = avl.inOrder();
            for (int i = 0; i < contacts.length; i++) {
                System.out.println((i + 1) + ". " + contacts[i]);
            }
        }
        currentPhase = PHASE_MENU;
    }

    private void loadSampleData() {
        if (!avl.isEmpty()) {
            System.out.println("\nThere are already " + avl.size() + " contact(s). Clear and reload? (y/n)");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("y")) {
                System.out.println("Load cancelled.");
                return;
            }
            avl.clear();
        }

        avl.insert(new Contact("Franco Armani",       "+54 9 11 2022-0001", "franco.armani@afa.org.ar"));
        avl.insert(new Contact("Juan Foyth",          "+54 9 11 2022-0002", "juan.foyth@afa.org.ar"));
        avl.insert(new Contact("Nicolas Tagliafico",  "+54 9 11 2022-0003", "nicolas.tagliafico@afa.org.ar"));
        avl.insert(new Contact("Gonzalo Montiel",     "+54 9 11 2022-0004", "gonzalo.montiel@afa.org.ar"));
        avl.insert(new Contact("Leandro Paredes",     "+54 9 11 2022-0005", "leandro.paredes@afa.org.ar"));
        avl.insert(new Contact("German Pezzella",     "+54 9 11 2022-0006", "german.pezzella@afa.org.ar"));
        avl.insert(new Contact("Rodrigo De Paul",     "+54 9 11 2022-0007", "rodrigo.depaul@afa.org.ar"));
        avl.insert(new Contact("Marcos Acuna",        "+54 9 11 2022-0008", "marcos.acuna@afa.org.ar"));
        avl.insert(new Contact("Julian Alvarez",      "+54 9 11 2022-0009", "julian.alvarez@afa.org.ar"));
        avl.insert(new Contact("Lionel Messi",        "+54 9 11 2022-0010", "lionel.messi@afa.org.ar"));
        avl.insert(new Contact("Angel Di Maria",      "+54 9 11 2022-0011", "angel.dimaria@afa.org.ar"));
        avl.insert(new Contact("Geronimo Rulli",      "+54 9 11 2022-0012", "geronimo.rulli@afa.org.ar"));
        avl.insert(new Contact("Cristian Romero",     "+54 9 11 2022-0013", "cristian.romero@afa.org.ar"));
        avl.insert(new Contact("Exequiel Palacios",   "+54 9 11 2022-0014", "exequiel.palacios@afa.org.ar"));
        avl.insert(new Contact("Angel Correa",        "+54 9 11 2022-0015", "angel.correa@afa.org.ar"));
        avl.insert(new Contact("Thiago Almada",       "+54 9 11 2022-0016", "thiago.almada@afa.org.ar"));
        avl.insert(new Contact("Alejandro Gomez",     "+54 9 11 2022-0017", "alejandro.gomez@afa.org.ar"));
        avl.insert(new Contact("Guido Rodriguez",     "+54 9 11 2022-0018", "guido.rodriguez@afa.org.ar"));
        avl.insert(new Contact("Nicolas Otamendi",    "+54 9 11 2022-0019", "nicolas.otamendi@afa.org.ar"));
        avl.insert(new Contact("Alexis Mac Allister", "+54 9 11 2022-0020", "alexis.macallister@afa.org.ar"));
        avl.insert(new Contact("Paulo Dybala",        "+54 9 11 2022-0021", "paulo.dybala@afa.org.ar"));
        avl.insert(new Contact("Lautaro Martinez",    "+54 9 11 2022-0022", "lautaro.martinez@afa.org.ar"));
        avl.insert(new Contact("Emiliano Martinez",   "+54 9 11 2022-0023", "emiliano.martinez@afa.org.ar"));
        avl.insert(new Contact("Enzo Fernandez",      "+54 9 11 2022-0024", "enzo.fernandez@afa.org.ar"));
        avl.insert(new Contact("Nahuel Molina",       "+54 9 11 2022-0025", "nahuel.molina@afa.org.ar"));
        avl.insert(new Contact("Lisandro Martinez",   "+54 9 11 2022-0026", "lisandro.martinez@afa.org.ar"));

        System.out.println("Sample data loaded. Total contacts: " + avl.size());
    }

    private String readNonEmpty(String fieldName) {
        while (true) {
            System.out.println(fieldName + " (or 'back' to cancel):");
            String value = scanner.nextLine().trim();
            if (value.equalsIgnoreCase("back")) return null;
            if (!value.isEmpty()) return value;
            System.out.println("  Field cannot be empty, try again.");
        }
    }
}
