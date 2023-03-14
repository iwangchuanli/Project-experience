
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ControlPanel extends Panel {
    AlgAnimFrame frame;
    Button run_button, stop_button, step_button;
    Choice data_choice, delay_choice;
    int data_selected = 0;
    TextField mesg;

    public ControlPanel(AlgAnimFrame frame, String algname) {
	setBackground( Color.white );
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gc = new GridBagConstraints();
	gc.insets = new Insets(5, 5, 5, 5);
	setLayout(gb);
	gc.fill = GridBagConstraints.BOTH;
	gc.weightx = 1.0;
	
        this.frame = frame;

        run_button = new Button("Run " + algname);
	gb.setConstraints(run_button, gc);
        this.add(run_button);
	
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
	data_choice.select(0);
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gb.setConstraints(data_choice, gc);
	this.add(data_choice);
	
	delay_choice = new Choice();
	delay_choice.addItem("Delay 200msec");
	delay_choice.addItem("Delay 400msec");
	delay_choice.addItem("Delay 600msec");
	delay_choice.addItem("Delay 800msec");
	delay_choice.addItem("Delay 1000msec");
	delay_choice.select(2);
	gc.gridwidth = GridBagConstraints.REMAINDER;
	gc.insets = new Insets(5, 5, 5, 200);
	gb.setConstraints(delay_choice, gc);	
	this.add(delay_choice);
	
	mesg = new TextField(50);
	mesg.disable();
	gc.insets = new Insets(5, 5, 5, 5);
	gb.setConstraints(mesg, gc);
	this.add(mesg);
    } // ControlPanel()

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
        } else if (target instanceof Choice) {
	    if (target == data_choice) {
		data_selected = data_choice.getSelectedIndex();
		frame.alg.generateData();
		setText("Data generated...");
	    } else if (target == delay_choice) {
		//System.out.println("delay");
		frame.delay = (delay_choice.getSelectedIndex() + 1) * 200;
		//frame.alg.setDelay(frame.delay);
		setText("Animation delay now set to " + frame.delay + " msec...");
	    }
        }
        return false;
    } // action()

    public int getDataChoice() {
        return data_selected;
    }

    public void setText(String str) {
	mesg.setText(str);
    }
} // class ControlPanel
