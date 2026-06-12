package emailModule;

/**
 * Representa un email con asunto, prioridad y pestaña asignada.
 * La pestaña empieza en null y se asigna cuando el email se clasifica.
 */
public class Email {

    private final String subject;
    private final Priority priority;
    private Tab tab;

    /**
     * Crea un email con asunto y prioridad. La pestaña queda sin asignar (null)
     * hasta que el usuario lo clasifique manualmente.
     */
    public Email(String subject, Priority priority) {
        this.subject = subject;
        this.priority = priority;
        this.tab = null;
    }

    /** Asigna este email a una pestaña (Principal, Notificaciones o Spam). */
    public void assignTab(Tab tab) {
        this.tab = tab;
    }

    public String getSubject() { return subject; }
    public Priority getPriority() { return priority; }
    public Tab getTab() { return tab; }
}
