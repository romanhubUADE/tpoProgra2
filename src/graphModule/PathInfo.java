package graphModule;

public class PathInfo<T> {
    public T previous;
    public int cost;

    public PathInfo(T previous, int cost) {
        this.previous = previous;
        this.cost = cost;
    }
}
