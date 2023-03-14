<%-- 
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
--%>
<%--login.jsp
  The next two lines are JSP directives.  The page directive tells the JSP
  compiler that this page contains Java (instead of JavaScript e.g.) code and
  outputs HTML (instead of XML, e.g.). The second directive tells the JSP
  compiler that this page uses a "tag library" with the specified unique
  identifier and whose tags are prefixed (on this page) by the word "decor".
--%>
<%@page language='java'	contentType='text/html'%>
<%@taglib uri='http://www.davidflanagan.com/tlds/decor_0_1.tld'
	  prefix='decor'%>
	
<%--
  The code below is in a <%!...%> declaration block.  When this JSP page
  is compiled to a servlet, this code is used to define members of the
  Servlet class.
--%>

<%!  // Begin declaration block

// This method does very simple password verification. In a real application,
// this method would probably check a database of passwords.
public boolean verify(String username, String password) {
    // Accept any username as long as the password is "java"
    return ((username!=null) && (password!=null) && password.equals("java"));
}

%>  <%-- End declaration block --%>

<%--
  The next block of code is between <% and %>, which mark it as a "scriptlet".
  When the JSP page is compiled, this code becomes part of the service()
  method of the servlet.  Scriptlet blocks are intermixed with HTML tags which
  are also compiled into the service() method and are output literally by the
  servlet.  Notice how this scriptlet ends in the middle of an else block.
  The HTML tags that follow it form part of the else{} block, and the block is
  closed in a scriptlet that comes later in the file.
--%>

<%  // Begin scriptlet
// This request parameter is required.  It specifies what should be
// displayed if the login attempt is successful
String nextPage = request.getParameter("nextpage");

// This request parameter specifies a title for the login page
String title = request.getParameter("title");
if (title == null) title = "Please Log In"; // If not specified, use a default

// Look for username and password parameters in the request
String username = request.getParameter("username");
String password = request.getParameter("password");

// If the username and password are defined and  valid, then store 
// the username in the session, and redirect to the specified page
// We do this without displaying any content of our own.
if ((username != null) && (password != null) && verify(username, password)) {
    session.setAttribute("username", username);
    response.sendRedirect(nextPage);
}
else {
    // Otherwise, we're going to have to display the login screen.
    // If the username and password properties are totally undefined, 
    // then this is the first time, and all we display is the screen.
    // Otherwise, if they are defined, then we've just had a failed login
    // attempt, so display an additional "please try again" message.
    String message = "";
    if ((username != null) || (password != null)) {
        message = "Invalid username or password. Please try again";
    }
%>

    <%-- This is the body of the else block started above.  It displays --%>
    <%-- the login page.  It is straight HTML with only a few Java --%>
    <%-- expressions contained in <%= %> tags. It also contains tags --%>
    <%-- from a custom tag library, the subject of a later example --%>
    <head><title>Login</title></head>
    <body bgcolor='white'>
    <br><br><br>        <%-- Space down from the top of the page a bit --%>
    <%-- A custom tag: display a decorative box --%>
    <decor:box color='yellow' margin='25' borderWidth='3' title='Login'>
    <div align=center>  <%-- Center everything inside the box --%>
    <%-- Display the login title and optional error message --%>
    <font face='helvetica'><h1><%=title%></h1></font>
    <font face='helvetica' color='red'><b><%=message%></b></font>
    <%-- Now display an HTML form for the user to enter login information --%>
    <form action='login.jsp' method='post'>
	<table>         <%-- Use a table to make the login form look nice --%>
	    <tr>        <%-- First row: username --%>
		<td align='right'>
		    <b><font face='helvetica'>Username:</font></b>
		</td>
		<td><input name='username'></td>
	    </tr><tr>   <%-- Second row: password --%>
		<td align='right'>
		    <b><font face='helvetica'>Password:</font></b>
		</td>
		<td><input type='password' name='password'></td>
	    </tr><tr>   <%-- Third row: login button --%>
		<td align='center' colspan=2><font face='helvetica'><b>
		    <input type=submit value='Login'>
		</b></font></td>
	    </tr>
	</table>
	<%-- The form must also include some hidden fields so this page --%>
	<%-- can pass the nextpage and title parameters back to itself --%>
        <input type='hidden' name='nextpage' value='<%=nextPage%>'>
        <input type='hidden' name='title' value='<%=title%>'>
    </form>
    </div>
    </decor:box> <%-- End of the custom box tag --%>
    </body>      <%-- End of the HTML output --%>
<%
} // This is one final scriptlet to close the else block started above
%>
