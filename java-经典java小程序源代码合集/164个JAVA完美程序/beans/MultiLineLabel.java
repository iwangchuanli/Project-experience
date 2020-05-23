/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.beans;
import java.awt.*;
import java.util.*;

/**
 * A custom component that displays multiple lines of text with specified
 * margins and alignment.  In Java 1.1 we could also subclass Component, 
 * making this a "lightweight" component.  Instead, we try to maintain 
 * Java 1.0 compatibility for this component.  This means that you will see
 * deprecation warnings when you compile this class with Java 1.1 or later.
 **/
public class MultiLineLabel extends Canvas {
    // User-specified properties
    protected String label;             // The label, not broken into lines
    protected int margin_width;         // Left and right margins
    protected int margin_height;        // Top and bottom margins
    protected Alignment alignment;      // The alignment of the text.

    // Computed state values
    protected int num_lines;            // The number of lines
    protected String[] lines;           // The label, broken into lines
    protected int[] line_widths;        // How wide each line is
    protected int max_width;            // The width of the widest line
    protected int line_height;          // Total height of the font
    protected int line_ascent;          // Font height above baseline
    protected boolean measured = false; // Have the lines been measured?
    
    // Here are five versions of the constructor.
    public MultiLineLabel(String label, int margin_width,
			  int margin_height, Alignment alignment) {
	this.label = label;                 // Remember all the properties.
	this.margin_width = margin_width;
	this.margin_height = margin_height;
	this.alignment = alignment;
	newLabel();                         // Break the label up into lines.
    }

    public MultiLineLabel(String label, int margin_width, int margin_height) {
	this(label, margin_width, margin_height, Alignment.LEFT);
    }

    public MultiLineLabel(String label, Alignment alignment) {
	this(label, 10, 10, alignment);
    }

    public MultiLineLabel(String label) { this(label, 10, 10, Alignment.LEFT);}

    public MultiLineLabel() { this(""); }
    
    // Methods to set and query the various attributes of the component.
    // Note that some query methods are inherited from the superclass.
    public void setLabel(String label) {
	this.label = label;
	newLabel();               // Break the label into lines.
	measured = false;         // Note that we need to measure lines.
	repaint();                // Request a redraw.
    }

    public void setFont(Font f) {
	super.setFont(f);         // Tell our superclass about the new font.
	measured = false;         // Note that we need to remeasure lines.
	repaint();                // Request a redraw.
    }

    public void setForeground(Color c) {
	super.setForeground(c);   // Tell our superclass about the new color.
	repaint();                // Request a redraw (size is unchanged).
    }

    public void setAlignment(Alignment a) { alignment = a; repaint(); }
    public void setMarginWidth(int mw) { margin_width = mw; repaint(); }
    public void setMarginHeight(int mh) { margin_height = mh; repaint(); }

    // Property getter methods.  Note that getFont(), getForeground(), etc.
    // are inherited from the superclass.
    public String getLabel() { return label; }
    public Alignment getAlignment() { return alignment; }
    public int getMarginWidth() { return margin_width; }
    public int getMarginHeight() { return margin_height; }
    
    /**
     * This method is called by a layout manager when it wants to
     * know how big we'd like to be.  In Java 1.1, getPreferredSize() is
     * the preferred version of this method.  We use this deprecated version
     * so that this component can interoperate with 1.0 components.
     */
    public Dimension preferredSize() {
	if (!measured) measure();
	return new Dimension(max_width + 2*margin_width,
			     num_lines * line_height + 2*margin_height);
    }

    /**
     * This method is called when the layout manager wants to know
     * the bare minimum amount of space we need to get by.
     * For Java 1.1, we'd use getMinimumSize().
     */
    public Dimension minimumSize() { return preferredSize(); }
    
    /**
     * This method draws the component.
     * Note that it handles the margins and the alignment, but that
     * it doesn't have to worry about the color or font--the superclass
     * takes care of setting those in the Graphics object we're passed.
     **/
    public void paint(Graphics g) {
	int x, y;
	Dimension size = this.size();  // use getSize() in Java 1.1
	if (!measured) measure();
	y = line_ascent + (size.height - num_lines * line_height)/2;
	for(int i = 0; i < num_lines; i++, y += line_height) {
	    if (alignment == Alignment.LEFT) x = margin_width;
	    else if (alignment == Alignment.CENTER) 
		x = (size.width - line_widths[i])/2; 
	    else x = size.width - margin_width - line_widths[i];
	    g.drawString(lines[i], x, y);
	}
    }

    /**
     * This internal method breaks a specified label up into an array of lines.
     * It uses the StringTokenizer utility class.
     **/
    protected synchronized void newLabel() {
	StringTokenizer t = new StringTokenizer(label, "\n");
	num_lines = t.countTokens();
	lines = new String[num_lines];
	line_widths = new int[num_lines];
	for(int i = 0; i < num_lines; i++) lines[i] = t.nextToken();
    }
    
    /**
     * This internal method figures out how the font is, and how wide each
     * line of the label is, and how wide the widest line is.
     **/
    protected synchronized void measure() {
	FontMetrics fm = this.getToolkit().getFontMetrics(this.getFont());
	line_height = fm.getHeight();
	line_ascent = fm.getAscent();
	max_width = 0;
	for(int i = 0; i < num_lines; i++) {
	    line_widths[i] = fm.stringWidth(lines[i]);
	    if (line_widths[i] > max_width) max_width = line_widths[i];
	}
	measured = true;
    }
}
