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
import java.io.*;  // We're doing input, so import I/O classes

/**
 * This program reads lines of text from the user, encodes them using the 
 * trivial "Rot13" substitution cipher, and then prints out the encoded lines.
 **/
public class Rot13Input {
    public static void main(String[] args) throws IOException {
        // Get set up to read lines of text from the user
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for(;;) {                                    // Loop forever
            System.out.print("> ");                    // Print a prompt
            String line = in.readLine();               // Read a line
            if ((line == null) || line.equals("quit")) // If EOF or "quit"...
                break;                                 // ...break out of loop
            StringBuffer buf = new StringBuffer(line); // Use a StringBuffer
            for(int i = 0; i < buf.length(); i++)      // For each character...
                buf.setCharAt(i, rot13(buf.charAt(i)));// ..read, encode, store
            System.out.println(buf);                   // Print encoded line
        }
    }
    
    /**
     * This method performs the Rot13 substitution cipher.  It "rotates"
     * each letter 13 places through the alphabet.  Since the Latin alphabet
     * has 26 letters, this method both encodes and decodes.
     **/
    public static char rot13(char c) {
        if ((c >= 'A') && (c <= 'Z')) {  // For uppercase letters
            c += 13;                       // Rotate forward 13
            if (c > 'Z') c -= 26;          // And subtract 26 if necessary
        }
        if ((c >= 'a') && (c <= 'z')) {  // Do the same for lowercase letters
            c += 13;
            if (c > 'z') c -= 26;
        }
        return c;                        // Return the modified letter
    }
}
