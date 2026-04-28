package emailModule;

public enum Priority {

    ALTO(1),
    BAJO(2);

    private final int numericValue;

    Priority(int numericValue) {
        this.numericValue = numericValue;
    }

    public int getNumericValue() {
        return numericValue;
    }
}
