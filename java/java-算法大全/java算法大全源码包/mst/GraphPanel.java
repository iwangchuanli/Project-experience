
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;

public class GraphPanel extends Panel {

    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

	String node_fname, edge_fname;
	DataInputStream inStream;

	Label titleLabel; 
	Graph graph;
	int type = 0;

	public GraphPanel( String title ) {
		Graphics g;
        	/* super.init(); */


//     	 	setLayout(null);
  //      	resize(700,700);
    //    	titleLabel=new Label(title, Label.CENTER);
      //  	titleLabel.setFont(new Font("Dialog",Font.PLAIN,14));
        //	add(titleLabel);
		//System.out.println( "" + num );
		graph = null;
		}

	public Graph Graph_Design() {
		return graph;
		}

	public void Highlight_Edge_Ori(Edge e) {
		Color ori_colour = Color.black;
		int i;
		
		for (i = 0; i < graph.edge_cnt; i++) {
			if ( graph.edges[i] == e ) {
				graph.edges[i].Set_Highlight( ori_colour );
				//graphs.edges[i].From_Node( ).Set_Highlight( ori_colour );
				//graphs.edges[i].To_Node( ).Set_Highlight( ori_colour );
				repaint();
				break;
				}
			}
		}

	public void Highlight_Edge_Focus(Edge e) {
		Color focus_colour = Color.yellow;
		int i;

		for (i = 0; i < graph.edge_cnt; i++) {
			if (graph.edges[i] == e) {
				graph.edges[i].Set_Highlight( focus_colour );
				//graphs.edges[i].From_Node( ).Set_Highlight( focus_colour );
				//graphs.edges[i].To_Node( ).Set_Highlight( focus_colour );
				repaint();
				break;
				}
			}
		}

	public void Highlight_Edge_Final(Edge e) {
		Color final_colour = Color.red;
		int i;

		for (i = 0; i < graph.edge_cnt; i++) {
			if (graph.edges[i] == e) {
				graph.edges[i].Set_Highlight( final_colour );
				//graphs.edges[i].From_Node( ).Set_Highlight( final_colour );
				//graphs.edges[i].To_Node( ).Set_Highlight( final_colour );
				repaint();
				break;
				}
			}
		}	

	public void Highlight_Node_Ori(Node n) {
		Color ori_colour = Color.blue;
		int i;
		
		for (i = 0; i < graph.node_cnt; i++) {
			if (graph.nodes[i] == n) {
				graph.nodes[i].Set_Highlight( ori_colour );
				repaint();
				break;
				}
			}
		}

	public void Highlight_Node_Focus(Node n) {
		Color focus_colour = Color.yellow;
		int i;

		for (i = 0; i < graph.node_cnt; i++) {
			if (graph.nodes[i] == n) {
				graph.nodes[i].Set_Highlight( focus_colour );
				repaint();
				break;
				}
			}
		}

	public void Highlight_Node_Final(Node n) {
		Color final_colour = Color.red;
		int i;
		
		for (i = 0; i < graph.node_cnt; i++) {
			if (graph.nodes[i] == n) {
				graph.nodes[i].Set_Highlight( final_colour );
				repaint();
				break;
				}
			}
		}
	
	public void Show_Cost_Node(Node n) {
		int i;

		for (i = 0; i < graph.node_cnt; i++) {
			if (graph.nodes[i] == n) {
				graph.nodes[i].Show_Cost( );
				repaint();
				break;
				}
			}
		}
	
	public void Set_Graph( Graph graph ) {
		this.graph = graph;
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
	   offGraphics.setColor(getBackground());
      offGraphics.fillRect(0, 0, d.width, d.height);
      Font font = new Font("Dialog", Font.PLAIN, 10);
      offGraphics.setFont(font);
      fm = offGraphics.getFontMetrics();
      paint(offGraphics);
      g.drawImage(offscreen, 0, 0, null);
    	}

	private int delay;

	public void setDelay( int d ) {
		delay = d;
		}

	public void paint( Graphics g ) {
		int h, w;
		
		h = size().height; 
		w = size().width;
		graph.Draw_Graph( g, h, w );
		//g.drawRect(10, 10, 100, 100);
		}

	// void update( );
	}
	
