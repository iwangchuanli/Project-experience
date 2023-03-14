/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.gui;
import java.awt.*;                 // LayoutManager stuff
import javax.swing.*;              // Swing components
import java.awt.event.*;           // AWT event handlers
import javax.swing.event.*;        // Swing event handlers
import java.beans.*;               // JavaBeans event handlers
import java.awt.print.*;           // Printing functionality
import java.io.*;                  // Input/output
import java.net.*;                 // Networking with URLs
import java.util.*;                // Hashtables and other utilities
// Import this class by name.  JFileChooser uses it, and its name conflicts
// with java.io.FileFilter
import javax.swing.filechooser.FileFilter;  
// Import a class for printing Swing documents.  See printing chapter.
import com.davidflanagan.examples.print.PrintableDocument;

/**
 * This class implements a simple web browser using the HTML
 * display capabilities of the JEditorPane component.
 **/
public class WebBrowser extends JFrame
    implements HyperlinkListener, PropertyChangeListener
{
    /**
     * A simple main() method that allows the WebBrowser class to be used
     * as a stand-alone application.
     **/
    public static void main(String[] args) throws IOException {
	// End the program when there are no more open browser windows
	WebBrowser.setExitWhenLastWindowClosed(true);
	WebBrowser browser = new WebBrowser();  // Create a browser window
	browser.setSize(800, 600);              // Set its size
	browser.setVisible(true);               // Make it visible.

	// Tell the browser what to display.  This method is defined below.
	browser.displayPage((args.length > 0) ? args[0] : browser.getHome());
    }
    
    // This class uses GUIResourceBundle to create its menubar and toolbar
    // This static initializer performs one-time registration of the
    // required ResourceParser classes.
    static {
	GUIResourceBundle.registerResourceParser(new MenuBarParser());
	GUIResourceBundle.registerResourceParser(new MenuParser());
	GUIResourceBundle.registerResourceParser(new ActionParser());
	GUIResourceBundle.registerResourceParser(new CommandParser());
	GUIResourceBundle.registerResourceParser(new ToolBarParser());
    }

    // These are the Swing components that the browser uses
    JEditorPane textPane;      // Where the HTML is displayed
    JLabel messageLine;        // Displays one-line messages
    JTextField urlField;       // Displays and edits the current URL
    JFileChooser fileChooser;  // Allows the user to select a local file

    // These are Actions that are used in the menubar and toolbar.
    // We obtain explicit references to them from the GUIResourceBundle
    // so we can enable and disable them.
    Action backAction, forwardAction;

    // These fields are used to maintain the browsing history of the window
    java.util.List history = new ArrayList();  // The history list
    int currentHistoryPage = -1;               // Current location in it
    public static final int MAX_HISTORY = 50;  // Trim list when over this size

    // These static fields control the behavior of the close() action
    static int numBrowserWindows = 0;
    static boolean exitWhenLastWindowClosed = false;

    // This is where the "home()" method takes us.  See also setHome()
    String home = "http://www.davidflanagan.com";  // A default value

    /** Create and initialize a new WebBrowser window */
    public WebBrowser() {
	super();                          // Chain to JFrame constructor

	textPane = new JEditorPane();     // Create HTML window
	textPane.setEditable(false);      // Don't allow the user to edit it

	// Register action listeners.  The first is to handle hyperlinks.
	// The second is to receive property change notifications, which tell
	// us when a document is done loading.  This class implements these
	// EventListener interfaces, and the methods are defined below
	textPane.addHyperlinkListener(this); 
	textPane.addPropertyChangeListener(this);

	// Put the text pane in a JScrollPane in the center of the window
	this.getContentPane().add(new JScrollPane(textPane),
				  BorderLayout.CENTER);

	// Now create a message line and place it at the bottom of the window
	messageLine = new JLabel(" ");
	this.getContentPane().add(messageLine, BorderLayout.SOUTH);

	// Read the file WebBrowserResources.properties (and any localized
	// variants appropriate for the current Locale) to create a
	// GUIResourceBundle from which we'll get our menubar and toolbar.
	GUIResourceBundle resources =
	    new GUIResourceBundle(this,"com.davidflanagan.examples.gui." +
				  "WebBrowserResources");

	// Read a menubar from the resource bundle and display it
	JMenuBar menubar = (JMenuBar) resources.getResource("menubar",
							    JMenuBar.class);
	this.setJMenuBar(menubar);

	// Read a toolbar from the resource bundle.  Don't display it yet.
	JToolBar toolbar = 
	    (JToolBar) resources.getResource("toolbar", JToolBar.class);

	// Create a text field that the user can enter a URL in.
	// Set up an action listener to respond to the ENTER key in that field
	urlField = new JTextField();
	urlField.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    displayPage(urlField.getText());
		}
	    });

	// Add the URL field and a label for it to the end of the toolbar
	toolbar.add(new JLabel("         URL:"));
	toolbar.add(urlField);

	// And add the toolbar to the top of the window
	this.getContentPane().add(toolbar, BorderLayout.NORTH);

	// Read cached copies of two Action objects from the resource bundle
	// These actions are used by the menubar and toolbar, and enabling and
	// disabling them enables and disables the menu and toolbar items.
	backAction = (Action)resources.getResource("action.back",Action.class);
	forwardAction =
	    (Action)resources.getResource("action.forward", Action.class);

	// Start off with both actions disabled
	backAction.setEnabled(false);
	forwardAction.setEnabled(false);
	
	// Create a ThemeManager for this frame, 
	// and add a Theme menu to the menubar
	ThemeManager themes = new ThemeManager(this, resources);
	menubar.add(themes.getThemeMenu());

	// Keep track of how many web browser windows are open
	WebBrowser.numBrowserWindows++;
    }

    /** Set the static property that controls the behavior of close() */
    public static void setExitWhenLastWindowClosed(boolean b) {
	exitWhenLastWindowClosed = b;
    }

    /** These are accessor methods for the home property. */
    public void setHome(String home) { this.home = home; }
    public String getHome() { return home; }

    /**
     * This internal method attempts to load and display the specified URL.
     * It is called from various places throughout the class.
     **/
    boolean visit(URL url) {
	try {
	    String href = url.toString();
	    // Start animating.  Animation is stopped in propertyChanged()
	    startAnimation("Loading " + href + "...");  
	    textPane.setPage(url);   // Load and display the URL 
	    this.setTitle(href);     // Display URL in window titlebar
	    urlField.setText(href);  // Display URL in text input field
	    return true;             // Return success
	}
	catch (IOException ex) {     // If page loading fails
	    stopAnimation();
	    messageLine.setText("Can't load page: " + ex.getMessage());
	    return false;            // Return failure
	}
    }

    /**
     * Ask the browser to display the specified URL, and put it in the
     * history list.
     **/
    public void displayPage(URL url) {
	if (visit(url)) {    // go to the specified url, and if we succeed:
	    history.add(url);       // Add the url to the history list
	    int numentries = history.size();
	    if (numentries > MAX_HISTORY+10) {  // Trim history when too large
		history = history.subList(numentries-MAX_HISTORY, numentries);
		numentries = MAX_HISTORY;
	    }
	    currentHistoryPage = numentries-1;  // Set current history page
	    // If we can go back, then enable the Back action
	    if (currentHistoryPage > 0) backAction.setEnabled(true);
	}
    }

    /** Like displayPage(URL), but takes a string instead */
    public void displayPage(String href) {
	try {
	    displayPage(new URL(href));
	}
	catch (MalformedURLException ex) {
	    messageLine.setText("Bad URL: " + href);
	}
    }

    /** Allow the user to choose a local file, and display it */
    public void openPage() {
	// Lazy creation: don't create the JFileChooser until it is needed
	if (fileChooser == null) {
	    fileChooser = new JFileChooser();
	    // This javax.swing.filechooser.FileFilter displays only HTML files
	    FileFilter filter = new FileFilter() {
		    public boolean accept(File f) {
			String fn = f.getName();
			if (fn.endsWith(".html") || fn.endsWith(".htm"))
			    return true;
			else return false;
		    }
		    public String getDescription() { return "HTML Files"; }
		};
	    fileChooser.setFileFilter(filter);
	    fileChooser.addChoosableFileFilter(filter);
	}

	// Ask the user to choose a file.
	int result = fileChooser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
	    // If they didn't click "Cancel", then try to display the file.
	    File selectedFile = fileChooser.getSelectedFile();
	    String url = "file://" + selectedFile.getAbsolutePath();
	    displayPage(url);
	}
    }

    /** Go back to the previously displayed page. */
    public void back() {
	if (currentHistoryPage > 0)  // go back, if we can
	    visit((URL)history.get(--currentHistoryPage));
	// Enable or disable actions as appropriate
	backAction.setEnabled((currentHistoryPage > 0));
	forwardAction.setEnabled((currentHistoryPage < history.size()-1));
    }

    /** Go forward to the next page in the history list */
    public void forward() {
	if (currentHistoryPage < history.size()-1)  // go forward, if we can
	    visit((URL)history.get(++currentHistoryPage));
	// Enable or disable actions as appropriate
	backAction.setEnabled((currentHistoryPage > 0));
	forwardAction.setEnabled((currentHistoryPage < history.size()-1));
    }

    /** Reload the current page in the history list */
    public void reload() {
	if (currentHistoryPage != -1)
	    visit((URL)history.get(currentHistoryPage));
    }

    /** Display the page specified by the "home" property */
    public void home() { displayPage(getHome()); }

    /** Open a new browser window */
    public void newBrowser() {
	WebBrowser b = new WebBrowser();
	b.setSize(this.getWidth(), this.getHeight());
	b.setVisible(true);
    }

    /**
     * Close this browser window.  If this was the only open window,
     * and exitWhenLastBrowserClosed is true, then exit the VM
     **/
    public void close() {
	this.setVisible(false);             // Hide the window
	this.dispose();                     // Destroy the window
	synchronized(WebBrowser.class) {    // Synchronize for thread-safety
	    WebBrowser.numBrowserWindows--; // There is one window fewer now
	    if ((numBrowserWindows==0) && exitWhenLastWindowClosed)
		System.exit(0);             // Exit if it was the last one
	}
    }
       
    /**
     * Exit the VM.  If confirm is true, ask the user if they are sure.
     * Note that showConfirmDialog() displays a dialog, waits for the user,
     * and returns the user's response (i.e. the button the user selected).
     **/
    public void exit(boolean confirm) {
	if (!confirm ||
	    (JOptionPane.showConfirmDialog(this,  // dialog parent
	         /* message to display */  "Are you sure you want to quit?",
		 /* dialog title */	   "Really Quit?",
		 /* dialog buttons */	   JOptionPane.YES_NO_OPTION) == 
	     JOptionPane.YES_OPTION))  // If Yes button was clicked
	    System.exit(0);
    }

    /**
     * Print the contents of the text pane using the java.awt.print API
     * Note that this API does not work efficiently in Java 1.2
     * All the hard work is done by the PrintableDocument class.
     **/
    public void print() {
	// Get a PrinterJob object from the system
	PrinterJob job = PrinterJob.getPrinterJob();
	// This is the object that we are going to print
	PrintableDocument pd = new PrintableDocument(textPane);
	// Tell the PrinterJob what we want to print
	job.setPageable(pd);
	// Display a print dialog, asking the user what pages to print, what
	// printer to print to, and giving the user a chance to cancel.
	if (job.printDialog()) {  // If the user did not cancel
	    try { job.print(); }  // Start printing!
	    catch(PrinterException ex) {  // display errors nicely
		messageLine.setText("Couldn't print: " + ex.getMessage());
	    }
	}
    }

    /** 
     * This method implements HyperlinkListener.  It is invoked when the user
     * clicks on a hyperlink, or move the mouse onto or off of a link
     **/
    public void hyperlinkUpdate(HyperlinkEvent e) {
	HyperlinkEvent.EventType type = e.getEventType();  // what happened?
	if (type == HyperlinkEvent.EventType.ACTIVATED) {     // Click!
	    displayPage(e.getURL());   // Follow the link; display new page
	}
	else if (type == HyperlinkEvent.EventType.ENTERED) {  // Mouse over!
	    // When mouse goes over a link, display it in the message line
	    messageLine.setText(e.getURL().toString());  
	}
	else if (type == HyperlinkEvent.EventType.EXITED) {   // Mouse out!
	    messageLine.setText(" ");  // Clear the message line
	}
    }

    /**
     * This method implements java.beans.PropertyChangeListener.  It is 
     * invoked whenever a bound property changes in the JEditorPane object.
     * The property we are interested in is the "page" property, because it
     * tells us when a page has finished loading.
     **/
    public void propertyChange(PropertyChangeEvent e) {
	if (e.getPropertyName().equals("page")) // If the page property changed
	    stopAnimation();              // Then stop the loading... animation
    }

    /**
     * The fields and methods below implement a simple animation in the
     * web browser message line; they are used to provide user feedback
     * while web pages are loading.
     **/
    String animationMessage;  // The "loading..." message to display
    int animationFrame = 0;   // What "frame" of the animation are we on
    String[] animationFrames = new String[] {  // The content of each "frame"
	"-", "\\", "|", "/", "-", "\\", "|", "/", 
	",", ".", "o", "0", "O", "#", "*", "+"
    };

    /** This object calls the animate() method 8 times a second */
    javax.swing.Timer animator =
	new javax.swing.Timer(125, new ActionListener() {
		public void actionPerformed(ActionEvent e) { animate(); }
	    });

    /** Display the next frame. Called by the animator timer */
    void animate() {
	String frame = animationFrames[animationFrame++];    // Get next frame
	messageLine.setText(animationMessage + " " + frame); // Update msgline
	animationFrame = animationFrame % animationFrames.length;
    }

    /** Start the animation.  Called by the visit() method. */
    void startAnimation(String msg) {
	animationMessage = msg;     // Save the message to display
	animationFrame = 0;         // Start with frame 0 of the animation
	animator.start();           // Tell the timer to start firing.
    }

    /** Stop the animation.  Called by propertyChanged() method. */
    void stopAnimation() {       
	animator.stop();            // Tell the timer to stop firing events
	messageLine.setText(" ");   // Clear the message line
    }
}
