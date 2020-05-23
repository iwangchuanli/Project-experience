/*Node class*/

import java.awt.*;
import java.io.*;
import java.util.*;

 public class Node implements ClassInterface{
	// Variable declarations
   int x;
   int y;
   double diameter;
	int node_id;
	double value; // Needed for Dijkstra
	Color label_colour, node_colour, ans_colour;
	Node rep;
	boolean show_cost = false;
	boolean show_id = true;
	static final double infinity = 1.0e300;
	static double x_scale = 1.0;
	static double y_scale = 1.0;
	static int radius = 25;

 	//Method declarations
   public Node( int id, int x, int y ) { // Constructor
   	this.x = x;
      this.y = y; 
		node_id = id;
		// radius = 25;
		label_colour = Color.blue;	
		node_colour = Color.blue;	
		ans_colour = Color.black;
		value = infinity;
    	}
	
	public int Compare( Node N ) {
		if ( N.value > this.value ) {
			return 1;
			
			}
		else if ( N.value == this.value ) {
			return 0;
			}
		
		return -1;	
			
		}
	
	public void Set_Rep( Node n ) {
		rep = n;
		}
	
	public Node Rep( ) {
		return rep;
		}
	
	public void Set_Scale( double xs, double ys ) {
		x_scale = xs;
		y_scale = ys;
		}

	public double Get_Scale_X( ) {
		return x_scale;
		}

   public double Get_Scale_Y( ) {
		return y_scale;
		}

	public void Show_Cost( ) {
		show_cost = true;
		}

   public void Show_ID(boolean b) {
		show_id = b;
		}
	
	public void Draw_Node( Graphics g ) {
		int tx, ty;
		//int diameter;
		Font value_font;
		
		value_font = new Font("Helvetica", Font.BOLD, 10);
		g.setFont(value_font);
		tx = (int)((x - radius)*x_scale);
		ty = (int)((y - radius)*y_scale);
		diameter = radius*(x_scale+y_scale);
  		g.setColor(node_colour);
		g.fillOval(tx,ty, (int)diameter, (int)diameter  );
		if (show_id) {
			g.setColor(label_colour);
			g.drawString(""+node_id, tx, ty);
    		}
		}
		
   public void Redraw_Node( Graphics g ) {
		int tx, ty;
   	//int diameter;
		
		tx = (int)((x - radius)*x_scale);
   	ty = (int)((y - radius)*y_scale);
   	diameter = radius*(x_scale+y_scale);
   	g.setColor(node_colour);
   	g.fillOval( tx,ty, (int)diameter, (int)diameter );
		if (show_cost) {
			Show_Ans(g);
			}
		
		}
   
   public int Mid_X() {		
		return (x);
    	}
    
	public int Mid_Y() {
      return (y);
      }
	
	public int Set_Radius( int r ) {
		radius = r;
		return radius;
		}
	
	public double Diameter( ) {
		return diameter;
		}
	
	public int Get_ID( ) {
		return (node_id);
		}
	
	public void Set_Cost( double val ) {
		value = val;
		}
	
	public double Cost( ) {
		return value;
		}
	
	public void Set_X( int val ) {
		x = val;
		}
	
	public void Set_Y( int val ) {
		y = val;
		}
	
	public Color Get_Node_Colour() {
		return node_colour;
		}

   public void Show_Ans( Graphics g ) {
		int temp_x;
		int temp_y;
		int r3;
		Font ans;
		r3 = (int)((radius*(x_scale+y_scale))/2);
		
		g.setColor(ans_colour);
		temp_x = (int)((x-radius)*x_scale);
		temp_y = (int)((y)*y_scale);
		ans = new Font("Helvetica", Font.BOLD, (int)(12*x_scale/y_scale));
		g.setFont(ans);
	
		g.drawString(""+(int)value, temp_x+(r3*2)/5, temp_y+(r3*8)/15);
		/*
		g.drawString("x = "+temp_x+", y = "+temp_y+", xs = "+x_scale+", ys = "+y_scale, 10, 10);
		*/
		/*g.drawLine(0, temp_y, 400, temp_y);*/
		}
	
	public void Set_Highlight( Color highlight_colour ) {
		this.node_colour = highlight_colour;
		}
	
	public void Highlight(Graphics g, Color highlight_colour) {
		int temp_x;
		int temp_y;
		int i, r2;
		
		g.setColor(highlight_colour);
		temp_x = (int)((x - radius)*x_scale);
		temp_y = (int)((y - radius)*y_scale);
		r2 = (int)(radius*(x_scale+y_scale));
		/*
		for (i = 1; i < 6; i++) {
				
			r2 += 2;
			g.drawOval(temp_x - i, temp_y-i, r2, r2 );
			}
		*/
		g.fillOval(temp_x, temp_y, r2, r2 );
		}


	public void Unhighlight_Node(Graphics g) {
      int temp_x;
      int temp_y;
      int i;
      temp_x = x;
      temp_y = y;
      g.setColor(Color.white);
		for (i = 1; i < 6; i++) {
         g.drawOval(temp_x - i, temp_y-i, 50 + 2*i, 50 + 2*i);
         }
		g.setColor(Color.black);
		}       

	}
