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

/** This class demonstrates how you might use the Rect class */
public class RectTest {
    public static void main(String[] args) {
        Rect r1 = new Rect(1, 1, 4, 4);    // Create Rect objects
        Rect r2 = new Rect(2, 3, 5, 6);
        Rect u = r1.union(r2);             // Invoke Rect methods
        Rect i = r2.intersection(r1);
	
        if (u.isInside(r2.x1, r2.y1))   // Use Rect fields and invoke a method
            System.out.println("(" + r2.x1 + "," + r2.y1 +
			       ") is inside the union");
	
        // These lines implicitly call the Rect.toString() method
        System.out.println(r1 + " union " + r2 + " = " + u);
        System.out.println(r1 + " intersect " + r2 + " = " + i);
    }
}
