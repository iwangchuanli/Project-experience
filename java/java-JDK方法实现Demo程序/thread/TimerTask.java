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

/**
 * This class implements the same API as the Java 1.3 java.util.TimerTask.
 * Note that a TimerTask can only be scheduled on one Timer at a time, but
 * that this implementation does not enforce that constraint.
 **/
public abstract class TimerTask implements Runnable {
    boolean cancelled = false;    // Has it been cancelled?
    long nextTime = -1;           // When is it next scheduled?
    long period;                  // What is the execution interval
    boolean fixedRate;            // Fixed-rate execution?

    protected TimerTask() {}

    /**
     * Cancel the execution of the task.  Return true if it was actually
     * running, or false if it was already cancelled or never scheduled.
     **/
    public boolean cancel() {
	if (cancelled) return false;         // Already cancelled;
	cancelled = true;                    // Cancel it
	if (nextTime == -1) return false;    // Never scheduled;
	return true;
    }

    /**
     * When it the timer scheduled to execute? The run() method can use this
     * to see whether it was invoked when it was supposed to be 
     **/
    public long scheduledExecutionTime() { return nextTime; }

    /**
     * Subclasses must override this to provide that code that is to be run.
     * The Timer class will invoke this from its internal thread.
     **/
    public abstract void run();

    // This method is used by Timer to tell the Task how it is scheduled.
    void schedule(long nextTime, long period, boolean fixedRate) {
	this.nextTime = nextTime;
	this.period = period;
	this.fixedRate = fixedRate;
    }

    // This will be called by Timer after Timer calls the run method.
    boolean reschedule() {
	if (period == 0 || cancelled) return false; // Don't run it again
	if (fixedRate) nextTime += period;
	else nextTime = System.currentTimeMillis() + period;
	return true;
    }
}
