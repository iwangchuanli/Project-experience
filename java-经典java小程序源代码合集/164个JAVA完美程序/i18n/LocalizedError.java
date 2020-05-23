/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.i18n;
import java.text.*;
import java.io.*;
import java.util.*;

/**
 * A convenience class that can display a localized exception message
 * depending on the class of the exception.  It uses a MessageFormat,
 * and passes five arguments that the localized message may include:
 *   {0}: the message included in the exception or error.
 *   {1}: the full class name of the exception or error.
 *   {2}: a guess at what file the exception was caused by.
 *   {3}: a line number in that file.
 *   {4}: the current date and time.
 * Messages are looked up in a ResourceBundle with the basename
 * "Errors", using a the full class name of the exception object as
 * the resource name.  If no resource is found for a given exception
 * class, the superclasses are checked.
 **/
public class LocalizedError {
    public static void display(Throwable error) {
        ResourceBundle bundle;
        // Try to get the resource bundle.
        // If none, print the error in a non-localized way.
        try { bundle = ResourceBundle.getBundle("Errors"); }
        catch (MissingResourceException e) {
            error.printStackTrace(System.err);
            return;
        }
	
        // Look up a localized message resource in that bundle, using the
        // classname of the error (or its superclasses) as the resource name.
        // If no resource was found, display the error without localization.
        String message = null;
        Class c = error.getClass();
        while((message == null) && (c != Object.class)) {
            try { message = bundle.getString(c.getName()); }
            catch (MissingResourceException e) { c = c.getSuperclass(); }
        }
        if (message == null) { error.printStackTrace(System.err);  return; }
	
        // Try to figure out the filename and line number of the
        // exception.  Output the error's stack trace into a string, and
        // use the heuristic that the first line number that appears in
        // the stack trace is after the first or  second colon.  We assume that
        // this stack frame is the first one the programmer has any control
        // over, and so report it as the location of the exception.
	// Note that this is implementation-dependent and not robust...
        String filename = "";
        int linenum = 0;
        try {
            StringWriter sw = new StringWriter(); // Output stream to a string.
            PrintWriter out=new PrintWriter(sw);  // PrintWriter wrapper.
            error.printStackTrace(out);           // Print stacktrace.
            String trace = sw.toString();         // Get it as a string.
            int pos = trace.indexOf(':');         // Look for first colon.
            if (error.getMessage() != null)       // If the error has a message
                pos = trace.indexOf(':', pos+1);  // look for second colon.
            int pos2 = trace.indexOf(')', pos);   // Look for end of line #
            linenum = Integer.parseInt(trace.substring(pos+1,pos2)); // line #
            pos2 = trace.lastIndexOf('(', pos);   // Back to start of filename.
            filename = trace.substring(pos2+1, pos); // Get filename.
        }
        catch (Exception e) { ; }                  // Ignore exceptions.

        // Set up an array of arguments to use with the message
        String errmsg = error.getMessage();
        Object[] args = {
            ((errmsg!= null)?errmsg:""), error.getClass().getName(),
            filename, new Integer(linenum), new Date()
        };
        // Finally, display the localized error message, using
        // MessageFormat.format() to substitute the arguments into the message.
        System.out.println(MessageFormat.format(message, args));
    }

    /** 
     * This is a simple test program that demonstrates the display() method.
     * You can use it to generate and display a FileNotFoundException or an
     * ArrayIndexOutOfBoundsException
     **/
    public static void main(String[] args) {
	try { FileReader in = new FileReader(args[0]); }
	catch(Exception e) { LocalizedError.display(e);	}
    }
}
