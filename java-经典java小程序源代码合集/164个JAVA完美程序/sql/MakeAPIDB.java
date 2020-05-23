/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.sql;
import java.sql.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;

/**
 * This class is a standalone program that reads a list of classes and 
 * builds a database of packages, classes, and class fields and methods.
 **/
public class MakeAPIDB {
    public static void main(String args[]) {
        Connection c = null;       // The connection to the database
        try { 
            // Read the classes to index from a file specified by args[0]
            ArrayList classnames = new ArrayList();
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String name;
            while((name = in.readLine()) != null) classnames.add(name);
            
            // Now determine the values needed to set up the database
            // connection The program attempts to read a property file named
            // "APIDB.props", or optionally specified by args[1].  This
            // property file (if any) may contain "driver", "database", "user",
            // and "password" properties that specify the necessary values for
            // connecting to the db.  If the properties file does not exist, or
            // does not contain the named properties, defaults will be used.
            Properties p = new Properties();          // Empty properties
            try {                                     
		p.load(new FileInputStream(args[1])); // Try to load properties
	    } 
            catch (Exception e1) { 
                try { p.load(new FileInputStream("APIDB.props")); }
                catch (Exception e2) {}
            } 

            // Read values from Properties file
            String driver = p.getProperty("driver");
            String database = p.getProperty("database");
            String user = p.getProperty("user", "");
            String password = p.getProperty("password", "");

	    // The driver and database properties are mandatory
	    if (driver == null) 
		throw new IllegalArgumentException("No driver specified!");
	    if (database == null) 
		throw new IllegalArgumentException("No database specified!");

            // Load the driver.  It registers itself with DriverManager.
            Class.forName(driver);

            // And set up a connection to the specified database
            c = DriverManager.getConnection(database, user, password);
            
            // Create three new tables for our data
            // The package table contains a package id and a package name.
            // The class table contains a class id, a package id, and a name.
            // The member table contains a class id, a member name, and an bit
            // that indicates whether the class member is a field or a method.
            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE package " + 
			    "(id INT, name VARCHAR(80))");
            s.executeUpdate("CREATE TABLE class " + 
			    "(id INT, packageId INT, name VARCHAR(48))");
            s.executeUpdate("CREATE TABLE member " + 
			    "(classId INT, name VARCHAR(48), isField BIT)");
            
            // Prepare some statements that will be used to insert records into
            // these three tables.
            insertpackage =
		c.prepareStatement("INSERT INTO package VALUES(?,?)");
            insertclass =
		c.prepareStatement("INSERT INTO class VALUES(?,?,?)");
            insertmember =
		c.prepareStatement("INSERT INTO member VALUES(?,?,?)");

            // Now loop through the list of classes and use reflection
	    // to store them all in the tables
	    int numclasses = classnames.size();
            for(int i = 0; i < numclasses; i++) {
		try {
		    storeClass((String)classnames.get(i));
		}
		catch(ClassNotFoundException e) {
		    System.out.println("WARNING: class not found: " +
				       classnames.get(i) + "; SKIPPING");
		}
	    }
        }
        catch (Exception e) {
            System.err.println(e);
            if (e instanceof SQLException)
                System.err.println("SQLState: " +
				   ((SQLException)e).getSQLState());
            System.err.println("Usage: java MakeAPIDB " + 
			       "<classlistfile> <propfile>");
        }
        // When we're done, close the connection to the database
        finally { try { c.close(); } catch (Exception e) {} }
    }

    /** 
     * This hash table records the mapping between package names and package
     * id.  This is the only one we need to store temporarily.  The others are
     * stored in the db and don't have to be looked up by this program
     **/
    static Map package_to_id = new HashMap();

    // Counters for the package and class identifier columns
    static int packageId = 0, classId = 0;
    
    // Some prepared SQL statements for use in inserting
    // new values into the tables.  Initialized in main() above.
    static PreparedStatement insertpackage, insertclass, insertmember;

    /**
     * Given a fully-qualified classname, this method stores the package name
     * in the package table (if it is not already there), stores the class name
     * in the class table, and then uses the Java Reflection API to look up all
     * methods and fields of the class, and stores those in the member table.
     **/
    public static void storeClass(String name) 
	throws SQLException, ClassNotFoundException
    {
        String packagename, classname;
	
        // Dynamically load the class.
        Class c = Class.forName(name);
	
        // Display output so the user knows that the program is progressing
        System.out.println("Storing data for: " + name);
	
        // Figure out the packagename and the classname
        int pos = name.lastIndexOf('.');
        if (pos == -1) {
            packagename = "";
            classname = name;
        }
        else {
            packagename = name.substring(0,pos);
            classname = name.substring(pos+1);
        }
	
        // Figure out what the package id is.  If there is one, then this
        // package has already been stored in the database.  Otherwise, assign
        // an id, and store it and the packagename in the db.
        Integer pid;
        pid = (Integer)package_to_id.get(packagename);  // Check hashtable
        if (pid == null) {
            pid = new Integer(++packageId);          // Assign an id
            package_to_id.put(packagename, pid);     // Remember it
            insertpackage.setInt(1, packageId);      // Set statement args
            insertpackage.setString(2, packagename);  
            insertpackage.executeUpdate();           // Insert into package db
        }

        // Now, store the classname in the class table of the database.
        // This record includes the package id, so that the class is linked to 
        // the package that contains it.  To store the class, we set arguments
        // to the PreparedStatement, then execute that statement
        insertclass.setInt(1, ++classId);       // Set class identifier
        insertclass.setInt(2, pid.intValue());  // Set package identifier
        insertclass.setString(3, classname);    // Set class name
        insertclass.executeUpdate();            // Insert the class record

        // Now, get a list of all non-private methods of the class, and
        // insert those into the "members" table of the database.  Each
        // record includes the class id of the containing class, and also
        // a value that indicates that these are methods, not fields.
        Method[] methods = c.getDeclaredMethods();   // Get a list of methods
        for(int i = 0; i < methods.length; i++) {    // For all non-private
            if (Modifier.isPrivate(methods[i].getModifiers())) continue;
            insertmember.setInt(1, classId);         // Set the class id
            insertmember.setString(2, methods[i].getName()); // Set method name
            insertmember.setBoolean(3, false);       // It is not a field
            insertmember.executeUpdate();            // Insert into db
        }

        // Do the same thing for the non-private fields of the class
        Field[] fields = c.getDeclaredFields();    // Get a list of fields
        for(int i = 0; i < fields.length; i++) {   // For each non-private
            if (Modifier.isPrivate(fields[i].getModifiers())) continue;
            insertmember.setInt(1, classId);       // Set the class id
            insertmember.setString(2, fields[i].getName()); // Set field name
            insertmember.setBoolean(3, true);      // It is a field
            insertmember.executeUpdate();          // Insert the record
        }
    }
}
