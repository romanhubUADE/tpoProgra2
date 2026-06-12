package setModule;

public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public SimpleArraySet() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public SimpleArraySet(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }

    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    private int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) return i;
        }
        return -1;
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) return false;
        validateSize(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index == -1) return false;

        elements[index] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return true;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
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

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E[] toArray() {
        E[] newArray = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        return newArray;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();

        E[] thisArray = this.toArray();
        for (E element : thisArray) {
            result.add(element);
        }

        E[] otherArray = other.toArray();
        for (E element : otherArray) {
            result.add(element);
        }

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        E[] thisArray = this.toArray();

        for (E element : thisArray) {
            if (other.contains(element)) result.add(element);
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        E[] thisArray = this.toArray();

        for (E element : thisArray) {
            if (!other.contains(element)) result.add(element);
        }

        return result;
    }
}
