/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * This servlet maintains an arbitrary set of counter variables and increments
 * and displays the value of one named counter each time it is invoked.  It
 * saves the state of the counters to a disk file, so the counts are not lost
 * when the server shuts down.  It is suitable for counting page hits, or any
 * other type of event.  It is not typically invoked directly, but is included
 * within other pages, using JSP, SSI, or a RequestDispatcher
 **/
public class Counter extends HttpServlet {
    HashMap counts;      // A hash table: maps counter names to counts
    File countfile;      // The file that counts are saved in
    long saveInterval;   // How often (in ms) to save our state while running?
    long lastSaveTime;   // When did we last save our state?

    // This method is called when the web server first instantiates this
    // servlet.  It reads initialization parameters (which are configured
    // at deployment time in the web.xml file), and loads the initial state
    // of the counter variables from a file.
    public void init() throws ServletException {
	ServletConfig config = getServletConfig();
	try {
	    // Get the save file.
	    countfile = new File(config.getInitParameter("countfile"));
	    // How often should we save our state while running?
	    saveInterval = 
		Integer.parseInt(config.getInitParameter("saveInterval"));
	    // The state couldn't have changed before now.
	    lastSaveTime = System.currentTimeMillis();
	    // Now read in the count data
	    loadState();
	}
	catch(Exception e) {
	    // If something goes wrong, wrap the exception and rethrow it
	    throw new ServletException("Can't init Counter servlet: " +
				       e.getMessage(), e);
	}
    }

    // This method is called when the web server stops the servlet (which
    // happens when the web server is shutting down, or when the servlet is
    // not in active use.)  This method saves the counts to a file so they
    // can be restored when the servlet is restarted.
    public void destroy() {
	try { saveState(); }  // Try to save the state
	catch(Exception e) {} // Ignore any problems: we did the best we could
    }

    // These constants define the request parameter and attribute names that
    // the servlet uses to find the name of the counter to increment.
    public static final String PARAMETER_NAME = "counter";
    public static final String ATTRIBUTE_NAME =
	"com.davidflanagan.examples.servlet.Counter.counter";

    /**
     * This method is called when the servlet is invoked.  It looks for a
     * request parameter named "counter", and uses its value as the name of
     * the counter variable to increment.  If it doesn't find the request
     * parameter, then it uses the URL of the request as the name of the
     * counter.  This is useful when the servlet is mapped to a URL suffix.
     * This method also checks how much time has elapsed since it last saved
     * its state, and saves the state again if necessary.  This prevents it
     * from losing too much data if the server crashes or shuts down without
     * calling the destroy() method.
     **/
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
	// Get the name of the counter as a request parameter
	String counterName = request.getParameter(PARAMETER_NAME);
	
	// If we didn't find it there, see if it was passed to us as a
	// request attribute, which happens when the output of this servlet
	// is included by another servlet
	if (counterName == null)
	    counterName = (String) request.getAttribute(ATTRIBUTE_NAME);

	// If it wasn't a parameter or attribute, use the request URL.
	if (counterName == null) counterName = request.getRequestURI();

	Integer count;  // What is the current count?

	// This block of code is synchronized because multiple requests may
	// be running at the same time in different threads.  Synchronization
	// prevents them from updating the counts hashtable at the same time
	synchronized(counts) {
	    // Get the counter value from the hashtable
	    count = (Integer)counts.get(counterName);
	    
	    // Increment the counter, or if it is new, log and start it at 1
	    if (count != null) count = new Integer(count.intValue() + 1);
	    else {
		// If this is a counter we haven't used before, send a message
		// to the log file, just so we can track what we're counting
		log("Starting new counter: " + counterName);
		// Start counting at 1!
		count = new Integer(1);
	    }
	    
	    // Store the incremented (or new) counter value into the hashtable
	    counts.put(counterName, count);

	    // Check whether saveInterval milliseconds have elapsed since we
	    // last saved our state.  If so, save it again.  This prevents
	    // us from losing more than saveInterval ms of data, even if the
	    // server crashes unexpectedly.  
	    if (System.currentTimeMillis() - lastSaveTime > saveInterval) {
		saveState();
		lastSaveTime = System.currentTimeMillis();
	    }
	}  // End of synchronized block
	
	// Finally, output the counter value.  Since this servlet is usually
	// included within the output of other servlets, we don't bother
	// setting the content type.
	PrintWriter out = response.getWriter();
	out.print(count);
    }

    // The doPost method just calls doGet, so that this servlet can be
    // included in pages that are loaded with POST requests
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws IOException
    {
	doGet(request, response);
    }

    // Save the state of the counters by serializing the hashtable to
    // the file specified by the initialization parameter.
    void saveState() throws IOException {
	ObjectOutputStream out = new ObjectOutputStream(
		    new BufferedOutputStream(new FileOutputStream(countfile)));
	out.writeObject(counts);  // Save the hashtable to the stream
	out.close();              // Always remember to close your files!
    }

    // Load the initial state of the counters by de-serializing a hashtable
    // from the file specified by the initialization parameter.  If the file
    // doesn't exist yet, then start with an empty hashtable.
    void loadState() throws IOException {
	if (!countfile.exists()) {
	    counts = new HashMap(); 
	    return;
	}
	ObjectInputStream in = null;
	try {
	    in = new ObjectInputStream(
		   new BufferedInputStream(new FileInputStream(countfile)));
	    counts = (HashMap) in.readObject();
	}
	catch(ClassNotFoundException e) {
	    throw new IOException("Count file contains bad data: " +
				  e.getMessage());
	}
	finally {
	    try { in.close(); }
	    catch (Exception e) {}
	}
    }
}
