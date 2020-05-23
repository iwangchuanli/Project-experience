/* AlgAnimApp.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class AlgAnimApp extends Applet {
    static String fn_label = "filename";
    static String button_label = "buttonname";
    URL homeURL, sourceURL;
    Button start_button;

    public void init() {
        String file_name = this.getParameter(fn_label); 
        homeURL = getDocumentBase();
        try {
            sourceURL = new URL( homeURL, file_name );
        } catch( IOException e ) {
            System.out.println("URL error " + file_name );
        }

        start_button = new Button(getParameter(button_label));
        add(start_button);

        validate();
    } // init()

    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
        if (target == start_button) {
            start_button.disable();
            new AlgAnimFrame(this, sourceURL);
            return true;
        }
        return false;
    } // action()

} // class AlgAnimApp
