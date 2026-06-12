# Clase 05B - TDA Set

## Concepto

Un **Set** (Conjunto) es una colección de elementos **no repetidos**. No se garantiza el orden de los elementos dentro de la estructura.

Permite operaciones de **pertenencia** y **comparación entre conjuntos**.

En Java existen `HashSet<E>`, `LinkedHashSet<E>` y `TreeSet<E>`, que incluyen optimizaciones avanzadas. En la materia usamos versiones simplificadas.

---

## Interfaz

```java
public interface SimpleSet<E> {
    // Operaciones básicas
    boolean add(E element);        // Agrega el elemento. Devuelve true si fue exitoso (no existía)
    boolean remove(E element);     // Remueve el elemento. Devuelve true si fue exitoso
    boolean contains(E element);   // Devuelve true si el elemento está en el conjunto

    // Auxiliares
    int size();
    boolean isEmpty();
    void clear();
    E[] toArray();

    // Operaciones entre conjuntos
    SimpleSet<E> unionWith(SimpleSet<E> other);        // Todos los elementos de ambos
    SimpleSet<E> intersectWith(SimpleSet<E> other);    // Solo los que existen en ambos
    SimpleSet<E> differenceWith(SimpleSet<E> other);   // Los que están en éste pero no en other
}
```

### Ejemplo de operaciones entre sets

```java
SimpleSet<Integer> a = { 1, 2, 3 }
SimpleSet<Integer> b = { 3, 4, 5 }

a.unionWith(b)       -> { 1, 2, 3, 4, 5 }
a.intersectWith(b)   -> { 3 }
a.differenceWith(b)  -> { 1, 2 }
b.differenceWith(a)  -> { 4, 5 }
```

---

## Complejidad

Todas las operaciones básicas son **O(n)** en ambas implementaciones, porque en algún punto hay que iterar sobre todo el Set:
- `contains`: verifica si el elemento existe
- `add`: llama a `contains` antes de agregar
- `remove`: itera para buscar y remover

---

## Implementación Estática (`SimpleArraySet<E>`)

- Usa `validateSize` y `resize` igual que otras estructuras estáticas
- **El orden no se garantiza**, así que no se corren todos los elementos al remover
- Al remover: se mueve el **último elemento al lugar vacío** (O(1) dentro del proceso de búsqueda)
- Si se remueve el último o único elemento, se nullea esa posición
- Al insertar: siempre al final

---

## Implementación Dinámica (`SimpleLinkedSet<E>`)

- No introduce conceptos nuevos respecto a otras estructuras dinámicas
- El recorrido y reconexión de nodos es igual que en otras estructuras enlazadas
- Todas las operaciones siguen siendo **O(n)** por las búsquedas con `contains`
- Si se intenta agregar un elemento repetido → lanza `IllegalArgumentException`
