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
import org.w3c.dom.*;                // Core DOM classes
import org.w3c.dom.traversal.*;      // TreeWalker and related DOM classes
import org.apache.xerces.parsers.*;  // Apache Xerces parser classes
import org.xml.sax.*;                // Xerces DOM parser uses some SAX classes
import javax.swing.*;                // Swing classes 
import javax.swing.tree.*;           // TreeModel and related classes
import javax.swing.event.*;          // Tree-related event classes
import java.io.*;                    // For reading the input XML file

/**
 * This class implements the Swing TreeModel interface so that the DOM tree
 * returned by a TreeWalker can be displayed in a JTree component.
 **/
public class DOMTreeWalkerTreeModel implements TreeModel {
    TreeWalker walker;  // The TreeWalker we're modeling for JTree
    
    /** Create a TreeModel for the specified TreeWalker */
    public DOMTreeWalkerTreeModel(TreeWalker walker) { this.walker = walker; }

    /** 
     * Create a TreeModel for a TreeWalker that returns all nodes
     * in the specified document
     **/
    public DOMTreeWalkerTreeModel(Document document) {
	DocumentTraversal dt = (DocumentTraversal)document;
	walker = dt.createTreeWalker(document, NodeFilter.SHOW_ALL,null,false);
    }

    /** 
     * Create a TreeModel for a TreeWalker that returns the specified 
     * element and all of its descendant nodes.
     **/
    public DOMTreeWalkerTreeModel(Element element) {
	DocumentTraversal dt = (DocumentTraversal)element.getOwnerDocument();
	walker = dt.createTreeWalker(element, NodeFilter.SHOW_ALL, null,false);
    }

    // Return the root of the tree 
    public Object getRoot() { return walker.getRoot(); }
    
    // Is this node a leaf? (Leaf nodes are displayed differently by JTree)
    public boolean isLeaf(Object node) {
	walker.setCurrentNode((Node)node);   // Set current node
	Node child = walker.firstChild();    // Ask for a child
	return (child == null);              // Does it have any?
    }
    
    // How many children does this node have?
    public int getChildCount(Object node) {
	walker.setCurrentNode((Node)node);   // Set the current node
	// TreeWalker doesn't count children for us, so we count ourselves
	int numkids = 0;
	Node child = walker.firstChild();    // Start with the first child
	while(child != null) {               // Loop 'till there are no more
	    numkids++;                       // Update the count
	    child = walker.nextSibling();    // Get next child
	}
	return numkids;                      // This is the number of children
    }
    
    // Return the specified child of a parent node.
    public Object getChild(Object parent, int index) {
	walker.setCurrentNode((Node)parent);  // Set the current node
	// TreeWalker provides sequential access to children, not random
	// access, so we've got to loop through the kids one by one
	Node child = walker.firstChild();
	while(index-- > 0) child = walker.nextSibling();
	return child;
    }
    
    // Return the index of the child node in the parent node
    public int getIndexOfChild(Object parent, Object child) {
	walker.setCurrentNode((Node)parent);    // Set current node
	int index = 0;
	Node c = walker.firstChild();           // Start with first child
	while((c != child) && (c != null)) {    // Loop 'till we find a match
	    index++;
	    c = walker.nextSibling();           // Get the next child
	}
	return index;                           // Return matching position
    }
    
    // Only required for editable trees; unimplemented here.
    public void valueForPathChanged(TreePath path, Object newvalue) {}
    
    // This TreeModel never fires any events (since it is not editable)
    // so event listener registration methods are left unimplemented
    public void addTreeModelListener(TreeModelListener l) {}
    public void removeTreeModelListener(TreeModelListener l) {}

    /**
     * This main() method demonstrates the use of this class, the use of the
     * Xerces DOM parser, and the creation of a DOM Level 2 TreeWalker object.
     **/
    public static void main(String[] args) throws IOException, SAXException {
	// Obtain an instance of a Xerces parser to build a DOM tree.
	// Note that we are not using the JAXP API here, so this
	// code uses Apache Xerces APIs that are not standards
	DOMParser parser = new org.apache.xerces.parsers.DOMParser();

	// Get a java.io.Reader for the input XML file and 
	// wrap the input file in a SAX input source
	Reader in = new BufferedReader(new FileReader(args[0]));
	InputSource input = new org.xml.sax.InputSource(in);

	// Tell the Xerces parser to parse the input source
	parser.parse(input);

	// Ask the parser to give us our DOM Document.  Once we've got the DOM
	// tree, we don't have to use the Apache Xerces APIs any more; from
	// here on, we use the standard DOM APIs
	Document document = parser.getDocument();

	// If we're using a DOM Level 2 implementation, then our Document
	// object ought to implement DocumentTraversal 
	DocumentTraversal traversal = (DocumentTraversal)document;

	// For this demonstration, we create a NodeFilter that filters out
	// Text nodes containing only space; these just clutter up the tree
	NodeFilter filter = new NodeFilter() {
		public short acceptNode(Node n) {
		    if (n.getNodeType() == Node.TEXT_NODE) { 
			// Use trim() to strip off leading and trailing space.
			// If nothing is left, then reject the node
			if (((Text)n).getData().trim().length() == 0)
			    return NodeFilter.FILTER_REJECT;
		    }
		    return NodeFilter.FILTER_ACCEPT;
		}
	    };

	// This set of flags says to "show" all node types except comments
	int whatToShow = NodeFilter.SHOW_ALL & ~NodeFilter.SHOW_COMMENT;

	// Create a TreeWalker using the filter and the flags
	TreeWalker walker = traversal.createTreeWalker(document, whatToShow,
						       filter, false);

	// Instantiate a TreeModel and a JTree to display it
	JTree tree = new JTree(new DOMTreeWalkerTreeModel(walker));
	
	// Create a frame and a scrollpane to display the tree, and pop them up
	JFrame frame = new JFrame("DOMTreeWalkerTreeModel Demo");
	frame.getContentPane().add(new JScrollPane(tree));
	frame.setSize(500, 250);
	frame.setVisible(true);
    }
}
