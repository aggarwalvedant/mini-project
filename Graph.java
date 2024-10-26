import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(String id, int x, int y) {
        vertices.put(id, new Vertex(id, x, y));
    }

    public void addEdge(String sourceId, String destId, int weight) {
        Vertex source = vertices.get(sourceId);
        Vertex dest = vertices.get(destId);
        Edge edge = new Edge(source, dest, weight);
        edges.add(edge);
        source.addEdge(edge);
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Vertex getVertex(String id) {
        return vertices.get(id);
    }
}
