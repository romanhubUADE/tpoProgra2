package queueModule;

import java.util.NoSuchElementException;

public class SimpleArrayQueue <E> implements SimpleQueue<E>{
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size; // cantidad de elementos
    private int front; // primer elemento que sale
    private int rear; // proxima posicion donde insertar


    public SimpleArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public void enqueue(E element) {
<<<<<<< Updated upstream
        validateSize();

        elements[rear] = element;
        rear = (rear + 1) % elements.length;
=======
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        validateSize(size + 1);
        elements[size] = element;
>>>>>>> Stashed changes
        size++;
    }

    private void validateSize() {
        if (size == elements.length) {
            resize();
        }
    }

    private void resize() {
        E[] temp = (E[]) new Object[elements.length * 2];

        for (int i = 0; i < size; i++) {
            temp[i] = elements[(front + i) % elements.length];
        }

        elements = temp;
        front = 0;
        rear = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return elements[front];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[(front + i) % elements.length] = null;
        }

        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }







}
