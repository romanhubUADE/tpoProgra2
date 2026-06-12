package priorityQueueModule;

/**
 * Interfaz que define las operaciones de una cola de prioridad genérica.
 * El elemento con MENOR valor de prioridad se considera el de mayor urgencia
 * y es el primero en salir (ej: prioridad 1 sale antes que prioridad 5).
 */
public interface SimplePriorityQueue<E> {
    /** Agrega un elemento con el valor de prioridad indicado. */
    void enqueue(E element, int priority);
    /** Saca y devuelve el elemento de mayor urgencia (menor valor de prioridad). */
    E dequeue();
    /** Devuelve el elemento de mayor urgencia sin sacarlo. */
    E peek();
    /** Devuelve el valor numérico de la mayor prioridad que hay en la cola. */
    int getHighestPriority();
    int size();
    boolean isEmpty();
    void clear();
}
