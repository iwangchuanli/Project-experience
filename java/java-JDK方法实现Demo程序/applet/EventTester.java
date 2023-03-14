/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.applet;
import java.applet.*;
import java.awt.*;
import java.util.*;

/** An applet that gives details about Java 1.0 events */
public class EventTester extends Applet {
    // Handle mouse events
    public boolean mouseDown(Event e, int x, int y)  {
	showLine(mods(e.modifiers) +  "Mouse Down: [" + x + "," + y + "]");
	return true;
    }
    public boolean mouseUp(Event e, int x, int y)  {
	showLine(mods(e.modifiers) + "Mouse Up: [" + x + "," + y + "]");
	return true;
    }
    public boolean mouseDrag(Event e, int x, int y)  {
	showLine(mods(e.modifiers) + "Mouse Drag: [" + x + "," + y + "]");
	return true;
    }
    public boolean mouseMove(Event e, int x, int y) {
	showLine(mods(e.modifiers) + "Mouse Move: [" + x + "," + y + "]");
	return true;
    }
    public boolean mouseEnter(Event e, int x, int y)  {
	showLine("Mouse Enter: [" + x + "," + y + "]"); return true;
    }
    public boolean mouseExit(Event e, int x, int y)  {
	showLine("Mouse Exit: [" + x + "," + y + "]"); return true;
    }
    
    // Handle focus events
    public boolean gotFocus(Event e, Object what)  {
	showLine("Got Focus"); return true;
    }
    public boolean lostFocus(Event e, Object what)  {
	showLine("Lost Focus"); return true;
    }
    
    // Handle key down and key up events
    // This gets more confusing because there are two types of key events
    public boolean keyDown(Event e, int key)  {
	int flags = e.modifiers;
	if (e.id == Event.KEY_PRESS)                 // a regular key
	    showLine("Key Down: " + mods(flags) + key_name(e));
	else if (e.id == Event.KEY_ACTION)           // a function key
	    showLine("Function Key Down: " + mods(flags) +
		     function_key_name(key));
	return true;
    }
    public boolean keyUp(Event e, int key)  {
	int flags = e.modifiers;
	if (e.id == Event.KEY_RELEASE)               // a regular key
	    showLine("Key Up: " + mods(flags) + key_name(e));
	else if (e.id == Event.KEY_ACTION_RELEASE)   // a function key
	    showLine("Function Key Up: " + mods(flags) +
		     function_key_name(key));
	return true;
    }
    
    // The remaining methods help us sort out the various modifiers and keys
    
    // Return the current list of modifier keys
    private String mods(int flags) {
	String s = "[ ";
	if (flags == 0) return "";
	if ((flags & Event.SHIFT_MASK) != 0) s += "Shift ";
	if ((flags & Event.CTRL_MASK) != 0) s += "Control ";
	if ((flags & Event.META_MASK) != 0) s += "Meta ";
	if ((flags & Event.ALT_MASK) != 0) s += "Alt ";
	s += "] ";
	return s;
    }
    
    // Return the name of a regular (non-function) key.
    private String key_name(Event e) {
	char c = (char) e.key;
	if (e.controlDown()) {   // If CTRL flag is set, handle control chars.
	    if (c < ' ') {
		c += '@';
		return "^" + c;
	    }
	}
	else {                   // If CTRL flag is not set, then certain ASCII
	    switch (c) {         // control characters have special meaning.
	    case '\n': return "Return";
	    case '\t': return "Tab";
	    case '\033': return "Escape";
	    case '\010': return "Backspace";
	    }
	}
	// Handle the remaining possibilities.
	if (c == '\177') return "Delete";
	else if (c == ' ') return "Space";
	else return String.valueOf(c);
    }
    
    // Return the name of a function key.  Just compare the key to the
    // constants defined in the Event class.
    private String function_key_name(int key) {
	switch(key) {
	case Event.HOME: return "Home";     case Event.END: return "End";
	case Event.PGUP: return "Page Up";  case Event.PGDN: return"Page Down";
	case Event.UP: return "Up";         case Event.DOWN: return "Down";
	case Event.LEFT: return "Left";     case Event.RIGHT: return "Right";
	case Event.F1: return "F1";         case Event.F2: return "F2";
	case Event.F3: return "F3";         case Event.F4: return "F4";
	case Event.F5: return "F5";         case Event.F6: return "F6";
	case Event.F7: return "F7";         case Event.F8: return "F8";
	case Event.F9: return "F9";         case Event.F10: return "F10";
	case Event.F11: return "F11";       case Event.F12: return "F12";
	}
	return "Unknown Function Key";
    }
    
    /** A list of lines to display in the window */
    protected Vector lines = new Vector();
    /** Add a new line to the list of lines, and redisplay */
    protected void showLine(String s) {
	if (lines.size() == 20) lines.removeElementAt(0);
	lines.addElement(s);
	repaint();
    }
    /** This method repaints the text in the window */
    public void paint(Graphics g) {
	for(int i = 0; i < lines.size(); i++)
	    g.drawString((String)lines.elementAt(i), 20, i*16 + 50);
    }
}
