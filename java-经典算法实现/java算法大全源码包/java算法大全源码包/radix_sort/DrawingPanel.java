
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class DrawingPanel extends Panel {
    int panel_height = 100;
    int panel_width = 100;
    int offset = 10;
    int pref_height = 200;
    int pref_width = 250;
    int delayDuration = 200;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    private boolean skip = false;
    private boolean noAnim = false;

    boolean showBinA = true, showBinB = false;
    int deltaY = 0;
    int bin_width, bin_height;
    int[] a;
    Vector[] binA, binB;
    int tmp_x, tmp_y, tmp_diameter, tmp_val;
    int highlightDigit;
    Font bigFont, smallFont, tinyFont;
    boolean highlight = true;

    public DrawingPanel() {
        smallFont = new Font("Dialog", Font.PLAIN, 10);
        fm = this.getFontMetrics(smallFont);
	bigFont = new Font("Dialog", Font.BOLD, 12);
	tinyFont = new Font("Dialog", Font.PLAIN, 9);
	tmp_x = tmp_y = tmp_diameter = tmp_val = highlightDigit = -1;
	setBackground(Color.white);
    } // DrawingPanel() 
    
    public void highlight(int i) {
	highlightDigit = i;
	repaint();
	delay();
    }

    public void setData(int[] a, Vector[] binA, Vector[] binB) {
	this.a = new int[a.length];
	this.binA = new Vector[binA.length];
	this.binB = new Vector[binB.length];
	for (int i = 0; i < a.length; i++) {
	    this.a[i] = a[i];
	}
	for (int i = 0; i < binA.length; i++) {
	    this.binA[i] = new Vector();
	    for (int j = 0; j < binA[i].size(); j++)
		this.binA[i].addElement(binA[i].elementAt(j));
	}
	for (int i = 0; i < binB.length; i++) {
	    this.binB[i] = new Vector();
	    for (int j = 0; j < binB[i].size(); j++)
		this.binB[i].addElement(binB[i].elementAt(j));
	}
	repaint();
	showBinA = true; showBinB = false;
	/*
	this.a = a;
	this.bin = bin; */
    } // setData()
    
    public void addBins() {
	binA = new Vector[binB.length];
	for (int i = 0; i < binB.length; i++) {
	    binA[i] = new Vector();
	}
	showBinA = true;
    }
    
    public void binUp() {
	showBinB = false;
	showBinA = true;
	// animate bins moving up and bottom open slowly
	for (int i = 0; i < 10; i++) {
	    deltaY = (i+1) * 2*panel_height/30;
	    repaint();
	    delay();
	}
		
	showBinA = false;	
	showBinB = true;
	deltaY = 0;
	binB = binA;
	repaint();
	delay();
    }

    public void delay() {
        try {
            Thread.sleep(skip?delayDuration/100:delayDuration);
        } catch (InterruptedException e) {}
    }

    public void setDelay(int delay) {
	this.delayDuration = delay;
    }
    
    public void restoreBinColor() {
	tmp_val = tmp_x = tmp_y = tmp_diameter = highlightDigit = -1;
        repaint();
    }

    public void move2bin(int i, int j) {
	tmp_val = a[i];
	a[i] = -1;
	int source_x = offset + i * (bin_width + offset);
	int source_y = panel_height/2 - bin_width/2;
	tmp_diameter = bin_width;
	int dest_x = offset + j*(bin_width+offset);
	int dest_y = panel_height - 2*offset - bin_height - tmp_diameter/2;
	
	// moving ball to mouth of bin
	tmp_x = source_x;
	tmp_y = source_y;
	for (int l = 0; l < 5; l++) {
	    tmp_x = source_x + (l+1)*(dest_x - source_x)/5;
	    tmp_y = source_y + (l+1)*(dest_y - source_y)/5;
	    repaint();
	    delay();
	}
	
	binA[j].addElement(new Integer(tmp_val));
	tmp_val = tmp_x = tmp_y = tmp_diameter = -1;
	repaint();
	delay();
    } // move2binA
    
    public void bin2bin(int i, int j) {
	int source_x = offset + i*(bin_width+offset);
	int source_y = 2*offset + bin_height - tmp_diameter/2;
	int dest_x = offset + j *(bin_width+offset);
	int dest_y = panel_height - 2*offset - bin_height - tmp_diameter/2;	
	tmp_val = ((Integer)binB[i].firstElement()).intValue();
	binB[i].removeElementAt(0);
	tmp_diameter = bin_width;
	tmp_x = source_x;
	tmp_y = source_y;	
	repaint();
	delay();
	for (int l = 0; l < 5; l++) {
	    tmp_x = source_x + (l+1)*(dest_x - source_x)/5;
	    tmp_y = source_y + (l+1)*(dest_y - source_y)/5;
	    repaint();
	    delay();
	}
	binA[j].addElement(new Integer(tmp_val));
	tmp_val = tmp_x = tmp_y = tmp_diameter = -1;
	repaint();
	delay();	
    }
            
    public void movefrombin(int i, int j) {
	if (binB[i].isEmpty())
	    return;
	int source_x = offset + i*(bin_width+offset);
	int source_y = 2*offset + bin_height - tmp_diameter/2;
	tmp_val = ((Integer)binB[i].firstElement()).intValue();
	binB[i].removeElementAt(0);
	
	int dest_x = offset + j * (bin_width + offset);
	int dest_y = panel_height/2 - bin_width/2;
	tmp_diameter = bin_width;
	
	tmp_x = source_x;
	tmp_y = source_y;
	repaint();
	
	for (int l = 0; l < 5; l++) {
	    tmp_x = source_x + (l+1)*(dest_x - source_x)/5;
	    tmp_y = source_y + (l+1)*(dest_y - source_y)/5;
	    repaint();
	    delay();
	}
	
	a[j] = tmp_val;
	restoreBinColor();
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
        offGraphics.setFont(smallFont);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void paint(Graphics g) {
	panel_height = size().height;
	panel_width = size().width;

	if (a!=null) {
	    bin_height = (panel_height - 2*offset) / 4;
	    int n = a.length > binA.length ? a.length : binA.length;
	    bin_width = (panel_width - 2*offset) / n - offset;

	    // draw bins
	    if (showBinA)
	      for (int i = 0; i < binA.length; i++) {
		drawBinA(g, bin_width, bin_height, i, binA[i]);
	      }
	    if (showBinB)
	      for (int i = 0; i < binB.length; i++) {
		drawBinB(g, bin_width, bin_height, i, binB[i]);
	      }

	    // draw balls
	    for (int i = 0; i < a.length; i++)
		if (a[i] != -1) {
		    int x = offset + i * (bin_width + offset);
		    int y = panel_height/2 - bin_width/2;
		    drawBall(g, x, y, a[i]);
		}
		
	    // draw temporary ball for animation purposes
	    if (tmp_val != -1)
		drawBall(g, tmp_x, tmp_y, tmp_val);
	}
	
        g.setColor( Color.black );
        g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    } // paint()
    
    public void drawBinA(Graphics g, int bin_width, int bin_height, int binNum,
	    Vector bin) {
	int x = offset + binNum*(bin_width+offset);
	int y = panel_height - 2*offset - bin_height - deltaY;
	
	// draw bin
	g.setColor( Color.black );
	g.fillRect(x-2, y, 2, bin_height);
	g.fillRect(x+bin_width, y, 2, bin_height);
	g.fillRect(x-2, y+bin_height, bin_width + 4, 2);
	// draw bin number
	g.setColor( Color.black );
	g.setFont(bigFont);
	g.drawString("" + binNum, x + bin_width/2 - 2, y + bin_height + offset + 5);
	
	// draw balls
	g.setColor( Color.red );
	for (int i = 0; i < bin.size(); i++)
	    drawBall(g, x, y + bin_height - (i + 1) * bin_width + 2,
		((Integer)bin.elementAt(i)).intValue());
    }
    
    public void drawBinB(Graphics g, int bin_width, int bin_height, int binNum,
	    Vector bin) {
	int x = offset + binNum*(bin_width+offset);
	int y = 2*offset;
	
	// draw bin
	g.setColor( Color.black );
	g.fillRect(x-2, y, 2, bin_height);
	g.fillRect(x+bin_width, y, 2, bin_height);
	//g.fillRect(x-2, y, bin_width + 4, 2);
	// draw bin number
	g.setColor( Color.black );
	g.setFont(bigFont);
	g.drawString("" + binNum, x + bin_width/2 - 2, y + offset - 12);
	
	// draw balls
	g.setColor( Color.red );
	for (int i = 0; i < bin.size(); i++)
	    drawBall(g, x, y + i * bin_width + 2,
		((Integer)bin.elementAt((bin.size() - 1) - i)).intValue());
    }
    
    public void drawBall(Graphics g, int x, int y, int ballValue) {
	int diameter = bin_width;
	g.setColor( Color.yellow );
	g.fillOval(x, y, diameter-2, diameter-2);
	g.setColor( Color.black );
	g.drawOval(x, y, diameter-2, diameter-2);
	//g.drawString(""+ballValue, x+diameter/2 - 10, y + diameter/2 + 2);
	String valString = new String("" + ballValue);
	int x2 = x + 3;
	int y2 = y + diameter/2 + 4;
	if (!highlight) {
	    if (valString.length() > 4)
		g.setFont(tinyFont);
	    else
		g.setFont(smallFont);
	    g.setColor( Color.black );
	    g.drawString(valString, x2, y2);
	    return;
	}
	for (int i = 0; i < valString.length(); i++) {
	    if (highlightDigit == (valString.length() - i)) {
		if (valString.length() > 4)
		    g.setFont(tinyFont);
		else
		    g.setFont(bigFont);
		g.setColor( Color.red );
	    } else {
		if (valString.length() > 4)
		    g.setFont(tinyFont);
		else
		    g.setFont(smallFont);
		g.setColor( Color.black );
	    }
	    g.drawString(valString.substring(i, i+1), x2, y2);
	    //x2 += (diameter - 5)/valString.length();
	    if (valString.length() > 4)
		x2 += 5;
	    else
		x2 += 6;
	}
    }
    
    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()

    public void setHighlight(boolean b) {
	highlight = b;
    }

    public void setSkip(boolean skip) {
	this.skip = skip;
    }

    public boolean getSkip() {
	return skip;
    }

    public void setNoAnim(boolean noAnim) {
	this.noAnim = noAnim;
    }

    public boolean getNoAnim() {
	return noAnim;
    }
} // class DrawingPanel
