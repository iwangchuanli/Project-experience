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
 * This class is much like the FizzBuzz class, but uses a switch statement
 * instead of repeated if/else statements
 **/
public class FizzBuzz2 {
    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++) { // count from 1 to 100
            switch(i % 35) {      // What's the remainder when divided by 35?
            case 0:                      // For multiples of 35...
                System.out.print("fizzbuzz ");  // print "fizzbuzz".
                break;                          // Don't forget this statement!
            case 5: case 10: case 15:    // If the remainder is any of these
            case 20: case 25: case 30:   // then the number is a multiple of 5
                System.out.print("fizz ");      // so print "fizz".
                break;
            case 7: case 14: case 21: case 28:  // For any multiple of 7...
                System.out.print("buzz ");      // print "buzz".
                break;
            default:                            // For any other number...
                System.out.print(i + " ");      // print the number.
                break;
            }
        }
        System.out.println();
    }
}
