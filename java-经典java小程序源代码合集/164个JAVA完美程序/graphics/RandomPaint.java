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

/**
 * This is a Paint implementation that fills every raster with a random
 * opaque color.  Using this Paint class allows you to visualize the
 * painting algorithm used by your Java2D implementation. 
 **/
public class RandomPaint implements Paint {
    /** This is the main Paint method;  all it does is return a PaintContext */
    public PaintContext createContext(ColorModel cm,
				      Rectangle deviceBounds,
				      Rectangle2D userBounds,
				      AffineTransform xform,
				      RenderingHints hints) {
	return new RandomPaintContext();
    }

    /** This Paint object only uses opaque colors */
    public int getTransparency() { return OPAQUE; }

    /**
     * The PaintContext class does all the work of painting
     **/
    class RandomPaintContext implements PaintContext {
	BufferedImage image;              // An image we can draw into
	Graphics2D imageGraphics;         // The Graphics object to do it with
	java.util.Random randomizer =     // For generating random numbers
	    new java.util.Random(System.currentTimeMillis());  // seed value
	Rectangle rect = new Rectangle(); // A scratch rectangle

	/** Return the color model used by this Paint implementation */
	public ColorModel getColorModel() { return image.getColorModel(); }

	/**
	 * This is the main method of PaintContext.  It must return a Raster
	 * that contains fill data for the specified rectangle.  For this 
	 * implementation, we just fill with a random solid color each time.
	 * Instead of setting pixels at the Raster level, we instead 
	 * manipulate a BufferedImage using the Graphics.fillRect() method.
	 * Note that we never create an image larger than we need.
	 **/
	public Raster getRaster(int x, int y, int w, int h) {
	    // Create an initial image or a larger image as needed
	    if ((image == null) || (image.getWidth() < w) ||
		(image.getHeight() < h)) {
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		imageGraphics = image.createGraphics();
	    }

	    // Choose and use a random color
	    imageGraphics.setColor(new Color(randomizer.nextInt(256),
					     randomizer.nextInt(256),
					     randomizer.nextInt(256)));
	    // Fill a rectangle of the specified size with that color
	    imageGraphics.fillRect(0, 0, w, h);

	    // Then extract the corresponding Raster from the image and return
	    rect.x = 0; rect.y = 0; rect.width = w; rect.height = h;
	    return image.getData(rect);
	}

	/** Called when the PaintContext is no longer needed. */
	public void dispose() {
	    imageGraphics.dispose();  
	    image = null;
	    imageGraphics = null;
	}
    }
}
