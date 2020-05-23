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
public class ScribblePane1 extends JPanel
    implements MouseListener, MouseMotionListener {
    protected int last_x, last_y;  // Previous mouse coordinates

    public ScribblePane1() {
	// This component registers itself as an event listener for
	// mouse events and mouse motion events.
	this.addMouseListener(this);
	this.addMouseMotionListener(this);

	// Give the component a preferred size
	setPreferredSize(new Dimension(450,200));
    }

    // A method from the MouseListener interface.  Invoked when the
    // user presses a mouse button.
    public void mousePressed(MouseEvent e) {
	last_x = e.getX();  // remember the coordinates of the click
	last_y = e.getY();
    }
    
    // A method from the MouseMotionListener interface.  Invoked when the
    // user drags the mouse with a button pressed.
    public void mouseDragged(MouseEvent e) {
	int x = e.getX();    // Get the current mouse position
	int y = e.getY();
	// Draw a line from the saved coordinates to the current position
	this.getGraphics().drawLine(last_x, last_y, x, y);
	last_x = x;          // Remember the current position
	last_y = y;
    }
    
    // The other, unused methods of the MouseListener interface.
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    // The other, unused, method of the MouseMotionListener interface.
    public void mouseMoved(MouseEvent e) {}
}
