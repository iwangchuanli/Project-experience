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
import java.util.Properties;

/**
 * This class uses the DatabaseMetaData class to obtain information about
 * the database, the JDBC driver, and the tables in the database, or about
 * the columns of a named table.
 **/
public class GetDBInfo {
    public static void main(String[] args) {
        Connection c = null;  // The JDBC connection to the database server
        try {
            // Look for the properties file DB.props in the same directory as
            // this program.  It will contain default values for the various
            // parameters needed to connect to a database
            Properties p = new Properties();
            try { p.load(GetDBInfo.class.getResourceAsStream("DB.props")); }
            catch (Exception e) {}
            
            // Get default values from the properties file
            String driver = p.getProperty("driver");     // Driver class name
            String server = p.getProperty("server", ""); // JDBC URL for server
            String user = p.getProperty("user", "");     // db user name
            String password = p.getProperty("password", ""); // db password
	    
            // These variables don't have defaults
            String database = null; // The db name (appended to server URL)
            String table = null;    // The optional name of a table in the db
            
            // Parse the command-line args to override the default values above
            for(int i = 0; i < args.length; i++) {
                if (args[i].equals("-d")) driver = args[++i];     //-d <driver>
                else if (args[i].equals("-s")) server = args[++i];//-s <server>
                else if (args[i].equals("-u")) user = args[++i];  //-u <user>
                else if (args[i].equals("-p")) password = args[++i]; 
                else if (database == null) database = args[i];    // <dbname>
                else if (table == null) table = args[i];          // <table>
                else throw new IllegalArgumentException("Unknown argument: "
							+args[i]);
            }

            // Make sure that at least a server or a database were specified. 
            // If not, we have no idea what to connect to, and cannot continue.
            if ((server.length() == 0) && (database.length() == 0))
                throw new IllegalArgumentException("No database specified.");

            // Load the db driver, if any was specified.
            if (driver != null) Class.forName(driver);
            
            // Now attempt to open a connection to the specified database on
            // the specified server, using the specified name and password
            c = DriverManager.getConnection(server+database, user, password);

            // Get the DatabaseMetaData object for the connection.  This is the
            // object that will return us all the data we're interested in here
            DatabaseMetaData md = c.getMetaData();

            // Display information about the server, the driver, etc.
            System.out.println("DBMS: " + md.getDatabaseProductName() + 
			       " " + md.getDatabaseProductVersion());
            System.out.println("JDBC Driver: " + md.getDriverName() + 
			       " " + md.getDriverVersion());
            System.out.println("Database: " + md.getURL());
            System.out.println("User: " + md.getUserName());

            // Now, if the user did not specify a table, then display a list of
            // all tables defined in the named database.  Note that tables are
            // returned in a ResultSet, just like query results are.
            if (table == null) {
                System.out.println("Tables:");
                ResultSet r = md.getTables("", "", "%", null);
                while(r.next()) System.out.println("\t" + r.getString(3));
            }

            // Otherwise, list all columns of the specified table.
            // Again, information about the columns is returned in a ResultSet
            else {
                System.out.println("Columns of " + table + ": ");
                ResultSet r = md.getColumns("", "", table, "%");
                while(r.next()) 
                    System.out.println("\t" + r.getString(4) + " : " +
				       r.getString(6));
            }
        }
        // Print an error message if anything goes wrong.
        catch (Exception e) {
            System.err.println(e);
            if (e instanceof SQLException)
                System.err.println(((SQLException)e).getSQLState());
            System.err.println("Usage: java GetDBInfo [-d <driver] " +
			       "[-s <dbserver>]\n" +
			       "\t[-u <username>] [-p <password>] <dbname>");
        }
        // Always remember to close the Connection object when we're done!
        finally { 
            try { c.close(); } catch (Exception e) {}
        }
    }
}
