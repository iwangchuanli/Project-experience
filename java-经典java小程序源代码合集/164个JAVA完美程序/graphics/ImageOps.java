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
import java.awt.color.*;

/** A demonstration of various image processing filters */
public class ImageOps implements GraphicsExample {
    static final int WIDTH = 600, HEIGHT = 675;         // Size of our example
    public String getName() {return "Image Processing";}// From GraphicsExample
    public int getWidth() { return WIDTH; }             // From GraphicsExample
    public int getHeight() { return HEIGHT; }           // From GraphicsExample

    Image image;

    /** This constructor loads the image we will manipulate */
    public ImageOps() {
	java.net.URL imageurl = this.getClass().getResource("cover.gif");
	image = new javax.swing.ImageIcon(imageurl).getImage();
    }
    
    // These arrays of bytes are used by the LookupImageOp image filters below
    static byte[] brightenTable = new byte[256];
    static byte[] thresholdTable = new byte[256];
    static {  // Initialize the arrays
	for(int i = 0; i < 256; i++) {
	    brightenTable[i] = (byte)(Math.sqrt(i/255.0)*255);
	    thresholdTable[i] = (byte)((i < 225)?0:i);
	}
    }

    // This AffineTransform is used by one of the image filters below
    static AffineTransform mirrorTransform;
    static {  // Create and initialize the AffineTransform
	mirrorTransform = AffineTransform.getTranslateInstance(127, 0);
	mirrorTransform.scale(-1.0, 1.0);  // flip horizontally
    }

    // These are the labels we'll display for each of the filtered images
    static String[] filterNames = new String[] {
	"Original", "Gray Scale",  "Negative",  "Brighten (linear)",
	"Brighten (sqrt)", "Threshold", "Blur",	"Sharpen",
	"Edge Detect", "Mirror", "Rotate (center)", "Rotate (lower left)"
    };

    // The following BufferedImageOp image filter objects perform
    // different types of image processing operations.
    static BufferedImageOp[] filters = new BufferedImageOp[] {
	// 1) No filter here.  We'll display the original image
	null,
	// 2) Convert to Grayscale color space
	new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null),
	// 3) Image negative.  Multiply each color value by -1.0 and add 255
	new RescaleOp(-1.0f, 255f, null),
	// 4) Brighten using a linear formula that increases all color values
	new RescaleOp(1.25f, 0, null),
	// 5) Brighten using the lookup table defined above
	new LookupOp(new ByteLookupTable(0, brightenTable), null),
	// 6) Threshold using the lookup table defined above
	new LookupOp(new ByteLookupTable(0, thresholdTable), null),
	// 7) Blur by "convolving" the image with a matrix
	new ConvolveOp(new Kernel(3, 3, new float[] {  
	    .1111f,.1111f,.1111f,
	    .1111f,.1111f,.1111f,
	    .1111f,.1111f,.1111f,})),
	// 8) Sharpen by using a different matrix
	new ConvolveOp(new Kernel(3, 3, new float[] {  
	    0.0f, -0.75f, 0.0f,
	    -0.75f, 4.0f, -0.75f,
	    0.0f, -0.75f, 0.0f})),
	// 9) Edge detect using yet another matrix
	new ConvolveOp(new Kernel(3, 3, new float[] {  
	    0.0f,  -0.75f, 0.0f,
	    -0.75f, 3.0f, -0.75f,
	    0.0f,  -0.75f, 0.0f})),
	// 10) Compute a mirror image using the transform defined above
	new AffineTransformOp(mirrorTransform,AffineTransformOp.TYPE_BILINEAR),
	// 11) Rotate the image 180 degrees about its center point
	new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI,64,95),
			      AffineTransformOp.TYPE_NEAREST_NEIGHBOR),
	// 12) Rotate the image 15 degrees about the bottom left
	new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/12,
								0, 190),
			      AffineTransformOp.TYPE_NEAREST_NEIGHBOR),
    };

    /** Draw the example */
    public void draw(Graphics2D g, Component c) {
	// Create a BufferedImage big enough to hold the Image loaded
	// in the constructor.  Then copy that image into the new
	// BufferedImage object so that we can process it.
	BufferedImage bimage = new BufferedImage(image.getWidth(c),
						 image.getHeight(c),
						 BufferedImage.TYPE_INT_RGB);
	Graphics2D ig = bimage.createGraphics();
	ig.drawImage(image, 0, 0, c);  // copy the image

	// Set some default graphics attributes
	g.setFont(new Font("SansSerif", Font.BOLD, 12));  // 12pt bold text
	g.setColor(Color.green);                          // Draw in green
	g.translate(10, 10);                              // Set some margins

	// Loop through the filters
	for(int i = 0; i < filters.length; i++) {
	    // If the filter is null, draw the original image, otherwise,
	    // draw the image as processed by the filter
	    if (filters[i] == null) g.drawImage(bimage, 0, 0, c);
	    else g.drawImage(filters[i].filter(bimage, null), 0, 0, c);
	    g.drawString(filterNames[i], 0, 205);      // Label the image
	    g.translate(137, 0);                       // Move over
	    if (i % 4 == 3) g.translate(-137*4, 215);  // Move down after 4
	}
    }
}
