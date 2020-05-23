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
    public int max_data = 7;
    /**
     * Array of strings used to set the choices of the data choice button
     * in data menu.
     */
    public static String[] dataSets = 
		{"Data Set 1", "Data Set 2", "Data Set 3"};

    int[] data = { 23, 10, 8, 12, 30, 5, 14, 18, 20, 2 };
    String[] label = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

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
        if (choice == 0) {
	    max_data = 7;
        } 
	drawingPanel.init();
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

        // finish sorting
        frame.finishAlg();
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
