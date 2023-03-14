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
import java.util.MissingResourceException;

/**
 * This subclass of MissingResourceException signals that a resource value
 * was present, but could not be properly parsed or otherwise converted to
 * the desired type.
 **/
public class MalformedResourceException extends MissingResourceException {
    public MalformedResourceException(String msg, String type, String key){
	super(msg, type, key);
    }
    // Convenience constructors: automatically generate exception message
    public MalformedResourceException(String type, String key){
	super("Malformed " + type + " resource: " + key, type, key);
    }
    public MalformedResourceException(Class type, String key){
	this(type.getName(), key);
    }
}
