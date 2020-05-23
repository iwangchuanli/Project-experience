
import java.awt.*;

/**
 * This class holds the information regarding a node from the heap/complete
 * binary tree. This class implements the <code>DrawingObj</code> interface
 * and hence can be freely added to the drawing panel using the
 * <code>addDrawingObj</code> method. e.g. <code><pre>
 *	Node node = new Node();
 *	drawingPanel.addDrawingObj(node);
 * </pre></code>
 * Any added drawing object can be remove from the panel by using the
 * <code>removeObj</code> method. e.g. <code><pre>
 *	drawingPanel.removeObj(node);
 * </pre></code>
 * @see DrawingPanel#addDrawingObj
 * @see DrawingPanel#removeObj
 * @see Heap
 */
public class Node implements DrawingObj {
    private String label;
    private int weight;
    private Node leftNode, rightNode;
    private int x, y;
    private int depth = -1;
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

    /**
     * Sets the x coordinate of the top-left corner of the node
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     * Sets the y coordinate of the top-left corner of the node
     */
    public void setY(int y) {
	this.y = y;
    }

    /**
     * Get the left most position of the node.
     * @return The x coordinates of the top-left corner of the node
     */
    public int getX() {
	return x;
    }

    /**
     * Get the top most position of the node.
     * @return The y coordinates of the top-left corner of the node
     */
    public int getY() {
	return y;
    }

    /**
     * Get the depth of his node.
     * @return The depth of this node in a tree.
     */
    public int getDepth() {
	return depth;
    }

    /**
     * Sets the depth of this node corresponding to the root node of the tree.
     * @param depth Depth of the node.
     */
    public void setDepth(int depth) {
	this.depth = depth;
    }

    /**
     * Move the node and all its branches based on the parameters.
     * @param x The horizontal destination position of this node.
     * @param y The vertical destination position of this node.
     */
    public void move(int x, int y) {
	int dx = x - this.x;
	int dy = y - this.y;
	moveNode(dx, dy);
    }

    /**
     * Move the tree starting with node dx pixels to the right and dy
     * pixels down.
     * @param node The root node of the tree to be moved.
     * @param dx The change in x direction.
     * @param dy The change in y direction.
     */
    public void moveNode(int dx, int dy) {
        x += dx;
        y += dy;
        if (!isLeaf()) {
	    if (getLeftNode() != null)
		getLeftNode().moveNode(dx, dy);
	    if (getRightNode() != null)
		getRightNode().moveNode(dx, dy);
        }
    }

    Font hugeFont, bigFont;
    /**
     * Assign some font instances to reduce initialization over during
     * redraw.
     */
    public void initFonts(Font hugeFont, Font bigFont) {
	this.hugeFont = hugeFont;
	this.bigFont = bigFont;
    }

    Color nodeColor = Color.red;
    /**
     * Set the color of the node.
     * @param nodeColor new color of the node.
     */
    public void initColors(Color nodeColor) {
	this.nodeColor = nodeColor;
    }

    /**
     * This method draws the node on the corresponding graphical context
     * normally passed in from the drawing panel.
     */
    public void draw(Graphics g) {
	if (highlight)
	    g.setColor(Color.black);
        else
            g.setColor( nodeColor );
        g.fillRect(x, y, 20, 30);
        g.setColor(Color.black);
        g.drawRect(x, y, 20, 30);
        if (getLabel().length() > 0) {
            g.setColor( Color.yellow );
            g.setFont( hugeFont );
            g.drawString(getLabel(), x + 5, y + 12);
        }
        g.setColor( Color.white );
        g.setFont( bigFont );
        g.drawString(""+getWeight(), x + 2, y+27);
 
        // draw links to children
        if (getLeftNode() != null) {
            g.setColor( Color.black );
            g.drawLine(x + 6, y + 30,
                getLeftNode().x + 10, getLeftNode().y);
            if (highlightLeft) {
            	g.setColor( Color.blue );
                g.drawLine(x + 5, y + 30,
                    getLeftNode().x + 9, getLeftNode().y);
                g.drawLine(x + 4, y + 30,
                    getLeftNode().x + 8, getLeftNode().y);
            }
        }
 
        if (getRightNode() != null) {
            g.setColor( Color.black );
            g.drawLine(x + 14, y + 30,
                getRightNode().x + 10, getRightNode().y);
            if (highlightRight) {
            	g.setColor( Color.blue );
                g.drawLine(x + 15, y + 30,
                    getRightNode().x + 11, getRightNode().y);
                g.drawLine(x + 16, y + 30,
                    getRightNode().x + 12, getRightNode().y);
            }
        }
    }
} // class Node
