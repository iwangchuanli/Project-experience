
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ImageButton extends Button {
        String imageFile;
        AlgAnimApp app;
        Image image = null, enabledImage = null, disabledImage = null;
	Font font = new Font("Helvetica", Font.PLAIN, 10);
	ControlPanel parent;
 
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
 
        public Dimension getPreferredSize() {
            return new Dimension( 42, 52 );
        }

	public Dimension preferredSize() {
            return new Dimension( 42, 52 );
	}
 
        public void setDisable() {
            image = disabledImage;
            prepareImage(image, 42, 52, null);
            disable();
	    parent.refreshButtons();
        }
 
        public void setEnable() {
            image = enabledImage;
            prepareImage(image, 42, 52, null);
            enable();
	    parent.refreshButtons();
        }
 
        public void print(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

        public void update(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
}
