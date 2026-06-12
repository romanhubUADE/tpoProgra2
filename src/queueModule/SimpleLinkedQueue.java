package queueModule;

import java.util.NoSuchElementException;
import listModule.LinkedNode;

/**
 * Implementación de la cola usando una lista doblemente enlazada.
 * Se encola por el final (last) y se desencola por el frente (first): comportamiento FIFO puro.
 * No tiene límite de tamaño (no requiere redimensionar).
 */
public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

    private LinkedNode<E> first; // el frente de la cola
    private LinkedNode<E> last;  // el final de la cola
    private int size;

    /** Inicializa la cola vacía. */
    public SimpleLinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Agrega el nuevo nodo al final de la cadena.
     * Si la cola estaba vacía, first y last apuntan al mismo nodo.
     */
    @Override
    public void enqueue(E element) {
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            // Enlazamos el nuevo nodo después del último
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Saca y devuelve el nodo del frente.
     * Si quedaba uno solo, both first y last pasan a null.
     */
    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        E value = first.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null; // el nuevo frente no tiene nodo anterior
        }
        size--;
        return value;
    }

    /** Devuelve el valor del frente sin eliminarlo. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        return first.value;
    }

    /** Suelta las referencias al frente y al final; el GC limpia el resto. */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
