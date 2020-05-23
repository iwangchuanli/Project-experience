import java.awt.*;
import java.lang.*;
import java.util.*;
import java.io.*;

class Arrow {
	//variable declaration
	int start_x, start_y;
	int end_x, end_y;
	double img_mid_x, img_mid_y, img_cir, diameter;
	double arc_angle, start_angle, value;
	boolean show_cost;
	int num_arcs, mid_pt_x, mid_pt_y, testcase;
	static final double HEAD_DISTANCE = 15;
	static final double VAL_X = 15;
	static final double VAL_Y = 15;
	Color arrow_colour, value_colour;
	
	//method declaration
	public Arrow(Color value_colour, double value, int start_x, int start_y, int end_x, int end_y, double img_mid_x, double img_mid_y, double img_cir, double start_angle, double arc_angle, Color arc_colour, int num_arcs, int mid_pt_x, int mid_pt_y, boolean show_cost, int testcase, double diameter) {
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;
		this.img_mid_x = img_mid_x;
		this.img_mid_y = img_mid_y;
		this.img_cir = img_cir;
		this.start_angle = start_angle;
		this.arc_angle = arc_angle;
		this.arrow_colour = arc_colour;
		this.value_colour = value_colour;
		this.num_arcs = num_arcs;
		this.mid_pt_x = mid_pt_x;
		this.mid_pt_y = mid_pt_y;
		this.value = value;
		this.show_cost = show_cost;
		this.testcase = testcase;
		this.diameter = diameter;
	}

	public void Draw_Arrow( Graphics g ) {
		double arrow_angle;
		int[] arrow_x = new int[3];
		int[] arrow_y = new int[3];
		int[] arrow_pt = new int[2];
		double gradient, constant;
		double distance, temp_dist;
		double mid_x_diff, mid_y_diff;
		double arrow_ratio;
		double trans_pts_x, trans_pts_y;
		
		trans_pts_x = 0.00;
		trans_pts_y = 0.00;
		arrow_ratio = 0.00;
		mid_x_diff = 0.00;
		mid_y_diff = 0.00;
		distance = 0.00;
		temp_dist = 0.00;
		gradient = 0.00;
		constant = 0.00;
		arrow_x[0] = 0;
		arrow_x[1] = 0;
		arrow_x[2] = 0;
		arrow_y[0] = 0;
		arrow_y[1] = 0;
		arrow_y[2] = 0;
		arrow_angle = 0.00;	

		
		arrow_angle = (start_angle + arc_angle/2)*Math.PI/180;
//		if (num_arcs != 1) {
		arrow_x[0] = (int)Math.round(img_mid_x + img_cir*Math.cos(arrow_angle));
		arrow_y[0] = (int)Math.round(img_mid_y - img_cir*Math.sin(arrow_angle));
//			}
//		else {
//			arrow_x[0] = mid_pt_x;
//			arrow_y[0] = mid_pt_y;
//			g.drawString("1", (int)arrow_x[0], (int)arrow_y[0]);
//		}
		/*calculate the distance between the midpt (on arc) and start node*/
		mid_x_diff = Math.abs(arrow_x[0] - start_x);
		mid_y_diff = Math.abs(arrow_y[0] - start_y);
		
		distance = Math.sqrt((mid_x_diff*mid_x_diff) + (mid_y_diff*mid_y_diff));
		
		temp_dist = distance - HEAD_DISTANCE;
		arrow_ratio = temp_dist/distance;
		arrow_x[1] = (int)((arrow_x[0] - start_x)*arrow_ratio + start_x);
		arrow_y[1] = (int)((arrow_y[0] - start_y)*arrow_ratio + start_y);
		
		//calculate the gradient of the tangent
		//equation of the circle is (x-c)^2 + (y-d)^2 = R^2
		//equation of the line is y = mx + c
		gradient = ((-1)*(arrow_x[0] - img_mid_x))/(arrow_y[0] - img_mid_y);
		//find the constant value of the line
		constant = arrow_y[0] - gradient*arrow_x[0];

		arrow_pt = Translated_Point(constant, arrow_x, arrow_y , gradient);
		arrow_x[2] = arrow_pt[0];
		arrow_y[2] = arrow_pt[1];

		if (num_arcs == 1 && testcase != 10) {
			/*x and y distances between mid pts and arc mid pts */
			trans_pts_x = arrow_x[0] - mid_pt_x;  
			trans_pts_y = arrow_y[0] - mid_pt_y;
			arrow_x[0] = mid_pt_x;
			arrow_y[0] = mid_pt_y;
			arrow_x[1] = arrow_x[1] - (int)trans_pts_x;
			arrow_y[1] = arrow_y[1] - (int)trans_pts_y;
			arrow_x[2] = arrow_x[2] - (int)trans_pts_x;
			arrow_y[2] = arrow_y[2] - (int)trans_pts_y;
		}
		else if (num_arcs == 1 && testcase == 10) {
			System.out.println("hello...");
			arrow_x[0] = (int)(start_x - diameter);
			arrow_y[0] = (int)start_y;
			arrow_x[1] = (int)(diameter/4 + arrow_x[0]);
			arrow_y[1] = (int)(diameter/6 - arrow_y[0]);
			arrow_x[2] = (int)(diameter/4 - arrow_x[0]);
			arrow_y[2] = (int)(diameter/6 - arrow_y[0]);
			}
		g.setColor(arrow_colour);
		g.fillPolygon(arrow_x, arrow_y, 3);
		if (value != 0) {
			g.setColor(value_colour);
			if (show_cost) {
				Display_Value( g, arrow_x, arrow_y );
				}
			}
	}
	
	private int[] Translated_Point(double constant, int[] arrow_x, int[] arrow_y, double gradient ) {
		double[] translated = new double[2];
		double[][] trans_matrix = new double[2][2];
		double ang_rot;
		double[] point2_temp = new double[4];
		int cnt, row, col;
		int[] pt = new int[2]; 
		
		translated[0] = 0.00;
		translated[1] = 0.00;
		trans_matrix[0][0] = 0.00;
		trans_matrix[0][1] = 0.00;
		trans_matrix[1][0] = 0.00;
		trans_matrix[1][1] = 0.00;
		point2_temp[0] = 0.00;
		point2_temp[1] = 0.00;
		point2_temp[2] = 0.00;
		point2_temp[3] = 0.00;
		cnt = 0;
		row = 0;
		col = 0;
   	ang_rot = 0.00;
		
		//translate the line to the origin
		//thus the translated points to be found are:
		translated[0] = arrow_x[1];
		if (constant < 0) {
			translated[1] = arrow_y[1] + Math.abs(constant);
			}
		else {
			translated[1] = arrow_y[1] - Math.abs(constant);
			}

		//find the angle of rotation
		ang_rot = Math.atan(gradient);

		if ((end_x - start_x) == 0) {
			if (arrow_x[0] > 0) {
				translated[0] = arrow_x[1] - arrow_x[0];
				}
			else {
				translated[0] = arrow_x[1] + arrow_x[0];
				}
			translated[1] = arrow_y[1];
			trans_matrix[0][0] = -1;
			trans_matrix[0][1] = 0;
			trans_matrix[1][0] = 0;
			trans_matrix[1][1] = 1;
			}
		else {
			//declare the transformation matrix
			trans_matrix[0][0] = Math.cos(2*ang_rot);
			trans_matrix[0][1] = Math.sin(2*ang_rot);
			trans_matrix[1][0] = Math.sin(2*ang_rot);
			trans_matrix[1][1] = Math.cos(2*ang_rot)*(-1);
			}
		
		//multiply the transformation matrix with the point
		//store it in an array
		for (row = 0; row < 2; row++) {
			for (col = 0; col < 2; col++) {
				point2_temp[cnt] = trans_matrix[row][col]*translated[col];
				cnt++;
				}
			}	
		
		if ((end_x - start_x) == 0) {
			if (arrow_x[0] > 0) {
				arrow_x[2] = (int)Math.round(point2_temp[0] + point2_temp[1] + arrow_x[0]);
				}
			else {
				arrow_x[2] = (int)Math.round(point2_temp[2] + point2_temp[1] - arrow_x[0]);
				}
			arrow_y[2] = (int)Math.round(point2_temp[2] + point2_temp[3]);
			}
		else {
			//from the array, get the reflected point
			arrow_x[2] = (int)Math.round(point2_temp[0] + point2_temp[1]);
			if (constant < 0) {
				arrow_y[2] = (int)Math.round(point2_temp[2] + point2_temp[3] - Math.abs(constant));
				}
			else {
				arrow_y[2] = (int)Math.round(point2_temp[2] + point2_temp[3] + Math.abs(constant));
				}
			}
		pt[0] = arrow_x[2];
		pt[1] = arrow_y[2];
		return pt;
		}

private void Display_Value( Graphics g, int[] ax, int[] ay ) {
	int pos_x, pos_y;

	pos_x = 0;
	pos_y = 0;

	switch(testcase) {
		case 1:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		case 2:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		case 3:
			pos_x = ax[0];
			pos_y = ay[0];
			break;
		case 4:
			pos_x = ax[1];
			pos_y = ay[1];
			break;
		case 5:
			pos_x = ax[1];
			pos_y = ay[1];
			break;
		case 6:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		case 7:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		case 8:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		case 10:
			pos_x = ax[2];
			pos_y = ay[2];
			break;
		default:
			break;
		}
	g.drawString(""+value, pos_x, pos_y);
	}
	
}
