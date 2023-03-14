
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
    ComBox comment1 = null;

    Font boldFont = new Font("Helvetica", Font.BOLD, 10);
    Font font = new Font("Dialog", Font.PLAIN, 10);
    Color darkGreen = new Color(0, 160, 0);
    Font fixFont = new Font("Courier", Font.PLAIN, 12);

    public GraphPanel( String title ) {
    	Graphics g;
    	graph = null;
    }

    public void setNodeComment(Node node, String comment) {
	if (node == null) return;
	int x = (int)((node.x - node.radius)*node.x_scale);
	x -= 20;
	if (x < 10) x = 10;
	if (x + comment.length()*8 > size().width-10)
	    x = size().width - 10 - comment.length()*8;
	int y = (int)((node.y + node.radius)*node.y_scale) + 5;
	this.comment1 = new ComBox(x, y, comment, fixFont);
	repaint(); delay();
    }

    public void hideNodeComment() {
	this.comment1 = null;
    }

    public Graph Graph_Design() {
    	return graph;
    }

    public void delay() {
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {}
    }

    public void Highlight_Edge_Ori(Edge e) {
    	Color ori_colour = Color.black;
    	int i;
		
    	for (i = 0; i < graph.edge_cnt; i++) {
    	    if ( graph.edges[i] == e ) {
       	    	graph.edges[i].Set_Highlight( ori_colour );
	            repaint();
		    break;
	    }
	}
    }

    public void flashEdge(Edge e) {
	for (int i = 0; i < graph.edge_cnt; i++) {
	    if (graph.edges[i] == e) {
		graph.edges[i].Set_Highlight( Color.white );
		repaint(); delay();
		graph.edges[i].Set_Highlight( Color.black );
		repaint(); delay();
		graph.edges[i].Set_Highlight( Color.white );
		repaint(); delay();
		break;
	    }
	}
    }

    public void Highlight_Edge_Focus(Edge e) {
    	Color focus_colour = darkGreen;
    	int i;

    	for (i = 0; i < graph.edge_cnt; i++) {
    	    if (graph.edges[i] == e) {
	        graph.edges[i].Set_Highlight( focus_colour );
		repaint(); delay();
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

    public void flashNode(Node n) {
	for (int i = 0; i < graph.node_cnt; i++) {
	    if (graph.nodes[i] == n) {
		graph.nodes[i].Set_Highlight( Color.black );
		repaint(); delay();
		graph.nodes[i].Set_Highlight( darkGreen );
		repaint(); delay();
		graph.nodes[i].Set_Highlight( Color.black );
		repaint(); delay();
		break;
	    }
	}
    }

    public void Highlight_Node_Focus(Node n) {
	Color focus_colour = darkGreen;
	int i;

	for (i = 0; i < graph.node_cnt; i++) {
	    if (graph.nodes[i] == n) {
		graph.nodes[i].Set_Highlight( focus_colour );
		repaint(); delay();
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
      offGraphics.setFont(font);
      fm = offGraphics.getFontMetrics();
      paint(offGraphics);
      g.drawImage(offscreen, 0, 0, null);
    }



    public void paint( Graphics g ) {
	int h, w;
	
	h = size().height; 
	w = size().width;
	g.setFont(boldFont);
	graph.Draw_Graph( g, h, w );

	g.setColor(Color.black);
	g.drawRect(1, 1, w-2, h-2);

	if (comment1 != null) {
	    comment1.draw(g);
	}
    }

}
	
