
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class DrawingPanel extends Panel {
    int panel_height = 100;
    int panel_width = 100;
    int offset = 10;
    int pref_height = 200;
    int pref_width = 250;
    int delayDuration = 200;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    Font bigFont, smallFont, tinyFont, hugeFont;
    String movingText;
    boolean drawMT = false;
    int[] freq = new int[26];
    boolean processFreq = true;
    int origNodesPosn = 0;
    Vector sortedNodes, comNodes, topRow;
    boolean encoding = false;
    Hashtable table;
    char highlightC;
    String testString, testCode;
    int highlightbit;
    int testx;
    TreeUtils treeUtils;
    int tableOffsetX = 5;
    boolean dec = false;

    public DrawingPanel() {
        smallFont = new Font("Dialog", Font.PLAIN, 10);
        fm = this.getFontMetrics(smallFont);
	bigFont = new Font("Dialog", Font.PLAIN, 12);
	hugeFont = new Font("Dialog", Font.PLAIN, 14);
	tinyFont = new Font("Dialog", Font.PLAIN, 8);
	setBackground(Color.white);
	movingText = new String();
	panel_height = size().height;
	panel_width = size().width;
	initFreq();
	treeUtils = new TreeUtils(this);
    } // DrawingPanel() 
    
    public void initFreq() {
	origNodesPosn = 0; initSortedNodes();
	for (int i = 0; i < 26; i++)
	    freq[i] = 0;
	initSortedNodes(); initTable();
	encoding = false; testString = new String();
	highlightbit = -1; testCode = new String();
	tableOffsetX = 5; dec = false;
	repaint();
    }

    public void decode(boolean dec) {
	this.dec = dec;
    }

    public void setFreq(int posn, int val) {
	freq[posn] = val;
    }

    public void setHighlightBit(int i) {
	highlightbit = i;
	repaint(); delay();
    }

    public void removeFirstChar() {
	testString = testString.substring(1);
    }

    public void setTestString(String str) {
	testString = new String(str);
	testx = panel_width - str.length()*20 - 20;
	repaint(); delay();
    }

    public void setCode(String code) {
	this.testCode = code;
	repaint(); delay();
    }

    public void initSortedNodes() {
	sortedNodes = new Vector();
	comNodes = new Vector();
    }

    public void insertSortedNode(Node node, int i) {
	int dest_x = offset + i*21;
	int dest_y = panel_height/2 - origNodesPosn + 50;
	if (sortedNodes.size() > 0)
	    dest_y = ((Node)sortedNodes.firstElement()).y;
	
	// move the remaining node to the right
	/* remove animation for sorting... 
	for (int j = i; j < sortedNodes.size(); j++) {
	    ((Node)sortedNodes.elementAt(j)).x += 10;
	}
	repaint();
	delay();
	for (int j = i; j < sortedNodes.size(); j++) {
	    ((Node)sortedNodes.elementAt(j)).x += 11;
	}
	repaint();
	delay();
	*/
	for (int j = i; j < sortedNodes.size(); j++) {
	    ((Node)sortedNodes.elementAt(j)).x += 21;
	}
	repaint(); delay();

	freq[((int)node.getLabel().charAt(0)) - 'A'] = -1;
	/*
	int source_x = offset +
			 (((int)node.getLabel().charAt(0)) - 'A') * 21;
	int source_y = panel_height/2 - origNodesPosn;

	// animate move from source to dest - 
	node.x = source_x;
	node.y = source_y;
	*/
	sortedNodes.insertElementAt(node, i);

	/*
	for (int k = 0; k < 5; k++) {
	    node.x = source_x + (k+1)*(dest_x - source_x)/6;
	    node.y = source_y + (k+1)*(dest_y-30 - source_y)/6;
	    repaint();
	    delay();
	}
	*/


	node.x = dest_x;
	node.y = dest_y;
	node.highlight = node.highlightLeft = node.highlightRight = false;
	repaint();
	delay();
    }

    public void moveSortedNodesUp() {
	int dy = offset*2 - ((Node)sortedNodes.elementAt(0)).y;
	for (int i = 0; i < 5; i++) {
	    for (int j = 0; j < sortedNodes.size(); j++) {
		((Node)sortedNodes.elementAt(j)).y -= (panel_height/4) / 5;
	    }
	    repaint();
	    delay();
	}
	for (int i = 0; i < sortedNodes.size(); i++)
	    ((Node)sortedNodes.elementAt(i)).y = offset*2;
	repaint();
	delay();
    }

    public void setNodes(Vector topRow) {
	this.topRow = new Vector();
	for (int i = 0; i < topRow.size(); i++)
	    this.topRow.addElement(topRow.elementAt(i));
    }

    public void combine(Node node1, Node node2, Node comNode) {
	comNode.y = node1.y;
	comNode.x = (node1.x + node2.x)/2;

	for (int i = 0; i < 3; i++) {
	    treeUtils.moveNode(node1, 0, 13);
	    treeUtils.moveNode(node2, 0, 13);
	    repaint();
	    delay();
	}

	comNodes.addElement(comNode);
	topRow.removeElementAt(0);
	topRow.removeElementAt(0);
	repaint();
	delay();
    }

    public void moveComNode(Node comNode, int posn) {
	if (posn == 0) {
	    topRow.insertElementAt(comNode, 0);
	    return;
	} 

	// calculate the highest branch this comNode has to pass
	int highestTree = 0;
	for (int i = 0; i < posn; i++) {
	    int height = treeUtils.treeHeight((Node)topRow.elementAt(i));
	    if (height > highestTree)
		highestTree = height;
	}

	// move comNode down below the highest branch
	int dy = highestTree + 5;
	for (int j = 0; j < 5; j++) {
	    treeUtils.moveNode(comNode, 0, dy/5);
	    repaint();
	    delay();
	}

	// left shift following nodes
	int dx = offset - treeUtils.leftMostPosn((Node)topRow.firstElement());
	for (int j = 0; j < 3; j++) {
	    for (int i = 0; i < posn; i++) {
	        treeUtils.moveNode((Node)topRow.elementAt(i), dx/3, 0);
	    	if (j == 2)
		    treeUtils.moveNode((Node)topRow.elementAt(i), dx%3, 0);
	    }
	    repaint();
	    delay();
	}

	// right shift comNode
	int left_dest_x = treeUtils.rightMostPosn(
			(Node)topRow.elementAt(posn-1)) + 1;
	dx = left_dest_x - treeUtils.leftMostPosn(comNode);
	for (int i = 0; i < 5; i++) {
	    treeUtils.moveNode(comNode, dx/5, 0);
	    repaint(); delay();
	}
	dx = left_dest_x - treeUtils.leftMostPosn(comNode);
	treeUtils.moveNode(comNode, dx, 0);

	// up shift comNode
	int dest_y = ((Node)topRow.elementAt(posn-1)).y;
	dy = dest_y - comNode.y;
	for (int i = 0; i < 5; i++) {
	    treeUtils.moveNode(comNode, 0, dy/5);
	    repaint();
	    delay();
	}
	dy = dest_y - comNode.y;
	treeUtils.moveNode(comNode, 0, dy);
	repaint(); delay();

	if (posn == topRow.size()) {
	    topRow.addElement(comNode);
	} else {
	    topRow.insertElementAt(comNode, posn);
	}
    }

    public void centralize(Node tree) {
	int dx = panel_width/2 - offset*2 - treeUtils.treeWidth(tree)/2;
	int dy = offset - tree.y;
	for (int i = 0; i < 3; i++) {
	    treeUtils.moveNode(tree, dx/3, dy/3);
	    repaint();
	    delay();
	}
	encoding = true;
	repaint();
	delay();
    }

    public void initTable() {
	this.table = new Hashtable();
	highlightC = '\0';
    }

    public void delay() {
        try {
            Thread.sleep(delayDuration);
        } catch (InterruptedException e) {}
    }

    public void shortDelay() {
	try {
	    Thread.sleep(delayDuration/3);
	} catch (InterruptedException e) {}
    }

    public void setDelay(int delay) {
	this.delayDuration = delay;
    }
    
    public void addMovingText(char c) {
	char[] ch = {c};
	final int max_char = 45;
	final int printlen = 135;
	
	processFreq = true;
	movingText = movingText.concat(new String(ch));
	if (movingText.length() > printlen) {
	    //movingText = movingText.substring(movingText.length() - max_char);
	    movingText = movingText.substring(movingText.length() - 1);
	    drawMT = false;
	}

	if (movingText.length() == printlen) {
	    drawMT = true;
	    repaint();
	    shortDelay();
	    drawMT = false;
	    //repaint();
	    //shortDelay();
	}
    }

    public void setProcFreq(boolean b) {
	processFreq = false;
	repaint();
    }

    public void hideOrigNodes() {
	origNodesPosn = panel_height;
    }

    public void moveOrigNodesUp() {
	for (int i = 0; i < 5; i++) {
	    origNodesPosn += (panel_height/4) / 6;
	    repaint();
	    delay();
	}
    }

    public void clearMovingText() {
	movingText = new String();
	drawMT = false;
	repaint();
    }

    public void highlightNode(Node node) {
	node.highlight = true;
	repaint(); delay();
    }
    public void restoreNode(Node node) {
	node.highlight = false;
	//repaint(); delay();
    }

    public void highlightLeft(Node node) {
	node.highlightLeft = true;
	repaint(); delay();
    }
    public void restoreLeft(Node node) {
	node.highlightLeft = false;
	//repaint(); delay();
    }
    
    public void highlightRight(Node node) {
	node.highlightRight = true;
	repaint(); delay();
    }
    public void restoreRight(Node node) {
	node.highlightRight = false;
	//repaint(); delay();
    }

    public void addTable(Character c, String code) {
	table.put(c, code);
	highlightTable(c.charValue());
    }

    public void moveTableRight() {
	for (int i = 10; i < panel_width/2; i += 20) {
	    tableOffsetX = i;
	    repaint(); delay();
	}
    }

    public void restoreTable() {
	while (tableOffsetX > 20) {
	    tableOffsetX -= 20;
	    repaint(); delay();
	}
	tableOffsetX = 5;
	repaint(); delay();
    }

    public void highlightTable(char c) {
	highlightC = c;
	repaint();
	delay();
	highlightC = '\0';
	repaint();
	delay();
    }

    public void moveTreeUp() {
	Node tree = (Node)topRow.firstElement();
	int orig = treeUtils.bottomMostPosn(tree);
	int dy = (panel_height/2 - 30) - orig;
	for (int i = 0; i < 3; i++) {
	    treeUtils.moveNode(tree, 0, dy/3);
	    repaint(); delay();
	}
    }

    public void moveTreeDown(Node tree) {
	int dest_y = 60;
	int dy = dest_y - tree.y;
	treeUtils.moveNode(tree, 0, dy);
	repaint(); delay();
    }
    
    public void update(Graphics g) {
        Dimension d = size();
        if (d.width < 1 || d.height < 1)
            return;

        if ((offscreen == null) || (d.width != offscreensize.width) ||
                (d.height != offscreensize.height)) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            offGraphics = offscreen.getGraphics();
        }

        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setFont(smallFont);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void paint(Graphics g) {
	panel_height = size().height;
	panel_width = size().width;

	if (processFreq) {
	    g.setColor( Color.yellow );
	    g.fillRect(offset-2, panel_height/2 - 57, 21*26 + 4,
				90);
	    g.setColor( Color.green );
	    g.fill3DRect(offset, panel_height/2 - 55, offset*19, 50, true);
	    g.setColor( Color.black );
	    g.setFont( bigFont );
	    g.drawString("Counting characters (A-Z)", 
			offset*2, panel_height/2 - 25);
	    g.setColor( Color.pink );
	    g.fillRect(offset - 2, panel_height/2 - 5, 21*26 + 4, 40);
	}

	if (drawMT)
	    drawMovingText(g, movingText);

	// draw node with freq
	if (origNodesPosn < panel_height/3) {
	    for (int i = 0; i < 26; i++) {
		if (freq[i] == -1) continue;
	        char[] c = {(char)(i + 'A')};
	    	treeUtils.drawLeafNode(g, offset + i*21, 
			panel_height/2 - origNodesPosn, 
			new String(c), freq[i]);
	    }
	}

	if (sortedNodes.size() > 0) {
	    for (int i = 0; i < sortedNodes.size(); i++) {
		Node node = (Node)sortedNodes.elementAt(i);
		treeUtils.drawLeafNode(g, node);
	    }
	}

	if (comNodes.size() > 0) {
	    for (int i = 0; i < comNodes.size(); i++) {
	        Node node = (Node)comNodes.elementAt(i);
		treeUtils.drawNode(g, node);
	    }
	}

	if (table.size() > 0) {
	    drawTable(g);
	}

	if (testString.length() > 0) {
	    drawTestString(g);
	}

	if (testCode.length() > 0) {
	    drawTestCode(g);
	}

        g.setColor( Color.black );
        g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    } // paint()

    public void drawTestCode(Graphics g) {
	int x, y;
	if (!dec) {
	    x = tableOffsetX - testCode.length() * 8; 
	    y = 5*panel_height/6;
	} else {
	    x = ((Node)topRow.firstElement()).x;
	    y = ((Node)topRow.firstElement()).y - 5;
	}
	g.setFont( hugeFont );
	g.setColor( Color.blue );
	g.drawString(testCode, x, y);
    }

    public void drawTestString(Graphics g) {
	int x, y;
	if (!dec) {
	    x = testx;
	    y = 5*panel_height/6;
	} else {
	    x = panel_width/2;
	    y = treeUtils.bottomMostPosn((Node)topRow.firstElement()) +30;

	    g.setColor( Color.cyan );
	    g.fill3DRect( x - 5, y - 18, 18 * testString.length() + 10, 
			25, true );
	}
	g.setFont( hugeFont );
	for (int i = 0; i < testString.length(); i++) {
	    if (highlightbit == i)
	    	g.setColor( Color.red );
	    else
	    	g.setColor( Color.black );
	    g.drawString(testString.substring(i, i+1), x, y);
	    x += 18;
	}
    }
	
    public void drawTable(Graphics g) {
	int x = offset + tableOffsetX;
	int y = panel_height/2;

	g.setColor(Color.black);
	g.setFont(bigFont);
	g.drawString("The encoding table is:", x, y);
	x += offset;
	y += 20;
	g.setFont(smallFont);
	for (char c = 'A'; c < 1+'Z'; c++) {
	    Character ch = new Character(c);
	    if (table.containsKey(ch)) {
		if (highlightC != c) {
		    g.setColor(Color.blue);
		} else {
		    g.setColor(Color.gray);
		    g.fillRect(x-2, y-10, 100, 12);
		    g.setColor(Color.red);
		}
		g.drawString(ch.toString(), x, y);
		g.drawString((String)table.get(ch), x + 20, y);
		y += 10;
	    }
	}
    }

    public void drawMovingText(Graphics g, String text) {
	g.setFont(bigFont);
	g.setColor( Color.black );
	g.drawString(text.substring(0,45), offset*21, panel_height/2 - 37);
	g.drawString(text.substring(45,90), offset*21, panel_height/2 - 25);
	g.drawString(text.substring(90), offset*21, panel_height/2 - 13);
    }

    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()
} // class DrawingPanel
