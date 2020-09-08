
// interface for Graph classes - implemented by ListGraph and MatrixGraph
public interface Graph {
    public void addEdge(int src, int targ, double w);

    public void removeEdge(int src, int targ);

    public LList outEdges(int src);

    public LList inEdges(int dest);

    public int size();
}
