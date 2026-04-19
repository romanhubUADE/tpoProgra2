package email;

/**
 * Representa los niveles de prioridad disponibles para un mail.
 *
 * Cada valor del enum tiene asociado un entero (numericValue) que se usa
 * directamente como parametro de prioridad en la PriorityQueue.
 * A mayor numero, mayor prioridad en la cola.
 *
 * ALTO = 2 (se procesa antes)
 * BAJO = 1 (se procesa despues)
 */
public enum Priority {

    ALTO(2),
    BAJO(1);

    // Valor numerico que representa la prioridad en la estructura interna.
    private final int numericValue;

    Priority(int numericValue) {
        this.numericValue = numericValue;
    }

    /**
     * Retorna el valor numerico asociado a esta prioridad.
     * Este valor es el que se pasa al metodo enqueue() de la PriorityQueue.
     *
     * @return entero representando la prioridad (mayor = mas urgente)
     */
    public int getNumericValue() {
        return numericValue;
    }
}
