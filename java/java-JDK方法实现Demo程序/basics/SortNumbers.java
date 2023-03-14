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
 * This class demonstrates how to sort numbers using a simple algorithm
 **/
public class SortNumbers {
    /**
     * This is a very simple sorting algorithm that is not very efficient
     * when sorting large numbers of things
     **/
    public static void sort(double[] nums) {
        // Loop through each element of the array, sorting as we go.
        // Each time through, find the smallest remaining element, and move it
        // to the first unsorted position in the array.
        for(int i = 0; i < nums.length; i++) {
            int min = i;  // holds the index of the smallest element
            // find the smallest one between i and the end of the array
            for(int j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) min = j;
            }
            // Now swap the smallest one with element i.  
            // This leaves all elements between 0 and i sorted.
            double tmp;
            tmp = nums[i];
            nums[i] = nums[min];
            nums[min] = tmp;
        }
    }

    /** This is a simple test program for the algorithm above */
    public static void main(String[] args) {
        double[] nums = new double[10];      // Create an array to hold numbers
        for(int i = 0; i < nums.length; i++) // Generate random numbers
            nums[i] = Math.random() * 100;
        sort(nums);                          // Sort them
        for(int i = 0; i < nums.length; i++) // Print them out
            System.out.println(nums[i]);
    }
}
