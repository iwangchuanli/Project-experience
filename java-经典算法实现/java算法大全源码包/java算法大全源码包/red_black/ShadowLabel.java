
import java.awt.*;

/**
 * This is another example implementation of the <code>DrawingObj</code>
 * interface. This particular class enables a test string to be displayed
 * on the drawing panel with a shadowing effect.
 * For example, the following lines initializes an instance of this class
 * and display a text string "Hello" on the drawing panel with blue text color
 * and gray shadow at position (100, 100): <code><pre>
 *	ShadowLabel helloLabel = new ShadowLabel("Hello");
 *	drawingPanel.addDrawingObj(helloLabel);
 *	helloLabel.move(100, 100);
 *	helloLabel.setColor(Color.blue);
 *	drawingPanel.redraw();
 * </pre></code>
 * This object can be removed from the drawing panel by using the
 * <code>removeObj</code> method from <code>DrawingPanel</code>.
 * @see DrawingPanel#removeObj
 */
public class ShadowLabel implements DrawingObj {
    private int x, y;
    private String str;
    private Font font;
    private Color col = Color.black;

    /**
     * Set the text color of the drawing object.
     * @param col The new text color of the object.
     */
    public void setColor(Color col) {
	this.col = col;
    }

    /**
     * Set the text string for this drawing object.
     * @param str The new text string for label object.
     */
    public void setText(String str) {
	this.str = str;
    }

    /**
     * Get the text string for this label object.
     * @return the text string for this label object.
     */
    public String getText() {
	return new String(str);
    }

    /**
     * Construct a new shadow label with the initial text string as
     * specified by the parameter.
     * @param str The initial text string for this shadow label.
     */
    public ShadowLabel(String str) {
	this.str = str;
	font = new Font("Helvetica", Font.BOLD, 14);
    }
	
    /**
     * Get the left most positionn of the shadow label.
     * @return the x-coordinate of the bottom left corner of the shadow label.
     */
    public int getX() {
	return x;
    }

    /**
     * Get the bottom most positionn of the shadow label.
     * @return the y-coordinate of the bottom left corner of the shadow label.
     */
    public int getY() {
	return y;
    }

    /**
     * Move this shadow label to the new position with its bottom left
     * corner specified by the paramters.
     * @param x The left most position of the shadow label.
     * @param y The bottom most position of the shadow label.
     */
    public void move(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Draw the text string with a gray label. This method is normally
     * called from the drawing panel paint method.
     */
    public void draw(Graphics g) {
	g.setFont(font);
	g.setColor(Color.lightGray);
	g.drawString(str, x+1, y+1);
	g.setColor(col);
	g.drawString(str, x, y);
    }


	public void setFontSize(int size) {
		font = new Font("Helvetica", Font.BOLD, size);
		}

	public Font getFont() {
		return font;
		}
}
