/* ComPanel.java */

import java.awt.*;
import java.applet.*;
import java.io.*;

/**
 * Commentary panel, which only consists of a few rows of texts in the form
 * of <code>java.awt.Label</code>.
 * The number of text labels are set during the class construction.
 * <p>
 * In this particular implemetation, the commentary panel is displayed as
 * the footer of the window frame.
 * @see AlgAnimFrame
 */
public class ComPanel extends Panel {
    private Label[] com;

    /**
     * Creates a commentary panel based on the initial comment and number
     * of text fields specified by the parameters.
     * @param s Normally the algorithm's name. This parameter will be
     * displayed on the first line of the text fields as "Running " + s.
     * @param n_lines The number of text fields to be created on this
     * commentary panel.
     */
    public ComPanel( String s, int n_lines ) {
        com = new Label[n_lines];
	setLayout(new GridLayout(0,1));
        int i;
        this.setBackground(Color.lightGray);
        for (i=0;i<n_lines;i++) {
            //com[i] = new TextField(50);
            com[i] = new Label();
	    com[i].setBackground(Color.lightGray);
            add( com[i] );
        }
        com[0].setText("Running " + s );
    }

    /**
     * This method set the text string on the text label specified by the
     * parameters.
     * @param line The text label to display the text string. The first one
     * starts from 0.
     * @param s The text string to be displayed.
     */
    public void setText( int line, String s ) {
	if (line < com.length)
            com[line].setText( s );
    }
}
