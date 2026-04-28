package list;

public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] elements;
    private int size;

    public SimpleArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean add(E element) {
<<<<<<< Updated upstream:src/list/SimpleArrayList.java
        if (size == elements.length) {
            resize();
        }
=======
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        validateSize(size + 1);
>>>>>>> Stashed changes:src/listModule/SimpleArrayList.java
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
<<<<<<< Updated upstream:src/list/SimpleArrayList.java
        if (index == size) {
            add(element);
            return;
        }

        validateIndex(index);

        if (size == elements.length) {
            resize();
        }

        // mover a la derecha
=======
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        validateInsertIndex(index);
        validateSize(size + 1);
        // Correr elementos a la derecha desde el final hasta index
>>>>>>> Stashed changes:src/listModule/SimpleArrayList.java
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

        // mover a la izquierda
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null; // limpiar referencia
        size--;

        return removed;
    }

    @Override
    public boolean remove(Object object) {
        if (object == null) return false;
        for (int i = 0; i < size; i++) {
            if ((object == null && elements[i] == null) ||
                    (object != null && object.equals(elements[i]))) {
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
        if (object == null) return false;
        for (int i = 0; i < size; i++) {
            if ((object == null && elements[i] == null) ||
                    (object != null && object.equals(elements[i]))) {
                return true;
            }
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