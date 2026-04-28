# SimpleDictionary<K, V>

## Archivo fuente
- `src/dictionaryModule/SimpleDictionary.java`

## Rol
Contrato de diccionario (pares `key → value`, keys únicas, sin orden garantizado).

## Métodos
- `V put(K key, V value)`
- `boolean remove(K key)`
- `boolean containsKey(K key)`
- `V get(K key)`
- `K[] keys()`
- `V[] values()`
- `int size()`
- `boolean isEmpty()`
- `void clear()`

## Implementaciones
- [[SimpleArrayDictionary]]
- [[SimpleLinkedDictionary]]
