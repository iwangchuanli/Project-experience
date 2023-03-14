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
import java.util.*;
import com.davidflanagan.examples.rmi.Mud.*;

/**
 * This class is a client program for the MUD.  The main() method sets up 
 * a connection to a RemoteMudServer, gets the initial RemoteMudPlace object,
 * and creates a MudPerson object to represent the user in the MUD.  Then it 
 * calls runMud() to put the person in the place, begins processing
 * user commands.  The getLine() and getMultiLine() methods are convenience
 * methods used throughout to get input from the user.
 **/
public class MudClient {
    /**
     * The main program.  It expects two or three arguments:
     *   0) the name of the host on which the mud server is running
     *   1) the name of the MUD on that host
     *   2) the name of a place within that MUD to start at (optional).
     *
     * It uses the Naming.lookup() method to obtain a RemoteMudServer object
     * for the named MUD on the specified host.  Then it uses the getEntrance()
     * or getNamedPlace() method of RemoteMudServer to obtain the starting
     * RemoteMudPlace object.  It prompts the user for a their name and 
     * description, and creates a MudPerson object.  Finally, it passes
     * the person and the place to runMud() to begin interaction with the MUD.
     **/
    public static void main(String[] args) {
        try {
            String hostname = args[0]; // Each MUD is uniquely identified by a 
            String mudname = args[1];  //   host and a MUD name.
            String placename = null;   // Each place in a MUD has a unique name
            if (args.length > 2) placename = args[2];
	    
            // Look up the RemoteMudServer object for the named MUD using
            // the default registry on the specified host.  Note the use of
            // the Mud.mudPrefix constant to help prevent naming conflicts
            // in the registry.
            RemoteMudServer server = 
                (RemoteMudServer)Naming.lookup("rmi://" + hostname + "/" +
					       Mud.mudPrefix + mudname);

            // If the user did not specify a place in the mud, use
            // getEntrance() to get the initial place.  Otherwise, call
            // getNamedPlace() to find the initial place.
            RemoteMudPlace location = null;
            if (placename == null) location = server.getEntrance();
            else location = (RemoteMudPlace) server.getNamedPlace(placename);
	    
            // Greet the user and ask for their name and description.
            // This relies on getLine() and getMultiLine() defined below.
            System.out.println("Welcome to " + mudname);
            String name = getLine("Enter your name: ");
            String description = getMultiLine("Please describe what " +
					  "people see when they look at you:");

            // Define an output stream that the MudPerson object will use to
            // display messages sent to it to the user.  We'll use the console.
            PrintWriter myout = new PrintWriter(System.out);
	    
            // Create a MudPerson object to represent the user in the MUD.
            // Use the specified name and description, and the output stream.
            MudPerson me = new MudPerson(name, description, myout);
	    
            // Lower this thread's priority one notch so that broadcast
            // messages can appear even when we're blocking for I/O.  This is
            // necessary on the Linux platform, but may not be necessary on all
            // platforms.
            int pri = Thread.currentThread().getPriority();
            Thread.currentThread().setPriority(pri-1);
	    
            // Finally, put the MudPerson into the RemoteMudPlace, and start
            // prompting the user for commands.
            runMud(location, me);
        }
        // If anything goes wrong, print a message and exit.
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Usage: java MudClient <host> <mud> [<place>]");
            System.exit(1);
        }
    }

    /**
     * This method is the main loop of the MudClient.  It places the person
     * into the place (using the enter() method of RemoteMudPlace).  Then it
     * calls the look() method to describe the place to the user, and enters a
     * command loop to prompt the user for a command and process the command
     **/
    public static void runMud(RemoteMudPlace entrance, MudPerson me) 
	throws RemoteException
    {
        RemoteMudPlace location = entrance;  // The current place
        String myname = me.getName();        // The person's name
        String placename = null;             // The name of the current place
        String mudname = null;             // The name of the mud of that place

        try { 
            // Enter the MUD
            location.enter(me, myname, myname + " has entered the MUD."); 
            // Figure out where we are (for the prompt)
            mudname = location.getServer().getMudName();
            placename = location.getPlaceName();
            // Describe the place to the user
            look(location);
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
	
        // Now that we've entered the MUD, begin a command loop to process
        // the user's commands.  Note that there is a huge block of catch
        // statements at the bottom of the loop to handle all the things that
        // could go wrong each time through the loop.
        for(;;) {  // Loop until the user types "quit"
            try {    // Catch any exceptions that occur in the loop
                // Pause just a bit before printing the prompt, to give output
                // generated indirectly by the last command a chance to appear.
                try { Thread.sleep(200); } catch (InterruptedException e) {}

                // Display a prompt, and get the user's input
                String line = getLine(mudname + '.' + placename + "> ");
		
                // Break the input into a command and an argument that consists
                // of the rest of the line.  Convert the command to lowercase.
                String cmd, arg;
                int i = line.indexOf(' ');
                if (i == -1) { cmd = line; arg = null; }
                else {
                    cmd = line.substring(0, i).toLowerCase();
                    arg = line.substring(i+1);
                }
                if (arg == null) arg = "";
		
                // Now go process the command.  What follows is a huge repeated
                // if/else statement covering each of the commands supported by
                // this client.  Many of these commands simply invoke one of
                // the remote methods of the current RemoteMudPlace object.
                // Some have to do a bit of additional processing.

                // LOOK: Describe the place and its things, people, and exits
                if (cmd.equals("look")) look(location);
                // EXAMINE: Describe a named thing
                else if (cmd.equals("examine")) 
                    System.out.println(location.examineThing(arg));
                // DESCRIBE: Describe a named person
                else if (cmd.equals("describe")) {
                    try { 
                        RemoteMudPerson p = location.getPerson(arg);
                        System.out.println(p.getDescription()); 
                    }
                    catch(RemoteException e) {
                        System.out.println(arg + " is having technical " +
					   "difficulties. No description " +
					   "is available.");
                    }
                }
                // GO: Go in a named direction
                else if (cmd.equals("go")) {
                    location = location.go(me, arg);
                    mudname = location.getServer().getMudName();
                    placename = location.getPlaceName();
                    look(location);
                }
                // SAY: Say something to everyone 
                else if (cmd.equals("say")) location.speak(me, arg);
                // DO: Do something that will be described to everyone
                else if (cmd.equals("do")) location.act(me, arg);
                // TALK: Say something to one named person
                else if (cmd.equals("talk")) {
                    try {
                        RemoteMudPerson p = location.getPerson(arg);
                        String msg = getLine("What do you want to say?: ");
                        p.tell(myname + " says \"" + msg + "\"");
                    }
                    catch (RemoteException e) {
                        System.out.println(arg + " is having technical " +
			   	         "difficulties. Can't talk to them.");
                    }
                }
                // CHANGE: Change my own description 
                else if (cmd.equals("change"))
                    me.setDescription(
			    getMultiLine("Describe yourself for others: "));
                // CREATE: Create a new thing in this place
                else if (cmd.equals("create")) {
                    if (arg.length() == 0)
                        throw new IllegalArgumentException("name expected");
                    String desc = getMultiLine("Please describe the " +
					       arg + ": ");
                    location.createThing(me, arg, desc);
                }
                // DESTROY: Destroy a named thing
                else if (cmd.equals("destroy")) location.destroyThing(me, arg);
                // OPEN: Create a new place and connect this place to it
                // through the exit specified in the argument.
                else if (cmd.equals("open")) {
                    if (arg.length() == 0) 
                      throw new IllegalArgumentException("direction expected");
                    String name = getLine("What is the name of place there?: ");
                    String back = getLine("What is the direction from " + 
					  "there back to here?: ");
                    String desc = getMultiLine("Please describe " +
					       name + ":");
                    location.createPlace(me, arg, back, name, desc);
                }
                // CLOSE: Close a named exit.  Note: only closes an exit
                // uni-directionally, and does not destroy a place.
                else if (cmd.equals("close")) {
                    if (arg.length() == 0) 
                      throw new IllegalArgumentException("direction expected");
                    location.close(me, arg);
                }
                // LINK: Create a new exit that connects to an existing place
                // that may be in another MUD running on another host
                else if (cmd.equals("link")) {
                    if (arg.length() == 0) 
                      throw new IllegalArgumentException("direction expected");
                    String host = getLine("What host are you linking to?: ");
                    String mud =
			getLine("What is the name of the MUD on that host?: ");
                    String place =
			getLine("What is the place name in that MUD?: ");
                    location.linkTo(me, arg, host, mud, place);
                    System.out.println("Don't forget to make a link from " +
				       "there back to here!");
                }
                // DUMP: Save the state of this MUD into the named file,
                // if the password is correct
                else if (cmd.equals("dump")) {
                    if (arg.length() == 0) 
                       throw new IllegalArgumentException("filename expected");
                    String password = getLine("Password: ");
                    location.getServer().dump(password, arg);
                }
                // QUIT: Quit the game
                else if (cmd.equals("quit")) {
                    try { location.exit(me, myname + " has quit."); } 
                    catch (Exception e) {}
                    System.out.println("Bye.");
                    System.out.flush();
                    System.exit(0);
                }
                // HELP: Print out a big help message
                else if (cmd.equals("help")) System.out.println(help);
                // Otherwise, this is an unrecognized command.
                else System.out.println("Unknown command.  Try 'help'.");
            }
            // Handle the many possible types of MudException
            catch (MudException e) {
                if (e instanceof NoSuchThing) 
                    System.out.println("There isn't any such thing here."); 
                else if (e instanceof NoSuchPerson) 
                   System.out.println("There isn't anyone by that name here.");
                else if (e instanceof NoSuchExit) 
                  System.out.println("There isn't an exit in that direction.");
                else if (e instanceof NoSuchPlace) 
                    System.out.println("There isn't any such place."); 
                else if (e instanceof ExitAlreadyExists)
                    System.out.println("There is already an exit " +
				       "in that direction.");
                else if (e instanceof PlaceAlreadyExists)
                    System.out.println("There is already a place " +
				       "with that name.");
                else if (e instanceof LinkFailed)
                    System.out.println("That exit is not functioning.");
                else if (e instanceof BadPassword) 
                    System.out.println("Invalid password."); 
                else if (e instanceof NotThere)      // Shouldn't happen
                    System.out.println("You can't do that when " +
				       "you're not there."); 
                else if (e instanceof AlreadyThere)  // Shouldn't happen
                    System.out.println("You can't go there; " +
				       "you're already there.");
            }
            // Handle RMI exceptions
            catch (RemoteException e) {
               System.out.println("The MUD is having technical difficulties.");
               System.out.println("Perhaps the server has crashed:");
               System.out.println(e);
            }
            // Handle everything else that could go wrong.
            catch (Exception e) {
                System.out.println("Syntax or other error:");
                System.out.println(e);
                System.out.println("Try using the 'help' command.");
            }
        }
    }
    
    /** 
     * This convenience method is used in several places in the
     * runMud() method above.  It displays the name and description of
     * the current place (including the name of the mud the place is in), 
     * and also displays the list of things, people, and exits in
     * the current place.
     **/
    public static void look(RemoteMudPlace p) 
	throws RemoteException, MudException
    {
        String mudname = p.getServer().getMudName(); // Mud name
        String placename = p.getPlaceName();         // Place name
        String description = p.getDescription();     // Place description
        Vector things = p.getThings();               // List of things here
        Vector names = p.getNames();                 // List of people here
        Vector exits = p.getExits();                 // List of exits from here

        // Print it all out
        System.out.println("You are in: " + placename +
			   " of the Mud: " + mudname);
        System.out.println(description);
        System.out.print("Things here: ");
        for(int i = 0; i < things.size(); i++) {      // Display list of things
            if (i > 0) System.out.print(", ");
            System.out.print(things.elementAt(i));
        }
        System.out.print("\nPeople here: ");
        for(int i = 0; i < names.size(); i++) {       // Display list of people
            if (i > 0) System.out.print(", ");
            System.out.print(names.elementAt(i));
        }
        System.out.print("\nExits are: ");
        for(int i = 0; i < exits.size(); i++) {       // Display list of exits
            if (i > 0) System.out.print(", ");
            System.out.print(exits.elementAt(i));
        }
        System.out.println();                         // Blank line
        System.out.flush();                           // Make it appear now!
    }
    
    /** This static input stream reads lines from the console */
    static BufferedReader in =
	new BufferedReader(new InputStreamReader(System.in));
    
    /** 
     * A convenience method for prompting the user and getting a line of 
     * input.  It guarantees that the line is not empty and strips off 
     * whitespace at the beginning and end of the line.
     **/
    public static String getLine(String prompt) {
        String line = null;
        do {                      // Loop until a non-empty line is entered
            try {
                System.out.print(prompt);             // Display prompt
                System.out.flush();                   // Display it right away
                line = in.readLine();                 // Get a line of input
                if (line != null) line = line.trim(); // Strip off whitespace
            } catch (Exception e) {}                // Ignore any errors
        } while((line == null) || (line.length() == 0));
        return line;
    }
    
    /**
     * A convenience method for getting multi-line input from the user.
     * It prompts for the input, displays instructions, and guarantees that
     * the input is not empty.  It also allows the user to enter the name of
     * a file from which text will be read.
     **/
    public static String getMultiLine(String prompt) {
        String text = "";
        for(;;) {  // We'll break out of this loop when we get non-empty input
            try {
                BufferedReader br = in;       // The stream to read from 
                System.out.println(prompt);   // Display the prompt
                // Display some instructions
                System.out.println("You can enter multiple lines.  " + 
				   "End with a '.' on a line by itself.\n" +
				   "Or enter a '<<' followed by a filename");
                // Make the prompt and instructions appear now.
                System.out.flush();
                // Read lines
                String line;
                while((line = br.readLine()) != null) {    // Until EOF
                    if (line.equals(".")) break;  // Or until a dot by itself
                    // Or, if a file is specified, start reading from it 
                    // instead of from the console.
                    if (line.trim().startsWith("<<")) {      
                        String filename = line.trim().substring(2).trim();
                        br = new BufferedReader(new FileReader(filename));
                        continue;  // Don't count the << as part of the input
                    }
		    // Add the line to the collected input
                    else text += line + "\n";  
                }
                // If we got at least one line, return it.  Otherwise, chastise
                // the user and go back to the prompt and the instructions.
                if (text.length() > 0) return text;
                else System.out.println("Please enter at least one line.");
            }
            // If there were errors, for example an IO error reading a file,
            // display the error and loop again, displaying prompt and
            // instructions
            catch(Exception e) { System.out.println(e); }
        }
    }

    /** This is the usage string that explains the available commands */
    static final String help = 
	"Commands are:\n" + 
	"look: Look around\n" +
	"examine <thing>: examine the named thing in more detail\n" +
	"describe <person>: describe the named person\n" +
	"go <direction>: go in the named direction (i.e. a named exit)\n" +
	"say <message>: say something to everyone\n" +
	"do <message>: tell everyone that you are doing something\n" +
	"talk <person>: talk to one person.  Will prompt for message\n" +
	"change: change how you are described.  Will prompt for input\n" +
	"create <thing>: create a new thing.  Prompts for description \n" +
	"destroy <thing>: destroy a thing.\n" + 
	"open <direction>: create an adjoining place. Prompts for input\n"+
	"close <direction>: close an exit from this place.\n" +
	"link <direction>: create an exit to an existing place,\n" +
	"     perhaps on another server.  Will prompt for input.\n" +
	"dump <filename>: save server state.  Prompts for password\n" +
	"quit: leave the Mud\n" +
	"help: display this message";
}
