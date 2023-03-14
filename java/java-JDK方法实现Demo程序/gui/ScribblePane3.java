/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.gui;
import java.awt.*;          // For Graphics object and colors
import javax.swing.*;       // For JPanel component
import java.awt.event.*;    // For ActionListener interface
import javax.swing.event.*; // For ListSelectionListener interface

/**
 * This scribble component includes a JButton to clear the screen, and
 * a JList that lets the user select a drawing color.  It uses
 * event listener objects to handle events from those sub-components.
 **/
public class ScribblePane3 extends ScribblePane2 {
    // These are colors the user can choose from
    Color[] colors = new Color[] { Color.black, Color.red, Color.blue };
    // These are names for those colors
    String[] colorNames = new String[] { "Black", "Red", "Blue" };

    // Add JButton and JList components to the panel.
    public ScribblePane3() {
	// Implicit super() call here invokes the superclass constructor

	// Add a "Clear" button to the panel.
	// Handle button events with an action listener
	JButton clear = new JButton("Clear");
	clear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { clear(); }
	    });
	this.add(clear);

	// Add a JList to allow color choices.
	// Handle list selection events with a ListSelectionListener.
	final JList colorList = new JList(colorNames);
	colorList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
		    setColor(colors[colorList.getSelectedIndex()]);
		}
	    });
	this.add(colorList);
    }
}
