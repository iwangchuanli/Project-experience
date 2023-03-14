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
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.Timer;  // Import explicitly because of java.util.Timer

/**
 * A Swing component that smoothly animates a spiral in a hypnotic way.
 **/
public class Hypnosis extends JComponent implements ActionListener {
    double x, y;           // The center of the spiral
    double r1, r2;         // The inner and outer radii of the spiral
    double a1, a2;         // The start and end angles of the spiral
    double deltaA;         // How much the angle changes each frame
    double deltaX, deltaY; // The trajectory of the center
    float linewidth;       // How wide the lines are
    Timer timer;           // The object that triggers the animation
    BufferedImage buffer;  // The image we use for double-buffering
    Graphics2D osg;        // Graphics2D object for drawing into the buffer

    public Hypnosis(double x, double y, double r1, double r2,
		    double a1, double a2, float linewidth, int delay,
		    double deltaA, double deltaX, double deltaY)
    {
	this.x = x; this.y = y;
	this.r1 = r1; this.r2 = r2;
	this.a1 = a1; this.a2 = a2;
	this.linewidth = linewidth;
	this.deltaA = deltaA;
	this.deltaX = deltaX;
	this.deltaY = deltaY;

	// Set up a timer to call actionPerformed() every delay milliseconds
	timer = new Timer(delay, this);

	// Create a buffer for double-buffering
	buffer = new BufferedImage((int)(2*r2+linewidth),
				   (int)(2*r2+linewidth), 
				   BufferedImage.TYPE_INT_RGB);

	// Create a Graphics object for the buffer, and set the linewidth
	// and request antialiasing when drawing with it
	osg = buffer.createGraphics();
	osg.setStroke(new BasicStroke(linewidth, BasicStroke.CAP_ROUND,
				      BasicStroke.JOIN_ROUND));
	osg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			     RenderingHints.VALUE_ANTIALIAS_ON);
    }

    // Start and stop the animation by starting and stopping the timer
    public void start() { timer.start(); }
    public void stop() { timer.stop(); }

    /** 
     * Swing calls this method to ask the component to redraw itself.
     * This method uses double-buffering to make the animation smoother.
     * Swing does double-buffering automatically, so this may not actually
     * make much difference, but it is important to understand the technique.
     **/
    public void paintComponent(Graphics g) {
	// Clear the background of the off-screen image
	osg.setColor(getBackground());
	osg.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

	// Now draw a black spiral into the off-screen image
	osg.setColor(Color.black);
	osg.draw(new Spiral(r2+linewidth/2, r2+linewidth/2, r1, a1, r2, a2));

	// Now copy that off-screen image onto the screen
	g.drawImage(buffer, (int)(x-r2), (int)(y-r2), this);
    }

    /** 
     * This method implements the ActionListener interface.  Our Timer object
     * calls this method periodically.  It updates the position and angles
     * of the spiral and requests a redraw.  Instead of redrawing the entire
     * component, however, this method requests a redraw only for the 
     * area that has changed.
     **/
    public void actionPerformed(ActionEvent e) {
	// Ask to have the old bounding box of the spiral redrawn.
	// Nothing else has anything drawn in it, so it doesn't need a redraw
	repaint((int)(x-r2-linewidth), (int)(y-r2-linewidth),
		(int)(2*(r2+linewidth)), (int)(2*(r2+linewidth)));

	// Now animate: update the position and angles of the spiral

	// Bounce if we've hit an edge
	Rectangle bounds = getBounds();
	if ((x - r2 + deltaX < 0) || (x + r2 + deltaX > bounds.width))
	    deltaX = -deltaX;
	if ((y - r2 + deltaY < 0) || (y + r2 + deltaY > bounds.height))
	    deltaY = -deltaY;

	// Move the center of the spiral
	x += deltaX;
	y += deltaY;

	// Increment the start and end angles;
	a1 += deltaA;
	a2 += deltaA;
	if (a1 > 2*Math.PI) {  // Don't let them get too big
	    a1 -= 2*Math.PI;
	    a2 -= 2*Math.PI;
	}

	// Now ask to have the new bounding box of the spiral redrawn.  This
	// rectangle will be intersected with the redraw rectangle requested
	// above, and only the combined region will be redrawn
	repaint((int)(x-r2-linewidth), (int)(y-r2-linewidth),
		(int)(2*(r2+linewidth)), (int)(2*(r2+linewidth)));
    }

    /** Tell Swing not to double-buffer for us, since we do our own */
    public boolean isDoubleBuffered() { return false; }

    /** This is a main() method for testing the component */
    public static void main(String[] args) {
	JFrame f = new JFrame("Hypnosis");
	Hypnosis h = new Hypnosis(200, 200, 10, 100, 0, 11*Math.PI, 7, 100,
				  2*Math.PI/30, 3, 5);
	f.getContentPane().add(h, BorderLayout.CENTER);
	f.setSize(400, 400);
	f.show();
	h.start();
    }
}
