/*Edge class*/

import java.awt.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Edge implements ClassInterface{
	//Variable declarations
	Arrow arrow_head, arrow_head_1;
	Node start, end, representative;
	int start_node, end_node;	
	double value;
	int index;
	int end_x;
   int end_y;
	static final double CR_RATIO = 0.9;  //0.605; 
	static final int Self = -1;
	Color value_colour, arc_colour, arrow_colour;
	boolean show_cost = false;
	static final int FULL_CIRCLE = 350;
	
	//method declarations
   public Edge( Node start, Node end, int value ) {
	
		this.start = start;
		this.end = end;
		this.value = value;
		index = 0;
		end_x = 0;
		end_y = 0;
		value_colour = Color.red;
		arc_colour = Color.black;	
		arrow_colour = arc_colour;

    	}

	public boolean New( ) {
		if (start.Rep().Get_ID() == -1 && end.Rep().Get_ID() == -1) {
			Set_Rep( start ); 	
			return true;
			}
		else
			return false;
		}
	
	public boolean Is_From( ) {
		System.out.println("Edge, is_from: from "+start.Get_ID()+", end "+end.Get_ID()+", "+start.Rep().Get_ID()+", "+end.Rep().Get_ID());
		if (start.Rep().Get_ID() != -1 && end.Rep().Get_ID() == -1) {
			Set_Rep( start.Rep() );
			return true;
			}
		else
			return false;
		}
	
	public boolean Is_To( ) {
		System.out.println("Edge, is_to: from "+start.Get_ID()+", end "+end.Get_ID()+", "+start.Rep().Get_ID()+", "+end.Rep().Get_ID());
		if (start.Rep().Get_ID() == -1 && end.Rep().Get_ID() != -1) {
			Set_Rep( end.Rep() );
			return true;
			}
		else
			return false;
		}

	public boolean Join( ) {
		System.out.println("Edge, join: from "+start.Get_ID()+", end "+end.Get_ID()+", "+start.Rep().Get_ID()+", "+end.Rep().Get_ID());
		if (start.Rep().Get_ID() != -1 && end.Rep().Get_ID() != -1 && start.Rep() != end.Rep() ) {
			//Set_Rep( start.Rep() );
			return true;
			}
		else
			return false;
		}

	public Node Rep() {
		return representative;
		}

	public void Set_Rep(Node N) {
		representative = N;	
		start.Set_Rep(representative);
		end.Set_Rep(representative);
		System.out.println("updating: from = "+start.Get_ID()+", to = "+end.Get_ID()+", rep = "+representative.Get_ID());
		}

	public Node From_Node() {
		return start;
		}

	public Node To_Node() {
		return end;
		}

	public int Get_Start() {
		int num;
	
		num = start.Get_ID();
		return (num);
		}
	public int Get_End() {
		int num;
	
		num = end.Get_ID();
		return (num);
		}

	public double Cost() {
		return value;
		}

	public boolean Cycle() {
		if ( (start.Rep() == start.Rep()) && (end.Rep() == end.Rep()) ) return false;
		else 
			return start.Rep() == end.Rep();
		}

	public void Update_Rep() {
		this.To_Node().Rep().Set_Rep( this.From_Node().Rep() );
		}

	public void Print_Edge( String s ) {
		System.out.println( s + " .. ");
		}

	public void Set_Highlight(Color highlight_colour) {
		this.arc_colour = highlight_colour;
		this.arrow_colour = highlight_colour;
		if (highlight_colour != Color.black) {
			value_colour = Color.black;
			}
		else {
			value_colour = Color.red;
			}
		}

public void Draw_Edge( Graphics g , int num_arcs, int arrow_status) {
		int start_x, start_y, end_x, end_y;
		int mid_value_x, mid_value_y;
		int mid_pt_x, mid_pt_y;
		int pt_x, pt_y;
		int x_diff, y_diff;
		double distance, temp, angle, temp1;
		double opp, adj, hyp, opp1, adj1;
		Color node_colour;
	
		double img_mid_x, img_mid_y;
		double chord, img_dia; //for arcs
		double mid_line, img_cir;
		double temp3, temp4, temp5, temp6;
		double img_x, img_y;
		double start_angle, arc_angle, half_arc_angle;
		double half_arc_angle_rad, temp_ang;
		int testcase;
		double img_x_e, img_y_e;
		double a, b, c, gamma, alpha;
		double determinant;
		double numerator, denominator;
		double tita;
		double phi;
		double yonx, xony;
		double pts_array[] = new double[4];
	 	double pt_angle;
		int i;
		
		//getting the scales for the node positions
		double start_scale_x, start_scale_y, end_scale_x, end_scale_y;
	
		start_scale_x = 1.00;
		start_scale_y = 1.00;
		end_scale_x = 1.00;
		end_scale_y = 1.00;
		yonx = 0.00;
		xony = 0.00;
		phi = 0.00;
		tita = 0.00;
		numerator = 0.00;	
		denominator = 0.00;
		start_angle = 0;
		arc_angle = 0;
		testcase = 0;
		img_mid_x = 0;
		img_mid_y = 0;
	
		img_y_e = 0.00;
		img_x_e = 0.00;
		opp = 0.00;
		adj = 0.00;

		opp1 = 0.00;
		adj1 = 0.00;
		pt_x = pt_y = 0;
	
		determinant = 0.00;

		for (i = 0; i < 4; i++) {
			pts_array[i] = 0.00;
			}
		
		start_scale_x = start.Get_Scale_X();
		start_scale_y = start.Get_Scale_Y();
		end_scale_x = end.Get_Scale_X();
		end_scale_y = end.Get_Scale_Y();
	
		start_x = (int)((start.Mid_X())*start_scale_x);
		start_y = (int)((start.Mid_Y())*start_scale_y);
		end_x = (int)((end.Mid_X())*end_scale_x);
		end_y = (int)((end.Mid_Y())*end_scale_y);

		x_diff = end_x - start_x;	
		y_diff = end_y - start_y;
		temp = Math.pow(x_diff,2) + Math.pow(y_diff,2);
		distance = Math.sqrt(temp);
		mid_pt_x = (end_x - start_x)/2 + start_x;
		mid_pt_y = (end_y - start_y)/2 + start_y;
	
		
		//drawing of arcs
		//let the ratio between chord and imaginary radius be 2.5/4
		
		chord = distance;
		img_cir = CR_RATIO*chord;
		
		temp3 = Math.pow((chord/2), 2);
      temp4 = Math.pow(img_cir, 2);
      temp5 = temp4 - temp3;
      mid_line = Math.sqrt(temp5);
		img_dia = 2*img_cir;
	
		half_arc_angle_rad = Math.asin((chord/2)/img_cir);
		temp6 = (half_arc_angle_rad/Math.PI)*180;
		half_arc_angle = temp6;
		arc_angle = 2*half_arc_angle;
		temp_ang = ((90 - arc_angle)/2)*(Math.PI)/180;
		hyp = img_cir;	
		yonx = (double)y_diff/(double)x_diff;
		xony = (double)x_diff/(double)y_diff;

		//new inclusion
		tita = Math.atan((double)y_diff/(double)x_diff);
		phi = Math.PI/2  - tita;

		pts_array = Circle_Attr(x_diff, chord, mid_pt_x, mid_pt_y, mid_line, 
						phi, tita, start_x, start_y, end_x, end_y);

		img_mid_x = pts_array[0];
		img_mid_y = pts_array[1];
		testcase = (int)pts_array[2];
		start_angle = Arc_Circle_Angle(pts_array[3], arc_angle, testcase);

		img_x = img_mid_x - img_cir;
      img_y = img_mid_y - img_cir;	

		g.setColor(arc_colour);
	
		if (num_arcs != 1)  {
			g.drawArc((int)img_x, (int)img_y, (int)img_dia, (int)img_dia, (int)start_angle, (int)arc_angle); 
			pt_angle = (start_angle + arc_angle/2)*Math.PI/180;
			pt_x = (int)Math.round(img_mid_x + img_cir*Math.cos(pt_angle));
			pt_y = (int)Math.round(img_mid_y - img_cir*Math.sin(pt_angle));
			}
		else if (testcase != 10){
			g.drawLine(start_x, start_y, end_x, end_y);
			}
		else if (testcase == 10) {
			System.out.println("numarcs = "+num_arcs);
			arc_angle = FULL_CIRCLE;
			img_dia = start.diameter;
			//g.drawArc((int)img_x, (int)img_y, (int)img_dia, (int)img_dia, (int)start_angle, (int)arc_angle);		
			g.drawArc((int)(start_x - img_dia), (int)(start_y - img_dia/2), (int)img_dia, (int)img_dia, (int)start_angle, (int)arc_angle);		
			}

		if (arrow_status == 1) {
			arrow_head = new Arrow(value_colour, value, start_x, start_y, end_x, end_y, img_mid_x, img_mid_y, img_cir, start_angle, arc_angle, arc_colour, num_arcs, mid_pt_x, mid_pt_y, show_cost, testcase, img_dia);
			arrow_head.Draw_Arrow( g );
			}
		else {
			if (show_cost) {
				if (num_arcs != 1) {
					g.setColor(value_colour);
					g.drawString(""+value, pt_x, pt_y);
					}
				else {
					g.setColor(value_colour);
					g.drawString(""+value, mid_pt_x, mid_pt_y);
					}
				}
			}	
		}

	private double Arc_Circle_Angle(double start_angle, double arc_angle, int testcase) {
		
		switch (testcase) {		
			case 1: start_angle = (start_angle)*180/Math.PI;break;
			case 2: start_angle = 90 - (arc_angle/2);break;
			case 3: start_angle = (Math.PI/2 + start_angle)*180/Math.PI;break;
			case 4: start_angle = 180 - arc_angle/2;break;
			case 5: start_angle = (Math.PI + start_angle)*180/Math.PI;break;
			case 6: start_angle = 270 - (arc_angle)/2;break;
			case 7: start_angle = (3*Math.PI/2 + start_angle)*180/Math.PI;break;
			case 8: start_angle = 360 - (arc_angle)/2;break;
			case 10: start_angle = 0.00;
			default: break;
			}	

		return start_angle;
		}
	
	
	
	private double[] Circle_Attr(int x_diff, double chord, double mid_pt_x, double mid_pt_y, double mid_line, double phi, double tita, int start_x, int start_y, int end_x, int end_y) { 
		double img_mid_x, img_mid_y;
		double start_angle, node_diameter = 0.00;
		int testcase, i, attr_size = 4;
		double[] attr = new double[attr_size];
			
		img_mid_x = 0.00;
		img_mid_y = 0.00;
		start_angle = 0.00;
		testcase = 0;
		
		for (i = 0; i < attr_size; i++) {
			attr[i] = 0.00;
			}
			
		if (x_diff < 0.00) {
			img_mid_x = mid_pt_x + mid_line*Math.cos(phi);
			img_mid_y = mid_pt_y - mid_line*Math.sin(phi);
			if (tita < 0.00) {
				testcase = 7;
				start_angle = Math.abs(tita) - Math.atan((chord/2)/mid_line);
				}
			else if (tita > 0.00) {
				testcase = 5;
				start_angle = Math.abs(phi) - Math.atan((chord/2)/mid_line);
				}
			}
		else if (x_diff > 0.00) {
			img_mid_x = mid_pt_x - mid_line*Math.cos(phi);
			img_mid_y = mid_pt_y + mid_line*Math.sin(phi);
			if (tita < 0.00) {
				testcase = 3;
				start_angle = Math.abs(tita) - Math.atan((chord/2)/mid_line);
				}
			else if (tita > 0.00) {
				testcase = 1;
				start_angle = Math.abs(phi) - Math.atan((chord/2)/mid_line);
				}
			}

		if ((start_x == end_x) && (start_y > end_y)) {  
      	testcase = 4;
			img_mid_x = start_x + mid_line;
         img_mid_y = start_y - (chord/2);
         }

		if ((start_x == end_x) && (start_y < end_y)) {  
         testcase = 8;
			img_mid_x = start_x - mid_line;
      	img_mid_y = start_y + (chord/2);
         }

		if ((start_x > end_x) && (start_y == end_y)) {  
         testcase = 6;
			img_mid_x = start_x - (chord/2);
         img_mid_y = start_y - mid_line;
         }

		if ((start_x < end_x) && (start_y == end_y)) {  
         testcase = 2;
			img_mid_x = start_x + (chord/2);
         img_mid_y = start_y + mid_line;
         }

		if ((start_x == end_x) && (start_y == end_y)) {  
         testcase = 10;
			node_diameter = start.diameter;
			img_mid_x = start_x - node_diameter;
			img_mid_y = start_y - (node_diameter)/2;
         System.out.println("in testcase 10, sd = "+node_diameter);
         System.out.println("x = "+img_mid_x+", y = "+img_mid_y);
			}
		
		attr[0] = img_mid_x;
		attr[1] = img_mid_y;
		attr[2] = (double)testcase;
		attr[3] = start_angle;
		
		return attr;
		}
	}


