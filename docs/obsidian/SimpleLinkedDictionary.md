# SimpleLinkedDictionary<K, V>

## Archivo fuente
- `src/dictionaryModule/SimpleLinkedDictionary.java`

## Tipo
Implementación dinámica de [[SimpleDictionary]] usando lista **simplemente enlazada**.

## Estructura interna
- `SimpleDictionaryNode<K, V> first`
- `int size`

## Ideas clave
- Lista simplemente enlazada: cada nodo tiene `key`, `value` y solo `next`.
- `put` recorre buscando la key; si existe reemplaza, si no inserta al frente.
- `remove` reconecta con `current.next = current.next.next`. Caso especial: lista vacía o key en `first`.

## Referencias
- [[SimpleDictionary]]
- [[SimpleDictionaryNode]]
