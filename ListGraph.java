//implements graph data structure using adjacency lists
public class ListGraph implements Graph {
    private int size; // the number of vertices in the Graph
    private LList[] adj; // the adjacency list for the graph

    /**
     * Constructs an instance of listGraph given a size
     * 
     * @param numVertices The number of vertices in the constructed graph
     */
    public ListGraph(int numVertices) {
        adj = new LList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adj[i] = new LList();
        }
        size = numVertices;
    }

    /**
     * Adds an edge from src to targ of weight w
     * 
     * @param src  The source of the edge
     * @param targ The target of the edge
     * @param w    The weight of the edge
     */
    public void addEdge(int src, int targ, double w) {
        adj[src].add(targ, w);
    }

    /**
     * Removes the edge from src to targ
     * 
     * @param src  The source of the edge
     * @param targ The target of the edge
     */
    public void removeEdge(int src, int targ) {
        adj[src].remove(targ);
    }

    /**
     * Finds edges from a node
     * 
     * @param src The source Node of the edges
     * @return The List of all edges leaving the node
     */
    public LList outEdges(int src) {
        return adj[src];
    }

    /**
     * Finds edges that arrive at targ
     * 
     * @param targ The target of the edges
     * @return The list of edges going to targ
     */
    public LList inEdges(int i) {
        LList output = new LList();
        for (int j = 0; j < size; j++) {
            if (adj[j].find(i))
                output.add(j, adj[j].getWeight(i)); // finds the weight going from node j to node i
        }
        return output;
    }

    /**
     * @return The number of vertices of the graph (the size)
     */
    public int size() {
        return size;
    }

}