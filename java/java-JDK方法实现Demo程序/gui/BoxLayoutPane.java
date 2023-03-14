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
import javax.swing.border.*;

public class BoxLayoutPane extends JPanel {
    public BoxLayoutPane() {
	// Use a BorderLayout layout manager to arrange various Box components
	this.setLayout(new BorderLayout());

	// Give the entire panel a margin by adding an empty border
	// We could also do this by overriding getInsets()
	this.setBorder(new EmptyBorder(10,10,10,10));

	// Add a plain row of buttons along the top of the pane
	Box row = Box.createHorizontalBox();
	for(int i = 0; i < 4; i++) {
	    JButton b = new JButton("B" + i);
	    b.setFont(new Font("serif", Font.BOLD, 12+i*2));
	    row.add(b);
	}
	this.add(row, BorderLayout.NORTH);

	// Add a plain column of buttons along the right edge
	// Use BoxLayout with a different kind of Swing container
	// Give the column a border: can't do this with the Box class
	JPanel col = new JPanel();
	col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
	col.setBorder(new TitledBorder(new EtchedBorder(), "Column"));
	for(int i = 0; i < 4; i++) {
	    JButton b = new JButton("Button " + i);
	    b.setFont(new Font("sanserif", Font.BOLD, 10+i*2));
	    col.add(b);
	}
	this.add(col, BorderLayout.EAST); // Add column to right of panel

	// Add a button box along the bottom of the panel.  
	// Use "Glue" to space the buttons evenly 
	Box buttonbox = Box.createHorizontalBox();
	buttonbox.add(Box.createHorizontalGlue());     // stretchy space
	buttonbox.add(new JButton("Okay"));
	buttonbox.add(Box.createHorizontalGlue());     // stretchy space
	buttonbox.add(new JButton("Cancel"));
	buttonbox.add(Box.createHorizontalGlue());     // stretchy space
	buttonbox.add(new JButton("Help"));
	buttonbox.add(Box.createHorizontalGlue());     // stretchy space
	this.add(buttonbox, BorderLayout.SOUTH);

	// Create a component to display in the center of the panel
	JTextArea textarea = new JTextArea();
	textarea.setText("This component has 12-pixel margins on left and top"+
			 " and has 72-pixel margins on right and bottom.");
	textarea.setLineWrap(true);
	textarea.setWrapStyleWord(true);

	// Use Box objects to give the JTextArea an unusual spacing
	// First, create a column with 3 kids.  The first and last kids
	// are rigid spaces.  The middle kid is the text area
	Box fixedcol = Box.createVerticalBox();
	fixedcol.add(Box.createVerticalStrut(12));  // 12 rigid pixels
	fixedcol.add(textarea);          // Component fills in the rest
	fixedcol.add(Box.createVerticalStrut(72));  // 72 rigid pixels

	// Now create a row.  Give it rigid spaces on the left and right,
	// and put the column from above in the middle.
	Box fixedrow = Box.createHorizontalBox();
	fixedrow.add(Box.createHorizontalStrut(12));
	fixedrow.add(fixedcol);
	fixedrow.add(Box.createHorizontalStrut(72));

	// Now add the JTextArea in the column in the row to the panel
	this.add(fixedrow, BorderLayout.CENTER);
    }
}
