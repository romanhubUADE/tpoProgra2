package tda;

import exception.DuplicateKeyException;
import exception.KeyNotFoundException;

public interface Dictionary<K, V> {

    void put(K key, V value) throws DuplicateKeyException;

    V get(K key) throws KeyNotFoundException;

    void update(K key, V value) throws KeyNotFoundException;

    void remove(K key) throws KeyNotFoundException;

    boolean containsKey(K key);

    boolean isEmpty();

    int size();
}
