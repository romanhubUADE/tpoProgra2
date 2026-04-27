package tda;

import exception.DuplicateKeyException;
import exception.KeyNotFoundException;

public class LinkedDictionary<K, V> implements Dictionary<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key   = key;
            this.value = value;
            this.next  = null;
        }
    }

    private Entry<K, V> head;
    private int count;

    public LinkedDictionary() {
        head  = null;
        count = 0;
    }

    @Override
    public void put(K key, V value) throws DuplicateKeyException {
        validateKey(key);
        validateValue(value);
        if (containsKey(key)) {
            throw new DuplicateKeyException("La clave '" + key + "' ya existe en el diccionario.");
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        head = newEntry;
        count++;
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        validateKey(key);
        Entry<K, V> entry = findEntry(key);
        if (entry == null) {
            throw new KeyNotFoundException("La clave '" + key + "' no existe en el diccionario.");
        }
        return entry.value;
    }

    @Override
    public void update(K key, V value) throws KeyNotFoundException {
        validateKey(key);
        validateValue(value);
        Entry<K, V> entry = findEntry(key);
        if (entry == null) {
            throw new KeyNotFoundException("La clave '" + key + "' no existe en el diccionario.");
        }
        entry.value = value;
    }

    @Override
    public void remove(K key) throws KeyNotFoundException {
        validateKey(key);
        if (head == null) {
            throw new KeyNotFoundException("La clave '" + key + "' no existe en el diccionario.");
        }

        if (head.key.equals(key)) {
            head = head.next;
            count--;
            return;
        }

        Entry<K, V> prev = head;
        while (prev.next != null && !prev.next.key.equals(key)) {
            prev = prev.next;
        }

        if (prev.next == null) {
            throw new KeyNotFoundException("La clave '" + key + "' no existe en el diccionario.");
        }

        prev.next = prev.next.next;
        count--;
    }

    @Override
    public boolean containsKey(K key) {
        validateKey(key);
        return findEntry(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    private Entry<K, V> findEntry(K key) {
        Entry<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null.");
        }
    }

    private void validateValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null.");
        }
    }
}
