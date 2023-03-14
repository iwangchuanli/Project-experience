
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

class OBSAnim {
    DrawingPanel drawingPanel;
    BinTree tree = null, optree = null;
    Vector freqList;
    Font fixFont = new Font("Courier", Font.PLAIN, 12);
    ComBox com1 = null, com2 = null, com3 = null, com4 = null;

    public OBSAnim(DrawingPanel drawingPanel) {
	this.drawingPanel = drawingPanel;
    }

    public void setBestMat(IntMatrix bestMat) {
	bestMat.setColor(Color.red, Color.white);
	bestMat.move(drawingPanel.getOffset() + 380,
		 drawingPanel.getOffset() + 10);
	drawingPanel.addDrawingObj(bestMat);
    }

    public void setCostMat(IntMatrix costMat) {
	costMat.setColor(Color.blue, Color.white);
	costMat.move(drawingPanel.getOffset(),
		drawingPanel.getOffset() + 10);
	drawingPanel.addDrawingObj(costMat);
    }

    public void setCom(String str, int x, int y) {
	if (drawingPanel.getSkip()) return;
	if (drawingPanel.isComExist(com1))
	    drawingPanel.removeCom(com1);
	if (com1 == null)
	    com1 = new ComBox(x, y, str, fixFont);
	else {
	    com1.setText(str);
	    com1.move(x, y);
	}
	drawingPanel.addCom(com1);
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    public void moveCom(int x, int y) {
	if (drawingPanel.getSkip()) return;
	com1.move(x, y);
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    public void setCom(String str) {
	if (drawingPanel.getSkip()) return;
	if (drawingPanel.isComExist(com1)) {
	    com1.setText(str);
	    drawingPanel.repaint(); drawingPanel.delay();
	}
    }

    public void setText2(String str) {
	if (drawingPanel.getSkip()) return;
	if (drawingPanel.isComExist(com2))
	    drawingPanel.removeCom(com2);
	if (com2 == null) {
	    com2 = new ComBox(drawingPanel.getOffset(),
		drawingPanel.getOffset() + 380, str,
		Color.white, Color.black, fixFont);
	} else if (drawingPanel.isComExist(com2)) {
	    com2.setText(str);
	}
	drawingPanel.addCom(com2);
    }

    public void setText(String str, int x, int y) {
	if (drawingPanel.getSkip()) return;
	if (drawingPanel.isComExist(com3))
	    drawingPanel.removeCom(com3);
	if (com3 == null)
	    com3 = new ComBox(x, y, str, Color.yellow, Color.red, fixFont);
	else {
	    com3.setText(str);
	    com3.move(x, y);
	    com3.setColor(Color.yellow); com3.setBackground(Color.red);
	}
	drawingPanel.addCom(com3);
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    public void setCom2(String str, int x, int y) {
	if (drawingPanel.getSkip()) return;
	if (drawingPanel.isComExist(com4))
	    drawingPanel.removeCom(com4);
	if (com4 == null)
	    com4 = new ComBox(x, y, str, fixFont);
	else {
	    com4.setText(str);
	    com4.move(x, y);
	    com4.setColor(Color.yellow); com4.setBackground(Color.red);
	}
	drawingPanel.addCom(com4);
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    public void hideCom() {
	if (drawingPanel.getSkip()) return;
	drawingPanel.removeCom(com1);
	drawingPanel.repaint();
	drawingPanel.delay();
	com1 = null;
    }

    public void hideCom2() {
	if (drawingPanel.getSkip()) return;
	drawingPanel.removeCom(com4);
	drawingPanel.repaint();
	drawingPanel.delay();
	com4 = null;
    }

    public void hideText() {
	drawingPanel.removeCom(com3);
	com3 = null;
    }

    public void setTree(BinTree tree) {
	drawingPanel.removeObj(this.tree);
	this.tree = tree;
	drawingPanel.addDrawingObj(tree);
	//repaint(); delay();
    }

    public void hideTree() {
	drawingPanel.removeObj(tree);
	this.tree = null;
	drawingPanel.repaint(); drawingPanel.delay();
    }

    private String toString(int i ) {
        return new String(""+ (char)('A' + i));
    }

    public void discardBinTree() {
	hideText();
	setCom("This tree has higher cost. Discarding...");
        if (tree != null) {
	    // move it out of the way
	    drawingPanel.setAnimStep(8);
	    Node rootNode = (Node)tree.nodeList.firstElement();
	    tree.setRoot(rootNode);
	    Vector pts = new Vector();
	    pts.addElement(new Point(rootNode.getX(), rootNode.getY()));
	    pts.addElement(new Point(rootNode.getX() + 700,
					rootNode.getY()));
	    drawingPanel.animate(tree, pts);
	    drawingPanel.removeObj(tree);
	    tree = null;
	}
	hideCom();
    }

    public BinTree moveOpt2Tree() {
	hideCom2();
	setCom("Extracting optimal tree...", 400, 400);

	Node rootNode = (Node)optree.nodeList.firstElement();
	optree.setRoot(rootNode);
	drawingPanel.setAnimStep(4);
	Vector traj = new Vector();
	traj.addElement(new Point(rootNode.getX(), rootNode.getY()));
	traj.addElement(new Point(rootNode.getX() + 80, rootNode.getY()));
	traj.addElement(new Point(rootNode.getX() + 80, rootNode.getY() - 30));
	drawingPanel.animate(optree, traj);
	tree = optree; optree = null;
	hideCom();
	return tree;
    }

    public void setOptree(BinTree binTree) {
	hideText();
	setCom("This is now the best sub-tree...", 
		drawingPanel.getOffset() + 10, drawingPanel.getOffset() + 440); 
	if (optree != null) {
	    // move it out of the way first
	    drawingPanel.setAnimStep(10);
	    Node root = (Node)optree.nodeList.firstElement();
	    optree.setRoot(root);
	    Vector traj = new Vector();
	    traj.addElement(new Point(root.getX(), root.getY()));
	    traj.addElement(new Point(root.getX(), 
			root.getY() + 300));
	    drawingPanel.animate(optree, traj);
	    drawingPanel.removeObj(optree);
	    optree = null;
	}

	Node rootNode = binTree.getNodeAt(1);
	binTree.setRoot(rootNode);
	drawingPanel.setAnimStep(5);
	Vector traj = new Vector();
	traj.addElement(new Point(rootNode.getX(), rootNode.getY()));
	traj.addElement(new Point(rootNode.getX(), rootNode.getY() + 80));
	traj.addElement(new Point(rootNode.getX() - 360, rootNode.getY() + 80));
	drawingPanel.animate(binTree, traj);
	optree = binTree; tree = null;
	hideCom();
    }

    Font hugeFont, bigFont;
    public void setFonts(Font hugeFont, Font bigFont) {
	this.hugeFont = hugeFont;
	this.bigFont = bigFont;
    }

    public void setFreq(int[] a) {
        freqList = new Vector();
        int x = drawingPanel.getOffset();
        int y = drawingPanel.getOffset() + 280;
        for (int i = 0; i < a.length; i++) {
            Node node = new Node(toString(i), a[i]);
            node.setX(x); x += 24;
            node.setY(y);
	    node.initFonts(hugeFont, bigFont);
	    drawingPanel.addDrawingObj(node);
            freqList.addElement(node);
        }
    }

    public void restoreFreq(int i) {
	if (i < freqList.size())
	    ((Node)freqList.elementAt(i)).highlight = false;
    }

    public void highlightFreq(int i ) {
	if (i < freqList.size())
	    ((Node)freqList.elementAt(i)).highlight = true;
    }

} // class OBSAnim
