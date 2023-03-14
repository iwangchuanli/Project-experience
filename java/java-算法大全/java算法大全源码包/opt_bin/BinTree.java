
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
public class BinTree implements DrawingObj {

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    DrawingPanel drawingPanel;
    OBSAnim obsAnim;
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
    public BinTree(OBSAnim obsAnim, DrawingPanel drawingPanel, int max_size) {
	this.obsAnim = obsAnim;
	this.bigFont = drawingPanel.getBigFont();
	this.smallFont = drawingPanel.getSmallFont();
	this.tinyFont = drawingPanel.getTinyFont();
	this.hugeFont = drawingPanel.getHugeFont();
	this.fixFont = drawingPanel.getFixFont();
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
	    node.setX( ((Node)posnList.elementAt(nodeList.size()-1)).getX() );
	    node.setY( ((Node)posnList.elementAt(nodeList.size()-1)).getY() );
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

    public void animateInsertNode(int srcX, int srcY, int posn) {
	Node posnNode = (Node)posnList.elementAt(posn-1);

	for (int i = 0; i < 10; i++) {
	    obsAnim.moveCom(srcX + (i+1)*(posnNode.getX() - 100 - srcX)/10,
		srcY + (i+1)*(posnNode.getY() - 50 - srcY)/10);
	}
    }

    public void insertNodeAt(Node node, int posn) {
	node.setX( ((Node)posnList.elementAt(posn-1)).getX() );
	node.setY( ((Node)posnList.elementAt(posn-1)).getY() );

	if (parent(posn) > 0) {
	    Node posnNode = (Node)posnList.elementAt(parent(posn)-1);
	    Node parentNode = null;
	    for (int i = 0; i < nodeList.size(); i++) {
		parentNode = (Node)nodeList.elementAt(i);
		if (parentNode.getX() == posnNode.getX() && parentNode.getY() == posnNode.getY())
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
	int offset = drawingPanel.getOffset() + 340;
	//int yStart = drawingPanel.getPanelHeight/2 -100 - depth*20;
	int yStart = drawingPanel.getOffset() + 300;
	for (int j = 0; j < posnList.size(); j++) {
	    Node node = (Node)posnList.elementAt(j);

	    if ( (j+1) > sum) {
		curDepth++; sum += power(2, curDepth);
	    }
	    node.setDepth(curDepth);

	    node.setY( yStart + curDepth*40 );

	    // assign X for bottom level
	    if (curDepth == depth) {
		node.setX( 22 + offset ) ;
		offset = node.getX();
	    }
	}

	// assign remaining X
	for (int j = posnList.size() - 1; j >= 0; j--) {
	    Node node = (Node)posnList.elementAt(j);

	    if (node.getDepth() == depth)
		continue;

	    if (node.getLeftNode() != null && node.getDepth() == depth-1) {
		node.setX( node.getLeftNode().getX() + 12 );
	    } else if (node.getLeftNode() != null && 
		node.getRightNode() != null) {
		node.setX( (node.getLeftNode().getX() + node.getRightNode().getX())/2 );
	    } else {
		// both children null -> can only at depth - 1
		int l = left(j+1);
	    }
	}
    }

    /* ------------------ Animation for Opt Bin Search Tree ---------------- */
    public void highlightLeftRightSubtree(OBSearch obs,
		int left, int c_root, int right) {
	if (drawingPanel.getNoAnim())
	    return;
	// highlight left subtree
	Node rootNode = getNodeAt(1);
	Node leftSubtree = getNodeAt(2);
	if (leftSubtree != null && !leftSubtree.isLeaf()) {
	    highlightNode(leftSubtree);
	    redraw();
	    obsAnim.setCom("Left subtree is optimal...",
		leftSubtree.getX() - 150, leftSubtree.getY() + 90);
	    // break off the link to parent first;
	    rootNode.setLeftNode(null);
	    int destX = leftSubtree.getX() - 3; int destY = leftSubtree.getY();
	    moveNode(leftSubtree, 0, 10); redraw();
	    moveNode(leftSubtree, 0, 10); redraw();
	    moveNode(leftSubtree, 0, 10); redraw();
	
	    if (obs.CostSubTree(left, c_root - 1) > 0) {
		obs.cost.setHighlight2(left, c_root-1);
		redraw();

		obsAnim.setCom("The optimal cost for this subtree is: " +
			obs.CostSubTree(left, c_root-1) + 
			", obtained from the cost matrix...");
		tmp1Str = new String(""+obs.CostSubTree(left, c_root-1));
		int srcX = tmp1X = 
			drawingPanel.getOffset() + (left+1)*(horizSpace+2);
		int srcY = tmp1Y = 
			drawingPanel.getOffset() + 10 + (c_root)*(vertSpace+2);
		redraw();
		for (int i = 0; i < 10; i++) {
		    tmp1X += (destX - srcX)/10;
		    tmp1Y += (destY - srcY)/10;
		    redraw();
		}
		tmp1X = destX;
		tmp2X = destX;

		obs.frame.waitStep();
		obs.cost.restoreHighlight2(left, c_root-1);
	    } else
		obs.frame.waitStep();

	    obsAnim.setCom("This cost + sum of all node frequencies = " +
		"subtree cost when attached to a parent = " + 
		obs.CostSubTree(left, c_root-1) + " + " + 
		sumNodeWeight(leftSubtree), 
		drawingPanel.getOffset(), leftSubtree.getY() - 120);
	    tmp1Str = new String("+" +
		sumNodeWeight(leftSubtree)); redraw();
	    tmp1X -= 8; redraw();
	    tmp1Str = new String("=");
	    tmp1X -= 8; redraw();
	    tmp1Str = new String(""+(obs.CostSubTree(left, c_root-1)+
		sumNodeWeight(leftSubtree))); redraw();
	    tmp1X -= 8; redraw();
	    tmp1X -= 8; tmp1Y = destY; redraw();
	    moveNode(leftSubtree, 0, -10); redraw();
	    moveNode(leftSubtree, 0, -10); redraw();
	    moveNode(leftSubtree, 0, -10); redraw();
	    rootNode.setLeftNode(leftSubtree);redraw();
	    obs.frame.waitStep();
	    obsAnim.hideCom();
	    
	    restoreNode(leftSubtree);
	    redraw();
	}

	// highlight right subtree
	Node rightSubtree = getNodeAt(3);
	if (rightSubtree != null && !rightSubtree.isLeaf()) {
	    highlightNode(rightSubtree);
	    redraw();
	    obsAnim.setCom("Right subtree is optimal...",
		rightSubtree.getX() - 20, rightSubtree.getY() + 90);

	    // break off the link to parent
	    rootNode.setRightNode(null);
	    int destX = rightSubtree.getX() - 3; int destY = rightSubtree.getY();
	    moveNode(rightSubtree, 0, 10); redraw();
	    moveNode(rightSubtree, 0, 10); redraw();
	    moveNode(rightSubtree, 0, 10); redraw();

	    if (obs.CostSubTree(c_root+1, right) > 0) {
	 	obs.cost.setHighlight2(c_root+1, right);
		redraw();

		obsAnim.setCom("The optimal cost for this subtree is: " +
			obs.CostSubTree(c_root+1, right) + "...",
			rightSubtree.getX() - 120, rightSubtree.getY() + 90);
		tmp2Str = new String(""+obs.CostSubTree(c_root+1, right));
		int srcX = tmp2X = 
			drawingPanel.getOffset() + (c_root+2)*(horizSpace+2);
		int srcY = tmp2Y =
			drawingPanel.getOffset() + 10 + (right+1)*(vertSpace+2);
		redraw();
		for (int i = 0; i < 10; i++) {
		    tmp2X += (destX - srcX)/10;
		    tmp2Y += (destY - srcY)/10;
		    redraw();
		}
		tmp2X = destX;
		tmp2Y = destY;

		obs.frame.waitStep();
		obs.cost.restoreHighlight2(c_root+1, right);
	    } else
		obs.frame.waitStep();

	    obsAnim.setCom("Subtree cost at depth + 1 " +
		"(attached to a parent node) = " + 
		obs.CostSubTree(c_root+1, right) + " + " + 
		sumNodeWeight(rightSubtree), 
		drawingPanel.getOffset() + 150, rightSubtree.getY() - 120);
	    tmp2Str = new String("+" +
		sumNodeWeight(rightSubtree)); redraw();
	    tmp2X += 8; redraw();
	    tmp2Str = new String("=");
	    tmp2X += 8; redraw();
	    tmp2Str = new String(""+(obs.CostSubTree(c_root+1, right) +
		sumNodeWeight(rightSubtree))); redraw();
	    tmp2X += 8; redraw();
	    tmp2X += 8; tmp2Y = destY; redraw();
	    moveNode(rightSubtree, 0, -10); redraw();
	    moveNode(rightSubtree, 0, -10); redraw();
	    moveNode(rightSubtree, 0, -10); redraw();
	    // reattach root
	    rootNode.setRightNode(rightSubtree); redraw();
	    obs.frame.waitStep();
	    obsAnim.hideCom();

	    restoreNode(rightSubtree);
	    redraw();
	}

	// get 3rd cost from root
	tmp3Str = new String("" + rootNode.getWeight());
	tmp3X = rootNode.getX() - 15;
	tmp3Y = rootNode.getY() + 5;
	redraw();
	tmp3X -= 10; redraw();
	tmp3X -= 10; redraw();

	if (leftSubtree != null && tmp1Str.length() == 0) {
	    obsAnim.setCom("Left node cost at depth 1...",
		leftSubtree.getX() - 160, leftSubtree.getY() - 40);
	    tmp1Str = new String("" + leftSubtree.getWeight());
	    tmp1X = leftSubtree.getX() - 15;
	    tmp1Y = leftSubtree.getY() + 5;
	    redraw();
	    tmp1Str = tmp1Str.concat("x2");
	    tmp1X -= 10; redraw();
	    tmp1Str = new String("" + 2*leftSubtree.getWeight());
	    tmp1X -= 10; redraw();
	    obsAnim.hideCom();
	}
	
	if (rightSubtree != null && tmp2Str.length() == 0) {
	    obsAnim.setCom("Right node cost at depth 1...",
		rightSubtree.getX() - 20, rightSubtree.getY() - 40);
	    tmp2Str = new String("" + rightSubtree.getWeight());
	    tmp2X = rightSubtree.getX();
	    tmp2Y = rightSubtree.getY() + 5;
	    redraw();
	    tmp2Str = tmp2Str.concat("x2");
	    tmp2X += 10; redraw();
	    tmp2Str = new String("" + 2*rightSubtree.getWeight());
	    tmp2X += 10; redraw();
	    tmp2X += 10; redraw();
	    obsAnim.hideCom();
	}
	
	// adding cost
	int total = Integer.parseInt(tmp3Str);
	if (tmp1Str.length() > 0)
	    total += Integer.parseInt(tmp1Str);
	if (tmp2Str.length() > 0)
	    total += Integer.parseInt(tmp2Str);
	
	// merge left and right cost into root cost
	obsAnim.setCom("Calculating cost for this subtree...",
		rootNode.getX() - 300, rootNode.getY() - 40);
	tmp1Y += (tmp3Y - tmp1Y) /2;
	tmp2Y += (tmp3Y - tmp2Y) /2;
	redraw();
	tmp1Y = tmp2Y = tmp3Y;
	redraw();
	
	int src1 = tmp1X; int src2 = tmp2X;
	for (int i = 0; i < 5; i++) {
	    tmp1X += (tmp3X - src1)/5;
	    tmp2X += (tmp3X - src2)/5;
	    redraw();
	}
	tmp1Str = new String(); tmp2Str = new String();
	tmp3Str = new String(""+total);
	redraw();
    } // highlightLeftRightSubtree()

    public void tree2Matrices(int left, int right) {
	if (drawingPanel.getNoAnim())
	    return;
	int dest1X = drawingPanel.getOffset() + (left+1)*(horizSpace+2);
	int dest1Y = drawingPanel.getOffset() + 10 + (right+1)*(vertSpace+2);
	int dest2X = drawingPanel.getOffset() + 380 + (left+1)*(horizSpace+2);
	int dest2Y = drawingPanel.getOffset() + 10 + (right+1)*(vertSpace+2);

	tmp3X -= 10; redraw();
	tmp3X -= 10; redraw();
	tmp3X -= 10; redraw();
	tmp3X -= 10; redraw();

	tmp1Str = new String(((Node)nodeList.firstElement()).getLabel());
	tmp1X = tmp3X; tmp1Y = tmp3Y;
	tmp1X += 10; redraw();
	tmp1X += 10; redraw();
	tmp1X += 10; redraw();
	tmp1X += 10; redraw();

	int src1X = tmp3X; int src1Y = tmp3Y;
	int src2X = tmp1X; int src2Y = tmp1Y;
	for (int i = 0; i < 10; i++) {
	    tmp3X += (dest1X - src1X)/10;
	    tmp3Y += (dest1Y - src1Y)/10;
	    tmp1X += (dest2X - src2X)/10;
	    tmp1Y += (dest2Y - src2Y)/10;
	    redraw();
	}
	tmp3X = dest1X; tmp3Y = dest1Y;
	tmp1X = dest2X; tmp1Y = dest2Y; redraw();
	tmp3Str = new String();
	tmp1Str = new String();
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
    public void draw(Graphics g) {
	drawTree(g);
    }

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
            return node.getX();
        else
            return leftMostPosn(node.getLeftNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The right most position of the tree
     */
    public int rightMostPosn(Node node) {
        if (node.isLeaf())
            return (node.getX() + 20);
        else
            return rightMostPosn(node.getRightNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The height of the tree in the number of pixels.
     */
    public int treeHeight(Node node) {
        return (bottomMostPosn(node) - node.getY());
    }
 
    /**
     * @param node The root node of the tree
     * @return The bottom most position of the tree
     */
    public int bottomMostPosn(Node node) {
	if (node == null) return 0;
        if (node.isLeaf())
            return (node.getY() + 30);
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
        int x = node.getX(); int y = node.getY();
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
	    g.drawString(node.getLabel(), node.getX() + 5, node.getY() + 12);
	}
        g.setColor( Color.white );
	g.setFont( bigFont );
        g.drawString(""+node.getWeight(), node.getX() + 2, node.getY()+27);
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
        g.fillRect(node.getX(), node.getY(), 20, 30);
	g.setColor(Color.black);
	g.drawRect(node.getX(), node.getY(), 20, 30);
	if (node.getLabel().length() > 0) {
	    g.setColor( Color.yellow );
	    g.setFont( hugeFont );
	    g.drawString(node.getLabel(), node.getX() + 5, node.getY() + 12);
	}
        g.setColor( Color.white );
	g.setFont( bigFont );
        g.drawString(""+node.getWeight(), node.getX() + 2, node.getY()+27);
 
        // draw links to children
	if (node.getLeftNode() != null) {
            g.setColor( Color.black );
            g.drawLine(node.getX() + 6, node.getY() + 30,
                node.getLeftNode().getX() + 10, node.getLeftNode().getY());
	    if (node.highlightLeft) {
                g.drawLine(node.getX() + 5, node.getY() + 30,
                    node.getLeftNode().getX() + 9, node.getLeftNode().getY());
                g.drawLine(node.getX() + 4, node.getY() + 30,
                    node.getLeftNode().getX() + 8, node.getLeftNode().getY());
	    }
	}

	if (node.getRightNode() != null) {
	    g.setColor( Color.black );
            g.drawLine(node.getX() + 14, node.getY() + 30,
                node.getRightNode().getX() + 10, node.getRightNode().getY());
	    if (node.highlightRight) {
                g.drawLine(node.getX() + 15, node.getY() + 30,
                    node.getRightNode().getX() + 11, node.getRightNode().getY());
                g.drawLine(node.getX() + 16, node.getY() + 30,
                    node.getRightNode().getX() + 12, node.getRightNode().getY());
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
	node.moveNode(dx, dy);
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
	    if (node.getX() == posnNode.getX() && node.getY() == posnNode.getY()) {
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

    Node root = null;
    public void setRoot(Node node) {
	this.root = node;
    }

    public int getX() {
	return getNodeAt(1).getX();
    }

    public int getY() {
	return getNodeAt(1).getY();
    }

    public void move(int x, int y) {
	Point orig = new Point(0, 0);
	if (root != null) {
	    orig.x = root.getX();
	    orig.y = root.getY();
	    root.move(x, y);
	} else {
	    root = (Node)nodeList.firstElement();
	    if (root != null) {
		orig.x = root.getX();
		orig.y = root.getY();
		root.move(x, y);
	    }
	}

	if (root != null) {
	    int dx = x - orig.x;
	    int dy = y - orig.y;
	    if (tmp1Str.length() > 0) {
		tmp1X += dx;
		tmp1Y += dy;
	    }

	    if (tmp2Str.length() > 0) {
		tmp2X += dx;
		tmp2Y += dy;
	    }

	    if (tmp3Str.length() > 0) {
		tmp3X += dx;
		tmp3Y += dy;
	    }
	}
    }
}
