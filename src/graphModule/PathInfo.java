package graphModule;

/**
 * Información de camino para Dijkstra: por cada vértice guarda el costo
 * acumulado más corto conocido desde el origen, y el vértice previo en ese
 * camino (para poder reconstruirlo después).
 *
 * Es una clase aparte (y no se reutiliza Edge) para que se lea sola:
 * acá `previous` es el nodo anterior, no un destino.
 */
public class PathInfo<T> {
    public T previous;   // nodo anterior en el camino más corto (null = origen o no alcanzado)
    public int cost;     // distancia acumulada desde el origen

    public PathInfo(T previous, int cost) {
        this.previous = previous;
        this.cost = cost;
    }
}
