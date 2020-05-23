
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The <code>ImageButton</code> class extends the original
 * <code>java.awt.Button</code> to display graphical images in 
 * <code>gif</code> format. In this particular implementation,
 * the images to be loaded have to be located in the same
 * directory as the <code>code base</code> (which can be accessed
 * using <code>Applet.getCodeBase()</code>).
 * <p>
 * In order to work around the bug in some of the older java complient
 * web browser, in particular, the <code>class not found</code> exception
 * during the initilization of the frame using Netscape 2.0,
 * this class is casted to its parent class <code>Button</code>
 * and explicitly casted back to <code>ImageButton</code> when the 
 * customized methods are required.
 * 
 * @see java.awt.Button
 */
public class ImageButton extends Button {
        String imageFile;
        AlgAnimApp app;
        Image image = null, enabledImage = null, disabledImage = null;
	Font font = new Font("Helvetica", Font.PLAIN, 10);
	ControlPanel parent;
 
	/**
	 * Creates an <code>ImageButton</code> using the <code>gif</code>
  	 * file specified by the first parameter on the panel defined by
	 * the third parameter.
	 * <p>
 	 * Before calling this constructor, make sure that the image file
	 * <code>imageFile.gif</code> exists. It is also required to have
	 * another gif image <code>imageFileDisable.gif</code>, which
	 * appears to be the disabled button.
	 *
	 * @param imageFile The gif image file without the
	 * <code>.gif</code> extension. The extension will be appended
 	 * when calling the <code>Applet.getImage()</code> method.
	 * <p> 
	 * @param app An instance to an applet. The only applet in this
	 * cluster of classes, an instance of <code>AlgAnimApp</code>, is
	 * usually passed in here. This particular applet is used to
	 * obtain the code base, i.e. the location of the image files.
	 * <p> 
	 * @param parent The panel where this image button is going to reside.
	 * This paremeter is passed in so that the repaint method for the
	 * panel can be called when a refreshing is required.
	 */
        public ImageButton(String imageFile, AlgAnimApp app,
		ControlPanel parent) {
	    this.parent = parent;
	    setLabel(imageFile);
            this.imageFile = imageFile;
            this.app = app;
            URL codeBase = app.getCodeBase();
            try {
                image = enabledImage =
                    app.getImage(new URL(codeBase, imageFile+".gif"));
                disabledImage =
                    app.getImage(new URL(codeBase, imageFile+"Disabled.gif"));
            } catch (MalformedURLException e) {
                System.out.println("Cannot get button Image: " + imageFile +
                        ".gif");
            }
            prepareImage(image, 42, 52, null);
            repaint();
        }
 
	/**
	 * Specify the dimension of the button. In this particular application,
	 * images with resolution of 42x52 are used. Therefore, this
	 * method always returns <code>Dimension</code> with
	 * <code>width</code> 42 and <code>height</code> 52.
	 * This method is only called by the system layout manager.
	 * @return The dimention of the button.
	 */
        public Dimension getPreferredSize() {
            return new Dimension( 42, 52 );
        }

	/**
	 * Specify the dimension of the button. In this particular application,
	 * images with resolution of 42x52 are used. Therefore, this
	 * method always returns <code>Dimension</code> with
	 * <code>width</code> 42 and <code>height</code> 52.
	 * This method is only called by the system layout manager.
	 * @return The dimention of the button.
	 */
	public Dimension preferredSize() {
            return new Dimension( 42, 52 );
	}
 
	/**
	 * Disable the image button and set the current image
	 * to <code>imageFileDisable.gif</code>.
	 */
        public void setDisable() {
            image = disabledImage;
            prepareImage(image, 42, 52, null);
            disable();
	    parent.refreshButtons();
        }
 
	/**
	 * Enable the image button and set the current image
	 * to <code>imageFile.gif</code>
	 */
        public void setEnable() {
            image = enabledImage;
            prepareImage(image, 42, 52, null);
            enable();
	    parent.refreshButtons();
        }
 
	/**
	 * Method to draw image on the button
	 */
        public void print(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

	/**
	 * This method is invokes when the <code>repaint</code> method
	 * is called.
	 */
        public void update(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

	/**
	 * Method to draw image on the button
	 */
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
}
