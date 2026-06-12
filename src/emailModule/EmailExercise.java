package emailModule;

import application.Exercise;
import java.util.Scanner;

public class EmailExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private final EmailSystem system;
    private Tab selectedTab;

    public EmailExercise(Scanner scanner) {
        super(scanner);
        system = new EmailSystem();
        selectedTab = null;
    }

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

    private void classifyLogic() {
        System.out.println("\nMove highest priority email to which tab?"
            + "\nprincipal - Principal"
            + "\nnotif     - Notificaciones"
            + "\nspam      - Spam"
        );
        Tab tab = readTab();
        if (tab == null) {

            currentPhase = 2;
            return;
        }
        system.classifyTopInboxEmail(tab);
        System.out.println("Email moved to " + tab.getDisplayName() + ".");
        currentPhase = 2;
    }

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

    private void readTopTabLogic() {
        Email email = system.readTopEmail(selectedTab);
        System.out.println("\n=== READING ===");
        System.out.println("Subject:  " + email.getSubject());
        System.out.println("Priority: " + email.getPriority().name());
        System.out.println("Tab:      " + email.getTab().getDisplayName());
        System.out.println("Email read and removed from tab.");
        currentPhase = 5;
    }

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

    private void printEmails(Email[] emails) {
        for (int i = 0; i < emails.length; i++) {
            Email e = emails[i];
            System.out.println((i + 1) + ". [" + e.getPriority().name() + "] " + e.getSubject());
        }
    }

    private void printStatus() {
        System.out.println("Inbox: " + system.inboxSize()
            + " | Principal: " + system.tabSize(Tab.PRINCIPAL)
            + " | Notificaciones: " + system.tabSize(Tab.NOTIFICACIONES)
            + " | Spam: " + system.tabSize(Tab.SPAM));
    }
}
