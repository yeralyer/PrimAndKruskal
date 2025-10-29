
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CityTransportationNetwork {
    public static void main(String[] args) {
        try {

            Gson gson = new Gson();
            FileReader reader = new FileReader("D:\\patterns projects\\DAAtransport\\src\\main\\Prim\\input_example.json");
            JsonObject inputJson = gson.fromJson(reader, JsonObject.class);


            JsonArray graphs = inputJson.getAsJsonArray("graphs");
            JsonArray results = new JsonArray();

            for (int i = 0; i < graphs.size(); i++) {
                JsonObject graphJson = graphs.get(i).getAsJsonObject();
                JsonObject result = processGraphWithBothAlgorithms(graphJson);
                results.add(result);
            }


            JsonObject outputJson = new JsonObject();
            outputJson.add("results", results);

            // Save the output to a file
            try (FileWriter writer = new FileWriter("D:\\patterns projects\\DAAtransport\\src\\main\\Prim\\output_example.json")) {
                gson.toJson(outputJson, writer);
            }

            System.out.println("Results have been written to output_example.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject processGraphWithBothAlgorithms(JsonObject graphJson) {
        JsonObject result = new JsonObject();
        result.addProperty("graph_id", graphJson.get("id").getAsInt());


        CityGraph kruskalGraph = new CityGraph();

        Graph primGraph = new Graph();


        for (int i = 0; i < graphJson.getAsJsonArray("edges").size(); i++) {
            JsonObject edgeJson = graphJson.getAsJsonArray("edges").get(i).getAsJsonObject();
            String from = edgeJson.get("from").getAsString();
            String to = edgeJson.get("to").getAsString();
            int weight = edgeJson.get("weight").getAsInt();

            kruskalGraph.addEdge(from, to, weight);
            primGraph.addEdge(from, to, weight);
        }


        JsonObject inputStats = new JsonObject();
        inputStats.addProperty("vertices", kruskalGraph.getNodes().size());
        inputStats.addProperty("edges", kruskalGraph.getEdges().size());
        result.add("input_stats", inputStats);


        JsonObject kruskalResult = KruskalAlgorithmProcessor.kruskalAlgorithm(kruskalGraph);
        result.add("kruskal", kruskalResult);


        String startNode = graphJson.getAsJsonArray("nodes").get(0).getAsString();
        Result primResult = PrimAlgorithm.computeMST(primGraph, startNode);


        JsonObject primJson = new JsonObject();

        JsonArray mstEdgesArray = new JsonArray();
        for (Edge edge : primResult.mstEdges) {
            JsonObject edgeJson = new JsonObject();
            edgeJson.addProperty("from", edge.from);
            edgeJson.addProperty("to", edge.to);
            edgeJson.addProperty("weight", edge.weight);
            mstEdgesArray.add(edgeJson);
        }

        primJson.add("mst_edges", mstEdgesArray);
        primJson.addProperty("total_cost", primResult.totalCost);
        primJson.addProperty("operations_count", primResult.edgesCount);
        primJson.addProperty("execution_time_ms", primResult.executionTime / 1000.0);

        result.add("prim", primJson);

        return result;
    }
}