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
import java.io.FileInputStream;
import java.util.Properties;

/**
 * This program uses the database created by MakeAPIDB.  It opens a connection
 * to a database using the same property file used by MakeAPIDB.  Then it
 * queries that database in several interesting ways to obtain useful 
 * information about Java APIs.  It can be used to look up the fully-qualified
 * name of a member, class, or package, or it can be used to list the members
 * of a class or package.
 **/
public class LookupAPI {
    public static void main(String[] args) {
        Connection c = null;                // JDBC connection to the database
        try {
            // Some default values
            String target = null;             // The name to look up
            boolean list = false;             // List members or lookup name?
            String propfile = "APIDB.props";  // The file of db parameters
	    
            // Parse the command-line arguments
            for(int i = 0; i < args.length; i++) {
                if (args[i].equals("-l")) list = true;
                else if (args[i].equals("-p")) propfile = args[++i];
                else if (target != null) 
                    throw new IllegalArgumentException("Unexpected argument: "
						       + args[i]);
                else target = args[i];
            }
            if (target == null)
                throw new IllegalArgumentException("No target specified");

            // Now determine the values needed to set up the database
            // connection The program attempts to read a property file
            // named "APIDB.props", or optionally specified with the
            // -p argument.  This property file may contain "driver",
            // "database", "user", and "password" properties that
            // specify the necessary values for connecting to the db.
            // If the properties file does not exist, or does not
            // contain the named properties, defaults will be used.
            Properties p = new Properties();               // Empty properties
            try { p.load(new FileInputStream(propfile)); } // Try to load props
            catch (Exception e) {}

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

            // Load the database driver
            Class.forName(driver);

            // And set up a connection to the specified database
            c = DriverManager.getConnection(database, user, password);

            // Tell it we will not do any updates.
	    // This hint may improve efficiency.
            c.setReadOnly(true);

            // If the "-l" option was given, then list the members of
            // the named package or class.  Otherwise, lookup all
            // matches for the specified member, class, or package.
            if (list) list(c, target);
            else lookup(c, target);
        }
        // If anything goes wrong, print the exception and a usage message.  If
        // a SQLException is thrown, display the state message it includes.
        catch (Exception e) {
            System.out.println(e);
            if (e instanceof SQLException) 
                System.out.println(((SQLException) e).getSQLState());
            System.out.println("Usage: java LookupAPI [-l] [-p <propfile>] " +
			       "target");
        }
        // Always close the DB connection when we're done with it.
        finally {
            try { c.close(); } catch (Exception e) {}
        }
    }
    
    /**
     * This method looks up all matches for the specified target string in the
     * database.  First, it prints the full name of any members by that name.
     * Then it prints the full name of any classes by that name.  Then it 
     * prints the name of any packages that contain the specified name
     **/
    public static void lookup(Connection c, String target) throws SQLException
    {
        // Create the Statement object we'll use to query the database
        Statement s = c.createStatement();
        
        // Go find all class members with the specified name
        s.executeQuery("SELECT DISTINCT " + 
		       "package.name, class.name, member.name, member.isField"+
		       " FROM package, class, member" + 
		       " WHERE member.name='" + target + "'" +
		       "   AND member.classId=class.id " +
		       "   AND class.packageId=package.id");
        
        // Loop through the results, and print them out (if there are any).
        ResultSet r = s.getResultSet();
        while(r.next()) {
            String pkg = r.getString(1);       // package name
            String cls = r.getString(2);       // class name
            String member = r.getString(3);    // member name
            boolean isField = r.getBoolean(4); // is the member a field?
            // Display this match
            System.out.println(pkg + "." + cls + "." + member + 
			       (isField?"":"()"));
        }
        
        // Now look for a class with the specified name
        s.executeQuery("SELECT package.name, class.name " + 
		       "FROM package, class " + 
		       "WHERE class.name='" + target + "' " +
		       "  AND class.packageId=package.id");
        // Loop through the results and print them out
        r = s.getResultSet();
        while(r.next()) System.out.println(r.getString(1) + "." +
					   r.getString(2));
        
        // Finally, look for a package that matches a part of of the name.
        // Note the use of the SQL LIKE keyword and % wildcard characters
        s.executeQuery("SELECT name FROM package " + 
		       "WHERE name='" + target + "'" +
		       "   OR name LIKE '%." + target + ".%' " +
		       "   OR name LIKE '" + target + ".%' " +
		       "   OR name LIKE '%." + target + "'");
        // Loop through the results and print them out
        r = s.getResultSet();
        while(r.next()) System.out.println(r.getString(1));
	
        // Finally, close the Statement object
        s.close();
    }
    
    /**
     * This method looks for classes with the specified name, or packages
     * that contain the specified name.  For each class it finds, it displays
     * all methods and fields of the class.  For each package it finds, it
     * displays all classes in the package.
     **/
    public static void list(Connection conn, String target) throws SQLException
    {
        // Create two Statement objects to query the database with
        Statement s = conn.createStatement();
        Statement t = conn.createStatement();
	
        // Look for a class with the given name
        s.executeQuery("SELECT package.name, class.name " + 
		       "FROM package, class " + 
		       "WHERE class.name='" + target + "' " +
		       "  AND class.packageId=package.id");
        // Loop through all matches
        ResultSet r = s.getResultSet();
        while(r.next()) {
            String p = r.getString(1);  // package name
            String c = r.getString(2);  // class name
            // Print out the matching class name
            System.out.println("class " + p + "." + c + " {");
            
            // Now query all members of the class
            t.executeQuery("SELECT DISTINCT member.name, member.isField " + 
			   "FROM package, class, member " + 
			   "WHERE package.name = '" + p + "' " +
			   "  AND class.name = '" + c + "' " + 
			   "  AND member.classId=class.id " +
			   "  AND class.packageId=package.id " +
			   "ORDER BY member.isField, member.name");
	    
            // Loop through the ordered list of all members, and print them out
            ResultSet r2 = t.getResultSet();
            while(r2.next()) {
                String m = r2.getString(1);
                int isField = r2.getInt(2);
                System.out.println("  " + m + ((isField == 1)?"":"()"));
            }
            // End the class listing
            System.out.println("}");
        }
        
        // Now go look for a package that matches the specified name
        s.executeQuery("SELECT name FROM package " +
		       "WHERE name='" + target + "'" +
		       "   OR name LIKE '%." + target + ".%' " +
		       "   OR name LIKE '" + target + ".%' " +
		       "   OR name LIKE '%." + target + "'");
        // Loop through any matching packages
        r = s.getResultSet();
        while(r.next()) {
            // Display the name of the package
            String p = r.getString(1);
            System.out.println("Package " + p + ": ");
            
            // Get a list of all classes and interfaces in the package
            t.executeQuery("SELECT class.name FROM package, class " + 
			   "WHERE package.name='" + p + "' " +
			   "  AND class.packageId=package.id " +
			   "ORDER BY class.name");
            // Loop through the list and print them out.
            ResultSet r2 = t.getResultSet();
            while(r2.next()) System.out.println("  " + r2.getString(1));
        }
	
        // Finally, close both Statement objects
        s.close(); t.close();
    }
}
