/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.io;
import java.io.*;

/**
 * A simple FilterReader that strips HTML tags (or anything between
 * pairs of angle brackets) out of a stream of characters.
 **/
public class RemoveHTMLReader extends FilterReader {
    /** A trivial constructor.  Just initialize our superclass */
    public RemoveHTMLReader(Reader in) { super(in); }
    
    boolean intag = false;    // Used to remember whether we are "inside" a tag

    /** 
     * This is the implementation of the no-op read() method of FilterReader.
     * It calls in.read() to get a buffer full of characters, then strips
     * out the HTML tags.  (in is a protected field of the superclass).
     **/
    public int read(char[] buf, int from, int len) throws IOException {
        int numchars = 0;        // how many characters have been read
        // Loop, because we might read a bunch of characters, then strip them
        // all out, leaving us with zero characters to return.
        while (numchars == 0) {
            numchars = in.read(buf, from, len); // Read characters
            if (numchars == -1) return -1;      // Check for EOF and handle it.

            // Loop through the characters we read, stripping out HTML tags.
            // Characters not in tags are copied over previous tags 
            int last = from;                    // Index of last non-HTML char
            for(int i = from; i < from + numchars; i++) { 
                if (!intag) {                      // If not in an HTML tag
                    if (buf[i] == '<') intag = true; // check for tag start
                    else buf[last++] = buf[i];       // and copy the character
                }
                else if (buf[i] == '>') intag = false;  // check for end of tag
            }
            numchars = last - from; // Figure out how many characters remain
        }                           // And if it is more than zero characters
        return numchars;            // Then return that number.
    } 
    
    /** 
     * This is another no-op read() method we have to implement.  We 
     * implement it in terms of the method above.  Our superclass implements
     * the remaining read() methods in terms of these two.
     **/
    public int read() throws IOException { 
        char[] buf = new char[1];
        int result = read(buf, 0, 1);
        if (result == -1) return -1;
        else return (int)buf[0];
    }
    
    /** This class defines a main() method to test the RemoveHTMLReader */
    public static class Test {
        /** The test program: read a text file, strip HTML, print to console */
        public static void main(String[] args) {
            try {
                if (args.length != 1) 
                    throw new IllegalArgumentException("Wrong number of args");
                // Create a stream to read from the file and strip tags from it
                BufferedReader in = new BufferedReader(
			     new RemoveHTMLReader(new FileReader(args[0])));
                // Read line by line, printing lines to the console
                String line;
                while((line = in.readLine()) != null)
                    System.out.println(line);
                in.close();  // Close the stream.
            }
            catch(Exception e) {
                System.err.println(e);
                System.err.println("Usage: java RemoveHTMLReader$Test" +
				   " <filename>");
            }
        }
    }
}
