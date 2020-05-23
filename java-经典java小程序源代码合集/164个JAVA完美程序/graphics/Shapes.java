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
import java.awt.font.*;
import java.awt.image.*;

/** A demonstration of Java2D shapes */
public class Shapes implements GraphicsExample {
    static final int WIDTH = 725, HEIGHT = 250;    // Size of our example
    public String getName() {return "Shapes";}     // From GraphicsExample
    public int getWidth() { return WIDTH; }        // From GraphicsExample
    public int getHeight() { return HEIGHT; }      // From GraphicsExample

    Shape[] shapes = new Shape[] {
	// A straight line segment
	new Line2D.Float(0, 0, 100, 100),
	// A quadratic bezier curve.  Two end points and one control point
	new QuadCurve2D.Float(0, 0, 80, 15, 100, 100),
	// A cubic bezier curve.  Two end points and two control points
	new CubicCurve2D.Float(0, 0, 80, 15, 10, 90, 100, 100),
	// A 120 degree portion of an ellipse
	new Arc2D.Float(-30, 0, 100, 100, 60, -120, Arc2D.OPEN),
	// A 120 degree portion of an ellipse, closed with a chord
	new Arc2D.Float(-30, 0, 100, 100, 60, -120, Arc2D.CHORD),
	// A 120 degree pie slice of an ellipse
	new Arc2D.Float(-30, 0, 100, 100, 60, -120, Arc2D.PIE),
	// An ellipse
	new Ellipse2D.Float(0, 20, 100, 60),
	// A rectangle
	new Rectangle2D.Float(0, 20, 100, 60),
	// A rectangle with rounded corners
	new RoundRectangle2D.Float(0, 20, 100, 60, 15, 15),
	// A triangle
	new Polygon(new int[] { 0, 0, 100 }, new int[] {20, 80, 80}, 3),
	// A random polygon, initialized in code below
	null,
	// A spiral: an instance of a custom Shape implementation
	new Spiral(50, 50, 5, 0, 50, 4*Math.PI),
    };

    {   // Initialize the null shape above as a Polygon with random points
	Polygon p = new Polygon();
	for(int i = 0; i < 10; i++) 
	    p.addPoint((int)(100*Math.random()), (int)(100*Math.random()));
	shapes[10] = p;
    }

    // These are the labels for each of the shapes 
    String[] labels = new String[] {
	"Line2D", "QuadCurve2D", "CubicCurve2D", "Arc2D (OPEN)",
	"Arc2D (CHORD)", "Arc2D (PIE)", "Ellipse2D", "Rectangle2D",
	"RoundRectangle2D", "Polygon", "Polygon (random)", "Spiral"
    };

    /** Draw the example */
    public void draw(Graphics2D g, Component c) {
	// Set basic drawing attributes
	g.setFont(new Font("SansSerif", Font.PLAIN, 10));      // select font
	g.setStroke(new BasicStroke(2.0f));                    // 2 pixel lines
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    // antialiasing
			   RenderingHints.VALUE_ANTIALIAS_ON);
	g.translate(10, 10);                                   // margins

	// Loop through each shape
	for(int i = 0; i < shapes.length; i++) {
	    g.setColor(Color.yellow);            // Set a color
	    g.fill(shapes[i]);                   // Fill the shape with it
	    g.setColor(Color.black);             // Switch to black
	    g.draw(shapes[i]);                   // Outline the shape with it
	    g.drawString(labels[i], 0, 110);     // Label the shape
	    g.translate(120, 0);                 // Move over for next shape
	    if (i % 6  == 5) g.translate(-6*120, 120);  // Move down after 6
	}
    }
}
