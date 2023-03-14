/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.basics;
/**
 * This class doesn't define a main() method, so it isn't a program by itself.
 * It does define a useful method that we can use in other programs, though.
 **/
public class Factorial {
    /** Compute and return x!, the factorial of x */
    public static int factorial(int x) {
	if (x < 0) throw new IllegalArgumentException("x must be >= 0");
        int fact = 1;
        for(int i = 2; i <= x; i++)    // loop
            fact *= i;                 // shorthand for: fact = fact * i;
        return fact;
    }
}
