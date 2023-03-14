// Author: Chien Wei Tan
// Filename: SpeedBar.java
// Updated: 10.08.1998

import java.awt.*;

public class SpeedBar extends Scrollbar {
	Scrollbar sb;
	//int barValue=0;
	//int barVisible=20;
	//int barMinimum=-100;
	//int barMaximum=100;
	

	SpeedBar() {
		super(Scrollbar.HORIZONTAL, 50, 20, 1, 100);
	}


	double getSpeed() {
        		double max = super.getMaximum();  
        		double val = super.getValue();  
		return (1-(val / max));
    	}
	
}