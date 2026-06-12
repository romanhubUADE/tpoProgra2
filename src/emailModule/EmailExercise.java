package emailModule;

import application.Exercise;
import java.util.Scanner;

/**
 * Ejercicio interactivo del sistema de email. Permite redactar emails, verlos
 * en el inbox, clasificarlos en pestañas y leerlos desde ahí.
 * Usa una máquina de estados (currentPhase) para navegar entre pantallas.
 */
public class EmailExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private final EmailSystem system;
    private Tab selectedTab; // pestaña seleccionada actualmente por el usuario

    public EmailExercise(Scanner scanner) {
        super(scanner);
        system = new EmailSystem();
        selectedTab = null;
    }

    /**
     * Método principal del ejercicio. Se llama en cada iteración del loop
     * y delega en el método correspondiente a la fase actual.
     */
    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();        break;
            case 1: composeLogic();     break;
            case 2: inboxLogic();       break;
            case 3: tabsMenuLogic();    break;
            case 4: classifyLogic();    break;
            case 5: viewTabLogic();     break;
            case 6: readTopTabLogic();  break;
        }
    }

    /** Muestra el menú principal y procesa la opción elegida. */
    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Email Exercise.");
            System.out.println("Convention: lower priority value = higher priority (ALTO=1, BAJO=2).");
        } else {
            printStatus();
        }

        System.out.println("\nChoose an option:"
            + "\ncompose - Write a new email."
            + "\ninbox   - Open inbox (" + system.inboxSize() + " mail(s))."
            + "\ntabs    - Open a tab."
            + "\nmm      - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "compose": currentPhase = 1; break;
            case "inbox":   currentPhase = 2; break;
            case "tabs":    currentPhase = 3; break;
            case "mm":      running = false;   break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    /** Pide asunto y prioridad, crea el email y lo agrega al inbox. */
    private void composeLogic() {
        System.out.println("\nEnter subject:");
        String subject = scanner.nextLine().trim();
        if (subject.isEmpty()) {
            System.out.println("Subject cannot be empty.");
            currentPhase = 0;
            return;
        }

        Priority priority = readPriority();
        Email email = new Email(subject, priority);
        system.receiveEmail(email);

        System.out.println("\nEmail received in inbox.");
        System.out.println("  Subject:  " + subject);
        System.out.println("  Priority: " + priority.name());
        printStatus();

        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nWrite another email? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "y": validInput = true;                    break;
                case "n": validInput = true; currentPhase = 0; break;
            }
        }
    }

    /** Muestra los emails del inbox. Si está vacío, vuelve al menú. */
    private void inboxLogic() {
        System.out.println("\n=== INBOX ===");
        if (system.isInboxEmpty()) {
            System.out.println("Inbox is empty.");
            currentPhase = 0;
            return;
        }

        printEmails(system.listInbox());

        System.out.println("\nChoose an option:"
            + "\nclassify - Classify the highest priority email."
            + "\nback     - Back to Email menu."
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "classify": currentPhase = 4; break;
            case "back":     currentPhase = 0; break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    /** Pide al usuario elegir una pestaña y mueve el email top del inbox ahí. */
    private void classifyLogic() {
        System.out.println("\nMove highest priority email to which tab?"
            + "\nprincipal - Principal"
            + "\nnotif     - Notificaciones"
            + "\nspam      - Spam"
        );
        Tab tab = readTab();
        if (tab == null) {
            // El usuario eligió "back"
            currentPhase = 2;
            return;
        }
        system.classifyTopInboxEmail(tab);
        System.out.println("Email moved to " + tab.getDisplayName() + ".");
        currentPhase = 2;
    }

    /** Muestra el menú de pestañas y permite seleccionar una para ver su contenido. */
    private void tabsMenuLogic() {
        System.out.println("\nChoose a tab to open:"
            + "\nprincipal - Principal       (" + system.tabSize(Tab.PRINCIPAL) + " mail(s))"
            + "\nnotif     - Notificaciones  (" + system.tabSize(Tab.NOTIFICACIONES) + " mail(s))"
            + "\nspam      - Spam            (" + system.tabSize(Tab.SPAM) + " mail(s))"
            + "\nback      - Back to Email menu."
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "principal": selectedTab = Tab.PRINCIPAL;      currentPhase = 5; break;
            case "notif":     selectedTab = Tab.NOTIFICACIONES; currentPhase = 5; break;
            case "spam":      selectedTab = Tab.SPAM;           currentPhase = 5; break;
            case "back":      currentPhase = 0; break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    /** Muestra los emails de la pestaña seleccionada. Si está vacía, vuelve al menú de pestañas. */
    private void viewTabLogic() {
        System.out.println("\n=== " + selectedTab.getDisplayName().toUpperCase() + " ===");
        if (system.isTabEmpty(selectedTab)) {
            System.out.println("This tab is empty.");
            currentPhase = 3;
            return;
        }

        printEmails(system.listTab(selectedTab));

        System.out.println("\nChoose an option:"
            + "\nread - Read the highest priority email (removes it)."
            + "\nback - Back to tabs menu."
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "read": currentPhase = 6; break;
            case "back": currentPhase = 3; break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    /** Lee (y elimina) el email de mayor prioridad de la pestaña seleccionada. */
    private void readTopTabLogic() {
        Email email = system.readTopEmail(selectedTab);
        System.out.println("\n=== READING ===");
        System.out.println("Subject:  " + email.getSubject());
        System.out.println("Priority: " + email.getPriority().name());
        System.out.println("Tab:      " + email.getTab().getDisplayName());
        System.out.println("Email read and removed from tab.");
        currentPhase = 5;
    }

    /** Pide al usuario que elija ALTO o BAJO hasta que ingrese una opción válida. */
    private Priority readPriority() {
        while (true) {
            System.out.println("Choose priority:"
                + "\nalto - High priority"
                + "\nbajo - Low priority"
            );
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "alto": return Priority.ALTO;
                case "bajo": return Priority.BAJO;
                default:
                    System.out.println("Invalid input, try again.");
            }
        }
    }

    /**
     * Pide al usuario que elija una pestaña. Devuelve null si el usuario ingresa "back".
     */
    private Tab readTab() {
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "principal": return Tab.PRINCIPAL;
                case "notif":     return Tab.NOTIFICACIONES;
                case "spam":      return Tab.SPAM;
                case "back":      return null;
                default:
                    System.out.println("Invalid input, try again.");
            }
        }
    }

    /** Imprime la lista de emails numerada con su prioridad y asunto. */
    private void printEmails(Email[] emails) {
        for (int i = 0; i < emails.length; i++) {
            Email e = emails[i];
            System.out.println((i + 1) + ". [" + e.getPriority().name() + "] " + e.getSubject());
        }
    }

    /** Muestra un resumen del estado actual: cuántos emails hay en cada cola. */
    private void printStatus() {
        System.out.println("Inbox: " + system.inboxSize()
            + " | Principal: " + system.tabSize(Tab.PRINCIPAL)
            + " | Notificaciones: " + system.tabSize(Tab.NOTIFICACIONES)
            + " | Spam: " + system.tabSize(Tab.SPAM));
    }
}
