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
import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * This class is just like WebAppConfig, but it uses the JDOM (Beta 4) API
 * instead of the DOM and JAXP APIs
 **/
public class WebAppConfig2 {
    /** The main method creates and demonstrates a WebAppConfig2 object */
    public static void main(String[] args)
	throws IOException, JDOMException
    {
	// Create a new WebAppConfig object that represents the web.xml
	// file specified by the first command-line argument
	WebAppConfig2 config = new WebAppConfig2(new File(args[0]));

	// Query the tree for the class name associated with the servlet
	// name specified as the 2nd command-line argument
	System.out.println("Class for servlet " + args[1] + " is " +
			   config.getServletClass(args[1]));

	// Add a new servlet name-to-class mapping to the DOM tree
	config.addServlet("foo", "bar");

	// And write out an XML version of the DOM tree to standard out
	config.output(System.out);
    }

    /**
     * This field holds the parsed JDOM tree.  Note that this is a JDOM
     * Document, not a DOM Document.
     **/
    protected org.jdom.Document document;  

    /**
     * Read the specified File and parse it to create a JDOM tree
     **/
    public WebAppConfig2(File configfile) throws IOException, JDOMException {
	// JDOM can build JDOM trees from a variety of input sources.  One
	// of those input sources is a SAX parser.  
	SAXBuilder builder =
	    new SAXBuilder("org.apache.xerces.parsers.SAXParser");
	// Parse the specified file and convert it to a JDOM document
	document = builder.build(configfile);
    }

    /**
     * This method looks for specific Element nodes in the JDOM tree in order
     * to figure out the classname associated with the specified servlet name
     **/
    public String getServletClass(String servletName) throws JDOMException {
	// Get the root element of the document.
	Element root = document.getRootElement();

	// Find all <servlet> elements in the document, and loop through them
	// to find one with the specified name.  Note the use of java.util.List
	// instead of org.w3c.dom.NodeList.
	List servlets = root.getChildren("servlet");
	for(Iterator i = servlets.iterator(); i.hasNext(); ) {
	    Element servlet = (Element) i.next();
	    // Get the text of the <servlet-name> tag within the <servlet> tag
	    String name = servlet.getChild("servlet-name").getContent();
	    if (name.equals(servletName)) {
		// If the names match, return the text of the <servlet-class>
		return servlet.getChild("servlet-class").getContent();
	    }
	}
	return null;
    }

    /**
     * This method adds a new name-to-class mapping in in the form of
     * a <servlet> sub-tree to the document.
     **/
    public void addServlet(String servletName, String className)
	throws JDOMException
    {
	// Create the new Element that represents our new servlet
	Element newServletName = new Element("servlet-name");
	newServletName.setContent(servletName);
	Element newServletClass = new Element("servlet-class");
	newServletClass.setContent(className);
	Element newServlet = new Element("servlet");
	newServlet.addChild(newServletName);
	newServlet.addChild(newServletClass);

	// find the first <servlet> child in the document
	Element root = document.getRootElement();
	Element firstServlet = root.getChild("servlet");

	// Now insert our new servlet tag before the one we just found.
	Element parent = firstServlet.getParent();
	List children = parent.getChildren();
	children.add(children.indexOf(firstServlet), newServlet);
    }

    /**
     * Output the JDOM tree to the specified stream as an XML document.
     **/
    public void output(OutputStream out) throws IOException {
	// JDOM can output JDOM trees in a variety of ways (such as converting
	// them to DOM trees or SAX event streams).  Here we use an "outputter"
	// that converts a JDOM tree to an XML document
	XMLOutputter outputter = new XMLOutputter("  ",    // indentation
						  true);   // use newlines
	outputter.output(document, out);
    }
}
