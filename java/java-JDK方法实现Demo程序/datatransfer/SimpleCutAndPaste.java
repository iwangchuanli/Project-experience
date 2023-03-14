/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.datatransfer;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

/**
 * This program demonstrates how to add simple copy-and-paste capabilities
 * to an application.
 **/
public class SimpleCutAndPaste extends Frame implements ClipboardOwner
{
    /** The main method creates a frame and pops it up. */
    public static void main(String[] args) {
	Frame f = new SimpleCutAndPaste();
	f.addWindowListener(new WindowAdapter() { 
		public void windowClosing(WindowEvent e) { System.exit(0); }
	    });
	f.pack();
	f.setVisible(true);
    }
    
    /** The text field that holds the text that is cut or pasted */
    TextField field;
    
    /**
     * The constructor builds a very simple test GUI, and registers this object
     * as the ActionListener for the buttons 
     **/
    public SimpleCutAndPaste() {
	super("SimpleCutAndPaste");  // Window title
	this.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Use a big font
	
	// Set up the Cut button
	Button copy = new Button("Copy");                       
	copy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { copy(); }
	    });
	this.add(copy, "West");
	
	// Set up the Paste button
	Button paste = new Button("Paste");
	paste.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { paste(); }
	    });
	this.add(paste, "East");
	
	// Set up the text field that they both operate on
	field = new TextField();
	this.add(field, "North");
    }
    
    /**
     * This method takes the current contents of the text field, creates a
     * StringSelection object to represent that string, and puts the 
     * StringSelection onto the clipboard
     **/
    public void copy() {
	// Get the currently displayed value
	String s = field.getText();                  
	
	// Create a StringSelection object to represent the text.
	// StringSelection is a pre-defined class that implements
	// Transferable and ClipboardOwner for us. 
	StringSelection ss = new StringSelection(s); 
	
	// Now set the StringSelection object as the contents of the clipboard
	// Also specify that we're the clipboard owner
	this.getToolkit().getSystemClipboard().setContents(ss, this);

	// Highlight the text to indicate it is on the clipboard.
	field.selectAll();
    }
    
    /**
     * Get the contents of the clipboard, and, if we understand the type,
     * display the contents.  This method understands strings and file lists.
     **/
    public void paste() {
	// Get the clipboard
	Clipboard c = this.getToolkit().getSystemClipboard();
	
	// Get the contents of the clipboard, as a Transferable object
	Transferable t = c.getContents(this);

	// Find out what kind of data is on the clipboard
	try { 
	    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		// If it is a string, then get and display the string
		String s = (String) t.getTransferData(DataFlavor.stringFlavor);
		field.setText(s); 
		
	    }
	    else if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
		// If it is a list of File objects, get the list and display
		// the name of the first file on the list
		java.util.List files = (java.util.List)
		    t.getTransferData(DataFlavor.javaFileListFlavor);
		java.io.File file = (java.io.File)files.get(0);
		field.setText(file.getName());
	    }
	}
	// If anything goes wrong with the transfer, just beep and do nothing.
	catch (Exception e) { this.getToolkit().beep(); }
    }

    /**
     * This method implements the ClipboardOwner interface.  It is called when
     * something else is placed on the clipboard.
     **/
    public void lostOwnership(Clipboard c, Transferable t) {
	// Un-highlight the text field, since we don't "own" the clipboard
	// anymore, and the text is no longer available to be pasted.
	field.select(0,0);
    }
}
