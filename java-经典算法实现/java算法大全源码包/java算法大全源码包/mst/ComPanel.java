/* ComPanel.java */

import java.awt.*;
import java.applet.*;
import java.io.*;

public class ComPanel extends Panel {
    TextField[] com;

    public ComPanel( String s, int n_lines ) {
        com = new TextField[n_lines];
	setLayout(new GridLayout(0,1));
        int i;
        //this.setBackground(Color.green);
        for (i=0;i<n_lines;i++) {
            com[i] = new TextField(60);
            add( com[i] );
        }
        com[0].setText("Running " + s );
    }

    public void setText( int line, String s ) {
        com[line].setText( s );
    }
}
