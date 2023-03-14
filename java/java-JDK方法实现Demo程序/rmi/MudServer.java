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
import java.rmi.registry.*;
import java.io.*;
import java.util.Hashtable;
import java.util.zip.*;
import com.davidflanagan.examples.rmi.Mud.*;

/**
 * This class implements the RemoteMudServer interface.  It also defines a
 * main() method so you can run it as a standalone program that will
 * set up and initialize a MUD server.  Note that a MudServer maintains an
 * entrance point to a MUD, but it is not the MUD itself.  Most of the 
 * interesting MUD functionality is defined by the RemoteMudPlace interface
 * and implemented by the RemotePlace class.  In addition to being a remote
 * object, this class is also Serializable, so that the state of the MUD
 * can be saved to a file and later restored.  Note that the main() method
 * defines two ways of starting a MUD: one is to start it from scratch with
 * a single initial place, and another is to restore an existing MUD from a
 * file.
 **/
public class MudServer extends UnicastRemoteObject 
    implements RemoteMudServer, Serializable
{
    MudPlace entrance;  // The standard entrance to this MUD
    String password;    // The password required to dump() the state of the MUD
    String mudname;     // The name that this MUD is registered under
    Hashtable places;   // A mapping of place names to places in this MUD
    
    /**
     * Start a MUD from scratch, with the given name and password.  Create
     * an initial MudPlace object as the entrance, giving it the specified
     * name and description.
     **/
    public MudServer(String mudname, String password, 
		     String placename, String description)
	throws RemoteException
    {
        this.mudname = mudname;
        this.password = password;
        this.places = new Hashtable();
        // Create the entrance place
        try { this.entrance = new MudPlace(this, placename, description); } 
        catch (PlaceAlreadyExists e) {} // Should never happen
    }
    
    /** For serialization only.  Never call this constructor. */
    public MudServer() throws RemoteException {}                   
    
    /** This remote method returns the name of the MUD */
    public String getMudName() throws RemoteException { return mudname; }
    
    /** This remote method returns the entrance place of the MUD */
    public RemoteMudPlace getEntrance() throws RemoteException { 
        return entrance; 
    }
    
    /**
     * This remote method returns a RemoteMudPlace object for the named place.
     * In this sense, a MudServer acts as like an RMI Registry object,
     * returning remote objects looked up by name.  It is simpler to do it this
     * way than to use an actual Registry object.  If the named place does not
     * exist, it throws a NoSuchPlace exception
     **/
    public RemoteMudPlace getNamedPlace(String name) 
              throws RemoteException, NoSuchPlace
    {
        RemoteMudPlace p = (RemoteMudPlace) places.get(name);
        if (p == null) throw new NoSuchPlace();
        return p;
    }
    
    /**
     * Define a new placename to place mapping in our hashtable.  
     * This is not a remote method.  The MudPlace() constructor calls it
     * to register the new place it is creating.
     **/
    public void setPlaceName(RemoteMudPlace place, String name) 
	throws PlaceAlreadyExists
    {
        if (places.containsKey(name)) throw new PlaceAlreadyExists();
        places.put(name, place);
    }
    
    /**
     * This remote method serializes and compresses the state of the MUD
     * to a named file, if the specified password matches the one specified
     * when the MUD was initially created.  Note that the state of a MUD
     * consists of all places in the MUD, with all things and exits in those
     * places.  The people in the MUD are not part of the state that is saved.
     **/
    public void dump(String password, String f) 
	throws RemoteException, BadPassword, IOException
    {
        if ((this.password != null)&& !this.password.equals(password)) 
            throw new BadPassword();
        ObjectOutputStream out = new ObjectOutputStream(
			        new GZIPOutputStream(new FileOutputStream(f)));
        out.writeObject(this);
        out.close();
    }
    
    /**
     * This main() method defines the standalone program that starts up a MUD
     * server.  If invoked with a single argument, it treats that argument as
     * the name of a file containing the serialized and compressed state of an
     * existing MUD, and recreates it.  Otherwise, it expects four command-line
     * arguments: the name of the MUD, the password, the name of the entrance
     * place for the MUD, and a description of that entrance place.
     * Besides creating the MudServer object, this program sets an appropriate
     * security manager, and uses the default rmiregistry to register the
     * the MudServer under its given name.
     **/
    public static void main(String[] args) {
        try {
            MudServer server;
            if (args.length == 1) {
                // Read the MUD state in from a file
                FileInputStream f = new FileInputStream(args[0]);
                ObjectInputStream in =
		    new ObjectInputStream(new GZIPInputStream(f));
                server = (MudServer) in.readObject();
            }
            // Otherwise, create an initial MUD from scratch
            else server = new MudServer(args[0], args[1], args[2], args[3]);
	    
            Naming.rebind(Mud.mudPrefix + server.mudname, server);
        }
        // Display an error message if anything goes wrong.
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Usage: java MudServer <savefile>\n" +
			       "   or: java MudServer <mudname> <password> " + 
			       "<placename> <description>");
            System.exit(1);
        }
    }

    /** This constant is a version number for serialization */
    static final long serialVersionUID = 7453281245880199453L;
}
