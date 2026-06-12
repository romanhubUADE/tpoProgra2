package queueModule;

/**
 * Interfaz del TDA Cola (FIFO): el primero en entrar es el primero en salir.
 * Define las operaciones básicas que toda implementación de cola debe tener.
 */
public interface SimpleQueue<E> {
    /** Agrega un elemento al final de la cola. */
    void enqueue(E element);
    /** Elimina y devuelve el elemento del frente. Lanza excepción si está vacía. */
    E dequeue();
    /** Devuelve el elemento del frente sin eliminarlo. Lanza excepción si está vacía. */
    E peek();
    /** Elimina todos los elementos de la cola. */
    void clear();
    /** Cantidad de elementos en la cola. */
    int size();
    /** Indica si la cola no tiene elementos. */
    boolean isEmpty();
}
