
import java.awt.*;

/**
 * Interface for the drawing object to be drawn in the drawing panel.
 * <p>
 * Any graphical objects to be displayed on the <code>DrawingPanel</code>
 * should implement this interface.
 * All the abstract methods of the interface must be defined in the
 * object class defining the graphical object.
 * <p>
 * For example, if <code>Box</code> is a class which implements
 * <code>DrawingObj</code>, i.e. the class declaration of <code>Box</code>
 * starts with the following line: <code><pre>
 *      class Box implements DrawingObj {
 *        ...
 * </pre></code>
 * Then any instance of class <code>Box</code> can be added to the drawing
 * canvas as follows: <code><pre>
 *      Box box = new Box(...);
 *      drawingPanel.addDrawingObj(box);
 *      box.move(x, y);
 *      drawingPanel.redraw();
 * </pre></code>
 * The first line declares an instance of class <code>Box</code> called
 * <code>box</code>. The next line uses the method <code>addDrawingObj</code>
 * to add <code>box</code> into the canvas <code>drawingPanel</code>, which
 * is an instance of the object class <code>DrawingPanel</code>.
 * <p>
 * The <code>move</code> method of the drawing object (which must be
 * specified) is then called to move the corresponding object to position
 * <code>(x, y)</code>. Finally, the <code>redraw()</code> method of
 * <code>DrawingPanel</code> class is called to refresh the panel and
 * delay for the object to be visible.
 * @see DrawingPanel
 * @see DrawingPanel#addDrawingObj
 * @see DrawingPanel#redraw
 */
public interface DrawingObj {
    /**
     * Paint method of the drawing object.
     * @param g A reference to the graphical context.
     */
    public void draw(Graphics g);
	
    /**
     * This method repositions the drawing object to the new location
     * specified by the paramters.
     * @param x The x coordinate of the drawing object's new position.
     * @param y The y coordinate of the drawing object's new position.
     */
    public void move(int x, int y);

    /**
     * Returns the x coordinate of the drawing object's reference point.
     * @return The x coordinate of the drawing object's reference point.
     */
    public int getX();

    /**
     * Returns the y coordinate of the drawing object's reference point.
     * @return The y coordinate of the drawing object's reference point.
     */
    public int getY();

	public Dimension getLimit( int dirn );
	/**
	* Returns the limit of this object in the specified direction
	  dirn = +1 = +x 
	  dirn = +2 = +y
	         -1 = -x
                 -2 = -y
	*/
} // interface
