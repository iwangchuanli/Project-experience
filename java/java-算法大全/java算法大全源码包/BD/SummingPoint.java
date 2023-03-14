// SummingPoint.java
//    Function block for block diagram animation
//
import java.awt.*;

public class SummingPoint implements DrawingObj {
	
	//
	int x, y;	// Position at centre
	// int h, w; 	// height, width
	Color c;
	int radius = 15;

	public SummingPoint( int x, int y ) {
		this.x = x; this.y = y;
		c = Color.red;
		// h = 2*radius;
		// w = 2*radius;
		}

//	public void setColour( awt.Color new ) {
//		c = new;
//		}

	public void move( int x, int y ) {
		this.x = x;
		this.y = y;
		// Draw ??
		}

	public int getX() { return x; }
	public int getY() { return y; }

	public void draw( Graphics g ) {
		int DrawX, DrawY; // Starting-to-draw position
		g.setColor( c );
		DrawX = x - radius;
		DrawY = y - radius;
		g.fillOval( DrawX, DrawY, 2*radius, 2*radius );
		}

	public Dimension getLimit( int dirn ) {
		switch ( dirn ) {
			case 1: return new Dimension( x+radius, y );
			case 2: return new Dimension( x, y+radius );
			case -1: return new Dimension( x-radius, y );
			case -2: return new Dimension( x, y-radius );
			default:
				return new Dimension( x, y );
			}
		}
		

}
	
