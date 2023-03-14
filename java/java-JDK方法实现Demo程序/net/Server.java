/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.net;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class is a generic framework for a flexible, multi-threaded server.
 * It listens on any number of specified ports, and, when it receives a 
 * connection on a port, passes input and output streams to a specified Service
 * object which provides the actual service.  It can limit the number of
 * concurrent connections, and logs activity to a specified stream.
 **/
public class Server {
    /**
     * A main() method for running the server as a standalone program.  The
     * command-line arguments to the program should be pairs of servicenames
     * and port numbers.  For each pair, the program will dynamically load the
     * named Service class, instantiate it, and tell the server to provide
     * that Service on the specified port.  The special -control argument
     * should be followed by a password and port, and will start special
     * server control service running on the specified port, protected by the
     * specified password.
     **/
    public static void main(String[] args) {
        try {
            if (args.length < 2)  // Check number of arguments
                throw new IllegalArgumentException("Must specify a service");
            
            // Create a Server object that uses standard out as its log and
            // has a limit of ten concurrent connections at once.
            Server s = new Server(System.out, 10);

            // Parse the argument list
            int i = 0;
            while(i < args.length) {
                if (args[i].equals("-control")) {  // Handle the -control arg
                    i++;
                    String password = args[i++];
                    int port = Integer.parseInt(args[i++]);
		    // add control service
                    s.addService(new Control(s, password), port);
                } 
                else {
                    // Otherwise start a named service on the specified port.
                    // Dynamically load and instantiate a Service class
                    String serviceName = args[i++];
                    Class serviceClass = Class.forName(serviceName); 
                    Service service = (Service)serviceClass.newInstance();
                    int port = Integer.parseInt(args[i++]);
                    s.addService(service, port);
                }
            }
        }
        catch (Exception e) { // Display a message if anything goes wrong
            System.err.println("Server: " + e);
            System.err.println("Usage: java Server " +
			       "[-control <password> <port>] " +
			       "[<servicename> <port> ... ]");
            System.exit(1);
        }
    }

    // This is the state for the server
    Map services;                   // Hashtable mapping ports to Listeners
    Set connections;                // The set of current connections
    int maxConnections;             // The concurrent connection limit
    ThreadGroup threadGroup;        // The threadgroup for all our threads
    PrintWriter logStream;          // Where we send our logging output to

    /**
     * This is the Server() constructor.  It must be passed a stream 
     * to send log output to (may be null), and the limit on the number of
     * concurrent connections.  
     **/
    public Server(OutputStream logStream, int maxConnections) { 
        setLogStream(logStream);
        log("Starting server");
        threadGroup = new ThreadGroup(Server.class.getName());
	this.maxConnections = maxConnections;
        services = new HashMap();
	connections = new HashSet(maxConnections);
    }
    
    /** 
     * A public method to set the current logging stream.  Pass null
     * to turn logging off
     **/
    public synchronized void setLogStream(OutputStream out) {
        if (out != null) logStream = new PrintWriter(out);
        else logStream = null;
    }

    /** Write the specified string to the log */
    protected synchronized void log(String s) { 
        if (logStream != null) {
            logStream.println("[" + new Date() + "] " + s);
            logStream.flush();
        }
    }
    /** Write the specified object to the log */
    protected void log(Object o) { log(o.toString()); }
    
    /**
     * This method makes the server start providing a new service.
     * It runs the specified Service object on the specified port.
     **/
    public synchronized void addService(Service service, int port)
	throws IOException
    {
        Integer key = new Integer(port);  // the hashtable key
        // Check whether a service is already on that port
        if (services.get(key) != null) 
            throw new IllegalArgumentException("Port " + port +
					       " already in use.");
        // Create a Listener object to listen for connections on the port
        Listener listener = new Listener(threadGroup, port, service);
        // Store it in the hashtable
        services.put(key, listener);
        // Log it
        log("Starting service " + service.getClass().getName() + 
	    " on port " + port);
        // Start the listener running.
        listener.start();
    }
    
    /**
     * This method makes the server stop providing a service on a port.
     * It does not terminate any pending connections to that service, merely
     * causes the server to stop accepting new connections
     **/
    public synchronized void removeService(int port) {
        Integer key = new Integer(port);  // hashtable key
        // Look up the Listener object for the port in the hashtable
        final Listener listener = (Listener) services.get(key);
        if (listener == null) return;
        // Ask the listener to stop
        listener.pleaseStop();
        // Remove it from the hashtable
        services.remove(key);
        // And log it.
        log("Stopping service " + listener.service.getClass().getName() + 
	    " on port " + port);
    }
    
    /** 
     * This nested Thread subclass is a "listener".  It listens for
     * connections on a specified port (using a ServerSocket) and when it gets
     * a connection request, it calls the servers addConnection() method to
     * accept (or reject) the connection.  There is one Listener for each
     * Service being provided by the Server.
     **/
    public class Listener extends Thread {
        ServerSocket listen_socket;    // The socket to listen for connections
        int port;                      // The port we're listening on
        Service service;               // The service to provide on that port
        volatile boolean stop = false; // Whether we've been asked to stop

        /**
	 * The Listener constructor creates a thread for itself in the
	 * threadgroup.  It creates a ServerSocket to listen for connections
	 * on the specified port.  It arranges for the ServerSocket to be
	 * interruptible, so that services can be removed from the server.
	 **/
        public Listener(ThreadGroup group, int port, Service service) 
	    throws IOException
	{
            super(group, "Listener:" + port);      
            listen_socket = new ServerSocket(port);
            // give it a non-zero timeout so accept() can be interrupted
            listen_socket.setSoTimeout(600000);
            this.port = port;
            this.service = service;
        }

        /** 
	 * This is the polite way to get a Listener to stop accepting
	 * connections
	 ***/
        public void pleaseStop() {
            this.stop = true;              // Set the stop flag
            this.interrupt();              // Stop blocking in accept()
	    try { listen_socket.close(); } // Stop listening.
	    catch(IOException e) {}
        }
        
        /**
	 * A Listener is a Thread, and this is its body.
	 * Wait for connection requests, accept them, and pass the socket on
	 * to the addConnection method of the server.
	 **/
        public void run() {
            while(!stop) {      // loop until we're asked to stop.
                try {
                    Socket client = listen_socket.accept();
                    addConnection(client, service);
                } 
                catch (InterruptedIOException e) {} 
                catch (IOException e) {log(e);}
            }
        }
    }
	
    /**
     * This is the method that Listener objects call when they accept a
     * connection from a client.  It either creates a Connection object 
     * for the connection and adds it to the list of current connections,
     * or, if the limit on connections has been reached, it closes the 
     * connection. 
     **/
    protected synchronized void addConnection(Socket s, Service service) {
	// If the connection limit has been reached
	if (connections.size() >= maxConnections) {
	    try {
		// Then tell the client it is being rejected.
		PrintWriter out = new PrintWriter(s.getOutputStream());
		out.print("Connection refused; " +
			  "the server is busy; please try again later.\n");
		out.flush();
		// And close the connection to the rejected client.
		s.close();
		// And log it, of course
		log("Connection refused to " +
		    s.getInetAddress().getHostAddress() +
		    ":" + s.getPort() + ": max connections reached.");
	    } catch (IOException e) {log(e);}
	}
	else {  // Otherwise, if the limit has not been reached
	    // Create a Connection thread to handle this connection
	    Connection c = new Connection(s, service);
	    // Add it to the list of current connections
	    connections.add(c);
	    // Log this new connection
	    log("Connected to " + s.getInetAddress().getHostAddress() +
		":" + s.getPort() + " on port " + s.getLocalPort() +
		" for service " + service.getClass().getName());
	    // And start the Connection thread to provide the service
	    c.start();
	}
    }

    /**
     * A Connection thread calls this method just before it exits.  It removes
     * the specified Connection from the set of connections.
     **/
    protected synchronized void endConnection(Connection c) {
	connections.remove(c);
	log("Connection to " + c.client.getInetAddress().getHostAddress() +
	    ":" + c.client.getPort() + " closed.");
    }

    /** Change the current connection limit */
    public synchronized void setMaxConnections(int max) {
	maxConnections = max;
    }

    /**
     * This method displays status information about the server on the
     * specified stream.  It can be used for debugging, and is used by the
     * Control service later in this example.
     **/
    public synchronized void displayStatus(PrintWriter out) {
	// Display a list of all Services that are being provided
	Iterator keys = services.keySet().iterator();
	while(keys.hasNext()) {
	    Integer port = (Integer) keys.next();
	    Listener listener =	(Listener) services.get(port);
	    out.print("SERVICE " + listener.service.getClass().getName()
		      + " ON PORT " + port + "\n");
	}
	
	// Display the current connection limit
	out.print("MAX CONNECTIONS: " + maxConnections + "\n");

	// Display a list of all current connections
	Iterator conns = connections.iterator();
	while(conns.hasNext()) {
	    Connection c = (Connection)conns.next();
	    out.print("CONNECTED TO " +
		      c.client.getInetAddress().getHostAddress() +
		      ":" + c.client.getPort() + " ON PORT " +
		      c.client.getLocalPort() + " FOR SERVICE " +
		      c.service.getClass().getName() + "\n");
	}
    }

    /**
     * This class is a subclass of Thread that handles an individual
     * connection between a client and a Service provided by this server.
     * Because each such connection has a thread of its own, each Service can
     * have multiple connections pending at once.  Despite all the other
     * threads in use, this is the key feature that makes this a
     * multi-threaded server implementation.
     **/
    public class Connection extends Thread {
        Socket client;     // The socket to talk to the client through
        Service service;   // The service being provided to that client
	
        /**
	 * This constructor just saves some state and calls the superclass
	 * constructor to create a thread to handle the connection.  Connection
	 * objects are created by Listener threads.  These threads are part of
	 * the server's ThreadGroup, so all Connection threads are part of that
	 * group, too.
	 **/
        public Connection(Socket client, Service service) {
            super("Server.Connection:" +
		  client.getInetAddress().getHostAddress() +
		  ":" + client.getPort());
            this.client = client;
            this.service = service;
        }
	
        /**
	 * This is the body of each and every Connection thread.
	 * All it does is pass the client input and output streams to the
	 * serve() method of the specified Service object.  That method is
	 * responsible for reading from and writing to those streams to
	 * provide the actual service.  Recall that the Service object has
	 * been passed from the Server.addService() method to a Listener
	 * object to the addConnection() method to this Connection object, and
	 * is now finally being used to provide the service.  Note that just
	 * before this thread exits it always calls the endConnection() method
	 * to remove itself from the set of connections
	 **/
        public void run() {
            try { 
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
                service.serve(in, out);
            } 
            catch (IOException e) {log(e);}
            finally { endConnection(this); }
        }
    }
    
    /**
     * Here is the Service interface that we have seen so much of.  It defines
     * only a single method which is invoked to provide the service.  serve()
     * will be passed an input stream and an output stream to the client.  It
     * should do whatever it wants with them, and should close them before
     * returning.
     *
     * All connections through the same port to this service share a single
     * Service object.  Thus, any state local to an individual connection must
     * be stored in local variables within the serve() method.  State that
     * should be global to all connections on the same port should be stored
     * in instance variables of the Service class.  If the same Service is
     * running on more than one port, there will typically be different
     * Service instances for each port.  Data that should be global to all
     * connections on any port should be stored in static variables.
     *
     * Note that implementations of this interface must have a no-argument 
     * constructor if they are to be dynamically instantiated by the main()
     * method of the Server class.
     **/
    public interface Service {
        public void serve(InputStream in, OutputStream out) throws IOException;
    }

    /**
     * A very simple service.  It displays the current time on the server
     * to the client, and closes the connection.
     **/
    public static class Time implements Service {
        public void serve(InputStream i, OutputStream o) throws IOException {
            PrintWriter out = new PrintWriter(o);
            out.print(new Date() + "\n");
            out.close();
            i.close();
        }
    }
    
    /**
     * This is another example service.  It reads lines of input from the
     * client, and sends them back, reversed.  It also displays a welcome
     * message and instructions, and closes the connection when the user 
     * enters a '.' on a line by itself.
     **/
    public static class Reverse implements Service {
        public void serve(InputStream i, OutputStream o) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            PrintWriter out = 
                new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
            out.print("Welcome to the line reversal server.\n");
            out.print("Enter lines.  End with a '.' on a line by itself.\n");
            for(;;) {
                out.print("> ");
                out.flush();
                String line = in.readLine();
                if ((line == null) || line.equals(".")) break;
                for(int j = line.length()-1; j >= 0; j--)
                    out.print(line.charAt(j));
                out.print("\n");
            }
            out.close();
            in.close();
        }
    }
    
    /**
     * This service is an HTTP mirror, just like the HttpMirror class
     * implemented earlier in this chapter.  It echos back the client's
     * HTTP request
     **/
    public static class HTTPMirror implements Service {
        public void serve(InputStream i, OutputStream o) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            PrintWriter out = new PrintWriter(o);
            out.print("HTTP/1.0 200 \n");
            out.print("Content-Type: text/plain\n\n");
            String line;
            while((line = in.readLine()) != null) {
                if (line.length() == 0) break;
                out.print(line + "\n");
            }
            out.close();
            in.close();
        }
    }
    
    /**
     * This service demonstrates how to maintain state across connections by
     * saving it in instance variables and using synchronized access to those
     * variables.  It maintains a count of how many clients have connected and
     * tells each client what number it is
     **/
    public static class UniqueID implements Service {
        public int id=0;
        public synchronized int nextId() { return id++; }
        public void serve(InputStream i, OutputStream o) throws IOException {
            PrintWriter out = new PrintWriter(o);
            out.print("You are client #: " + nextId() + "\n");
            out.close();
            i.close();
        }
    }
    
    /**
     * This is a non-trivial service.  It implements a command-based protocol
     * that gives password-protected runtime control over the operation of the 
     * server.  See the main() method of the Server class to see how this
     * service is started.  
     *
     * The recognized commands are:
     *   password: give password; authorization is required for most commands
     *   add:      dynamically add a named service on a specified port
     *   remove:   dynamically remove the service running on a specified port
     *   max:      change the current maximum connection limit.
     *   status:   display current services, connections, and connection limit
     *   help:     display a help message
     *   quit:     disconnect
     *
     * This service displays a prompt, and sends all of its output to the user
     * in capital letters.  Only one client is allowed to connect to this 
     * service at a time.
     **/
    public static class Control implements Service {
        Server server;             // The server we control
        String password;           // The password we require
        boolean connected = false; // Whether a client is already connected
	
        /**
	 * Create a new Control service.  It will control the specified Server
	 * object, and will require the specified password for authorization
	 * Note that this Service does not have a no argument constructor,
	 * which means that it cannot be dynamically instantiated and added as
	 * the other, generic services above can be.
	 **/
        public Control(Server server, String password) {
            this.server = server;
            this.password = password;
        }

        /**
	 * This is the serve method that provides the service.  It reads a
	 * line the client, and uses java.util.StringTokenizer to parse it
	 * into commands and arguments.  It does various things depending on
	 * the command.
	 **/
        public void serve(InputStream i, OutputStream o) throws IOException {
            // Setup the streams
            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            PrintWriter out = new PrintWriter(o);
            String line;  // For reading client input lines
	    // Has the user has given the password yet?
            boolean authorized = false; 

            // If there is already a client connected to this service, display
            // a message to this client and close the connection.  We use a
            // synchronized block to prevent a race condition.
            synchronized(this) { 
                if (connected) { 
                    out.print("ONLY ONE CONTROL CONNECTION ALLOWED.\n");
                    out.close();
                    return;
                }
                else connected = true;
            }

	    // This is the main loop: read a command, parse it, and handle it
            for(;;) {  // infinite loop
                out.print("> ");           // Display a prompt
                out.flush();               // Make it appear right away
                line = in.readLine();      // Get the user's input
                if (line == null) break;   // Quit if we get EOF.
                try {
                    // Use a StringTokenizer to parse the user's command
                    StringTokenizer t = new StringTokenizer(line);
                    if (!t.hasMoreTokens()) continue;  // if input was empty
                    // Get first word of the input and convert to lower case
                    String command = t.nextToken().toLowerCase(); 
                    // Now compare to each of the possible commands, doing the
                    // appropriate thing for each command
                    if (command.equals("password")) {  // Password command
                        String p = t.nextToken();      // Get the next word
                        if (p.equals(this.password)) { // Is it the password?
                            out.print("OK\n");         // Say so
                            authorized = true;         // Grant authorization
                        }
                        else out.print("INVALID PASSWORD\n"); // Otherwise fail
                    }
                    else if (command.equals("add")) {  // Add Service command
                        // Check whether password has been given
                        if (!authorized) out.print("PASSWORD REQUIRED\n"); 
                        else {
                            // Get the name of the service and try to
                            // dynamically load and instantiate it.
                            // Exceptions will be handled below
                            String serviceName = t.nextToken();
                            Class serviceClass = Class.forName(serviceName);
                            Service service;
                            try {
				service = (Service)serviceClass.newInstance();
			    }
                            catch (NoSuchMethodError e) {
                                throw new IllegalArgumentException(
					        "Service must have a " +
						"no-argument constructor");
                            }
                            int port = Integer.parseInt(t.nextToken());
                            // If no exceptions occurred, add the service
                            server.addService(service, port);
                            out.print("SERVICE ADDED\n");    // acknowledge
                        }
                    }
                    else if (command.equals("remove")) { // Remove service
                        if (!authorized) out.print("PASSWORD REQUIRED\n");
                        else {
                            int port = Integer.parseInt(t.nextToken()); 
                            server.removeService(port); // remove the service
                            out.print("SERVICE REMOVED\n"); // acknowledge
                        }
                    }
                    else if (command.equals("max")) { // Set connection limit
                        if (!authorized) out.print("PASSWORD REQUIRED\n");
                        else {
                            int max = Integer.parseInt(t.nextToken()); 
                            server.setMaxConnections(max); 
                            out.print("MAX CONNECTIONS CHANGED\n");
                        }
                    }
                    else if (command.equals("status")) { // Status Display
                        if (!authorized) out.print("PASSWORD REQUIRED\n");
			else server.displayStatus(out);
                    }
                    else if (command.equals("help")) {  // Help command
                        // Display command syntax.  Password not required
                        out.print("COMMANDS:\n" + 
				  "\tpassword <password>\n" +
				  "\tadd <service> <port>\n" +
				  "\tremove <port>\n" +
				  "\tmax <max-connections>\n" +
				  "\tstatus\n" +
				  "\thelp\n" + 
				  "\tquit\n");
                    }
                    else if (command.equals("quit")) break; // Quit command.
                    else out.print("UNRECOGNIZED COMMAND\n"); // Error
		}
                catch (Exception e) {
                    // If an exception occurred during the command, print an
                    // error message, then output details of the exception.
                    out.print("ERROR WHILE PARSING OR EXECUTING COMMAND:\n" +
			      e + "\n");
                }
            }
            // Finally, when the loop command loop ends, close the streams
            // and set our connected flag to false so that other clients can
            // now connect.
            connected = false;
            out.close();
            in.close();
        }    
    }
}
