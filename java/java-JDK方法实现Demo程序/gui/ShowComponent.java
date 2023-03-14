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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.Vector;

/**
 * This class is a program that uses reflection and JavaBeans introspection to
 * create a set of named components, set named properties on those components,
 * and display them.  It allows the user to view the components using any
 * installed look-and-feel.  It is intended as a simple way to experiment with
 * AWT and Swing components, and to view a number of the other examples
 * developed in this chapter.  It also demonstrates frames, menus, and the
 * JTabbedPane component.
 **/
public class ShowComponent {
    // The main program
    public static void main(String[] args) {
	// Process the command line to get the components to display
	Vector components = getComponentsFromArgs(args);

	// Create a frame (a window) to display them in
	JFrame frame = new JFrame("ShowComponent");

	// Handle window close requests by exiting the VM
	frame.addWindowListener(new WindowAdapter() { // Anonymous inner class
		public void windowClosing(WindowEvent e) { System.exit(0); }
	    });
	
	// Set up a menu system that allows the user to select the 
	// look-and-feel of the component from a list of installed PLAFs
	JMenuBar menubar = new JMenuBar();      // Create a menubar
	frame.setJMenuBar(menubar);             // Tell the frame to display it
	JMenu plafmenu = createPlafMenu(frame); // Create a menu
	menubar.add(plafmenu);                  // Add the menu to the menubar

	// Create a JTabbedPane to display each of the components
	JTabbedPane pane = new JTabbedPane();

	// Now add each component as a tab of the tabbed pane
	// Use the unqualified component classname as the tab text
	for(int i = 0; i < components.size(); i++) {
	    Component c = (Component)components.elementAt(i);
	    String classname = c.getClass().getName();
	    String tabname = classname.substring(classname.lastIndexOf('.')+1);
	    pane.addTab(tabname, c);
	}

	// Add the tabbed pane to the frame.  Note the call to getContentPane()
	// This is required for JFrame, but not for most Swing components
	frame.getContentPane().add(pane);

	// Set the frame size and pop it up
	frame.pack();              // Make frame as big as its kids need 
	frame.setVisible(true);    // Make the frame visible on the screen

	// The main() method exits now but the Java VM keeps running because
	// all AWT programs automatically start an event-handling thread.
    }

    /**
     * This static method queries the system to find out what Pluggable
     * Look-and-Feel (PLAF) implementations are available.  Then it creates a
     * JMenu component that lists each of the implementations by name and
     * allows the user to select one of them using JRadioButtonMenuItem
     * components.  When the user selects one, the selected menu item
     * traverses the component hierarchy and tells all components to use the
     * new PLAF.
     **/
    public static JMenu createPlafMenu(final JFrame frame) {
	// Create the menu
	JMenu plafmenu = new JMenu("Look and Feel");

	// Create an object used for radio button mutual exclusion
	ButtonGroup radiogroup = new ButtonGroup();  

	// Look up the available look and feels
	UIManager.LookAndFeelInfo[] plafs =
	    UIManager.getInstalledLookAndFeels();

	// Loop through the plafs, and add a menu item for each one
	for(int i = 0; i < plafs.length; i++) {
	    String plafName = plafs[i].getName();
	    final String plafClassName = plafs[i].getClassName();

	    // Create the menu item
	    JMenuItem item = plafmenu.add(new JRadioButtonMenuItem(plafName));
	    
	    // Tell the menu item what to do when it is selected
	    item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			try {
			    // Set the new look and feel
			    UIManager.setLookAndFeel(plafClassName);
			    // Tell each component to change its look-and-feel
			    SwingUtilities.updateComponentTreeUI(frame);
			    // Tell the frame to resize itself to the its
			    // children's new desired sizes
			    frame.pack();
			}
			catch(Exception ex) { System.err.println(ex); }
		    }

		});

	    // Only allow one menu item to be selected at once
	    radiogroup.add(item);  
	}
	return plafmenu;
    }

    /**
     * This method loops through the command line arguments looking for
     * class names of components to create and property settings for those
     * components in the form name=value.  This method demonstrates 
     * reflection and JavaBeans introspection as they can be applied to
     * dynamically created GUIs
     **/
    public static Vector getComponentsFromArgs(String[] args) {
	Vector components = new Vector();       // List of components to return
	Component component = null;             // The current component 
	PropertyDescriptor[] properties = null; // Properties of the component
	Object[] methodArgs = new Object[1];    // We'll use this below

      nextarg:  // This is a labeled loop
	for(int i = 0; i < args.length; i++) {  // Loop through all arguments
	    // If the argument does not contain an equal sign, then it is
	    // a component class name.  Otherwise it is a property setting
	    int equalsPos = args[i].indexOf('=');
	    if (equalsPos == -1) {  // Its the name of a component
		try {
		    // Load the named component class
		    Class componentClass = Class.forName(args[i]);
		    // Instantiate it to create the component instance
		    component = (Component)componentClass.newInstance();
		    // Use JavaBeans to introspect the component
		    // And get the list of properties it supports
		    BeanInfo componentBeanInfo =
			Introspector.getBeanInfo(componentClass);
		    properties = componentBeanInfo.getPropertyDescriptors();
		}
		catch(Exception e) {  
		    // If any step failed, print an error and exit
		    System.out.println("Can't load, instantiate, " +
				       "or introspect: " + args[i]);
		    System.exit(1);
		}

		// If we succeeded, store the component in the vector
		components.addElement(component);
	    }
	    else { // The arg is a name=value property specification 
		String name =args[i].substring(0, equalsPos); // property name
		String value =args[i].substring(equalsPos+1); // property value

		// If we don't have a component to set this property on, skip!
		if (component == null) continue nextarg;

		// Now look through the properties descriptors for this
		// component to find one with the same name.
		for(int p = 0; p < properties.length; p++) {
		    if (properties[p].getName().equals(name)) {
			// Okay, we found a property of the right name.
			// Now get its type, and the setter method
			Class type = properties[p].getPropertyType();
			Method setter = properties[p].getWriteMethod();

			// Check if property is read-only!
			if (setter == null) {  
			    System.err.println("Property " + name+
					       " is read-only");
			    continue nextarg;  // continue with next argument
			}

			// Try to convert the property value to the right type
			// We support a small set of common property types here
			// Store the converted value in an Object[] so it can
			// be easily passed when we invoke the property setter
			try {
			    if (type == String.class) { // no conversion needed
				methodArgs[0] = value;   
			    }
			    else if (type == int.class) {     // String to int
				methodArgs[0] = Integer.valueOf(value);
			    }
			    else if (type == boolean.class) { // to boolean
				methodArgs[0] = Boolean.valueOf(value);
			    }
			    else if (type == Color.class) {   // to Color
				methodArgs[0] = Color.decode(value);
			    }
			    else if (type == Font.class) {    // String to Font
				methodArgs[0] = Font.decode(value);
			    }
			    else {
				// If we can't convert, ignore the property
				System.err.println("Property " + name + 
						   " is of unsupported type " +
						   type.getName());
				continue nextarg;
			    }
			}
			catch (Exception e) {
			    // If conversion failed, continue with the next arg
			    System.err.println("Can't convert  '" + value +
					       "' to type " + type.getName() +
					       " for property " + name);
			    continue nextarg;
			}

			// Finally, use reflection to invoke the property
			// setter method of the component we created, and pass
			// in the converted property value.
			try { setter.invoke(component, methodArgs); }
			catch (Exception e) {
			    System.err.println("Can't set property: " + name);
			}

			// Now go on to next command-line arg
			continue nextarg;  
		    }
		}

		// If we get here, we didn't find the named property
		System.err.println("Warning: No such property: " + name);
	    }
	}

	return components;
    }
}
