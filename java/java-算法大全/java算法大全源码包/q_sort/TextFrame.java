/* TextFrame class */

import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;
import java.net.*;

public class TextFrame extends Panel {
    String file_name;

    String[] lines;
    int n_lines, highlight;
    int width = 200;
    int height = 400;
    int delay = 400;
    int max_lines = 100;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    static final int dx = 2;
    Font font;
    int line_space = 14;
    int font_size = 10;

    static final String ignore_trigger = "/*-";
    static final String start_sign = "/*-------";
    static final String end_sign = "//-";

    private StringBuffer trim(String s) {
        int pos;
        StringBuffer sb;

        pos = s.indexOf(ignore_trigger);
        if (pos >= 0) {
            sb = new StringBuffer(s.substring(0,pos)); //sb.setLength(pos);
        } else 
            sb = new StringBuffer( s );
        return sb;
    }
                
    private String expandtabs( StringBuffer sb ) {
        int i, len;
        len = sb.length();
        i = 0;
        while( i < len ) {
            if ( sb.charAt( i ) == '\t' ) {
                sb.setCharAt(i,' ');
                sb.insert(i,' ');
                len++;
            }
            i++;
        }
        return sb.toString();        
    }

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
                    if ( s.startsWith( start_sign ) ) {
                        n_lines = 0;
                        cnt = 0;
                    } else if (s.trim().startsWith(ignore_trigger)) {
                        continue;
                    } else if (s.startsWith(end_sign)) {
                        break;
                    } else {
                        sb = trim( s );
                        /* Copy the buffer into a string */
                        lines[cnt++] = expandtabs( sb );
                    } 
                } else
                    break;
            }
        } catch( IOException e ) {}
        return cnt;
    }

    public TextFrame( String fn ) {
        FileInputStream instream;

        file_name = fn;

        File source = new File( file_name );
        
        if ( source.exists() && source.isFile() ) {
            try {
                lines = new String[ max_lines ];
                instream = new FileInputStream( source );
            } catch( IOException e ) {}
            /* Read the file */
        } else
            System.out.println("Cant access [" + file_name + "]");
    }
        
    public TextFrame( URL sourceURL, String fn ) {
        InputStream source = null;
        StringBuffer sb;

        n_lines = 0;
        file_name = sourceURL.toString();

            try {
                  source = sourceURL.openStream();
            } catch( IOException e ) {
        }

        if ( source != null ) {
            lines = new String[ max_lines ];

            /* Read the file */
            DataInputStream ds = new DataInputStream( source );
            n_lines = ReadSource( ds );
        } 
                        
        highlight = -1;        
    }

    public void Highlight( int h ) {
        highlight = h;
        this.repaint();
    }

    public Dimension getPreferredSize() {
        int h = line_space * n_lines;
        return new Dimension( width, h );
    }

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

    public void paint( Graphics g ) {
        int i, x, y;

        SetFont( g );
        x = dx; y = line_space;

        g.setColor( Color.black );
        for (i=0;i<n_lines;i++) {
            if( i == highlight ) {
                g.setColor( Color.red );
            }
            g.drawString( lines[i], x, y );
            g.setColor( Color.black );
            y += line_space;                
        }
        //g.drawString("EOF",x,y);
    }
}



