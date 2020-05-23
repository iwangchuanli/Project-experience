<%--
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
--%>
<%--forcelogin.jsp
  This is a fragment of JSP code.  It cannot stand alone, but is
  intended to be included within other JSP pages.  It looks for a
  "username" attribute in the Session object, and if it does not find
  one, it forwards the request to the login.jsp page, which will
  authenticate the user and store their name in the session object.
  This code sends a "nextpage" request parameter to the login.jsp, so
  that the login page knows where to return to once the user has
  successfully logged in.  Note that request parameters passed to this
  page are not preserved when the login page redirects back to this page.
--%>
<%
// Check whether the username is already defined in the Session object
String _username = (String) session.getAttribute("username");
if (_username == null) {  // If the user has not already logged in
   String _page = request.getRequestURI();
%>
<%-- Invoke the login.jsp page.  The <jsp:forward> tag invokes the  --%>
<%-- page directly on the server side without sending a redirect --%>
<%-- to the client The <jsp:param> tags specify request parameters --%>
<%-- that are passed to login.jsp --%>
<jsp:forward page='login.jsp'>  
    <jsp:param name='nextpage' value='<%=_page%>'/>
    <jsp:param name='title'
               value='<%= "You must log in to access " + _page%>'/>
</jsp:forward>
<%}%>
