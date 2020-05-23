/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.xml;
import org.xml.sax.*;             // The main SAX package
import org.xml.sax.helpers.*;     // SAX helper classes
import java.io.*;                 // For reading the input file
import java.util.*;               // Hashtable, lists, and so on

/**
 * Parse a web.xml file using the SAX2 API and the Xerces parser from the
 * Apache project.
 * 
 * This class extends DefaultHandler so that instances can serve as SAX2
 * event handlers, and can be notified by the parser of parsing events.
 * We simply override the methods that receive events we're interested in
 **/
public class ListServlets2 extends org.xml.sax.helpers.DefaultHandler {
    /** The main method sets things up for parsing */
    public static void main(String[] args) throws IOException, SAXException {
	// Create the parser we'll use.  The parser implementation is a 
	// Xerces class, but we use it only through the SAX XMLReader API
	org.xml.sax.XMLReader parser=new org.apache.xerces.parsers.SAXParser();

	// Specify that we don't want validation.  This is the SAX2
	// API for requesting parser features.  Note the use of a
	// globally unique URL as the feature name.  Non-validation is
	// actually the default, so this line isn't really necessary.
	parser.setFeature("http://xml.org/sax/features/validation", false);

	// Instantiate this class to provide handlers for the parser and 
	// tell the parser about the handlers
	ListServlets2 handler = new ListServlets2();
	parser.setContentHandler(handler);
	parser.setErrorHandler(handler);

	// Create an input source that describes the file to parse.
	// Then tell the parser to parse input from that source
	org.xml.sax.InputSource input=new InputSource(new FileReader(args[0]));
	parser.parse(input);
    }

    HashMap nameToClass;     // Map from servlet name to servlet class name
    HashMap nameToPatterns;  // Map from servlet name to url patterns

    StringBuffer accumulator;                         // Accumulate text
    String servletName, servletClass, servletPattern; // Remember text

    // Called at the beginning of parsing.  We use it as an init() method
    public void startDocument() {
	accumulator = new StringBuffer();
	nameToClass = new HashMap();
	nameToPatterns = new HashMap();
    }

    // When the parser encounters plain text (not XML elements), it calls
    // this method, which accumulates them in a string buffer.
    // Note that this method may be called multiple times, even with no
    // intervening elements.
    public void characters(char[] buffer, int start, int length) {
	accumulator.append(buffer, start, length);
    }

    // At the beginning of each new element, erase any accumulated text.
    public void startElement(String namespaceURL, String localName,
			     String qname, Attributes attributes) {
	accumulator.setLength(0);
    }

    // Take special action when we reach the end of selected elements.
    // Although we don't use a validating parser, this method does assume
    // that the web.xml file we're parsing is valid.
    public void endElement(String namespaceURL, String localName, String qname)
    {
	if (localName.equals("servlet-name")) {        // Store servlet name
	    servletName = accumulator.toString().trim();
	}
	else if (localName.equals("servlet-class")) {  // Store servlet class
	    servletClass = accumulator.toString().trim();
	}
	else if (localName.equals("url-pattern")) {    // Store servlet pattern
	    servletPattern = accumulator.toString().trim();
	}
	else if (localName.equals("servlet")) {        // Map name to class
	    nameToClass.put(servletName, servletClass);
	}
	else if (localName.equals("servlet-mapping")) {// Map name to pattern
	    List patterns = (List)nameToPatterns.get(servletName);
	    if (patterns == null) {
		patterns = new ArrayList();
		nameToPatterns.put(servletName, patterns);
	    }
	    patterns.add(servletPattern);
	}
    }

    // Called at the end of parsing.  Used here to print our results.
    public void endDocument() {
	List servletNames = new ArrayList(nameToClass.keySet());
	Collections.sort(servletNames);
	for(Iterator iterator = servletNames.iterator(); iterator.hasNext();) {
	    String name = (String)iterator.next();
	    String classname = (String)nameToClass.get(name);
	    List patterns = (List)nameToPatterns.get(name);
	    System.out.println("Servlet: " + name);
	    System.out.println("Class: " + classname);
	    if (patterns != null) {
		System.out.println("Patterns:");
		for(Iterator i = patterns.iterator(); i.hasNext(); ) {
		    System.out.println("\t" + i.next());
		}
	    }
	    System.out.println();
	}
    }

    // Issue a warning
    public void warning(SAXParseException exception) {
	System.err.println("WARNING: line " + exception.getLineNumber() + ": "+
			   exception.getMessage());
    }

    // Report a parsing error
    public void error(SAXParseException exception) {
	System.err.println("ERROR: line " + exception.getLineNumber() + ": " +
			   exception.getMessage());
    }

    // Report a non-recoverable error and exit
    public void fatalError(SAXParseException exception) throws SAXException {
	System.err.println("FATAL: line " + exception.getLineNumber() + ": " +
			   exception.getMessage());
	throw(exception);
    }
}
