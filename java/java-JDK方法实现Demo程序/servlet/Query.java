/* 
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.  
 */
package com.davidflanagan.examples.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

/**
 * This class demonstrates how JDBC can be used within a servlet.  It uses
 * initialization parameters (which come from the web.xml configuration file)
 * to create a single JDBC database connection, which is shared by all clients
 * of the servlet.
 ***/
public class Query extends HttpServlet {
    Connection db;  // This is the shared JDBC database connection

    public void init() throws ServletException {
	// Read initialization parameters from the web.xml file
	ServletConfig config = getServletConfig();
	String driverClassName = config.getInitParameter("driverClassName");
	String url = config.getInitParameter("url");
	String username = config.getInitParameter("username");
	String password = config.getInitParameter("password");
	    
	// Use those init params to establish a connection to the database
	// If anything goes wrong, log it, wrap the exception and re-throw it
	try {
	    Class.forName(driverClassName);
	    db = DriverManager.getConnection(url, username, password);
	}
	catch (Exception e) {
	    log("Can't create DB connection", e);
	    throw new ServletException("Query: can't initialize: " +
				       e.getMessage(), e);
	}
    }

    /** Close the database connection when the servlet is unloaded  */
    public void destroy() {
	try { db.close(); }        // Try to close the connection
	catch (SQLException e) {}  // Ignore errors; at least we tried!
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
	response.setContentType("text/html");    // We're outputting HTML
	PrintWriter out = response.getWriter();  // Where to output it to

	// Output document header and a form for entering SQL queries
	// When the form is submitted, this servlet is reloaded
	out.println("<head><title>DB Query</title></head>\n" +
		    "<body bgcolor=white><h1>DB Query</h1>\n" +
		    "<form><b>Query: </b><input name='q'>" + 
		    "<input type=submit></form>");

	// See if a query was specified in this request.
	String query = request.getParameter("q");
	if (query != null) {
	    // display the query text as a page heading
	    out.println("<h1>" + query + "</h1>");

	    // Now try to execute the query and display the results in a table
	    Statement statement = null;  // An object to execute the query
	    try {
		// Create a statement to use
		statement = db.createStatement();
		// Use it to execute the specified query, and get result set
		ResultSet results = statement.executeQuery(query);
		// Ask for extra information about the results
		ResultSetMetaData metadata = results.getMetaData();
		// How many columns are there in the results?
		int numcols = metadata.getColumnCount();

		// Begin a table, and output a header row of column names
		out.println("<table border=2><tr>");
		for(int i = 0; i < numcols; i++) 
		    out.print("<th>" + metadata.getColumnLabel(i+1) + "</th>");
		out.println("</tr>");

		// Now loop through the "rows" of the result set
		while(results.next()) {
		    // For each row, display the the values for each column
		    out.print("<tr>");
		    for(int i = 0; i < numcols; i++)
			out.print("<td>" + results.getObject(i+1) + "</td>");
		    out.println("</tr>");
		}
		out.println("</table>");  // end the table

	    }
	    catch (SQLException e) {
		// If anything goes wrong (usually a SQL error) display the
		// error to the user so they can correct it.
		out.println("SQL Error: " + e.getMessage());
	    }
	    finally { // Whatever happens, always close the Statement object
		try { statement.close(); }
		catch(Exception e) {}
	    }
	}

	// Now, display the number of hits on this page by invoking the
	// Counter servlet and including its output in this page.
	// This is done with a RequestDispatcher object.
	RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/servlet/counter");
	if (dispatcher != null) {
	    out.println("<br>Page hits:");
	    // Add a request attribute that tells the servlet what to count.
	    // Use the attribute name defined by the Counter servlet, and
	    // use the name of this class as a unique counter name.
	    request.setAttribute(Counter.ATTRIBUTE_NAME,Query.class.getName());
	    // Tell the dispatcher to invoke its servlet and include the output
	    dispatcher.include(request, response);
	}

	// Finally, end the HTML output
	out.println("</body>");
    }
}
