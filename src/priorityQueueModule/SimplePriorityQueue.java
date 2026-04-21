package priorityQueueModule;

public interface SimplePriorityQueue<E> {
    public void enqueeu(E element, int priority);
    public E dequeue();
    public E peek();
    public int getHighestPriority();
    public int size();
    public boolean isEmpty();
    public void clear();
}
