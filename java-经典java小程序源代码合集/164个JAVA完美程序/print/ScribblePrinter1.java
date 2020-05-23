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
import java.util.*;

/** 
 * A "scribble" application that remembers the scribble and allows the user
 * to print it.  It displays an AWT API and uses the Java 1.1 printing API.
 **/
public class ScribblePrinter1 extends Panel {
    private short last_x = 0, last_y = 0;             // last click posistion
    private Vector lines = new Vector(256,256);       // store the scribble
    private Properties printprefs = new Properties(); // store user preferences
    private Frame frame;
    
    public ScribblePrinter1(Frame frame) {
	// Remember the frame: we'll need it to create a PrintJob
	this.frame = frame;

	// Register event types we're interested in for scribbling
	enableEvents(AWTEvent.MOUSE_EVENT_MASK |
		     AWTEvent.MOUSE_MOTION_EVENT_MASK);

	// Add a print button to he layout, and respond to it by printing
	Button b = new Button("Print");
	b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { printScribble(); }
	    });
	this.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	this.add(b);
    }
    
    /** Redraw (or print) the scribble based on stored lines */
    public void paint(Graphics g) {
	for(int i = 0; i < lines.size(); i++) {
	    Line l = (Line)lines.elementAt(i);
	    g.drawLine(l.x1, l.y1, l.x2, l.y2);
	}
    }
    
    /** Print out the scribble */
    public void printScribble() {
	// Obtain a PrintJob
	Toolkit toolkit = this.getToolkit();
	PrintJob job = toolkit.getPrintJob(frame, "ScribblePrinter1", printprefs);

	// If the user clicked Cancel in the print dialog, don't print
	if (job == null) return; 

	// Get the Graphics object we use to draw to the printer
	Graphics g = job.getGraphics();
	
	// Give the output a larger top and left margin.  Otherwise it will
	// be scrunched up in the upper-left corner of the page.
	g.translate(100, 100);
	
	// Draw a border around the output area.
	Dimension size = this.getSize();
	g.drawRect(-1, -1, size.width+2, size.height+2);
	
	// Set a clipping region so our scribbles don't go outside the border
	// On-screen this happens automatically, but not on paper.
	g.setClip(0, 0, size.width, size.height);

	// Print this component and all components it contains
	// This will invoke the paint() method, and will paint the button too.
	// Use print() instead of printAll() if you don't the button to show.
	this.printAll(g); 
	
	// Finish up.
	g.dispose();      // End the current page
	job.end();        // End the print job
    }
    
    /** Called when the user clicks to begin a scribble */
    public void processMouseEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_PRESSED) {
	    last_x = (short)e.getX();        // remember click position
	    last_y = (short)e.getY();
	}
	else super.processMouseEvent(e);
    }
    
    /** Called when the the user drags the mouse: does the scribbling */
    public void processMouseMotionEvent(MouseEvent e) {
	if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
	    Graphics g = getGraphics();
	    g.drawLine(last_x, last_y, e.getX(), e.getY());  // draw the line
	    lines.addElement(new Line(last_x, last_y,        // and save it
				      (short) e.getX(), (short)e.getY()));
	    last_x = (short) e.getX();
	    last_y = (short) e.getY();
	}
	else super.processMouseMotionEvent(e);
    }
    
    /** The main method.  Create a ScribblePrinter1 object and away we go! */
    public static void main(String[] args) {
	Frame frame = new Frame("ScribblePrinter1");
	ScribblePrinter1 s = new ScribblePrinter1(frame);
	frame.add(s, BorderLayout.CENTER);
	frame.setSize(400, 400);
	frame.show();
    }
    
    /** 
     * This inner class stores the coordinates of one line of the scribble.
     **/
    class Line {
	public short x1, y1, x2, y2;
	public Line(short x1, short y1, short x2, short y2) {
	    this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
	}
    }
}
