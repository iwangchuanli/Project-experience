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
 * This program sends e-mail using a mailto: URL
 **/
public class SendMail {
    public static void main(String[] args) {
        try {
            // If the user specified a mailhost, tell the system about it.
            if (args.length >= 1)
		System.getProperties().put("mail.host", args[0]);
	    
            // A Reader stream to read from the console
            BufferedReader in =
		new BufferedReader(new InputStreamReader(System.in));
	    
            // Ask the user for the from, to, and subject lines
            System.out.print("From: ");
            String from = in.readLine();
            System.out.print("To: ");
            String to = in.readLine();
            System.out.print("Subject: ");
            String subject = in.readLine();
	    
            // Establish a network connection for sending mail
            URL u = new URL("mailto:" + to);      // Create a mailto: URL 
            URLConnection c = u.openConnection(); // Create its URLConnection
            c.setDoInput(false);                  // Specify no input from it
            c.setDoOutput(true);                  // Specify we'll do output
            System.out.println("Connecting...");  // Tell the user
            System.out.flush();                   // Tell them right now
            c.connect();                          // Connect to mail host
            PrintWriter out =                     // Get output stream to host
                new PrintWriter(new OutputStreamWriter(c.getOutputStream()));

            // Write out mail headers.  Don't let users fake the From address
            out.print("From: \"" + from + "\" <" +
		      System.getProperty("user.name") + "@" + 
		      InetAddress.getLocalHost().getHostName() + ">\n");
            out.print("To: " + to + "\n");
            out.print("Subject: " + subject + "\n");
            out.print("\n");  // blank line to end the list of headers

            // Now ask the user to enter the body of the message
            System.out.println("Enter the message. " + 
			       "End with a '.' on a line by itself.");
            // Read message line by line and send it out.
            String line;
            for(;;) {
                line = in.readLine();
                if ((line == null) || line.equals(".")) break;
                out.print(line + "\n");
            }
	    
            // Close (and flush) the stream to terminate the message 
            out.close();
            // Tell the user it was successfully sent.
            System.out.println("Message sent.");
        }
        catch (Exception e) {  // Handle any exceptions, print error message.
            System.err.println(e);
            System.err.println("Usage: java SendMail [<mailhost>]");
        }
    }
}
