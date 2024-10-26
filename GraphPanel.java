import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class GraphPanel extends JPanel {
    private Graph graph;
    private Set<Edge> highlightedEdges;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        this.highlightedEdges = new HashSet<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw edges
        for (Edge edge : graph.getEdges()) {
            drawEdge(g2d, edge, highlightedEdges.contains(edge));
        }

        // Draw vertices
        for (Vertex vertex : graph.getVertices()) {
            drawVertex(g2d, vertex);
        }
    }

    private void drawVertex(Graphics2D g2d, Vertex vertex) {
        int diameter = 30;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(vertex.getX() - diameter/2, vertex.getY() - diameter/2, diameter, diameter);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(vertex.getX() - diameter/2, vertex.getY() - diameter/2, diameter, diameter);
        g2d.drawString(vertex.getId(), vertex.getX() - 5, vertex.getY() + 5);
    }

    private void drawEdge(Graphics2D g2d, Edge edge, boolean highlighted) {
        Vertex source = edge.getSource();
        Vertex dest = edge.getDestination();
        
        g2d.setColor(highlighted ? Color.GREEN : Color.BLACK);
        g2d.drawLine(source.getX(), source.getY(), dest.getX(), dest.getY());
        
        // Draw weight
        int midX = (source.getX() + dest.getX()) / 2;
        int midY = (source.getY() + dest.getY()) / 2;
        g2d.drawString(String.valueOf(edge.getWeight()), midX, midY);
    }

    public void highlightMST(List<Edge> mstEdges) {
        highlightedEdges.clear();
        highlightedEdges.addAll(mstEdges);
        repaint();
    }
}
