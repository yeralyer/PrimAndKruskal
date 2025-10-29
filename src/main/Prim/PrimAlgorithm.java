// PrimAlgorithm.java - ИЗМЕНЕНИЕ
import java.util.*;

public class PrimAlgorithm {

    public static Result computeMST(Graph graph, String startVertex) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operationsCount = 0;
        long startTime = System.currentTimeMillis();

        visited.add(startVertex);
        pq.addAll(graph.getEdges(startVertex));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            operationsCount++;
            if (!visited.contains(edge.to)) {
                visited.add(edge.to);
                mstEdges.add(edge);
                totalCost += edge.weight;

                for (Edge nextEdge : graph.getEdges(edge.to)) {
                    if (!visited.contains(nextEdge.to)) {
                        pq.add(nextEdge);
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        return new Result(mstEdges, totalCost, visited.size(), operationsCount, executionTime);
    }
}