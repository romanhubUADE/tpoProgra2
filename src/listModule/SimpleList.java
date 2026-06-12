package listModule;

/**
 * Contrato (interfaz) que define las operaciones básicas de una lista genérica.
 * Cualquier implementación (array, lista enlazada, etc.) tiene que cumplir con esto.
 */
public interface SimpleList<E> {
    /** Agrega el elemento al final de la lista. Devuelve true si se agregó. */
    boolean add(E element);
    /** Inserta el elemento en la posición indicada, desplazando los siguientes. */
    void add(int index, E element);
    /** Elimina y devuelve el elemento en la posición indicada. */
    E remove(int index);
    /** Elimina la primera ocurrencia del objeto. Devuelve true si lo encontró. */
    boolean remove(Object object);
    /** Vacía la lista por completo. */
    void clear();
    /** Indica si el objeto está en la lista. */
    boolean contains(Object object);
    /** Devuelve el elemento en la posición indicada sin eliminarlo. */
    E get(int index);
    /** Reemplaza el elemento en la posición indicada y devuelve el valor anterior. */
    E set(int index, E element);
    /** Devuelve la cantidad de elementos en la lista. */
    int size();
    /** Indica si la lista no tiene elementos. */
    boolean isEmpty();
}
