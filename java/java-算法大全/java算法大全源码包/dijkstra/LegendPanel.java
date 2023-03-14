/* LegendPanel.java */

import java.awt.*;
import java.applet.*;
import java.io.*;

public class LegendPanel extends Panel {

	//variables
	Color c[];
	String s[];
	int num;
	Canvas can;
	String title;
	
      	Font type = new Font("Helvetica", Font.BOLD, 12);
	//constructor
	
	public LegendPanel(Color[] collection, String[] s, int num) {
		this.c = collection;
		this.s = s;
		this.num = num;
		
		}
		
	//methods
	public void paint( Graphics g ) {
		//can.Draw_Comp( g );		
		int i, pos_x = 0, pos_y = 20;
		int h, w;
		Color box_colour, outline, character = Color.black;
	
		//resize(150, 75);	
		h = size().height;
		w = size().width;
	     outline = Color.black;
	     for (i = 0; i < num; i++) {
	      box_colour = c[i];
	      g.setColor(box_colour);
	      pos_x = 10;
	      
	      g.fillRect( pos_x, pos_y, w/10, h/10 );
	      g.setColor(outline);
	      g.drawRect( pos_x, pos_y, (w/10)-1, (h/10)-1);
			g.setFont(type);
			g.setColor(character);
			g.drawString(s[i], pos_x+(w/10)+pos_x, pos_y+(h/10) );
			
			pos_y = pos_y + 2*(h/10);
			}
		}
		
	}
	
