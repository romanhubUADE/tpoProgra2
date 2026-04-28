package dictionaryModule;

public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private K[] keys;
    private V[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void validateSize(int newSize) {
        if (newSize > keys.length) resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    private int indexOf(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) return i;
        }
        return -1;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if (value instanceof String && ((String) value).isBlank()) throw new IllegalArgumentException("value cannot be blank");

        int index = indexOf(key);
        if (index != -1) {
            V previous = values[index];
            values[index] = value;
            return previous;
        }

        validateSize(size + 1);
        keys[size] = key;
        values[size] = value;
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        int index = indexOf(key);
        if (index == -1) return false;

        keys[index] = keys[size - 1];
        values[index] = values[size - 1];
        keys[size - 1] = null;
        values[size - 1] = null;
        size--;
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        return indexOf(key) != -1;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (key instanceof String && ((String) key).isBlank()) throw new IllegalArgumentException("key cannot be blank");
        int index = indexOf(key);
        if (index == -1) return null;
        return values[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        for (int i = 0; i < size; i++) result[i] = keys[i];
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] result = (V[]) new Object[size];
        for (int i = 0; i < size; i++) result[i] = values[i];
        return result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }
}
