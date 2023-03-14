/* AlgAnimFrame.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class AlgAnimFrame extends Frame {
    AlgAnimApp parentApp;
    URL sourceURL;
    String algname;
    AlgThread alg = null;

    Panel drawingPanel;
    TextFrame tf;
    ComPanel cpanel;
    ControlPanel control_panel;
    StatsPanel stats_panel;
    Button stats_button, unknown_panel;
    boolean step;    

    public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {
        this.parentApp = parentApp;
        this.sourceURL = sourceURL;
        this.algname = parentApp.getParameter("algname");
        setTitle(algname);
	  stats_panel = new StatsPanel(this, "Source stats");
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
        gc.gridheight = GridBagConstraints.RELATIVE;
        cpanel = new ComPanel( algname, 6 );
        gb.setConstraints( cpanel, gc );
        add( cpanel );
 
        /* Control panel */
        control_panel = new ControlPanel(this, algname);
        gc.gridx = GridBagConstraints.RELATIVE;
	  gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints( control_panel, gc );
        add( control_panel );
	
	  /* Stats panel */
	  //stats_button = new Button("stats_panel");
	  gc.gridx = 0;
	  gc.gridy = GridBagConstraints.RELATIVE;
	  gc.gridheight = GridBagConstraints.REMAINDER;
	  add(stats_panel);

	  /* unknown panel */
	  unknown_panel = new Button("unknown_panel");
	  gc.gridx = gc.gridy = GridBagConstraints.REMAINDER;
	  gc.gridwidth = GridBagConstraints.REMAINDER;
	  add(unknown_panel);

        // generate initial data set
        alg.generateData();

        pack();
        validate();
        show();
    } // init()

    public Dimension preferredSize() {
        return new Dimension(800,680);
    }

    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            if (alg != null) {
              alg.drawingPanel = null;
              if (alg.isAlive())
                alg.stop();
            }
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
	    Thread.sleep((int)(1000.0 * control_panel.speedBar.getSpeed()));
	} catch (InterruptedException e) {}
	//setText(0, "Current Speed: " + ((int)((1-control_panel.speedBar.getSpeed())*100)));
    }

    public void restoreDrawingPanelColor() {
        alg.restoreDrawingPanelColor();
    }

    public void startAlg() {
	if (alg.isAlive() && !step) {
	    alg.stop();
	    alg = new AlgThread(this);
	    alg.start();
	} else if (alg.isAlive()) {
	    alg.resume();
	    setStep(false);
	} else { // alg.isAlive() == false
	    alg = new AlgThread(this);
	    alg.start();
	}
	if (!step) {
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
	    // setText(2, "Click NEXT STEP...");
	    control_panel.step_button.enable();
	    try {
		alg.suspend();
	    } catch (SecurityException e) {}
	}
    }        
    public void setStep(boolean step) {
	// setText(2, "Stepping through the next line(s)...");
	this.step = step;
        control_panel.stop_button.disable();
        control_panel.run_button.enable();
        control_panel.data_choice.enable();
	if (step)
	    control_panel.step_button.disable();	
    }   
   
    // Stats Panel methods
    public void addStatsLabel(String statsTitle, String s_label) {
	stats_panel.addStatsLabel(statsTitle, s_label);
    }

    public void updateStatsValue(String statsTitle, String s_label, int statsValue) {
	stats_panel.updateStatsValue(statsTitle, s_label, statsValue);
    }

} // class AlgAnimFrame
