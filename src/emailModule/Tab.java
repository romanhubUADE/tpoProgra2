package emailModule;

/**
 * Representa las pestañas (carpetas) del sistema de email.
 * Cada pestaña tiene un nombre para mostrar en pantalla.
 */
public enum Tab {

    PRINCIPAL("Principal"),
    NOTIFICACIONES("Notificaciones"),
    SPAM("Spam");

    private final String displayName;

    Tab(String displayName) {
        this.displayName = displayName;
    }

    /** Devuelve el nombre legible de la pestaña, para mostrar en la interfaz. */
    public String getDisplayName() {
        return displayName;
    }
}
