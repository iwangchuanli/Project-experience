/* AlgAnimFrame.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class AlgAnimFrame extends Frame {
    AlgAnimApp parentApp;
    URL sourceURL;
    String algname;
	 String algfile; //merv
	 String filename; //merv
    AlgThread alg = null;

    Panel drawingPanel;
    TextFrame tf;
    ComPanel cpanel;
    ControlPanel control_panel;
	 ExtraFrame extraframe;
    boolean step = false, stepWait = false;
    int delay = 200;

	 
    public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {
	setBackground(Color.white);
        this.parentApp = parentApp;
        this.sourceURL = sourceURL;
        this.algname = parentApp.getParameter("algname");
		  this.algfile = parentApp.getParameter("algfile");//merv	
        this.filename = parentApp.getParameter("filename");//merv
		  setTitle(algname);
        alg = new AlgThread(this);

		  // Arrange panels within frame
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        setLayout( gb );
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = GridBagConstraints.RELATIVE;
        /* Give any extra space to the animation panel */
        gc.weighty = 1.0;
        gc.weightx = 0.0;
        gc.gridx = 0; gc.gridy = 0;
        gb.setConstraints( drawingPanel, gc );
        add(drawingPanel);

        /* Source panel */
        tf = new TextFrame( sourceURL, sourceURL.getFile() );
        gc.gridx = GridBagConstraints.RELATIVE; gc.gridy = 0;
        /* The text panel's requirements are known and fixed */
        gc.weightx = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints( tf, gc );
        add( tf );
 
        /* Commentary panel */
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
        gb.setConstraints( control_panel, gc );
        add( control_panel );

        // generate initial data set
        alg.generateData();

		  pack();
        validate();
        show();

		  extraframe = new ExtraFrame(this);	
		  
    } // init()

    public Dimension preferredSize() {
        return new Dimension(800,480);
    }

    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            if (alg != null) {
              alg.drawingPanel = null;
              if (alg.isAlive())
                alg.stop();
            }
				extraframe.Remove_Window( );
            dispose();
            parentApp.start_button.enable();
        }
        return super.handleEvent(event);
    }

    public void setText( int n, String s ) {
        cpanel.setText( n, s );
    }
 
    public void Highlight( int n ) {
        tf.Highlight(n);
	try {
	    Thread.sleep(delay);
	} catch (InterruptedException e) {}
    }

    public void restoreDrawingPanelColor() {
        alg.restoreDrawingPanelColor();
    }

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
 
    public void finishAlg() {
        control_panel.stop_button.disable();
        control_panel.run_button.enable();
        control_panel.data_choice.enable();
        alg.restoreDrawingPanelColor();
    }
 
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
