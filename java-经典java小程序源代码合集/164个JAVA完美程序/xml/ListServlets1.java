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
import javax.xml.parsers.*;                   // The JAXP package
import org.xml.sax.*;                         // The main SAX package
import java.io.*;

/**
 * Parse a web.xml file using JAXP and SAX1.  Print out the names
 * and class names of all servlets listed in the file.
 * 
 * This class implements the HandlerBase helper class, which means
 * that it defines all the "callback" methods that the SAX parser will
 * invoke to notify the application.  In this example we override the 
 * methods that we require.
 *
 * This example uses full package names in places to help keep the JAXP
 * and SAX APIs distinct.
 **/
public class ListServlets1 extends org.xml.sax.HandlerBase {
    /** The main method sets things up for parsing */
    public static void main(String[] args)
	throws IOException, SAXException, ParserConfigurationException
    {
	// Create a JAXP "parser factory" for creating SAX parsers
	javax.xml.parsers.SAXParserFactory spf=SAXParserFactory.newInstance();

	// Configure the parser factory for the type of parsers we require
	spf.setValidating(false);  // No validation required

	// Now use the parser factory to create a SAXParser object
	// Note that SAXParser is a JAXP class, not a SAX class
	javax.xml.parsers.SAXParser sp = spf.newSAXParser();
	
	// Create a SAX input source for the file argument
	org.xml.sax.InputSource input=new InputSource(new FileReader(args[0]));

	// Give the InputSource an absolute URL for the file, so that
	// it can resolve relative URLs in a <!DOCTYPE> declaration, e.g.
	input.setSystemId("file://" + new File(args[0]).getAbsolutePath());

	// Create an instance of this class; it defines all the handler methods
	ListServlets1 handler = new ListServlets1();

	// Finally, tell the parser to parse the input and notify the handler
	sp.parse(input, handler);
	
	// Instead of using the SAXParser.parse() method, which is part of the
	// JAXP API, we could also use the SAX1 API directly.  Note the
	// difference between the JAXP class javax.xml.parsers.SAXParser and
	// the SAX1 class org.xml.sax.Parser
	//
	// org.xml.sax.Parser parser = sp.getParser();  // Get the SAX parser
	// parser.setDocumentHandler(handler);          // Set main handler
	// parser.setErrorHandler(handler);             // Set error handler
	// parser.parse(input);                         // Parse!
    }

    StringBuffer accumulator = new StringBuffer();  // Accumulate parsed text
    String servletName;      // The name of the servlet
    String servletClass;     // The class name of the servlet
    String servletId;        // Value of id attribute of <servlet> tag

    // When the parser encounters plain text (not XML elements), it calls
    // this method, which accumulates them in a string buffer
    public void characters(char[] buffer, int start, int length) {
	accumulator.append(buffer, start, length);
    }

    // Every time the parser encounters the beginning of a new element, it
    // calls this method, which resets the string buffer
    public void startElement(String name, AttributeList attributes) {
	accumulator.setLength(0);  // Ready to accumulate new text
	// If its a servlet tag, look for id attribute
	if (name.equals("servlet"))
	    servletId = attributes.getValue("id");
    }

    // When the parser encounters the end of an element, it calls this method
    public void endElement(String name) {
	if (name.equals("servlet-name")) {
	    // After </servlet-name>, we know the servlet name saved up
	    servletName = accumulator.toString().trim();
	}
	else if (name.equals("servlet-class")) {
	    // After </servlet-class>, we've got the class name accumulated
	    servletClass = accumulator.toString().trim();
	}
	else if (name.equals("servlet")) {
	    // Assuming the document is valid, then when we parse </servlet>,
	    // we know we've got a servlet name and class name to print out
	    System.out.println("Servlet " + servletName +
			       ((servletId != null)?" (id="+servletId+")":"") +
			       ": " + servletClass);
	}
    }

    /** This method is called when warnings occur */
    public void warning(SAXParseException exception) {
	System.err.println("WARNING: line " + exception.getLineNumber() + ": "+
			   exception.getMessage());
    }

    /** This method is called when errors occur */
    public void error(SAXParseException exception) {
	System.err.println("ERROR: line " + exception.getLineNumber() + ": " +
			   exception.getMessage());
    }

    /** This method is called when non-recoverable errors occur. */
    public void fatalError(SAXParseException exception) throws SAXException {
	System.err.println("FATAL: line " + exception.getLineNumber() + ": " +
			   exception.getMessage());
	throw(exception);
    }
}
