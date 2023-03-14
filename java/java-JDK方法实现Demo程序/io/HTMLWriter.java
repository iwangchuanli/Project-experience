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
import java.net.*;
import java.applet.Applet;
import netscape.javascript.JSObject;    // A special class we need

/**
 * An output stream that sends HTML text to a newly created web browser window.
 * It relies on the netscape.javascript.JSObject class to send JavaScript
 * commands to the Web browser, and only works for applets running in 
 * the Netscape Navigator Web browser.
 **/
public class HTMLWriter extends Writer {
    JSObject main_window;        // the initial browser window
    JSObject window;             // the new window we create
    JSObject document;           // the document of that new window
    static int window_num = 0;   // used to give each new window a unique name
    
    /**
     * When you create a new HTMLWriter, it pops up a new, blank, Web browser
     * window to display the output in.  You must specify the applet
     * (this specifies the main browser window) and the desired size
     * for the new window.
     **/
    public HTMLWriter(Applet applet, int width, int height) {
        // Verify that we can find the JSObject class we need.  Warn if not.
        try { Class c = Class.forName("netscape.javascript.JSObject"); }
        catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError("HTMLWriter requires " +
        	   "Netscape Navigator 4.0 or higher " +
		   "or a browser that supports LiveConnect technology");
        }
	
        // Get a reference to the main browser window from the applet.
        main_window = JSObject.getWindow(applet);
	
        // Create a new window to display output in.  This command sends a
	// string of JavaScript to the web browser
        window = (JSObject)
            main_window.eval("self.open(''," +
			     "'HTMLWriter" + window_num++ + "'," +
			     "'menubar,status,resizable,scrollbars," +
			     "width=" + width + ",height=" + height + "')");

        // Obtain the Document object of this new window, and open it.
        document = (JSObject) window.getMember("document");
        document.call("open", null);
    }
    
    /**
     * This is the write() method required for all Writer subclasses.
     * Writer defines all its other write() methods in terms of this one.
     **/
    public void write(char[] buf, int offset, int length) {
        // If no window or document, do nothing.   This occurs if the stream
        // has been closed, or if the code is not running in Navigator.
        if ((window == null) || (document == null)) return;
        // If the window has been closed by the user, do nothing
        if (((Boolean)window.getMember("closed")).booleanValue()) return;
        // Otherwise, create a string from the specified bytes
        String s = new String(buf, offset, length);
        // And pass it to the JS document.write() method to output the HTML
        document.call("write", new String[] { s });
    }
    
    /**
     * There is no general way to force JavaScript to flush all pending output,
     * so this method does nothing.  To flush, output a <P> tag or some other
     * HTML tag that forces a line break in the output.
     **/
    public void flush() {}

    /**
     * When the stream is closed, close the JavaScript Document object
     * (But don't close the window yet.)
     **/
    public void close() { document.call("close", null); document = null; }
    
    /**
     * If the browser window is still open, close it.
     * This method is unique to HTMLWriter.  
     **/
    public void closeWindow() {
        if (document != null) close();
        if (!((Boolean)window.getMember("closed")).booleanValue())
            window.call("close", null);
        window = null;
    }
    
    /** A finalizer method to close the window in case we forget. */
    public void finalize() { closeWindow(); }
    
    /**
     * This nested class is an applet that demonstrates the use of HTMLWriter.
     * It reads the contents of the URL specified in its url parameter and
     * writes them out to an HTMLWriter stream.  It will only work in 
     * Netscape 4.0 or later.  It requires an <APPLET> tag like this:
     *   <APPLET CODE="HTMLWriter$Test.class" WIDTH=10 HEIGHT=10 MAYSCRIPT>
     *   <PARAM NAME="url" VALUE="HTMLWriter.java">
     *   </APPLET>
     * Note that MAYSCRIPT attribute.  It is required to enable the applet
     * to invoke JavaScript.
     **/
    public static class Test extends Applet {
        HTMLWriter out;
        /** When the applet starts, read and display specified URL */
        public void init() {  
            try {
                // Get the URL specified in the <PARAM> tag
                URL url =
		    new URL(this.getDocumentBase(), this.getParameter("url"));
                // Get a stream to read its contents
                Reader in = new InputStreamReader(url.openStream());
                // Create an HTMLWriter stream for out output
                out = new HTMLWriter(this, 400, 200);
                // Read buffers of characters and output them to the HTMLWriter
                char[] buffer = new char[4096];
                int numchars;
                while((numchars = in.read(buffer)) != -1) 
                    out.write(buffer, 0, numchars);
                // Close the streams
                in.close();
                out.close();
            }
            catch (IOException e) {}
        }
        /** When the applet terminates, close the window we created */
        public void destroy() { out.closeWindow(); }
    }
}
