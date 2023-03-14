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
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This class creates and displays a window containing a TextArea, 
 * in which the contents of a text file are displayed.
 **/
public class FileViewer extends Frame implements ActionListener {
    String directory;  // The default directory to display in the FileDialog
    TextArea textarea; // The area to display the file contents into 
    
    /** Convenience constructor: file viewer starts out blank */
    public FileViewer() { this(null, null); }
    /** Convenience constructor: display file from current directory */
    public FileViewer(String filename) { this(null, filename); }
    
    /**
     * The real constructor.  Create a FileViewer object to display the
     * specified file from the specified directory 
     **/
    public FileViewer(String directory, String filename) {
        super();  // Create the frame
        
	// Destroy the window when the user requests it
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) { dispose(); }
	    });

        // Create a TextArea to display the contents of the file in
        textarea = new TextArea("", 24, 80);
        textarea.setFont(new Font("MonoSpaced", Font.PLAIN, 12));
        textarea.setEditable(false);
        this.add("Center", textarea);
        
        // Create a bottom panel to hold a couple of buttons in
        Panel p = new Panel();
        p.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        this.add(p, "South");
	
        // Create the buttons and arrange to handle button clicks
        Font font = new Font("SansSerif", Font.BOLD, 14);
        Button openfile = new Button("Open File");
        Button close = new Button("Close");
        openfile.addActionListener(this);
        openfile.setActionCommand("open");
        openfile.setFont(font);
        close.addActionListener(this);
        close.setActionCommand("close");
        close.setFont(font);
        p.add(openfile);
        p.add(close);
	
        this.pack();
	
        // Figure out the directory, from filename or current dir, if necessary
        if (directory == null) {
            File f;
            if ((filename != null)&& (f = new File(filename)).isAbsolute()) {
                directory = f.getParent();
                filename = f.getName();
            }
            else directory = System.getProperty("user.dir");
        }
	
        this.directory = directory;   // Remember the directory, for FileDialog
        setFile(directory, filename); // Now load and display the file
    }
    
    /**
     * Load and display the specified file from the specified directory
     **/
    public void setFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0)) return;
        File f;
        FileReader in = null;
        // Read and display the file contents.  Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            f = new File(directory, filename); // Create a file object
            in = new FileReader(f);            // And a char stream to read  it
            char[] buffer = new char[4096];    // Read 4K characters at a time
            int len;                           // How many chars read each time
            textarea.setText("");              // Clear the text area
            while((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textarea.append(s);                    // And display them
            }
            this.setTitle("FileViewer: " + filename);  // Set the window title
            textarea.setCaretPosition(0);              // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) { 
            textarea.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally { try { if (in!=null) in.close(); } catch (IOException e) {} }
    }

    /**
     * Handle button clicks
     **/
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("open")) {          // If user clicked "Open" button
            // Create a file dialog box to prompt for a new file to display
            FileDialog f = new FileDialog(this, "Open File", FileDialog.LOAD);
            f.setDirectory(directory);       // Set the default directory

	    // Display the dialog and wait for the user's response
            f.show();                        

            directory = f.getDirectory();    // Remember new default directory
            setFile(directory, f.getFile()); // Load and display selection
            f.dispose();                     // Get rid of the dialog box
        }
        else if (cmd.equals("close"))      // If user clicked "Close" button
            this.dispose();                  //    then close the window
    }
    
    /**
     * The FileViewer can be used by other classes, or it can be
     * used standalone with this main() method.
     **/
    static public void main(String[] args) throws IOException {
        // Create a FileViewer object
        Frame f = new FileViewer((args.length == 1)?args[0]:null);
        // Arrange to exit when the FileViewer window closes
        f.addWindowListener(new WindowAdapter() {
		public void windowClosed(WindowEvent e) { System.exit(0); }
	    });
        // And pop the window up
        f.show();
    }
}


