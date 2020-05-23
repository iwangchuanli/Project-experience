/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

/**
 * A Java applet that simulates a client-side imagemap.
 * Plays a sound whenever the user clicks on one of the hyperlinks.
 */
public class Soundmap extends Applet implements MouseListener {
    protected Image image;     // The image to display.
    protected Vector rects;    // A list of rectangles in it.
    protected AudioClip sound; // A sound to play on user clicks in a rectangle
    protected ImagemapRectangle highlight; // Which rectangle is highlighted

    /** Initialize the applet */
    public void init() {
        // Look up the name of the image, relative to a base URL, and load it.
        // Note the use of three Applet methods in this one line.
        image = this.getImage(this.getDocumentBase(),
			      this.getParameter("image"));

        // Lookup and parse a list of rectangular areas and their URLs.
        // The convenience routine getRectangleParameter() is defined below.
        rects = new Vector();
        ImagemapRectangle r;
        for(int i = 0; (r = getRectangleParameter("rect" + i)) != null; i++)
            rects.addElement(r);

        // Look up a sound to play when the user clicks one of those areas.
        sound = this.getAudioClip(this.getDocumentBase(),
				  this.getParameter("sound"));

        // Specify an "event listener" object to respond to mouse button
        // presses and releases.  Note that this is the Java 1.1 event model.
        this.addMouseListener(this);
    }

    /**
     * Called when the applet is being unloaded from the system.
     * We use it here to "flush" the image we no longer need. This may
     * result in memory and other resources being freed more quickly.
     **/
    public void destroy() { image.flush(); }

    /**
     * To display the applet, we simply draw the image, and highlight the
     * current rectangle if any.
     **/
    public void paint(Graphics g) {
	g.drawImage(image, 0, 0, this);
	if (highlight != null) {
	    g.setColor(Color.red);
	    g.drawRect(highlight.x, highlight.y,
		       highlight.width, highlight.height);
	    g.drawRect(highlight.x+1, highlight.y+1,
		       highlight.width-2, highlight.height-2);
	}
    }

    /**
     * We override this method so that it doesn't clear the background
     * before calling paint().  No clear is necessary, since paint() overwrites
     * everything with an image.  Causes less flickering this way.
     **/
    public void update(Graphics g) { paint(g); }

    /**
     * Parse a comma-separated list of rectangle coordinates and a URL.
     * Used to read the imagemap rectangle definitions from applet parameters
     **/
    protected ImagemapRectangle getRectangleParameter(String name) {
        int x, y, w, h;
        URL url;
        String value = this.getParameter(name);
        if (value == null) return null;

        try {
            StringTokenizer st = new StringTokenizer(value, ",");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            url = new URL(this.getDocumentBase(), st.nextToken());
        }
        catch (NoSuchElementException e) { return null; }
        catch (NumberFormatException e) { return null; }
        catch (MalformedURLException e) { return null; }

        return new ImagemapRectangle(x, y, w, h, url);
    }

    /** Called when a mouse button is pressed. */
    public void mousePressed(MouseEvent e) {
	// On button down, check if we're inside one of the rectangles.
	// If so, highlight the rectangle, display a message, and play a sound.
	// The utility routine findrect() is defined below.
	ImagemapRectangle r = findrect(e);
	// If a rectangle is found, and is not already highlighted
	if (r != null && r != highlight) {
	    highlight = r;                // Remember which rectangle it is
	    showStatus("To: " + r.url);   // display its URL in status line
	    sound.play();                 // play the sound
	    repaint();                    // request a redraw to highlight it
	}
    }

    /** Called when a mouse button is released. */
    public void mouseReleased(MouseEvent e) {
	// If the user releases the mouse button over a highlighted
	// rectangle, tell the browser to display its URL.  Also,
	// erase the highlight and clear status
	if (highlight != null) {
	    ImagemapRectangle r = findrect(e);
	    if (r == highlight)  getAppletContext().showDocument(r.url);
	    showStatus("");     // clear the message.
	    highlight = null;   // forget the highlight
	    repaint();          // request a redraw
	}
    }

    /** Unused methods of the MouseListener interface */
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    /** Find the rectangle we're inside. */
    protected ImagemapRectangle findrect(MouseEvent e) {
	int i, x = e.getX(), y = e.getY();
	for(i = 0; i < rects.size(); i++)  {
	    ImagemapRectangle r = (ImagemapRectangle) rects.elementAt(i);
	    if (r.contains(x, y)) return r;
	}
	return null;
    }

    /**
     * A helper class.  Just like java.awt.Rectangle, but with a URL field.
     * Note the use of a nested toplevel class for neatness.
     **/
    static class ImagemapRectangle extends Rectangle {
        URL url;
        public ImagemapRectangle(int x, int y, int w, int h, URL url) {
            super(x, y, w, h);
            this.url = url;
        }
    }
}
