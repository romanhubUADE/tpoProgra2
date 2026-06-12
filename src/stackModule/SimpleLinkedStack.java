package stackModule;

import java.util.NoSuchElementException;
import listModule.LinkedNode;

/**
 * Implementación de SimpleStack usando nodos enlazados de LinkedNode.
 * El tope de la pila es siempre el nodo apuntado por last.
 * Reutiliza LinkedNode del módulo de listas, usando solo los punteros prev y next.
 */
public class SimpleLinkedStack<E> implements SimpleStack<E> {

    private LinkedNode<E> last; // el tope de la pila (nodo más reciente)
    private int size;

    /** Crea una pila vacía. */
    public SimpleLinkedStack() {
        last = null;
        size = 0;
    }

    /** Crea un nuevo nodo, lo enlaza al tope actual y lo convierte en el nuevo tope. */
    @Override
    public void push(E element) {
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (!isEmpty()) {
            // encadenar el nuevo nodo encima del tope anterior
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode; // el nuevo nodo es ahora el tope
        size++;
    }

    /** Retira el nodo del tope, retrocede last al nodo anterior y devuelve el valor. */
    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        E value = last.value;
        if (size == 1) {
            // era el único nodo: la pila queda vacía
            last = null;
        } else {
            last = last.prev;  // el tope retrocede un nivel
            last.next = null;  // cortar la referencia al nodo eliminado
        }
        size--;
        return value;
    }

    /** Devuelve el valor del tope sin retirarlo. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        return last.value;
    }

    /**
     * Vacía la pila soltando la referencia al tope.
     * El GC se encarga de liberar el resto de los nodos.
     */
    @Override
    public void clear() {
        last = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
