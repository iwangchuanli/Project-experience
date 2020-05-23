/* LFrame.java */

import java.awt.*;

public class LFrame extends Frame {

    public LFrame(Color[] colors, String[] strings) {
	add(new Symbol(colors, strings));
	setTitle("Legends");
        pack();
        validate();
        show();
    } // init()

    class Symbol extends Panel {
	Color[] col;
	String[] str;
	public Symbol( Color[] col, String[] str ) {
	    this.col = col;
	    this.str = str;
	    setBackground( Color.white );
	    setFont( new Font("Dialog", Font.PLAIN, 12) );
	}
	
	public void paint(Graphics g) {
	    for (int i = 0; i < col.length; i++) {
	    	g.setColor( col[i] );
	    	g.fillRect( 20, 15 + i*30, 10, 10 );
	    	g.drawLine( 5, 20 + i*30, 45, 20 + i*30 );
	    }

	    g.setColor( Color.black );
	    for (int i = 0; i < str.length; i++) {
		g.drawString(str[i], 50, 25 + i*30);
	    }
	}

	public Dimension getPreferredSize() {
	    return new Dimension( 480, 320 );
	}
    }

    public Dimension preferredSize() {
        return new Dimension(480,320);
    }

    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            dispose();
        }
        return super.handleEvent(event);
    }
} // class LFrame
