
import java.awt.*;

/**
 * This is just another example implementation of the <code>DrawingObj</code>
 * interface. It creates a small Legend describing the color code used
 * in the drawing panel. This class has to be modified for different
 * application. The <code>draw</code> method has to be re-implemented
 * to reflex a particular application. This drawing object can be added
 * to the drawing panel by: <code><pre>
 *	drawingPanel.addDrawingObj(new Legend(x, y));
 * </pre></code>
 * @see ComBox
 * @see IntMatrix
 */
public class Legend implements DrawingObj {
    private int x, y;
    private Font font = new Font("Helvetica", Font.BOLD, 18);
    private Font font2 = new Font("Helvetica", Font.BOLD, 14);

    /**
     * Construct a legend object on the same drawing panel with the topleft
     * corner specified by the parameters.
     * @param x Left most position of the legend object.
     * @param y Right most position of the legend object.
     */
    public Legend(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Move the object to the position as specified by the parameter.
     * This coordinate represents the topleft corner of the object.
     * @param x The left most position of the legend object.
     * @param y The right most position of the legend object.
     */
    public void move(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Get the left most position of the legend object. This method is not
     * actually used. Just there for completeness when implementing the
     * <code>DrawingObj</code> interface.
     * @return The left most position of the legend object.
     */
    public int getX() {
	return x;
    }

    /**
     * Get the top most position of the legend object. This method is not
     * actually used. Just there for completeness when implementing the
     * <code>DrawingObj</code> interface.
     * @return The top most position of the legend object.
     */
    public int getY() {
	return y;
    }

    /**
     * This method draws the details of the legend object on the appropriate
     * graphical context, normally the drawing panel.
     * This method has to be modified for various application.
     */
    public void draw(Graphics g) {
	g.setColor(Color.gray);
	g.setFont(font);
	g.drawString("LEGEND", x + 11, y + 21);
	g.setColor(Color.red);
	g.drawString("LEGEND", x + 10, y + 20);
	g.setColor(Color.blue);
	g.fillRect(x + 10, y + 33, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x, y, 100, 75);
	g.drawLine(x, y + 25, x + 100, y+25);
	g.drawRect(x + 10, y + 33, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Pivot", x + 30, y + 43);

	g.setColor(Color.green);
	g.fillRect(x+10, y+58, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x+10, y+58, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Sorted", x + 30, y + 68);
    }
}
