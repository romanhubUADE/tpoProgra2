package graphModule;

import dictionaryModule.SimpleArrayDictionary;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleLinkedList;
import listModule.SimpleList;

/**
 * Implementación del TDA Grafo usando listas de adyacencia.
 * Internamente usa un diccionario: cada vértice mapea a la lista de aristas que salen de él.
 */
public class ListGraph<T> implements Graph<T> {
    // Diccionario: vértice -> lista de aristas salientes
    private SimpleDictionary<T, SimpleList<Edge<T>>> adjacencyList;

    public ListGraph() {
        adjacencyList = new SimpleArrayDictionary<T, SimpleList<Edge<T>>>();
    }

    @Override
    public SimpleList<T> vertices() {
        return adjacencyList.keys();
    }

    @Override
    public SimpleList<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public boolean addVertex(T vertex) {
        if (containsVertex(vertex)) return false;
        // Cada vértice nuevo arranca con una lista de vecinos vacía.
        adjacencyList.put(vertex, new SimpleLinkedList<Edge<T>>());
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (!containsVertex(vertex)) return false;

        // Primero eliminamos la entrada del vértice, luego limpiamos aristas que apuntan a él.
        adjacencyList.remove(vertex);
        SimpleList<T> vertices = vertices();
        for (int i = 0; i < vertices.size(); i++)
            removeEdge(vertices.get(i), vertex);
        return true;
    }

    @Override
    public boolean containsEdge(T from, T to) {
        return getEdge(from, to) != null;
    }

    @Override
    public int getWeight(T from, T to) {
        Edge<T> targetEdge = getEdge(from, to);
        if (targetEdge == null) return -1;
        return targetEdge.weight;
    }

    /**
     * Busca la arista que va de 'from' a 'to' recorriendo la lista de vecinos.
     * Devuelve null si 'from' no existe o no hay arista hacia 'to'.
     */
    private Edge<T> getEdge(T from, T to) {
        if (!containsVertex(from)) return null;

        SimpleList<Edge<T>> edges = adjacencyList.get(from);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).destination.equals(to)) return edges.get(i);
        }
        return null;
    }

    @Override
    public boolean addEdge(T from, T to, int weight) {
        // Si alguno de los dos vértices no existe, lo creamos automáticamente.
        addVertex(from);
        addVertex(to);
        Edge<T> edge = getEdge(from, to);

        if (edge == null) {
            // Arista nueva: la agregamos a la lista de vecinos de 'from'.
            adjacencyList.get(from).add(new Edge<T>(to, weight));
            return true;
        }

        // La arista ya existe: solo actualizamos el peso si cambió.
        if (edge.weight != weight) {
            edge.weight = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(T from, T to) {
        Edge<T> edge = getEdge(from, to);
        if (edge == null) return false;
        return adjacencyList.get(from).remove(edge);
    }
}
