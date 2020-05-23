
import java.awt.*;
import java.applet.*;
import java.io.*;

public class StickPanel extends Panel {
    int max_sticks = 0;
    int stick_width = 20;
    int panel_height = 100;
    int bottom_offset = 10;
    int pref_height = 200;
    int pref_width = 250;
    Stick[] sticks;
    int nsticks = 0;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;
    int pivot = -1;
    int currentLocation = -1;
    int p_pos = -1;
    AlgAnimFrame frame;

    public StickPanel(AlgAnimFrame frame) {
	this.frame = frame;
        Font font = new Font("Dialog", Font.PLAIN, 10);
        fm = this.getFontMetrics(font);
    } // StickPanel() 

    public void setMax(int n) {
        sticks = new Stick[n];
        max_sticks = n;
    }

    /* Adds a new stick */
    int addStick(int value) {
        Stick b = new Stick(0, 0, stick_width, value);
        sticks[nsticks] = b;
        return nsticks++;
    } // addStick()

    int setSticks(int[] a, int n) {
        int i, x, dx;

        dx = stick_width;
        x = dx;
        nsticks = 0;
        for(i=0;i<n;i++) {
            sticks[nsticks++] = new Stick(x, 0, stick_width, a[i]);
            x += dx;
        }
        repaint();
        return n;
    } // setSticks()

    public void delay() {
        try {
            Thread.sleep((int)(frame.control_panel.speedBar.getSpeed() * 1000.0));
        } catch (InterruptedException e) {}
    }

    void swapSticks(int i, int j) {
        if (i != j) {
            Color curColor = sticks[i].cur_colour;
            sticks[i].setCurrentColour(getBackground());
            repaint();
            delay();
            sticks[i].setCurrentColour(curColor);
            sticks[j].setCurrentColour(getBackground());
            repaint();
            delay();
            sticks[j].setCurrentColour(curColor);
            sticks[i].setCurrentColour(getBackground());
            repaint();
            delay();
            sticks[i].setCurrentColour(curColor);
            sticks[j].setCurrentColour(getBackground());
            repaint();
            delay();

            sticks[i].setCurrentColour(curColor);
            sticks[j].setCurrentColour(curColor);
        }
        
        if (i == pivot)
            pivot = j;
        else if (j == pivot)
            pivot = i;
        int tmp_h = sticks[i].h,
            tmp_value = sticks[i].value;
        sticks[i].h = sticks[j].h;
        sticks[i].value = sticks[j].value;
        sticks[j].h = tmp_h;
        sticks[j].value = tmp_value;

        repaint();
    } // swapSticks()

    void setPivot(int posn) {
        pivot = posn;
        repaint();
        delay();
    }

    void setCurrentLocation(int location) {
        this.currentLocation = location;
        repaint();
        delay();
    }

    void setPos(int pos) {
        p_pos = pos;
        repaint();
        delay();
    }

    void setPartition(int low, int high) {
        for (int i = 0; i < nsticks; i++)
            if (i < low || i > high)
                sticks[i].restoreColour();
            else
                sticks[i].setCurrentColour(Color.green);
        currentLocation = -1;
        p_pos = -1;
        repaint();
        delay();
    }

    void restoreStickColor() {
        for (int i = 0; i < nsticks; i++)
            sticks[i].restoreColour();
        currentLocation = -1;
        p_pos = -1;
        repaint();
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
        Font font = new Font("Dialog", Font.PLAIN, 10);
        offGraphics.setFont(font);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void paint(Graphics g) {
        panel_height = size().height; 
        int w = size().width;

        for (int i = 0; i < nsticks; i++) {
            sticks[i].setY( panel_height - bottom_offset );
            sticks[i].paintStick(g);
            if (i == pivot) {
                g.setColor(Color.black);
                drawVertArrow(g, sticks[i].x + 5, 20,
                        sticks[i].x + 5, sticks[i].y - sticks[i].h - 5);
                g.drawString("Pivot", sticks[i].x - 10, 15);
            } 
            if (i == currentLocation) {
                g.setColor(Color.blue);
                drawVertArrow(g, sticks[i].x + sticks[i].w/2, 60,
                   sticks[i].x + sticks[i].w/2, sticks[i].y - sticks[i].h - 5);
                g.drawString("i", sticks[i].x + sticks[i].w/2 - 2, 55);
            } 
            if (i == p_pos) {
                g.setColor(Color.magenta);
                drawVertArrow(g, sticks[i].x + sticks[i].w - 5, 40,
                  sticks[i].x + sticks[i].w - 5, sticks[i].y - sticks[i].h - 5);
                g.drawString("p_pos", sticks[i].x + sticks[i].w - 8, 35);
            }
        }

        g.setColor( Color.black );
        g.drawRect( 1, 1, w-2, panel_height-2 );

    } // paint()

    public void drawVertArrow(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);

	int[] axPoints = new int[3];
	int[] ayPoints = new int[3];

	axPoints[0] = x2;
	ayPoints[0] = y2;
	axPoints[1] = x2-1;
	ayPoints[1] = y2-2;
	axPoints[2] = x2+2;
	ayPoints[2] = y2-2;

	g.fillPolygon(axPoints, ayPoints, 3);
    }

    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()

} // class StickPanel
