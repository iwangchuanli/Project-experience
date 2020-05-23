/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.print;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/** 
 * A "scribble" application that remembers the scribble and allows the user
 * to print it.  It displays a Swing API and uses the Java 1.2 printing API.
 * It also uses Java2D features to draw and represent the scribble.
 **/
public class ScribblePrinter2 extends JComponent implements Printable {
    Stroke linestyle = new BasicStroke(3.0f); // Draw with wide lines
    GeneralPath scribble = new GeneralPath(); // Holds the scribble

    public ScribblePrinter2() {
	// Register event types we're interested in for scribbling
	enableEvents(AWTEvent.MOUSE_EVENT_MASK |
		     AWTEvent.MOUSE_MOTION_EVENT_MASK);

	// Add a print button to he layout, and respond to it by printing
	JButton b = new JButton("Print");
	b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { printScribble(); }
	    });
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	this.add(b);
    }
    
    /** Redraw (or print) the scribble based on stored lines */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);   // Allow the superclass to draw itself
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(linestyle);   // Specify wide lines
	g2.draw(scribble);         // Draw the scribble
    }
    
    /**
     * Print out the scribble.  This is the method invoked by the Print button;
     * it is not part of the Printable interface
     **/
    public void printScribble() {
	// Obtain a java.awt.print.PrinterJob  (not java.awt.PrintJob)
	PrinterJob job = PrinterJob.getPrinterJob();

	// Tell the PrinterJob to print us (since we implement Printable)
	// using the default page layout
	job.setPrintable(this, job.defaultPage());

	// Display the print dialog that allows the user to set options.
	// The method returns false if the user cancelled the print request
	if (job.printDialog()) {
	    // If not cancelled, start printing!  This will call the print()
	    // method defined by the Printable interface.
	    try { job.print(); }
	    catch (PrinterException e) { System.err.println(e);  }
	}
    }

    /**
     * This is the method defined by the Printable interface.  It prints the
     * scribble to the specified Graphics object, respecting the paper size
     * and margins specified by the PageFormat.  If the specified page number
     * is not page 0, it returns a code saying that printing is complete.  The
     * method must be prepared to be called multiple times per printing request
     **/
    public int print(Graphics g, PageFormat format, int pagenum) {
	// We are only one page long; reject any other page numbers
	if (pagenum > 0) return Printable.NO_SUCH_PAGE;

	// The Java 1.2 printing API passes us a Graphics object, but we
	// can always cast it to a Graphics2D object
	Graphics2D g2 = (Graphics2D) g;

	// Translate to accomodate the requested top and left margins.
	g2.translate(format.getImageableX(), format.getImageableY());

	// Figure out how big the drawing is, and how big the page 
	// (excluding margins) is
	Dimension size = this.getSize();                  // Scribble size
	double pageWidth = format.getImageableWidth();    // Page width
	double pageHeight = format.getImageableHeight();  // Page height

	// If the scribble is too wide or tall for the page, scale it down
	if (size.width > pageWidth) {
	    double factor = pageWidth/size.width;  // How much to scale
	    g2.scale(factor, factor);              // Adjust coordinate system
	    pageWidth /= factor;                   // Adjust page size up
	    pageHeight /= factor;
	}
	if (size.height > pageHeight) {   // Do the same thing for height
	    double factor = pageHeight/size.height;
	    g2.scale(factor, factor);
	    pageWidth /= factor;
	    pageHeight /= factor;
	}

	// Now we know the scribble will fit on the page.  Center it by
	// translating as necessary.
	g2.translate((pageWidth-size.width)/2,(pageHeight-size.height)/2);

	// Draw a line around the outside of the drawing area
	g2.drawRect(-1, -1, size.width+2, size.height+2);
	
	// Set a clipping region so the scribbles don't go out of bounds
	g2.setClip(0, 0, size.width, size.height);

	// Finally, print the component by calling the paintComponent() method.
	// Or, call paint() to paint the component, its background, border, and
	// children, including the Print JButton
	this.paintComponent(g);

	// Tell the PrinterJob that the page number was valid
	return Printable.PAGE_EXISTS;
    }
    
    /** Called when the user clicks to begin a scribble */
    public void processMouseEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_PRESSED) {
	    scribble.moveTo(e.getX(), e.getY());  // Start a new line 
	}
	else super.processMouseEvent(e);
    }
    
    /** Called when the the user drags the mouse: does the scribbling */
    public void processMouseMotionEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
	    scribble.lineTo(e.getX(), e.getY());  // Add a line to the scribble
	    repaint();  // Redraw the whole scribble. Clean but a little slow
	}
	else super.processMouseMotionEvent(e);
    }
    
    /** The main method.  Create a ScribblePrinter2 object and away we go! */
    public static void main(String[] args) {
	JFrame frame = new JFrame("ScribblePrinter2");
	ScribblePrinter2 s = new ScribblePrinter2();
	frame.getContentPane().add(s, BorderLayout.CENTER);
	frame.setSize(400, 400);
	frame.setVisible(true);
    }
}
