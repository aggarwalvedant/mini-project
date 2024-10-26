import java.util.*;

public class Vertex {
    private String id;
    private int x, y;
    private List<Edge> edges;

    public Vertex(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }
    public List<Edge> getEdges() { return edges; }
}
