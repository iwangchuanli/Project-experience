
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

    int bin_width, bin_height;
    int[] a;
    Vector[] bin;
    int tmp_x, tmp_y, tmp_diameter, tmp_val;
    Font bigFont, smallFont;

    public DrawingPanel() {
        smallFont = new Font("Dialog", Font.PLAIN, 10);
	bigFont = new Font("Dialog", Font.BOLD, 12);
        fm = this.getFontMetrics(smallFont);
	tmp_x = tmp_y = tmp_diameter = tmp_val = -1;
	setBackground( Color.white );
    } // DrawingPanel() 

    public void setData(int[] a, Vector[] bin) {
	this.a = new int[a.length];
	this.bin = new Vector[bin.length];
	for (int i = 0; i < a.length; i++) {
	    this.a[i] = a[i];
	}
	for (int i = 0; i < bin.length; i++) {
	    this.bin[i] = new Vector();
	    for (int j = 0; j < bin[i].size(); j++)
		this.bin[i].addElement(bin[i].elementAt(j));
	}
	repaint();
	/*
	this.a = a;
	this.bin = bin; */
    } // setData()

    public void delay() {
        try {
            Thread.sleep(skip?delayDuration/100:delayDuration);
        } catch (InterruptedException e) {}
    }

    public void setDelay(int delay) {
	this.delayDuration = delay;
    }

    public void restoreBinColor() {
	tmp_val = tmp_x = tmp_y = tmp_diameter = -1;
        repaint();
    }

    public void move2bin(int i, int j) {
	tmp_val = a[i];
	a[i] = -1;
	int source_x = offset + i * (bin_width + offset);
	int source_y = offset;
	tmp_diameter = bin_width - (bin_width*(10-tmp_val)/10)/2;
	source_x += (bin_width - tmp_diameter)/2;
	source_y += (bin_width - tmp_diameter)/2;
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
	
	bin[j].addElement(new Integer(tmp_val));
	tmp_val = tmp_x = tmp_y = tmp_diameter = -1;
	repaint();
	delay();
    } // move2bin
    
    public void movefrombin(int i, int j) {
	if (bin[i].isEmpty())
	    return;
	int source_x = offset + i*(bin_width+offset);
	int source_y = panel_height - 2*offset - bin_height - tmp_diameter/2;
	tmp_val = ((Integer)bin[i].firstElement()).intValue();
	bin[i].removeElementAt(0);
	
	int dest_x = offset + j * (bin_width + offset);
	int dest_y = offset;
	tmp_diameter = bin_width - (bin_width*(10-tmp_val)/10)/2;
	dest_x += (bin_width - tmp_diameter)/2;
	dest_y += (bin_width - tmp_diameter)/2;
	
	tmp_x = source_x;
	tmp_y = source_y;
	repaint();
	
	for (int l = 0; l < 5; l++) {
	    tmp_x = source_x + (l+1) * (dest_x - source_x)/5;
	    tmp_y = source_y + (l+1) * (dest_y - source_y)/5;
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
        //Font font = new Font("Dialog", Font.PLAIN, 10);
        offGraphics.setFont(smallFont);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void paint(Graphics g) {
	Dimension d = size();
	panel_height = d.height;
	panel_width = d.width;
	if (a!=null) {
	    bin_height = (panel_height - 2*offset) / 3;
	    bin_width = (panel_width - 2*offset) / bin.length - offset;

	    // draw bins
	    for (int i = 0; i < bin.length; i++) {
		drawBin(g, bin_width, bin_height, i, bin[i]);
	    }

	    // draw balls
	    for (int i = 0; i < a.length; i++)
		if (a[i] != -1) {
		    int x = offset + i * (bin_width + offset);
		    int y = offset;
		    int diameter = bin_width - (bin_width*(10-a[i])/10)/2;
		    x += (bin_width - diameter)/2;
		    y += (bin_width - diameter)/2;
		    drawBall(g, x, y, diameter, a[i]);
		}
		
	    // draw temporary ball for animation purposes
	    if (tmp_val != -1)
		drawBall(g, tmp_x, tmp_y, tmp_diameter, tmp_val);
	}
	
        g.setColor( Color.black );
        g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    } // paint()

    public void drawBin(Graphics g, int bin_width, int bin_height, int binNum,
	    Vector bin) {

	int width = bin_width - (bin_width*(9-binNum)/10)/2;
	int x = offset + binNum*(bin_width+offset);
	int y = panel_height - 2*offset - bin_height;
	
	// draw bin
	g.setColor( Color.black );
	g.fillRect(x-2, y, 2, bin_height);
	g.fillRect(x+width, y, 2, bin_height);
	g.fillRect(x-2, y+bin_height, width + 4, 2);
	// draw bin number
	//g.setFont(new Font("Dialog", Font.BOLD, 12));
	g.setFont(bigFont);
	g.drawString("" + binNum, x + width/2 - 2, y + bin_height + offset + 5);
	
	// draw balls
	g.setColor( Color.red );
	int ball_diameter = width;
	for (int i = 0; i < bin.size(); i++)
	    drawBall(g, x, y + bin_height - (i + 1) * ball_diameter,
		ball_diameter, ((Integer)bin.elementAt(i)).intValue());
    }

    public void drawBall(Graphics g, int x, int y, int diameter, int ballValue) {
	g.setColor( Color.red );
	g.fillOval(x, y, diameter, diameter);
	g.setColor( Color.black );
	g.drawOval(x, y, diameter, diameter);
	g.setColor( Color.black );
	//g.setFont(new Font("Dialog", Font.PLAIN, 10));
	g.setFont(smallFont);
	g.drawString(""+ballValue, x+diameter/2, y + diameter/2);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()

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
