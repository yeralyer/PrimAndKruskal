

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Integer> rank = new HashMap<>();

    public String find(String node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
            rank.put(node, 0);
        }
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public boolean union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);

        if (root1.equals(root2)) {
            return false; // They are already in the same set, so no cycle
        }

        // Union by rank
        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }

        return true;
    }
}
