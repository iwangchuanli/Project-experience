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

/** This class defines an enumerated type with three values */
public class Alignment {
    /** This private constructor prevents anyone from instantiating us */
    private Alignment() {};
    // The following three constants are the only instances of this class
    public static final Alignment LEFT = new Alignment();
    public static final Alignment CENTER = new Alignment();
    public static final Alignment RIGHT = new Alignment();
}
