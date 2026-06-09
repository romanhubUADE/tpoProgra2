package bstModule;

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

    // Declarado como SimpleBST (abstracción), instanciado como AVL: el árbol
    // se autobalancea (TP09) sin que el resto del ejercicio cambie.
    private final SimpleBST<Contact> bst;
    private Contact selectedContact;

    public ContactsExercise(Scanner scanner) {
        super(scanner);
        bst = new SimpleAVL<>();
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
        System.out.println("\nTotal contacts: " + bst.size());
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

        if (bst.contains(new Contact(name))) {
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
            bst.insert(c);
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

        Contact found = bst.search(new Contact(name));
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

    // ── Edit ──────────────────────────────────────────────────────────────────

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

        if (bst.contains(new Contact(newName))) {
            System.out.println("A contact named \"" + newName + "\" already exists.");
            currentPhase = PHASE_EDIT_MENU;
            return;
        }

        try {
            bst.remove(selectedContact);
            selectedContact.setName(newName);
            bst.insert(selectedContact);
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
                bst.remove(selectedContact);
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
        if (bst.isEmpty()) {
            System.out.println("\nNo contacts saved.");
        } else {
            System.out.println("\n-- All Contacts (" + bst.size() + ") – alphabetical order --");
            Object[] contacts = bst.inOrder();
            for (int i = 0; i < contacts.length; i++) {
                System.out.println((i + 1) + ". " + contacts[i]);
            }
        }
        currentPhase = PHASE_MENU;
    }

    private void loadSampleData() {
        if (!bst.isEmpty()) {
            System.out.println("\nThere are already " + bst.size() + " contact(s). Clear and reload? (y/n)");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("y")) {
                System.out.println("Load cancelled.");
                return;
            }
            bst.clear();
        }

        bst.insert(new Contact("Alice Smith",    "555-1001", "alice@mail.com"));
        bst.insert(new Contact("Bob Johnson",    "555-1002", "bob@mail.com"));
        bst.insert(new Contact("Carlos Lopez",   "555-1003", "carlos@mail.com"));
        bst.insert(new Contact("Diana Prince",   "555-1004", "diana@mail.com"));
        bst.insert(new Contact("Eduardo Gomez",  "555-1005", "eduardo@mail.com"));
        bst.insert(new Contact("Fiona Green",    "555-1006", "fiona@mail.com"));
        bst.insert(new Contact("Gabriel Torres", "555-1007", "gabriel@mail.com"));
        bst.insert(new Contact("Hannah White",   "555-1008", "hannah@mail.com"));

        System.out.println("Sample data loaded. Total contacts: " + bst.size());
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

