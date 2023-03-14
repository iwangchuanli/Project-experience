/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.basics;

/**
 * This program plays the game "Fizzbuzz".  It counts to 100, replacing each
 * multiple of 5 with the word "fizz", each multiple of 7 with the word "buzz",
 * and each multiple of both with the word "fizzbuzz".  It uses the modulo
 * operator (%) to determine if a number is divisible by another.
 **/
public class FizzBuzz {                      // Everything in Java is a class
    public static void main(String[] args) { // Every program must have main()
        for(int i = 1; i <= 100; i++) {           // count from 1 to 100
            if (((i % 5) == 0)&& ((i % 7) == 0))  // Is it a multiple of 5 & 7?
                System.out.print("fizzbuzz");    
            else if ((i % 5) == 0)                // Is it a multiple of 5?
		System.out.print("fizz"); 
            else if ((i % 7) == 0)                // Is it a multiple of 7?
		System.out.print("buzz"); 
            else System.out.print(i);             // Not a multiple of 5 or 7
            System.out.print(" ");                
        }
        System.out.println();
    }
}
