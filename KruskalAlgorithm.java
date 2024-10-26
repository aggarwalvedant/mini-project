import java.util.*;

public class KruskalAlgorithm {
    private Graph graph;

    public KruskalAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public MSTResult findMST() {
        List<Edge> result = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        pq.addAll(graph.getEdges());

        Map<Vertex, Vertex> parent = new HashMap<>();
        for (Vertex v : graph.getVertices()) {
            parent.put(v, v);
        }

        StringBuilder steps = new StringBuilder();
        int totalWeight = 0;

        while (!pq.isEmpty() && result.size() < graph.getVertices().size() - 1) {
            Edge edge = pq.poll();
            Vertex x = find(parent, edge.getSource());
            Vertex y = find(parent, edge.getDestination());

            if (x != y) {
                result.add(edge);
                totalWeight += edge.getWeight();
                union(parent, x, y);
                steps.append("Added edge: ").append(edge.getSource().getId())
                     .append(" - ").append(edge.getDestination().getId())
                     .append(" (").append(edge.getWeight()).append(")\n");
            }
        }

        return new MSTResult(result, totalWeight, steps.toString());
    }

    private Vertex find(Map<Vertex, Vertex> parent, Vertex vertex) {
        if (parent.get(vertex) != vertex) {
            parent.put(vertex, find(parent, parent.get(vertex)));
        }
        return parent.get(vertex);
    }

    private void union(Map<Vertex, Vertex> parent, Vertex x, Vertex y) {
        Vertex xRoot = find(parent, x);
        Vertex yRoot = find(parent, y);
        parent.put(xRoot, yRoot);
    }
}
