package emailModule;

public class Email {

    private final String subject;
    private final Priority priority;
    private Tab tab;

    public Email(String subject, Priority priority) {
        this.subject = subject;
        this.priority = priority;
        this.tab = null;
    }

    public void assignTab(Tab tab) {
        this.tab = tab;
    }

    public String getSubject() { return subject; }
    public Priority getPriority() { return priority; }
    public Tab getTab() { return tab; }
}
