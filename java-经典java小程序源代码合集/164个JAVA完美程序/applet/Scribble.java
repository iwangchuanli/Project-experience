/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.applet;
import java.applet.*;
import java.awt.*;

/**
 * This applet lets the user scribble with the mouse.
 * It demonstrates the Java 1.0 event model.
 **/
public class Scribble extends Applet {
    private int lastx, lasty;    // Remember last mouse coordinates.
    Button erase_button;         // The Erase button.
    
    /** Initialize the erase button, ask for keyboard focus */
    public void init() {
	erase_button = new Button("Erase");  
	this.add(erase_button);
	this.setBackground(Color.white);  // Set background color for scribble
	this.requestFocus();  // Ask for keyboard focus so we get key events
    }

    /** Respond to mouse clicks */
    public boolean mouseDown(Event e, int x, int y) {
	lastx = x; lasty = y;             // Remember where the click was
	return true;
    }

    /** Respond to mouse drags */
    public boolean mouseDrag(Event e, int x, int y) {
	Graphics g = getGraphics();
	g.drawLine(lastx, lasty, x, y);   // Draw from last position to here
	lastx = x; lasty = y;             // And remember new last position
	return true;
    }

    /** Respond to key presses: Erase drawing when user types 'e' */
    public boolean keyDown(Event e, int key) {
	if ((e.id == Event.KEY_PRESS) && (key == 'e')) {
	    Graphics g = getGraphics();
	    g.setColor(this.getBackground());   
	    g.fillRect(0, 0, bounds().width, bounds().height);
	    return true;
	}
	else return false;
    }

    /** Respond to Button clicks: erase drawing when user clicks button */
    public boolean action(Event e, Object arg) {
	if (e.target == erase_button) {
	    Graphics g = getGraphics();
	    g.setColor(this.getBackground());   
	    g.fillRect(0, 0, bounds().width, bounds().height);
	    return true;
	}
	else return false;
    }
}
