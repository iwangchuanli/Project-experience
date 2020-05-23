/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.io;
import java.io.*;
import java.util.zip.*;

/**
 * This class defines two static methods for gzipping files and zipping
 * directories.  It also defines a demonstration program as a nested class.
 **/
public class Compress {
    /** Gzip the contents of the from file and save in the to file. */
    public static void gzipFile(String from, String to) throws IOException {
        // Create stream to read from the from file
        FileInputStream in = new FileInputStream(from);
        // Create stream to compress data and write it to the to file.
        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
        // Copy bytes from one stream to the other
        byte[] buffer = new byte[4096];
        int bytes_read;
        while((bytes_read = in.read(buffer)) != -1) 
            out.write(buffer, 0, bytes_read);
        // And close the streams
        in.close();
        out.close();
    }
    
    /** Zip the contents of the directory, and save it in the zipfile */
    public static void zipDirectory(String dir, String zipfile) 
	throws IOException, IllegalArgumentException {
        // Check that the directory is a directory, and get its contents
        File d = new File(dir);
        if (!d.isDirectory())
            throw new IllegalArgumentException("Compress: not a directory:  " +
					       dir);
        String[] entries = d.list();
        byte[] buffer = new byte[4096];  // Create a buffer for copying 
        int bytes_read;
	
        // Create a stream to compress data and write it to the zipfile
        ZipOutputStream out =
	    new ZipOutputStream(new FileOutputStream(zipfile));
	
        // Loop through all entries in the directory
        for(int i = 0; i < entries.length; i++) {
            File f = new File(d, entries[i]);
            if (f.isDirectory()) continue;        // Don't zip sub-directories
            FileInputStream in = new FileInputStream(f); // Stream to read file
            ZipEntry entry = new ZipEntry(f.getPath());  // Make a ZipEntry
            out.putNextEntry(entry);                     // Store entry
            while((bytes_read = in.read(buffer)) != -1)  // Copy bytes
                out.write(buffer, 0, bytes_read);
            in.close();                                  // Close input stream
        }
        // When we're done with the whole loop, close the output stream
        out.close();
    }

    /**
     * This nested class is a test program that demonstrates the use of the
     * static methods defined above.
     **/
    public static class Test {
        /**
	 * Compress a specified file or directory.  If no destination name is
	 * specified, append .gz to a file name or .zip to a directory name
	 **/
        public static void main(String args[]) throws IOException {
            if ((args.length != 1)&& (args.length != 2)) {  // check arguments
                System.err.println("Usage: java Compress$Test <from> [<to>]");
                System.exit(0);
            }
            String from = args[0], to;
            File f = new File(from);
            boolean directory = f.isDirectory();  // Is it a file or directory?
            if (args.length == 2) to = args[1];    
            else {                             // If destination not specified
                if (directory) to = from + ".zip";   //   use a .zip suffix
                else to = from + ".gz";              //   or a .gz suffix
            }

            if ((new File(to)).exists()) { // Make sure not to overwrite
                System.err.println("Compress: won't overwrite existing file: "+
				   to);
                System.exit(0);
            }

            // Finally, call one of the methods defined above to do the work.
            if (directory) Compress.zipDirectory(from, to);
            else Compress.gzipFile(from, to);
        }
    }
}
