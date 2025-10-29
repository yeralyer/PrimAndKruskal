

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CityGraph {
    private List<RoadEdge> edges = new ArrayList<>();
    private Set<String> nodes = new HashSet<>();

    public void addEdge(String from, String to, int weight) {
        edges.add(new RoadEdge(from, to, weight));
        nodes.add(from);
        nodes.add(to);
    }

    public List<RoadEdge> getEdges() {
        return edges;
    }

    public Set<String> getNodes() {
        return nodes;
    }
}
