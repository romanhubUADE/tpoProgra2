package graphModule;

import listModule.SimpleList;

/**
 * TDA Grafo dirigido y ponderado de tipo genérico.
 * Define las operaciones básicas para trabajar con vértices y aristas con peso.
 */
public interface Graph<T> {
    /** Devuelve todos los vértices del grafo. */
    public SimpleList<T> vertices();

    /** Agrega un vértice nuevo. Devuelve false si ya existía. */
    boolean addVertex(T vertex);

    /** Elimina un vértice y todas las aristas que lleguen a él. Devuelve false si no existía. */
    boolean removeVertex(T vertex);

    /** Agrega (o actualiza) una arista dirigida de 'from' a 'to' con el peso dado. Devuelve false si no hubo cambio. */
    boolean addEdge(T from, T to, int weight);

    /** Elimina la arista dirigida de 'from' a 'to'. Devuelve false si no existía. */
    boolean removeEdge(T from, T to);

    /** Indica si el vértice existe en el grafo. */
    boolean containsVertex(T vertex);

    /** Indica si existe una arista dirigida de 'from' a 'to'. */
    boolean containsEdge(T from, T to);

    /** Devuelve el peso de la arista de 'from' a 'to', o -1 si no existe. */
    int getWeight(T from, T to);

    /** Devuelve la lista de aristas que salen desde 'vertex' (sus vecinos directos). */
    public SimpleList<Edge<T>> getNeighbors(T vertex);
}
