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
    int delay = 200;

    Panel drawingPanel;
    TextFrame tf;
    ComPanel cpanel;
    ControlPanel control_panel;
    Scrollbar vScrollbar;
    boolean step = false, stepWait = false;

    public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {
        this.parentApp = parentApp;
        this.sourceURL = sourceURL;
        this.algname = parentApp.getParameter("algname");

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
        gc.weightx = 1.0;
        gc.gridx = 0; gc.gridy = 0;
        gb.setConstraints( drawingPanel, gc );
        add(drawingPanel);

        /* Source panel */
	Panel p = new Panel();
        tf = new TextFrame( sourceURL, sourceURL.getFile() );
	vScrollbar = new Scrollbar(Scrollbar.VERTICAL);
	vScrollbar.setValues(0, 10, 0, 90);
	vScrollbar.setPageIncrement(10);
	p.setLayout(new BorderLayout());
	p.add("Center", tf);
	p.add("East", vScrollbar);
	p.validate();
	
        gc.gridx = GridBagConstraints.RELATIVE; gc.gridy = 0;
        /* The text panel's requirements are known and fixed */
        gc.weightx = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gb.setConstraints( p, gc );
        add( p );
 
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
    } // init()

    public Dimension preferredSize() {
        return new Dimension(900,500);
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
        } else if (event.target == vScrollbar) {
	    switch (event.id) {
		case Event.SCROLL_LINE_UP:
		case Event.SCROLL_LINE_DOWN:
		case Event.SCROLL_PAGE_UP:
		case Event.SCROLL_PAGE_DOWN:
		case Event.SCROLL_ABSOLUTE:
		    int val = ((Scrollbar)(event.target)).getValue();
		    int min = ((Scrollbar)(event.target)).getMinimum();
		    int max = ((Scrollbar)(event.target)).getMaximum();
		    tf.setStart(val*tf.n_lines/(max - min + 10));
		    //System.out.println(((Integer)event.arg).intValue());
		    tf.repaint();
	    }
	}
        return super.handleEvent(event);
    }

    public void setText( int n, String s ) {
        cpanel.setText( n, s );
    }
 
    public void Highlight( int n ) {
        tf.Highlight(n);
	int numLineVisible = tf.size().height/tf.line_space;
	
	if ( (n < (tf.start + 2)) || (n > (tf.start + numLineVisible - 2))) {
	    int max = vScrollbar.getMaximum();
	    int min = vScrollbar.getMinimum();
	    int startLine = n - numLineVisible/2;
	    if (startLine > 0) {
	    	tf.setStart(startLine);
	    	vScrollbar.setValue(startLine * (max - min) / tf.n_lines);
	    } else {
	    	tf.setStart(0);
	    	vScrollbar.setValue(0);
	    }
	}
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
	control_panel.step_button.enable(); step = false;
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
