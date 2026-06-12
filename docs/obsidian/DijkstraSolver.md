# DijkstraSolver

## Archivo fuente
- `src/graphModule/DijkstraSolver.java`

## Qué es
Clase **utilitaria** (solo método estático) que implementa el **Algoritmo de Dijkstra** sobre un [[Graph]]. No es un TDA ni un ejercicio: es el algoritmo puro, desacoplado de la interfaz de consola.

## Método
```java
public static <T> SimpleDictionary<T, PathInfo<T>> dijkstraAllNodes(Graph<T> graph, T origin)
```
- Calcula el camino mínimo desde `origin` hacia **todos** los vértices.
- Devuelve un diccionario: cada vértice → su [[PathInfo]] (costo acumulado + previo).
- Si `origin` no es vértice del grafo, devuelve el diccionario vacío.

## Cómo funciona
1. Inicializa todos los vértices con costo `Integer.MAX_VALUE` y previo `null`; al origen le pone costo `0`.
2. Usa una [[SimpleLinkedPriorityQueue]] como cola de **no visitados** (prioridad = costo) y un [[SimpleArraySet]] de **visitados**.
3. Bucle `while`: desencola el más cercano, lo saltea si ya está visitado, recorre sus vecinos (`getNeighbors`), relaja costos (si `costoActual + peso < costoVecino`, actualiza costo y previo) y encola, y marca el nodo como visitado.

## Dependencias
- [[Graph]] (usa `vertices()`, `containsVertex()`, `getNeighbors()`)
- [[Edge]] (los vecinos vienen como `Edge<T>`)
- [[PathInfo]] (el valor de la tabla resultado)
- [[SimpleDictionary]] / [[SimpleArrayDictionary]]
- [[SimpleLinkedPriorityQueue]] · [[SimpleArraySet]] · [[SimpleList]]

## Nota de diseño
Devuelve `PathInfo<T>` y no `Edge<T>` (como sugiere la diapo). Ver el porqué en [[Clase11 - Dijkstra]] → "Decisión de diseño".

## TP / Clase
- [[TP10 - Graph y Dijkstra]] · [[Clase11 - Dijkstra]]
