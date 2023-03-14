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
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class AppletMenuBar extends Panel {
    // Menubar contents
    Vector labels = new Vector();
    Vector menus = new Vector();

    // Properties
    Insets margins = new Insets(3, 10, 3, 10); // top, left, bottom, right
    int spacing = 10;              // Space between menu labels
    Color highlightColor;          // Rollover color for labels

    // internal stuff
    boolean remeasure = true;      // Whether the labels need to be remeasured
    int[] widths;                          // The width of each label
    int[] startPositions;                  // Where each label starts
    int ascent, descent;                   // Font metrics
    Dimension prefsize = new Dimension();  // How big do we want to be?
    int highlightedItem = -1;              // Which item is the mouse over?

    /**
     * Create a new component that simulates a menubar by displaying
     * the specified labels.  Whenever the user clicks the specified label,
     * popup up the PopupMenu specified in the menus array.
     * Elements of the menus arra may be a static PopupMenu object, or
     * a PopupMenuFactory object for dynamically creating menus.
     * Perhaps we'll also provide some other kind of constructor or factory
     * method that reads popup menus out of a config file.
     */
    public AppletMenuBar() {
	// We'd like these kinds of events to be delivered
	enableEvents(AWTEvent.MOUSE_EVENT_MASK |
		     AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    /** Add a popup menu to the menubar */
    public void addMenu(String label, PopupMenu menu) {
	insertMenu(label, menu, -1);
    }

    /** Insert a popup menu into the menubar */
    public void insertMenu(String label, PopupMenu menu, int index) {
	if (index < 0) index += labels.size()+1;  // Position to put it at
	this.add(menu);                           // Popup belongs to us
	labels.insertElementAt(label, index);     // Remember the label
	menus.insertElementAt(menu, index);       // Remember the menu
	remeasure = true;                         // Remeasure everything
	invalidate();                             // Container must relayout
    }

    /** Property accessor methods for margins property */
    public Insets getMargins() { return (Insets) margins.clone(); }
    public void setMargins(Insets margins) {
	this.margins = margins;
	remeasure = true;
	invalidate();
    }

    /** Property accessor methods for spacing property */
    public int getSpacing() { return spacing; }
    public void setSpacing(int spacing) {
	if (this.spacing != spacing) {
	    this.spacing = spacing;
	    remeasure = true;
	    invalidate();
	}
    }

    /** Accessor methods for highlightColor property */
    public Color getHighlightColor() {
	if (highlightColor == null) return getForeground();
	else return highlightColor;
    }
    public void setHighlightColor(Color c) {
	if (highlightColor != c) {
	    highlightColor = c;
	    repaint();
	}
    }

    /** We override the setFont() method so we can remeasure */
    public void setFont(Font f) {
	super.setFont(f);
	remeasure = true;
	invalidate();
    }

    /** Override these color property setter method so we can repaint */
    public void setForeground(Color c) {
	super.setForeground(c);
	repaint();
    }
    public void setBackground(Color c) {
	super.setBackground(c);
	repaint();
    }

    /**
     * This method is called to draw tell the component to redraw itself.
     * If we were implementing a Swing component, we'd override 
     * paintComponent() instead
     **/
    public void paint(Graphics g) {
	if (remeasure) measure();  // Remeasure everything first, if needed
	
	// Figure out Y coordinate to draw at
	Dimension size = getSize();
	int baseline = size.height - margins.bottom - descent;
	// Set the font to draw with
	g.setFont(getFont());
	// Loop through the labels
	int nummenus = labels.size();
	for(int i = 0; i < nummenus; i++) {
	    // Set the drawing color.  Highlight the current item
	    if ((i == highlightedItem) && (highlightColor != null))
		g.setColor(getHighlightColor());
	    else
		g.setColor(getForeground());

	    // Draw the menu label at the position computed in measure()
	    g.drawString((String)labels.elementAt(i), 
			 startPositions[i], baseline);
	}

	// Now draw a groove at the bottom of the menubar.
	Color bg = getBackground();
	g.setColor(bg.darker());
	g.drawLine(0, size.height-2, size.width, size.height-2);
	g.setColor(bg.brighter());
	g.drawLine(0, size.height-1, size.width, size.height-1);
    }

    /** Called when a mouse event happens over the menubar */
    protected void processMouseEvent(MouseEvent e) {
	int type = e.getID();             // What type of event?
	int item = findItemAt(e.getX());  // Over which menu label?

	if (type == MouseEvent.MOUSE_PRESSED) {
	    // If it was a mouse down event, then pop up the menu
	    if (item == -1) return;
	    Dimension size = getSize();
	    PopupMenu pm = (PopupMenu) menus.elementAt(item);
	    if (pm != null) pm.show(this, startPositions[item]-3, size.height);

	}
	else if (type == MouseEvent.MOUSE_EXITED) {
	    // If the mouse left the menubar, then unhighlight
	    if (highlightedItem != -1) {
		highlightedItem = -1;
		if (highlightColor != null) repaint();
	    }
	}
	else if ((type == MouseEvent.MOUSE_MOVED) || 
		 (type == MouseEvent.MOUSE_ENTERED)) {
	    // If the mouse moved, change the highlighted item, if necessary
	    if (item != highlightedItem) {
		highlightedItem = item;
		if (highlightColor != null) repaint();
	    }
	}
    }

    /** This method is called when the mouse moves */
    protected void processMouseMotionEvent(MouseEvent e) {
	processMouseEvent(e);
    }

    /** This utility method converts an X coordinate to a menu label index */
    protected int findItemAt(int x) {
	// This could be a more efficient search...
	int nummenus = labels.size();
	int halfspace = spacing/2-1;
	int i;
	for(i = nummenus-1; i >= 0; i--) {
	    if ((x >= startPositions[i]-halfspace) && 
		(x <= startPositions[i]+widths[i]+halfspace)) break;
	}
	return i;
    }


    /**
     * Measure the menu labels, and figure out their positions, so we
     * can determine when a click happens, and so we can redraw efficiently.
     **/
    protected void measure() {
	// Get information about the font
	FontMetrics fm = this.getFontMetrics(getFont());
	// Remember the basic font size
	ascent = fm.getAscent();
	descent = fm.getDescent();
	// Create arrays to hold the measurements and positions
	int nummenus = labels.size();
	widths = new int[nummenus];
	startPositions = new int[nummenus];

	// Measure the label strings and
	// figure out the starting position of each label
	int pos = margins.left;
	for(int i = 0; i < nummenus; i++) {
	    startPositions[i] = pos;
	    String label = (String)labels.elementAt(i);
	    widths[i] = fm.stringWidth(label);
	    pos += widths[i] + spacing;
	}

	// Compute our preferred size from this data
	prefsize.width = pos - spacing + margins.right;
	prefsize.height = ascent + descent + margins.top + margins.bottom;

	// We've don't need to be remeasured anymore.
	remeasure = false;
    }

    /**
     * These methods tell the container how big the menubar wants to be.
     * 
     **/
    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
	if (remeasure) measure();
	return prefsize;
    }
    /** @deprecated Here for compatibility with Java 1.0 */
    public Dimension minimumSize() { return getPreferredSize(); }
    /** @deprecated Here for compatibility with Java 1.0 */
    public Dimension preferredSize() { return getPreferredSize(); }

    /** 
     * This method is called when the underlying AWT component is created.
     * We can't measure ourselves (no font metrics) until this is called.
     **/
    public void addNotify() {
	super.addNotify();
	measure();
    }

    /** This method tells the container not to give us keyboard focus */
    public boolean isFocusTraversable() { return false; }
}

