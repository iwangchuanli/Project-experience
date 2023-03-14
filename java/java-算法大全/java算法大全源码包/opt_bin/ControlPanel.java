/* ControlPanel class */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class extends the <code>java.awt.Panel</code> class to form
 * a panel which holds the buttons/choices/labels that control
 * the animation of the algorithm.
 *
 * @see Panel
 */
public class ControlPanel extends Panel {
    private AlgAnimFrame frame;
    private Button runButton; 
    private Button stopButton;
    private Button stepButton, skipButton;
    private Font font;

    /**
     * Creates a panel to hold the control buttons of the animation
     * tool.
     * @param frame The parent window frame that contains this panel
     * @param algname The algorithm name parsed from the applet parameter
     */
    public ControlPanel(AlgAnimFrame frame, String algname) {
	setBackground( Color.lightGray );
	setLayout( new FlowLayout(FlowLayout.LEFT) );
        this.frame = frame;

        stopButton = new Button("       ") {
	    public void paint(Graphics g) {
		Dimension d = size();
		if (isEnabled())
		    g.setColor(Color.black);
		else
		    g.setColor(Color.gray);
		g.fillRect(d.width/3, 6, d.width/3, d.height - 12);
		g.setColor(Color.gray);
		g.drawRect(d.width/3, 6, d.width/3, d.height - 12);
	    }
	};
        stopButton.disable();
        this.add(stopButton);

        runButton = new Button("       ") {
	    public void paint(Graphics g) {
		Dimension d = size();
		int[] xPts = new int[3], yPts = new int[3];
		xPts[0] = xPts[1] = d.width/3;
		xPts[2] = 2*d.width/3;
		yPts[0] = 5; yPts[1] = d.height - 5;
		yPts[2] = d.height/2;
		if (isEnabled())
		    g.setColor(Color.black);
		else
		    g.setColor(Color.gray);
		g.fillPolygon(xPts, yPts, 3);
		g.setColor(Color.gray);
		g.drawPolygon(xPts, yPts, 3);
	    }
	};
        this.add(runButton);
	
	stepButton = new Button("       ") {
	    public void paint(Graphics g) {
		Dimension d = size();
		int[] xPts = new int[3], yPts = new int[3];
		xPts[0] = xPts[1] = d.width/3 + 1;
		xPts[2] = 2*d.width/3 + 1;
		yPts[0] = 5; yPts[1] = d.height - 5;
		yPts[2] = d.height/2;
		if (isEnabled())
		    g.setColor(Color.black);
		else
		    g.setColor(Color.gray);
		g.fillPolygon(xPts, yPts, 3);
		g.fillRect(d.width/3 - 2, 5, 2, d.height - 9);
		g.setColor(Color.gray);
		g.drawPolygon(xPts, yPts, 3);
	    }
	};
	this.add(stepButton);

	skipButton = new Button("       ") {
	    public void paint(Graphics g) {
		Dimension d = size();
		int[] xPts = new int[3], yPts = new int[3];
		xPts[0] = xPts[1] = d.width/4;
		xPts[2] = d.width/2;
		yPts[0] = 5; yPts[1] = d.height - 5;
		yPts[2] = d.height/2;
		if (isEnabled())
		    g.setColor(Color.black);
		else
		    g.setColor(Color.gray);
		g.fillPolygon(xPts, yPts, 3);
		g.setColor(Color.gray);
		g.drawPolygon(xPts, yPts, 3);
		xPts[0] = xPts[1] = d.width/2;
		xPts[2] = 3*d.width/4;
		yPts[0] = 5; yPts[1] = d.height - 5;
		yPts[2] = d.height/2;
		if (isEnabled())
		    g.setColor(Color.black);
		else
		    g.setColor(Color.gray);
		g.fillPolygon(xPts, yPts, 3);
		g.setColor(Color.gray);
		g.drawPolygon(xPts, yPts, 3);
	    }
	};
	this.add(skipButton);

    } // ControlPanel()

    /**
     * Action handler for the buttons and choice buttons in the control
     * panel.
     * @param e Event invoked
     * @param arg Object that invokes the event
     */
    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
        if (target == runButton) {
	    skipButton.enable();
	    frame.setSkip(false);
	    frame.setText(0, "Running algorithm continuously...");
            frame.startAlg();
	    skipButton.disable();
            return true;
        } else if (target == stopButton) {
	    frame.setText(0, "Stop button pressed...");
	    frame.setStep(false);
            if (frame.getAlg().isAlive())
                frame.getAlg().stop();
            frame.finishAlg();
	    skipButton.enable();
            return true;
	} else if (target == stepButton) {
	    frame.setText(0, "Executing next step...");
	    frame.setSkip(false);
	    skipButton.enable();
	    frame.setStep(true);
	    if (!frame.getAlg().isAlive())
		frame.startAlg();
	   // else
		//frame.getAlg().resume();
	    return true;
	} else if (target == skipButton) {
            frame.startAlg();
	    frame.setText(0, "Skipping to the next phase...");
	    frame.setSkip(true);
	    skipButton.disable();
	    return true;
	} 
        return false;
    } // action()

    /**
     * @return Run button to initiate the animation of the algorithm.
     */
    public Button getRunButton() {
	return runButton;
    }

    /**
     * @return Stop button to terminate the execution of algorithm.
     */
    public Button getStopButton() {
	return stopButton;
    }

    /**
     * @return Step button to trace through the execution of the algorithm.
     * If the <code>Run</code> button has already been pressed, the
     * execution mode will be switched from <code>RUN</code> to
     * <code>STEP</code>.
     */
    public Button getStepButton() {
	return stepButton;
    }

    /**
     * @return Skip button to bypass the animation of the algorithm.
     */
    public Button getSkipButton() {
	return skipButton;
    }
} // class ControlPanel
