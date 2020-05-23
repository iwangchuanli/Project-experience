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
import java.io.*; // Import all classes in java.io package.  Saves typing.

/**
 * This program displays factorials as the user enters values interactively
 **/
public class FactQuoter {
    public static void main(String[] args) throws IOException {
        // This is how we set things up to read lines of text from the user.
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        // Loop forever
        for(;;) {
            // Display a prompt to the user
            System.out.print("FactQuoter> ");
            // Read a line from the user
            String line = in.readLine();
            // If we reach the end-of-file, 
	    // or if the user types "quit", then quit
            if ((line == null) || line.equals("quit")) break;
            // Try to parse the line, and compute and print the factorial
            try { 
                int x = Integer.parseInt(line);
                System.out.println(x + "! = " + Factorial4.factorial(x)); 
            }
            // If anything goes wrong, display a generic error message
            catch(Exception e) { System.out.println("Invalid Input"); }
        }
    }
}
