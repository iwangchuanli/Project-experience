/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.print;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

/** 
 * A character output stream that sends output to a printer.
 **/
public class HardcopyWriter extends Writer {
    // These are the instance variables for the class
    protected PrintJob job;                 // The PrintJob object in use
    protected Graphics page;                // Graphics object for current page
    protected String jobname;               // The name of the print job
    protected int fontsize;                 // Point size of the font
    protected String time;                  // Current time (appears in header)
    protected Dimension pagesize;           // Size of the page (in dots)
    protected int pagedpi;                  // Page resolution in dots per inch
    protected Font font, headerfont;        // Body font and header font
    protected FontMetrics metrics;          // Metrics for the body font
    protected FontMetrics headermetrics;    // Metrics for the header font
    protected int x0, y0;                   // Upper-left corner inside margin
    protected int width, height;            // Size (in dots) inside margins
    protected int headery;                  // Baseline of the page header
    protected int charwidth;                // The width of each character
    protected int lineheight;               // The height of each line
    protected int lineascent;               // Offset of font baseline
    protected int chars_per_line;           // Number of characters per line
    protected int lines_per_page;           // Number of lines per page
    protected int charnum = 0, linenum = 0; // Current column and line position
    protected int pagenum = 0;              // Current page number

    // A field to save state between invocations of the write() method
    private boolean last_char_was_return = false;

    // A static variable that holds user preferences between print jobs
    protected static Properties printprops = new Properties();

    /**
     * The constructor for this class has a bunch of arguments:  
     * The frame argument is required for all printing in Java.
     * The jobname appears left justified at the top of each printed page.
     * The font size is specified in points, as on-screen font sizes are.
     * The margins are specified in inches (or fractions of inches).
     **/
    public HardcopyWriter(Frame frame, String jobname, int fontsize, 
			  double leftmargin, double rightmargin,
			  double topmargin, double bottommargin) 
	throws HardcopyWriter.PrintCanceledException
    {
	// Get the PrintJob object with which we'll do all the printing.
	// The call is synchronized on the static printprops object, which 
	// means that only one print dialog can be popped up at a time.
	// If the user clicks Cancel in the print dialog, throw an exception.
	Toolkit toolkit = frame.getToolkit();   // get Toolkit from Frame
	synchronized(printprops) {
	    job = toolkit.getPrintJob(frame, jobname, printprops);
	}
	if (job == null) 
	    throw new PrintCanceledException("User cancelled print request");
	
	pagesize = job.getPageDimension();      // query the page size
	pagedpi = job.getPageResolution();      // query the page resolution
	
	// Bug Workaround:
	// On windows, getPageDimension() and getPageResolution don't work, so
	// we've got to fake them.
	if (System.getProperty("os.name").regionMatches(true,0,"windows",0,7)){
	    // Use screen dpi, which is what the PrintJob tries to emulate
	    pagedpi = toolkit.getScreenResolution();
	    // Assume a 8.5" x 11" page size.  A4 paper users must change this.
	    pagesize = new Dimension((int)(8.5 * pagedpi), 11*pagedpi);
	    // We also have to adjust the fontsize.  It is specified in points,
	    // (1 point = 1/72 of an inch) but Windows measures it in pixels.
	    fontsize = fontsize * pagedpi / 72;
	}
	
	// Compute coordinates of the upper-left corner of the page.
	// I.e. the coordinates of (leftmargin, topmargin).  Also compute
	// the width and height inside of the margins.
	x0 = (int)(leftmargin * pagedpi);
	y0 = (int)(topmargin * pagedpi);
	width = pagesize.width - (int)((leftmargin + rightmargin) * pagedpi);
	height = pagesize.height - (int)((topmargin + bottommargin) * pagedpi);
	
	// Get body font and font size
	font = new Font("Monospaced", Font.PLAIN, fontsize);  
	metrics = frame.getFontMetrics(font);
	lineheight = metrics.getHeight();
	lineascent = metrics.getAscent();
	charwidth = metrics.charWidth('0');  // Assumes a monospaced font!
	
	// Now compute columns and lines will fit inside the margins
	chars_per_line = width / charwidth;
	lines_per_page = height / lineheight;
	
	// Get header font information
	// And compute baseline of page header: 1/8" above the top margin
	headerfont = new Font("SansSerif", Font.ITALIC, fontsize);
	headermetrics = frame.getFontMetrics(headerfont);
	headery = y0 - (int)(0.125 * pagedpi) -  
	    headermetrics.getHeight() + headermetrics.getAscent();
	
	// Compute the date/time string to display in the page header
	DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
						       DateFormat.SHORT);
	df.setTimeZone(TimeZone.getDefault());
	time = df.format(new Date());
	
	this.jobname = jobname;                 // save name
	this.fontsize = fontsize;               // save font size
    }
    
    /**
     * This is the write() method of the stream.  All Writer subclasses 
     * implement this.  All other versions of write() are variants of this one
     **/
    public void write(char[] buffer, int index, int len) {
	synchronized(this.lock) {  // For thread safety
	    // Loop through all the characters passed to us
	    for(int i = index; i < index + len; i++) {
		// If we haven't begun a page (or a new page), do that now.
		if (page == null) newpage();
		
		// If the character is a line terminator, then begin new line, 
		// unless it is a \n immediately after a \r.
		if (buffer[i] == '\n') {
		    if (!last_char_was_return) newline();
		    continue;
		}
		if (buffer[i] == '\r') {
		    newline();
		    last_char_was_return = true;
		    continue;
		}
		else last_char_was_return = false;
		
		// If it some other non-printing character, ignore it.
		if (Character.isWhitespace(buffer[i]) &&
		    !Character.isSpaceChar(buffer[i]) && (buffer[i] != '\t'))
		    continue;
		
		// If no more characters will fit on the line, start new line.
		if (charnum >= chars_per_line) {
		    newline();
		    // Also start a new page, if necessary
		    if (page == null) newpage();  
		}
		
		// Now print the character:
		// If it is a space, skip one space, without output.
		// If it is a tab, skip the necessary number of spaces.
		// Otherwise, print the character.
		// It is inefficient to draw only one character at a time, but
		// because our FontMetrics don't match up exactly to what the
		// printer uses we need to position each character individually
		if (Character.isSpaceChar(buffer[i])) charnum++;
		else if (buffer[i] == '\t') charnum += 8 - (charnum % 8);
		else {
		    page.drawChars(buffer, i, 1, 
				   x0 + charnum * charwidth, 
				   y0 + (linenum*lineheight) + lineascent);
		    charnum++;
		}
	    }
	}
    }
    
    /** 
     * This is the flush() method that all Writer subclasses must implement.
     * There is no way to flush a PrintJob without prematurely printing the
     * page, so we don't do anything.
     **/
    public void flush() { /* do nothing */ }
    
    /**
     * This is the close() method that all Writer subclasses must implement.
     * Print the pending page (if any) and terminate the PrintJob.
     */
    public void close() {
	synchronized(this.lock) {
	    if (page != null) page.dispose();   // Send page to the printer
	    job.end();                          // Terminate the job
	}
    }
    
    /**
     * Set the font style.  The argument should be one of the font style 
     * constants defined by the java.awt.Font class.  All subsequent output
     * will be in that style.  This method relies on all styles of the
     * Monospaced font having the same metrics.
     **/
    public void setFontStyle(int style) {
	synchronized (this.lock) {
	    // Try to set a new font, but restore current one if it fails
	    Font current = font;
	    try { font = new Font("Monospaced", style, fontsize); }
	    catch (Exception e) { font = current; }
	    // If a page is pending, set the new font. Otherwise newpage() will
	    if (page != null) page.setFont(font);
	}
    }
    
    /** End the current page.  Subsequent output will be on a new page. */
    public void pageBreak() { synchronized(this.lock) { newpage(); } }
    
    /** Return the number of columns of characters that fit on the page */
    public int getCharactersPerLine() { return this.chars_per_line; }
    
    /** Return the number of lines that fit on a page */
    public int getLinesPerPage() { return this.lines_per_page; }
    
    /** This internal method begins a new line */
    protected void newline() {
	charnum = 0;                      // Reset character number to 0
	linenum++;                        // Increment line number
	if (linenum >= lines_per_page) {  // If we've reached the end of page
	    page.dispose();                 //  send page to printer
	    page = null;                    //  but don't start a new page yet.
	}
    }
    
    /** This internal method begins a new page and prints the header. */
    protected void newpage() {
	page = job.getGraphics();              // Begin the new page
	linenum = 0; charnum = 0;              // Reset line and char number
	pagenum++;                             // Increment page number
	page.setFont(headerfont);              // Set the header font.
	page.drawString(jobname, x0, headery); // Print job name left justified
	
	String s = "- " + pagenum + " -";      // Print the page # centered.
	int w = headermetrics.stringWidth(s);
	page.drawString(s, x0 + (this.width - w)/2, headery);
	w = headermetrics.stringWidth(time);   // Print date right justified
	page.drawString(time, x0 + width - w, headery);
	
	// Draw a line beneath the header
	int y = headery + headermetrics.getDescent() + 1;
	page.drawLine(x0, y, x0+width, y);
	
	// Set the basic monospaced font for the rest of the page.
	page.setFont(font);
    }
    
    /**
     * This is the exception class that the HardcopyWriter constructor
     * throws when the user clicks "Cancel" in the print dialog box.
     **/
    public static class PrintCanceledException extends Exception {
	public PrintCanceledException(String msg) { super(msg); }
    }
    
    /**
     * A program that prints the specified file using HardcopyWriter
     **/
    public static class PrintFile {
	public static void main(String[] args) {
	    try {
		if (args.length != 1) 
		    throw new IllegalArgumentException("Wrong # of arguments");
		FileReader in = new FileReader(args[0]);
		HardcopyWriter out = null;
		Frame f = new Frame("PrintFile: " + args[0]);
		f.setSize(200, 50);
		f.show();
		try {
		    out = new HardcopyWriter(f, args[0], 10, .5, .5, .5, .5);
		} 
		catch (HardcopyWriter.PrintCanceledException e) {
		    System.exit(0);
		}
		f.setVisible(false);
		char[] buffer = new char[4096];
		int numchars;
		while((numchars = in.read(buffer)) != -1) 
		    out.write(buffer, 0, numchars);
		in.close();
		out.close();
	    }
	    catch (Exception e) {
		System.err.println(e);
		System.err.println("Usage: " +
				   "java HardcopyWriter$PrintFile <filename>");
		System.exit(1);
	    }
	    System.exit(0); 
	}
    }
    
    /** 
     * A program that prints a demo page using HardcopyWriter
     **/
    public static class Demo extends Frame implements ActionListener {
	/** The main method of the program.  Create a test window */
	public static void main(String[] args) {
	    Frame f = new Demo();
	    f.show();
	}
	// Buttons used in this program
	protected Button print, quit;
	
	/** Constructor for the test program's window. */
	public Demo() {
	    super("HardcopyWriter Test");          // Call frame constructor
	    Panel p = new Panel();                 // Add a panel to the frame
	    this.add(p, "Center");                 // Center it
	    p.setFont(new Font("SansSerif",        // Set a default font
			       Font.BOLD, 18));
	    print = new Button("Print Test Page"); // Create a Print button
	    quit = new Button("Quit");             // Create a Quit button
	    print.addActionListener(this);         // Specify that we'll handle
	    quit.addActionListener(this);          //   button presses
	    p.add(print);                          // Add the buttons to panel
	    p.add(quit);
	    this.pack();                           // Set the frame size
	}
	
	/** Handle the button presses */
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o == quit) System.exit(0);
	    else if (o == print) printDemoPage();
	}
	
	/** Print the demo page */
	public void printDemoPage() {
	    // Create a HardcopyWriter, using a 14 point font and 3/4" margins.
	    HardcopyWriter hw;
	    try { hw=new HardcopyWriter(this, "Demo Page",14,.75,.75,.75,.75);}
	    catch (HardcopyWriter.PrintCanceledException e) { return; }
	    
	    // Send output to it through a PrintWriter stream
	    PrintWriter out = new PrintWriter(hw);
	    
	    // Figure out the size of the page
	    int rows = hw.getLinesPerPage(), cols = hw.getCharactersPerLine();
	    
	    // Mark upper left and upper-right corners
	    out.print("+");                            // upper-left corner
	    for(int i=0;i<cols-2;i++) out.print(" ");  // space over
	    out.print("+");                            // upper-right corner
	    
	    // Display a title
	    hw.setFontStyle(Font.BOLD + Font.ITALIC);
	    out.println("\n\t\tHardcopy Writer Demo Page\n\n");
	    
	    // Demonstrate font styles
	    hw.setFontStyle(Font.BOLD);
	    out.println("Font Styles:");
	    int[] styles = { Font.PLAIN, Font.BOLD,
			     Font.ITALIC, Font.ITALIC+Font.BOLD };
	    for(int i = 0; i < styles.length; i++) {
		hw.setFontStyle(styles[i]);
		out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
			    "abcdefghijklmnopqrstuvwxyz");
		out.println("1234567890!@#$%^&*()[]{}<>,.?:;+-=/\\`'\"_~|");
	    }
	    hw.setFontStyle(Font.PLAIN);
	    out.println("\n");
	    
	    // Demonstrate tab stops
	    hw.setFontStyle(Font.BOLD);
	    out.println("Tab Stops:");
	    hw.setFontStyle(Font.PLAIN);
	    out.println("          1         2         3         4         5");
	    out.println("012345678901234567890123456789012345678901234567890");
	    out.println("^\t^\t^\t^\t^\t^\t^");
	    out.println("\n");
	    
	    // Output some information about page dimensions and resolution
	    hw.setFontStyle(Font.BOLD);
	    out.println("Dimensions:");
	    hw.setFontStyle(Font.PLAIN);
	    out.println("\tResolution: " + hw.pagedpi + " dots per inch");
	    out.println("\tPage width (pixels): " + hw.pagesize.width);
	    out.println("\tPage height (pixels): " + hw.pagesize.height);
	    out.println("\tWidth inside margins (pixels): " + hw.width);
	    out.println("\tHeight inside margins (pixels): " + hw.height);
	    out.println("\tCharacters per line: " + cols);
	    out.println("\tLines per page: " + rows);
	    
	    // Skip down to the bottom of the page
	    for(int i = 0; i < rows-30; i++) out.println();

	    // And mark the lower left and lower right
	    out.print("+");                            // lower-left
	    for(int i=0;i<cols-2;i++) out.print(" ");  // space-over
	    out.print("+");                            // lower-right
	    
	    // Close the output stream, forcing the page to be printed
	    out.close();
	}
    }
}
