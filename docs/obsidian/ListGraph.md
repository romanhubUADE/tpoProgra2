# ListGraph<T>

## Archivo fuente
- `src/graphModule/ListGraph.java`

## Tipo
Implementación dinámica de [[Graph]] mediante **Lista de Adyacencia**:

```java
SimpleDictionary<T, SimpleList<Edge<T>>> adjacencyList;
```

Cada vértice es una **key**; su **value** es la lista de [[Edge]] salientes (sus vecinos).

## Ideas clave
- Auxiliar `getEdge(from, to)`: busca la arista por destino. Se reutiliza para contains / add / modificar / remove.
- `addEdge`: crea los vértices si faltan; agrega la arista, o modifica el peso si ya existía.
- `removeVertex`: borra la key y luego recorre **todos** los vértices para eliminar las aristas **entrantes**.
- `getWeight` devuelve `-1` si la arista no existe.

## Dependencias
- [[SimpleDictionary]] (activa: [[SimpleArrayDictionary]])
- [[SimpleList]] (activa: [[SimpleLinkedList]])
- [[Edge]]

## Referencias
- [[Graph]]
