package setModule;

public interface SimpleSet<E> {

    boolean add(E element);

    boolean remove(E element);

    boolean contains(E element);

    int size();

    boolean isEmpty();

    void clear();

    E[] toArray();

    SimpleSet<E> unionWith(SimpleSet<E> other);

    SimpleSet<E> intersectWith(SimpleSet<E> other);

    SimpleSet<E> differenceWith(SimpleSet<E> other);
}
