package queueModule;

import java.util.NoSuchElementException;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public SimpleArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) newArray[i] = elements[i];
        elements = newArray;
    }

    @Override
    public void enqueue(E element) {
        validateSize(size + 1);
        elements[size] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        E value = elements[0];
        for (int i = 0; i < size - 1; i++) elements[i] = elements[i + 1];
        elements[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        return elements[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) elements[i] = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
