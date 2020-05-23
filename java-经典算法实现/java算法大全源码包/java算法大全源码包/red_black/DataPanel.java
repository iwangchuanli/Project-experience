import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class DataPanel extends DrawingPanel {
	private int panel_height = 200;
	private int panel_width = 200;
	private int offset = 10;
	private int pref_height = 400;
	private int pref_width = 250;

	private int x_offset = 1;
	private int y_offset = 1;
	
	private int containerWidth, containerHeight;

	private Panel panel;

	public DataPanel() {

		setBackground(Color.lightGray);
		panel_height = size().height;
		panel_width = size().width;

		}

	public void DrawContainer(Graphics g, DataPanel panel) {
		int x1, x2, y1, y2;
		int pwidth, pheight;

		pwidth = panel.getPanelWidth();
		pheight = panel.getPanelHeight();

		System.out.println("Drawing Container. pwidth:" +pwidth+ "pheight" +pheight);

		g.setColor(Color.black);

		x1 = x_offset;
		y1 = y_offset;
		x2 = x1;
		y2 = pheight - y_offset;

		if(g == null) System.out.println("g is null");
		g.drawLine(x1, y1, x2, y2);

		x1 = x2;
		y1 = y2;
		x2 = pwidth - x_offset;
		y2 = y1;

		g.drawLine(x1, y1, x2, y2);
		
		x1 = x2;
		y1 = y2;
		x2 = x1;
		y2 = y_offset;

		g.drawLine(x1, y1, x2, y2);

		containerWidth = offset + x2;
		containerHeight = offset + y1;
		}

	public int getPanelWidth() {
		return panel_width;
		}

	public int getPanelHeight() {
		return panel_height;
		}

	public int getContainerWidth() {
		return containerWidth;
		}

	public int getContainerHeight() {
		return containerHeight;
		}
	

	}
