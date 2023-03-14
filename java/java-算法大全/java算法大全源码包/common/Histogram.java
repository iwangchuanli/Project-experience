
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * This is one example of the drawing object inheriting
 * <code>java.awt.Panel</code> which can be added to the drawing panel
 * (<code>DrawingPanel</code>). 
 * <p>
 * Since <code>DrawingPanel</code> is also an extension of panel,
 * this <code>Histogram</code> panel has to be added to the drawing panel
 * by using the following statement (otherwise, the layout manager of
 * this class might interprete its dimension as null during the panel
 * initialization and nothing will show up): <code><pre>
 *	Histogram histogram = new Histogram();
 *	drawingPanel.setLayout(null);
 *	drawingPanel.add(histogram);
 * 	...
 *	histogram.setTitle("title");
 *	histogram.setXLabel("x-axis label");
 *	histogram.setYLabel("y-axis label");
 *	histogram.setYMax(maxY);
 *	histogram.setXMax(maxX);
 * 	histogram.setYStep(10);
 *	histogram.setXStep(5);
 *	histogram.reshape(x, y, width, height);
 * </pre></code>
 * <code>drawingPanel</code> is an instance of the class object
 * <code>DrawingPanel</code>, which can be typically obtained from
 * the instance of <code>AlgAnimFrame</code>, by calling the
 * <code>getDrawingPanel()</code> method of the class.
 * e.g. in <code>AlgThread</code>, the instance of <code>AlgAnimFrame</code>
 * is passed in as <code>frame</code>. Therefore, <code><pre>
 * 	drawingPanel = frame.getDrawingPanel();
 * </pre></code>
 * The <code>reshape</code> method is inherited from a parent class.
 * It is called to move the histogram to position <code>(x, y)</code>
 * and set the dimension of the histogram to <code>width x height</code>.
 * @see DrawingPanel
 * @see AlgAnimFrame
 * @see Panel
 */
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

    /**
     * A constructor to this class which set the background of the panel
     * to white, font to size 12 PLAIN courier, and initializes the
     * title, x-axis label, y-axis label, etc.
     * Note that although the initial background color is set to white,
     * it can be changed at any time after this contructor is called by
     * using the <code>setBackground</code> method inherited. e.g.
     * <code><pre>
     *	Histogram histogram = new Histogram();
     *	histogram.setBackground(Color.lightGray);
     * </pre></code>
     * For detail of what color can be set, refer to 
     * <code>java.awt.Color</code>.
     */
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

    /**
     * Initialize the histogram, removing all bars from the graph.
     */
    public void init() {
	table = new Hashtable();
	repaint();
    }

    /**
     * Get the left most position of the panel.
     * @return The x-coordinate of the top-left position of the histogram.
     */
    public int getX() {
	return location().x;
    }

    /**
     * Get the top most position of the panel.
     * @return The y-coordinate of the top-left position of the histogram.
     */
    public int getY() {
	return location().y;
    }

    /**
     * Set a title for the histogram.
     * @param title The new title of the histogram.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Set a title for the x-axis.
     * @param xLabel new title for the x-axis of the histogram.
     */
    public void setXLabel(String xLabel) {
	this.xLabel = xLabel;
    }

    /**
     * Set a title for the y-axis of the histogram.
     * @param yLabel new title for the y-axis of the histogram.
     */
    public void setYLabel(String yLabel) {
	this.yLabel = yLabel;
    }

    /**
     * Set the maximum value for the y-axis of the histogram.
     * @param yMax The maximum value for the y-axis of the histogram.
     */
    public void setYMax(int yMax) {
	this.yMax = yMax;
    }

    /**
     * Set the incremental step for the y-axis. For example, if the minimum
     * value of the y-axis is 0, and this incremental step is set to 5,
     * then the value 0, 5, 10, 15, 20, ... will be displayed on the
     * y-axis of the histogram.
     * @param step The incremental step for the y-axis of the histogram.
     */
    public void setYStep(int step) {
	this.yStep = step;
    }

    /**
     * Set the minimum value for the y-axis of the histogram.
     * @param yMin The new minimum value for the y-axis of the histogram.
     */
    public void setYMin(int yMin) {
	this.yMin = yMin;
    }

    /**
     * Set the maximum value for the x-axis of the histogram.
     * @param xMax The maximum value for the x-axis of the histogram.
     */
    public void setXMax(int xMax) {
	this.xMax = xMax;
    }

    /**
     * Set the incremental step for the x-axis of the histogram.
     * @param step The new incremental step for the x-axis of the histogram.
     * @see Histogram#setYStep
     */
    public void setXStep(int step) {
	this.xStep = step;
    }

    /**
     * Set the minimum value for the x-axis of the histogram.
     * @param xMin The new minimum value for the x-axis of the histogram.
     */
    public void setXMin(int xMin) {
	this.xMin = xMin;
    }

    /**
     * Set the Y value for the vertical bar at the specified X position.
     * If the x or y value specified in the parameter is larger than the
     * maximum x or y value, the maximum value will be set to the specified
     * value + 1.
     * @param x The vertical bar at X = x
     * <p>
     * @param y The value of the vertical bar specified by the first parameter.
     */
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

    /**
     * Increment the Y value of vertical bar specified by the parameter.
     * Initially, all x values are set to zero. If the x-value does not
     * exist in the hashtable governing the histogram, it will be added.
     * @param x Specifies the vertical bar at X = x.
     */
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

    /**
     * This method is invoked when the <code>repaint()</code> method is called.
     * The <code>update method is override here to eleminate flashing during
     * the updating of the histogram panel.
     */
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

    /**
     * Same as <code>paint()</code>. This method just calls the paint() method.
     * It must be defined here to implement the DrawingObj interface.
     * @see Histogram#paint
     */
    public void draw(Graphics g) {
	paint(g);
    }

    /**
     * Method to draw objects on the histogram panel.
     */
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
