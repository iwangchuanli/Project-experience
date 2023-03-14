/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.serialization;
import java.io.*;

/**
 * A simple class that implements a growable array of ints, and knows
 * how to serialize itself as efficiently as a non-growable array.
 **/
public class IntList implements Serializable {
    protected int[] data = new int[8]; // An array to store the numbers.
    protected transient int size = 0;  // Index of next unused element of array
    
    /** Return an element of the array */
    public int get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size) throw new ArrayIndexOutOfBoundsException(index);
        else return data[index];
    }
    
    /** Add an int to the array, growing the array if necessary */
    public void add(int x) {
        if (data.length==size) resize(data.length*2);  // Grow array if needed.
        data[size++] = x;                              // Store the int in it.
    }
    
    /** An internal method to change the allocated size of the array */
    protected void resize(int newsize) {
	int[] newdata = new int[newsize];            // Create a new array
        System.arraycopy(data, 0, newdata, 0, size); // Copy array elements.
	data = newdata;                              // Replace old array
    }
    
    /** Get rid of unused array elements before serializing the array */
    private void writeObject(ObjectOutputStream out) throws IOException {
        if (data.length > size) resize(size);  // Compact the array.
        out.defaultWriteObject();              // Then write it out normally.
    }
    
    /** Compute the transient size field after deserializing the array */
    private void readObject(ObjectInputStream in)
	throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();                // Read the array normally.
        size = data.length;                    // Restore the transient field.
    }

    /**
     * Does this object contain the same values as the object o?
     * We override this Object method so we can test the class.
     **/
    public boolean equals(Object o) {
	if (!(o instanceof IntList)) return false;
	IntList that = (IntList) o;
	if (this.size != that.size) return false;
	for(int i = 0; i < this.size; i++)
	    if (this.data[i] != that.data[i]) return false;
	return true;
    }

    /** A main() method to prove that it works */
    public static void main(String[] args) throws Exception {
	IntList list = new IntList();
	for(int i = 0; i < 100; i++) list.add((int)(Math.random()*40000));
	IntList copy = (IntList)Serializer.deepclone(list);
	if (list.equals(copy)) System.out.println("equal copies");
	Serializer.store(list, new File("intlist.ser"));
    }
}
