package graphModule;

public class Edge<T> {
    public T destination;
    public int weight;

    public Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

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
