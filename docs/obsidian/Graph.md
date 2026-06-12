# Graph<T>

## Archivo fuente
- `src/graphModule/Graph.java`

## Rol
Contrato de grafo: conjunto de **vértices** conectados por **aristas** (con peso). Modela una red.

## Métodos
- `T[] vertices()`
- `boolean addVertex(T vertex)`
- `boolean removeVertex(T vertex)`
- `boolean addEdge(T from, T to, int weight)`
- `boolean removeEdge(T from, T to)`
- `boolean containsVertex(T vertex)`
- `boolean containsEdge(T from, T to)`
- `int getWeight(T from, T to)`

## Implementaciones
- [[ListGraph]]

## Referencias
- [[Edge]]
