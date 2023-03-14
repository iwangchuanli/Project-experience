/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.net;
import java.io.*;
import java.net.*;

/**
 * This simple program uses the URL class and its openStream() method to
 * download the contents of a URL and copy them to a file or to the console.
 **/
public class GetURL {
    public static void main(String[] args) {
        InputStream in = null;   
        OutputStream out = null;
        try {
            // Check the arguments
            if ((args.length != 1)&& (args.length != 2)) 
                throw new IllegalArgumentException("Wrong number of args");
	    
            // Set up the streams
            URL url = new URL(args[0]);   // Create the URL
            in = url.openStream();        // Open a stream to it
            if (args.length == 2)         // Get an appropriate output stream
                out = new FileOutputStream(args[1]);
            else out = System.out;
	    
            // Now copy bytes from the URL to the output stream
            byte[] buffer = new byte[4096];
            int bytes_read;
            while((bytes_read = in.read(buffer)) != -1)
                out.write(buffer, 0, bytes_read);
	}
        // On exceptions, print error message and usage message.
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java GetURL <URL> [<filename>]");
        }
        finally {  // Always close the streams, no matter what.
            try { in.close();  out.close(); } catch (Exception e) {}
        }
    }
}
