
import java.awt.*;

/**
 * Interface for the drawing object to be drawn in the drawing panel.
 * @see DrawingPanel
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
     * @return The x coordinate of the drawing object's reference point.
     */
    public int getX();

    /**
     * @return The y coordinate of the drawing object's reference point.
     */
    public int getY();
} // interface
