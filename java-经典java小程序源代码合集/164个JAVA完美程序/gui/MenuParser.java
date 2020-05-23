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
import com.davidflanagan.examples.reflect.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.StringTokenizer;

/**
 * This class parses a JMenu or JPopupMenu from textual descriptions found in
 * a GUIResourceBundle.  The grammar is straightforward: the menu label
 * followed by a colon and a list of menu items.  Menu items that begin with
 * a '>' character are submenus.  Menu items that begin with a '-' character
 * are separators.  All other items are action names.
 **/
public class MenuParser implements ResourceParser {
    static final Class[] supportedTypes = new Class[] {
	JMenu.class, JPopupMenu.class  // This class handles two resource types
    };

    public Class[] getResourceTypes() { return supportedTypes; }

    public Object parse(GUIResourceBundle bundle, String key, Class type)
	throws java.util.MissingResourceException
    {
	// Get the string value of the key
	String menudef = bundle.getString(key);

	// Break it up into words, ignoring whitespace, colons and commas
	StringTokenizer st = new StringTokenizer(menudef, " \t:,");

	// The first word is the label of the menu
	String menuLabel = st.nextToken();

	// Create either a JMenu or JPopupMenu
	JMenu menu = null;
	JPopupMenu popup = null;
	if (type == JMenu.class) menu = new JMenu(menuLabel);
	else popup = new JPopupMenu(menuLabel);

	// Then loop through the rest of the words, creating a JMenuItem
	// for each one.  Accumulate these items in a list
	while(st.hasMoreTokens()) {
	    String item = st.nextToken();     // the next word
	    char firstchar = item.charAt(0);  // determines type of menu item
	    switch(firstchar) {
	    case '-':   // words beginning with - add a separator to the menu
		if (menu != null) menu.addSeparator();
		else popup.addSeparator();
		break;
	    case '>':   // words beginning with > are submenu names
		// strip off the > character, and recurse to parse the submenu
		item = item.substring(1);  
		// Parse a submenu and add it to the list of items
		JMenu submenu = (JMenu)parse(bundle, item, JMenu.class);
		if (menu != null) menu.add(submenu);
		else popup.add(submenu);
		break;
	    case '!': // words beginning with ! are action names
		item = item.substring(1);   // strip off the ! character
		/* falls through */         // fall through to the next case
	    default:  // By default all other words are taken as action names
		// Look up the named action and add it to the menu
		Action action = (Action)bundle.getResource(item, Action.class);
		if (menu != null) menu.add(action);
		else popup.add(action);
		break;
	    }
	}

	// Finally, return the menu or the popup menu
	if (menu != null) return menu;
	else return popup;
    }
}
