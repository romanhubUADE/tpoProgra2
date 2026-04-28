# SimpleArrayDictionary<K, V>

## Archivo fuente
- `src/dictionaryModule/SimpleArrayDictionary.java`

## Tipo
Implementación estática de [[SimpleDictionary]] con dos arrays sincronizados (`keys[]` y `values[]`).

## Ideas clave
- Keys únicas: `put` reemplaza si existe, agrega si no.
- Remoción optimizada: reemplaza con el último par (key+value).
- `keys`/`values` retornan copias sin orden garantizado.

## Referencias
- [[SimpleDictionary]]
