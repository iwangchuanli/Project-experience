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
 * A simple JPanel subclass that uses event listeners to allow the user
 * to scribble with the mouse.  Note that scribbles are not saved or redrawn.
 **/
public class ScribblePane2 extends JPanel {
    public ScribblePane2() {
	// Give the component a preferred size
	setPreferredSize(new Dimension(450,200));

	// Register a mouse event handler defined as an inner class
	// Note the call to requestFocus().  This is required in order for
	// the component to receive key events.
	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) { 
		    moveto(e.getX(), e.getY());  // Move to click position
		    requestFocus();              // Take keyboard focus
		}
	    });

	// Register a mouse motion event handler defined as an inner class
	// By subclassing MouseMotionAdapter rather than implementing
	// MouseMotionListener, we only override the method we're interested
	// in and inherit default (empty) implementations of the other methods.
	addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		    lineto(e.getX(), e.getY());  // Draw to mouse position
		}
	    });

	// Add a keyboard event handler to clear the screen on key 'C'
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_C) clear();
		}
	    });
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
	Graphics g = getGraphics();          // Get the object to draw with
	g.setColor(color);                   // Tell it what color to use
	g.drawLine(last_x, last_y, x, y);    // Tell it what to draw
	moveto(x, y);                        // Save the current point
    }

    /**
     * Clear the drawing area, using the component background color.  This
     * method works by requesting that the component be redrawn.  Since this
     * component does not have a paintComponent() method, nothing will be
     * drawn.  However, other parts of the component, such as borders or
     * sub-components will be drawn correctly.
     **/
    public void clear() { repaint(); }

    /** This field holds the current drawing color property */
    Color color = Color.black; 
    /** This is the property "setter" method for the color property */
    public void setColor(Color color) { this.color = color; }
    /** This is the property "getter" method for the color property */
    public Color getColor() { return color; }

}
