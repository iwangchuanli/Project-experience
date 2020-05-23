/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
import com.davidflanagan.examples.net.*;  // Note no package statement here.
import java.io.*;

/**
 * This is a demonstration service.  It attempts to do things that may
 * or may not be allowed by the security policy and reports the
 * results of its attempts to the client.
 **/
public class SecureService implements Server.Service {
    public void serve(InputStream i, OutputStream o) throws IOException {
	PrintWriter out = new PrintWriter(o);

	// Try to install our own security manager.  If we can do this,
	// we can defeat any access control.
	out.println("Trying to create and install a security manager...");
	try {
	    System.setSecurityManager(new SecurityManager());
	    out.println("Success!");
	}
	catch (Exception e) { out.println("Failed: " + e); }

	// Try to make the Server and the Java VM exit.
	// This is a denial of service attack, and it should not succeed!
        out.println();
	out.println("Trying to exit...");
	try { System.exit(-1); }
	catch (Exception e) { out.println("Failed: " + e); }

	// The default system policy allows this property to be read
        out.println();
	out.println("Attempting to find java version...");
	try { out.println(System.getProperty("java.version")); }
	catch (Exception e) { out.println("Failed: " + e); }

	// The default system policy does not allow this property to be read
	out.println();
	out.println("Attempting to find home directory...");
	try { out.println(System.getProperty("user.home")); }
	catch (Exception e) { out.println("Failed: " + e); }

	// Our custom policy explicitly allows this property to be read
	out.println();
	out.println("Attempting to read service.tmp property...");
	try {
	    String tmpdir = System.getProperty("service.tmp");
	    out.println(tmpdir);
	    File dir = new File(tmpdir);
	    File f = new File(dir, "testfile");

	    // Check whether we've been given permission to write files to
	    // the tmpdir directory
	    out.println();
	    out.println("Attempting to write a file in " + tmpdir + "...");
	    try {
		new FileOutputStream(f);
		out.println("Opened file for writing: " + f);
	    }
	    catch (Exception e) { out.println("Failed: " + e); }

	    // Check whether we've been given permission to read files from
	    // the tmpdir directory
	    out.println();
	    out.println("Attempting to read from " + tmpdir + "...");
	    try {
		FileReader in = new FileReader(f);
		out.println("Opened file for reading: " + f);
	    }
	    catch (Exception e) { out.println("Failed: " + e); }
	}
	catch (Exception e) { out.println("Failed: " + e); }

	// Close the Service sockets
	out.close();
	i.close();
    }
}
