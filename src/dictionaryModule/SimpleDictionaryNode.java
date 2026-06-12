package dictionaryModule;

/**
 * Nodo encadenado del diccionario: guarda una clave, su valor asociado
 * y la referencia al siguiente nodo de la lista.
 */
public class SimpleDictionaryNode<K, V> {
    public K key;
    public V value;
    public SimpleDictionaryNode<K, V> next;

    /** Crea un nodo con la clave y el valor dados. El puntero al siguiente queda en null. */
    public SimpleDictionaryNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
