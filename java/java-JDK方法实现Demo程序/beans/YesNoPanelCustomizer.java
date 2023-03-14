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
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

/**
 * This class is a customizer for the YesNoPanel bean.  It displays a
 * TextArea and three TextFields where the user can enter the main message
 * and the labels for each of the three buttons.  It does not allow the
 * alignment property to be set.
 **/
public class YesNoPanelCustomizer extends Panel
    implements Customizer, TextListener
{
    protected YesNoPanel bean;    // The bean being customized
    protected TextArea message;   // For entering the message    
    protected TextField fields[]; // For entering button text

    // The bean box calls this method to tell us what object to customize.
    // This method will always be called before the customizer is displayed,
    // so it is safe to create the customizer GUI here.
    public void setObject(Object o) {
	bean = (YesNoPanel)o;   // save the object we're customizing
	
	// Put a label at the top of the panel.
	this.setLayout(new BorderLayout());
	this.add(new Label("Enter the message to appear in the panel:"),
		 "North");
	
	// And a big text area below it for entering the message.
	message = new TextArea(bean.getMessageText());
	message.addTextListener(this);
	// TextAreas don't know how big they want to be.  You must tell them.
	message.setSize(400, 200);
	this.add(message, "Center");
	
	// Then add a row of textfields for entering the button labels.
	Panel buttonbox = new Panel();                     // The row container
	buttonbox.setLayout(new GridLayout(1, 0, 25, 10)); // Equally spaced
	this.add(buttonbox, "South");                      // Put row on bottom
	
	// Now go create three TextFields to put in this row.  But actually
	// position a Label above each, so create an container for each
	// TextField+Label combination.
	fields = new TextField[3];               // Array of TextFields.
	String[] labels = new String[] {         // Labels for each.
	    "Yes Button Label", "No Button Label", "Cancel Button Label"};
	String[] values = new String[] {         // Initial values of each.
	    bean.getYesLabel(), bean.getNoLabel(), bean.getCancelLabel()};
	for(int i = 0; i < 3; i++) {
	    Panel p = new Panel();                 // Create a container.
	    p.setLayout(new BorderLayout());       // Give it a BorderLayout.
	    p.add(new Label(labels[i]), "North");  // Put a label on the top.
	    fields[i] = new TextField(values[i]);  // Create the text field.
	    p.add(fields[i], "Center");            // Put it below the label.
	    fields[i].addTextListener(this);       // Set the event listener.
	    buttonbox.add(p);                      // Add container to row.
	}
    }
    // Add some space around the outside of the panel.
    public Insets getInsets() { return new Insets(10, 10, 10, 10); }
    
    // This is the method defined by the TextListener interface.  Whenever the
    // user types a character in the TextArea or TextFields, this will get
    // called.  It updates the appropriate property of the bean and fires a
    // property changed event, as all customizers are required to do.
    // Note that we are not required to fire an event for every keystroke.
    // Instead we could include an "Apply" button that would make all the
    // changes at once, with a single property changed event.
    public void textValueChanged(TextEvent e) {
	TextComponent t = (TextComponent)e.getSource();
	String s = t.getText();
	if (t == message) bean.setMessageText(s);
	else if (t == fields[0]) bean.setYesLabel(s);
	else if (t == fields[1]) bean.setNoLabel(s);
	else if (t == fields[2]) bean.setCancelLabel(s);
	listeners.firePropertyChange(null, null, null);
    }
    
    // This code uses the PropertyChangeSupport class to maintain a list of
    // listeners interested in the edits we make to the bean.
    protected PropertyChangeSupport listeners =new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener l) {
	listeners.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l) {
	listeners.removePropertyChangeListener(l);
    }
}
