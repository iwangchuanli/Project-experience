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
import javax.swing.*;
import javax.swing.table.*;   // TableModel and other JTable-related classes
import java.beans.*;          // For JavaBean introspection
import java.util.*;           // For array sorting 

/**
 * This class is a JTable subclass that displays a table of the JavaBeans
 * properties of any specified class.
 **/
public class PropertyTable extends JTable {
    /** This main method allows the class to be demonstrated  standalone */
    public static void main(String[] args) {
	// Specify the name of the class as a command-line argument
	Class beanClass = null;
 	try {
	    // Use reflection to get the Class from the classname
	    beanClass = Class.forName(args[0]);
	}
	catch (Exception e) {  // Report errors
	    System.out.println("Can't find specified class: "+e.getMessage());
	    System.out.println("Usage: java TableDemo <JavaBean class name>");
	    System.exit(0);
	}
	
	// Create a table to display the properties of the specified class
	JTable table = new PropertyTable(beanClass);
	
	// Then put the table in a scrolling window, put the scrolling 
	// window into a frame, and pop it all up on to the screen
	JScrollPane scrollpane = new JScrollPane(table);
	JFrame frame = new JFrame("Properties of JavaBean: " + args[0]);
	frame.getContentPane().add(scrollpane);
	frame.setSize(500, 400);
	frame.setVisible(true);
    }

    /**
     * This constructor method specifies what data the table will display
     * (the table model) and uses the TableColumnModel to customize the
     * way that the table displays it.  The hard work is done by the
     * TableModel implementation below.
     **/
    public PropertyTable(Class beanClass) {
	// Set the data model for this table
	try {
	    setModel(new JavaBeanPropertyTableModel(beanClass));
	}
	catch (IntrospectionException e) {
	    System.err.println("WARNING: can't introspect: " + beanClass);
	}
	
	// Tweak the appearance of the table by manipulating its column model
	TableColumnModel colmodel = getColumnModel();
	
	// Set column widths
	colmodel.getColumn(0).setPreferredWidth(125);
	colmodel.getColumn(1).setPreferredWidth(200);
	colmodel.getColumn(2).setPreferredWidth(75);
	colmodel.getColumn(3).setPreferredWidth(50);
	
	// Right justify the text in the first column
	TableColumn namecol = colmodel.getColumn(0);
	DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	renderer.setHorizontalAlignment(SwingConstants.RIGHT);
	namecol.setCellRenderer(renderer);
    }

    /**
     * This class implements TableModel and represents JavaBeans property data
     * in a way that the JTable component can display.  If you've got some
     * type of tabular data to display, implement a TableModel class to
     * describe that data, and the JTable component will be able to display it.
     **/
    static class JavaBeanPropertyTableModel extends AbstractTableModel {
	PropertyDescriptor[] properties;  // The properties to display

	/**
	 * The constructor: use the JavaBeans introspector mechanism to get 
	 * information about all the properties of a bean.  Once we've got
	 * this information, the other methods will interpret it for JTable.
	 **/
	public JavaBeanPropertyTableModel(Class beanClass)
	    throws java.beans.IntrospectionException
	{
	    // Use the introspector class to get "bean info" about the class.
	    BeanInfo beaninfo = Introspector.getBeanInfo(beanClass);
	    // Get the property descriptors from that BeanInfo class
	    properties = beaninfo.getPropertyDescriptors();
	    // Now do a case-insensitive sort by property name
	    // The anonymous Comparator implementation specifies how to 
	    // sort PropertyDescriptor objects by name
	    Arrays.sort(properties, new Comparator() {
		    public int compare(Object p, Object q) {
			PropertyDescriptor a = (PropertyDescriptor) p;
			PropertyDescriptor b = (PropertyDescriptor) q;
			return a.getName().compareToIgnoreCase(b.getName());
		    }
		    public boolean equals(Object o) { return o == this; }
		});
	}

	// These are the names of the columns represented by this TableModel
	static final String[] columnNames = new String[] {
	    "Name", "Type", "Access", "Bound"
	};

	// These are the types of the columns represented by this TableModel
	static final Class[] columnTypes = new Class[] {
	    String.class, Class.class, String.class, Boolean.class
	};

	// These simple methods return basic information about the table
	public int getColumnCount() { return columnNames.length; }
	public int getRowCount() { return properties.length; }
	public String getColumnName(int column) { return columnNames[column]; }
	public Class getColumnClass(int column) { return columnTypes[column]; }

	/**
	 * This method returns the value that appears at the specified row and
	 * column of the table
	 **/
	public Object getValueAt(int row, int column) {
	    PropertyDescriptor prop = properties[row];
	    switch(column) {
	    case 0: return prop.getName();
	    case 1: return prop.getPropertyType();
	    case 2: return getAccessType(prop);
	    case 3: return new Boolean(prop.isBound());
	    default: return null;
	    }
	}

	// A helper method called from getValueAt() above
	String getAccessType(PropertyDescriptor prop) {
	    java.lang.reflect.Method reader = prop.getReadMethod();
	    java.lang.reflect.Method writer = prop.getWriteMethod();
	    if ((reader != null) && (writer != null)) return "Read/Write";
	    else if (reader != null) return "Read-Only";
	    else if (writer != null) return "Write-Only";
	    else return "No Access";  // should never happen
	}
    }
}
