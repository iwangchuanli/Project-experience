/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * <code>AlgThread</code> is an extension of the provided Java 
 * multi-threading package.
 * It enables the graphical display of the main execution thread to be
 * updated during the execution of the algorithm.
 * <p>
 * A section of this source file is normally displayed on the TextFrame.
 * This section starts with the line after 
 * <code>/*------------------*</code><code>/
 * </code> and finishes before the line <code>//------------
 * </code>.
 * Every line starting with <code>/*-*</code><code>/</code> will be discarded in
 * the source code display area and each line is terminated by the first
 * encounter of <code>/*-*</code><code>/</code>.
 * <p>
 * The source file <code>AlgThread.java</code> is <b>to be
 * modified/completed</b> for separate animation algorithms.
 *
 * @author	Woi L Ang
 * @since	JDK1.0
 */
public class AlgThread extends Thread {
    /**
     * Size of the data set appropriate for algorighm animation.
     */
    public int max_data = 20;
    /**
     * Array of strings used to set the choices of the data choice button
     * in data menu.
     */
    public static String[] dataSets = 
		{"random numbers", "ascending order", 
		 "descending order", "random numbers (random pivot)",
		 "ascending order (random pivot)",
		 "descending order (random pivot)"};

    int[] data = null;
    Stick[] sticks = null;

    /**
     * The initial caller of the constructor of this class.
     * It holds the references to all existing panels.
     */ 
    public AlgAnimFrame frame;
    /**
     * A reference to the drawing panel in order to update the animation
     * during the execution of the algorithm.
     * @see DrawingPanel
     */
    public DrawingPanel drawingPanel;

    /**
     * Creates a new thread using the frame passed in as the parameter.
     * If this constructor is called from the frame constructor,
     * a drawingPanel will be initialized and assigned to the frame
     * class.
     * 
     * @param frame An extended frame where the algorithm is going to 
     * execute in.
     *
     * @see AlgAnimFrame
     */
    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;
	this.drawingPanel = frame.getDrawingPanel();
        if (frame != null && frame.getAlg() != null && 
            frame.getAlg().drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
	    this.data = frame.getAlg().data;
	    this.max_data = frame.getAlg().max_data;
        }
    }

    /**
     * Sets the delay between each animation step. This determines
     * the rate the drawingPanel is updated. The <code>setDelay</code>
     * method is normally called by the action listener of
     * the <code>delay choice button</code> on the control panel.
     * @param delay The delay set in milliseconds.
     */
    public void setDelay(int delay) {
	drawingPanel.setDelay(delay);
    }

    /**
     * Generate data based on the choice made on the menu.
     * This method is application specific
     * and the contents for the satisfaction of each 'if' statement
     * have to be filled in based on the algorithm.
     */
    public void generateData() {
	this.drawingPanel = frame.getDrawingPanel();
        int choice = frame.getDataChoice();
        if (choice == 0 || choice == 3) {
	    max_data = 20;
	    data = new int[max_data];
	    generateRandomData(max_data);
        } else if (choice == 1 || choice == 4) {
	    max_data = 20;
	    data = new int[max_data];
	    generateAscendingData(max_data);
	} else if (choice == 2 || choice == 5) {
	    max_data = 20;
	    data = new int[max_data];
	    generateDescendingData(max_data);
	}
	initData();
    }

    public int getRandom(int from, int to) {
	return new Double(Math.random()*(to-from)).intValue() + from;
    }

    public void generateRandomData(int n) {
	for (int i = 0; i < n; i++)
	    data[i] = new Double(Math.random()*40).intValue() + 10;
    }

    public void generateAscendingData(int n) {
	for (int i = 0; i < n; i++)
	    data[i] = i*2 + 9;
    }

    public void generateDescendingData(int n) {
	for (int i = 0; i < n; i++)
	    data[i] = 49 - i*2;
    }

    private void initData() {
	drawingPanel.init();
	sticks = new Stick[max_data];
	for (int i = 0; i < max_data; i++) {
	    sticks[i] = new Stick(data[i], i*18 + 50, 20);
	    drawingPanel.addDrawingObj(sticks[i]);
	}
	drawingPanel.addDrawingObj(
	    new Legend(
		(drawingPanel.getPanelWidth()>0?
			drawingPanel.getPanelWidth():840) - 105, 5));
	drawingPanel.repaint(); drawingPanel.delay();
    }

    private void reorderStick() {
	for (int i = 0; i < max_data; i++) {
	    sticks[i].move(i*18 + 50, 20);
	    if (sticks[i].getColor() == Color.lightGray)
		sticks[i].setColor(Color.red);
	}
	drawingPanel.redraw();
    }
/*--------------------------------------------------------------------*/
    private void quicksort(int from, int to) { /*-*/int line = 0;
	/*-*/frame.Highlight(line);
	/*-*/int leftMost = sticks[from].getX();
	/*-*/int rightMost = sticks[to].getX();
	/*-*/if (from > 0) {leftMost += 10; rightMost += 10;}
	int pivot = from;
	/*-*/frame.Highlight(line + 1);
	if (frame.getDataChoice() > 2) {
	    /*-*/frame.Highlight(line + 2);
	    /*-*/frame.Highlight(line + 3);
	    pivot = getRandom(from, to); }
	/*-*/animatePivot(pivot, from, to);
	int pivotValue = data[pivot];
	/*-*/frame.Highlight(line + 4);
	Vector leftPartition = new Vector(), rightPartition = new Vector();
	/*-*/frame.Highlight(line + 5);
	/*-*/Vector leftIndex = new Vector(), rightIndex = new Vector();
	/*-*/ComBox generalCom = new ComBox(sticks[to].getX() + 20, 180,
	/*-*/	"Comparing each entry in the partition with the pivot...",
	/*-*/	drawingPanel.getFixFont());
	/*-*/drawingPanel.addCom(generalCom);
	/*-*/drawingPanel.redraw();
	/*-*/frame.Highlight(line + 6);
	for (int i = from; i <= to; i++) {
	    /*-*/frame.Highlight(line + 7);
	    if (i != pivot) {
		/*-*/frame.Highlight(line + 8);
		if (data[i] < pivotValue) {
		    leftPartition.addElement(new Integer(data[i]));
		    /*-*/frame.Highlight(line + 9);
		    /*-*/animateAddLeft(i, pivot, leftIndex, leftMost);
		    /*-*/leftIndex.addElement(new Integer(i));
		} else {
		    /*-*/frame.Highlight(line + 10);
		    rightPartition.insertElementAt(new Integer(data[i]), 0);
		    /*-*/frame.Highlight(line + 11);
		    /*-*/animateAddRight(i, pivot, rightIndex, rightMost);
		    /*-*/rightIndex.insertElementAt(new Integer(i), 0);
		}
		/*-*/frame.Highlight(line + 13);
	    }
	    /*-*/frame.Highlight(line + 14);
	}
	/*-*/drawingPanel.removeCom(generalCom);

	/*-*/Stick[] savSticks = new Stick[max_data];
	/*-*/for (int i = from; i <= to; i++) savSticks[i] = sticks[i];
	/*-*/frame.Highlight(line + 16);
	for (int i = from; i <= to; i++)
	    if ( (i-from) < leftPartition.size()) {
		/*-*/frame.Highlight(line + 17);
		data[i] = 
		    ((Integer)leftPartition.elementAt(i-from)).intValue();
		/*-*/frame.Highlight(line + 19);
	/*-*/sticks[i] =
	/*-*/    savSticks[((Integer)leftIndex.elementAt(i-from)).intValue()];
	    } else if ( (i-from) == leftPartition.size()) {
		/*-*/frame.Highlight(line + 20);
		data[i] = pivotValue;
		/*-*/frame.Highlight(line + 21);
	/*-*/sticks[i] = savSticks[pivot];
	    } else {
		/*-*/frame.Highlight(line + 22);
		data[i] = 
		    ((Integer)rightPartition.elementAt(
			i-from-leftPartition.size()-1)).intValue();
		/*-*/frame.Highlight(line + 25);
	/*-*/sticks[i] =
	/*-*/    savSticks[((Integer)rightIndex.elementAt(
	/*-*/		i-from-leftIndex.size()-1)).intValue()];
	    }

	/*-*/animateMovePartUp(from, to, leftPartition.size() + from);
	/*-*/reorderStick();
	/*-*/frame.waitSkip();
	/*-*/frame.Highlight(line + 28);
	if (leftPartition.size() > 1)
	    quicksort(from, from + leftPartition.size()-1);
	/*-*/else if (leftPartition.size() == 1) {
	/*-*/	sticks[from].setColor(Color.green);
	/*-*/	ComBox com = new ComBox(sticks[from].getX(),
	/*-*/	    sticks[from].getY() + sticks[from].getHeight()*3 + 2,
	/*-*/	    "Single entry in left partition: " +
	/*-*/		 sticks[from].getHeight() +
	/*-*/	    " --> sorted", drawingPanel.getFixFont());
	/*-*/	drawingPanel.addCom(com);
	/*-*/	drawingPanel.redraw();
	/*-*/	frame.waitStep();
	/*-*/	drawingPanel.removeCom(com);
	/*-*/}
	/*-*/frame.Highlight(line + 30);
	if (rightPartition.size() > 1)
	    quicksort(leftPartition.size() + 1 + from, 
		leftPartition.size() + from + rightPartition.size());
	/*-*/else if (rightPartition.size() == 1) {
	/*-*/	sticks[to].setColor(Color.green);
	/*-*/	ComBox com = new ComBox(sticks[to].getX(),
	/*-*/	    sticks[to].getY() + sticks[to].getHeight()*3 + 2,
	/*-*/	    "Single entry in right partition: " + 
	/*-*/		sticks[to].getHeight() +
	/*-*/	    " --> sorted", drawingPanel.getFixFont());
	/*-*/	drawingPanel.addCom(com);
	/*-*/	drawingPanel.redraw();
	/*-*/	frame.waitStep();
	/*-*/	drawingPanel.removeCom(com);
	/*-*/}
    }

//----
    /**
     * This method is invoked when the <code>Thread</code> class
     * <code>start()</code> method is called.
     * It contains the statements to execute the methods which perform
     * the algorithm. This method is to be completed based on separate
     * animation algorithms.
     * @see java.lang.Thread
     */
    public void run() {
	initData();
	// alg here
	quicksort(0, max_data-1);

        // finish sorting
        frame.finishAlg();
frame.setText(0, "Animation completed successfully. Press 'run' to restart.");
    }

    /**
     * Restore the drawing panel at the beginning or the end of each
     * simulation.
     */
    public void restoreDrawingPanel() {
    }

    /**
     * This method is to be placed after the line where the fast
     * forward function is to be performed.
     */
    public void waitSkip() {
	if (!drawingPanel.getSkip()) return;
	((ImageButton)frame.getSkipItem()).setEnable();
	((ImageButton)frame.getRunItem()).setEnable();
	((ImageButton)frame.getStopItem()).setDisable();
	drawingPanel.setSkip(false);
	frame.setStep(true);
	reorderStick();
	frame.waitStep();
    }

//--- Animation methods ---
    private void animatePivot(int pivot, int from, int to) {
	ComBox com1 = new ComBox(sticks[from].getX() - 20,
		sticks[from].getY() + sticks[from].getHeight()*3 + 2,
		"Now processing this partition",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com1);
	drawingPanel.redraw();
	for (int k = 0; k < 5; k++) {
	    for (int i = from; i <= to; i++)
		sticks[i].setColor(Color.black);
	    drawingPanel.redraw();
	    for (int i = from; i <= to; i++)
		sticks[i].setColor(Color.red);
	    drawingPanel.redraw();
	}
	drawingPanel.removeCom(com1);
	
	if (to < max_data-1) {
	    for (int i = to+1; i < max_data; i++) {
		sticks[i].move(sticks[i].getX() + 10, sticks[i].getY());
		if (sticks[i].getColor() == Color.red)
		    sticks[i].setColor(Color.lightGray);
	    }
	    drawingPanel.redraw();
	}

	if (from > 0) {
	    for (int i = from; i < max_data; i++)
		sticks[i].move(sticks[i].getX() + 10, sticks[i].getY());
	    for (int i = 0; i < from; i++)
		if (sticks[i].getColor() == Color.red)
		    sticks[i].setColor(Color.lightGray);
	    drawingPanel.redraw();
	}

	Stick pivotStick = sticks[pivot];
	pivotStick.setColor(Color.blue);
	drawingPanel.redraw();

	ComBox com = new ComBox(pivotStick.getX() - 20,
		pivotStick.getY() + pivotStick.getHeight()*3 + 10,
		"Pivot set to " + pivotStick.getHeight() + "...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.redraw();
	frame.waitStep();
	drawingPanel.removeCom(com);
    }

    private void animateAddLeft(int i, int pivot, 
		Vector leftIndex, int leftMost) {
	Stick pivotStick = sticks[pivot];
	Stick leftStick = sticks[i];

	int destX = leftMost + leftIndex.size()*18;
	int destY = 340;

	Vector traj = new Vector();
	traj.addElement(new Point(leftStick.getX(), leftStick.getY()));
	traj.addElement(new Point(leftStick.getX(), leftStick.getY() + 160));
	
	int posn1 = pivotStick.getX();
	traj.addElement(new Point(posn1, leftStick.getY() + 160));
	traj.addElement(new Point(posn1, 
		pivotStick.getY() + pivotStick.getHeight()*3 + 5));
	drawingPanel.setAnimStep(3);
	drawingPanel.animate(leftStick, traj);

	ComBox com = new ComBox(leftStick.getX() + 20,
		leftStick.getY() - 15,
		"Smaller than pivot -> add to left partition...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.redraw();

	traj = new Vector();
	traj.addElement(new Point(leftStick.getX(), leftStick.getY()));
	traj.addElement(new Point(posn1, 180));
	traj.addElement(new Point(destX, 180));
	traj.addElement(new Point(destX, destY));
	drawingPanel.setAnimStep(3);
	drawingPanel.animate(leftStick, traj);

	drawingPanel.removeCom(com);
	frame.waitStep();
    } // animateAddLeft()

    private void animateAddRight(int i, int pivot, 
		Vector rightIndex, int rightMost) {
	Stick pivotStick = sticks[pivot];
	Stick rightStick = sticks[i];

	int destX = rightMost - rightIndex.size()*18;
	int destY = 340;

	Vector traj = new Vector();
	traj.addElement(new Point(rightStick.getX(), rightStick.getY()));
	traj.addElement(new Point(rightStick.getX(), rightStick.getY() + 160));
	
	int posn1 = pivotStick.getX();
	traj.addElement(new Point(posn1, rightStick.getY() + 160));
	traj.addElement(new Point(posn1, 
		pivotStick.getY() + pivotStick.getHeight()*3 + 5));
	drawingPanel.setAnimStep(3);
	drawingPanel.animate(rightStick, traj);

	ComBox com = new ComBox(rightStick.getX() + 20,
		rightStick.getY() - 15,
		"Greater than (or equal to) pivot -> add to right partition...",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.redraw();

	traj = new Vector();
	traj.addElement(new Point(rightStick.getX(), rightStick.getY()));
	traj.addElement(new Point(posn1, 180));
	traj.addElement(new Point(destX, 180));
	traj.addElement(new Point(destX, destY));
	drawingPanel.setAnimStep(3);
	drawingPanel.animate(rightStick, traj);

	drawingPanel.removeCom(com);
	frame.waitStep();
    }

    private void animateMovePartUp(int from, int to, int pivot) {
	sticks[pivot].setColor(Color.green);
	int destX;
	if (pivot > from)
	    destX = sticks[pivot-1].getX() + 18;
	else
	    destX = sticks[pivot+1].getX() - 18;

	ComBox com = new ComBox(destX + 20, 300,
		"Moving the pivot into the empty slot between two partition..",
		drawingPanel.getFixFont());
	ComBox com2 = new ComBox(com.getX() + 10, com.getY() - 25,
		"Pivot '" + sticks[pivot].getHeight() + "' is now sorted..",
		drawingPanel.getFixFont());
	drawingPanel.addCom(com);
	drawingPanel.addCom(com2);
	drawingPanel.repaint();

	Vector traj = new Vector();
	traj.addElement(new Point(sticks[pivot].getX(), sticks[pivot].getY()));
	traj.addElement(new Point(destX, sticks[pivot].getY()));
	traj.addElement(new Point(destX, 340));
	drawingPanel.setAnimStep(4);
	drawingPanel.animate(sticks[pivot], traj);

	drawingPanel.removeCom(com);
	drawingPanel.removeCom(com2);

	for (int i = from; i <= to; i++)
	    sticks[i].move(sticks[i].getX(), 240);
	drawingPanel.redraw();
	for (int i = from; i <= to; i++)
	    sticks[i].move(sticks[i].getX(), 120);
	drawingPanel.redraw();
	for (int i = from; i <= to; i++)
	    sticks[i].move(sticks[i].getX(), 20);
	drawingPanel.redraw();
	frame.waitStep();
    }
}
