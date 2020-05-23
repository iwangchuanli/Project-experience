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
 * This class defines methods for computing pseudo-random numbers, and defines
 * the state variable that needs to be maintained for use by those methods.
 **/
public class Randomizer {
    // Carefully chosen constants from the book "Numerical Recipes in C".
    // All "static final" fields are constants.
    static final int m = 233280;
    static final int a = 9301;
    static final int c = 49297;

    // The state variable maintained by each Randomizer instance
    int seed = 1;
    
    /** 
     * The constructor for the Randomizer() class.  It must be passed some
     * arbitrary initial value or "seed" for its pseudo-randomness.
     **/
    public Randomizer(int seed) { this.seed = seed; }
    
    /**
     * This method computes a pseudo-random number between 0 and 1 using a very
     * simple algorithm.  Math.random() and java.util.Random are actually a lot
     * better at computing randomness.
     **/
    public float randomFloat() {
        seed = (seed * a + c) % m;
        return (float) Math.abs((float)seed/(float)m);
    }
    
    /** 
     * This method computes a pseudo-random integer between 0 and specified
     * maximum.  It uses randomFloat() above.
     **/
    public int randomInt(int max) {
        return Math.round(max * randomFloat());
    }
    
    /**
     * This nested class is a simple test program: it prints 10 random ints.
     * Note how the Randomizer object is seeded using the current time.
     **/
    public static class Test {
        public static void main(String[] args) {
            Randomizer r = new Randomizer((int)new java.util.Date().getTime());
            for(int i = 0; i < 10; i++) System.out.println(r.randomInt(100));
        }
    }
}
