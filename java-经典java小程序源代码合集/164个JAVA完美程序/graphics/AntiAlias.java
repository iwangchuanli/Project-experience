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
import java.awt.geom.*;
import java.awt.image.*;

/** A demonstration of anti-aliasing */
public class AntiAlias implements GraphicsExample {
    static final int WIDTH = 650, HEIGHT = 350;        // Size of our example
    public String getName() {return "AntiAliasing";}   // From GraphicsExample
    public int getWidth() { return WIDTH; }            // From GraphicsExample
    public int getHeight() { return HEIGHT; }          // From GraphicsExample

    /** Draw the example */
    public void draw(Graphics2D g, Component c) {
	BufferedImage image =                   // Create an off-screen image
	    new BufferedImage(65, 35, BufferedImage.TYPE_INT_RGB);
	Graphics2D ig = image.createGraphics(); // Get its Graphics for drawing

	// Set the background to a gradient fill.  The varying color of
	// the background helps to demonstrate the anti-aliasing effect
	ig.setPaint(new GradientPaint(0,0,Color.black,65,35,Color.white));
	ig.fillRect(0, 0, 65, 35);

	// Set drawing attributes for the foreground.
	// Most importantly, turn on anti-aliasing.
	ig.setStroke(new BasicStroke(2.0f));                   // 2-pixel lines
	ig.setFont(new Font("Serif", Font.BOLD, 18));          // 18-point font
	ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING,   // Anti-alias!
			    RenderingHints.VALUE_ANTIALIAS_ON);

	// Now draw pure blue text and a pure red oval
	ig.setColor(Color.blue);
	ig.drawString("Java", 9, 22);
	ig.setColor(Color.red);
	ig.drawOval(1, 1, 62, 32);
	
	// Finally, scale the image by a factor of 10 and display it
	// in the window.  This will allow us to see the anti-aliased pixels
	g.drawImage(image, AffineTransform.getScaleInstance(10, 10), c);

	// Draw the image one more time at its original size, for comparison
	g.drawImage(image, 0, 0, c);
    }
}
