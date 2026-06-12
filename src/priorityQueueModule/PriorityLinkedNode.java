package priorityQueueModule;

/**
 * Nodo doblemente enlazado que usa SimpleLinkedPriorityQueue.
 * Guarda el valor del elemento y su prioridad, más punteros al nodo anterior y al siguiente.
 */
public class PriorityLinkedNode<E> {
    public E value;
    public int priority;
    public PriorityLinkedNode<E> next;
    public PriorityLinkedNode<E> prev;

    public PriorityLinkedNode(E value, int priority) {
        this.value = value;
        this.priority = priority;
        this.next = null;
        this.prev = null;
    }
}
