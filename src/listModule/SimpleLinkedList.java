package listModule;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private LinkedNode<E> first;
    private LinkedNode<E> last;
    private int size;

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
            current = first;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    // Desconecta un nodo y repara las conexiones, maneja los 4 casos borde
    private void removeAndReconnect(LinkedNode<E> toRemove) {
        if (toRemove == first && toRemove == last) {
            first = null;
            last = null;
        } else if (toRemove == first) {
            first = toRemove.next;
            first.prev = null;
        } else if (toRemove == last) {
            last = toRemove.prev;
            last.next = null;
        } else {
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
    }

    // ── Operaciones principales ───────────────────────────────────────────────

    @Override
    public boolean add(E element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        validateInsertIndex(index);
        if (index == size) { add(element); return; }
        LinkedNode<E> current = getNodeByIndex(index);
        LinkedNode<E> newNode = new LinkedNode<>(element);
        newNode.prev = current.prev;
        newNode.next = current;
        if (current.prev != null) current.prev.next = newNode;
        else first = newNode;
        current.prev = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        LinkedNode<E> toRemove = getNodeByIndex(index);
        E value = toRemove.value;
        removeAndReconnect(toRemove);
        return value;
    }

    @Override
    public boolean remove(Object object) {
        if (object == null) return false;
        LinkedNode<E> current = first;
        while (current != null) {
            if (current.value.equals(object)) { removeAndReconnect(current); return true; }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() { first = null; last = null; size = 0; }

    @Override
    public boolean contains(Object object) {
        if (object == null) return false;
        LinkedNode<E> current = first;
        while (current != null) {
            if (current.value.equals(object)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) { return getNodeByIndex(index).value; }

    @Override
    public E set(int index, E element) {
        LinkedNode<E> node = getNodeByIndex(index);
        E old = node.value;
        node.value = element;
        return old;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
