package setModule;

import listModule.LinkedNode;

/**
 * Implementación del TDA Conjunto usando una lista doblemente enlazada.
 * Mantiene referencias al primer y último nodo para agregar al final en O(1).
 * No garantiza ningún orden particular de los elementos.
 */
public class SimpleLinkedSet<E> implements SimpleSet<E> {

    private LinkedNode<E> first;
    private LinkedNode<E> last;
    private int size;

    /** Crea un conjunto vacío. */
    public SimpleLinkedSet() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Compara dos elementos con soporte para null.
     * Evita NullPointerException al usar equals.
     */
    private boolean same(E a, E b) {
        return a == null ? b == null : a.equals(b);
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) return false;

        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) {
            // Primer elemento: first y last apuntan al mismo nodo
            first = newNode;
            last = newNode;
        } else {
            // Encadena el nuevo nodo al final
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        LinkedNode<E> current = first;

        while (current != null) {
            if (same(current.value, element)) {
                if (current == first && current == last) {
                    // Era el único nodo
                    first = null;
                    last = null;
                } else if (current == first) {
                    // Elimina el primer nodo
                    first = current.next;
                    first.prev = null;
                } else if (current == last) {
                    // Elimina el último nodo
                    last = current.prev;
                    last.next = null;
                } else {
                    // Nodo intermedio: conecta el anterior con el siguiente
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }

        return false; // no se encontró el elemento
    }

    @Override
    public boolean contains(E element) {
        LinkedNode<E> current = first;
        while (current != null) {
            if (same(current.value, element)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // Soltar las referencias es suficiente; el GC libera los nodos
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        LinkedNode<E> current = first;
        int index = 0;

        while (current != null) {
            array[index] = current.value;
            current = current.next;
            index++;
        }

        return array;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();

        // Agrega todos los elementos de este conjunto
        E[] thisArray = this.toArray();
        for (E element : thisArray) {
            result.add(element);
        }

        // Agrega los del otro (add ignora duplicados automáticamente)
        E[] otherArray = other.toArray();
        for (E element : otherArray) {
            result.add(element);
        }

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        E[] thisArray = this.toArray();

        // Solo agrega los elementos que están en ambos conjuntos
        for (E element : thisArray) {
            if (other.contains(element)) result.add(element);
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        E[] thisArray = this.toArray();

        // Solo agrega los elementos que NO están en el otro conjunto
        for (E element : thisArray) {
            if (!other.contains(element)) result.add(element);
        }

        return result;
    }
}
