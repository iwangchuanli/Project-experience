<%--
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
--%>
<%@page language='java'	contentType='text/html'%>  <%-- On every JSP page --%>

<%-- Specify a tag library to use in this file --%>
<%@taglib uri='http://www.davidflanagan.com/tlds/decor_0_1.tld'
	  prefix='decor'%>

<%-- Include the JSP code from forcelogin.jsp when this page is compiled --%>
<%-- The included file checks if the user is logged in, and if not, --%>
<%-- forwards to the login.jsp page to get a username and password. This --%>
<%-- ensures that the 'username' attribute is defined in the session. --%>
<%@include file='forcelogin.jsp'%>

<%-- Declare a new variable named 'user'.  It is an instance of UserBean --%>
<%-- and it is associated with the session object.  If this is a new --%>
<%-- session, then instantiate the bean and set its 'userName' property --%>
<%-- to the value of the username from the session. --%>
<jsp:useBean id='user' scope='session'
	     class='com.davidflanagan.examples.servlet.UserBean'>
  <jsp:setProperty name='user' property='userName'
                   value='<%=(String)session.getAttribute("username")%>'/>
</jsp:useBean>

<%-- Each time this page is displayed, set the 'favoriteColor' property of --%>
<%-- the 'user' bean.  Since no 'value' parameter is supplied, set the     --%>
<%-- property to the 'favoriteColor' parameter of the incoming request, if --%>
<%-- there is a parameter of that name; otherwise don't set the property.  --%>
<jsp:setProperty name='user' property='favoriteColor'/>

<%-- Begin to output the page.  Note the use of the JSP to include a Java --%>
<%-- expression within the HTML <body> tag.  This is an explicit use of   --%>
<%-- 'user' bean declared above. --%>
<head><title>The Demo Portal</title></head>
<body bgcolor='<%=user.getFavoriteColor()%>'>

<%-- Greet the user by invoking the hello servlet and including its output --%>
<%-- here.  The hello servlet expects to find the username in the session --%>
<%-- where the login.jsp page stored it.  Note the difference between --%>
<%-- this runtime jsp:include tag and the @include directive used above --%>
<%-- to include the forcelogin.jsp file at compile-time. --%>
<div align=center>
<h1><jsp:include page='servlet/hello' flush='true'/></h1>
</div>

<%-- Display a box using our 'decor' tag library, and as its content --%>
<%-- use the value of the 'customContent' property of the 'user' bean --%>
<%-- Hopefully, the bean will provide interesting content tailored --%>
<%-- to the specified user --%>
<decor:box title='Your Custom Content'>
  <jsp:getProperty name='user' property='customContent'/>
</decor:box>

<%-- Begin another box --%>
<%-- The content of this box is a table with 2 cells side-by-side --%>
<p>
<decor:box color='yellow' margin='15' borderColor='darkgreen'
	   title='Favorite Colors' titleAlign='center' titleColor='#aaffaa'>
<table><tr><td>
<%-- The first cell is another box.  It displays some text and the value--%>
<%-- of the 'favoriteColor' property of the 'user' bean --%>
<decor:box color='pink' margin='20'>
Your favorite color is: 
<jsp:getProperty name='user' property='favoriteColor'/>
</decor:box>
</td>
<%-- This is the second cell of the table.  It contains a form for --%>
<%-- selecting a favorite color. Note the technique used to output the --%>
<%-- <select> element of the form. --%>
<td>
<form method='post'>                <%-- Begin the form --%>
<select name='favoriteColor'>       <%-- Begin a <select> object --%>
<%                                  // Begin a Java scriptlet
// Ask the bean for the list of color choices, and the user's favorite
String[] colors = user.getColorChoices();
String favorite = user.getFavoriteColor();
// Now start looping through the list of colors.  The body of this loop
// is the HTML and JSP code below.
for(int i = 0; i < colors.length; i++) {
%>                                  <%-- End the scriptlet --%>
    <%-- Each time through the loop, we'll output an <option> element --%>
    <%-- Note the use of JSP <%=...%> expression tags to customize this --%>
    <%-- option tag for each iteration of the loop --%>
    <option value='<%=colors[i]%>'
        <%=(colors[i].equals(favorite))?"selected":""%>
    >
	<%=colors[i]%>              <%-- Label of <option> element --%>
    </option>
<%}%>	                            <%-- End the for loop --%>
</select>                           <%-- End the <select> object --%>
<input type=submit value='Select'>  <%-- Submit button for the form --%>
</form>                             <%-- End the form--%>
</td></tr></table>                  <%-- End of 2nd cell and the table --%>
</decor:box>                        <%-- End of the box --%>

<%-- Begin a new box.  This one also contains a 2-cell table --%>
<br>
<decor:box title='Logout when Done' color='lightblue'>
<table><tr><td valign=top>

<%-- The first table cell is a simple form that allows the user to logout --%>
<%-- See Logout.java 
for details --%>
<form action='servlet/logout' method='post'>
  <input type=hidden name='page' value='../portal.jsp'>
  <font face='helvetica'><b><input type='submit' value='Logout'></b></font>
</form>

<%-- The second item in the box is another box that displays counts --%>
<%-- We use jsp:include twice more to invoke the Counter servlet. --%>
<%-- Note that for the 2nd inclusion, we rely on the fact that the web.xml --%>
<%-- file maps any URL ending in ".count" to the Counter servlet --%>
</td><td valign=top>
<decor:box color='#a0a0c0' margin='5' borderWidth='1'>
You have visited
  <jsp:include page='servlet/counter' flush='true'>
      <jsp:param name='counter'
                 value='<%="portaluser_"+session.getAttribute("username")%>'/>
  </jsp:include>
times. The portal has been visited
  <jsp:include page='portal.jsp.count' flush='true'/>
times.
</decor:box>
</td></tr></table>
</decor:box>
