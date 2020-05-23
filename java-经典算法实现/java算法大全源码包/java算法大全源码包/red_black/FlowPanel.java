import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class FlowPanel extends Panel {
	private int panel_height = 400;
	private int panel_width = 400;
	private int offset = 10;
	private int pref_height = 400;
	private int pref_width = 450;

	private DrawingPanel dpBefore, dpAfter;

	public FlowPanel(AlgAnimFrame frame) {
		setBackground(Color.lightGray);
		panel_height = size().height;
		panel_width = size().width;

		setLayout(new FlowLayout());
	

		dpBefore = frame.getBeforeDp();
		dpAfter = frame.getDrawingPanel();

		add(dpBefore);
		add(dpAfter);

				//pack();
		validate();
		show();

		}
	
	}
