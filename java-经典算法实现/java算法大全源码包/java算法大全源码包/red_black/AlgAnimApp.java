/* AlgAnimApp.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * This class creates an applet on a java compliant web browser. There
 * is a single button on the applet, which will bring up a frame,
 * in which the animation is run.
 * <p>
 * This class is normally <b>NOT TO BE CHANGED</b> and is used by every
 * animation algorithm.
 * <p>
 * The source code filename and the button label are 
 * passed to this class from the
 * <code>applet</code> tag in the HTML file.
 */
public class AlgAnimApp extends Applet {
    static String fn_label = "filename";
    static String button_label = "buttonname";
    URL homeURL, sourceURL;
    /**
     * An awt button to bring up a frame. This button will be disabled once
     * the frame is created and will be enabled again when the frame is
     * destroyed.
     * @see AlgAnimFrame
     */
    public Button start_button;

    /**
     * This constructor should not be called directly. It is called by
     * the browser and inherits the properties of the Applet class.
     */
    public AlgAnimApp() {}

    /**
     * Applet initialization method which sets the button label.
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
        }
        return false;
    } // action()

} // class AlgAnimApp
