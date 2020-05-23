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
 * This is an abstract Paint implementation that computes the color of each
 * point to be painted by passing the coordinates of the point to the calling
 * the abstract methods computeRed(), computeGreen(), computeBlue() and
 * computeAlpha().  Subclasses must implement these three methods to perform
 * whatever type of painting is desired.  Note that while this class provides
 * great flexibility, it is not very efficient.
 **/
public abstract class GenericPaint implements Paint {
    /** This is the main Paint method;  all it does is return a PaintContext */
    public PaintContext createContext(ColorModel cm,
				      Rectangle deviceBounds,
				      Rectangle2D userBounds,
				      AffineTransform xform,
				      RenderingHints hints) {
	return new GenericPaintContext(xform);
    }

    /** This paint class allows translucent painting */
    public int getTransparency() { return TRANSLUCENT; }

    /**
     * These three methods return the red, green, blue, and alpha values of
     * the pixel at appear at the specified user-space coordinates.  The return
     * value of each method should be between 0 and 255.
     **/
    public abstract int computeRed(double x, double y);
    public abstract int computeGreen(double x, double y);
    public abstract int computeBlue(double x, double y);
    public abstract int computeAlpha(double x, double y);

    /**
     * The PaintContext class does all the work of painting
     **/
    class GenericPaintContext implements PaintContext {
	ColorModel model;  // The color model
	Point2D origin, unitVectorX, unitVectorY;  // For device-to-user xform

	public GenericPaintContext(AffineTransform userToDevice) {
	    // Our color model packs ARGB values into a single int
	    model = new DirectColorModel(32, 0x00ff0000,0x0000ff00,
					 0x000000ff, 0xff000000);
	    // The specified transform converts user to device pixels
	    // We need to figure out the reverse transformation, so we
	    // can compute the user space coordinates of each device pixel
	    try {
		AffineTransform deviceToUser = userToDevice.createInverse();
		origin = deviceToUser.transform(new Point(0,0), null);
		unitVectorX = deviceToUser.deltaTransform(new Point(1,0),null);
		unitVectorY = deviceToUser.deltaTransform(new Point(0,1),null);
	    }
	    catch (NoninvertibleTransformException e) {
		// If we can't invert the transform, just use device space
		origin = new Point(0,0);
		unitVectorX = new Point(1,0);
		unitVectorY = new Point(0, 1);
	    }
	}

	/** Return the color model used by this Paint implementation */
	public ColorModel getColorModel() { return model; }

	/**
	 * This is the main method of PaintContext.  It must return a Raster
	 * that contains fill data for the specified rectangle.  It creates a
	 * raster of the specified size, and loops through the device pixels.
	 * For each one, it converts the coordinates to user space, then calls
	 * the computeRed(), computeGreen() and computeBlue() methods to
	 * obtain the appropriate color for the device pixel.
	 **/
	public Raster getRaster(int x, int y, int w, int h) {
	    WritableRaster raster = model.createCompatibleWritableRaster(w,h);
	    int[] colorComponents = new int[4];
	    for(int j = 0; j < h; j++) {      // Loop through rows of raster
		int deviceY = y + j; 
		for(int i = 0; i < w; i++) {  // Loop through columns
		    int deviceX = x + i;
		    // Convert device coordinate to user-space coordinate
		    double userX = origin.getX() + 
			deviceX * unitVectorX.getX() + 
			deviceY * unitVectorY.getX();
		    double userY = origin.getY() + 
			deviceX * unitVectorX.getY() + 
			deviceY * unitVectorY.getY();
		    // Compute the color components of the pixel
		    colorComponents[0] = computeRed(userX, userY);
		    colorComponents[1] = computeGreen(userX, userY);
		    colorComponents[2] = computeBlue(userX, userY);
		    colorComponents[3] = computeAlpha(userX, userY);
		    // Set the color of the pixel
		    raster.setPixel(i, j, colorComponents);
		}
	    }
	    return raster;
	}

	/** Called when the PaintContext is no longer needed. */
	public void dispose() {}
    }
}
