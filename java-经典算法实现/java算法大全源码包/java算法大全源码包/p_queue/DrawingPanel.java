
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

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    Heap heap;

    public DrawingPanel() {
        smallFont = new Font("Dialog", Font.PLAIN, 10);
        fm = this.getFontMetrics(smallFont);
	bigFont = new Font("Dialog", Font.PLAIN, 12);
	hugeFont = new Font("Dialog", Font.PLAIN, 14);
	fixFont = new Font("Courier", Font.PLAIN, 12);
	tinyFont = new Font("Dialog", Font.PLAIN, 8);
	setBackground(Color.white);
	panel_height = size().height;
	panel_width = size().width;
	heap = null;
    } // DrawingPanel() 
    
    public void shortDelay() {
	try {
	    Thread.sleep(delayDuration/3);
	} catch (InterruptedException e) {}
    }

    public void delay() {
	try {
	    Thread.sleep(delayDuration);
	} catch (InterruptedException e) {}
    }

    public void setDelay(int delay) {
	this.delayDuration = delay;
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

    public void drawHeap(Heap heap) {
	this.heap = heap;
	repaint();
    }

    public Heap getHeap() {
	return this.heap;
    }

    public void paint(Graphics g) {
	panel_height = size().height;
	panel_width = size().width;

	if (heap != null)
	    heap.drawTree(g);

        g.setColor( Color.black );
        g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    } // paint()

    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()
} // class DrawingPanel
