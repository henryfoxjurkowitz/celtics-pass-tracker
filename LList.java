// doubly linkedList class that stores Nodes with a pointer to another Node, and a weight for that path
public class LList {
    private Node first; // pointer to first node
    private Node last; // pointer to last node

    /**
     * Constructs an empty LList
     */
    public LList() {
        first = null;
        last = null;
    }

    /**
     * Adds an edge to this node's LList
     * 
     * @param target The destination of this edge
     * @param weight The weight of this added edge
     */
    public void add(int target, double weight) {
        Node n = new Node(target, weight);
        if (first == null) {
            first = n;
        } else {
            last.next = n;
            n.previous = last;
        }
        last = n;
    }

    /**
     * Determines whether there is an edge from this node to target
     * 
     * @param target The node to which we want check if there is an edge
     * @return True if there is an edge, False if there is not
     */
    public boolean find(int target) {
        Node n = first;
        while (n != null) {
            if (n.pointer == target)
                return true;
            n = n.next;
        }
        return false;
    }

    /**
     * Removes the edge to target Node, if there is one
     * 
     * @param target The Node whose edge we want to remove
     */
    public void remove(int target) {
        Node n = first;
        while (n != null) {
            if (n.pointer == target) {
                if (first == n)
                    first = n.next;
                else
                    n.previous.next = n.next;
                if (last == n)
                    last = n.previous;
                else
                    n.next.previous = n.previous;
                break;
            } else
                n = n.next;
        }
    }

    /**
     * Gets the weight of the path from this node to target
     * 
     * @param target The target Node
     * @return The weight of the edge, or 0 if there is no edge
     */
    public double getWeight(int target) {
        Node n = first;
        while (n != null) {
            if (n.pointer == target)
                return n.weight;
            n = n.next;
        }
        return 0;
    }
}

// Node class where each node has a pointer to another node, and the weight of
// the path to that Node
class Node {
    int pointer;
    double weight;
    Node next, previous;

    /**
     * Constructs a Node object
     * 
     * @param p The int pointer representing another node
     * @param w The weight of the path to that node
     */
    public Node(int p, double w) {
        pointer = p;
        weight = w;
        next = null;
        previous = null;
    }
}
