/* AlgAnimApp.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * This class creates an applet on a java compliant web browser. There
 * is a single button on the applet, which will bring up a frame,
 * performing the corresponding algorithm animation.
 * <p>
 * This class is normally <b>NOT TO BE CHANGED</b> and is reuseable for any
 * animation algorithm.
 * <p>
 * The filename of the source code and the button label are 
 * passed into this class from the
 * <code>applet</code> tag in the HTML file.
 */
public class AlgAnimApp extends Applet {
    static String fn_label = "filename";
    static String button_label = "buttonname";
    URL homeURL, sourceURL;
    /**
     * A awt button to bring up a frame. This button will be disabled once
     * the frame is created and will be enabled again when the frame is
     * destroy.
     * @see AlgAnimFrame
     */
    public Button start_button;

    /**
     * This constructor is not to be manually called. It inherits completely
     * the properties of the Applet class.
     */
    public AlgAnimApp() {}

    /**
     * Applet initialization method, which creates the button attribute.
     */
    public void init() {
	setBackground( Color.white );
        String file_name = this.getParameter(fn_label); 
        homeURL = getCodeBase();
        try {
            sourceURL = new URL( homeURL, file_name );
        } catch( IOException e ) {
            System.out.println("URL error " + file_name );
        }

        start_button = new Button(getParameter(button_label));
        add(start_button);

        validate();
	
    } // init()

    /**
     * The <code>start_button</code> action handling method, which is
     * automatically invoked when the button is pressed.
     */
    public boolean action(Event e, Object arg) {
        Object target = e.target;
 
        if (target == start_button) {
            start_button.disable();
            new AlgAnimFrame(this, sourceURL);
            return true;
	    //setVisible(true);
        }
        return false;
    } // action()

} // class AlgAnimApp
