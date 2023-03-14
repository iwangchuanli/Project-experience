
class Node {
    String label;
    int weight;
    Node leftNode, rightNode;
    int x, y;
    boolean highlightLeft = false, highlightRight = false, highlight = false;
    
    public Node(Node node1, Node node2) {
	leftNode = node1;
	rightNode = node2;
	weight = node1.getWeight() + node2.getWeight();
	label = new String();
    }

    public Node() {
	label = new String();
	weight = 0;
	leftNode = rightNode = null;
    } // Constructor 1
    
    public Node(String label, int weight) {
	this.label = new String(label);
	this.weight = weight;
	leftNode = rightNode = null;
    } // Constructor 2
    
    public Node(int weight) {
	this.label = new String();
	this.weight = weight;
	leftNode = rightNode = null;
    } // Constructor 3
    
    public Node(String label) {
	this.label = new String(label);
	this.weight = 0;
	leftNode = rightNode = null;
    } // Constructor 4
    
    public void setWeight(int weight) {
	this.weight = weight;
    }
    
    public void setLabel(String label) {
	this.label = new String(label);
    }
    
    public int getWeight() {
	return weight;
    }
    
    public String getLabel() {
	return new String(label);
    }

    public void setLeftNode(Node node) {
	leftNode = node;
    }

    public void setRightNode(Node node) {
	rightNode = node;
    }

    public Node getLeftNode() {
	return leftNode;
    }

    public Node getRightNode() {
	return rightNode;
    }

    public boolean isLeaf() {
	return ((rightNode==null)&&(leftNode==null));
    }
} // class Node
