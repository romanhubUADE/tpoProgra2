package stackModule;

import java.util.NoSuchElementException;

/**
 * Implementación de SimpleStack usando un arreglo interno de tamaño dinámico.
 * El tope de la pila corresponde a la posición size-1 del arreglo.
 */
public class SimpleArrayStack<E> implements SimpleStack<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4; // capacidad inicial del arreglo

    /** Crea una pila vacía con la capacidad inicial por defecto (4). */
    public SimpleArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /** Verifica si el arreglo necesita crecer antes de agregar un elemento más. */
    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    /** Duplica la capacidad del arreglo copiando los elementos al nuevo. */
    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) newArray[i] = elements[i];
        elements = newArray;
    }

    /** Agrega el elemento al tope (al final del arreglo). */
    @Override
    public void push(E element) {
        validateSize(size + 1);
        elements[size] = element;
        size++;
    }

    /** Retira el elemento del tope (última posición), limpia la celda y lo devuelve. */
    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        E value = elements[size - 1];
        elements[size - 1] = null; // limpiar referencia colgante
        size--;
        return value;
    }

    /** Devuelve el elemento del tope sin modificar la pila. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        return elements[size - 1];
    }

    /** Pone null en todas las posiciones y resetea el tamaño a 0. */
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
