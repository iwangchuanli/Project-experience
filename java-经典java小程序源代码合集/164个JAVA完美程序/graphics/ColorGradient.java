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

/** An applet that demonstrates the Color class */
public class ColorGradient extends Applet {
    Color startColor, endColor;   // Start and end color of the gradient
    Font bigFont;                 // A font we'll use

    /** 
     * Get the gradient start and end colors as applet parameter values, and
     * parse them using Color.decode().  If they are malformed, use white.
     **/
    public void init() {
	try {
	    startColor = Color.decode(getParameter("startColor"));
	    endColor = Color.decode(getParameter("endColor"));
	}
	catch (NumberFormatException e) {
	    startColor = endColor = Color.white;
	}
	bigFont = new Font("Helvetica", Font.BOLD, 72);
    }

    /** Draw the applet.  The interesting code is in fillGradient() below */
    public void paint(Graphics g) {
	fillGradient(this, g, startColor, endColor);  // display the gradient
	g.setFont(bigFont);                    // set a font
	g.setColor(new Color(100, 100, 200));  // light blue
	g.drawString("Colors!", 100, 100);     // draw something interesting
    }

    /**
     * Draw a color gradient from the top of the specified component to the
     * bottom.  Start with the start color and change smoothly to the end 
     **/
    public void fillGradient(Component c, Graphics g, Color start, Color end) {
	Rectangle bounds = this.getBounds();  // How big is the component?
	// Get the red, green, and blue components of the start and end
	// colors as floats between 0.0 and 1.0.  Note that the Color class
	// also works with int values between 0 and 255
	float r1 = start.getRed()/255.0f;
	float g1 = start.getGreen()/255.0f;
	float b1 = start.getBlue()/255.0f;
	float r2 = end.getRed()/255.0f;
	float g2 = end.getGreen()/255.0f;
	float b2 = end.getBlue()/255.0f;
	// Figure out how much each component should change at each y value
	float dr = (r2-r1)/bounds.height;
	float dg = (g2-g1)/bounds.height;
	float db = (b2-b1)/bounds.height;

	// Now loop once for each row of pixels in the component
	for(int y = 0; y < bounds.height; y++) {
	    g.setColor(new Color(r1, g1, b1));    // Set the color of the row
	    g.drawLine(0, y, bounds.width-1, y);  // Draw the row
	    r1 += dr; g1 += dg; b1 += db;         // Increment color components
	}
    }
}
