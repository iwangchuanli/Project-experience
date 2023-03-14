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
    /**
     * Reference point to window frame which contains this panel.
     * @see AlgAnimFrame
     */
    public AlgAnimFrame frame;
    /**
     * Run button to initiate the animation of the algorithm.
     */
    public Button run_button; 
    /**
     * Stop button to terminate the execution of algorithm.
     */
    public Button stop_button;
    /**
     * Step button to trace through the execution of the algorithm.
     * If the <code>Run</code> button has already been pressed, the
     * execution mode will be switched from <code>RUN</code> to
     * <code>STEP</code>.
     */
    public Button step_button;
    /**
     * Quit button to dispose the current popped up frame and
     * enable the applet button which brought up this frame.
     */
    public Button quit_button;
    Button noAnim_button;
    /**
     * Choices of data set, determined by the array of string 
     * <code>data_set</code> from the <code>AlgThread</code> class.
     * @see AlgThread
     */
    public Choice data_choice;
    /**
     * Choices of animation delay to cope with the slower CPU speed of
     * some machines.
     */
    public Choice delay_choice;
    /**
     * Record the current data set selected from the choice button
     */
    private int data_selected = 0;
    /**
     * Message display area showing the current status of the control
     * panel.
     */
    public TextField mesg;

    boolean noAnim = false;

    /**
     * Creates a panel to hold the control buttons of the animation
     * tool.
     * @param frame The parent window frame that contains this panel
     * @param algname The algorithm name parsed from the applet parameter
     */
    public ControlPanel(AlgAnimFrame frame, String algname) {
	setBackground( Color.white );
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gc = new GridBagConstraints();
	setLayout(gb);
	gc.fill = GridBagConstraints.BOTH;
	gc.weightx = 1.0;
	
        this.frame = frame;

	gc.insets = new Insets(5, 5, 5, 25);
        run_button = new Button("Run ");
	gb.setConstraints(run_button, gc);
        this.add(run_button);
	
	gc.insets = new Insets(5, 5, 5, 5);
	step_button = new Button("Next Step");
	gb.setConstraints(step_button, gc);
	this.add(step_button);

        stop_button = new Button("Stop");
        stop_button.disable();
	gb.setConstraints(stop_button, gc);
        this.add(stop_button);

        data_choice = new Choice();
        // assigning data sets
        for (int i = 0; i < frame.alg.dataSets.length; i++)
            data_choice.addItem(frame.alg.dataSets[i]);
	//data_choice.select(1);
	gc.insets = new Insets(5, 5, 5, 20);
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gb.setConstraints(data_choice, gc);
	this.add(data_choice);
	
	gc.insets = new Insets(5, 5, 5, 5);
	gc.gridwidth = 1;
	noAnim_button = new Button(" Anim OFF ");
	gb.setConstraints(noAnim_button, gc);
	this.add(noAnim_button);

	delay_choice = new Choice();
	delay_choice.addItem("Delay 200msec");
	delay_choice.addItem("Delay 400msec");
	delay_choice.addItem("Delay 600msec");
	delay_choice.addItem("Delay 800msec");
	delay_choice.addItem("Delay 1000msec");
	delay_choice.select(0);
	gc.insets = new Insets(5, 5, 5, 5);
	gc.gridwidth = GridBagConstraints.RELATIVE;
	gb.setConstraints(delay_choice, gc);	
	this.add(delay_choice);

	quit_button = new Button("Quit");
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gc.insets = new Insets(5, 40, 5, 10);
	gb.setConstraints(quit_button, gc);
	this.add(quit_button);
	
	mesg = new TextField(50);
	mesg.setEditable(false);
	gc.insets = new Insets(5, 5, 5, 5);
	gb.setConstraints(mesg, gc);
	this.add(mesg);
    } // ControlPanel()

    /**
     * Action handler for the buttons and choice buttons in the control
     * panel.
     * @param e Event invoked
     * @param arg Object that invokes the event
     */
    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
        if (target == run_button) {
	    setText("Running algorithm continuously...");
            frame.startAlg();
            return true;
        } else if (target == stop_button) {
	    setText("Stop button pressed...");
	    frame.setStep(false);
            if (frame.alg.isAlive())
                frame.alg.stop();
            frame.finishAlg();
            return true;
	} else if (target == step_button) {
	    setText("Executing next step...");
	    frame.setStep(true);
	    if (!frame.alg.isAlive())
		frame.startAlg();
	   // else
		//frame.alg.resume();
	    return true;
	} else if (target == noAnim_button) {
	    if (noAnim) {
	    	noAnim_button.setLabel(" Anim OFF ");
		noAnim = false;
	    } else {
	    	noAnim_button.setLabel(" Anim ON ");
		noAnim = true;
	    }
	    frame.alg.drawingPanel.setNoAnim(noAnim);
	} else if (target == quit_button) {
	    frame.parentApp.start_button.enable();
	    frame.tf.dispose();
	    frame.dispose();
        } else if (target instanceof Choice) {
	    if (target == data_choice) {
		data_selected = data_choice.getSelectedIndex();
		frame.alg.generateData();
		//setText("Data generated...");
	    } else if (target == delay_choice) {
		//System.out.println("delay");
		frame.delay = (delay_choice.getSelectedIndex() + 1) * 200;
		frame.alg.setDelay(frame.delay);
		setText("Animation delay now set to " + frame.delay + " msec...");
	    }
        }
        return false;
    } // action()

    /**
     * Returns the current choice of the data_choice button.
     */
    public int getDataChoice() {
        return data_selected;
    }

    /**
     * Sets a text string in the message display area.
     * @param str The string to be displayed
     */
    public void setText(String str) {
	mesg.setText(str);
    }

    public void paint(Graphics g) {
	Dimension d = size();
	g.setColor( Color.black );
	g.drawRect( 1, 0, d.width-2, d.height-1 );
    }
} // class ControlPanel
