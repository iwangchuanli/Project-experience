/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.gui;
import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * This class extends ResourceBundle and adds methods to retrieve types of
 * resources commonly used in GUIs.  Additionally, it adds extensibility
 * by allowing ResourceParser objects to be registered to parse other
 * resource types.
 **/
public class GUIResourceBundle extends ResourceBundle {
    // The root object.  Required to parse certain resource types like Commands
    Object root;           

    // The resource bundle that actually contains the textual resources
    // This class is a wrapper around this bundle
    ResourceBundle bundle;

    /** Create a GUIResourceBundle wrapper around a specified bundle */
    public GUIResourceBundle(Object root, ResourceBundle bundle) {
	this.root = root;
	this.bundle = bundle;
    }

    /**
     * Load a named bundle and create a GUIResourceBundle around it.  This 
     * constructor takes advantage of the internationalization features of
     * the ResourceBundle.getBundle() method.
     **/
    public GUIResourceBundle(Object root, String bundleName)
	throws MissingResourceException
    {
	this.root = root;
	this.bundle = ResourceBundle.getBundle(bundleName);
    }

    /**
     * Create a PropertyResourceBundle from the specified stream and then
     * create a GUIResourceBundle wrapper for it
     **/
    public GUIResourceBundle(Object root, InputStream propertiesStream)
        throws IOException
    {
	this.root = root;
	this.bundle = new PropertyResourceBundle(propertiesStream);
    }

    /**
     * Create a PropertyResourceBundle from the specified properties file and
     * then create a GUIResourceBundle wrapper for it.
     **/
    public GUIResourceBundle(Object root, File propertiesFile) 
        throws IOException
    {
	this(root, new FileInputStream(propertiesFile));
    }

    /** This is one of the abstract methods of ResourceBundle */
    public Enumeration getKeys() { return bundle.getKeys(); }

    /** This is the other abstract method of ResourceBundle */
    protected Object handleGetObject(String key)
	throws MissingResourceException
    {
	return bundle.getObject(key);  // simply defer to the wrapped bundle
    }
    
    /** This is a property accessor method for our root object */
    public Object getRoot() { return root; }

    /** 
     * This method is like the inherited getString() method, except that 
     * when the named resource is not found, it returns the specified default 
     * instead of throwing an exception 
     **/
    public String getString(String key, String defaultValue) {
	try { return bundle.getString(key); }
	catch(MissingResourceException e) { return defaultValue; }
    }

    /**
     * Look up the named resource and parse it as a list of strings separated
     * by spaces, tabs, or commas.
     **/
    public java.util.List getStringList(String key)
	throws MissingResourceException
    {
	String s = getString(key);
	StringTokenizer t = new StringTokenizer(s, ", \t", false);
	ArrayList list = new ArrayList();
	while(t.hasMoreTokens()) list.add(t.nextToken());
	return list;
    }

    /** Like above, but return a default instead of throwing an exception */
    public java.util.List getStringList(String key,
					java.util.List defaultValue) {
	try { return getStringList(key); }
	catch(MissingResourceException e) { return defaultValue; }
    }

    /** Look up the named resource and try to interpret it as a boolean. */
    public boolean getBoolean(String key) throws MissingResourceException {
	String s = bundle.getString(key);
	s = s.toLowerCase();
	if (s.equals("true")) return true;
	else if (s.equals("false")) return false;
	else if (s.equals("yes")) return true;
	else if (s.equals("no")) return false;
	else if (s.equals("on")) return true;
	else if (s.equals("off")) return false;
	else {
	    throw new MalformedResourceException("boolean", key);
	}
    }

    /** As above, but return the default instead of throwing an exception */
    public boolean getBoolean(String key, boolean defaultValue) {
	try { return getBoolean(key); }
	catch(MissingResourceException e) {
	    if (e instanceof MalformedResourceException)
		System.err.println("WARNING: " + e.getMessage());
	    return defaultValue;
	}
    }

    /** Like getBoolean(), but for integers */
    public int getInt(String key) throws MissingResourceException {
	String s = bundle.getString(key);
	
	try {
	    // Use decode() instead of parseInt() so we support octal
	    // and hexadecimal numbers
	    return Integer.decode(s).intValue();
	} catch (NumberFormatException e) {
	    throw new MalformedResourceException("int", key);
	}
    }

    /** As above, but with a default value */
    public int getInt(String key, int defaultValue) {
	try { return getInt(key); }
	catch(MissingResourceException e) {
	    if (e instanceof MalformedResourceException)
		System.err.println("WARNING: " + e.getMessage());
	    return defaultValue;
	}
    }

    /** Return a resource of type double */
    public double getDouble(String key) throws MissingResourceException {
	String s = bundle.getString(key);
	
	try {
	    return Double.parseDouble(s);
	} catch (NumberFormatException e) {
	    throw new MalformedResourceException("double", key);
	}
    }

    /** As above, but with a default value */
    public double getDouble(String key, double defaultValue) {
	try { return getDouble(key); }
	catch(MissingResourceException e) {
	    if (e instanceof MalformedResourceException)
		System.err.println("WARNING: " + e.getMessage());
	    return defaultValue;
	}
    }

    /** Look up the named resource and convert to a Font */
    public Font getFont(String key) throws MissingResourceException {
	// Font.decode() always returns a Font object, so we can't check
	// whether the resource value was well-formed or not.
	return Font.decode(bundle.getString(key));
    }

    /** As above, but with a default value */
    public Font getFont(String key, Font defaultValue) {
	try { return getFont(key); }
	catch (MissingResourceException e) { return defaultValue; }
    }

    /** Look up the named resource, and convert to a Color */
    public Color getColor(String key) throws MissingResourceException {
	try {
	    return Color.decode(bundle.getString(key));
	}
	catch (NumberFormatException e) { 
	    // It would be useful to try to parse color names here as well
	    // as numeric color specifications
	    throw new MalformedResourceException("Color", key);
	}
    }

    /** As above, but with a default value */
    public Color getColor(String key, Color defaultValue) {
	try { return getColor(key); }
	catch(MissingResourceException e) {
	    if (e instanceof MalformedResourceException)
		System.err.println("WARNING: " + e.getMessage());
	    return defaultValue;
	}
    }

    /** A hashtable for mapping resource types to resource parsers */
    static HashMap parsers = new HashMap();

    /** An extension mechanism: register a parser for new resource types */
    public static void registerResourceParser(ResourceParser parser) {
	// Ask the ResourceParser what types it can parse
	Class[] supportedTypes = parser.getResourceTypes();
	// Register it in the hashtable for each of those types
	for(int i = 0; i < supportedTypes.length; i++)
	    parsers.put(supportedTypes[i], parser);
    }

    /** Look up a ResourceParser for the specified resource type */
    public static ResourceParser getResourceParser(Class type) {
	return (ResourceParser) parsers.get(type);
    }

    /**
     * Look for a ResourceParser for the named type, and if one is found, 
     * ask it to parse and return the named resource 
     **/
    public Object getResource(String key, Class type)
	throws MissingResourceException
    {
	// Get a parser for the specified type
	ResourceParser parser = (ResourceParser)parsers.get(type);
	if (parser == null) 
	    throw new MissingResourceException(
                  "No ResourceParser registered for " +
		  type.getName() + " resources",
		  type.getName(), key);
	
	try {  // Ask the parser to parse the resource
	    return parser.parse(this, key, type);
	}
	catch(MissingResourceException e) {
	    throw e;  // Rethrow MissingResourceException exceptions
	}
	catch(Exception e) {
	    // If any other type of exception occurs, convert it to
	    // a MalformedResourceException
	    String msg = "Malformed " + type.getName() + " resource: " +
		key + ": " + e.getMessage();
	    throw new MalformedResourceException(msg, type.getName(), key);
	}
    }

    /**
     * Like the 2-argument version of getResource, but return a default value
     * instead of throwing a MissingResourceException
     **/
    public Object getResource(String key, Class type, Object defaultValue) {
	try {  return getResource(key, type); }
	catch (MissingResourceException e) {
	    if (e instanceof MalformedResourceException)
		System.err.println("WARNING: " + e.getMessage());
	    return defaultValue;
	}
    }
}
