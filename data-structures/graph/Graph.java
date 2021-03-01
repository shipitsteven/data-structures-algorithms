import java.util.HashMap;
import java.util.LinkedList;
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

    private class Node<K, E> {
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

        test.addEdge("Seattle", "Portland", "PNW cities");
        test.addEdge("Seattle", "Los Angeles", "West Coast cities");

        System.out.println(test.toString());
    }
}