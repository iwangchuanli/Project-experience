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
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/** 
 * This applet connects to the "finger" server on the host
 * it was served from to determine who is currently logged on.
 * Because it is an untrusted applet, it can only connect to the host
 * from which it came.  Since web servers do not usually run finger
 * servers themselves, this applet will often be used in conjunction
 * with a proxy server, to serve it from some other host that does run
 * a finger server.
 **/
public class Who extends Applet implements ActionListener, Runnable {
    Button who;  // The button in the applet

    /**
     * The init method just creates a button to display in the applet.
     * When the user clicks the button, we'll check who is logged on.
     **/
    public void init() {
        who = new Button("Who?");
        who.setFont(new Font("SansSerif", Font.PLAIN, 14));
        who.addActionListener(this);
        this.add(who);
    }
    
    /** 
     * When the button is clicked, start a thread that will connect to
     * the finger server and display who is logged on
     **/
    public void actionPerformed(ActionEvent e) { new Thread(this).start(); }
    
    /**
     * This is the method that does the networking and displays the results.
     * It is implemented as the body of a separate thread because it might
     * take some time to complete, and applet methods need to return promptly.
     **/
    public void run() {
        // Disable the button so we don't get multiple queries at once...
        who.setEnabled(false);
	
        // Create a window to display the output in
        Frame f = new Frame("Who's Logged On: Connecting...");
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    ((Frame)e.getSource()).dispose();
		}
	    });
        TextArea t = new TextArea(10, 80);
        t.setFont(new Font("MonoSpaced", Font.PLAIN, 10));
        f.add(t, "Center");
        f.pack();
        f.show();
	
        // Find out  who's logged on
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // Connect to port 79 (the standard finger port) on the host
            // that the applet was loaded from.
            String hostname = this.getCodeBase().getHost();
            s = new Socket(hostname, 79);
            // Set up the streams
            out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    
            // Send a blank line to the finger server, telling it that we want
            // a listing of everyone logged on instead of information about an
            // individual user.
            out.print("\n");
            out.flush();    // Send it now!
	    
            // Now read the server's response and display it in the textarea
	    // The server should send lines terminated with \n.  The 
	    // readLine() method will detect these lines, even when running
	    // on a Mac that terminates lines with \r
            String line;
            while((line = in.readLine()) != null) {
                t.append(line);
                t.append("\n");
            }
	    // Update the window title to indicate we're finished
            f.setTitle("Who's Logged On: " + hostname);
        }
        // If something goes wrong, we'll just display the exception message
        catch (IOException e) { 
            t.append(e.toString()); 
            f.setTitle("Who's Logged On: Error");
        }
        // And finally, don't forget to close the streams!
        finally {
	    try { in.close(); out.close(); s.close(); } catch(Exception e) {}
	}
        
        // And enable the button again
        who.setEnabled(true);
    }
}



