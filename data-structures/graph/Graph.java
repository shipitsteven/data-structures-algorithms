import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph<K, E> {

    private Map<K, List<Node<K, E>>> G = null;

    public Graph() {
        G = new HashMap<>();
    }

    public boolean addEdge(K v1, K v2, E edgeLabel) {
        if (!G.containsKey(v1)) {
            G.put(v1, new LinkedList<>());
        }

        G.get(v1).add(new Node<>(v1, v2, edgeLabel));

        return true;
    }

    public boolean addUndirectedEdge(K v1, K v2, E edgeLabel) {
        addEdge(v1, v2, edgeLabel);
        addEdge(v2, v2, edgeLabel);

        return true;
    }

    public String toString() {
        String data = "";
        for (K key : G.keySet()) {
            data += key + " ==> [ " + G.get(key) + " ]\n";
        }

        return data;
    }

    // Depth first search
    // Does not work, I'm passing in the wrong start param, it should be a node?
    // string rep of the node key? or something else?
    // But the algorithm should be solid
    public List depthFirstRecursive(Node<K, E> start) {
        List<Node<K, E>> result = new ArrayList<>();
        Map<Object, Boolean> visited = new HashMap<>();

        return dfs(start, result, visited);
    }

    private List dfs(Node<K, E> vertex, List<Node<K, E>> result, Map<Object, Boolean> visited) {
        if (vertex == null)
            return null;
        visited.put(vertex.key, true);
        result.add(vertex);
        for (Node<K, E> neighbor : G.get(vertex.key)) {
            if (!visited.containsKey(neighbor.key)) {
                return dfs(neighbor, result, visited);
            }
        }

        return result;

    }

    public class Node<K, E> {
        K key = null;
        K endNode = null;
        E edgeLabel = null;

        public Node(K key, K endNode, E edgeLabel) {
            this.key = key;
            this.endNode = endNode;
            this.edgeLabel = edgeLabel;
        }

        @Override
        public boolean equals(Object node1) {
            return key.equals(node1);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        public String toString() {
            return "{ key: " + key + " , endNode: " + endNode + ", edgeLabel " + edgeLabel + " }";
        }
    }

    public static void main(String[] args) {
        Graph<String, String> test = new Graph<>();

        test.addEdge("Seattle", "Portland", "One way");
        test.addEdge("Seattle", "Los Angeles", "One way");
        test.addEdge("Los Angeles", "Las Vegas", "One way");

        // System.out.println(test.depthFirstRecursive("Seattle"));
    }
}