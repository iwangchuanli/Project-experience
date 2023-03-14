/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.i18n;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

/** A convenience class to automatically create localized menu panes */
public class SimpleMenu {
    /** The convenience method that creates menu panes */
    public static JMenu create(ResourceBundle bundle,
			       String menuname, String[] itemnames,
			       ActionListener listener)
    {
        // Get the menu title from the bundle.  Use name as default label.
        String menulabel;
        try { menulabel = bundle.getString(menuname + ".label"); }
        catch(MissingResourceException e) { menulabel = menuname; }
	
        // Create the menu pane.
        JMenu menu = new JMenu(menulabel); 
	
        // For each named item in the menu.
        for(int i = 0; i < itemnames.length; i++) {
            // Look up the label for the item, using name as default.
            String itemlabel;
            try {
		itemlabel =
		    bundle.getString(menuname+"."+itemnames[i]+".label");
	    }
            catch (MissingResourceException e) { itemlabel = itemnames[i]; }
	    
	    JMenuItem item = new JMenuItem(itemlabel);
			
            // Look up an accelerator for the menu item
            try {
		String acceleratorText =
		    bundle.getString(menuname+"."+itemnames[i]+".accelerator");
		item.setAccelerator(KeyStroke.getKeyStroke(acceleratorText));
	    }
            catch (MissingResourceException e) {}
	    
            // Register an action listener and command for the item.
            if (listener != null) {
                item.addActionListener(listener);
                item.setActionCommand(itemnames[i]);
            }
	    
            // Add the item to the menu.
            menu.add(item);
        }
	
        // Return the automatically created localized menu.
        return menu;
    }
    
    /** A simple test program for the above code */
    public static void main(String[] args) {
	// Get the locale: default, or specified on command-line
	Locale locale;
	if (args.length == 2) locale = new Locale(args[0], args[1]);
	else locale = Locale.getDefault();

	// Get the resource bundle for that Locale.  This will throw an
	// (unchecked) MissingResourceException if no bundle is found.
	ResourceBundle bundle = 
	    ResourceBundle.getBundle("com.davidflanagan.examples.i18n.Menus",
				     locale);
	
	// Create a simple GUI window to display the menu with
        final JFrame f = new JFrame("SimpleMenu: " +   // Window title
			         locale.getDisplayName(Locale.getDefault()));
        JMenuBar menubar = new JMenuBar();         // Create a menubar.
        f.setJMenuBar(menubar);                    // Add menubar to window

	// Define an action listener for that our menu will use.
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    String s = e.getActionCommand();
		    Component c = f.getContentPane();
		    if (s.equals("red")) c.setBackground(Color.red);
		    else if (s.equals("green"))	c.setBackground(Color.green);
		    else if (s.equals("blue")) c.setBackground(Color.blue);
		}
	    };

        // Now create a menu using our convenience routine with the resource
	// bundle and action listener we've created
        JMenu menu = SimpleMenu.create(bundle, "colors",
				       new String[] {"red", "green", "blue"},
				       listener);
	
	// Finally add the menu to the GUI, and pop it up
        menubar.add(menu);           // Add the menu to the menubar
        f.setSize(300, 150);         // Set the window size.
	f.setVisible(true);          // Pop the window up.
    }
}
