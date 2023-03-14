// LabelledBox.java
//    Function block for block diagram animation
//
import java.awt.*;
import java.util.*;

public class LabelledBox implements DrawingObj {
	
	
	int x, y;		// Position at centre
	
	int h, w; 		// Height, width
	
	String label, subscript;// Contents of label
	Color cur_colour;
	Font font;

	public LabelledBox( String label, String subscript,int x, int y, int w, int h ) {
		this.x = x; this.y = y;
		this.h = h; this.w = w;
		
		this.label = new String( label );
		this.subscript = new String( subscript );
		cur_colour = Color.blue;
		
		}

	public void setColour(Color c) {
		 cur_colour = c;
		}

	public void move( int x, int y ) {
		this.x = x;
		this.y = y;
		// Draw ??
		}

	public int getX() { return x; }
	public int getY() { return y; }
	
	private Point labelLocation (Graphics g) {
		
		FontMetrics fm = g.getFontMetrics();
		int x = (w/2) - ((fm.stringWidth(label)/2)+(fm.stringWidth(subscript)/2));
		int y = (h/2) + (fm.getAscent()/2);
		return new Point(x,y);
	}
	public void draw( Graphics g ) {
		int DrawX, DrawY;	// Starting-to-draw position 	
		DrawX = x - w/2;
		DrawY = y - h/2;
		g.setColor( Color.white );
		g.fillRect( DrawX, DrawY, w, h );
	        Point labelLoc = labelLocation(g);
		g.setColor( cur_colour );
		g.drawRect( DrawX, DrawY, w, h );
		// g.setFont( font );
		g.drawString( label, DrawX+labelLoc.x , DrawY+labelLoc.y );
		g.drawString( subscript, DrawX+labelLoc.x+13 ,DrawY+labelLoc.y+5);
		
		}

	public Dimension getLimit( int dirn ) {
		switch ( dirn ) {
			case 1: return new Dimension( x + w/2, y );
			case 2: return new Dimension( x, y + h/2 );
			case -1: return new Dimension( x - w/2, y );
			case -2: return new Dimension( x, y - h/2 );
			default:
				return new Dimension( x, y );
			}
		}

}
	
