package emailModule;

public enum Tab {

    PRINCIPAL("Principal"),
    NOTIFICACIONES("Notificaciones"),
    SPAM("Spam");

    private final String displayName;

    Tab(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
