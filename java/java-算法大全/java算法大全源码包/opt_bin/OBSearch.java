
import java.lang.*;
import java.awt.*;

class OBSearch extends Object {
    int n;
    IntMatrix cost, best;
    int[] rel_freq;
    DrawingPanel drawingPanel;
    OBSAnim obsAnim;
    AlgAnimFrame frame;
    BinTree binTree;

    static final int horizSpace = 34;
    static final int vertSpace = 19;
    
    public OBSearch( int n, int[] freq,  String[] label,
		DrawingPanel drawingPanel, AlgAnimFrame frame ) {
	this.drawingPanel = drawingPanel;
	obsAnim = new OBSAnim(drawingPanel);
	obsAnim.setFonts(drawingPanel.getHugeFont(), drawingPanel.getBigFont());
	this.frame = frame;
	this.n = n;
	cost = new IntMatrix( n );
	best = new IntMatrix( n );
	rel_freq = new int[n];
	for(int j=0; j<n; j++) 
	    rel_freq[j] = freq[j];

	cost.setDiag( freq );
	for(int k=0;k<n;k++)
	    best.setElem( k, k, k );
	cost.setLT( Integer.MAX_VALUE );
	best.setTitle("Roots Matrix");
	String[] bestLabel = new String[label.length];
	for (int i = 0; i < label.length; i++)
	    bestLabel[i] = new String(label[i] + ":" + i);
	best.setRowLabels(bestLabel);
	best.setColLabels(bestLabel);
	cost.setTitle("Costs Matrix");
	cost.setRowLabels(label);
	cost.setColLabels(label);
	obsAnim.setCostMat(cost);
	obsAnim.setBestMat(best);
	drawingPanel.repaint();

	binTree = new BinTree(obsAnim, drawingPanel, n*2);
	obsAnim.setFreq(freq);
	obsAnim.setTree(binTree);
    }

    int CostSubTree( int j, int k ) {
	int c;
	if ( (j<0) || (j>k) || (j>=n) || (k>=n) )
	    c = 0;
	else {
	    c = cost.elem(j,k);
	}
	return c;
    }

/*-----------------------------------------------------------------------*/
    // Evaluate trees of size k
    public void TreeEvaluate( int tree_size ) { /*-*/int line = 1;
	/*-*/frame.Highlight(line);
	int left, right, c_root, k, c, best_cost, best_root;

	/*-*/obsAnim.setText2("Optimal subtree: ");
	// For trees starting at 0, n-tree_size-1
	for(left=0;left<=(n-tree_size);left++) {
            /*-*/frame.Highlight(line + 4);
	    right = left+tree_size-1;/*-*/frame.Highlight(line + 5);
	    /*-*/for (int l = left; l <= right; l++) 
	    /*-*/	obsAnim.highlightFreq(l);
	    /*-*/binTree = new BinTree(obsAnim, drawingPanel, n*2);
	    /*-*/obsAnim.setTree(binTree);
	    /*-*/obsAnim.setCom2(
	    /*-*/	"Checking all possible subtree from: " +
	    /*-*/ 	toString(left) + " to " + toString(right) + "...",
	    /*-*/	drawingPanel.getOffset(), 
	    /*-*/	drawingPanel.getOffset() + 320);
	    /*-*/drawingPanel.repaint(); drawingPanel.delay();
	    best_cost = cost.elem(left,right); // Best cost
	    /*-*/frame.Highlight(line + 6);
	    best_root = left; /*-*/frame.Highlight(line + 7);
	    // If left tree has k nodes, right tree has tree_size - k - 1
	    for(c_root=left;c_root<=right;c_root++) {
		/*-*/frame.Highlight(line + 9);
		// Check each candidate root
		c = CostSubTree(left, c_root-1) + CostSubTree(c_root+1, right); 
		/*-*/frame.Highlight(line + 11);
		/*-*/binTree = new BinTree(obsAnim, drawingPanel, n*2);
		/*-*/obsAnim.setTree(binTree);
		/*-*/binTree.insertNodeAt(new Node(toString(c_root),
		/*-*/	rel_freq[c_root]), 1);
		/*-*/formSubTree( left, c_root, right, 1 );
		/*-*/obsAnim.setText("Subtree " + (c_root-left+1),
	        /*-*/	binTree.getNodeAt(1).getX() - 30,
		/*-*/	binTree.getNodeAt(1).getY() - 35);
		/*-*/binTree.redraw();
		/*-*/binTree.highlightLeftRightSubtree(this, left, 
		/*-*/	c_root, right);

		/*-*/frame.waitStep();
		/*-*/frame.Highlight(line + 13);
		if ( c < best_cost ) {
		    /*-*/obsAnim.setOptree(binTree);
		    best_cost = c; /*-*/frame.Highlight(line + 14);
		    best_root = c_root; /*-*/frame.Highlight(line + 15);
		} /*-*/ else {
		    /*-*/obsAnim.discardBinTree();
		/*-*/}
		/*-*/frame.Highlight(line + 16);
		/*-*/obsAnim.hideCom(); 
		/*-*/frame.waitStep();obsAnim.hideText();
	    }
	    /*-*/frame.Highlight(line + 17);
	    /*-*/binTree = obsAnim.moveOpt2Tree();
	    /*-*/obsAnim.setCom("Adding cost and root to matrices...",
	    /*-*/	300, 350);
	    /*-*/binTree.tree2Matrices(left, right);
	    /*-*/frame.waitStep();

	    // Update the cost, best matrices
	    best.setElem(left,right,best_root); /*-*/frame.Highlight(line + 20);
	    cost.setElem(left,right,best_cost); /*-*/frame.Highlight(line + 21);
	    // Add the cost of each key
	    c = 0; /*-*/frame.Highlight(line + 23);
	    for(k=left;k<=right;k++)
		c = c + rel_freq[k];
	    /*-*/frame.Highlight(line + 24);
	    /*-*/frame.Highlight(line + 25);
	    cost.incElem(left,right,c); /*-*/frame.Highlight(line + 26);
	    /*-*/obsAnim.hideCom2();
	    /*-*/cost.setHighlight(left, right);
	    /*-*/best.setHighlight(left, right);
	    /*-*/obsAnim.setCom("Optimal cost for sub-tree: " + 
	    /*-*/	toString(left) +
	    /*-*/	" to " + toString(right),
	    /*-*/	drawingPanel.getOffset(), 
	    /*-*/	drawingPanel.getOffset() + 10 + (right - 1)*vertSpace);
	    /*-*/obsAnim.setCom2(
	    /*-*/	"Root for this optimal " +
	    /*-*/	"subtree is: " + toString(best_root) +
	    /*-*/	", represented by " + best_root + " in root matrix...",
	    /*-*/	drawingPanel.getOffset() + 300, 
	    /*-*/	drawingPanel.getOffset() + 10 + (right - 1)*vertSpace);
	    /*-*/drawingPanel.repaint(); drawingPanel.delay();
	    /*-*/frame.waitStep();
	    /*-*/obsAnim.hideCom(); 
	    /*-*/obsAnim.hideCom2(); 
	    /*-*/for (int l = left; l <= right; l++) 
	    /*-*/	obsAnim.restoreFreq(l);
	    /*-*/cost.restoreHighlight(left, right);
	    /*-*/best.restoreHighlight(left, right);
	    /*-*/drawingPanel.repaint(); drawingPanel.delay();
    	} /*-*/frame.Highlight(line + 27);
	/*-*/frame.Highlight(line + 28);
    }

    public void BuildOptTree(DrawingPanel drawingPanel, AlgAnimFrame frame) { 
	/*-*/int line = 31;
	/*-*/frame.Highlight(line);
	int root;
	// Build all the sub-trees in turn
	cost.setDiag( rel_freq ); /*-*/frame.Highlight(line + 3);
	for(int k=0;k<n;k++)
	    best.setElem( k, k, k );
	for( int k=2; k<=n; k++ ) {
	    this.TreeEvaluate( k );
	    /*-*/frame.waitSkip();
	}
	root = best.elem(0,n-1);
	/*-*/best.setHighlight2(0,n-1);
	/*-*/obsAnim.setCom("Root for the whole tree is: " + root + "...",
	/*-*/	drawingPanel.getOffset() + 280,
	/*-*/	drawingPanel.getOffset() + 10 + (n - 2)*vertSpace);
	/*-*/binTree = new BinTree(obsAnim, drawingPanel, n*2);
	/*-*/obsAnim.setTree(binTree);
	/*-*/binTree.animateInsertNode(drawingPanel.getOffset() + 280,
	/*-*/	drawingPanel.getOffset() + 10 + (n - 2)*vertSpace, 1);
	/*-*/binTree.insertNodeAt(new Node(toString(root),
	/*-*/	rel_freq[root]), 1); binTree.redraw();
	/*-*/printTree( 0, root, n-1, 1 );
    }
//----------------------------------------------------------------
    void printTree( int left, int root, int right, int parentPosn ) {
	frame.waitStep();
	int left_child, right_child, i;
	if ( left < root) {
	    left_child = best.elem( left, root-1 );
	    /*-*/obsAnim.setCom("Left subtree root of  " + root +
	    /*-*/	" is best[" + left + ", " + (root-1) + "] = " + 
	    /*-*/	left_child,
	    /*-*/	drawingPanel.getOffset() + 280 + (left)*horizSpace, 
	    /*-*/	drawingPanel.getOffset() + 10 + (root - 2)*vertSpace);
	    /*-*/best.setHighlight2( left, root-1 ); 
	    /*-*/drawingPanel.repaint(); drawingPanel.delay();
	/*-*/binTree.animateInsertNode(
	    /*-*/	drawingPanel.getOffset() + 280 + (left)*horizSpace, 
	    /*-*/	drawingPanel.getOffset() + 10 + (root - 2)*vertSpace,
			binTree.left(parentPosn));
	    binTree.insertNodeAt(new Node(toString(left_child),
			rel_freq[left_child]), 
			binTree.left(parentPosn));
	    binTree.redraw();
	    printTree( left, left_child, root-1,
			binTree.left(parentPosn) );
	}
	if ( root < right) {
	    right_child = best.elem( root+1, right );
	    /*-*/obsAnim.setCom("Right subtree root of  " + root +
	    /*-*/	" is best[" + (root+1) + ", " + right + "] = "
		/*-*/ + right_child,
	    /*-*/	drawingPanel.getOffset() + 280 + (root)*horizSpace, 
	    /*-*/	drawingPanel.getOffset() + 10 + (right - 2)*vertSpace);
	    /*-*/best.setHighlight2( root+1, right );
	    /*-*/drawingPanel.repaint(); drawingPanel.delay();
	/*-*/binTree.animateInsertNode(
	    /*-*/	drawingPanel.getOffset() + 280 + (root)*horizSpace, 
	    /*-*/	drawingPanel.getOffset() + 10 + (right - 2)*vertSpace,
			binTree.right(parentPosn));
	    binTree.insertNodeAt(new Node(toString(right_child),
			rel_freq[right_child]), 
			binTree.right(parentPosn));
	    binTree.redraw();
	    printTree( root+1, right_child, right,
			binTree.right(parentPosn) );
	}
    }

    String toString(int i) {
	return new String("" + (char)('A' + i));
    }

    void formSubTree( int left, int root, int right, int parentPosn ) {
	int left_child, right_child, i;
	if ( left < root) {
	    left_child = best.elem( left, root-1 );
	    binTree.insertNodeAt(new Node(toString(left_child), 
			rel_freq[left_child]), 
			binTree.left(parentPosn));
	    formSubTree( left, left_child, root-1,
			binTree.left(parentPosn) );
	}
	if ( root < right) {
	    right_child = best.elem( root+1, right );
	    binTree.insertNodeAt(new Node(toString(right_child), 
			rel_freq[right_child]), 
			binTree.right(parentPosn));
	    formSubTree( root+1, right_child, right,
			binTree.right(parentPosn) );
	}
    }

} // class OBSearch
