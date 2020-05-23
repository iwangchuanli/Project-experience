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

/** A demonstration of Java2D transformations */
public class Paints implements GraphicsExample {
    static final int WIDTH = 800, HEIGHT = 375;  // Size of our example

    public String getName() { return "Paints"; } // From GraphicsExample
    public int getWidth() { return WIDTH; }      // From GraphicsExample
    public int getHeight() { return HEIGHT; }    // From GraphicsExample

    /** Draw the example */
    public void draw(Graphics2D g, Component c) {
	// Paint the entire background using a GradientPaint.
	// The background color varies diagonally from deep red to pale blue
	g.setPaint(new GradientPaint(0, 0, new Color(150, 0, 0),
				     WIDTH, HEIGHT, new Color(200, 200, 255)));
	g.fillRect(0, 0, WIDTH, HEIGHT);          // fill the background

	// Use a different GradientPaint to draw a box.
	// This one alternates between deep opaque green and transparent green.
	// Note: the 4th arg to Color() constructor specifies color opacity
	g.setPaint(new GradientPaint(0, 0, new Color(0, 150, 0),
				     20, 20, new Color(0, 150, 0, 0), true));
	g.setStroke(new BasicStroke(15));         // use wide lines
	g.drawRect(25, 25, WIDTH-50, HEIGHT-50);  // draw the box

	// The glyphs of fonts can be used as Shape objects, which enables
	// us to use Java2D techniques with letters Just as we would with
	// any other shape.  Here we get some letter shapes to draw.
	Font font = new Font("Serif", Font.BOLD, 10);  // a basic font
	Font bigfont =                                 // a scaled up version
	    font.deriveFont(AffineTransform.getScaleInstance(30.0, 30.0));
	GlyphVector gv = bigfont.createGlyphVector(g.getFontRenderContext(),
						   "JAV");
	Shape jshape = gv.getGlyphOutline(0);   // Shape of letter J
	Shape ashape = gv.getGlyphOutline(1);   // Shape of letter A
	Shape vshape = gv.getGlyphOutline(2);   // Shape of letter V

	// We're going to outline the letters with a 5-pixel wide line
	g.setStroke(new BasicStroke(5.0f));

	// We're going to fake shadows for the letters using the
	// following Paint and AffineTransform objects
	Paint shadowPaint = new Color(0, 0, 0, 100);     // Translucent black
	AffineTransform shadowTransform =
	    AffineTransform.getShearInstance(-1.0, 0.0); // Shear to the right
	shadowTransform.scale(1.0, 0.5);                 // Scale height by 1/2

	// Move to the baseline of our first letter
	g.translate(65, 270);

	// Draw the shadow of the J shape
	g.setPaint(shadowPaint);
	g.translate(15,20);     // Compensate for the descender of the J
	// transform the J into the shape of its shadow, and fill it
	g.fill(shadowTransform.createTransformedShape(jshape));
	g.translate(-15,-20);   // Undo the translation above

	// Now fill the J shape with a solid (and opaque) color
	g.setPaint(Color.blue);     // Fill with solid, opaque blue
	g.fill(jshape);             // Fill the shape
	g.setPaint(Color.black);    // Switch to solid black
	g.draw(jshape);             // And draw the outline of the J

	// Now draw the A shadow
	g.translate(75, 0);         // Move to the right
	g.setPaint(shadowPaint);    // Set shadow color
	g.fill(shadowTransform.createTransformedShape(ashape)); // draw shadow

	// Draw the A shape using a solid transparent color
	g.setPaint(new Color(0, 255, 0, 125));  // Transparent green as paint
	g.fill(ashape);                         // Fill the shape
	g.setPaint(Color.black);                // Switch to solid back
	g.draw(ashape);                         // Draw the outline
	
	// Move to the right and draw the shadow of the letter V
	g.translate(175, 0);
	g.setPaint(shadowPaint);
	g.fill(shadowTransform.createTransformedShape(vshape));

	// We're going to fill the next letter using a TexturePaint, which
	// repeatedly tiles an image. The first step is to obtain the image.
	// We could load it from an image file, but here we create it 
	// ourselves by drawing a into an off-screen image.  Note that we use
	// a GradientPaint to fill the off-screen image, so the fill pattern
	// combines features of both Paint classes.
	BufferedImage tile =                   // Create an image
	    new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
	Graphics2D tg = tile.createGraphics(); // Get its Graphics for drawing
	tg.setColor(Color.pink);       
	tg.fillRect(0, 0, 50, 50);     // Fill tile background with pink
	tg.setPaint(new GradientPaint(40, 0, Color.green,  // diagonal gradient
				      0, 40, Color.gray)); // green to gray
	tg.fillOval(5, 5, 40, 40);     // Draw a circle with this gradient

	// Use this new tile to create a TexturePaint and fill the letter V
	g.setPaint(new TexturePaint(tile, new Rectangle(0, 0, 50, 50)));
	g.fill(vshape);                        // Fill letter shape
	g.setPaint(Color.black);               // Switch to solid black
	g.draw(vshape);                        // Draw outline of letter

	// Move to the right and draw the shadow of the final A
	g.translate(160, 0);
	g.setPaint(shadowPaint);
	g.fill(shadowTransform.createTransformedShape(ashape));

	// For the last letter, use a custom Paint class to fill with a 
	// complex mathematically defined pattern.  The GenericPaint
	// class is defined later in the chapter.
	g.setPaint(new GenericPaint() {
		public int computeRed(double x, double y) { return 128; }
		public int computeGreen(double x, double y) {
		    return (int)((Math.sin(x/7) + Math.cos(y/5) + 2)/4 *255);
		}
		public int computeBlue(double x, double y) {
		    return ((int)(x*y))%256;
		}
		public int computeAlpha(double x, double y) {
		    return ((int)x%25*8+50) + ((int)y%25*8+50);
		}
	    });
	g.fill(ashape);                // Fill letter A
	g.setPaint(Color.black);       // Revert to solid black
	g.draw(ashape);                // Draw the outline of the A
    }
}
