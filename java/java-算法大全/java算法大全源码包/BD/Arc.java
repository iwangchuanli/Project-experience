// Arc.java
// Function block for block diagram animation
//
import java.awt.*;

public class Arc implements DrawingObj {
	
	int x[], y[];		// Position of Arc
	int width; 			// Width of Arc
	boolean plus, sumPoint;	// plus/minus sign towards SummingPoints
	boolean PaintMove;
	Color cur_colour;
	int cnt, max;
	FunctionBlock fbk;
	LabelledBox lb;
	SummingPoint spt;
	Node start, end;        // Start and end of each Arc


	public Arc( int width, boolean plus, boolean sumPoint,Node start, Node end ) {
		this.width = width;
		//w = width;
		this.plus = plus;
		this.sumPoint = sumPoint;
		cur_colour = Color.green;
		max =4 ;
		x = new int[ max ];
		y = new int[ max ];
		cnt = 0;
		this.start = start;
		this.end = end;
		}

	public void setColour(Color c) {
		 cur_colour = c;
		}

	
	Node getStart() { return start; }
	void setStart( Node start ) { this.start = start; }
	Node getEnd() { return end; }
	void setEnd( Node start ) { this.start = start; }

	public void addPoint( int x, int y ) {
		if ( cnt < max ) {
			this.x[cnt] = x;
			this.y[cnt] = y;
			cnt++;
			
			}
		else {
			// Create a larger array and copy the old points
			}
		}

	
	public void move (int x, int y) {
		int i, dx, dy;
	
		dx = x - this.x[0];
		dy = y - this.y[0];
		
		for ( i = 0; i < cnt; i++ ) {
			this.x[i] = this.x[i] + dx;
			this.y[i] = this.y[i] + dy;
			}
	        	
		}
	
	public void moveOffset (int dx, int dy) {
		int i;
                		
		for ( i = 0; i < cnt; i++  ) {
			this.x[i] = this.x[i] + dx ;
			this.y[i] = this.y[i] + dy ;
			}
		}

	public int getX() { return x[0]; }
	public int getY() { return y[0]; }

	
	public void draw( Graphics g ) {
		int lx, ly, lx2, ly2, last_dirn, j, i;
		int arrowx_i, arrowx_j, arrowx_k;
                int arrowy_i, arrowy_j, arrowy_k;
                		
		Dimension start_pt;
		String p_or_m;
		g.setColor( cur_colour );
		g.drawPolyline( x, y, cnt );
		lx = x[cnt-1]; ly = y[cnt-1];
		lx2 = x[cnt-2]; ly2 = y[cnt-2];

	//Draw arrow head
        // initialized direction to point right
	
	last_dirn = -1;
	i = -10; j = 13;	// Refining position of + / - sign	
        arrowx_i = -5; arrowx_j = -5; arrowx_k = 0;
	arrowy_i = -5; arrowy_j = 5; arrowy_k = 0;
	

		
		
		
	//Pointing Left
	
		 if ((ly == ly2) && (lx2 > lx)) {
			last_dirn = 1; i = 5;
                        arrowx_i = 5; arrowx_j = 5; arrowx_k = 0;
			arrowy_i = -5; arrowy_j = 5; arrowy_k = 0;	

			
			}
        //Pointing Up
	
	        else if ((lx == lx2) && (ly2 > ly)) {
			last_dirn = 2; i = 5;
                        arrowx_i = -5; arrowx_j = 5; arrowx_k = 0;
			arrowy_i = 5; arrowy_j = 5; arrowy_k = 0;	

			
			}
	
        //Pointing Down
		else if ((lx == lx2) && (ly2 < ly)) {
			last_dirn = -2; j = -5;
                        arrowx_i = -5; arrowx_j = 5; arrowx_k = 0;
			arrowy_i = -5; arrowy_j = -5; arrowy_k = 0;	
                        		
			
		
		}
       	Object s_pt = end.getObject();
	start_pt = ((DrawingObj)s_pt).getLimit( last_dirn );


	int[] arrowx = {start_pt.width + arrowx_i, start_pt.width + arrowx_j, start_pt.width + arrowx_k};
	int[] arrowy = {start_pt.height + arrowy_i, start_pt.height + arrowy_j, start_pt.height + arrowy_k};		
	g.fillPolygon(arrowx, arrowy, 3);
		g.setColor( Color.black );
		
		if (sumPoint) {
			if ( plus ) { p_or_m = "+"; }
			else { p_or_m = "-"; }
      		g.drawString( p_or_m, start_pt.width+i, start_pt.height+j );
			}
                   			        	

	}

	public Dimension getLimit( int dirn ) {
		return new Dimension( 0, 0 );
		}
			
}
	
