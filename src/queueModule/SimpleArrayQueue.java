package queueModule;

import java.util.NoSuchElementException;

/**
 * Implementación de la cola usando un arreglo dinámico.
 * El frente siempre está en el índice 0. Al desencolar se corren todos los elementos hacia adelante.
 * Crece automáticamente al duplicar capacidad cuando el arreglo se llena.
 */
public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    /** Inicializa la cola con capacidad 4. */
    public SimpleArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /** Verifica si hay espacio; si no, redimensiona antes de insertar. */
    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    /** Duplica la capacidad del arreglo copiando los elementos existentes. */
    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) newArray[i] = elements[i];
        elements = newArray;
    }

    /** Agrega el elemento al final del arreglo (posición size). */
    @Override
    public void enqueue(E element) {
        validateSize(size + 1);
        elements[size] = element;
        size++;
    }

    /**
     * Saca y devuelve el elemento del frente (índice 0).
     * Desplaza todos los elementos un lugar hacia la izquierda para mantener el frente en 0.
     */
    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        E value = elements[0];
        // Corremos todo hacia la izquierda para que el siguiente pase al frente
        for (int i = 0; i < size - 1; i++) elements[i] = elements[i + 1];
        elements[size - 1] = null; // limpiamos la última posición que quedó duplicada
        size--;
        return value;
    }

    /** Devuelve el elemento del frente sin modificar la cola. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        return elements[0];
    }

    /** Limpia el arreglo seteando en null todas las posiciones usadas. */
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
