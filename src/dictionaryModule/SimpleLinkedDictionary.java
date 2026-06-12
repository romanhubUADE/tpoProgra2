package dictionaryModule;

import listModule.SimpleList;
import listModule.SimpleLinkedList;

/**
 * Implementación del diccionario usando una lista enlazada de nodos (clave, valor, siguiente).
 * Los nuevos elementos se insertan al frente. Búsqueda lineal O(n).
 */
public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    private SimpleDictionaryNode<K, V> first; // primer nodo de la cadena
    private int size;

    /** Inicializa el diccionario vacío. */
    public SimpleLinkedDictionary() {
        first = null;
        size = 0;
    }

    /**
     * Recorre la lista buscando la clave. Si la encuentra, actualiza el valor y devuelve el anterior.
     * Si no existe, inserta un nuevo nodo al frente y devuelve null.
     */
    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException("key cannot be null");
        if (value == null) throw new NullPointerException("value cannot be null");

        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) {
                // Clave encontrada: reemplazamos el valor
                V previous = current.value;
                current.value = value;
                return previous;
            }
            current = current.next;
        }

        // Clave nueva: se agrega al frente de la lista
        SimpleDictionaryNode<K, V> newNode = new SimpleDictionaryNode<>(key, value);
        newNode.next = first;
        first = newNode;
        size++;
        return null;
    }

    /**
     * Elimina el nodo con la clave dada.
     * Trata por separado el caso en que sea el primer nodo para evitar acceder a un nodo anterior inexistente.
     */
    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        if (isEmpty()) return false;

        // Caso especial: la clave está en el primer nodo
        if (first.key.equals(key)) {
            first = first.next;
            size--;
            return true;
        }

        // Recorremos buscando el nodo anterior al que hay que eliminar
        SimpleDictionaryNode<K, V> current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next; // saltamos el nodo a eliminar
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /** Recorre la lista buscando la clave; devuelve true si la encuentra. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    /** Recorre la lista y devuelve el valor de la clave, o null si no existe. */
    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    /** Recorre la lista y agrega cada clave a una lista resultado. */
    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleLinkedList<K>();
        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            result.add(current.key);
            current = current.next;
        }
        return result;
    }

    /** Recorre la lista y copia cada valor en un arreglo resultado. */
    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] result = (V[]) new Object[size];
        SimpleDictionaryNode<K, V> current = first;
        int index = 0;
        while (current != null) {
            result[index] = current.value;
            current = current.next;
            index++;
        }
        return result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    /** Desconecta todos los nodos dejando que el GC los limpie. */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }
}
