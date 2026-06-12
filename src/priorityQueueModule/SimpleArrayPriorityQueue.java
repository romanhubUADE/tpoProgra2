package priorityQueueModule;

import java.util.NoSuchElementException;

/**
 * Cola de prioridad implementada con dos arrays paralelos: uno para los elementos
 * y otro para sus prioridades. El array siempre se mantiene ordenado de menor a mayor
 * valor de prioridad, así el frente (índice 0) es siempre el de mayor urgencia.
 * Se redimensiona automáticamente al quedarse sin espacio.
 */
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

    /** Si el array se llenó, duplica su capacidad antes de insertar. */
    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    /** Duplica el tamaño de los dos arrays copiando los elementos existentes. */
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

    /**
     * Inserta el elemento manteniendo el orden ascendente de prioridades.
     * Desplaza hacia la derecha todos los elementos con prioridad mayor
     * hasta encontrar la posición correcta para el nuevo elemento.
     */
    @Override
    public void enqueue(E element, int priority) {
        if (element == null) throw new NullPointerException("element cannot be null");
        validateSize(size + 1);

        int insertIndex = size;
        // Mueve elementos con prioridad mayor hacia la derecha para abrir hueco
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

    /**
     * Saca el elemento del frente (índice 0, el de menor valor de prioridad)
     * y desplaza el resto una posición hacia la izquierda.
     */
    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        E value = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            priorities[i] = priorities[i + 1];
        }
        // Limpia la última posición que quedó duplicada
        elements[size - 1] = null;
        priorities[size - 1] = 0;
        size--;
        return value;
    }

    /** Devuelve el elemento de mayor urgencia sin sacarlo de la cola. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return elements[0];
    }

    /** Devuelve el número de prioridad más urgente (el menor valor). */
    @Override
    public int getHighestPriority() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return priorities[0];
    }

    /** Vacía la cola poniendo null en cada posición para que el GC pueda liberar memoria. */
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
