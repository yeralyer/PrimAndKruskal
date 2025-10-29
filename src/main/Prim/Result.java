// Result.java - ИЗМЕНЕНИЕ
import java.util.List;

public class Result {
    public List<Edge> mstEdges;
    public int totalCost;
    public int verticesCount;
    public int edgesCount;
    public long executionTime;

    public Result(List<Edge> mstEdges, int totalCost, int verticesCount, int edgesCount, long executionTime) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.verticesCount = verticesCount;
        this.edgesCount = edgesCount;
        this.executionTime = executionTime;
    }
}