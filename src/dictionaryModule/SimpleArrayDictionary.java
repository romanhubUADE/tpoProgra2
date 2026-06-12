package dictionaryModule;

import listModule.SimpleLinkedList;
import listModule.SimpleList;

/**
 * Implementación del diccionario usando dos arreglos paralelos: uno para claves y otro para valores.
 * Crece automáticamente cuando se llena (duplica capacidad). Búsqueda lineal O(n).
 */
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private K[] keys;
    private V[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    /** Inicializa los arreglos con capacidad 4. */
    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /** Verifica si hay lugar; si no, redimensiona antes de insertar. */
    private void validateSize(int newSize) {
        if (newSize > keys.length) resize();
    }

    /** Duplica la capacidad de ambos arreglos copiando los elementos existentes. */
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

    /** Busca la clave en el arreglo y devuelve su índice, o -1 si no existe. */
    private int indexOf(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) return i;
        }
        return -1;
    }

    /**
     * Agrega la clave con su valor. Si la clave ya existe, reemplaza el valor y devuelve el anterior.
     * Si es nueva, la agrega al final y devuelve null.
     */
    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException("key cannot be null");
       // if (value == null) throw new NullPointerException("value cannot be null");

        int index = indexOf(key);
        if (index != -1) {
            // La clave ya existe: actualizamos el valor
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

    /**
     * Elimina la entrada con la clave dada.
     * Para no dejar huecos, mueve el último elemento al lugar del eliminado.
     */
    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = indexOf(key);
        if (index == -1) return false;

        // Truco para evitar correr todos los elementos: el último tapa el hueco
        keys[index] = keys[size - 1];
        values[index] = values[size - 1];
        keys[size - 1] = null;
        values[size - 1] = null;
        size--;
        return true;
    }

    /** Devuelve true si la clave existe en el diccionario. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        return indexOf(key) != -1;
    }

    /** Devuelve el valor asociado a la clave, o null si no existe. */
    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException("key cannot be null");
        int index = indexOf(key);
        if (index == -1) return null;
        return values[index];
    }

    /** Devuelve una lista enlazada con todas las claves almacenadas. */
    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleLinkedList<K>();
        for (int i = 0; i < size; i++) result.add(keys[i]);
        return result;
    }

    /** Devuelve un arreglo con todos los valores (sin nulos al final). */
    @Override
    public V[] values() {
        V[] result = (V[]) new Object[size];
        for (int i = 0; i < size; i++) result[i] = values[i];
        return result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    /** Limpia el diccionario seteando en null todas las posiciones usadas. */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }
}
