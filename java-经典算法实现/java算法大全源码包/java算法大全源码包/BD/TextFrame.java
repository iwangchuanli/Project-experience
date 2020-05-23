/* TextFrame.java */

import java.awt.*;
import java.net.*;

/**
 * This <code>TextFrame</code> class brings up a frame containing the text
 * panel. The class constructor is normally called during the initialization of
 * of a <code>AlgAnimFrame</code> instance.
 * 
 * @see TextPanel
 */
public class TextFrame extends Frame {
    private Scrollbar vScrollbar;
	private TransformPanel tp[];
	static final int n_tf = 6;
	IOPoint io;

    /**
     * Construct a text frame and initialize the text panel to be added to
     * the text frame. By default, this frame is going to be displayed
     * with its top-left corner at position (600,0). This can be changed
     * in the constructor according to preference.
     * @param sourceURL The URL of the algorithm source code to be display
     * on the text panel.
     */
  public TextFrame( URL sourceURL ) {
		int j, k, m;
		GridBagLayout lay = new GridBagLayout ();
		this.setLayout( lay );
		GridBagConstraints constraints = new GridBagConstraints();

		System.out.println( "TextFrame( URL ) constructor" );

		constraints.weightx = constraints.weighty = 1;
		constraints.gridx = constraints.gridy = 0;
		constraints.gridwidth = 1; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		move(600, 0);
		setTitle("Transformations");
		m = 0;
		tp = new TransformPanel[n_tf*3];
		for(k=0;k<n_tf;k++) {
			for(j=0;j<3;j++) { /* Before/after pair */
				tp[m] = new TransformPanel( k, j==0  );
				constraints.gridx = j;
				//constraints.weightx = 1;
				//constraints.gridwidth = 1;
				if (j == 1) {
				tp[m] = new TransformPanel (6,j==1);
				//constraints.weightx = 0;
				//constraints.gridwidth = GridBagConstraints.RELATIVE;
				 }
				this.add( tp[m], constraints );
				m++;
				}
			constraints.gridy++;
			}
		System.out.println("TextFrame cons: built " + m + " TPs" );

		vScrollbar = new Scrollbar(Scrollbar.VERTICAL);
		// vScrollbar.setValues(0, 10, 0, 90);
		// vScrollbar.setPageIncrement(10);
		// add("East", vScrollbar);
    pack();
    validate();
    } // init()

    /**
     * Return the preferred size of the frame.
     * @return The frame size as an object of type Dimension.
     */
    public Dimension preferredSize() {
        return new Dimension(320,450);
    }

		private void paintPanels() {
			int k;
			for(k=0;k<tp.length;k++) {
				tp[k].repaint();
				}
			}

    /**
     * An event handler for the text frame which handles the WINDOW_DESTROY
     * event and vertical scrollbar.
     */
    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            dispose();
        } else if (event.target == vScrollbar) {
            switch (event.id) {
                case Event.SCROLL_LINE_UP:
                case Event.SCROLL_LINE_DOWN:
                case Event.SCROLL_PAGE_UP:
                case Event.SCROLL_PAGE_DOWN:
                case Event.SCROLL_ABSOLUTE:
                    int val = ((Scrollbar)(event.target)).getValue();
                    int min = ((Scrollbar)(event.target)).getMinimum();
                    int max = ((Scrollbar)(event.target)).getMaximum();
                    paintPanels();
	    }
  	}
        return super.handleEvent(event);
    }

    /**
     * Get the text panel corresponding to this frame.
     * @return The text panel contains in this frame.
     * @see TextPanel
     */
    public TransformPanel getTextPanel() {
	return tp[0];
    	}

    /**
     * Get the vertical scrollbar governing the view window of the text panel.
     * @return The vertical scrollbar.
     * @see TextPanel
     */
    public Scrollbar getVertScrollbar() {
	return vScrollbar;
    }
} // class TextFrame
