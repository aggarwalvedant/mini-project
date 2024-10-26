import javax.swing.*;
import java.awt.*;

public class MST_GUI extends JFrame {
    private Graph graph;
    private GraphPanel graphPanel;
    private JRadioButton kruskalRadio, primRadio;
    private JButton executeButton;
    private JTextArea resultArea;
    private JLabel totalWeightLabel;

    public MST_GUI() {
        setTitle("Minimum Spanning Tree Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeGraph();
        initializeComponents();
        layoutComponents();
        
        setVisible(true);
    }

    private void initializeGraph() {
        graph = new Graph();
        
        // Add vertices with positions
        String[] labels = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        int[][] positions = {{100, 200}, {200, 100}, {300, 100}, {400, 100}, {500, 200},
                             {400, 300}, {300, 300}, {200, 300}, {250, 200}};
        for (int i = 0; i < labels.length; i++) {
            graph.addVertex(labels[i], positions[i][0], positions[i][1]);
        }
        
        // Add edges
        graph.addEdge("a", "b", 4);
        graph.addEdge("a", "h", 8);
        graph.addEdge("b", "c", 8);
        graph.addEdge("b", "h", 11);
        graph.addEdge("c", "d", 7);
        graph.addEdge("c", "i", 2);
        graph.addEdge("c", "f", 4);
        graph.addEdge("d", "e", 9);
        graph.addEdge("d", "f", 14);
        graph.addEdge("e", "f", 10);
        graph.addEdge("f", "g", 2);
        graph.addEdge("g", "h", 1);
        graph.addEdge("g", "i", 6);
        graph.addEdge("h", "i", 7);
    }

    private void initializeComponents() {
        graphPanel = new GraphPanel(graph);
        
        kruskalRadio = new JRadioButton("Kruskal's Algorithm");
        primRadio = new JRadioButton("Prim's Algorithm");
        ButtonGroup algorithmGroup = new ButtonGroup();
        algorithmGroup.add(kruskalRadio);
        algorithmGroup.add(primRadio);
        kruskalRadio.setSelected(true);
        
        executeButton = new JButton("Execute");
        executeButton.addActionListener(e -> executeAlgorithm());
        
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        
        totalWeightLabel = new JLabel("Total MST Weight: ");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.add(kruskalRadio);
        topPanel.add(primRadio);
        topPanel.add(executeButton);
        topPanel.add(totalWeightLabel);
        
        add(topPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
    }

    private void executeAlgorithm() {
        if (kruskalRadio.isSelected()) {
            KruskalAlgorithm kruskal = new KruskalAlgorithm(graph);
            displayResults(kruskal.findMST());
        } else {
            PrimAlgorithm prim = new PrimAlgorithm(graph);
            displayResults(prim.findMST());
        }
    }

    private void displayResults(MSTResult result) {
        graphPanel.highlightMST(result.getEdges());
        totalWeightLabel.setText("Total MST Weight: " + result.getTotalWeight());
        resultArea.setText(result.getStepByStepInfo());
        graphPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MST_GUI());
    }
}
