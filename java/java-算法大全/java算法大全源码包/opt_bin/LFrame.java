/* LFrame.java */

import java.awt.*;

/**
 * <code>LFrame</code> is an extension of the <code>java.awt.Frame</code> class
 * customized to display the legend of the color code used in the
 * animation panel.
 */
public class LFrame extends Frame {

    /**
     * The dimension of the frame in the number of pixels.
     */
    public int width = 480, height = 320;

    /**
     * Creates a new legend frame.
     * @param colors Array of Color using in the DrawingPanel.
     * @param strings The descriptions of each color representation.
     */
    public LFrame(Color[] colors, String[] strings) {
	setLayout(new BorderLayout());
	add("Center", new Symbol(colors, strings));
	setTitle("Legends");
        pack();
        validate();
        show();
    } // init()

    /**
     * Set the dimension of the legend window.
     * @param width The width of the window.
     * @param height The height of the window.
     */
    public void setDimension(int width, int height) {
	this.width = width;
	this.height = height;
    }

    class Symbol extends Panel {
	Color[] col;
	String[] str;
	public Symbol( Color[] col, String[] str ) {
	    this.col = col;
	    this.str = str;
	    setBackground( Color.white );
	    setFont( new Font("Dialog", Font.PLAIN, 12) );
	}
	
	public void paint(Graphics g) {
	    for (int i = 0; i < col.length; i++) {
	    	g.setColor( col[i] );
	    	g.fillRect( 20, 15 + i*30, 10, 10 );
	    	g.drawLine( 5, 20 + i*30, 45, 20 + i*30 );
	    }

	    g.setColor( Color.black );
	    for (int i = 0; i < str.length; i++) {
		g.drawString(str[i], 50, 25 + i*30);
	    }
	}

	public Dimension getPreferredSize() {
	    return new Dimension( 480, 320 );
	}
    }

    /**
     * Returns the dimension of the window when probed by the window
     * manager.
     */
    public Dimension preferredSize() {
        return new Dimension(width, height);
    }

    /**
     * Legend window event handling method to dispose the legend frame
     * when the WINDOW_DESTROY event is received.
     * @param event Event invoked.
     * @see Event
     */
    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            dispose();
        }
        return super.handleEvent(event);
    }
} // class LFrame
