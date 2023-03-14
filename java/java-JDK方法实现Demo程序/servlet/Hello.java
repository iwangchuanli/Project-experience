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
import javax.servlet.*;         // Basic servlet classes and interfaces
import javax.servlet.http.*;    // HTTP specific servlet stuff
import java.io.*;               // Servlets do IO and throw IOExceptions

/**
 * This simple servlet greets the user.  It looks in the request and session
 * objects in an attempt to greet the user by name.
 **/
public class Hello extends HttpServlet {
    // This method is invoked when the servlet is the subject of an HTTP GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
	// See if the username is specified in the request
	String name = request.getParameter("username");

	// If not, look in the session object.  The web server or servlet
	// container performs session tracking automatically for the servlet,
	// and associates a HttpSession object with each session.
	if (name == null) 
	    name = (String)request.getSession().getAttribute("username");

	// If the username is not found in either place, use a default name.
	if (name == null) name = "World";

	// Specify the type of output we produce.  If this servlet is
	// included from within another servlet or JSP page, this setting
	// will be ignored.
	response.setContentType("text/html");

	// Get an stream that we can write the output to
	PrintWriter out = response.getWriter();

	// And, finally, do our output.
	out.println("Hello " + name + "!");
    }

    // This method is invoked when the servlet is the subject of an HTTP POST.
    // It calls the doGet() method so that this servlet works correctly
    // with either type of request.
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws IOException
    {
	doGet(request, response);
    }
}
