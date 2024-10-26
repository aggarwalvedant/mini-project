import java.util.*;

public class PrimAlgorithm {
    private Graph graph;

    public PrimAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public MSTResult findMST() {
        List<Edge> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        StringBuilder steps = new StringBuilder();
        int totalWeight = 0;

        // Start with vertex 'a'
        Vertex start = graph.getVertex("a");
        visited.add(start);
        pq.addAll(start.getEdges());

        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            Edge edge = pq.poll();
            Vertex v = edge.getDestination();

            if (!visited.contains(v)) {
                visited.add(v);
                result.add(edge);
                totalWeight += edge.getWeight();
                pq.addAll(v.getEdges());

                steps.append("Added edge: ").append(edge.getSource().getId())
                     .append(" - ").append(edge.getDestination().getId())
                     .append(" (").append(edge.getWeight()).append(")\n");
            }
        }

        return new MSTResult(result, totalWeight, steps.toString());
    }
}
