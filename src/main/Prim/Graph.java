

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addEdge(String from, String to, int weight) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to, weight));
        adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(to, from, weight));
    }

    public Collection<? extends Edge> getEdges(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }
}