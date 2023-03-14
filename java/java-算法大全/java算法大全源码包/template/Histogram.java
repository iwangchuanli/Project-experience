
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class Histogram extends Panel implements DrawingObj {
    int panel_height, panel_width;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    Font font;

    String title, xLabel, yLabel;
    int yMax, yMin, yStep;
    int xMax, xMin, xStep;

    Hashtable table;

    public Histogram() {
	setBackground(Color.white);
	this.font = new Font("Courier", Font.PLAIN, 12);
	this.fm = this.getFontMetrics(font);
	title = new String();
	xLabel = new String();
	yLabel = new String();
	yMax = 0; yMin = 0; yStep = 0;
	table = new Hashtable();
    }

    public void init() {
	table = new Hashtable();
	repaint();
    }

    public int getX() {
	return location().x;
    }

    public int getY() {
	return location().y;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setXLabel(String xLabel) {
	this.xLabel = xLabel;
    }

    public void setYLabel(String yLabel) {
	this.yLabel = yLabel;
    }

    public void setYMax(int yMax) {
	this.yMax = yMax;
    }

    public void setYStep(int step) {
	this.yStep = step;
    }

    public void setYMin(int yMin) {
	this.yMin = yMin;
    }

    public void setXMax(int xMax) {
	this.xMax = xMax;
    }

    public void setXStep(int step) {
	this.xStep = step;
    }

    public void setXMin(int xMin) {
	this.xMin = xMin;
    }

    public void setValueXY(int x, int y) {
	Integer X = new Integer(x);
	Integer Y = new Integer(y);
	table.put(X, Y);
	if (x > xMax)
	    setXMax(x + 1);
	if (y > yMax)
	    setYMax(y + 1);
	if (xStep < (xMax-xMin)/10) {
	    xStep = (xMax-xMin)/10;
	    xStep += 10-(xStep%10);
	}
	if (yStep < (yMax-yMin)/10) {
	    yStep = (yMax-yMin)/10;
	    yStep += 10-(yStep%10);
	}
    }

    public void incValueX(int x) {
	Integer X = new Integer(x);
	if (table.containsKey(X)) {
	    Integer Y = new Integer(((Integer)table.get(X)).intValue() + 1);
	    table.put(X, Y);
	    if (Y.intValue() > yMax)
		setYMax(Y.intValue() + 1);
	} else {
	    Integer Y = new Integer(1);
	    table.put(X, Y);
	    if (x > xMax)
		setXMax(x + 1);
	    if (Y.intValue() > yMax)
		setYMax(Y.intValue() + 1);
	}
	if (xStep < (xMax-xMin)/10) {
	    xStep = (xMax-xMin)/10;
	    xStep += 10-(xStep%10);
	}
	if (yStep < (yMax-yMin)/10) {
	    yStep = (yMax-yMin)/10;
	    yStep += 10-(yStep%10);
	}
    }

    public void update(Graphics g) {
        Dimension d = size();
        if (d.width < 1 || d.height < 1)
            return;
 
        if ((offscreen == null) || (d.width != offscreensize.width) ||
                    (d.height != offscreensize.height)) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            offGraphics = offscreen.getGraphics();
        }
 
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setFont(font);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void draw(Graphics g) {
	paint(g);
    }

    public void paint(Graphics g) {
        panel_height = size().height;
        panel_width = size().width;
 
	g.setColor(Color.black);
	//g.drawRect(1, 1, panel_width-2, panel_height-2);

	// draw Title
	g.setFont(font);
	g.drawString(title, (panel_width - title.length()*7)/2, 20);

	// draw X axis
	g.drawLine(30, panel_height - 30, panel_width - 30, panel_height - 30);
	g.drawString(xLabel, (panel_width - xLabel.length()*7)/2, 
		panel_height - 5);

	// draw Y axis
	g.drawLine(30, 50, 30, panel_height - 30);
	g.drawString(yLabel, 5, 45);

	if (xMax != xMin) {
	    int range = (panel_width - 85);
	    for (int i = xMin; i <= xMax; i += xStep) {
		g.drawLine(45+(i-xMin)*range/(xMax-xMin), panel_height-28,
			45+(i-xMin)*range/(xMax-xMin), panel_height-32);
		g.drawString(""+i,
			45+(i-xMin)*range/(xMax-xMin) - 7,
		    	panel_height-17);
	    }
	}

	if (yMax != yMin) {
	    int range = (panel_height - 90);
	    for (int i = yMin; i <= yMax; i += yStep) {
	    	if (i == yMin)
		    continue;
		g.drawLine(28, panel_height-30-(i-yMin)*range/(yMax-yMin),
			32, panel_height-30-(i-yMin)*range/(yMax-yMin));
		g.drawString(""+i,
			5, panel_height-30-(i-yMin)*range/(yMax-yMin) + 5);
	    }
	}

	if (!table.isEmpty()) {
	    // plot bar
	    g.setColor(Color.blue);
	    int barWidth = (panel_width - 85) / (xMax - xMin);
	    if (barWidth < 2) barWidth = 2;
	    for (int i = xMin; i <= xMax; i++) {
		Integer X = new Integer(i);
		if (!table.containsKey(X))
		    continue;

		int barHeight = ((Integer)table.get(X)).intValue();
		barHeight = (barHeight-yMin) * (panel_height - 90) / 
				(yMax - yMin);

		g.fillRect(
			45+(i-xMin)*(panel_width - 85) / (xMax - xMin) -
				barWidth/2, 
			panel_height - 30 - barHeight,
			barWidth>2?barWidth - 1:barWidth, barHeight);
	    }
	}
    } // paint();
}
