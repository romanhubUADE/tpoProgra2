package graphModule;

import dictionaryModule.SimpleArrayDictionary;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleList;
import priorityQueueModule.SimpleLinkedPriorityQueue;
import priorityQueueModule.SimplePriorityQueue;
import setModule.SimpleArraySet;
import setModule.SimpleSet;

public class DijkstraSolver {

    public static <T> SimpleDictionary<T, PathInfo<T>> dijkstraAllNodes(Graph<T> graph, T origin) {
        SimpleDictionary<T, PathInfo<T>> result = new SimpleArrayDictionary<T, PathInfo<T>>();

        if (!graph.containsVertex(origin)) return result;

        SimpleList<T> vertices = graph.vertices();
        int vertexCount = vertices.size();

        for (int i = 0; i < vertexCount; i++) {
            result.put(vertices.get(i), new PathInfo<T>(null, Integer.MAX_VALUE));
        }
        result.get(origin).cost = 0;

        SimplePriorityQueue<T> unvisited = new SimpleLinkedPriorityQueue<T>();
        SimpleSet<T> visited = new SimpleArraySet<T>();

        unvisited.enqueue(origin, 0);

        while (!unvisited.isEmpty()) {
            T current = unvisited.dequeue();
            if (visited.contains(current))
                continue;

            int costToCurrent = result.get(current).cost;
            SimpleList<Edge<T>> neighbors = graph.getNeighbors(current);
            int neighborsCount = neighbors.size();

            for (int i = 0; i < neighborsCount; i++) {
                T neighbor = neighbors.get(i).destination;
                if (visited.contains(neighbor))
                    continue;
                int totalCost = costToCurrent + neighbors.get(i).weight;

                unvisited.enqueue(neighbor, totalCost);

                if (totalCost < result.get(neighbor).cost) {
                    result.get(neighbor).previous = current;
                    result.get(neighbor).cost = totalCost;
                }
            }
            visited.add(current);
        }
        return result;
    }

}
