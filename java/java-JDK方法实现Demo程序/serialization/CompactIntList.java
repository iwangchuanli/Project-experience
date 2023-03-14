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
 * This subclass of IntList assumes that most of the integers it contains are
 * less than 32,000.  It implements Externalizable so that it can define a 
 * compact serialization format that takes advantage of this fact.
 **/
public class CompactIntList extends IntList implements Externalizable {
    /**
     * This version number is here in case a later revision of this class wants
     * to modify the externalization format, but still retain compatibility
     * with externalized objects written by this version
     **/
    static final byte version = 1;

    /**
     * This method from the Externalizable interface is responsible for saving
     * the complete state of the object to the specified stream.  It can write
     * anything it wants as long as readExternal() can read it.
     **/
    public void writeExternal(ObjectOutput out) throws IOException {
        if (data.length > size) resize(size);  // Compact the array.

	out.writeByte(version);  // Start with our version number.
	out.writeInt(size);      // Output the number of array elements
	for(int i = 0; i < size; i++) {  // Now loop through the array
	    int n = data[i];             // The array element to write
	    if ((n < Short.MAX_VALUE) && (n > Short.MIN_VALUE+1)) {
		// If n fits in a short and is not Short.MIN_VALUE, then write
		// it out as a short, saving ourselves two bytes
		out.writeShort(n);
	    }
	    else {
		// Otherwise write out the special value Short.MIN_VALUE to
		// signal that the number does not fit in a short, and then
		// output the number using a full 4 bytes, for 6 bytes total
		out.writeShort(Short.MIN_VALUE);
		out.writeInt(n);
	    }
	}
    }

    /**
     * This Externalizable method is responsible for completely restoring the
     * state of the object.  A no-arg constructor will be called to re-create
     * the object, and this method must read the state written by 
     * writeExternal() to restore the object's state.
     **/
    public void readExternal(ObjectInput in)
        throws IOException, ClassNotFoundException
    {
	// Start by reading and verifying the version number.
	byte v = in.readByte();
	if (v != version)
	    throw new IOException("CompactIntList: unknown version number");

	// Read the number of array elements, and make array that big
	int newsize = in.readInt();
	resize(newsize);
	this.size = newsize;

	// Now read that many values from the stream
	for(int i = 0; i < newsize; i++) {
	    short n = in.readShort();
	    if (n != Short.MIN_VALUE) data[i] = n;
	    else data[i] = in.readInt();
	}
    }

    /** A main() method to prove that it works */
    public static void main(String[] args) throws Exception {
	CompactIntList list = new CompactIntList();
	for(int i = 0; i < 100; i++) list.add((int)(Math.random()*40000));
	CompactIntList copy = (CompactIntList)Serializer.deepclone(list);
	if (list.equals(copy)) System.out.println("equal copies");
	Serializer.store(list, new File("compactintlist.ser"));
    }
}
