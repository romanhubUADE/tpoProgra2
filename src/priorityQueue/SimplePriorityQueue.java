package priorityQueue;

/**
 * Interfaz que define el contrato del TDA PriorityQueue (Cola de Prioridad).
 */
public interface SimplePriorityQueue<T> {

    /**
     * Inserta un elemento en la cola con el nivel de prioridad indicado.
     * El elemento quedara posicionado segun su prioridad respecto a los
     * elementos existentes.
     *
     * @param value    el elemento a insertar, no puede ser null
     * @param priority nivel de prioridad numerico, debe ser mayor o igual a 0
     * @throws IllegalArgumentException si value es null o priority es negativa
     */
    void enqueue(T value, int priority);

    /**
     * Extrae y retorna el elemento de mayor prioridad de la cola.
     * Si dos elementos tienen la misma prioridad, se extrae el que
     * fue insertado primero (orden FIFO).
     *
     * @return el elemento con mayor prioridad
     * @throws java.util.NoSuchElementException si la cola esta vacia
     */
    T dequeue();

    /**
     * Retorna el elemento de mayor prioridad sin extraerlo de la cola.
     *
     * @return el elemento con mayor prioridad
     * @throws java.util.NoSuchElementException si la cola esta vacia
     */
    T peek();

    /**
     * Retorna la cantidad de elementos actualmente en la cola.
     *
     * @return numero de elementos, 0 si la cola esta vacia
     */
    int size();

    /**
     * Indica si la cola no contiene elementos.
     *
     * @return true si la cola esta vacia, false en caso contrario
     */
    boolean isEmpty();

    /**
     * Elimina todos los elementos de la cola.
     * Despues de llamar a este metodo, size() retorna 0.
     */
    void clear();
}
