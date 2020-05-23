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
import java.text.DateFormat;
import java.util.Date;

/** 
 * This class creates and displays a window containing a list of
 * files and sub-directories in a specified directory.  Clicking on an
 * entry in the list displays more information about it. Double-clicking 
 * on an entry displays it, if a file, or lists it if a directory.
 * An optionally-specified FilenameFilter filters the displayed list.
 **/
public class FileLister extends Frame implements ActionListener, ItemListener {
    private List list;                  // To display the directory contents in
    private TextField details;          // To display detail info in.
    private Panel buttons;              // Holds the buttons
    private Button up, close;           // The Up and Close buttons
    private File currentDir;            // The directory currently listed
    private FilenameFilter filter;      // An optional filter for the directory
    private String[] files;             // The directory contents
    private DateFormat dateFormatter =  // To display dates and time correctly
          DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    
    /** 
     * Constructor: create the GUI, and list the initial directory. 
     **/
    public FileLister(String directory, FilenameFilter filter) { 
        super("File Lister");              // Create the window
        this.filter = filter;              // Save the filter, if any
        
	// Destroy the window when the user requests it
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) { dispose(); }
	    });

        list = new List(12, false);        // Set up the list
        list.setFont(new Font("MonoSpaced", Font.PLAIN, 14));
        list.addActionListener(this);
        list.addItemListener(this);
        
        details = new TextField();         // Set up the details area
        details.setFont(new Font("MonoSpaced", Font.PLAIN, 12));
        details.setEditable(false);

        buttons = new Panel();             // Set up the button box
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        buttons.setFont(new Font("SansSerif", Font.BOLD, 14));

        up = new Button("Up a Directory"); // Set up the two buttons
        close = new Button("Close");
        up.addActionListener(this);
        close.addActionListener(this);

        buttons.add(up);                   // Add buttons to button box
        buttons.add(close);
        
        this.add(list, "Center");          // Add stuff to the window
        this.add(details, "North");
        this.add(buttons, "South");
        this.setSize(500, 350);
        
        listDirectory(directory);          // And now list initial directory.
    }
    
    /**
     * This method uses the list() method to get all entries in a directory
     * and then displays them in the List component. 
     **/
    public void listDirectory(String directory) {
        // Convert the string to a File object, and check that the dir exists
        File dir = new File(directory);
        if (!dir.isDirectory()) 
           throw new IllegalArgumentException("FileLister: no such directory");

        // Get the (filtered) directory entries
        files = dir.list(filter);        

        // Sort the list of filenames.  Prior to Java 1.2, you could use
	// com.davidflanagan.examples.classes.Sorter.sort() to sort instead.
	java.util.Arrays.sort(files);
	
        // Remove any old entries in the list, and add the new ones
        list.removeAll();
        list.add("[Up to Parent Directory]");  // A special case entry
        for(int i = 0; i < files.length; i++) list.add(files[i]);
        
        // Display directory name in window titlebar and in the details box
        this.setTitle(directory);
        details.setText(directory);
	
        // Remember this directory for later.
        currentDir = dir;
    }
    
    /**
     * This ItemListener method uses various File methods to obtain information
     * about a file or directory. Then it displays that info.
     **/
    public void itemStateChanged(ItemEvent e) {
        int i = list.getSelectedIndex() - 1;  // minus 1 for Up To Parent entry
        if (i < 0) return;
        String filename = files[i];               // Get the selected entry 
        File f = new File(currentDir, filename);  // Convert to a File
        if (!f.exists())                          // Confirm that it exists
            throw new IllegalArgumentException("FileLister: " +
					       "no such file or directory");

        // Get the details about the file or directory, concatenate to a string
        String info = filename;
        if (f.isDirectory()) info += File.separator;
        info += " " + f.length() + " bytes ";
        info += dateFormatter.format(new java.util.Date(f.lastModified()));
        if (f.canRead()) info += " Read";
        if (f.canWrite()) info += " Write";
	
        // And display the details string
        details.setText(info);
    }
    
    /**
     * This ActionListener method is invoked when the user double-clicks on an 
     * entry or clicks on one of the buttons.  If they double-click on a file,
     * create a FileViewer to display that file.  If they double-click on a
     * directory, call the listDirectory() method to display that directory
     **/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) this.dispose();
        else if (e.getSource() == up) { up(); }
        else if (e.getSource() == list) {  // Double click on an item
            int i = list.getSelectedIndex(); // Check which item
            if (i == 0) up();                // Handle first Up To Parent item
            else {                           // Otherwise, get filename
                String name = files[i-1]; 
                File f = new File(currentDir, name);    // Convert to a File
                String fullname = f.getAbsolutePath();
                if (f.isDirectory()) listDirectory(fullname);  // List dir
                else new FileViewer(fullname).show();          // display file
            }
        }
    }
    
    /** A convenience method to display the contents of the parent directory */
    protected void up() {
        String parent = currentDir.getParent();
        if (parent == null) return;
        listDirectory(parent);
    }
    
    /** A convenience method used by main() */
    public static void usage() {
        System.out.println("Usage: java FileLister [directory_name] " + 
			   "[-e file_extension]");
        System.exit(0);
    }
    
    /**
     * A main() method so FileLister can be run standalone.
     * Parse command line arguments and create the FileLister object.
     * If an extension is specified, create a FilenameFilter for it.
     * If no directory is specified, use the current directory.
     **/
    public static void main(String args[]) throws IOException {
        FileLister f;
        FilenameFilter filter = null;  // The filter, if any
        String directory = null;       // The specified dir, or the current dir
        
        // Loop through args array, parsing arguments
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-e")) {
                if (++i >= args.length) usage();
                final String suffix = args[i];  // final for anon. class below

                // This class is a simple FilenameFilter.  It defines the
                // accept() method required to determine whether a specified
                // file should be listed.  A file will be listed if its name
                // ends with the specified extension, or if it is a directory.
                filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
			    if (name.endsWith(suffix)) return true;
			    else return (new File(dir, name)).isDirectory();
			}
		    };
            }
            else {
                if (directory != null) usage();  // If already specified, fail.
                else directory = args[i];
            }
        }
        
        // if no directory specified, use the current directory
        if (directory == null) directory = System.getProperty("user.dir");
        // Create the FileLister object, with directory and filter specified.
        f = new FileLister(directory, filter);
        // Arrange for the application to exit when the window is closed
        f.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) { System.exit(0); }
        });
        // Finally, pop the window up up.
        f.show();
    }
}
