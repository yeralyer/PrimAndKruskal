import java.util.List;

public class Result {
    List<Edgee> mstEdges;
    int totalCost;
    int verticesCount;
    int edgesCount;
    long executionTime;

    public Result(List<Edgee> mstEdges, int totalCost, int verticesCount, int edgesCount, long executionTime) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.verticesCount = verticesCount;
        this.edgesCount = edgesCount;
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "MST Edges: " + mstEdges + ", Total Cost: " + totalCost +
                ", Vertices: " + verticesCount + ", Edges: " + edgesCount +
                ", Execution Time: " + executionTime + "ms";
    }
}