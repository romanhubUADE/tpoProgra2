package emailModule;

/**
 * Representa la prioridad de un email.
 * ALTO tiene valor numérico 1 y BAJO tiene 2, porque menor número = mayor prioridad
 * (así funciona la cola de prioridad interna).
 */
public enum Priority {

    ALTO(1),
    BAJO(2);

    private final int numericValue;

    Priority(int numericValue) {
        this.numericValue = numericValue;
    }

    /** Devuelve el valor numérico asociado (1 para ALTO, 2 para BAJO). */
    public int getNumericValue() {
        return numericValue;
    }
}
