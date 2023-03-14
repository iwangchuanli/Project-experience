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
import java.util.Date;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * This class is a simple implementation of the Java 1.3 java.util.Timer API
 **/
public class Timer {
    // This sorted set stores the tasks that this Timer is responsible for.
    // It uses a comparator (defined below) to sort the task by execution time.
    TreeSet tasks = new TreeSet(new TimerTaskComparator());

    // This is the thread the timer uses to execute the tasks
    TimerThread timer;

    /** This constructor create a Timer that does not use a daemon thread */
    public Timer() { this(false); }

    /** The main constructor: the internal thread is a daemon if specified */
    public Timer(boolean isDaemon) {
	timer = new TimerThread(isDaemon);  // TimerThread is defined below
	timer.start();                      // Start the thread running
    }

    /** Stop the timer thread, and discard all scheduled tasks */
    public void cancel() {
	synchronized(tasks) {     // Only one thread at a time!
	    timer.pleaseStop();   // Set a flag asking the thread to stop
	    tasks.clear();        // Discard all tasks
	    tasks.notify();       // Wake up the thread if it is in wait().
	}
    }

    /** Schedule a single execution after delay milliseconds */
    public void schedule(TimerTask task, long delay) {
	task.schedule(System.currentTimeMillis() + delay, 0, false);
	schedule(task);
    }

    /** Schedule a single execution at the specified time */
    public void schedule(TimerTask task, Date time) {
	task.schedule(time.getTime(), 0, false);
	schedule(task);
    }

    /** Schedule a periodic execution starting at the specified time */
    public void schedule(TimerTask task, Date firstTime, long period) {
	task.schedule(firstTime.getTime(), period, false);
	schedule(task);
    }

    /** Schedule a periodic execution starting after the specified delay */
    public void schedule(TimerTask task, long delay, long period) {
	task.schedule(System.currentTimeMillis() + delay, period, false);
	schedule(task);
    }

    /** 
     * Schedule a periodic execution starting after the specified delay.
     * Schedule fixed-rate executions period ms after the start of the last.
     * Instead of fixed-interval executions measured from the end of the last.
     **/
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
	task.schedule(System.currentTimeMillis() + delay, period, true);
	schedule(task);
    }

    /** Schedule a periodic execution starting after the specified time */
    public void scheduleAtFixedRate(TimerTask task, Date firstTime,
				    long period)
    {
	task.schedule(firstTime.getTime(), period, true);
	schedule(task);
    }


    // This internal method adds a task to the sorted set of tasks
    void schedule(TimerTask task) {
	synchronized(tasks) {  // Only one thread can modify tasks at a time!
	    tasks.add(task);   // Add the task to the sorted set of tasks
	    tasks.notify();    // Wake up the thread if it is waiting
	}
    }

    /** 
     * This inner class is used to sort tasks by next execution time.
     **/
    static class TimerTaskComparator implements Comparator {
	public int compare(Object a, Object b) {
	    TimerTask t1 = (TimerTask) a;
	    TimerTask t2 = (TimerTask) b;
	    long diff = t1.nextTime - t2.nextTime;
	    if (diff < 0) return -1;
	    else if (diff > 0) return 1;
	    else return 0;
	}
	public boolean equals(Object o) { return this == o; }
    }

    /**
     * This inner class defines the thread that runs each of the tasks at their
     * scheduled times
     **/
    class TimerThread extends Thread {
	// This flag is will be set true to tell the thread to stop running.
	// Note that it is declared volatile, which means that it may be 
	// changed asynchronously by another thread, so threads must always
	// read its true value, and not used a cached version.
	volatile boolean stopped = false;  

	// The constructor
	public TimerThread(boolean isDaemon) { setDaemon(isDaemon); }

	// Ask the thread to stop by setting the flag above
	public void pleaseStop() { stopped = true; }

	// This is the body of the thread
	public void run() {
	    TimerTask readyToRun = null;  // Is there a task to run right now?

	    // The thread loops until the stopped flag is set to true.
	    while(!stopped) {
		// If there is a task that is ready to run, then run it!
		if (readyToRun != null) { 
		    if (readyToRun.cancelled) {  // If it was cancelled, skip.
			readyToRun = null;
			continue;
		    }
		    // Run the task.
		    readyToRun.run();
		    // Ask it to reschedule itself, and if it wants to run 
		    // again, then insert it back into the set of tasks.
		    if (readyToRun.reschedule())
			schedule(readyToRun);
		    // We've run it, so there is nothing to run now
		    readyToRun = null;
		    // Go back to top of the loop to see if we've been stopped
		    continue;
		}

		// Now acquire a lock on the set of tasks
		synchronized(tasks) {
		    long timeout;  // how many ms 'till the next execution?

		    if (tasks.isEmpty()) {   // If there aren't any tasks
			timeout = 0;  // Wait 'till notified of a new task
		    }
		    else {
			// If there are scheduled tasks, then get the first one
			// Since the set is sorted, this is the next one.
			TimerTask t = (TimerTask) tasks.first();
			// How long 'till it is next run?
			timeout = t.nextTime - System.currentTimeMillis();
			// Check whether it needs to run now
			if (timeout <= 0) {
			    readyToRun = t;  // Save it as ready to run
			    tasks.remove(t); // Remove it from the set
			    // Break out of the synchronized section before
			    // we run the task
			    continue;
			}
		    }

		    // If we get here, there is nothing ready to run now,
		    // so wait for time to run out, or wait 'till notify() is
		    // called when something new is added to the set of tasks.
		    try { tasks.wait(timeout); }
		    catch (InterruptedException e) {}

		    // When we wake up, go back up to the top of the while loop
		}
	    }
	}
    }
    
    /** This inner class defines a test program */
    public static class Test {
	public static void main(String[] args) {
	    final TimerTask t1 = new TimerTask() { // Task 1: print "boom"
		    public void run() { System.out.println("boom"); }
		};
	    final TimerTask t2 = new TimerTask() { // Task 2: print "BOOM"
		    public void run() { System.out.println("\tBOOM"); }
		};
	    final TimerTask t3 = new TimerTask() { // Task 3: cancel the tasks
		    public void run() { t1.cancel(); t2.cancel(); }
		};
	    
	    // Create a timer, and schedule some tasks
	    final Timer timer = new Timer();
	    timer.schedule(t1, 0, 500);     // boom every .5sec starting now
	    timer.schedule(t2, 2000, 2000); // BOOM every 2s, starting in 2s
	    timer.schedule(t3, 5000);       // Stop them after 5 seconds

	    // Schedule a final task: starting in 5 seconds, count
	    // down from 5, then destroy the timer, which, since it is
	    // the only remaining thread, will cause the program to exit.
	    timer.scheduleAtFixedRate(new TimerTask() {
		    public int times = 5;
		    public void run() {
			System.out.println(times--);
			if (times == 0) timer.cancel();
		    }
		}, 
				      5000,500);
	}
    }
}
