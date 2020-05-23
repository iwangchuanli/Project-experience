
/**
 * This class holds the information regarding a node from the heap/complete
 * binary tree.
 * @see Heap
 */
public class Node {
    String label;
    int weight;
    Node leftNode, rightNode;
    /**
     * The coordinates of the top-left corner of the node
     */
    public int x, y;
    int depth = -1;
    /**
     * Attribute to indicate if the left branch is to be highlighted.
     */
    public boolean highlightLeft = false;
    /**
     * Attribute to indicate if the right branch is to be highlighted.
     */
    public boolean highlightRight = false;
    /**
     * Attribute to indicate if the node is to be highlighted.
     */
    public boolean highlight = false;
    
    /**
     * Create a node with the left and right child nodes specified and
     * the weight of the current node is the sum of the child node.
     * @param node1 The left node of this newly created node.
     * @param node2 The right node of this newly created node.
     */
    public Node(Node node1, Node node2) {
	leftNode = node1;
	rightNode = node2;
	weight = node1.getWeight() + node2.getWeight();
	label = new String();
    }

    /**
     * Create a node with the left node as set and weight of the current node
     * set to 0.
     * @param node The left node of the newly created node.
     */
    public Node(Node node) {
	leftNode = node;
	rightNode = null;
	weight = 0;
	label = new String();
    }

    /**
     * Create a new left node with weight 0.
     */
    public Node() {
	label = new String();
	weight = 0;
	leftNode = rightNode = null;
    } // Constructor 1
    
    /**
     * Create a new leaf node with label and weight as specified in the
     * parameters.
     * @param label The label of the new node.
     * @param weight The weight of the new node.
     */
    public Node(String label, int weight) {
	this.label = new String(label);
	this.weight = weight;
	leftNode = rightNode = null;
    } // Constructor 2
    
    /**
     * Create a new leaf node with the specified weight.
     * @param weight The weight of the new node.
     */
    public Node(int weight) {
	this.label = new String();
	this.weight = weight;
	leftNode = rightNode = null;
    } // Constructor 3
    
    /**
     * Create a new leaf node with 0 weight and label as specified.
     * @param label Label of the new node.
     */
    public Node(String label) {
	this.label = new String(label);
	this.weight = 0;
	leftNode = rightNode = null;
    } // Constructor 4
    
    /**
     * Set the weight of this node.
     * @param weight The weight to be assigned to this node.
     */
    public void setWeight(int weight) {
	this.weight = weight;
    }
    
    /**
     * Set the label of this node.
     * @param label The label to be assigned to this node.
     */
    public void setLabel(String label) {
	this.label = new String(label);
    }
    
    /**
     * Get the weight of this node.
     * @return Weight of this node.
     */
    public int getWeight() {
	return weight;
    }
    
    /**
     * Get the label of this node.
     * @return Label of this node.
     */
    public String getLabel() {
	return new String(label);
    }

    /**
     * Link the left branch of this node to the node passed in as the parameter.
     * @param node The new left child of this node.
     */
    public void setLeftNode(Node node) {
	leftNode = node;
    }

    /**
     * Link the right branch of this node to 
     * the node passed in as the parameter.
     * @param node The new right child of this node.
     *
     */
    public void setRightNode(Node node) {
	rightNode = node;
    }

    /**
     * Get the left child of this node.
     * @return the left child this this node.
     */
    public Node getLeftNode() {
	return leftNode;
    }

    /**
     * Get the right child of this node.
     * @return the right child this this node.
     *
     */
    public Node getRightNode() {
	return rightNode;
    }

    /**
     * Check if this node is a leaf node. A leaf node has both left and right
     * nodes null.
     * @return true if the node is a leaf node; false otherwise.
     */
    public boolean isLeaf() {
	return ((rightNode==null)&&(leftNode==null));
    }
} // class Node
