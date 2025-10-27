import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class GraphProcessor {

    public static void main(String[] args) throws IOException {

        String inputFile = "D:\\patterns projects\\DAAtransport\\src\\main\\java\\input_example.json";
        FileReader reader = new FileReader(inputFile);
        Gson gson = new Gson();
        GraphInput graphInput = gson.fromJson(reader, GraphInput.class);

        List<GraphResult> results = new ArrayList<>();
        for (GraphData graphData : graphInput.graphs) {
            Graph graph = new Graph();
            for (EdgeData edgeData : graphData.edges) {
                graph.addEdge(edgeData.from, edgeData.to, edgeData.weight);
            }


            Result result = PrimAlgorithm.computeMST(graph, graphData.nodes[0]);


            GraphResult graphResult = new GraphResult(graphData.id, result);
            results.add(graphResult);
        }


        FileWriter writer = new FileWriter("D:\\patterns projects\\DAAtransport\\src\\main\\java\\output_example.json");
        gson.toJson(new Output(results), writer);
        writer.close();

        System.out.println("Результаты сохранены в файл output_example.json");
    }
}

class GraphResult {
    int graph_id;
    Result prim_result;

    public GraphResult(int graph_id, Result prim_result) {
        this.graph_id = graph_id;
        this.prim_result = prim_result;
    }
}

class Output {
    List<GraphResult> results;

    public Output(List<GraphResult> results) {
        this.results = results;
    }
}

class GraphInput {
    List<GraphData> graphs;
}

class GraphData {
    public int id;
    String[] nodes;
    List<EdgeData> edges;
}

class EdgeData {
    String from;
    String to;
    int weight;
}

