package listModule;

/**
 * Implementación de SimpleList usando una lista doblemente enlazada.
 * Cada elemento vive en un LinkedNode que apunta al anterior y al siguiente.
 * No tiene capacidad máxima: crece de a un nodo por elemento agregado.
 */
public class SimpleLinkedList<E> implements SimpleList<E> {

    private LinkedNode<E> first; // puntero al primer nodo de la cadena
    private LinkedNode<E> last;  // puntero al último nodo de la cadena
    private int size;

    /** Crea una lista vacía sin nodos. */
    public SimpleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    // ── Helpers privados ─────────────────────────────────────────────────────

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void validateInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Navega al nodo del índice dado, empezando desde el extremo más cercano
    private LinkedNode<E> getNodeByIndex(int index) {
        validateIndex(index);
        LinkedNode<E> current;
        if (index < size / 2) {
            // el índice está en la primera mitad: arrancamos desde el principio
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // el índice está en la segunda mitad: arrancamos desde el final (más eficiente)
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // Desconecta un nodo y repara las conexiones, maneja los 4 casos borde
    private void removeAndReconnect(LinkedNode<E> toRemove) {
        if (toRemove == first && toRemove == last) {
            // era el único nodo: la lista queda vacía
            first = null;
            last = null;
        } else if (toRemove == first) {
            // era el primero: el segundo pasa a ser el nuevo first
            first = toRemove.next;
            first.prev = null;
        } else if (toRemove == last) {
            // era el último: el penúltimo pasa a ser el nuevo last
            last = toRemove.prev;
            last.next = null;
        } else {
            // nodo del medio: los vecinos se conectan entre sí saltando este nodo
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
    }

    // ── Operaciones principales ───────────────────────────────────────────────

    /** Agrega el elemento al final de la cadena. Devuelve true siempre. */
    @Override
    public boolean add(E element) {
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) {
            // primer elemento: first y last apuntan al mismo nodo
            first = newNode;
            last = newNode;
        } else {
            // enlaza el nuevo nodo al final y actualiza last
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
        return true;
    }

    /** Inserta el elemento antes del nodo que está en index. */
    @Override
    public void add(int index, E element) {
        validateInsertIndex(index);
        if (index == size) {
            // insertar al final es igual que el add normal
            add(element);
            return;
        }
        LinkedNode<E> current = getNodeByIndex(index);
        LinkedNode<E> newNode = new LinkedNode<>(element);

        // Asignar prev y next del nuevo nodo ANTES de modificar los existentes
        newNode.prev = current.prev;
        newNode.next = current;

        if (current.prev != null) {
            current.prev.next = newNode; // el nodo anterior ahora apunta al nuevo
        } else {
            first = newNode; // el nuevo nodo es el primero de la lista
        }
        current.prev = newNode; // el nodo que estaba en index ahora tiene al nuevo como prev
        size++;
    }

    /** Elimina el nodo en index y devuelve su valor. */
    @Override
    public E remove(int index) {
        LinkedNode<E> toRemove = getNodeByIndex(index);
        E value = toRemove.value;
        removeAndReconnect(toRemove);
        return value;
    }

    /** Recorre la cadena buscando el objeto y elimina la primera ocurrencia. */
    @Override
    public boolean remove(Object object) {
        LinkedNode<E> current = first;
        while (current != null) { // != null, no equals — null no puede ejecutar métodos
            if (current.value.equals(object)) {
                removeAndReconnect(current);
                return true;
            }
            current = current.next; // avanzar al siguiente nodo
        }
        return false;
    }

    /**
     * Vacía la lista soltando las referencias a los extremos.
     * El GC se encarga del resto de los nodos.
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        LinkedNode<E> current = first;
        while (current != null) {
            if (current.value.equals(object)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        return getNodeByIndex(index).value;
    }

    /** Reemplaza el valor del nodo en index y devuelve el valor anterior. */
    @Override
    public E set(int index, E element) {
        LinkedNode<E> node = getNodeByIndex(index);
        E old = node.value;
        node.value = element;
        return old;
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
