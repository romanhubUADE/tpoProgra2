package priorityQueue;

import java.util.NoSuchElementException;

/**
 * Implementacion del TDA PriorityQueue mediante una lista enlazada ordenada.
 */
public class LinkedPriorityQueue<T> implements SimplePriorityQueue<T> {

    // Primer nodo de la lista. El de mayor prioridad
    private PriorityNode<T> head;

    private int size;

    public LinkedPriorityQueue() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserta un elemento en la posicion correcta segun su prioridad.
     *
     * @param value    el elemento a insertar, no puede ser null
     * @param priority nivel de prioridad, debe ser mayor o igual a 0
     * @throws IllegalArgumentException si value es null o priority es negativa
     */
    @Override
    public void enqueue(T value, int priority) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null.");
        }
        if (priority < 0) {
            throw new IllegalArgumentException("La prioridad no puede ser negativa.");
        }

        PriorityNode<T> newNode = new PriorityNode<>(value, priority);

        // El nuevo nodo pasa a ser el head.
        if (head == null || newNode.priority > head.priority) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // el orden de llegada (FIFO).
        PriorityNode<T> current = head;
        while (current.next != null && current.next.priority >= newNode.priority) {
            current = current.next;
        }

        // Insertar el nuevo nodo entre current y current.next
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Extrae y retorna el elemento de mayor prioridad (el del frente de la lista).
     *
     * @return el elemento con mayor prioridad
     * @throws NoSuchElementException si la cola esta vacia
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede extraer de una cola vacia.");
        }

        // El elemento de mayor prioridad siempre esta en el head.
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    /**
     * Retorna el elemento de mayor prioridad sin extraerlo.
     *
     * @return el elemento con mayor prioridad
     * @throws NoSuchElementException si la cola esta vacia
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede hacer peek en una cola vacia.");
        }
        return head.value;
    }

    /**
     * Retorna la cantidad de elementos en la cola.
     *
     * @return numero de elementos
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Indica si la cola esta vacia.
     *
     * @return true si no hay elementos
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Vacia la cola eliminando todos los elementos.
     * Descarta la referencia al head para que el garbage collector
     * pueda liberar los nodos.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
