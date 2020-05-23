/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.classes;
/**
 * A class to compute the running average of numbers passed to it
 **/
public class Averager {
    // Private fields to hold the current state.
    private int n = 0;
    private double sum = 0.0, sumOfSquares = 0.0;

    /** 
     * This method adds a new datum into the average.
     **/
    public void addDatum(double x) {
        n++;
        sum += x;
        sumOfSquares += x * x;
    }
    
    /** This method returns the average of all numbers passed to addDatum() */
    public double getAverage() { return sum / n; }

    /** This method returns the standard deviation of the data */
    public double getStandardDeviation() {
          return Math.sqrt(((sumOfSquares - sum*sum/n)/n));
    }

    /** This method returns the number of numbers passed to addDatum() */
    public double getNum() { return n; }

    /** This method returns the sum of all numbers passed to addDatum() */
    public double getSum() { return sum; }

    /** This method returns the sum of the squares of all numbers. */
    public double getSumOfSquares() { return sumOfSquares; }

    /** This method resets the Averager object to begin from scratch */
    public void reset() { n = 0; sum = 0.0; sumOfSquares = 0.0; }

    /** 
     * This nested class is a simple test program we can use to check that 
     * our code works okay.
     **/
    public static class Test {
        public static void main(String args[]) {
            Averager a = new Averager();
            for(int i = 1; i <= 100; i++) a.addDatum(i);
            System.out.println("Average: " + a.getAverage());
            System.out.println("Standard Deviation: " +
			       a.getStandardDeviation());
            System.out.println("N: " + a.getNum());
            System.out.println("Sum: " + a.getSum());
            System.out.println("Sum of squares: " + a.getSumOfSquares());
        }
    }
}
