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
import java.awt.*;
import javax.swing.*;

public class BorderLayoutPane extends JPanel {
    String[] borders = {"North", "East", "South", "West", "Center"};
    public BorderLayoutPane() {
	// Use a BorderLayout with 10-pixel margins between components
	this.setLayout(new BorderLayout(10, 10));
	for(int i = 0; i < 5; i++) {          // Add children to the pane
	    this.add(new JButton(borders[i]),    // Add this component
		     borders[i]);                // Using this constraint
	}
    }
}
