import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithmProcessor {

    public static JsonObject kruskalAlgorithm(CityGraph graph) {
        long startTime = System.currentTimeMillis();

        // Sort edges by their weight
        Collections.sort(graph.getEdges(), (e1, e2) -> Integer.compare(e1.getWeight(), e2.getWeight()));

        UnionFind uf = new UnionFind();
        List<RoadEdge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operationsCount = 0;

        for (RoadEdge edge : graph.getEdges()) {
            operationsCount++;
            if (uf.union(edge.getFrom(), edge.getTo())) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
            }
        }

        long endTime = System.currentTimeMillis();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        // Output the result in the required format
        JsonObject result = new JsonObject();
        JsonArray mstEdgesArray = new JsonArray();
        for (RoadEdge edge : mstEdges) {
            JsonObject edgeJson = new JsonObject();
            edgeJson.addProperty("from", edge.getFrom());
            edgeJson.addProperty("to", edge.getTo());
            edgeJson.addProperty("weight", edge.getWeight());
            mstEdgesArray.add(edgeJson);
        }

        result.add("mst_edges", mstEdgesArray);
        result.addProperty("total_cost", totalCost);
        result.addProperty("operations_count", operationsCount);
        result.addProperty("execution_time_ms", executionTime); // Добавлено время выполнения

        return result;
    }

    public static JsonObject processGraph(JsonObject graphJson) {
        CityGraph graph = new CityGraph();
        for (int i = 0; i < graphJson.getAsJsonArray("edges").size(); i++) {
            JsonObject edgeJson = graphJson.getAsJsonArray("edges").get(i).getAsJsonObject();
            String from = edgeJson.get("from").getAsString();
            String to = edgeJson.get("to").getAsString();
            int weight = edgeJson.get("weight").getAsInt();
            graph.addEdge(from, to, weight);
        }

        // Убрано измерение времени здесь, так как оно теперь внутри kruskalAlgorithm
        JsonObject kruskalResult = kruskalAlgorithm(graph);

        // Return the result in the specified format
        JsonObject result = new JsonObject();
        result.addProperty("graph_id", graphJson.get("id").getAsInt());

        JsonObject inputStats = new JsonObject();
        inputStats.addProperty("vertices", graph.getNodes().size());
        inputStats.addProperty("edges", graph.getEdges().size());
        result.add("input_stats", inputStats);

        result.add("kruskal", kruskalResult);

        return result;
    }
}