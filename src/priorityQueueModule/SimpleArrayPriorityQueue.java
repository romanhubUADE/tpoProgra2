package priorityQueueModule;

import java.util.NoSuchElementException;

public class SimpleArrayPriorityQueue<E> implements SimplePriorityQueue<E> {

    private E[] elements;
    private int[] priorities;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayPriorityQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        priorities = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newElements = (E[]) new Object[elements.length * 2];
        int[] newPriorities = new int[priorities.length * 2];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
            newPriorities[i] = priorities[i];
        }
        elements = newElements;
        priorities = newPriorities;
    }

    @Override
    public void enqueue(E element, int priority) {
        if (element == null) throw new NullPointerException("element cannot be null");
        validateSize(size + 1);

        int insertIndex = size;
        for (int i = size - 1; i >= 0; i--) {
            if (priority >= priorities[i]) break;
            elements[i + 1] = elements[i];
            priorities[i + 1] = priorities[i];
            insertIndex = i;
        }

        elements[insertIndex] = element;
        priorities[insertIndex] = priority;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        E value = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            priorities[i] = priorities[i + 1];
        }
        elements[size - 1] = null;
        priorities[size - 1] = 0;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return elements[0];
    }

    @Override
    public int getHighestPriority() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return priorities[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
            priorities[i] = 0;
        }
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
