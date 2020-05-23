/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.gui;
import javax.swing.*;       // For JPanel component
import java.awt.*;          // For Graphics object
import java.awt.event.*;    // For Event and Listener objects

/**
 * Another scribble class.  This one overrides the low-level event processing
 * methods of the component instead of registering event listeners.
 **/
public class ScribblePane4 extends JPanel {
    public ScribblePane4() {
	// Give the component a preferred size
	setPreferredSize(new Dimension(450,200));

	// Tell the system what kind of events the component is interested in
	enableEvents(AWTEvent.MOUSE_EVENT_MASK |
		     AWTEvent.MOUSE_MOTION_EVENT_MASK |
		     AWTEvent.KEY_EVENT_MASK);
    }

    public void processMouseEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_PRESSED) {
	    moveto(e.getX(), e.getY());
	    requestFocus();
	}
	else super.processMouseEvent(e); // pass unhandled events to superclass
    }

    public void processMouseMotionEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_DRAGGED) lineto(e.getX(), e.getY());
	else super.processMouseMotionEvent(e);
    }

    public void processKeyEvent(KeyEvent e) {
	if ((e.getID() == KeyEvent.KEY_PRESSED) && 
	    (e.getKeyCode() == KeyEvent.VK_C)) clear();
	else super.processKeyEvent(e);   // Give superclass a chance to handle
    }
    
    /** These are the coordinates of the the previous mouse position */
    protected int last_x, last_y;

    /** Remember the specified point */
    public void moveto(int x, int y) {
	last_x = x;
	last_y = y;
    }

    /** Draw from the last point to this point, then remember new point */
    public void lineto(int x, int y) {
	getGraphics().drawLine(last_x, last_y, x, y);
	moveto(x, y);
    }

    /** Clear the drawing area, using the component background color */
    public void clear() { repaint(); }
}
