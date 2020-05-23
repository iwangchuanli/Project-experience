
import java.lang.*;
import java.awt.*;
import java.util.*;

class OptMatMult extends Object {
    int n;
    IntMatrix cost, best, dimMat;
    DrawingPanel drawingPanel;
    AlgAnimFrame frame;
    Dimension[] dims;

    static final int horizSpace = 66;
    static final int vertSpace = 19;
    
    /**
     * Dimension is used to represent the dimension of the matrices,
     * where the number of columns is represented by the 'width' attribute
     * and the number of rows is represented by the 'height' attribute from
     * java.awt.Dimension
     */
    public OptMatMult(Dimension[] dims, IntMatrix costMat, IntMatrix bestMat,
		DrawingPanel drawingPanel, AlgAnimFrame frame,
		IntMatrix dimMat ) {
	this.drawingPanel = drawingPanel;
	this.frame = frame;
	this.cost = costMat;
	this.best = bestMat;
	this.dimMat = dimMat;
	this.dims = dims;
	this.n = dims.length;
    }

    private int CostSubTree( int j, int k ) {
	int c;
	if ( (j<0) || (j>=k) || (j>=n) || (k>=n) )
	    c = 0;
	else {
	    c = cost.elem(j,k);
	}
	return c;
    }

/*-----------------------------------------------------------------------*/
    // Evaluate trees of size k
    public void matEvaluate( int numMat ) { /*-*/int line = 1;
	/*-*/frame.Highlight(line);
	int left, right, c_index, k, c, best_cost, best_index;

	// For matrix starting at 0, n-numMat
	/*-*/frame.Highlight(line + 4);
	for (left = 0; left < (n-numMat+1); left++) {
	    right = left+numMat-1;
	    /*-*/frame.Highlight(line + 5);
	    /*-*/ComBox com = highlightDimMat(left, right);
	    best_cost = cost.elem(left,right); // Best cost
	    /*-*/frame.Highlight(line + 6);
	    best_index = left;
	    /*-*/frame.Highlight(line + 7);
	    // If left tree has k nodes, right tree has numMat - k - 1
	    /*-*/DrawingObj[] objs, bestObjs = null;
	    /*-*/frame.Highlight(line + 9);
	    for (c_index = left; c_index < right; c_index++) {
		// Check each candidate index
		/*-*/frame.Highlight(line + 11);
		c = CostSubTree(left, c_index) + 
		    CostSubTree(c_index+1, right) +
		    dims[left].height * dims[c_index].width * 
			dims[right].width; 

		/*-*/objs = animateCost(left, c_index, right);
	    	/*-*/frame.Highlight(line + 16);
		if ( c < best_cost ) {
		    best_cost = c;
		    /*-*/frame.Highlight(line + 17);
		    best_index = c_index;
		    /*-*/frame.Highlight(line + 18);
		    /*-*/bestObjs = animateBest(objs, bestObjs);
		} /*-*/else animateRemoveObjs(objs);
	    }
	    /*-*/frame.Highlight(line + 20);

	    // Update the cost, best matrices
	    /*-*/frame.Highlight(line + 23);
	    /*-*/frame.Highlight(line + 24);
	    /*-*/animateStoreInMat(bestObjs, left, right, best_index);
	    best.setElem(left,right,best_index);
	    cost.setElem(left,right,best_cost);
	    /*-*/restoreDimMat(left, right, com);
    	}
	/*-*/frame.Highlight(line + 25);
    } // matEvaluate()

    public void start() { /*-*/int line = 28;
	/*-*/frame.Highlight(line);
	// Build all the sub-trees in turn
	/*-*/frame.Highlight(line + 3);
	/*-*/frame.Highlight(line + 4);
	/*-*/frame.Highlight(line + 5);
	for (int i = 0; i < n-1; i++)
	    cost.setElem(i, i+1,
		dims[i].height * dims[i].width * dims[i+1].width);
	/*-*/frame.Highlight(line + 6);
	/*-*/frame.Highlight(line + 7);
	for(int k=0;k<n-1;k++)
	    best.setElem( k, k+1, k );
	/*-*/animateInit();
	/*-*/frame.waitSkip();
	/*-*/frame.Highlight(line + 8);
	for( int k=3; k<=n; k++ ) {
	    /*-*/frame.Highlight(line + 9);
	    this.matEvaluate( k );
	    /*-*/frame.waitSkip();
	    /*-*/frame.Highlight(line + 10);
	}
	/*-*/frame.Highlight(line + 11);
	formBestParenthesisation();
	/*-*/frame.Highlight(line + 12);
    }
//----------------------------------------------------------------

    private Color darkGreen = new Color(0, 150, 0);
    private void animateInit() {
	ComBox com = new ComBox(420, 20, "Initialization...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.repaint(); drawingPanel.delay();

	ShadowLabel mesg1 = new ShadowLabel(
	   "There is only one possible way to multiply two");
	mesg1.setColor(darkGreen);
	mesg1.move(420, 70);
	drawingPanel.addDrawingObj(mesg1);
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	ShadowLabel mesg2 = new ShadowLabel(
	   "matrices in order...");
	mesg2.setColor(darkGreen);
	mesg2.move(420, 90);
	drawingPanel.addDrawingObj(mesg2);
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	ShadowLabel mesg3 = new ShadowLabel(
	   "The entries of the cost matrix are intialized.");
	mesg3.setColor(darkGreen);
	mesg3.move(420, 110);
	drawingPanel.addDrawingObj(mesg3);
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	for (int i = 0; i < n-1; i++)
	    cost.setHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    cost.restoreHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    cost.setHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    cost.restoreHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    cost.setHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    cost.restoreHighlight(i, i+1);
	frame.waitStep();

	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("For example, this entry is the cost for");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();
	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("multiplying A0 and A1, which is the number");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("of scalar multiplications during the matrix product.");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	FloatingBox fb = new FloatingBox("" + cost.elem(0, 1),
		drawingPanel.getFixFont());
	fb.move(cost.getPosn(0, 1).x, cost.getPosn(0, 1).y);
	drawingPanel.addDrawingObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	Vector traj = new Vector();
	traj.addElement(new Point(fb.getX(), fb.getY()));
	traj.addElement(new Point(420, fb.getY()));
	traj.addElement(new Point(420, 130));
	drawingPanel.animate(fb, traj);

	ShadowLabel eq = new ShadowLabel("=");
	eq.setColor(darkGreen);
	eq.move(488, 142);
	drawingPanel.addDrawingObj(eq);
	drawingPanel.repaint(); drawingPanel.delay();

	FloatingBox fb2 = new FloatingBox("" + dims[0].height,
		drawingPanel.getFixFont());
	fb2.move(dimMat.getPosn(0, 0).x, dimMat.getPosn(0, 0).y);
	drawingPanel.addDrawingObj(fb2);
	drawingPanel.repaint(); drawingPanel.delay();
	traj = new Vector();
	traj.addElement(new Point(fb2.getX(), fb2.getY()));
	traj.addElement(new Point(500, fb2.getY()));
	traj.addElement(new Point(500, 130));
	drawingPanel.animate(fb2, traj);

	ShadowLabel mult1 = new ShadowLabel("*");
	mult1.setColor(darkGreen);
	mult1.move(568, 145);
	drawingPanel.addDrawingObj(mult1);
	drawingPanel.repaint(); drawingPanel.delay();

	FloatingBox fb3 = new FloatingBox("" + dims[0].width,
		drawingPanel.getFixFont());
	fb3.move(dimMat.getPosn(1, 0).x, dimMat.getPosn(1, 0).y);
	drawingPanel.addDrawingObj(fb3);
	drawingPanel.repaint(); drawingPanel.delay();
	traj = new Vector();
	traj.addElement(new Point(fb3.getX(), fb3.getY()));
	traj.addElement(new Point(580, fb3.getY()));
	traj.addElement(new Point(580, 130));
	drawingPanel.animate(fb3, traj);

	ShadowLabel mult2 = new ShadowLabel("*");
	mult2.setColor(darkGreen);
	mult2.move(648, 145);
	drawingPanel.addDrawingObj(mult2);
	drawingPanel.repaint(); drawingPanel.delay();

	FloatingBox fb4 = new FloatingBox("" + dims[1].width,
		drawingPanel.getFixFont());
	fb4.move(dimMat.getPosn(1, 1).x, dimMat.getPosn(1, 1).y);
	drawingPanel.addDrawingObj(fb4);
	drawingPanel.repaint(); drawingPanel.delay();
	traj = new Vector();
	traj.addElement(new Point(fb4.getX(), fb4.getY()));
	traj.addElement(new Point(660, fb4.getY()));
	traj.addElement(new Point(660, 130));
	drawingPanel.animate(fb4, traj);

	frame.waitStep();

	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("The entries initialized in the best matrix");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();
	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("are only the indices of the first matrices of");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();
	mesg1.setText(mesg2.getText()); mesg2.setText(mesg3.getText());
	mesg3.setText("the matrix-pair multiplications...");
	drawingPanel.repaint(); drawingPanel.delay();drawingPanel.delay();

	drawingPanel.removeObj(eq);
	drawingPanel.removeObj(mult1);
	drawingPanel.removeObj(mult2);
	drawingPanel.removeObj(fb);
	drawingPanel.removeObj(fb2);
	drawingPanel.removeObj(fb3);
	drawingPanel.removeObj(fb4);

	for (int i = 0; i < n-1; i++)
	    best.setHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    best.restoreHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    best.setHighlight(i, i+1);
	drawingPanel.repaint(); drawingPanel.delay();
	for (int i = 0; i < n-1; i++)
	    best.restoreHighlight(i, i+1);
	frame.waitStep();
	drawingPanel.repaint(); drawingPanel.delay();

	drawingPanel.removeCom(com);
	drawingPanel.removeObj(mesg1);
	drawingPanel.removeObj(mesg2);
	drawingPanel.removeObj(mesg3);
    } // animateInit()

    private ComBox highlightDimMat(int left, int right) {
	ComBox com = new ComBox(420, 20, "Finding optimal order for these " +
		(right - left + 1) + " matrices product..",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	for (int i = left; i < right+1; i++) {
	    dimMat.setHighlight(0, i);
	    dimMat.setHighlight(1, i);
	}
	drawingPanel.repaint(); drawingPanel.delay();
	return com;
    } // highlightDimMat()

    private void restoreDimMat(int left, int right, ComBox com) {
	for (int i = left; i < right+1; i++) {
	    dimMat.restoreHighlight(0, i);
	    dimMat.restoreHighlight(1, i);
	}
	drawingPanel.removeCom(com);
	frame.waitStep();
	drawingPanel.repaint(); drawingPanel.delay();
    } // restoreDimMat()

    private DrawingObj[] animateCost(int left, int c_index, int right) {
	DrawingObj[] objs = new DrawingObj[3];

	ComBox com = new ComBox(420, 70, "Checking this parenthesisation...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);

	Subscript[] s1 = new Subscript[c_index-left+1];
	for (int i = left; i <= c_index; i++)
	    s1[i-left] = new Subscript("A", ""+i);
	Subscript[] s2 = new Subscript[right-c_index];
	for (int i = c_index+1; i <= right; i++)
	    s2[i-c_index-1] = new Subscript("A", ""+i);

	Parenthesis p1 = new Parenthesis(s1, drawingPanel.getFixFont());
	Parenthesis p2 = new Parenthesis(s2, drawingPanel.getFixFont());

	p1.move(450, 120); p2.move(p1.getRight(), 120);
	drawingPanel.addDrawingObj(p1);
	drawingPanel.addDrawingObj(p2);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();

	FloatingBox fb1 = null, fb2 = null;
	Parenthesis tmpL = null, tmpR = null;
	if (s1.length > 1) {
	    Subscript[] tmpS1 = new Subscript[c_index-left+1];
	    for (int i = left; i <= c_index; i++)
	    	tmpS1[i-left] = new Subscript("A", ""+i);
	    tmpL = new Parenthesis(tmpS1, drawingPanel.getFixFont());
	    
	    com.setText("The optimal cost for this obtained from cost matrix.");
	    DimIndicator d = new DimIndicator((p1.getX()+p1.getRight())/2,
		p1.getY() + 2, p1.getX(), p1.getRight(),
		"cost["+left+","+c_index+"]",
		drawingPanel.getFixFont());
	    drawingPanel.addDrawingObj(d);
	    drawingPanel.repaint(); drawingPanel.delay();

	    fb1 = new FloatingBox(""+cost.elem(left,c_index),
		drawingPanel.getFixFont());
	    Point p = cost.getPosn(left, c_index);
	    fb1.move(p.x, p.y);
	    drawingPanel.addDrawingObj(fb1);
	    drawingPanel.repaint(); drawingPanel.delay(); drawingPanel.delay();
	    Vector traj = new Vector();
	    traj.addElement(new Point(fb1.getX(), fb1.getY()));
	    traj.addElement(new Point(d.getX()-32, fb1.getY()));
	    traj.addElement(new Point(d.getX()-32, d.getY()+15));
	    drawingPanel.animate(fb1, traj);
	    frame.waitStep();

	    drawingPanel.removeObj(d);
	    traj = new Vector();
	    traj.addElement(new Point(fb1.getX(), fb1.getY()));
	    traj.addElement(new Point(p2.getRight() + 150, fb1.getY()));
	    drawingPanel.animate(fb1, traj);
	    tmpL.move(fb1.getX(), p1.getY());
	    drawingPanel.addDrawingObj(tmpL);
	    drawingPanel.repaint(); drawingPanel.delay();
	}

	if (s2.length > 1) {
	    Subscript[] tmpS2 = new Subscript[right-c_index];
	    for (int i = c_index+1; i <= right; i++)
	    	tmpS2[i-c_index-1] = new Subscript("A", ""+i);
	    tmpR = new Parenthesis(tmpS2, drawingPanel.getFixFont());

	    com.setText("The optimal cost for this obtained from cost matrix.");
	    DimIndicator d = new DimIndicator((p2.getX()+p2.getRight())/2,
		p2.getY() + 2, p2.getX(), p2.getRight(),
		"cost["+(c_index+1)+","+right+"]",
		drawingPanel.getFixFont());
	    drawingPanel.addDrawingObj(d);
	    drawingPanel.repaint(); drawingPanel.delay();

	    fb2 = new FloatingBox(""+cost.elem(c_index+1, right),
		drawingPanel.getFixFont());
	    Point p = cost.getPosn(c_index+1, right);
	    fb2.move(p.x, p.y);
	    drawingPanel.addDrawingObj(fb2);
	    drawingPanel.repaint(); drawingPanel.delay(); drawingPanel.delay();
	    Vector traj = new Vector();
	    traj.addElement(new Point(fb2.getX(), fb2.getY()));
	    traj.addElement(new Point(d.getX()-32, fb2.getY()));
	    traj.addElement(new Point(d.getX()-32, d.getY()+15));
	    drawingPanel.animate(fb2, traj);
	    frame.waitStep();

	    drawingPanel.repaint(); drawingPanel.delay();
	    drawingPanel.removeObj(d);
	    traj = new Vector();
	    traj.addElement(new Point(fb2.getX(), fb2.getY()));
	    traj.addElement(new Point(p2.getRight() + 50, fb2.getY()));
	    drawingPanel.animate(fb2, traj);
	    tmpR.move(fb2.getX(), p2.getY());
	    drawingPanel.addDrawingObj(tmpR);
	    drawingPanel.repaint(); drawingPanel.delay();
	}

	DimIndicator d1 = new DimIndicator((p1.getX()+p1.getRight())/2,
		p1.getY() + 2, p1.getX(), p1.getRight(),
		""+ dims[left].height + "x" + dims[c_index].width,
		drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d1);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();

	drawingPanel.removeObj(d1);
	d1 = new DimIndicator((p2.getX()+p2.getRight())/2,
		p2.getY() + 2, p2.getX(), p2.getRight(),
		"" + dims[c_index+1].height + "x" + dims[right].width,
		drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d1);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();

	drawingPanel.removeObj(d1);
	com.setText("Cost = " + dims[left].height + "x" + 
		dims[c_index+1].height + "x" + dims[right].width + " = " +
		(dims[left].height*dims[c_index+1].height*dims[right].width));
	drawingPanel.repaint(); drawingPanel.delay();

	FloatingBox box = new FloatingBox(""+
		(dims[left].height*dims[c_index+1].height*dims[right].width),
		drawingPanel.getFixFont());
	box.move(p1.getX(), p2.getY() + 17);
	drawingPanel.addDrawingObj(box);
	drawingPanel.repaint();drawingPanel.delay();
	frame.waitStep();

	com.setText("Adding to form total cost...");
	drawingPanel.repaint();drawingPanel.delay();
	Vector traj0 = new Vector(), traj1 = new Vector(), 
	    traj2 = new Vector();
	int numAnim = 1;
	traj0.addElement(new Point(box.getX(), box.getY()));
	traj0.addElement(new Point(p2.getRight() + 10, box.getY()));
	traj0.addElement(new Point(p2.getRight() + 10, 
			p2.getY() - 13));
	drawingPanel.setAnimStep(5);
	if (fb1 != null) {
	    numAnim++;
	    traj1.addElement(new Point(fb1.getX(), fb1.getY()));
	    traj1.addElement(new Point(p2.getRight() + 10, fb1.getY()));
	    traj1.addElement(new Point(p2.getRight() + 10, 
			p2.getY() - 13));
	}
	if (fb2 != null) {
	    numAnim++;
	    traj2.addElement(new Point(fb2.getX(), fb2.getY()));
	    traj2.addElement(new Point(p2.getRight() + 10, fb2.getY()));
	    traj2.addElement(new Point(p2.getRight() + 10, 
			p2.getY() - 13));
	}
	DrawingObj[] animObjs = new DrawingObj[numAnim];
	Vector[] trajs = new Vector[numAnim];
	animObjs[0] = box; trajs[0] = traj0;
	int count = 1;
	if (fb1 != null) {
	    animObjs[count] = fb1;
	    trajs[count++] = traj1;
	}
	if (fb2 != null) {
	    animObjs[count] = fb2;
	    trajs[count++] = traj2;
	}
	drawingPanel.removeObj(tmpL);
	drawingPanel.removeObj(tmpR);
	drawingPanel.animate(animObjs, trajs);

	box.setText("" +
		(dims[left].height * dims[c_index].width * dims[right].width +
		 CostSubTree(left, c_index) + CostSubTree(c_index+1, right)));
	drawingPanel.removeObj(fb1);
	drawingPanel.removeObj(fb2);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();
	drawingPanel.removeCom(com);
	objs[0] = p1; objs[1] = p2; objs[2] = box;
	return objs;
    } // animateCost()

    private DrawingObj[] animateBest(DrawingObj[] objs, DrawingObj[] bestObjs) {
	ComBox com = new ComBox(30+3*66, 135,
		"This parenthesisation gives the best cost so far...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.repaint(); drawingPanel.delay();

	if (bestObjs != null) {
	    ComBox com2 = new ComBox(30+3*66+10, 145,
		"Remove previous best..", drawingPanel.getFixFont());
	    drawingPanel.addCom(com2);
	    drawingPanel.repaint(); drawingPanel.delay();
	    for (int k = 0; k < 5; k++) {
	        for (int i = 0; i < bestObjs.length; i++)
		    drawingPanel.removeObj(bestObjs[i]);
	        drawingPanel.repaint(); drawingPanel.delay();
	        for (int i = 0; i < bestObjs.length; i++)
		    drawingPanel.addDrawingObj(bestObjs[i]);
	        drawingPanel.repaint(); drawingPanel.delay();
	    }
	    for (int i = 0; i < bestObjs.length; i++)
		drawingPanel.removeObj(bestObjs[i]);
	    drawingPanel.removeCom(com2);
	    drawingPanel.repaint(); drawingPanel.delay();
	}
	Vector traj1 = new Vector(), traj2 = new Vector(), traj3 = new Vector();
	traj1.addElement(new Point(objs[0].getX(), objs[0].getY()));
	traj2.addElement(new Point(objs[1].getX(), objs[1].getY()));
	traj3.addElement(new Point(objs[2].getX(), objs[2].getY()));
	int dx = 30+3*66 - objs[0].getX();
	traj1.addElement(new Point(objs[0].getX() + dx, objs[0].getY()));
	traj2.addElement(new Point(objs[1].getX() + dx, objs[1].getY()));
	traj3.addElement(new Point(objs[2].getX() + dx, objs[2].getY()));
	Vector[] trajs = new Vector[3];
	trajs[0] = traj1; trajs[1] = traj2; trajs[2] = traj3;
	drawingPanel.animate(objs, trajs);

	drawingPanel.removeCom(com);
	bestObjs = objs;
	frame.waitStep();
	return bestObjs;
    } // animateBest()

    private void animateRemoveObjs(DrawingObj[] objs) {
	ComBox com = new ComBox(objs[0].getX(), objs[0].getY() + 20,
		"Higher cost.. Dispose!", drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.repaint(); drawingPanel.delay();

	Vector traj1 = new Vector(), traj2 = new Vector(), traj3 = new Vector();
	traj1.addElement(new Point(objs[0].getX(), objs[0].getY()));
	traj2.addElement(new Point(objs[1].getX(), objs[1].getY()));
	traj3.addElement(new Point(objs[2].getX(), objs[2].getY()));
	int dx = 700;
	traj1.addElement(new Point(objs[0].getX() + dx, objs[0].getY()));
	traj2.addElement(new Point(objs[1].getX() + dx, objs[1].getY()));
	traj3.addElement(new Point(objs[2].getX() + dx, objs[2].getY()));
	Vector[] trajs = new Vector[3];
	trajs[0] = traj1; trajs[1] = traj2; trajs[2] = traj3;
	drawingPanel.setAnimStep(10);
	drawingPanel.animate(objs, trajs);
	drawingPanel.setAnimStep(5);

	drawingPanel.removeCom(com);
	for (int i = 0; i < objs.length; i++)
	    drawingPanel.removeObj(objs[i]); 
    } // animateRemoveObjs()

    private void animateStoreInMat(DrawingObj[] bestObjs, int left, int right,
		int bestIndex) {
	ComBox com = new ComBox(30+3*66, 135,
		"Moving optimal cost to cost matrix...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.repaint(); drawingPanel.delay();
	com.setText("Cost for matrix multiplication of A"+left+" to A"+right+
	    " is stored in matrix at column = "+left+ " and row = "+right);
	com.move(20, 60);
	drawingPanel.repaint(); drawingPanel.delay();
	Vector traj = new Vector();
	traj.addElement(new Point(bestObjs[2].getX(), bestObjs[2].getY()));
	traj.addElement(new Point(bestObjs[2].getX(), 
			cost.getPosn(left, right).y));
	traj.addElement(new Point(
		cost.getPosn(left, right).x, cost.getPosn(left, right).y));
	drawingPanel.setAnimStep(10);
	drawingPanel.animate(bestObjs[2], traj);
	frame.waitStep();

	Parenthesis p1 = (Parenthesis)bestObjs[0];
	DimIndicator d = new DimIndicator((p1.getRight() - 7),
		p1.getY() + 2, p1.getRight() - 14, p1.getRight(),
		""+bestIndex, drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d);
	com.setText("The index of the last matrix in the left sub-product is" +
	    " going to the same position of the best matrix.");
	com.move(20, 60);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();
	drawingPanel.removeObj(d);
	FloatingBox fb = new FloatingBox("" + bestIndex, 
		drawingPanel.getFixFont());
	fb.move(d.getX()-32, d.getY() + 15);
	drawingPanel.addDrawingObj(fb);
	Point p = best.getPosn(left, right);
	traj = new Vector();
	traj.addElement(new Point(fb.getX(), fb.getY()));
	traj.addElement(new Point(fb.getX(), p.y));
	traj.addElement(new Point(p.x, p.y));
	drawingPanel.animate(fb, traj);
	
	drawingPanel.removeObj(fb);
	drawingPanel.removeCom(com);
	for (int i = 0; i < bestObjs.length; i++)
	    drawingPanel.removeObj(bestObjs[i]);
	drawingPanel.setAnimStep(5);
    } // animateStoreInMat()

    private void formBestParenthesisation() {
	int bestIndex = best.elem(0, n-1);
	best.setHighlight2(0, n-1);
	drawingPanel.repaint(); drawingPanel.delay();
	Parenthesis leftP, rightP;
	if (bestIndex+1 > 2)
	    leftP = formParen(0, bestIndex, Color.blue);
	else {
	    Subscript[] s1 = new Subscript[bestIndex+1];
	    for (int i = 0; i <= bestIndex; i++)
		s1[i] = new Subscript("A", ""+i);
	    leftP = new Parenthesis(s1, drawingPanel.getFixFont());
	}

	if (n - bestIndex - 1 > 2)
	    rightP = formParen(bestIndex+1, n-1, Color.blue);
	else {
	    Subscript[] s2 = new Subscript[n - bestIndex - 1];
	    for (int i = bestIndex + 1; i < n; i++)
		s2[i - bestIndex - 1] = new Subscript("A", ""+i);
	    rightP = new Parenthesis(s2, drawingPanel.getFixFont());
	}
	leftP.move(450, 120);
	rightP.move(leftP.getRight(), 120);
	drawingPanel.addDrawingObj(leftP);
	drawingPanel.addDrawingObj(rightP);
	ShadowLabel result = new ShadowLabel("Best Parenthesisation");
	result.setColor(darkGreen);
	result.move(450, 100);
	drawingPanel.addDrawingObj(result);
	drawingPanel.repaint(); drawingPanel.delay();
	animateFormParen(leftP, rightP);
    }

    private Parenthesis formParen(int left, int right, Color col) {
	Parenthesis result = null;
	int bestIndex = best.elem(left, right);
	best.setHighlight2(left, right);
	drawingPanel.repaint(); drawingPanel.delay();

	Parenthesis leftP, rightP;
	if (bestIndex-left+1 > 2)
	    leftP = formParen(left, bestIndex, darkGreen);
	else {
	    Subscript[] s1 = new Subscript[bestIndex-left+1];
	    for (int i = left; i <= bestIndex; i++)
		s1[i-left] = new Subscript("A", ""+i);
	    leftP = new Parenthesis(s1, drawingPanel.getFixFont());
	}

	if (right - bestIndex > 2)
	    rightP = formParen(bestIndex+1, right, darkGreen);
	else {
	    Subscript[] s2 = new Subscript[right - bestIndex];
	    for (int i = bestIndex + 1; i <= right; i++)
		s2[i - bestIndex - 1] = new Subscript("A", ""+i);
	    rightP = new Parenthesis(s2, drawingPanel.getFixFont());
	}
	
	leftP.setColor(col);
	rightP.setColor(col);
	Parenthesis[] arrayP = new Parenthesis[2];
	arrayP[0] = leftP;
	arrayP[1] = rightP;
	result = new Parenthesis(arrayP, drawingPanel.getFixFont());
	return result;
    }

    private void animateFormParen(Parenthesis left, Parenthesis right) {
	ComBox com = new ComBox(420, 50,
		"This parenthesis is obtained from best matrix...",
		drawingPanel.getFixFont());
	DimIndicator d = new DimIndicator(left.getRight() - 16,
		left.getY() + 2, left.getRight() - 23, left.getRight() - 9,
		"best[0,"+(n-1)+"]", drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d);
	drawingPanel.addCom(com);
	drawingPanel.repaint(); drawingPanel.delay();
	FloatingBox fb = new FloatingBox(""+best.elem(0,5),
		drawingPanel.getFixFont());
	Point src = best.getPosn(0, 5);
	fb.move(src.x, src.y);
	drawingPanel.addDrawingObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	Vector traj = new Vector();
	traj.addElement(new Point(fb.getX(), fb.getY()));
	traj.addElement(new Point(d.getX() - 32, fb.getY()));
	traj.addElement(new Point(d.getX() - 32, d.getY() + 15));
	drawingPanel.animate(fb, traj);
	drawingPanel.removeObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();
	drawingPanel.removeObj(d);

	left = right.getLeftParen();
	d = new DimIndicator(left.getRight() - 16,
		left.getY() + 2, left.getRight() - 23, left.getRight() - 9,
		"best[2,5]", drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d);
	drawingPanel.repaint(); drawingPanel.delay();
	fb.setText(""+best.elem(2,5));
	src = best.getPosn(2, 5);
	fb.move(src.x, src.y);
	drawingPanel.addDrawingObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	traj = new Vector();
	traj.addElement(new Point(fb.getX(), fb.getY()));
	traj.addElement(new Point(d.getX() - 32, fb.getY()));
	traj.addElement(new Point(d.getX() - 32, d.getY() + 15));
	drawingPanel.animate(fb, traj);
	drawingPanel.removeObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();
	drawingPanel.removeObj(d);

	left = left.getLeftParen();
	d = new DimIndicator(left.getRight() - 16,
		left.getY() + 2, left.getRight() - 23, left.getRight() - 9,
		"best[2,4]", drawingPanel.getFixFont());
	drawingPanel.addDrawingObj(d);
	drawingPanel.repaint(); drawingPanel.delay();
	fb.setText(""+best.elem(2,4));
	src = best.getPosn(2, 4);
	fb.move(src.x, src.y);
	drawingPanel.addDrawingObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	traj = new Vector();
	traj.addElement(new Point(fb.getX(), fb.getY()));
	traj.addElement(new Point(d.getX() - 32, fb.getY()));
	traj.addElement(new Point(d.getX() - 32, d.getY() + 15));
	drawingPanel.animate(fb, traj);
	drawingPanel.removeObj(fb);
	drawingPanel.repaint(); drawingPanel.delay();
	frame.waitStep();
	drawingPanel.removeObj(d);

	drawingPanel.removeCom(com);
    }
} // class OptMatMult
