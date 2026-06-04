package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    private void resize() {
        @SuppressWarnings("unchecked")
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

    private void validateInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean add(E element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (size == elements.length) {
            resize();
        }
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        validateInsertIndex(index);

        if (index == size) {
            add(element);
            return;
        }

        validateIndex(index);

        if (size == elements.length) {
            resize();
        }

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
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public boolean remove(Object object) {
        if (object == null) return false;
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
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
            if (object.equals(elements[i])) {
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

