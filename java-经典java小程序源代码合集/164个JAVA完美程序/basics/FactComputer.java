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
 * This program computes and displays the factorial of a number specified
 * on the command line.  It handles possible user input errors with try/catch.
 **/
public class FactComputer {
    public static void main(String[] args) {
        // Try to compute a factorial.
	// If something goes wrong, handle it in the catch clause below.
        try {
            int x = Integer.parseInt(args[0]);
            System.out.println(x + "! = " + Factorial4.factorial(x));
        }
        // The user forgot to specify an argument.
	// Thrown if args[0] is undefined.
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You must specify an argument");
            System.out.println("Usage: java FactComputer <number>");
        }
        // The argument is not a number.  Thrown by Integer.parseInt().
        catch (NumberFormatException e) {
            System.out.println("The argument you specify must be an integer");
        }
        // The argument is < 0.  Thrown by Factorial4.factorial()
        catch (IllegalArgumentException e) {
            // Display the message sent by the factorial() method:
            System.out.println("Bad argument: " + e.getMessage());
        }
    }
}
