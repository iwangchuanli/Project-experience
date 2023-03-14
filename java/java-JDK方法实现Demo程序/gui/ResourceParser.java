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

/**
 * This interface defines an extension mechanism that allows GUIResourceBundle
 * to parse arbitrary resource types
 **/
public interface ResourceParser {
    /**
     * Return an array of classes that specify what kind of resources
     * this parser can handle 
     **/
    public Class[] getResourceTypes();

    /**
     * Read the property named by key from the specified bundle, convert
     * it to the specified type, and return it.  For complex resources,
     * the parser may need to read more than one property from the bundle; 
     * typically it may a number of properties whose names begin with the 
     * specified key.
     **/
    public Object parse(GUIResourceBundle bundle, String key, Class type)
	throws Exception;
}
