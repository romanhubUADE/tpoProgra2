package dictionaryModule;

public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    private SimpleDictionaryNode<K, V> first;
    private int size;

    public SimpleLinkedDictionary() {
        first = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException("key cannot be null");
        if (value == null) throw new NullPointerException("value cannot be null");

        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) {
                V previous = current.value;
                current.value = value;
                return previous;
            }
            current = current.next;
        }

        SimpleDictionaryNode<K, V> newNode = new SimpleDictionaryNode<>(key, value);
        newNode.next = first;
        first = newNode;
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        if (isEmpty()) return false;

        if (first.key.equals(key)) {
            first = first.next;
            size--;
            return true;
        }

        SimpleDictionaryNode<K, V> current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

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

    @Override
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        SimpleDictionaryNode<K, V> current = first;
        int index = 0;
        while (current != null) {
            result[index] = current.key;
            current = current.next;
            index++;
        }
        return result;
    }

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

    @Override
    public void clear() {
        first = null;
        size = 0;
    }
}
