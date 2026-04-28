package priorityQueueModule;

import java.util.NoSuchElementException;

public class SimpleLinkedPriorityQueue<E> implements SimplePriorityQueue<E> {

    private PriorityLinkedNode<E> first;
    private PriorityLinkedNode<E> last;
    private int size;

    public SimpleLinkedPriorityQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void enqueue(E element, int priority) {
        if (element == null) throw new NullPointerException("element cannot be null");
        PriorityLinkedNode<E> newNode = new PriorityLinkedNode<>(element, priority);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else if (priority < first.priority) {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else {
            PriorityLinkedNode<E> current = last;
            while (current.prev != null && priority < current.priority) {
                current = current.prev;
            }
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            } else {
                last = newNode;
            }
            current.next = newNode;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        E value = first.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return first.value;
    }

    @Override
    public int getHighestPriority() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return first.priority;
    }

    @Override
    public void clear() { first = null; last = null; size = 0; }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
