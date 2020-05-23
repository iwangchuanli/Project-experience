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
import java.io.*;

/**
 * A general-purpose SQL interpreter program.
 **/
public class ExecuteSQL {
    public static void main(String[] args) {
        Connection conn = null;  // Our JDBC connection to the database server
        try {
            String driver = null, url = null, user = "", password = "";

            // Parse all the command-line arguments
            for(int n = 0; n < args.length; n++) {
                if (args[n].equals("-d")) driver = args[++n];
                else if (args[n].equals("-u")) user = args[++n];
                else if (args[n].equals("-p")) password = args[++n];
                else if (url == null) url = args[n];
                else throw new IllegalArgumentException("Unknown argument.");
            }

            // The only required argument is the database URL.
            if (url == null) 
                throw new IllegalArgumentException("No database specified");

            // If the user specified the classname for the DB driver, load
            // that class dynamically.  This gives the driver the opportunity
            // to register itself with the DriverManager.
            if (driver != null) Class.forName(driver);

            // Now open a connection the specified database, using the
            // user-specified username and password, if any.  The driver
            // manager will try all of the DB drivers it knows about to try to
            // parse the URL and connect to the DB server.
            conn = DriverManager.getConnection(url, user, password);

            // Now create the statement object we'll use to talk to the DB
            Statement s = conn.createStatement();

            // Get a stream to read from the console
            BufferedReader in =
		new BufferedReader(new InputStreamReader(System.in));

            // Loop forever, reading the user's queries and executing them
            while(true) {
                System.out.print("sql> ");   // prompt the user
                System.out.flush();          // make the prompt appear now.
                String sql = in.readLine();  // get a line of input from user

                // Quit when the user types "quit".
                if ((sql == null) || sql.equals("quit")) break;

                // Ignore blank lines
                if (sql.length() == 0) continue;
                
                // Now, execute the user's line of SQL and display results.
                try {
                    // We don't know if this is a query or some kind of
                    // update, so we use execute() instead of executeQuery()
                    // or executeUpdate() If the return value is true, it was
                    // a query, else an update.
                    boolean status = s.execute(sql);
                
		    // Some complex SQL queries can return more than one set
		    // of results, so loop until there are no more results
                    do {
                        if (status) { // it was a query and returns a ResultSet
                            ResultSet rs = s.getResultSet();   // Get results
                            printResultsTable(rs, System.out); // Display them
                        }
                        else {
                            // If the SQL command that was executed was some
                            // kind of update rather than a query, then it
                            // doesn't return a ResultSet.  Instead, we just
                            // print the number of rows that were affected.
                            int numUpdates = s.getUpdateCount();
                            System.out.println("Ok. " + numUpdates +
					       " rows affected.");
                        }

                        // Now go see if there are even more results, and
                        // continue the results display loop if there are.
                        status = s.getMoreResults();
                    } while(status || s.getUpdateCount() != -1);
                }
                // If a SQLException is thrown, display an error message.
                // Note that SQLExceptions can have a general message and a
                // DB-specific message returned by getSQLState()
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage()+ ":" +
				       e.getSQLState());
                }
                // Each time through this loop, check to see if there were any
                // warnings.  Note that there can be a whole chain of warnings.
                finally { // print out any warnings that occurred
		    SQLWarning w;
                    for(w=conn.getWarnings(); w != null; w=w.getNextWarning())
                        System.err.println("WARNING: " + w.getMessage() + 
					   ":" + w.getSQLState());
                }
            }
        }
        // Handle exceptions that occur during argument parsing, database
        // connection setup, etc.  For SQLExceptions, print the details.
        catch (Exception e) {
            System.err.println(e);
            if (e instanceof SQLException)
                System.err.println("SQL State: " +
				   ((SQLException)e).getSQLState());
            System.err.println("Usage: java ExecuteSQL [-d <driver>] " +
			       "[-u <user>] [-p <password>] <database URL>");
        }

        // Be sure to always close the database connection when we exit,
        // whether we exit because the user types 'quit' or because of an
        // exception thrown while setting things up.  Closing this connection
        // also implicitly closes any open statements and result sets
        // associated with it.
        finally {
            try { conn.close(); } catch (Exception e) {}
        }
    }
    
    /**
     * This method attempts to output the contents of a ResultSet in a 
     * textual table.  It relies on the ResultSetMetaData class, but a fair
     * bit of the code is simple string manipulation.
     **/
    static void printResultsTable(ResultSet rs, OutputStream output) 
	throws SQLException
    {
        // Set up the output stream
        PrintWriter out = new PrintWriter(output);
	
        // Get some "meta data" (column names, etc.) about the results
        ResultSetMetaData metadata = rs.getMetaData();
        
        // Variables to hold important data about the table to be displayed
        int numcols = metadata.getColumnCount(); // how many columns
        String[] labels = new String[numcols];   // the column labels
        int[] colwidths = new int[numcols];      // the width of each
        int[] colpos = new int[numcols];         // start position of each
        int linewidth;                           // total width of table
        
        // Figure out how wide the columns are, where each one begins, 
        // how wide each row of the table will be, etc.
        linewidth = 1; // for the initial '|'.
        for(int i = 0; i < numcols; i++) {             // for each column
            colpos[i] = linewidth;                     // save its position
            labels[i] = metadata.getColumnLabel(i+1);  // get its label 
            // Get the column width.  If the db doesn't report one, guess
            // 30 characters.  Then check the length of the label, and use
            // it if it is larger than the column width
            int size = metadata.getColumnDisplaySize(i+1);
            if (size == -1) size = 30;  // Some drivers return -1...
	    if (size > 500) size = 30;  // Don't allow unreasonable sizes
            int labelsize = labels[i].length();
            if (labelsize > size) size = labelsize;   
            colwidths[i] = size + 1;              // save the column the size  
            linewidth += colwidths[i] + 2;        // increment total size
        }
        
        // Create a horizontal divider line we use in the table.
        // Also create a blank line that is the initial value of each 
        // line of the table
        StringBuffer divider = new StringBuffer(linewidth);
        StringBuffer blankline = new StringBuffer(linewidth);
        for(int i = 0; i < linewidth; i++) { 
            divider.insert(i, '-');
            blankline.insert(i, " ");
        }
        // Put special marks in the divider line at the column positions
        for(int i=0; i<numcols; i++) divider.setCharAt(colpos[i]-1,'+');
        divider.setCharAt(linewidth-1, '+');
        
        // Begin the table output with a divider line
        out.println(divider);
        
        // The next line of the table contains the column labels.
        // Begin with a blank line, and put the column names and column
        // divider characters "|" into it.  overwrite() is defined below.
        StringBuffer line = new StringBuffer(blankline.toString());
        line.setCharAt(0, '|');
        for(int i = 0; i < numcols; i++) {
            int pos = colpos[i] + 1 + (colwidths[i]-labels[i].length())/2;
            overwrite(line, pos, labels[i]);
            overwrite(line, colpos[i] + colwidths[i], " |");
        }
        
        // Then output the line of column labels and another divider
        out.println(line);
        out.println(divider);
        
        // Now, output the table data.  Loop through the ResultSet, using
        // the next() method to get the rows one at a time. Obtain the 
        // value of each column with getObject(), and output it, much as 
        // we did for the column labels above.
        while(rs.next()) {
            line = new StringBuffer(blankline.toString());
            line.setCharAt(0, '|');
            for(int i = 0; i < numcols; i++) {
                Object value = rs.getObject(i+1);
		if (value != null) 
		    overwrite(line, colpos[i] + 1, value.toString().trim());
                overwrite(line, colpos[i] + colwidths[i], " |");
            }
            out.println(line);
        }
        
        // Finally, end the table with one last divider line.
        out.println(divider);
        out.flush();
    }
    
    /** This utility method is used when printing the table of results */
    static void overwrite(StringBuffer b, int pos, String s) {
        int slen = s.length();                 // string length
        int blen = b.length();                 // buffer length
        if (pos+slen > blen) slen = blen-pos;  // does it fit?
        for(int i = 0; i < slen; i++)          // copy string into buffer
            b.setCharAt(pos+i, s.charAt(i));
    }
}
