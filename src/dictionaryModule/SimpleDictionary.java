package dictionaryModule;

import listModule.SimpleList;

/**
 * Interfaz del TDA Diccionario: asocia claves únicas de tipo K con valores de tipo V.
 * Define las operaciones básicas que cualquier implementación debe respetar.
 */
public interface SimpleDictionary<K, V> {
    /** Agrega o reemplaza un par clave-valor. Devuelve el valor anterior si la clave ya existía, o null si era nueva. */
    V put(K key, V value);
    /** Elimina la entrada con la clave dada. Devuelve true si existía, false si no. */
    boolean remove(K key);
    /** Indica si la clave existe en el diccionario. */
    boolean containsKey(K key);
    /** Devuelve el valor asociado a la clave, o null si no existe. */
    V get(K key);
    /** Devuelve una lista con todas las claves almacenadas. */
    SimpleList<K> keys();
    /** Devuelve un arreglo con todos los valores almacenados. */
    V[] values();
    /** Cantidad de pares clave-valor en el diccionario. */
    int size();
    /** Indica si el diccionario está vacío. */
    boolean isEmpty();
    /** Elimina todos los elementos del diccionario. */
    void clear();
}
