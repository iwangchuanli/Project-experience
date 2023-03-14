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
import javax.servlet.http.*;

/**
 * This class is a simple non-visual JavaBean that defines properties that can
 * be used from a JSP page with the <jsp:useBean>, <jsp:setProperty> and
 * <jsp:getProperty> tags.  These JSP tags allow significant chunks of Java
 * code to be taken out of JSP pages and placed in Java files, where they are
 * easier to read, edit, and maintain.  This example only defines a few
 * trivial properties, but the class could do much more.
 * 
 * This class is instantiated by portal.jsp and is bound in the Session object.
 * Therefore, it implements HttpSessionBindingListener so that it is notified 
 * when the session is terminated (when the user logs out or the session
 * times out).
 **/
public class UserBean implements HttpSessionBindingListener {
    String username;              // The name of the user we represent
    String favorite = colors[0];  // The user's favorite color, with a default
    static final String[] colors = { "gray", "lightblue", "pink", "yellow" };

    // These are the getter and setter methods for the userName property
    // In a real program, setUserName() would probably look up information 
    // about the user in a database of some kind.
    public String getUserName() { return username; }
    public void setUserName(String username) { this.username = username; }

    // These are the getter and setter methods for the favoriteColor property
    public String getFavoriteColor() { return favorite; }
    public void setFavoriteColor(String favorite) { this.favorite = favorite; }

    // Return a list of colors the user is allowed to choose from
    public String[] getColorChoices() { return colors; }

    // This is a getter method for the "customContent" property.  In a more
    // sophisticated example, this method might query a database and return
    // current news clippings or stock quotes for the user.  Not here, though.
    public String getCustomContent() {
	return "Your name backwards is: <tt>" + 
	    new StringBuffer(username).reverse() + "</tt>";
    }


    // This method implements HttpSessionBindingListener.  If an instance of
    // this class is bound in a HttpSession object, then this method will
    // be invoked when the instance becomes unbound, which typically happens
    // when the session is invalidated because the user logged out or
    // was inactive for too long.  In a real example, this method would
    // probably save information about the user to a file or database.
    public void valueUnbound(HttpSessionBindingEvent e) {
	System.out.println(username + " logged out or timed out." + 
			   " Favorite color: " + favorite);
    }

    // Part of HttpSessionBindingListener; we don't care about it here
    public void valueBound(HttpSessionBindingEvent e) {}
}
