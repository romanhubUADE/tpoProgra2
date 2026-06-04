package priorityQueueModule;

class PriorityLinkedNode<E> {
    E value;
    int priority;
    PriorityLinkedNode<E> prev;
    PriorityLinkedNode<E> next;

    PriorityLinkedNode(E value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}

