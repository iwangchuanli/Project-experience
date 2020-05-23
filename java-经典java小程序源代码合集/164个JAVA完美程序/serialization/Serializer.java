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
 * This class defines utility routines that use Java serialization.
 **/
public class Serializer {
    /**
     * Serialize the object o (and any Serializable objects it refers to) and
     * store its serialized state in File f.
     **/
    static void store(Serializable o, File f) throws IOException {
	ObjectOutputStream out =   // The class for serialization
	    new ObjectOutputStream(new FileOutputStream(f));
	out.writeObject(o);        // This method serializes an object graph
	out.close();
    }

    /**
     * Deserialize the contents of File f and return the resulting object
     **/
    static Object load(File f) throws IOException, ClassNotFoundException {
	ObjectInputStream in =     // The class for de-serialization
	    new ObjectInputStream(new FileInputStream(f));
	return in.readObject();    // This method deserializes an object graph
    }

    /**
     * Use object serialization to make a "deep clone" of the object o.
     * This method serializes o and all objects it refers to, and then
     * deserializes that graph of objects, which means that everything is
     * copied.  This differs from the clone() method of an object which is
     * usually implemented to produce a "shallow" clone that copies references
     * to other objects, instead of copying all referenced objects.
     **/
    static Object deepclone(final Serializable o)
	throws IOException, ClassNotFoundException
    {
	// Create a connected pair of "piped" streams.  
	// We'll write bytes to one, and them from the other one.
	final PipedOutputStream pipeout = new PipedOutputStream();
	PipedInputStream pipein = new PipedInputStream(pipeout);
	
	// Now define an independent thread to serialize the object and write
	// its bytes to the PipedOutputStream
	Thread writer = new Thread() {
		public void run() {
		    ObjectOutputStream out = null;
		    try {
			out = new ObjectOutputStream(pipeout);
			out.writeObject(o);
		    }
		    catch(IOException e) {}
		    finally {
			try { out.close(); } catch (Exception e) {}
		    }
		}
	    };
	writer.start();  // Make the thread start serializing and writing

	// Meanwhile, in this thread, read and deserialize from the piped
	// input stream.  The resulting object is a deep clone of the original.
	ObjectInputStream in = new ObjectInputStream(pipein);
	return in.readObject();
    }

    /**
     * This is a simple serializable data structure that we use below for
     * testing the methods above
     **/
    public static class DataStructure implements Serializable {
	String message;        
	int[] data;            
	DataStructure other;
	public String toString() { 
	    String s = message;
	    for(int i = 0; i < data.length; i++) 
		s += " " + data[i];
	    if (other != null) s += "\n\t" + other.toString();
	    return s;
	}
    }

    /** This class defines a main() method for testing */
    public static class Test {
	public static void main(String[] args)
	    throws IOException, ClassNotFoundException
	{
	    // Create a simple object graph
	    DataStructure ds = new DataStructure();
	    ds.message = "hello world";
	    ds.data = new int[] { 1, 2, 3, 4 };
	    ds.other = new DataStructure();
	    ds.other.message = "nested structure";
	    ds.other.data = new int[] { 9, 8, 7 };

	    // Display the original object graph
	    System.out.println("Original data structure: " + ds);

	    // Output it to a file
	    File f = new File("datastructure.ser");
	    System.out.println("Storing to a file...");
	    Serializer.store(ds, f);

	    // Read it back from the file, and display it again
	    ds = (DataStructure) Serializer.load(f);
	    System.out.println("Read from the file: " + ds);

	    // Create a deep clone and display that.  After making the copy
	    // modify the original to prove that the clone is "deep".
	    DataStructure ds2 = (DataStructure) Serializer.deepclone(ds);
	    ds.other.message = null; ds.other.data = null; // Change original
	    System.out.println("Deep clone: " + ds2);
	}
    }
}
