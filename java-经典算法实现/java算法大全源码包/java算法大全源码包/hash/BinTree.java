
import java.awt.*;
import java.util.*;

/**
 * The <code>BinTree</code> class provides the utilities to draw a complete
 * binary tree on the corresponding graphical context. It also contains
 * some animation methods used in <b>heap sort</b>, <b>priority queue</b>
 * and <b>sorting using priority queue</b>
 * <p>
 * There are basically 4 types of graphical objects: <b>input</b>,
 * <b>output</b>, <b>tree representation of heap</b>, and
 * <b>array representation of heap</b>.
 * The <code>Node</code> class is used to store the graphical objects.
 * @see Node
 */
public class BinTree extends Node {

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    DrawingPanel drawingPanel;
    Node tree, posn;
    Vector nodeList, posnList;
    int max_node_per_level, depth;
    Node movingNode;
    Point[] aPoints;
    Color darkgreen = new Color(0, 140, 0);
    Color darkRed = new Color(140, 0, 0);
    Color darkBlue = new Color(0, 0, 140);
    int max_size;

    int tmp1X, tmp1Y, tmp2X, tmp2Y, tmp3X, tmp3Y;
    String tmp1Str = new String(), tmp2Str = new String(), 
		tmp3Str = new String();
    static final int horizSpace = 32, vertSpace = 17;

    /**
     * Create a new heap and pre-calculate the location of each node
     * given the maximum number of node passed in as the parameter.
     * @param drawingPanel The drawing panel where the heap is to be drawn.
     * @param max_size The maximum number of nodes in the heap.
     */
    public BinTree(DrawingPanel drawingPanel, int max_size) {
	this.bigFont = drawingPanel.bigFont;
	this.smallFont = drawingPanel.smallFont;
	this.tinyFont = drawingPanel.tinyFont;
	this.hugeFont = drawingPanel.hugeFont;
	this.fixFont = drawingPanel.fixFont;
	this.drawingPanel = drawingPanel;
	this.max_size = max_size;
	this.tree = null;
	nodeList = new Vector();
	posnList = new Vector();
	calNodesCoord(max_size);
	hideMovingNode();
    }

    /**
     * Repaints the drawing panel and delay.
     */
    public void redraw() {
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    /**
     * Re-initialize the heap. This method is called when the animation
     * is re-started.
     */
    public void initBinTree() {
	this.tree = null;
	tmp1Str = new String();
	tmp2Str = new String();
	tmp3Str = new String();
	nodeList = new Vector();
    }

    private void hideMovingNode() {
	movingNode = new Node(-1);
    }

    /**
     * Re-assign the heap using the array of weights passed in as the
     * parameter.
     * @param a Array of weight for the newly re-assigned heap.
     */
    public void setBinTree(int[] a) {
	if (a.length > posnList.size())
	    calNodesCoord(a.length);
	tree = null;
	nodeList = new Vector();
	for (int i = 0; i < a.length; i++)
	    insert(new Node(a[i]));
    } // setData()

    private void insert(Node node) {
	nodeList.addElement(node);
	if (posnList.size() >= nodeList.size()) {
	    node.x = ((Node)posnList.elementAt(nodeList.size()-1)).x;
	    node.y = ((Node)posnList.elementAt(nodeList.size()-1)).y;
	}
	if (tree == null) {
	    tree = node;
	} else {
	    // locate blank branch to link to this node
	    for (int i = 0; i < nodeList.size()-1; i++) {
		Node n = (Node)nodeList.elementAt(i);
		if (n.getLeftNode() == null) {
		    n.setLeftNode(node);
		    break;
		} else if (n.getRightNode() == null) {
		    n.setRightNode(node);
		    break;
	   	}
	    }
	}
    }

    public void insertNodeAt(Node node, int posn) {
	node.x = ((Node)posnList.elementAt(posn-1)).x;
	node.y = ((Node)posnList.elementAt(posn-1)).y;

	if (parent(posn) > 0) {
	    Node posnNode = (Node)posnList.elementAt(parent(posn)-1);
	    Node parentNode = null;
	    for (int i = 0; i < nodeList.size(); i++) {
		parentNode = (Node)nodeList.elementAt(i);
		if (parentNode.x == posnNode.x && parentNode.y == posnNode.y)
		    break;
	    }
		
	    if (left(parent(posn)) == posn)
	    	parentNode.setLeftNode(node);
	    else
	    	parentNode.setRightNode(node);
	}
	nodeList.addElement(node);
    }

    /**
     * Re-assign the weight of node i according to the parameters.
     * @param i The node, which weight is going to be re-assigned.
     * @param val The new weight to be set.
     */
    public void setNode(int i, int val) {
	((Node)nodeList.elementAt(i)).setWeight(val);
    }

    private void calNodesCoord(int size) {
	// count max number of nodes needed
	int i =  0; int sum = power(2, i++);
	while (sum < size)
	    sum += power(2, i++);
	max_node_per_level = power(2, i-1);
	depth = i-1;

	int max_nodes = sum;
	// insert nodes with no value into position tree
	Node backTree = tree; // backup current tree
	Vector backNodeList = nodeList; nodeList = new Vector(); 
	posnList = new Vector();
	for (int j = 0; j < max_nodes; j++) {
	    Node node = new Node(-1);
	    insert(node);
	}
	posn = tree;
	posnList = nodeList;
	tree = backTree;
	nodeList = backNodeList;
	

	// assign Y
	int curDepth = 0; sum = power(2, 0);
	int offset = drawingPanel.offset + 340;
	//int yStart = drawingPanel.panel_height/2 -100 - depth*20;
	int yStart = drawingPanel.offset + 300;
	for (int j = 0; j < posnList.size(); j++) {
	    Node node = (Node)posnList.elementAt(j);

	    if ( (j+1) > sum) {
		curDepth++; sum += power(2, curDepth);
	    }
	    node.depth = curDepth;

	    node.y = yStart + curDepth*40;

	    // assign X for bottom level
	    if (curDepth == depth) {
		node.x = 22 + offset;
		offset = node.x;
	    }
	}

	// assign remaining X
	for (int j = posnList.size() - 1; j >= 0; j--) {
	    Node node = (Node)posnList.elementAt(j);

	    if (node.depth == depth)
		continue;

	    if (node.getLeftNode() != null && node.depth == depth-1) {
		node.x = node.getLeftNode().x + 12;
	    } else if (node.getLeftNode() != null && 
		node.getRightNode() != null) {
		node.x = (node.getLeftNode().x + node.getRightNode().x)/2;
	    } else {
		// both children null -> can only at depth - 1
		int l = left(j+1);
	    }
	}
    }

    /* ------------------------ Utils -------------------- */

    /**
     * Performs the exponent of <code>num</code> to the power of 
     * <code>pow</code>.
     * @param num Base.
     * @param pow Exponent.
     */
    public int power(int num, int pow) {
	int result = 1;
	for (int i = 0; i < pow; i++)
	    result *= num;
	return result;
    }

    /**
     * @return The position of the parent of node i.
     * @param i The node, whose parent is of interest.
     */
    public int parent(int i) {
	return (i/2);
    }

    /**
     * Returns the left child of node i.
     */
    public int left(int i) {
        return (2*i);
    }

    /**
     * Returns the right child of node i.
     */
    public int right(int i) {
        return (2*i + 1);
    }
 
    /* ------------------------ Graphical Primitives ------------------ */

    /**
     * Draws all graphical objects within this class.
     * @param g Graphical context.
     */
    public void drawTree(Graphics g) {
	if (tmp1Str.length() > 0)
	    drawBox(g, tmp1X, tmp1Y, tmp1Str, 
		Color.white, Color.black, fixFont);

	if (tmp2Str.length() > 0)
	    drawBox(g, tmp2X, tmp2Y, tmp2Str, 
		Color.white, Color.black, fixFont);

	if (tmp3Str.length() > 0)
	    drawBox(g, tmp3X, tmp3Y, tmp3Str,
		Color.white, Color.black, fixFont);

	for (int i = 0; i < nodeList.size(); i++) {
	    Node node = (Node)nodeList.elementAt(i);
	    drawNode(g, node);
	}

	if (movingNode.getWeight() > -1)
	    drawNode(g, movingNode);
    }

    public void drawBox(Graphics g, int x, int y, String str,
                                Color fg, Color bg, Font font) {
	String blank = new String();
	for (int i = 0; i < 4-str.length(); i++)
	    blank = blank.concat(" ");
        g.setColor(bg);
        g.fillRect(x, y, horizSpace, vertSpace);
        g.setColor(Color.black);
        g.drawRect(x, y, horizSpace, vertSpace);
        g.setColor(fg);
        g.setFont(font);
        g.drawString(blank + str, x + 2, y + vertSpace - 4);
    }

    /**
     * Draw an arrowed line segment using the array of points.
     * @param g Graphical context
     * @param points The vortices of the line segment. 
     * An arrow head will be drawn using the last point in the array at the tip.
     */
    public void drawArrow(Graphics g, Point[] points) {
	// remember: no drawPolyline in jdk1.0.*
	int[] axPoints = new int[3], ayPoints = new int[3];
	g.setColor( Color.magenta );
	if (points.length < 3) {
	  Point endArrow = points[1], startArrow = points[0];
	  if (endArrow.y == startArrow.y) {
	    g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    axPoints[0] = endArrow.x; ayPoints[0] = endArrow.y;
	    int unit = (endArrow.x - startArrow.x)/
			Math.abs(endArrow.x - startArrow.x);
	    axPoints[1] = endArrow.x - unit*2;
	    ayPoints[1] = endArrow.y - 2;
	    axPoints[2] = endArrow.x - unit*2;
	    ayPoints[2] = endArrow.y + 2;
	    g.fillPolygon(axPoints, ayPoints, 3);
	  } else if (endArrow.x == startArrow.x) {
	    g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    axPoints[0] = endArrow.x; ayPoints[0] = endArrow.y;
	    int unit = (endArrow.y - startArrow.y)/
			Math.abs(endArrow.y - startArrow.y);
	    axPoints[1] = endArrow.x - 1;
	    ayPoints[1] = endArrow.y - unit*2;
	    axPoints[2] = endArrow.x + 2;
	    ayPoints[2] = endArrow.y - unit*2;
	    g.fillPolygon(axPoints, ayPoints, 3);
	  }
	} else if (points.length == 3) {
	    // draw a line for the first and second points then use
	    // drawArrow to draw the remaining segment
	    g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
	    Point[] line = new Point[2];
	    line[0] = points[1];
	    line[1] = points[2];
	    drawArrow(g, line);
	}
    }

    /**
     * @param node The root node of the tree
     * @return The width of the tree in the number of pixels.
     */
    public int treeWidth(Node node) {
        return (rightMostPosn(node) - leftMostPosn(node));
    }
 
    /**
     * @param node The root node of the tree
     * @return The left most position of the tree
     */
    public int leftMostPosn(Node node) {
        if (node.isLeaf())
            return node.x;
        else
            return leftMostPosn(node.getLeftNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The right most position of the tree
     */
    public int rightMostPosn(Node node) {
        if (node.isLeaf())
            return (node.x + 20);
        else
            return rightMostPosn(node.getRightNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The height of the tree in the number of pixels.
     */
    public int treeHeight(Node node) {
        return (bottomMostPosn(node) - node.y);
    }
 
    /**
     * @param node The root node of the tree
     * @return The bottom most position of the tree
     */
    public int bottomMostPosn(Node node) {
	if (node == null) return 0;
        if (node.isLeaf())
            return (node.y + 30);
        else {
            int rightBottom = bottomMostPosn(node.getRightNode());
            int leftBottom = bottomMostPosn(node.getLeftNode());
            return (rightBottom > leftBottom ? rightBottom : leftBottom);
        }
    }

    /**
     * Drawing a leaf node of the heap.
     * @param g Graphical context
     * @param node The node to be drawn
     */
    public void drawLeafNode(Graphics g, Node node) {
        int x = node.x; int y = node.y;
        int weight = node.getWeight();
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.blue );
        g.fillRect(x, y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(x, y, 20, 30);
	if (node.getLabel().length() > 0) {
	    g.setColor( Color.yellow );
	    g.setFont( hugeFont );
	    g.drawString(node.getLabel(), node.x + 5, node.y + 12);
	}
        g.setColor( Color.white );
	g.setFont( bigFont );
        g.drawString(""+node.getWeight(), node.x + 2, node.y+27);
    }

    /**
     * Draw a node of the array representation of the heap.
     * @param g Graphical context
     * @param x The x coordinate of the top left corner of the node
     * @param y The y coordinate of the top left corner of the node
     * @param node The node to be drawn
     */
    public void drawArrayNode(Graphics g, int x, int y, Node node) {
        int weight = node.getWeight();
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.red );
        g.fillRect(x, y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(x, y, 20, 30);
        g.setFont(hugeFont);
        g.setColor( Color.yellow );
        g.drawString(""+weight, x+2, y+25);
    }

    /**
     * Drawing a node based on the input parameters.
     * @param g Graphical context
     * @param node The node to be drawn
     */
    public void drawNode(Graphics g, Node node) {
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( darkgreen );
        g.fillRect(node.x, node.y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(node.x, node.y, 20, 30);
	if (node.getLabel().length() > 0) {
	    g.setColor( Color.yellow );
	    g.setFont( hugeFont );
	    g.drawString(node.getLabel(), node.x + 5, node.y + 12);
	}
        g.setColor( Color.white );
	g.setFont( bigFont );
        g.drawString(""+node.getWeight(), node.x + 2, node.y+27);
 
        // draw links to children
	if (node.getLeftNode() != null) {
            g.setColor( Color.black );
            g.drawLine(node.x + 6, node.y + 30,
                node.getLeftNode().x + 10, node.getLeftNode().y);
	    if (node.highlightLeft) {
                g.drawLine(node.x + 5, node.y + 30,
                    node.getLeftNode().x + 9, node.getLeftNode().y);
                g.drawLine(node.x + 4, node.y + 30,
                    node.getLeftNode().x + 8, node.getLeftNode().y);
	    }
	}

	if (node.getRightNode() != null) {
	    g.setColor( Color.black );
            g.drawLine(node.x + 14, node.y + 30,
                node.getRightNode().x + 10, node.getRightNode().y);
	    if (node.highlightRight) {
                g.drawLine(node.x + 15, node.y + 30,
                    node.getRightNode().x + 11, node.getRightNode().y);
                g.drawLine(node.x + 16, node.y + 30,
                    node.getRightNode().x + 12, node.getRightNode().y);
	    }
	}
    }

    /**
     * Move the tree starting with node dx pixels to the right and dy
     * pixels down.
     * @param node The root node of the tree to be moved.
     * @param dx The change in x direction.
     * @param dy The change in y direction.
     */
    public void moveNode(Node node, int dx, int dy) {
	if (node == null) return;
        node.x += dx;
        node.y += dy;
        if (!node.isLeaf()) {
            moveNode(node.getLeftNode(), dx, dy);
            moveNode(node.getRightNode(), dx, dy);
        }
    }

    public void highlightNode(Node node) {
	node.highlight = true;
	if (node.getLeftNode() != null)
	    highlightNode(node.getLeftNode());
	if (node.getRightNode() != null)
	    highlightNode(node.getRightNode());
    }

    public void restoreNode(Node node) {
	node.highlight = false;
	if (node.getLeftNode() != null)
	    restoreNode(node.getLeftNode());
	if (node.getRightNode() != null)
	    restoreNode(node.getRightNode());
    }

    public Node getNodeAt(int posn) {
	Node posnNode = (Node)posnList.elementAt(posn-1);
	for (int i = 0; i < nodeList.size(); i++) {
	    Node node = (Node)nodeList.elementAt(i);
	    if (node.x == posnNode.x && node.y == posnNode.y) {
		return node;
	    }
	}
	return null;
    }

    public int sumNodeWeight(Node node) {
	if (node == null) return 0;
	return (sumNodeWeight(node.getRightNode()) + 
		sumNodeWeight(node.getLeftNode()) + 
		node.getWeight());
    }
}
