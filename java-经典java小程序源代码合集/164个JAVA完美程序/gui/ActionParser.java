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
import java.util.*;

/**
 * This class parses an Action object from a GUIResourceBundle.
 * The specified key is used to look up the Command string for the action.
 * The key is also used as a prefix for other resource names that specify
 * other attributes (such as the label and icon) associated with the Action.
 * An action named "zoomOut" might be specified like this:
 * 
 *      zoomOut: zoom(0.5);
 *      zoomOut.label: Zoom Out
 *      zoomOut.description: Zoom out by a factor of 2
 * 
 * Because Action objects are often reused by an application (for example
 * in a toolbar and a menu system, this ResourceParser caches the Action
 * objects it returns.  By sharing Action objects, you can disable and enable
 * an action and that change will affect the entire GUI.
 **/
public class ActionParser implements ResourceParser {
    static final Class[] supportedTypes = new Class[] { Action.class };
    public Class[] getResourceTypes() { return supportedTypes; }

    HashMap bundleToCacheMap = new HashMap();

    public Object parse(GUIResourceBundle bundle, String key, Class type)
	throws java.util.MissingResourceException
    {
	// Look up the Action cache associated with this bundle
	HashMap cache = (HashMap) bundleToCacheMap.get(bundle);
	if (cache == null) {  // If there isn't one, create one and save it
	    cache = new HashMap();
	    bundleToCacheMap.put(bundle, cache);
	}
	// Now look up the Action associated with the key in the cache.
	Action action = (Action) cache.get(key);
	// If we found a cached action, return it.
	if (action != null) return action;

	// If there was no cached action create one.  The command is
	// the only required resource.  It will throw an exception if
	// missing or malformed.
	Command command = (Command) bundle.getResource(key, Command.class);

	// The remaining calls all supply default values, so they will not
	// throw exceptions, even if ResourceParsers haven't been registered
	// for types like Icon and KeyStroke
	String label = bundle.getString(key + ".label", null);
	Icon icon = (Icon) bundle.getResource(key + ".icon", Icon.class, null);
	String tooltip = bundle.getString(key + ".description", null);
	KeyStroke accelerator = 
	    (KeyStroke) bundle.getResource(key + ".accelerator", 
					   KeyStroke.class, null);
	int mnemonic = bundle.getInt(key + ".mnemonic", KeyEvent.VK_UNDEFINED);
	boolean enabled = bundle.getBoolean(key + ".enabled", true);

	// Create a CommandAction object with these values
	action = new CommandAction(command, label, icon, tooltip,
				   accelerator, mnemonic, enabled);

	// Save it in the cache, then return it
	cache.put(key, action);
	return action;
    }
}
