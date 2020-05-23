/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.beans;

/**
 * The YesNoPanel class fires an event of this type when the user clicks one
 * of its buttons.  The id field specifies which button the user pressed.
 **/
public class AnswerEvent extends java.util.EventObject {
    public static final int YES = 0, NO = 1, CANCEL = 2;  // Button constants
    protected int id;                             // Which button was pressed?
    public AnswerEvent(Object source, int id) {
	super(source);
	this.id = id;
    }
    public int getID() { return id; }             // Return the button
}
