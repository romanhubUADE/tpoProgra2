package priorityQueueModule;

import java.util.NoSuchElementException;

/**
 * Cola de prioridad implementada con una lista doblemente enlazada.
 * La lista se mantiene ordenada: {@code first} apunta al nodo de mayor urgencia
 * (menor valor de prioridad) y {@code last} al de menor urgencia.
 * El doble enlace permite buscar la posición de inserción desde el final,
 * lo que es más eficiente cuando el nuevo elemento tiene prioridad baja.
 */
public class SimpleLinkedPriorityQueue<E> implements SimplePriorityQueue<E> {

    private PriorityLinkedNode<E> first;
    private PriorityLinkedNode<E> last;
    private int size;

    public SimpleLinkedPriorityQueue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Inserta el elemento en la posición correcta para conservar el orden.
     * Hay tres casos: lista vacía, el nuevo tiene mayor urgencia que el actual primero,
     * o hay que buscar su lugar desde el final hacia adelante.
     */
    @Override
    public void enqueue(E element, int priority) {
        if (element == null) throw new NullPointerException("element cannot be null");
        PriorityLinkedNode<E> newNode = new PriorityLinkedNode<>(element, priority);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else if (priority < first.priority) {
            // El nuevo es más urgente que todos: va al frente
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else {
            // Busca desde el final hacia atrás el primer nodo con prioridad <= al nuevo
            PriorityLinkedNode<E> current = last;
            while (current.prev != null && priority < current.priority) {
                current = current.prev;
            }
            // Enchufa el nuevo nodo entre current y current.next
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            } else {
                // El nuevo quedó al final de la lista
                last = newNode;
            }
            current.next = newNode;
        }

        size++;
    }

    /** Saca y devuelve el elemento del frente (el de mayor urgencia). */
    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
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

    /** Devuelve el elemento de mayor urgencia sin sacarlo. */
    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return first.value;
    }

    /** Devuelve el número de prioridad más urgente (el menor valor). */
    @Override
    public int getHighestPriority() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty.");
        return first.priority;
    }

    /** Vacía la cola soltando las referencias para que el GC libere los nodos. */
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
