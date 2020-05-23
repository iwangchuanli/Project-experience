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
	final Font helv10 = frame.helv10;

	setBackground(Color.white);
        stopButton = new ImageButton("stop", frame.getApplet(), this);
        ((ImageButton)stopButton).setDisable();
        this.add(stopButton);

        runButton = new ImageButton("run", frame.getApplet(), this);
        this.add(runButton);
	
	stepButton = new ImageButton("step", frame.getApplet(), this);
	this.add(stepButton);

	skipButton = new ImageButton("skip", frame.getApplet(), this);
	this.add(skipButton);

	refreshButtons();
    } // ControlPanel()

    void refreshButtons() {
	if (runButton != null)
	    runButton.repaint();
	if (stopButton != null)
	    stopButton.repaint();
	if (stepButton != null)
	    stepButton.repaint();
	if (skipButton != null)
	    skipButton.repaint();
    }

    /**
     * Action handler for the buttons and choice buttons in the control
     * panel.
     * @param e Event invoked
     * @param arg Object that invokes the event
     */
    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
	refreshButtons();
        if (target == runButton) {
	    ((ImageButton)skipButton).setDisable();
	    ((ImageButton)runButton).setDisable();
	    frame.setSkip(false);
	    frame.setText(0, "Running algorithm continuously...");
            frame.startAlg();
        } else if (target == stopButton) {
	    frame.setText(0, "Stop button pressed...");
	    frame.setStep(false);
            if (frame.getAlg().isAlive())
                frame.getAlg().stop();
            frame.finishAlg();
	    ((ImageButton)skipButton).setEnable();
	} else if (target == stepButton) {
	    frame.setText(0, "Executing next step...");
	    frame.setSkip(false);
	    ((ImageButton)skipButton).setEnable();
	    frame.setStep(true);
	    if (!frame.getAlg().isAlive())
		frame.startAlg();
	   // else
		//frame.getAlg().resume();
	    ((ImageButton)stepButton).setDisable();
	} else if (target == skipButton) {
	    frame.setSkip(true);
            frame.startAlg();
	    frame.setText(0, "Skipping to the next phase...");
	    ((ImageButton)skipButton).setDisable();
	} 
	refreshButtons();
	return true;
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
