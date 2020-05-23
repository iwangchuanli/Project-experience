
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ControlPanel extends Panel {
    AlgAnimFrame frame;
    Button run_button, stop_button, step_button;
    Choice data_choice;
    int data_selected = 0;
    SpeedBar speedBar;

    public ControlPanel(AlgAnimFrame frame, String algname) {
        this.frame = frame;

        run_button = new Button("Run " + algname);
        this.add(run_button);
	step_button = new Button("Next Step");
	this.add(step_button);

        stop_button = new Button("Stop");
        stop_button.disable();
        this.add(stop_button);

        data_choice = new Choice();
        // assigning data sets
        for (int i = 0; i < frame.alg.dataSets.length; i++)
            data_choice.addItem(frame.alg.dataSets[i]);
        this.add(data_choice);
	
	//Panel p = new Panel();
	//p.add("North", new Label("Speed"));
	speedBar = new SpeedBar();
	//p.add("South", speedBar);
	this.add(speedBar);
    } // ControlPanel()

    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
        if (target == run_button) {
            frame.startAlg();
            return true;
        } else if (target == stop_button) {
	    frame.setStep(false);
            if (frame.alg.isAlive())
                frame.alg.stop();
            frame.finishAlg();
            return true;
	} else if (target == step_button) {
	    frame.setStep(true);
	    if (!frame.alg.isAlive())
		frame.startAlg();
	    else
		frame.alg.resume();
	    return true;
        } else if (target instanceof Choice) {
            data_selected = data_choice.getSelectedIndex();
            frame.alg.generateData();
        }
        return false;
    } // action()

    public int getDataChoice() {
        return data_selected;
    }

} // class ControlPanel
