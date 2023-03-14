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
import javax.servlet.jsp.*;           // JSP classes
import javax.servlet.jsp.tagext.*;    // Tag Library classes
import java.io.IOException;

/**
 * This Tag implementation is part of the "decor" tag library.  It uses HTML
 * tables to display a decorative box (with an optional title) around its
 * content.  The various properties correspond to the attributes supported by
 * the tag.
 **/
public class DecorBox extends TagSupport {
    // These fields contain values that control the appearance of the box
    String align;          // Alignment of the box
    String title;          // Title for the box
    String titleColor;     // Title foreground color
    String titleAlign;     // Title alignment relative to box
    String color;          // Box background color
    String borderColor;    // Border (and title background) color 
    String margin;         // Pixels between box edge and content
    String borderWidth;    // Pixel width of the box border

    // The following property setter methods set the values of the fields above
    // When a JSP page uses this tag any tag attribute settings will be
    // translated into calls to these methods.
    public void setAlign(String value) { align = value; }
    public void setTitle(String value) { title = value; }
    public void setTitleColor(String value) { titleColor = value; }
    public void setTitleAlign(String value) { titleAlign = value; }
    public void setColor(String value) { this.color = value; }
    public void setBorderColor(String value) { borderColor = value; }
    public void setMargin(String value) { margin = value; }
    public void setBorderWidth(String value) { borderWidth = value; }

    /**
     * This inherited method is always the first property setter invoked
     * by the JSP container.  We don't care about the page context here, but
     * use this method to set the default values of the various properties.
     * They are initialized here in case the JSP container wants to reuse
     * this Tag object on multiple pages.
     **/
    public void setPageContext(PageContext context) {
	// Important!  Let the superclass save the page context object.
	// We'll need it in doStartTag() below.
	super.setPageContext(context);

	// Now set default values for all the other properties
	align = "center";
	title = null;
	titleColor = "white";
	titleAlign = "left";
	color = "lightblue";
	borderColor = "black";
	margin = "20";
	borderWidth = "4";
    }

    /**
     * This method is called when a <decor:box> tag is encountered.  Any
     * attributes will first be processed by calling the setter methods above.
     **/
    public int doStartTag() throws JspException {
	try {
	    // Get the output stream from the PageContext object, which
	    // will have been passed to the setPageContext() method.
	    JspWriter out = pageContext.getOut();

	    // Output the HTML tags necessary to display the box. The <div>
	    // handles the alignment, and the <table> creates the border.
	    out.print("<div align='" + align + "'>" +
		      "<table bgcolor='" + borderColor + "' " +
		      "border='0' cellspacing='0' " +
		      "cellpadding='" + borderWidth + "'>");

	    // If there is a title, display it as a cell of the outer table
	    if (title != null)
		out.print("<tr><td align='" + titleAlign + "'>" +
			  "<font face='helvetica' size='+1' " +
			  "color='" + titleColor + "'><b>" +
			  title + "</b></font></td></tr>");

	    // Now begin an inner table that has a different color than 
	    // the border.
	    out.print("<tr><td><table bgcolor='" + color + "' " +
		      "border='0' cellspacing='0' " +
		      "cellpadding='" + margin + "'><tr><td>");
	} 
	catch (IOException e) {
	    // Unlike a PrintWriter, a JspWriter can throw IOExceptions
	    // We have to catch them and wrap them in a JSPException
	    throw new JspException(e.getMessage());
	}

	// This return value tells the JSP class to process the body of the tag
	return EVAL_BODY_INCLUDE;
    }

    /**
     * This method is called when the closing </decor:box> tag is encountered
     **/
    public int doEndTag() throws JspException {
	// Try to output HTML to close the <table> and <div> tags.
	// Catch IOExceptions and rethrow them as JspExceptions
	try {
	    JspWriter out = pageContext.getOut();
	    out.println("</td></tr></table></td></tr></table></div>");
	}
	catch (IOException e) { throw new JspException(e.getMessage()); }

	// This return value says to continue processing the JSP page.
	return EVAL_PAGE;
    }
}
