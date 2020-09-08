// implements graph data structure using 2D adjacency matrix
public class MatrixGraph implements Graph {
    private double[][] adj; // the adjacency matrix for the graph

    /**
     * Constructs an instance of a MatrixGraph given a size
     * 
     * @param numVertices The number of vertices in the constructed graph
     */
    public MatrixGraph(int numVertices) {
        adj = new double[numVertices][numVertices];
    }

    /**
     * Adds an edge from src to targ of weight w
     * 
     * @param src  The source of the edge
     * @param targ The target of the edge
     * @param w    The weight of the edge
     */
    public void addEdge(int src, int targ, double w) {
        adj[src][targ] = w;
    }

    /**
     * Removes the edge from src to targ
     * 
     * @param src  The source of the edge
     * @param targ The target of the edge
     */
    public void removeEdge(int src, int targ) {
        adj[src][targ] = 0;
    }

    /**
     * Finds edges from a node
     * 
     * @param src The source Node of the edges
     * @return The List of all edges leaving the node
     */
    public LList outEdges(int src) {
        LList output = new LList();
        for (int dest = 0; dest < adj.length; dest++) {
            if (adj[src][dest] != 0)
                output.add(dest, adj[src][dest]);
        }
        return output;
    }

    /**
     * Finds edges that arrive at targ
     * 
     * @param targ The target of the edges
     * @return The list of edges going to targ
     */
    public LList inEdges(int targ) {
        LList output = new LList();
        for (int src = 0; src < adj.length; src++) {
            if (adj[src][targ] != 0)
                output.add(src, adj[src][targ]);
        }
        return output;
    }

    /**
     * @return The number of vertices of the graph (the size)
     */
    public int size() {
        return adj.length;
    }

}