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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.davidflanagan.examples.gui.ItemChooser;

/**
 * This is a JDialog subclass that allows the user to select a font, in any
 * style and size, from the list of available fonts on the system.  The 
 * dialog is modal. Display it with show(); this method does not return
 * until the user dismisses the dialog.  When show() returns, call 
 * getSelectedFont() to obtain the user's selection.  If the user clicked the
 * dialog's "Cancel" button, getSelectedFont() will return null.
 **/
public class FontChooser extends JDialog {
    // These fields define the component properties
    String family;           // The name of the font family
    int style;               // The font style
    int size;                // The font size
    Font selectedFont;       // The Font they correspond to

    // This is the list of all font families on the system
    String[] fontFamilies;

    // The various Swing components used in the dialog
    ItemChooser families, styles, sizes;
    JTextArea preview;
    JButton okay, cancel;

    // The names to appear in the "Style" menu
    static final String[] styleNames = new String[] {
	"Plain", "Italic", "Bold", "BoldItalic"
    };
    // The style values that correspond to those names
    static final Integer[] styleValues = new Integer[] {
	new Integer(Font.PLAIN), new Integer(Font.ITALIC),
	new Integer(Font.BOLD), new Integer(Font.BOLD+Font.ITALIC)
    };
    // The size "names" to appear in the size menu
    static final String[] sizeNames = new String[] {
	"8", "10", "12", "14", "18", "20", "24", "28", "32", 
	"40", "48", "56", "64", "72"
    };

    // This is the default preview string displayed in the dialog box
    static final String defaultPreviewString = 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZ\n" + 
	"abcdefghijklmnopqrstuvwxyz\n" + 
	"1234567890!@#$%^&*()_-=+[]{}<,.>\n" +
	"The quick brown fox jumps over the lazy dog";

    /** Create a font chooser dialog for the specified frame. */
    public FontChooser(Frame owner) {
	super(owner, "Choose a Font");  // Set dialog frame and title

	// This dialog must be used as a modal dialog.  In order to be used
	// as a modeless dialog, it would have to fire a PropertyChangeEvent
	// whenever the selected font changed, so that applications could be 
	// notified of the user's selections.
	setModal(true);
	
	// Figure out what fonts are available on the system
	GraphicsEnvironment env =
	    GraphicsEnvironment.getLocalGraphicsEnvironment();
	fontFamilies = env.getAvailableFontFamilyNames();

	// Set initial values for the properties
	family = fontFamilies[0];
	style = Font.PLAIN;
	size = 18;
	selectedFont = new Font(family, style, size);

	// Create ItemChooser objects that allow the user to select font
	// family, style, and size.
	families = new ItemChooser("Family", fontFamilies, null, 0,
				   ItemChooser.COMBOBOX);
	styles = new ItemChooser("Style", styleNames, styleValues, 0,
				 ItemChooser.COMBOBOX);
	sizes = new ItemChooser("Size", sizeNames,null,4,ItemChooser.COMBOBOX);

	// Now register event listeners to handle selections
	families.addItemChooserListener(new ItemChooser.Listener() {
		public void itemChosen(ItemChooser.Event e) {
		    setFontFamily((String)e.getSelectedValue());
		}
	    });
	styles.addItemChooserListener(new ItemChooser.Listener() {
		public void itemChosen(ItemChooser.Event e) {
		    setFontStyle(((Integer)e.getSelectedValue()).intValue());
		}
	    });
	sizes.addItemChooserListener(new ItemChooser.Listener() {
		public void itemChosen(ItemChooser.Event e) {
		   setFontSize(Integer.parseInt((String)e.getSelectedValue()));
		}
	    });

	// Create a component to preview the font.
	preview = new JTextArea(defaultPreviewString, 5, 40);
	preview.setFont(selectedFont);

	// Create buttons to dismiss the dialog, and set handlers on them
	okay = new JButton("Okay");
	cancel = new JButton("Cancel");
	okay.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { hide(); }
	    });
	cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    selectedFont = null;
		    hide();
		}
	    });
	
	// Put the ItemChoosers in a Box 
	Box choosersBox = Box.createHorizontalBox();
	choosersBox.add(Box.createHorizontalStrut(15));
	choosersBox.add(families);
	choosersBox.add(Box.createHorizontalStrut(15));
	choosersBox.add(styles);
	choosersBox.add(Box.createHorizontalStrut(15));
	choosersBox.add(sizes);
	choosersBox.add(Box.createHorizontalStrut(15));
	choosersBox.add(Box.createGlue());

	// Put the dismiss buttons in another box
	Box buttonBox = Box.createHorizontalBox();
	buttonBox.add(Box.createGlue());
	buttonBox.add(okay);
	buttonBox.add(Box.createGlue());
	buttonBox.add(cancel);
	buttonBox.add(Box.createGlue());

	// Put the choosers at the top, the buttons at the bottom, and
	// the preview in the middle.
	Container contentPane = getContentPane();
	contentPane.add(new JScrollPane(preview), BorderLayout.CENTER);
	contentPane.add(choosersBox, BorderLayout.NORTH);
	contentPane.add(buttonBox, BorderLayout.SOUTH);

	// Set the dialog size based on the component size.
	pack();
    }

    /**
     * Call this method after show() to obtain the user's selection.  If the
     * user used the "Cancel" button, this will return null
     **/
    public Font getSelectedFont() { return selectedFont; }


    // These are other property getter methods
    public String getFontFamily() { return family; }
    public int getFontStyle() { return style; }
    public int getFontSize() { return size; }

    // The property setter methods are a little more complicated.
    // Note that none of these setter methods update the corresponding
    // ItemChooser components as they ought to.
    public void setFontFamily(String name) { 
	family = name; 
	changeFont();
    }
    public void setFontStyle(int style) {
	this.style = style;
	changeFont();
    }
    public void setFontSize(int size) {
	this.size = size;
	changeFont();
    }
    public void setSelectedFont(Font font) {
	selectedFont = font;
	family = font.getFamily();
	style = font.getStyle();
	size = font.getSize();
	preview.setFont(font);
    }

    // This method is called when the family, style, or size changes
    protected void changeFont() {
	selectedFont = new Font(family, style, size);
	preview.setFont(selectedFont);
    }

    // Override this inherited method to prevent anyone from making us modeless
    public boolean isModal() { return true; }

    /** This inner class demonstrates the use of FontChooser */
    public static class Demo {
	public static void main(String[] args) {
	    // Create some components and a FontChooser dialog
	    final JFrame frame = new JFrame("demo");
	    final JButton button = new JButton("Push Me!");
	    final FontChooser chooser = new FontChooser(frame);
	    
	    // Handle button clicks
	    button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			// Pop up the dialog
			chooser.show();
			// Get the user's selection
			Font font = chooser.getSelectedFont();
			// If not cancelled, set the button font
			if (font != null) button.setFont(font);
		    }
		});
	    
	    // Display the demo
	    frame.getContentPane().add(button);
	    frame.setSize(200, 100);
	    frame.show();
	}
    }
}
