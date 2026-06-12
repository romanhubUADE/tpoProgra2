package graphModule;

import listModule.SimpleList;

public interface Graph<T> {
    public SimpleList<T> vertices();
    boolean addVertex(T vertex);
    boolean removeVertex(T vertex);
    boolean addEdge(T from, T to, int weight);
    boolean removeEdge(T from, T to);
    boolean containsVertex(T vertex);
    boolean containsEdge(T from, T to);
    int getWeight(T from, T to);
    public SimpleList<Edge<T>> getNeighbors(T vertex);
}
