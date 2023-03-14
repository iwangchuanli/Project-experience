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
import com.davidflanagan.examples.reflect.Command;

/**
 * This class parses a Command object from a GUIResourceBundle.  It uses
 * the Command.parse() method to perform all the actual parsing work.
 **/
public class CommandParser implements ResourceParser {
    static final Class[] supportedTypes = new Class[] { Command.class };
    public Class[] getResourceTypes() { return supportedTypes;}

    public Object parse(GUIResourceBundle bundle, String key, Class type)
	throws java.util.MissingResourceException, java.io.IOException
    {
	String value = bundle.getString(key);  // look up the command text
	return Command.parse(bundle.getRoot(), value);  // parse it!
    }
}
