package setModule;

import list.LinkedNode;

public class SimpleLinkedSet<E> implements SimpleSet<E> {

    private LinkedNode<E> first;
    private LinkedNode<E> last;
    private int size;

    public SimpleLinkedSet() {
        first = null;
        last = null;
        size = 0;
    }

    private boolean same(E a, E b) {
        return a == null ? b == null : a.equals(b);
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) return false;

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
    public boolean remove(E element) {
        LinkedNode<E> current = first;

        while (current != null) {
            if (same(current.value, element)) {
                if (current == first && current == last) {
                    first = null;
                    last = null;
                } else if (current == first) {
                    first = current.next;
                    first.prev = null;
                } else if (current == last) {
                    last = current.prev;
                    last.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }

        return false;
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
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() { first = null; last = null; size = 0; }

    @Override
    @SuppressWarnings("unchecked")
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
        SimpleSet<E> result = new SimpleLinkedSet<>();
        E[] thisArray = this.toArray();

        for (E element : thisArray) {
            if (other.contains(element)) result.add(element);
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        E[] thisArray = this.toArray();

        for (E element : thisArray) {
            if (!other.contains(element)) result.add(element);
        }

        return result;
    }
}
