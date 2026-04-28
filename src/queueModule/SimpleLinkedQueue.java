package queueModule;

import java.util.NoSuchElementException;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

<<<<<<< Updated upstream
    private Node<E> first;
    private Node<E> last;
=======
    private LinkedNode<E> first;
    private LinkedNode<E> last;
>>>>>>> Stashed changes
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public void enqueue(E element) {
<<<<<<< Updated upstream
        Node<E> newNode = new Node<>(element);

=======
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        LinkedNode<E> newNode = new LinkedNode<>(element);
>>>>>>> Stashed changes
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        E data = first.data;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        }

        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return first.data;
    }

    @Override
    public void clear() { first = null; last = null; size = 0; }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}