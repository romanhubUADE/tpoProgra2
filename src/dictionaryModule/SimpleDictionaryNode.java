package dictionaryModule;

class SimpleDictionaryNode<K, V> {
    K key;
    V value;
    SimpleDictionaryNode<K, V> next;

    SimpleDictionaryNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

