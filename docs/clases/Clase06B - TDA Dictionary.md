# Clase 06B - TDA Dictionary

## Concepto

Un **Dictionary** es una colección de **elementos asociados**: cada entrada es un par `(key, value)`.

- La **key** funciona como "índice" para buscar.
- El **value** es el dato que guardamos.
- Las **keys son únicas**; los values pueden repetirse.
- **No se garantiza el orden** de las keys dentro de la estructura.
- Permite búsquedas con **significado** en vez de por índice numérico.

---

## Interfaz

```java
public interface SimpleDictionary<K, V> {
    // Operaciones principales
    V put(K key, V value);          // Agrega o reemplaza. Devuelve el value anterior (null si no existía)
    boolean remove(K key);          // Remueve el par. Devuelve true si existía
    boolean containsKey(K key);     // Devuelve true si la key está
    V get(K key);                   // Devuelve el value asociado, o null si no existe

    // Vistas
    K[] keys();                     // Array con todas las keys
    V[] values();                   // Array con todas las values

    // Auxiliares
    int size();
    boolean isEmpty();
    void clear();
}
```

> Tanto `key` como `value` `null` lanzan `NullPointerException`.

### Notas sobre `put`

- Si la `key` **ya existe** → reemplaza el `value` y devuelve el anterior.
- Si la `key` **no existe** → agrega el par y devuelve `null`.

---

## En Java

Existen `Dictionary<K,V>` (clase deprecada) y `Map<K,V>` (interfaz). En la materia trabajamos con una versión "pura":

- Interfaz: `SimpleDictionary<K, V>`
- Clases: `SimpleArrayDictionary<K, V>` y `SimpleLinkedDictionary<K, V>`

---

## Complejidad

Las dos implementaciones tienen la misma complejidad temporal: **O(n)** para todas las operaciones básicas, porque en algún momento hay que iterar sobre todas las keys (igual que en Set):

- `containsKey`: para chequear existencia.
- `put`: para ver si reemplaza o agrega.
- `remove`: para ver si puede remover.

---

## Implementación Estática (`SimpleArrayDictionary<K, V>`)

Funciona prácticamente igual que `SimpleArraySet`:

- Vuelven `validateSize` y `resize`.
- Internamente se mantienen **dos arrays sincronizados**: `keys[]` y `values[]` (mismo índice = mismo par).
- Como el orden **no se garantiza**, no hay que correr todos los elementos.
- Al **remover**: se mueve el **último elemento al lugar vacío** y se nullea la última posición (o la única si era el único).
- Al **insertar**: siempre al final, después de iterar para ver si hay que reemplazar.

---

## Implementación Dinámica (`SimpleLinkedDictionary<K, V>`)

Usa un nodo nuevo:

```java
class SimpleDictionaryNode<K, V> {
    K key;
    V value;
    SimpleDictionaryNode<K, V> next;   // Solo next: lista simplemente enlazada
}
```

- Las operaciones siguen siendo **O(n)** por las búsquedas.
- La iteración se hace con un `current` y mirando `current.next`:
  - Se itera mientras `current.next != null` (enlace simple).
  - Se compara con `current.next.key.equals(key)`.
  - Para remover, se reconecta con `current.next = current.next.next`.
- **Casos especiales**:
  - Diccionario vacío.
  - El `first` tiene la `key` que buscamos (no hay `current.next` que reconectar).
