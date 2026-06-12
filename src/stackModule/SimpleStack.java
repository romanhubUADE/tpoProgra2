package stackModule;

/**
 * Contrato (interfaz) que define las operaciones básicas de una pila genérica.
 * Una pila es LIFO: el último en entrar es el primero en salir.
 */
public interface SimpleStack<E> {
    /** Apila el elemento en el tope de la pila. */
    void push(E element);
    /** Retira y devuelve el elemento del tope. Lanza excepción si la pila está vacía. */
    E pop();
    /** Devuelve el elemento del tope sin retirarlo. Lanza excepción si la pila está vacía. */
    E peek();
    /** Vacía la pila por completo. */
    void clear();
    /** Devuelve la cantidad de elementos en la pila. */
    int size();
    /** Indica si la pila no tiene elementos. */
    boolean isEmpty();
}
