package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public SimpleArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public SimpleArrayList(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }


    private void validateSize(int newSize) {
        if (newSize > elements.length) {
            resize();
        }
    }
    
    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    // Para get, set, remove(int): 0 <= index < size
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Para add(int, E): 0 <= index <= size (permite insertar al final)
    private void validateInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // ── Operaciones principales ───────────────────────────────────────────────

    @Override
    public boolean add(E element) {
        validateSize(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        validateInsertIndex(index);
        validateSize(size + 1);
        // Correr elementos a la derecha desde el final hasta index
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        E removed = elements[index];
        // Correr elementos a la izquierda
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; // limpiar referencia colgante
        size--;
        return removed;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        E old = elements[index];
        elements[index] = element;
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
