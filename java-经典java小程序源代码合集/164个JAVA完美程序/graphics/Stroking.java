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

/** A demonstration of how Stroke objects work */
public class Stroking implements GraphicsExample {
    static final int WIDTH = 725, HEIGHT = 250;  // Size of our example
    public String getName() {return "Stroking";} // From GraphicsExample
    public int getWidth() { return WIDTH; }      // From GraphicsExample
    public int getHeight() { return HEIGHT; }    // From GraphicsExample

    /** Draw the example */
    public void draw(Graphics2D g, Component c) {
	// Create the shape we'll work with.  See convenience method below.
	Shape pentagon = createRegularPolygon(5, 75);

	// Set up basic drawing attributes
	g.setColor(Color.black);                          // Draw in black
	g.setStroke(new BasicStroke(1.0f));               // Use thin lines
	g.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Basic small font

	g.translate(100, 100);                        // Move to position
	g.draw(pentagon);                             // Outline the shape
	g.drawString("The shape", -30, 90);           // Draw the caption

	g.translate(175, 0);                          // Move over
	g.fill(pentagon);                             // Fill the shape
	g.drawString("The filled shape", -50, 90);    // Another caption

	// Now use a Stroke object to create a "stroked shape" for our shape
	BasicStroke wideline = new BasicStroke(10.0f);
	Shape outline = wideline.createStrokedShape(pentagon);

	g.translate(175, 0);                          // Move over
	g.draw(outline);                              // Draw the stroked shape
	g.drawString("A Stroke creates",-50,90);      // Draw the caption
	g.drawString("a new shape", -35, 105);

	g.translate(175,0);                           // Move over
	g.fill(outline);                              // Fill the stroked shape
	g.drawString("Filling the new shape",-65,90); // Draw the caption
	g.drawString("outlines the old one",-65,105);
    }

    // A convenience method to define a regular polygon.
    // Returns a shape that represents a regular polygon with the specified
    // radius and number of sides, and centered at the origin.
    public Shape createRegularPolygon(int numsides, int radius) {
	Polygon p = new Polygon();               
	double angle = 2 * Math.PI / numsides;   // Angle between vertices
	for(int i = 0; i < numsides; i++)  // Compute location of each vertex
	    p.addPoint((int)(radius * Math.sin(angle*i)),
		       (int)(radius * -Math.cos(angle*i)));
	return p;  
    }
}
