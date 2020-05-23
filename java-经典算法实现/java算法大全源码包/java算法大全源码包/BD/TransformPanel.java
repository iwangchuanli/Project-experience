/* TransformPanel class */

import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * Text panel to display the source code of the animation algorithm.
 * This class is initialized in the <code>TextFrame</code> class
 * construction during the main frame initialization.
 * Each line of the source code starting with <code>/*-</code> will be ignored.
 * The line after <code>/*-------</code> is considered as the first line of
 * text being displayed.
 * The line before <code>//-</code> is the last line of source code being
 * displayed. The text after this line will no longer be parsed.
 * See also <A href="AlgThread.html"><code>AlgThread</code></A> for special
 * symbol used in displaying source code in text panel.
 * <p>
 * This method should NOT have to be called manually.
 * @see TextFrame
 * @see AlgAnimFrame
 */
public class TransformPanel extends Panel {
    String file_name;

    private boolean highlighted;
    private int width = 300;
    private int height = 400;

		private BlockDiagram bd;

    private Dimension offscreensize = null;
    private Image offscreen = null;
    private Graphics offGraphics = null;
    private static final int dx = 2;
    private Font font;
    private int font_size = 10;
    private int line_space = 14;
    private int start;

    private void SetFont( Graphics g ) {
        FontMetrics fm;
        this.setBackground( Color.white );
        font = new Font( "Dialog", Font.PLAIN, font_size );
        fm = g.getFontMetrics( font );
        line_space = fm.getHeight() + 1;
        g.setFont( font );
    }

    private int ReadSource( DataInputStream ds ) {
        int cnt = 0;
        StringBuffer sb;
                
        try {
            while( true ) {
                String s;
                if ( (s = ds.readLine()) != null ) {
                   // if ( s.startsWith( start_sign ) ) {
                   //     n_lines = 0;
                   //     cnt = 0;
                   // } else if (s.trim().startsWith(ignore_trigger)) {
                   //     continue;
                   // } else if (s.startsWith(end_sign)) {
                   //     break;
                   // } else {
                   //     sb = trim( s );
                   //     /* Copy the buffer into a string */
                   //     lines[cnt++] = expandtabs( sb );
                   // } 
                } else
                    break;
            }
        } catch( IOException e ) {}
        return cnt;
    }

		public TransformPanel( int number, boolean before ) {
			System.out.println("TransformPanel(" + number + "," +
                                              before + ")" );
			bd = new BlockDiagram( number, before );
			if ( bd == null ) {
				System.out.println("null blockdiagram");
				}
			highlighted = false;
			
			}

    /**
     * Creates a text panel based on the source file specified in the String
     * passed in as the parameter.
     * @param fn Filename of the source code
     */
    public TransformPanel( String fn ) {
        FileInputStream instream;

        file_name = fn;

        File source = new File( file_name );
        
        if ( source.exists() && source.isFile() ) {
            try {
                // lines = new String[ max_lines ];
                instream = new FileInputStream( source );
            } catch( IOException e ) {}
            /* Read the file */
        } else
            System.out.println("Cant access [" + file_name + "]");
    }
        
    /**
     * Creates a text frame based on the URL specified by
     * the parameter.
     * @param sourceURL URL of the source code
     * @see URL
     */
    public TransformPanel( URL sourceURL) {
        InputStream source = null;
        StringBuffer sb;

        // n_lines = 0;
				// start = 0;
        // file_name = sourceURL.toString();

				try {
	   			 source = sourceURL.openStream();
				} catch( IOException e ) {
       	}

        if ( source != null ) {
          //   lines = new String[ max_lines ];

           //  /* Read the file */
           //  DataInputStream ds = new DataInputStream( source );
           //  n_lines = ReadSource( ds );
        } 
                        
        // highlight = -1;        
    }

    public void Highlight( int h ) {
        highlighted = h == 0;
        this.repaint();
    }

    /**
     * Returns the initial dimension of the text panel. This method
     * will be called by the layout manager during the corresponding
     * frame initialization.
     */
    public Dimension getPreferredSize() {
        // int h = line_space * (n_lines + 1) - start * line_space;
        int h = 200;
        return new Dimension( width, h );
    }
    
    /**
     * Return the minimum allowed dimension of the text panel.
     */
    public Dimension getMinimumSize() {
        // int h = line_space * (n_lines + 1) - start * line_space;
        int h = 140;
        return new Dimension( width, h );
    }    

    /**
     * This method is invoked when the <code>repaint()</code> method of the
     * parent class is called. This method eliminates flashing during animation.
     */
    public void update(Graphics g) {
        Dimension d = size();

        if (d.width < 1 || d.height < 1)
            return;

        if ((offscreen == null) || (d.width != offscreensize.width) ||
                (d.height != offscreensize.height)) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            offGraphics = offscreen.getGraphics();
        }

        offGraphics.setColor(Color.white);
        offGraphics.fillRect(0, 0, d.width, d.height);
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }
    
    /**
     * Set the first line to display.
     * @param start First line to display
     * @see TransformPanel#getStart
     */
    // public void setStart(int start) {
		//		this.start = start;
    // }

    /**
     * This method print the source code starting from the first line specified
     * by <code>setStart(int)</code> on the text panel.
     * @param g Graphical context of the text panel.
     */
    public void paint( Graphics g ) {
        int i, x, y;

        SetFont( g );
        x = dx; y = line_space;
        g.setColor( Color.black );
        bd.draw( g );
    }

    /**
     * Get the number of lines of source code to be displayed on the text panel.
     * @return The number of lines of source code to be displayed on the 
     *		text frame.
     */
    public int getNumLines() {
	return 0;
    	}

    /**
     * Get the number of pixels used by each line of text.
     * @return The number of pixels allocated for each line of text.
     */
    public int getLineSpace() {
			return line_space;
    }

    /**
     * Get the first line of the source code that will be displayed.
     * This attribute is used to couple the text panel to a vertical scrollbar
     * in the TextFrame. 
     * @return The first line of source code that will be displayed.
     * 
     */
    public int getStart() {
			return start;
    }
		public void setStart( int s ) {
			// not relevant to TransformPanel
			}
}
