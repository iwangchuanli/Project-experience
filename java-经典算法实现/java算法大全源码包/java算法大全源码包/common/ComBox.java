/* ComBox.java */

import java.awt.*;
import java.lang.*;

/**
 * This class composes a commentary box to be drawn on any drawing panel/canvas.
 * The width of the commentary box will be determined by the length of the
 * text string being displayed.
 * <p>
 * Since this object implements <CODE>DrawingObj</CODE>, it can be added
 * into the <CODE>DrawingPanel</CODE> by using either the <code>addCom</code>
 * or <code>addDrawingObj</code> method. An added commentary box can be
 * removed by calling the <code>removeCom</code> or <code>removeObj</code>
 * method of the <code>DrawingPanel</code> class.
 * <p>
 * If the <code>addCom</code> method
 * is called, the added commentary box is guaranteed to be displayed on
 * top of all drawing objects added to the panel using 
 * <code>addDrawingObj</code>.
 * Otherwise, if the commentary box is added to the panel using
 * <code>addDrawingObj</code>, the last added object will be on top.
 * <p>
 * Typically, a commentary box can be initialized and added to a
 * drawing panel and displayed by using the following statements:
 * <code> <pre>
 *      ComBox com = new ComBox(x, y, "Example Comment", font);
 *      drawingPanel.addCom(com);
 *      drawingPanel.redraw();
 * </pre> </code>
 * It is removed from the drawing panel by:
 * <code> <pre>
 *      drawingPanel.removeCom(com);
 * </pre> </code>
 * where <code>drawingPanel</code> is an instance of the class object
 * <code>DrawingPanel</code>, <code>x</code> and <code>y</code> define
 * the top left position of the commentary box and <code>font</code>
 * is an instance of the <code>Font</code> object, typically a fixed font,
 * which is declared as follows:
 * <code><pre>
 *      Font font = new Font("Courier", Font.PLAIN, 12);
 * </pre></code>
 * <B>Note</B> that the size-12 courier font is recommended for all situation
 * since the width of the com box is calculated based on this particular
 * size font. Otherwise, the dimension has to be modified.
 *
 * @see DrawingPanel
 * @see DrawingPanel#addCom
 * @see DrawingPanel#addDrawingObj
 * @see DrawingPanel#redraw
 */
public class ComBox implements DrawingObj {
    private String str;
    private Point topLeft;
    private int height, width;
    private Font font;
    private int[] xPts, yPts;
    private int nPts;
    private Color bg;
    private Color fg;

    /**
     * Creates a commentary box with its topleft corner at (topLeftX, topLeftY)
     * and the commentary text specified by the third <code>String</code>
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
     * and the commentary text specified by the third <code>String</code>
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
     * The background color of commentary box is specified by <code>
     * java.awt.Color</code>.
     * @param col Background color, instance of java.awt.color
     * @see Color
     */
    public void setBackground(Color col) {
	this.bg = col;
    }

    /**
     * Set the text color of the commentary box.
     * This foreground color (text color) is
     * specified by <code>
     * java.awt.Color</code>.
     * @see Color
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
	if (str.length() < 1) return;
	g.setColor(bg);
	g.fillPolygon(xPts, yPts, nPts);

	g.setColor(Color.black);
	g.drawPolygon(xPts, yPts, nPts);

	g.setColor(fg);
	g.setFont(font);
	g.drawString(str, topLeft.x + 4, topLeft.y + height - 10);
    }

    /**
     * Move the top left position of the commentary box.
     * @param x Horizontal position of the top left corner.
     * @param y Vertical position of the top left corner.
     * @see setTopLeft
     */
    public void move(int x, int y) {
	setTopLeft(x, y);
    }

    /**
     * X coordinate of the top left position of the commentary box.
     * @return The X coordinate of the top left corner of the commentary
     * box.
     */
    public int getX() {
	return topLeft.x;
    }

    /**
     * Y coordinate of the top left position of the commentary box.
     * @return The Y coordinate of the top left corner of the commentary
     * box.
     */
    public int getY() {
	return topLeft.y;
    }

    /**
     * This method is called to obtain the current text string to be
     * displayed in the commentary box.
     * @return The text string to be displayed on the commentary box.
     */
    public String getText() {
	return str;
    }
}
