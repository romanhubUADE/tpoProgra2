package dictionaryModule;

public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    private SimpleDictionaryNode<K, V> first;
    private SimpleDictionaryNode<K, V> last;
    private int size;

    public SimpleLinkedDictionary() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if (value instanceof String && ((String) value).isBlank()) throw new IllegalArgumentException("value cannot be blank");

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
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        if (isEmpty()) return false;

        if (first.key.equals(key)) {
            if (first == last) last = null;
            first = first.next;
            size--;
            return true;
        }

        SimpleDictionaryNode<K, V> current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                if (current.next == last) last = current;
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
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        SimpleDictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
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
    public void clear() { first = null; last = null; size = 0; }
}
