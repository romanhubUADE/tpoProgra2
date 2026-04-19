package email;

/**
 * Representa las pestanas disponibles en el sistema de correo.
 *
 * Cada pestana tiene su propia cola de prioridad independiente.
 * El displayName se usa para mostrar el nombre en la interfaz de consola.
 */
public enum Tab {

    PRINCIPAL("Principal"),
    NOTIFICACIONES("Notificaciones"),
    SPAM("Spam");

    // Nombre legible para mostrar en pantalla.
    private final String displayName;

    Tab(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retorna el nombre de la pestana para mostrar al usuario.
     *
     * @return nombre legible de la pestana
     */
    public String getDisplayName() {
        return displayName;
    }
}
