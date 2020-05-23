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

/**
 * An applet that demonstrates most of the graphics primitives in
 * java.awt.Graphics.
 **/
public class GraphicsSampler extends Applet {
    Color fill, outline, textcolor;  // The various colors we use
    Font font;                       // The font we use for text
    FontMetrics metrics;             // Information about font size
    Image image, background;         // Some images we draw with

    // This method is called when the applet is first created.
    // It performs initialization, such as creating the resources
    // (graphics attribute values) used by the paint() method.
    public void init() {
	// Initialize color resources.  Note the use of the Color() constructor
	// and the use of pre-defined color constants.
	fill = new Color(200, 200, 200); // Equal red, green, and blue == gray
	outline = Color.blue;            // Same as new Color(0, 0, 255)
	textcolor = Color.red;           // Same as new Color(255, 0, 0)

	// Create a font for use in the paint() method.  Get its metrics, too.
	font = new Font("sansserif", Font.BOLD, 14);
	metrics = this.getFontMetrics(font);

	// Load some Image objects for use in the paint() method.
	image = this.getImage(this.getDocumentBase(), "tiger.gif");
	background = this.getImage(this.getDocumentBase(), "background.gif");

	// Set a property that tells the applet its background color
	this.setBackground(Color.lightGray);
    }
    
    // This method is called whenever the applet needs to be drawn or redrawn
    public void paint(Graphics g) {
	g.setFont(font);  // Specify the font we'll be using throughout
	
	// Draw a background by tiling an image tile() is defined below
	tile(g, this, background);
	
	// Draw a line
	g.setColor(outline);            // Specify the drawing color
	g.drawLine(25, 10, 150, 80);    // Draw a line from (25,10) to (150,80)
	// Draw some text.  See the centerText() method below.
	centerText("drawLine()", null, g, textcolor, 25, 10, 150, 80);
	
	// Draw and fill an arc
	g.setColor(fill);
	g.fillArc(225, 10, 150, 80, 90, 135);
	g.setColor(outline);
	g.drawArc(225, 10, 150, 80, 90, 135);
	centerText("fillArc()", "drawArc()", g, textcolor,225,10,150,80);
	
	// Draw and fill a rectangle
	g.setColor(fill);
	g.fillRect(25, 110, 150, 80);
	g.setColor(outline);
	g.drawRect(25, 110, 150, 80);
	centerText("fillRect()", "drawRect()", g, textcolor, 25, 110, 150, 80);
	
	// Draw and fill a rounded rectangle
	g.setColor(fill);
	g.fillRoundRect(225, 110, 150, 80, 20, 20);
	g.setColor(outline);
	g.drawRoundRect(225, 110, 150, 80, 20, 20);
	centerText("fillRoundRect()", "drawRoundRect()", g, textcolor,
		   225, 110, 150, 80);
	
	// Draw and fill an oval
	g.setColor(fill);
	g.fillOval(25, 210, 150, 80);
	g.setColor(outline);
	g.drawOval(25, 210, 150, 80);
	centerText("fillOval()", "drawOval()", g, textcolor, 25, 210, 150, 80);
	
	// Define an octagon using arrays of X and Y coordinates
	int numpoints = 8;
	int[] xpoints = new int[numpoints+1];
	int[] ypoints = new int[numpoints+1];
	for(int i=0; i < numpoints; i++) {
	    double angle = 2*Math.PI * i / numpoints;
	    xpoints[i] = (int)(300 + 75*Math.cos(angle));
	    ypoints[i] = (int)(250 - 40*Math.sin(angle));
	}

	// Draw and fill the polygon
	g.setColor(fill);
	g.fillPolygon(xpoints, ypoints, numpoints);
	g.setColor(outline);
	g.drawPolygon(xpoints, ypoints, numpoints);
	centerText("fillPolygon()", "drawPolygon()", g, textcolor,
		   225, 210, 150, 80);
	
	// Draw a 3D rectangle (clear an area for it first)
	g.setColor(fill);
	g.fillRect(20, 305, 160, 90);
	g.draw3DRect(25, 310, 150, 80, true);
	g.draw3DRect(26, 311, 148, 78, true);
	g.draw3DRect(27, 312, 146, 76, true);
	centerText("draw3DRect()", "x 3", g, textcolor, 25, 310, 150, 80);
      
	// Draw an image (centered within an area)
	int w = image.getWidth(this);
	int h = image.getHeight(this);
	g.drawImage(image, 225 + (150-w)/2, 310 + (80-h)/2, this);
	centerText("drawImage()", null, g, textcolor,  225, 310, 150, 80);
    }
    
    // Utility method to tile an image on the background of the component 
    protected void tile(Graphics g, Component c, Image i) {
	// Use bounds() instead of getBounds() if you want
	// compatibility with Java 1.0 and old browsers like Netscape 3
	Rectangle r = c.getBounds();             // How big is the component?
	int iw = i.getWidth(c);                  // How big is the image?
	int ih = i.getHeight(c);
	if ((iw <= 0) || (ih <= 0)) return;        
	for(int x=0; x < r.width; x += iw)       // Loop horizontally
	    for(int y=0; y < r.height; y += ih)  // Loop vertically
		g.drawImage(i, x, y, c);         // Draw the image
    }
    
    // Utility method to center two lines of text in a rectangle.
    // Relies on the FontMetrics obtained in the init() method.
    protected void centerText(String s1, String s2, Graphics g, Color c,
			      int x, int y, int w, int h)
    {
	int height = metrics.getHeight();  // How tall is the font?
	int ascent = metrics.getAscent();  // Where is the font baseline?
	int width1=0, width2 = 0, x0=0, x1=0, y0=0, y1=0;
	width1 = metrics.stringWidth(s1);  // How wide are the strings?
	if (s2 != null) width2 = metrics.stringWidth(s2);
	x0 = x + (w - width1)/2;           // Center the strings horizontally
	x1 = x + (w - width2)/2;
	if (s2 == null)                    // Center one string vertically
	    y0 = y + (h - height)/2 + ascent;
	else {                             // Center two strings vertically
	    y0 = y + (h - (int)(height * 2.2))/2 + ascent;
	    y1 = y0 + (int)(height * 1.2);
	}
	g.setColor(c);                     // Set the color
	g.drawString(s1, x0, y0);          // Draw the strings
	if (s2 != null) g.drawString(s2, x1, y1);
    }
}
