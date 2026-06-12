package stackModule;

import java.util.NoSuchElementException;

public class SimpleArrayStack<E> implements SimpleStack<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public SimpleArrayStack() {
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
    public void push(E element) {
        validateSize(size + 1);
        elements[size] = element;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        E value = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        return elements[size - 1];
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
