package setModule;

/**
 * TDA Conjunto (Set): colección sin elementos repetidos.
 * Define las operaciones básicas de un conjunto matemático,
 * incluyendo unión, intersección y diferencia.
 */
public interface SimpleSet<E> {
    /** Agrega el elemento si no existe. Devuelve true si fue agregado, false si ya estaba. */
    boolean add(E element);

    /** Elimina el elemento si existe. Devuelve true si fue eliminado, false si no estaba. */
    boolean remove(E element);

    /** Devuelve true si el elemento está en el conjunto. */
    boolean contains(E element);

    /** Devuelve la cantidad de elementos. */
    int size();

    /** Devuelve true si el conjunto no tiene elementos. */
    boolean isEmpty();

    /** Elimina todos los elementos del conjunto. */
    void clear();

    /** Devuelve los elementos como arreglo. */
    E[] toArray();

    /** Devuelve un nuevo conjunto con todos los elementos de este conjunto y del otro (A ∪ B). */
    SimpleSet<E> unionWith(SimpleSet<E> other);

    /** Devuelve un nuevo conjunto con los elementos que están en ambos conjuntos (A ∩ B). */
    SimpleSet<E> intersectWith(SimpleSet<E> other);

    /** Devuelve un nuevo conjunto con los elementos de este que NO están en el otro (A - B). */
    SimpleSet<E> differenceWith(SimpleSet<E> other);
}
