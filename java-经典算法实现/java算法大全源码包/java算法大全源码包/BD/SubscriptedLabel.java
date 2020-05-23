// SubscriptedLabel.java
//    SubscriptedLabel for block diagram animation
//
import java.awt.*;
import java.util.*;

public class SubscriptedLabel {
	//
	int x, y;	// Position
	
	static int h = 30; // Fixing the height
	static int w = 30; // Fixing the width
	String label, subscript;
	Color c;
	Font font;
	Node input;
	Vector output;


	public SubscriptedLabel( String label, String subscript, int x, int y) {
		
		this.x = x ;
		this.y = y;
		this.label =  label ;
		this.subscript = subscript ;
		c = Color.black;
	
		}
	
	private Point labelLocation (Graphics g) {
		
		FontMetrics fm = g.getFontMetrics();
		int x = (w/2) - (fm.stringWidth(label)/2);
		int y = (h/2) + (fm.getAscent()/2);
		return new Point(x,y);
	}

//	public void setColour( awt.Color new ) {
//		c = new;
//		}





	


	public void draw( Graphics g ) {
		//int lx, ly;  // Centre of label
		//lx = x + w/2;
		//ly = y + h/2;
		Point labelLoc = labelLocation(g);
		g.setColor( c );
		
		// g.setFont( font );
		g.drawString( label,x + labelLoc.x , y + labelLoc.y );
		g.drawString( subscript, x + 5 ,y +2);
		}

}
	
