package listModule;

/**
 * Nodo de una lista doblemente enlazada.
 * Guarda un valor y apunta al nodo siguiente y al anterior.
 */
public class LinkedNode<E> {
    public E value;           // el dato que guarda este nodo
    public LinkedNode<E> next; // puntero al siguiente nodo en la cadena
    public LinkedNode<E> prev; // puntero al nodo anterior en la cadena

    /** Crea un nodo con el valor dado; deja los punteros en null. */
    public LinkedNode(E value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}
