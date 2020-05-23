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
    public int max_data = 6;
    /**
     * Array of strings used to set the choices of the data choice button
     * in data menu.
     */
    public static String[] dataSets = 
		{"Data Set 1"};

    Dimension[] data;
    OptMatMult optMatMult;

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
	drawingPanel.init();
        int choice = frame.getDataChoice();
        if (choice == 0) {
	    max_data = 6;
	    data = new Dimension[max_data];
	    data[0] = new Dimension(100, 10); // col row
	    data[1] = new Dimension(5, 100);
	    data[2] = new Dimension(50, 5);
	    data[3] = new Dimension(70, 50);
	    data[4] = new Dimension(7, 70);
	    data[5] = new Dimension(10, 7);
	    initData(data);
        } else if (choice == 1) {
	    max_data = 3;
	    data = new Dimension[max_data];
	    data[0] = new Dimension(100, 10); // col row
	    data[1] = new Dimension(5, 100);
	    data[2] = new Dimension(50, 5);
	    initData(data);
	}
    }

    private void initData(Dimension[] dim) {
	// show matrix dimensions
	IntMatrix dimMat = new IntMatrix(dim.length, 2);
	dimMat.move(20, 20);
	dimMat.setColor(Color.gray, Color.white);
	String[] colLabels = {"Rows  ", "Columns"};
	dimMat.setColLabels(colLabels);
	Subscript[] subs = new Subscript[dim.length];
	for (int i = 0; i < dim.length; i++) {
	    subs[i] = new Subscript("   A", ""+i);
	    subs[i].setColor(Color.white);
	    dimMat.setElem(0, i, dim[i].height);
	    dimMat.setElem(1, i, dim[i].width);
	}
	dimMat.setRowLabels(subs);
	drawingPanel.addDrawingObj(dimMat);
	// label for best Matrix
	ShadowLabel dimLabel = new ShadowLabel("Dimension of Matrices");
	dimLabel.move(30 + 3*66, 20 + 19*(dim.length/2));
        drawingPanel.addDrawingObj(dimLabel);

	// show Cost matrix
	IntMatrix costMat = new IntMatrix(dim.length);
	costMat.blankAboveDiag();
	costMat.setColor(Color.blue, Color.white);
	costMat.move(20, 20 + (dim.length + 2)*19);
	costMat.setRowLabels(subs);
	costMat.setColLabels(subs);
	drawingPanel.addDrawingObj(costMat);
	// label for cost matrix
	ShadowLabel costLabel = new ShadowLabel("Cost Matrix");
	costLabel.move(30 + (dim.length + 1)*66, 
		20 + (dim.length + 2 + dim.length/2)*19);
	costLabel.setColor(Color.blue);
	drawingPanel.addDrawingObj(costLabel);

	// show Best matrix
	IntMatrix bestMat = new IntMatrix(dim.length);
	bestMat.blankAboveDiag();
	bestMat.setColor(Color.red, Color.white);
	bestMat.move(20, 20 + 2*(dim.length + 2)*19);
	bestMat.setRowLabels(subs);
	bestMat.setColLabels(subs);
	drawingPanel.addDrawingObj(bestMat);
	// label for best Matrix
	ShadowLabel bestLabel = new ShadowLabel("Best Matrix");
	bestLabel.move(30 + (dim.length + 1)*66,
                20 + (2*(dim.length + 2) + dim.length/2)*19);
	bestLabel.setColor(Color.red);
        drawingPanel.addDrawingObj(bestLabel);

	// Representation note
	ShadowLabel noteLabel =
	    new ShadowLabel("Each entry in the BEST MATRIX represents " +
		"the index of the last matrix in the left parenthesisation.");
	noteLabel.setColor(Color.red);
	noteLabel.move(20, 20+3*(dim.length + 2)*19);
	drawingPanel.addDrawingObj(noteLabel);

	optMatMult = new OptMatMult(dim, costMat, bestMat, drawingPanel,
			frame, dimMat);
	drawingPanel.repaint();
    }

/*--------------------------------------------------------------------*/
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
	generateData();
	// alg here
	optMatMult.start();

        // finish sorting
        frame.finishAlg();
frame.setText(0, "Execution completed successfully... Click 'run' to restart.");
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
	frame.waitStep();
    }
}
