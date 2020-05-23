/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.rmi;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import com.davidflanagan.examples.rmi.Mud.*;

/**
 * This is the simplest of the remote objects that we implement for the MUD.
 * It maintains only a little bit of state, and has only two exported
 * methods 
 **/
public class MudPerson extends UnicastRemoteObject implements RemoteMudPerson {
    String name;             // The name of the person 
    String description;      // The person's description
    PrintWriter tellStream;  // Where to send messages we receive to
    
    public MudPerson(String n, String d, PrintWriter out)
	throws RemoteException
    {
        name = n;
        description = d;
        tellStream = out;
    }
    
    /** Return the person's name.  Not a remote method */
    public String getName() { return name; }
    
    /** Set the person's name.  Not a remote method */
    public void setName(String n) { name = n; }
    
    /** Set the person's description.  Not a remote method */
    public void setDescription(String d) { description = d; }
    
    /** Set the stream that messages to us should be written to. Not remote. */
    public void setTellStream(PrintWriter out) { tellStream = out; }
    
    /** A remote method that returns this person's description */
    public String getDescription() throws RemoteException {
	return description;
    }
    
    /** 
     * A remote method that delivers a message to the person.
     * I.e. it delivers a message to the user controlling the "person"
     **/
    public void tell(String message) throws RemoteException {
        tellStream.println(message);
        tellStream.flush();
    }
}
