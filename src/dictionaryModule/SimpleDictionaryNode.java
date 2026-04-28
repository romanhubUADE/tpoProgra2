package dictionaryModule;

public class SimpleDictionaryNode<K, V> {
    public K key;
    public V value;
    public SimpleDictionaryNode<K, V> next;

    public SimpleDictionaryNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
