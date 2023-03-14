/* ComBox.java */

import java.awt.*;
import java.lang.*;

/**
 * This class composes a commentary box to be drawn on any drawing panel/canvas.
 * The width of the commentary box will be determined by the length of the
 * text string being displayed.
 */
public class ComBox {
    /**
     * The text string to be displayed on the commentary box.
     * @see String
     */
    public String str;
    /**
     * Top left position of the commentary box. This attribute consists
     * of two components: <b>x</b> and <b>y</b> based on the Point class
     * in <code>java.awt.*</code>.
     * @see Point
     */
    public Point topLeft;
    int height, width;
    Font font;
    int[] xPts, yPts;
    int nPts;
    /**
     * Background color of the commentary box specified by <code>
     * java.awt.Color</code>.
     * @see Color
     */
    public Color bg;
    /**
     * Foreground color (text color) of the commentary box specified by <code>
     * java.awt.Color</code>.
     * @see Color
     */
    public Color fg;

    /**
     * Creates a commentary box with its topleft corner at (topLeftX, topLeftY)
     * and commentary string specified by the third <code>String</code>
     * parameter. By default, the background color is yellow and
     * the text is displayed in blue.
     * @param topLeftX Integer value specifying the horizontal position of the
     * topleft corner of the commentary box.
     * @param topLeftY Integer value specifying the vertical position of the
     * topleft corner of the commentary box.
     * @param str The text string to be displayed on the commentary box.
     */
    public ComBox(int topLeftX, int topLeftY, String str, Font font) {
	this.topLeft = new Point(topLeftX, topLeftY);
	this.str = str;
	this.fg = Color.blue;
	this.bg = Color.yellow;
	this.font = font;
	height = 30;
	width = 7*str.length() + 6;
	nPts = 8;
	xPts = new int[nPts];
	yPts = new int[nPts];
	xPts[0] = topLeft.x + 4; xPts[1] = topLeft.x + width - 4;
	xPts[2] = xPts[3] = topLeft.x + width;
	xPts[4] = xPts[1]; xPts[5] = xPts[0];
	xPts[6] = xPts[7] = topLeft.x;
	yPts[0] = yPts[1] = topLeft.y;
	yPts[2] = topLeft.y + 4;
	yPts[3] = topLeft.y + height - 4;
	yPts[4] = yPts[5] = topLeft.y + height;
	yPts[6] = yPts[3];
	yPts[7] = yPts[2];
    }

    /**
     * Creates a commentary box with its topleft corner at (topLeftX, topLeftY)
     * and commentary string specified by the third <code>String</code>
     * parameter, using the foreground and background colors indicated by
     * the fourth and fifth parameters. 
     * 
     * @param topLeftX Integer value specifying the horizontal position of the
     * topleft corner of the commentary box.
     * @param topLeftY Integer value specifying the vertical position of the
     * topleft corner of the commentary box.
     * @param str The text string to be displayed on the commentary box.
     * @param fg Color of the text in the commentary box.
     * @param bg Color of the commentary box background.
     */
    public ComBox(int topLeftX, int topLeftY, String str, Color fg, Color bg,
		Font font) {
	this.topLeft = new Point(topLeftX, topLeftY);
	this.str = str;
	this.fg = fg;
	this.bg = bg;
	this.font = font;
	height = 30;
	width = 7*str.length() + 6;
	nPts = 8;
	xPts = new int[nPts];
	yPts = new int[nPts];
	xPts[0] = topLeft.x + 4; xPts[1] = topLeft.x + width - 4;
	xPts[2] = xPts[3] = topLeft.x + width;
	xPts[4] = xPts[1]; xPts[5] = xPts[0];
	xPts[6] = xPts[7] = topLeft.x;
	yPts[0] = yPts[1] = topLeft.y;
	yPts[2] = topLeft.y + 4;
	yPts[3] = topLeft.y + height - 4;
	yPts[4] = yPts[5] = topLeft.y + height;
	yPts[6] = yPts[3];
	yPts[7] = yPts[2];
    }

    /**
     * Set the text string to be displayed on the commentary box.
     * @param str Text string in the form of class String
     */
    public void setText(String str) {
	this.str = str;
        width = 7*str.length() + 6;
        xPts[0] = topLeft.x + 4; xPts[1] = topLeft.x + width - 4;
        xPts[2] = xPts[3] = topLeft.x + width;
        xPts[4] = xPts[1]; xPts[5] = xPts[0];
        xPts[6] = xPts[7] = topLeft.x;
        yPts[0] = yPts[1] = topLeft.y;
        yPts[2] = topLeft.y + 4;
        yPts[3] = topLeft.y + height - 4;
        yPts[4] = yPts[5] = topLeft.y + height;
        yPts[6] = yPts[3];
        yPts[7] = yPts[2];
    }

    /**
     * Set the top left position of the commentary box.
     * @param topLeftX Horizontal position of the top left corner.
     * @param topLeftY Vertical position of the top left corner.
     */
    public void setTopLeft(int topLeftX, int topLeftY) {
	this.topLeft = new Point(topLeftX, topLeftY);
        xPts[0] = topLeft.x + 4; xPts[1] = topLeft.x + width - 4;
        xPts[2] = xPts[3] = topLeft.x + width;
        xPts[4] = xPts[1]; xPts[5] = xPts[0];
        xPts[6] = xPts[7] = topLeft.x;
        yPts[0] = yPts[1] = topLeft.y;
        yPts[2] = topLeft.y + 4;
        yPts[3] = topLeft.y + height - 4;
        yPts[4] = yPts[5] = topLeft.y + height;
        yPts[6] = yPts[3];
        yPts[7] = yPts[2];
    }

    /**
     * Set the background color of the commentary box.
     * @param col Background color, instance of java.awt.color
     */
    public void setBackground(Color col) {
	this.bg = col;
    }

    /**
     * Set the text color of the commentary box.
     * @param col Foreground color, instance of java.awt.color
     */
    public void setColor(Color col) {
	this.fg = col;
    }

    /**
     * This method is to be called from the <code>paint(Graphics g)</code>
     * method of the drawing panel/canvas. It uses the graphical context
     * of the drawing panel/canvas to construct the commentary box on
     * the drawing panel/canvas.
     * @param g Graphical context from the <code>paint(Graphics g)</code>
     * method of the drawing panel/canvas.
     * @see Graphics
     */
    public void draw(Graphics g) {
	g.setColor(bg);
	g.fillPolygon(xPts, yPts, nPts);

	g.setColor(Color.black);
	g.drawPolygon(xPts, yPts, nPts);

	g.setColor(fg);
	g.setFont(font);
	g.drawString(str, topLeft.x + 4, topLeft.y + height - 10);
    }
}
