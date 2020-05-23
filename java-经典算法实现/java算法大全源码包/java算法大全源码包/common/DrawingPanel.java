
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * <code>DrawingPanel</code> is the graphical panel attached to the
 * animation frame <code>AlgAnimFrame</code>. It contains the methods
 * to draw objects for the animation algorithm or instances of another
 * classes, which contain the drawing methods.
 * <p>
 * This class is <b>NOT TO BE MODIFIED</b>.
 * <p>
 * Any object class which implements the interface <code>DrawingObj</code>
 * can be added to this panel by calling the <code>addDrawingObj()</code>
 * method. Similarly, commentary box of type <code>ComBox</code> can be
 * added to the panel by using <code>addCom</code> method. Since the
 * commentary box (<code>ComBox</code>) is also implementing the interface
 * <code>DrawingObj</code>, it can also be added by using the
 * <code>addDrawingObj</code> method. However, <code>ComBox</code> added
 * to the drawing panel by using <code>addCom</code> will always appear on
 * top of any object added to the panel by using the <code>addDrawingObj</code>
 * method.
 * <p>
 * Note that last object added to the panel will be displayed on top.
 *
 * @see AlgAnimFrame#getDrawingPanel
 * @see DrawingObj
 */
public class DrawingPanel extends Panel {
    private int panel_height = 100;
    private int panel_width = 100;
    private int offset = 10;
    private int pref_height = 200;
    private int pref_width = 250;
    private int delayDuration = 200;
    private FontMetrics fm;
    private Dimension offscreensize = null;
    private Image offscreen = null;
    private Graphics offGraphics = null;
    private boolean noAnim = false;

    private Font bigFont;
    private Font smallFont, tinyFont, hugeFont, fixFont;

    private boolean skip = false;

    private Vector comment;
    private Vector drawingObj;

    /**
     * Creates a panel with white background and initializes the fonts
     * to be used during the animation.
     * @see DrawingPanel#getBigFont
     * @see DrawingPanel#getSmallFont
     * @see DrawingPanel#getTinyFont
     * @see DrawingPanel#getHugeFont
     * @see DrawingPanel#getFixFont
     */
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
	comment = new Vector();
	drawingObj = new Vector();
	init();
    } // DrawingPanel() 

    /**
     * Initialize the commentary boxes and drawing objects, 
     * removing all of them from the drawing panel. 
     */
    public void init() {
	comment = new Vector();
	drawingObj = new Vector();
    }
    
    /**
     * Causing a short delay of 1/3 of the normal delay duration.
     * This method is normally used during debugging to speed up
     * the achievement of certain system state.
     */
    public void shortDelay() {
	try {
	    Thread.sleep(delayDuration/3);
	} catch (InterruptedException e) {}
    }

    /**
     * Invoke a delay for a certain duration specified by 
     * <code>setDelay(int)</code>.
     * @see DrawingPanel#setDelay
     */
    public void delay() {
	try {
	  if (!skip)
	    Thread.sleep(delayDuration);
	  else
	    Thread.sleep(delayDuration/100);
	} catch (InterruptedException e) {}
    }

    private String toString(int i ) {
        return new String(""+ (char)('A' + i));
    }

    /**
     * Set the delay duration between animation update. This method
     * is normally called from the event handler of the simulation
     * delay menu bar.
     * @param delay Delay duration in milliseconds.
     */
    public void setDelay(int delay) {
	this.delayDuration = delay;
    }

    /**
     * Calls repaint() followed by delay(). Since these two methods
     * are being called very frequently in order, the redraw() method
     * is constructed to save typing.
     */
    public void redraw() {
	repaint();
	delay();
    }
    
    /**
     * This method is invoked when the <code>repaint()</code> method is called.
     * The <code>update</code> method is override here to eliminate flashing.
     * @param g Graphical context
     */
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

    /**
     * Method to draw objects on the drawing panel.
     */
    public void paint(Graphics g) {
	panel_height = size().height;
	panel_width = size().width;

    	for (int i = 0; i < drawingObj.size(); i++) {
	    DrawingObj dr = (DrawingObj)drawingObj.elementAt(i);
	    if (dr != null)
		dr.draw(g);
	    else
		drawingObj.removeElementAt(i);
	}

	for (int i = 0; i < comment.size(); i++) {
	    ComBox com = (ComBox)comment.elementAt(i);
	    if (com != null)
		com.draw(g);
	}

        g.setColor( Color.black );
        g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    } // paint()

    /**
     * Returns the initial preferred size of the drawing panel.
     * This method is called by the layout manager during the creation
     * of the corresponding drawing frame.
     * @return The dimension of the drawing panel.
     */
    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()

    /**
     * Returns the font objects initialized during the class initialization, 
     * which
     * can be readily used during the animation. Initialization fonts
     * on the fly during the animation will slow down the screen update
     * significantly (especially on SGI Irix platform). Therefore,
     * this fonts are only initialized during the creation of the class
     * and used by any other classes that require them to reduce
     * font initialization overhead.
     * @return Size 12 PLAIN Dialog font.
     */
    public Font getBigFont() {
	return bigFont;
    }

    /**
     * Return font objects initialized during the class initialization, which
     * can be readily used during the animation.
     * @return Size 10 PLAIN Dialog font.
     * @see DrawingPanel#getBigFont
     */
    public Font getSmallFont() {
	return smallFont;
    }

    /**
     * Return font objects initialized during the class initialization, which
     * can be readily used during the animation.
     * @return Size 8 PLAIN Dialog font.
     * @see DrawingPanel#getBigFont
     */
    public Font getTinyFont() {
	return tinyFont;
    }

    /**
     * Return font objects initialized during the class initialization, which
     * can be readily used during the animation.
     * @return Size 14 PLAIN Dialog font.
     * @see DrawingPanel#getBigFont
     */
    public Font getHugeFont() {
	return hugeFont;
    }

    /**
     * Return font objects initialized during the class initialization, which
     * can be readily used during the animation.
     * @return Size 12 PLAIN Courier font.
     * @see DrawingPanel#getBigFont
     */
    public Font getFixFont() {
	return fixFont;
    }

    /**
     * Get the height of the drawing panel.
     * @return Height of the drawing panel set during the initialization of the
     * class or after each invoke of the <code>repaint()</code> method.
     */
    public int getPanelHeight() {
	return panel_height;
    }

    /**
     * Get the width of the drawing panel.
     * @return Width of the drawing panel set during the initialization of the
     * class or after each invoke of the <code>repaint()</code> method.
     */
    public int getPanelWidth() {
	return panel_width;
    }

    /**
     * Get the number of pixel towards the edge of the panel which are not
     * going to be drawn.
     * @return Inset of the drawing panel, 
     * i.e. the edge pads on the four sides of
     * of the panel that are not to be drawn.
     */
    public int getOffset() {
	return offset;
    }

    /**
     * Set if the animation is in the 'SKIP' . . 'UNTIL' mode.
     * @param skip TRUE if the algorithm animation is to be fast forwarded
     * to a specific point of execution. FALSE otherwise.
     */
    public void setSkip(boolean skip) {
	this.skip = skip;
    }

    /**
     * Set if the animation is disable.
     * @param noAnim TRUE is animation is disabled; FALSE otherwise.
     */
    public void setNoAnim(boolean noAnim) {
	this.noAnim = noAnim;
    }

    /**
     * Check if the noAnim flag is set.
     * @return If the noAnim flag is set.
     */
    public boolean getNoAnim() {
	return noAnim;
    }

    /**
     * Check if the animationis in the skip mode.
     * @return Returns if the animation is in the 'SKIP' . . 'UNTIL' mode.
     */
    public boolean getSkip() {
	return skip;
    }

    /**
     * Adds a drawing object to the canvas.
     * @param obj The object to be added.
     */
    public void addDrawingObj(DrawingObj obj) {
	if (isObjExist(obj))
	    removeObj(obj);
	drawingObj.addElement(obj);
    }

    /**
     * Checks if a certain object has already been added to the drawing
     * panel.
     * @return Returns TRUE if the drawing object has been added;
     *          FALSE otherwise.
     * @param obj The drawing object to be checked.
     */
    public boolean isObjExist(DrawingObj obj) {
        boolean res = false;
 
	for (int i = 0; i < drawingObj.size(); i++) {
            DrawingObj dobj = (DrawingObj)drawingObj.elementAt(i);
            if (dobj.equals(obj)) {
                res = true;
		break;
	    }
        }
 
        return res;
    }

    /**
     * Remove a drawing object which matches the parameter.
     * @param obj The drawing object to be removed.
     * @return TRUE if the object has been successfully deleted; FALSE
     *	otherwise.
     */
    public boolean removeObj(DrawingObj obj) {
	if (obj == null) return false;
	for (int i = 0; i < drawingObj.size(); i++) {
	    DrawingObj ob = (DrawingObj)drawingObj.elementAt(i);
	    if (ob.equals(obj)) {
		drawingObj.removeElementAt(i);
		return true;
	    }
	}
	return false;
    }

    /**
     * Adds a commentary box to the drawing panel.
     * @param com The commentary box to be added.
     */
    public void addCom(ComBox com) {
	if (isComExist(com))
	    removeCom(com);
	comment.addElement(com);
    }

    /**
     * Check if a commentary box has already been added to the drawing panel.
     * @return Returns TRUE if the commentary box has been added;
     *		FALSE otherwise.
     * @param com The commentary box to be checked.
     */
    public boolean isComExist(ComBox com) {
	boolean res = false;

	for (int i = 0; i < comment.size(); i++) {
	    ComBox com2 = (ComBox)comment.elementAt(i);
	    if (com2.equals(com)) {
		res = true;
		break;
	    }
	}

	return res;
    }

    /**
     * Remove the commentary box.
     * @param com The combox to be removed.
     */
    public void removeCom(ComBox com) {
	if (com == null) return;
	for (int i = 0; i < comment.size(); i++) {
	    ComBox com2 = (ComBox)comment.elementAt(i);
	    if (com2.equals(com)) {
		comment.removeElementAt(i);
		break;
	    }
	}
    }

/* ----------------------- Animation methods ----------------------- */
    int animStep = 5;

    /**
     * Set the animation step, i.e. the number of step for an object
     * to move from one point to another.
     * @param step The number of animation step.
     */
    public void setAnimStep(int step) {
	this.animStep = step;
    }

    /**
     * Animate a single drawing object through a sequence of points
     * specified by the <code>Vector</code> construction.
     * Each element of the vector is of type Point, where the first
     * is the source and the last is the destination of the trajectory
     * to be animated on the object.
     * @param obj The drawing object to be move.
     * @param pts The trajectory the drawing object is to be moved.
     */
    public void animate(DrawingObj obj, Vector pts) {
	if (skip) return;
	if (pts.size() > 1) {
	    Point src = (Point)pts.firstElement();

	    for (int i = 1; i < pts.size(); i++) {
		if (noAnim || skip) {
		    Point finalPt = (Point)pts.lastElement();
		    obj.move(finalPt.x, finalPt.y);
		    break;
		}
		Point dest = (Point)pts.elementAt(i);

		for (int j = 0; j < animStep; j++) {
		    int x = src.x + (dest.x - src.x)*(j+1) / animStep; 
		    int y = src.y + (dest.y - src.y)*(j+1) / animStep; 
		    obj.move(x, y);
		    repaint(); delay();
		}
		obj.move(dest.x, dest.y);
		repaint(); delay();
	
		src = new Point(dest.x, dest.y);
	    }
	} else if (pts.size() > 0) {
	    Point pt = (Point)pts.firstElement();
	    obj.move(pt.x, pt.y);
	    repaint();
	}
    }

    /**
     * Animate a list of drawing objects store in an array.
     * The trajectories of these objects are passed in through the
     * second parameter. The size of the arrays passed in through
     * both parameters should be equal. Otherwise, nothing will be performed.
     * The number of points in all the trajectories must be equal too.
     * @param objs Array holding the drawing objects to be animated.
     * @param pts Array holding the trajectories of the drawing objects.
     */
    public void animate(DrawingObj[] objs, Vector[] pts) {
	if (skip) return;
	if (objs.length != pts.length)
	    return;
	else if (objs.length < 1)
	    return;

	// check if the number of points for all trajectories are equal
	for (int i = 1; i < objs.length; i++) {
	    if (pts[i].size() != pts[i-1].size())
		return;
	}

	if (pts[0].size() > 1) {
	    if (noAnim || skip) {
		for (int i = 0; i < objs.length; i++) {
		    Point finalPt = (Point)pts[i].lastElement();
		    objs[i].move(finalPt.x, finalPt.y);
		}
		return;
	    }
	    Point[] src = new Point[objs.length];
	    for (int i = 0; i < objs.length; i++) {
		if (pts[i].size() > 1) {
		    src[i] = (Point)pts[i].firstElement();
		} else
		    src[i] = new Point(-1, -1);
	    }

	    for (int i = 1; i < pts[0].size(); i++) {
		Point[] dest = new Point[objs.length];
		for (int j = 0; j < objs.length; j++) {
		    dest[j] = (Point)pts[j].elementAt(i);
		}

		for (int j = 0; j < animStep; j++) {
		    for (int k = 0; k < objs.length; k++) {
		    	int x = src[k].x + 
				(dest[k].x - src[k].x)*(j+1) / animStep; 
		    	int y = src[k].y + 
				(dest[k].y - src[k].y)*(j+1) / animStep; 
		    	objs[k].move(x, y);
		    }
		    repaint(); delay();
		}
		for (int k = 0; k < objs.length; k++) {
		    objs[k].move(dest[k].x, dest[k].y);
		    src[k] = new Point(dest[k].x, dest[k].y);
		}
		repaint(); delay();
	
	    }
	    for (int i = 0; i < objs.length; i++) {
		if (pts[i].size() > 0) {
	    	    Point pt = (Point)pts[i].lastElement();
	    	    objs[i].move(pt.x, pt.y);
		}
	    }
	    repaint();
	} else if (pts[0].size() > 0) {
	    for (int i = 0; i < objs.length; i++) {
		if (pts[i].size() > 0) {
	    	    Point pt = (Point)pts[i].lastElement();
	    	    objs[i].move(pt.x, pt.y);
		}
	    }
	    repaint();
	}
    }
} // class DrawingPanel
