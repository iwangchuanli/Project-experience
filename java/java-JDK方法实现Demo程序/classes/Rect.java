/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.classes;
/**
 * This class represents a rectangle.  Its fields represent the coordinates
 * of the corners of the rectangle.  Its methods define operations that can
 * be performed on Rect objects.
 **/
public class Rect {
    // These are the data fields of the class
    public int x1, y1, x2, y2;

    /**
     * The is the main constructor for the class.  It simply uses its arguments
     * to initialize each of the fields of the new object.  Note that it has
     * the same name as the class, and that it has no return value declared in
     * its signature.
     **/
    public Rect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * This is another constructor.  It defines itself in terms of the above
     **/
    public Rect(int width, int height) { this(0, 0, width, height); }
    
    /** This is yet another constructor. */
    public Rect() { this(0, 0, 0, 0); }

    /** Move the rectangle by the specified amounts */
    public void move(int deltax, int deltay) {
        x1 += deltax; x2 += deltax;
        y1 += deltay; y2 += deltay;
    }

    /** Test whether the specified point is inside the rectangle */
    public boolean isInside(int x, int y) {
        return ((x >= x1)&& (x <= x2)&& (y >= y1)&& (y <= y2));
    }

    /** 
     * Return the union of this rectangle with another.  I.e. return the 
     * smallest rectangle that includes them both.
     **/
    public Rect union(Rect r) {
        return new Rect((this.x1 < r.x1) ? this.x1 : r.x1,
			(this.y1 < r.y1) ? this.y1 : r.y1,
			(this.x2 > r.x2) ? this.x2 : r.x2,
			(this.y2 > r.y2) ? this.y2 : r.y2);
    }
    
    /** 
     * Return the intersection of this rectangle with another. 
     * I.e. return their overlap.
     **/
    public Rect intersection(Rect r) {
        Rect result =  new Rect((this.x1 > r.x1) ? this.x1 : r.x1,
				(this.y1 > r.y1) ? this.y1 : r.y1,
				(this.x2 < r.x2) ? this.x2 : r.x2,
				(this.y2 < r.y2) ? this.y2 : r.y2);
        if (result.x1 > result.x2) { result.x1 = result.x2 = 0; }
        if (result.y1 > result.y2) { result.y1 = result.y2 = 0; }
        return result;
    }

    /**
      * This is a method of our superclass, Object.  We override it so that
      * Rect objects can be meaningfully converted to strings, can be 
      * concatenated to strings with the + operator, and can be passed to 
      * methods like System.out.println()
      **/
    public String toString() {
        return "[" + x1 + "," + y1 + "; " + x2 + "," + y2 + "]";
    }
}
