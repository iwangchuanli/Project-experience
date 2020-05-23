/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.graphics;
import java.applet.*;
import java.awt.*;

/** An applet that displays a simple animation */
public class BouncingCircle extends Applet implements Runnable {
    int x = 150, y = 50, r = 50;  // Position and radius of the circle
    int dx = 11, dy = 7;          // Trajectory of circle
    Thread animator;              // The thread that performs the animation
    volatile boolean pleaseStop;  // A flag to ask the thread to stop
    
    /** This method simply draws the circle at its current position */
    public void paint(Graphics g) {
	g.setColor(Color.red);
	g.fillOval(x-r, y-r, r*2, r*2);
    }
    
    /**
     * This method moves (and bounces) the circle and then requests a redraw.
     * The animator thread calls this method periodically.
     **/
    public void animate() {
	// Bounce if we've hit an edge.
	Rectangle bounds = getBounds();
	if ((x - r + dx < 0) || (x + r + dx > bounds.width)) dx = -dx;
	if ((y - r + dy < 0) || (y + r + dy > bounds.height)) dy = -dy;

	// Move the circle.
	x += dx;  y += dy;

	// Ask the browser to call our paint() method to draw the circle
	// at its new position.
	repaint();
    }
    
    /**
     * This method is from the Runnable interface.  It is the body of the 
     * thread that performs the animation.  The thread itself is created 
     * and started in the start() method.
     **/
    public void run() {
	while(!pleaseStop) {           // Loop until we're asked to stop
	    animate();                       // Update and request redraw
	    try { Thread.sleep(100); }       // Wait 100 milliseconds
	    catch(InterruptedException e) {} // Ignore interruptions
	}
    }

    /** Start animating when the browser starts the applet */
    public void start() {
	animator = new Thread(this);   // Create a thread
	pleaseStop = false;            // Don't ask it to stop now
	animator.start();              // Start the thread.
	// The thread that called start now returns to its caller.
	// Meanwhile, the new animator thread has called the run() method
    }
    
    /** Stop animating when the browser stops the applet */
    public void stop() {
	// Set the flag that causes the run() method to end
	pleaseStop = true;
    }
}
