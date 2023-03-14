/* AlgAnimFrame.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * The <code>AlgAnimFrame</code> class is a top-level window with a title
 * and border. The layout used is GridBagLayout.
 * <p>
 * This extended frame class holds a set references to the panel/frame objects
 * used in the algorithm animation.
 * @see Frame
 */
public class AlgAnimFrame extends Frame {
    AlgAnimApp parentApp;
    URL sourceURL;
    String algname;
    /**
     * The reference to the AlgThread which contains the details and
     * execution sequences of the algorithm.
     * @see AlgThread
     */
    public AlgThread alg = null;
    int delay = 200;

    /**
     * An instance of the drawing panel which is cast to its super class
     * <code>Panel</code>. This instance is used to set the GridBagConstraint
     * of the layout manager.
     * @see DrawingPanel
     */
    public Panel drawingPanel;
    /**
     * An instance of the <code>TextFrame</code> used to set the layout
     * constraints and highlight certain lines of the source code.
     * @see TextFrame
     */
    public TextFrame tf;
    /**
     * A reference to the control panel.
     */
    public ControlPanel control_panel;
    boolean step = false, stepWait = false;
    public ComPanel cpanel;

    /**
     * Creates and shows the frame consists of a drawing panel, commentary
     * panel and control panel. The text frame is now displayed on a separate
     * window.
     * @param parentApp The applet which results in the creation of this frame
     * @param sourceURL The URL of the source code to be displayed on the text
     * frame
     * @see URL
     */
    public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {
        this.parentApp = parentApp;
        this.sourceURL = sourceURL;
        this.algname = parentApp.getParameter("algname");
	setBackground(Color.white);

        setTitle(algname);
	move(0, 0);
        alg = new AlgThread(this);

        // Arrange panels within frame
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        setLayout( gb );
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        /* Give any extra space to the animation panel */
        gc.weighty = 1.0;
        gc.weightx = 2.0;
        gc.gridx = 0; gc.gridy = 0;
        gb.setConstraints( drawingPanel, gc );
        add(drawingPanel);

        /* Source panel */
        tf = new TextFrame( sourceURL );
	
        /* commentary panel */
        gc.gridwidth = GridBagConstraints.RELATIVE;
        gc.gridx = 0; gc.gridy = GridBagConstraints.RELATIVE;
        gc.weighty = 0.0;
        gc.gridheight = GridBagConstraints.REMAINDER;
        cpanel = new ComPanel( algname, 3 );
        gb.setConstraints( cpanel, gc );
        add( cpanel );
 
        /* Control panel */
        control_panel = new ControlPanel(this, algname);
        gc.gridx = gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = GridBagConstraints.REMAINDER;
	gc.insets = new Insets(0, 2, 2, 2);
        gb.setConstraints( control_panel, gc );
        add( control_panel );

        // generate initial data set
        alg.generateData();

        pack();
        validate();
        show();
	if (tf.tp.n_lines > 0)
	    tf.toFront();
    } // init()

    /**
     * Returns the preferred size of the frame. By default, it is set
     * to 920x600. It can be modified based on the specific application.
     * @return the dimension of the frame
     */
    public Dimension preferredSize() {
        return new Dimension(800,600);
    }

    public Dimension minimumSize() {
	return new Dimension(800,600);
    }

    /**
     * Event handler of the frame. The main purpose of this method is to
     * perform the cleanup statements upon receival of the WINDOW_DESTROY
     * event message.
     */
    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            if (alg != null) {
              alg.drawingPanel = null;
              if (alg.isAlive())
                alg.stop();
            }
            parentApp.start_button.enable();
	    tf.dispose();
            dispose();
        } 
        return super.handleEvent(event);
    }

    public void setText(int n, String s) {
	cpanel.setText(n, s);
    }

    /**
     * Highlights the specified line of the source code on the text panel.
     * If the line is beyond the scroll pane, it will be scrolled to the
     * center of the window.
     * @param n The line to be highlighted.
     */
    public void Highlight( int n ) {
        tf.tp.Highlight(n);
	int numLineVisible = tf.tp.size().height/tf.tp.line_space;
	
	if ( (n < (tf.tp.start + 2)) || 
		(n > (tf.tp.start + numLineVisible - 2))) {
	    int max = tf.vScrollbar.getMaximum();
	    int min = tf.vScrollbar.getMinimum();
	    int startLine = n - numLineVisible/2;
	    if (startLine > 0) {
	    	tf.tp.setStart(startLine);
	    	tf.vScrollbar.setValue(startLine * (max - min) / tf.tp.n_lines);
	    } else {
	    	tf.tp.setStart(0);
	    	tf.vScrollbar.setValue(0);
	    }
	}
	try {
	    Thread.sleep(delay/4);
	} catch (InterruptedException e) {}
    }

    /**
     * Restore the drawing panel at the end of the animation or during
     * initialization.
     */
    public void restoreDrawingPanelColor() {
        alg.restoreDrawingPanelColor();
    }

    /**
     * Start the animation algorithm if the <b>run</b> or <b>step</b>
     * button is pressed.
     */
    public void startAlg() {
	if (alg.isAlive() && !stepWait) {
	    alg.stop();
	    alg = new AlgThread(this);
	    alg.start();
	} else if (alg.isAlive() && stepWait) {
	    //alg.resume();
	    //System.out.println("alive");
	    step = true;
	    stepWait = false;
	} else { // alg.isAlive() == false
	    alg = new AlgThread(this);
	    alg.start();
	}
	if (!stepWait) {
	    control_panel.run_button.disable();
	    control_panel.data_choice.disable();
	    control_panel.stop_button.enable();
	    control_panel.step_button.enable();
	}
    }

    /**
     * This method is invoked at the end of the animation or when
     * the <b>stop</b> button is pressed. It restores the buttons
     * status on the control panel.
     */
    public void finishAlg() {
        control_panel.stop_button.disable();
        control_panel.run_button.enable();
        control_panel.data_choice.enable();
	control_panel.step_button.enable(); step = false;
        alg.restoreDrawingPanelColor();
    }
    
    /**
     * This method is called when the <b>step</b> execution mode is used.
     * It is normally added to the line where the execution will wait
     * for the <b>step</b> button to be pressed.
     */
    public void waitStep() {
	if (step) {
	    control_panel.setText("Click NEXT STEP...");
	    control_panel.step_button.enable();
	    control_panel.repaint();
	    step = false; stepWait = true;
	    while (!step) {
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {}
	    }
	    if (!stepWait)
		step = false;
	}
    }
    
    /**
     * Sets the attribute which indicate if the <b>step</b> execution
     * mode is current.
     */
    public void setStep(boolean step) {
	//setText(2, "Stepping through the next line(s)...");
	this.step = step;
        control_panel.stop_button.disable();
        control_panel.run_button.enable();
        control_panel.data_choice.enable();
	if (step) {
	    control_panel.step_button.disable();	
	    stepWait = true;
	} else 
	    stepWait = false;
    }
} // class AlgAnimApp
