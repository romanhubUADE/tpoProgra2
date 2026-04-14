package queueModule;

import java.util.NoSuchElementException;
import listModule.LinkedNode;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

    private LinkedNode<E> first; // el frente de la cola
    private LinkedNode<E> last;  // el final de la cola
    private int size;

    public SimpleLinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
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
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        return first.value;
    }

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
