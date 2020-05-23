/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.graphics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.print.*;

/**
 * This class displays one or more GraphicsExample objects in a
 * Swing JFrame and a JTabbedPane
 */
public class GraphicsExampleFrame extends JFrame {
    public GraphicsExampleFrame(final GraphicsExample[] examples) {
	super("GraphicsExampleFrame");

	Container cpane = getContentPane();   // Set up the frame 
	cpane.setLayout(new BorderLayout());
	final JTabbedPane tpane = new JTabbedPane(); // And the tabbed pane 
	cpane.add(tpane, BorderLayout.CENTER);

	// Add a menubar
	JMenuBar menubar = new JMenuBar();         // Create the menubar
	this.setJMenuBar(menubar);                 // Add it to the frame
	JMenu filemenu = new JMenu("File");        // Create a File menu
	menubar.add(filemenu);                     // Add to the menubar
	JMenuItem print = new JMenuItem("Print");  // Create a Print item
	filemenu.add(print);                       // Add it to the menu
	JMenuItem quit = new JMenuItem("Quit");    // Create a Quit item
	filemenu.add(quit);                        // Add it to the menu

	// Tell the Print menu item what to do when selected
	print.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // Get the currently displayed example, and call 
		    // the print method (defined below)
		    print(examples[tpane.getSelectedIndex()]);
		}
	    });

	// Tell the Quit menu item what to do when selected
	quit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { System.exit(0); }
	    });

	// In addition to the Quit menu item, also handle window close events
	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) { System.exit(0); }
	    });

	// Insert each of the example objects into the tabbed pane
	for(int i = 0; i < examples.length; i++) {
	    GraphicsExample e = examples[i];
	    tpane.addTab(e.getName(), new GraphicsExamplePane(e));
	}
    }

    /**
     * This inner class is a custom Swing component that displays
     * a GraphicsExample object.
     */
    public class GraphicsExamplePane extends JComponent {
	GraphicsExample example;  // The example to display
	Dimension size;           // How much space it requires
	
	public GraphicsExamplePane(GraphicsExample example) {
	    this.example = example;
	    size = new Dimension(example.getWidth(), example.getHeight());
	}

	/** Draw the component and the example it contains */
	public void paintComponent(Graphics g) {
	    g.setColor(Color.white);                    // set the background
	    g.fillRect(0, 0, size.width, size.height);  // to white
	    g.setColor(Color.black);             // set a default drawing color
	    example.draw((Graphics2D) g, this);  // ask example to draw itself
	}

	// These methods specify how big the component must be
	public Dimension getPreferredSize() { return size; }
	public Dimension getMinimumSize() { return size; }
    }

    /** This method is invoked by the Print menu item */
    public void print(final GraphicsExample example) {
	// Start off by getting a printer job to do the printing
	PrinterJob job = PrinterJob.getPrinterJob();
	// Wrap the example in a Printable object (defined below)
	// and tell the PrinterJob that we want to print it
	job.setPrintable(new PrintableExample(example));

	// Display the print dialog to the user
	if (job.printDialog()) {
	    // If they didn't cancel it, then tell the job to start printing
	    try {
		job.print();
	    }
	    catch(PrinterException e) {
		System.out.println("Couldn't print: " + e.getMessage());
	    }
	}
    }

    /**
     * This inner class implements the Printable interface in order to print
     * a GraphicsExample object.
     **/
    class PrintableExample implements Printable  {
	GraphicsExample example;  // The example to print

	// The constructor.  Just remember the example
	public PrintableExample(GraphicsExample example) {
	    this.example = example;
	}

	/**
	 * This method is called by the PrinterJob to print the example
	 **/
	public int print(Graphics g, PageFormat pf, int pageIndex) {
	    // Tell the PrinterJob that there is only one page
	    if (pageIndex != 0) return NO_SUCH_PAGE;

	    // The PrinterJob supplies us a Graphics object to draw with.
	    // Anything drawn with this object will be sent to the printer.
	    // The Graphics object can safely be cast to a Graphics2D object.
	    Graphics2D g2 = (Graphics2D)g;

	    // Translate to skip the left and top margins.
	    g2.translate(pf.getImageableX(), pf.getImageableY());

	    // Figure out how big the printable area is, and how big
	    // the example is.
	    double pageWidth = pf.getImageableWidth();
	    double pageHeight = pf.getImageableHeight();
	    double exampleWidth = example.getWidth();
	    double exampleHeight = example.getHeight();

	    // Scale the example if needed
	    double scalex = 1.0, scaley = 1.0;
	    if (exampleWidth > pageWidth) scalex = pageWidth/exampleWidth;
	    if (exampleHeight > pageHeight) scaley = pageHeight/exampleHeight;
	    double scalefactor = Math.min(scalex, scaley);
	    if (scalefactor != 1) g2.scale(scalefactor, scalefactor);

	    // Finally, call the draw() method of the example, passing in
	    // the Graphics2D object for the printer
	    example.draw(g2, GraphicsExampleFrame.this);

	    // Tell the PrinterJob that we successfully printed the page
	    return PAGE_EXISTS;
	}
    }

    /** 
     * The main program.  Use Java reflection to load and instantiate
     * the specified GraphicsExample classes, then create a
     * GraphicsExampleFrame to display them.
     **/
    public static void main(String[] args) {
	GraphicsExample[] examples = new GraphicsExample[args.length];

	// Loop through the command line arguments
	for(int i=0; i < args.length; i++) {
	    // The class name of the requested example
	    String classname = args[i];
	    
	    // If no package is specified, assume it is in this package
	    if (classname.indexOf('.') == -1)
		classname = "com.davidflanagan.examples.graphics."+args[i];

	    // Try to instantiate the named GraphicsExample class
	    try {
		Class exampleClass = Class.forName(classname);
		examples[i] = (GraphicsExample) exampleClass.newInstance();
	    }
	    catch (ClassNotFoundException e) {  // unknown class
		System.err.println("Couldn't find example: "  + classname);
		System.exit(1);
	    }
	    catch (ClassCastException e) {      // wrong type of class
		System.err.println("Class " + classname + 
				   " is not a GraphicsExample");
		System.exit(1);
	    }
	    catch (Exception e) {  // class doesn't have a public constructor
		// catch InstantiationException, IllegalAccessException
		System.err.println("Couldn't instantiate example: " +
				   classname);
		System.exit(1);
	    }
	}

	// Now create a window to display the examples in, and make it visible
	GraphicsExampleFrame f = new GraphicsExampleFrame(examples);
	f.pack();
	f.setVisible(true);
    }
}
