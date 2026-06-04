package priorityQueueModule;

public interface SimplePriorityQueue<E> {
    void enqueue(E element, int priority);
    E dequeue();
    E peek();
    int getHighestPriority();
    void clear();
    int size();
    boolean isEmpty();
}

