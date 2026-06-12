# Clase 10 - TDA Graph

## Concepto

Un **Graph** o **Grafo** es una estructura que busca representar una **red**. Consta de dos conjuntos:

- **Vértices** (o **Nodos**): representan una entidad, objeto, punto geográfico, etc.
- **Aristas** (o **Edges**): representan la **conexión** entre dos vértices.

La **Teoría de Grafos** fue gestada por **Leonhard Euler** en 1736, como respuesta al **Problema de los Puentes de Königsberg**.

Muchas estructuras se pueden abstraer a grafos:
- Mapas geográficos (GPS).
- Conexiones de red (toda la internet).
- Redes de contactos interpersonales.
- Redes neuronales para Machine Learning.

---

## Clasificación

Los grafos se categorizan según **dos dimensiones**:

- **Dirigidos / no dirigidos**: en un grafo **dirigido** las aristas van en un solo sentido, y hablamos de vértices **origen** y **destino**. En uno **no dirigido** la conexión es mutua.
- **Ponderados / no ponderados**: en un grafo **ponderado** las aristas tienen un **peso** o **costo** (distancia, caminos desfavorables, etc.). En uno no ponderado, todas valen igual.

---

## Vocabulario

- **Adyacentes** / **vecinos**: dos vértices que comparten una arista.
- **Vértices aislados**: aquellos que no tienen aristas.
- **Aristas salientes**: un vértice es **origen** de ellas.
- **Aristas entrantes**: un vértice es **destino** de ellas.
- **Camino**: secuencia de vértices vecinos.
- **Peso de un camino**: suma de los pesos de sus aristas.
- **Alcanzable**: un vértice lo es desde otro si existe un camino posible entre ellos.
- **Ciclo**: un camino que empieza y termina en el mismo vértice.

---

## Interfaz

```java
public interface Graph<T> {
    T[] vertices();                              // Todos los vértices
    boolean addVertex(T vertex);                 // Agrega un vértice
    boolean removeVertex(T vertex);              // Remueve un vértice y sus aristas
    boolean addEdge(T from, T to, int weight);   // Agrega o modifica una arista
    boolean removeEdge(T from, T to);            // Remueve una arista
    boolean containsVertex(T vertex);            // ¿Existe el vértice?
    boolean containsEdge(T from, T to);          // ¿Existe la arista?
    int getWeight(T from, T to);                 // Peso de la arista (-1 si no existe)
}
```

> Nota: las slides muestran las funciones `remove` como `void` ("se manejan solo"). En esta implementación se siguió la **firma de la interfaz** (slide 19), que las declara como `boolean` (true si removió, false si no existía). Es consistente con el resto de los módulos.

---

## Implementación

Las 3 maneras más comunes de implementar un Graph son:

1. **Matriz de Adyacencia** (estática): filas y columnas para los vértices, las celdas guardan conexión / peso. Ineficiente en memoria con pocas conexiones; destaca en matrices fijas, densas y muy interconectadas.
2. **Lista de Adyacencia** (dinámica): un diccionario donde cada **vértice es una key**, y su **value es una lista de sus vecinos**.
3. **Enfoque Orientado a Objetos**: clases custom para `Node` y/o `Edge`. Similar a la lista de adyacencia, pero permite expandir al nodo con más datos y funcionalidad.

> La distinción entre estática y dinámica es **distinta** que en otros TDAs.

---

## Implementación Dinámica (`ListGraph<T>`)

Es una **Lista de Adyacencia**. La "lista" es:

```java
SimpleDictionary<T, SimpleList<Edge<T>>> adjacencyList;
```

Cada **key** es un vértice; su **value** es la lista de aristas salientes (sus vecinos).

### La clase `Edge<T>`

```java
class Edge<T> {
    T destination;   // el vértice destino
    int weight;      // el peso de la arista
}
```

- Debe **overridear `equals`** para comparar **ambas variables** (`destination` y `weight`).
- **OJO**: la `Edge` es una clase aparte. **NO** es un `Graph`, y el `Graph` **NO** tiene `weight` (el peso vive en la arista).

### Función auxiliar `getEdge`

La mayoría de las funciones necesitan una auxiliar:

```java
private Edge<T> getEdge(T from, T to)
```

- Para buscar un edge, muchas veces solo importa el **destino** (si no sabemos el peso, es difícil encontrarlo de otra forma).
- Encuentra el edge cuyo destino sea el buscado.
- Se **reutiliza** para saber si existe, agregar, modificar y remover.

### Reglas de las operaciones

- `addEdge`: primero hace `addVertex(from)` y `addVertex(to)` (los crea si no están). Si la arista no existe, la agrega. Si existe con otro peso, lo **modifica**. Si existe con el mismo peso, devuelve `false`.
- `removeVertex`: borra la key del diccionario y luego **recorre todos los vértices** para eliminar las aristas **entrantes** a ese vértice (un vértice se guarda como key y también dentro de los vecinos de otros).
- Para **borrar** una arista no se necesita el peso; para **guardarla**, sí.

---

## Complejidad

Al apoyarse en un `SimpleDictionary` y `SimpleList` (ambos **O(n)** en sus búsquedas), las operaciones del grafo son **O(n)** o peores:

- `removeVertex` es la más costosa: recorre **todos los vértices** para limpiar aristas entrantes → **O(V · E)** en el peor caso.
- `getEdge` recorre la lista de vecinos del origen → **O(E)**.
