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
import org.w3c.dom.*;         // W3C DOM classes for traversing the document
import java.io.*;

/**
 * Output a DOM Level 1 Document object to a java.io.PrintWriter as a simple
 * XML document.  This class does not handle every type of DOM node, and it
 * doesn't deal with all the details of XML like DTDs, character encodings and
 * preserved and ignored whitespace.  However, it does output basic
 * well-formed XML that can be parsed by a non-validating parser.
 **/
public class XMLDocumentWriter {
    PrintWriter out;  // the stream to send output to

    /** Initialize the output stream */
    public XMLDocumentWriter(PrintWriter out) {	this.out = out; }

    /** Close the output stream. */
    public void close() { out.close(); }

    /** Output a DOM Node (such as a Document) to the output stream */
    public void write(Node node) { write(node, ""); }

    /**
     * Output the specified DOM Node object, printing it using the specified
     * indentation string
     **/
    public void write(Node node, String indent) {
	// The output depends on the type of the node
	switch(node.getNodeType()) {
	case Node.DOCUMENT_NODE: {       // If its a Document node
	    Document doc = (Document)node;
	    out.println(indent + "<?xml version='1.0'?>");  // Output header
	    Node child = doc.getFirstChild();   // Get the first node
	    while(child != null) {              // Loop 'till no more nodes
		write(child, indent);           // Output node
		child = child.getNextSibling(); // Get next node
	    }
	    break;
	} 
	case Node.DOCUMENT_TYPE_NODE: {  // It is a <!DOCTYPE> tag
	    DocumentType doctype = (DocumentType) node;
	    // Note that the DOM Level 1 does not give us information about
	    // the the public or system ids of the doctype, so we can't output
	    // a complete <!DOCTYPE> tag here.  We can do better with Level 2.
	    out.println("<!DOCTYPE " + doctype.getName() + ">");
	    break;
	}
	case Node.ELEMENT_NODE: {        // Most nodes are Elements
	    Element elt = (Element) node;
	    out.print(indent + "<" + elt.getTagName());   // Begin start tag
	    NamedNodeMap attrs = elt.getAttributes();     // Get attributes
	    for(int i = 0; i < attrs.getLength(); i++) {  // Loop through them
		Node a = attrs.item(i);
		out.print(" " + a.getNodeName() + "='" +  // Print attr. name
			  fixup(a.getNodeValue()) + "'"); // Print attr. value
	    }
	    out.println(">");                             // Finish start tag

	    String newindent = indent + "    ";           // Increase indent
	    Node child = elt.getFirstChild();             // Get child
	    while(child != null) {                        // Loop 
		write(child, newindent);                  // Output child
		child = child.getNextSibling();           // Get next child
	    }

	    out.println(indent + "</" +                   // Output end tag
			elt.getTagName() + ">");
	    break;
	}
	case Node.TEXT_NODE: {                   // Plain text node
	    Text textNode = (Text)node;
	    String text = textNode.getData().trim();   // Strip off space
	    if ((text != null) && text.length() > 0)   // If non-empty
		out.println(indent + fixup(text));     // print text
	    break;
	}
	case Node.PROCESSING_INSTRUCTION_NODE: {  // Handle PI nodes
	    ProcessingInstruction pi = (ProcessingInstruction)node;
	    out.println(indent + "<?" + pi.getTarget() +
			       " " + pi.getData() + "?>");
	    break;
	}
	case Node.ENTITY_REFERENCE_NODE: {        // Handle entities
	    out.println(indent + "&" + node.getNodeName() + ";");
	    break;
	}
	case Node.CDATA_SECTION_NODE: {           // Output CDATA sections
	    CDATASection cdata = (CDATASection)node;
	    // Careful! Don't put a CDATA section in the program itself!
	    out.println(indent + "<" + "![CDATA[" + cdata.getData() +
			"]]" + ">");
	    break;
	}
	case Node.COMMENT_NODE: {                 // Comments
	    Comment c = (Comment)node;
	    out.println(indent + "<!--" + c.getData() + "-->");
	    break;
	}
	default:   // Hopefully, this won't happen too much!
	    System.err.println("Ignoring node: " + node.getClass().getName());
	    break;
	}
    }

    // This method replaces reserved characters with entities.
    String fixup(String s) {
	StringBuffer sb = new StringBuffer();
	int len = s.length();
	for(int i = 0; i < len; i++) {
	    char c = s.charAt(i);
	    switch(c) {
	    default: sb.append(c); break;
	    case '<': sb.append("&lt;"); break;
	    case '>': sb.append("&gt;"); break;
	    case '&': sb.append("&amp;"); break;
	    case '"': sb.append("&quot;"); break;
	    case '\'': sb.append("&apos;"); break;
	    }
	}
	return sb.toString();
    }
}
