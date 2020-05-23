/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.thread;
import java.io.*;
import java.awt.*;     // AWT classes for the demo program
import javax.swing.*;  // Swing GUI classes for the demo

/**
 * This class contains a useful static method for listing all threads
 * and threadgroups in the VM.  It also has a simple main() method so it
 * can be run as a standalone program.
 **/
public class ThreadLister {
    /** Display information about a thread. */
    private static void printThreadInfo(PrintWriter out, Thread t, 
					String indent) {
        if (t == null) return;
        out.println(indent + "Thread: " + t.getName() +
		    "  Priority: " + t.getPriority() +
		    (t.isDaemon()?" Daemon":"") +
		    (t.isAlive()?"":" Not Alive"));
    }
    
    /** Display info about a thread group and its threads and groups */
    private static void printGroupInfo(PrintWriter out, ThreadGroup g, 
				       String indent) {
        if (g == null) return;
        int num_threads = g.activeCount();
        int num_groups = g.activeGroupCount();
        Thread[] threads = new Thread[num_threads];
        ThreadGroup[] groups = new ThreadGroup[num_groups];
        
        g.enumerate(threads, false);
        g.enumerate(groups, false);
        
        out.println(indent + "Thread Group: " + g.getName() + 
		    "  Max Priority: " + g.getMaxPriority() +
		    (g.isDaemon()?" Daemon":""));
        
        for(int i = 0; i < num_threads; i++)
            printThreadInfo(out, threads[i], indent + "    ");
        for(int i = 0; i < num_groups; i++)
            printGroupInfo(out, groups[i], indent + "    ");
    }
    
    /** Find the root thread group and list it recursively */
    public static void listAllThreads(PrintWriter out) {
        ThreadGroup current_thread_group;
        ThreadGroup root_thread_group;
        ThreadGroup parent;
        
        // Get the current thread group
        current_thread_group = Thread.currentThread().getThreadGroup();
        
        // Now go find the root thread group
        root_thread_group = current_thread_group;
        parent = root_thread_group.getParent();
        while(parent != null) {
            root_thread_group = parent;
            parent = parent.getParent();
        }
        
        // And list it, recursively
        printGroupInfo(out, root_thread_group, "");
    }
    
    /**
     * The main() method create a simple graphical user interface to display
     * the threads in.  This allows us to see the "event dispatch thread" used
     * by AWT and Swing.
     **/
    public static void main(String[] args) {
	// Create a simple Swing GUI
	JFrame frame = new JFrame("ThreadLister Demo");
	JTextArea textarea = new JTextArea();
	frame.getContentPane().add(new JScrollPane(textarea),
				   BorderLayout.CENTER);
	frame.setSize(500, 400);
	frame.setVisible(true);

	// Get the threadlisting as a string using a StringWriter stream
	StringWriter sout = new StringWriter();  // To capture the listing 
	PrintWriter out = new PrintWriter(sout);
	ThreadLister.listAllThreads(out);        // List threads to stream
	out.close();                             
	String threadListing = sout.toString();  // Get listing as a string

	// Finally, display the thread listing in the GUI
	textarea.setText(threadListing);
    }
}
