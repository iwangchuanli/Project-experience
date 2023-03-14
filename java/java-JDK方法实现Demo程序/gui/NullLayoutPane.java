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

public class NullLayoutPane extends JPanel {
    public NullLayoutPane() {
	// Get rid of the default layout manager.
	// We'll arrange the components ourselves.
	this.setLayout(null);

	// Create some buttons and set their sizes and positions explicitly
	for(int i = 1; i <= 9; i++) {
	    JButton b = new JButton("Button #" + i);
	    b.setBounds(i*30, i*20, 125, 30); // use reshape() in Java 1.0
	    this.add(b);
	}
    }
    
    // Specify how big the panel should be.
    public Dimension getPreferredSize() { return new Dimension(425, 250); }
}
