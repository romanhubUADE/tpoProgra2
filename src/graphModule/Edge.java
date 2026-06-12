package graphModule;

/**
 * Representa una arista dirigida y ponderada del grafo.
 * Guarda el vértice de destino y el peso (costo) de la conexión.
 */
public class Edge<T> {
    public T destination; // vértice al que llega la arista
    public int weight;    // costo o distancia de esta conexión

    public Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Dos aristas son iguales si tienen el mismo destino Y el mismo peso.
     * Se usa al eliminar una arista de la lista de vecinos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge<?> other = (Edge<?>) obj;
        if (weight != other.weight) return false;
        if (destination == null) return other.destination == null;
        return destination.equals(other.destination);
    }
}
