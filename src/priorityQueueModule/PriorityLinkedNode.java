package priorityQueueModule;

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
