import java.util.List;

public class MSTResult {
    private List<Edge> edges;
    private int totalWeight;
    private String stepByStepInfo;

    public MSTResult(List<Edge> edges, int totalWeight, String stepByStepInfo) {
        this.edges = edges;
        this.totalWeight = totalWeight;
        this.stepByStepInfo = stepByStepInfo;
    }

    public List<Edge> getEdges() { return edges; }
    public int getTotalWeight() { return totalWeight; }
    public String getStepByStepInfo() { return stepByStepInfo; }
}
