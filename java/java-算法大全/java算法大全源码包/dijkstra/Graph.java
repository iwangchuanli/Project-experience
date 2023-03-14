/* Graph class */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;



public class Graph {
	Node nodes[];
	Edge edges[];
	Arrow arrows[];
	
	int n_nodes, n_edges, n_arrows, arrow_status;
	int node_cnt, edge_cnt, arrow_cnt;
	int type;
	Node source_node;
	static int padding = 100;

	public static final int orig_colour = 1;
	public static final int inter_colour = 2;
	public static final int final_colour = 3;

	Color darkGreen = new Color(0, 160, 0);
	
	public Graph( int n_nodes, int n_edges, int arrow_status ) {
		this.n_nodes = n_nodes;
		this.n_edges = n_edges;
		this.arrow_status = arrow_status;
		//System.out.println( "New Graph " + n_nodes + " nodes, " + n_edges + " edges" ); 
		n_arrows = n_edges;
		nodes = new Node[n_nodes];
		edges = new Edge[n_edges];
		arrows = new Arrow[n_arrows];
		node_cnt = edge_cnt = arrow_cnt = 0;
		}

	public void Insert_Ans(Graphics g, int n_id) {
		int i;

		for (i = 0; i < node_cnt; i++) {
			if (nodes[i].Get_ID() == n_id) {
				nodes[i].Show_Ans(g);
				break;
				}
			}
		}
/*
	public void Ani_Edge(Graphics g, int from, int to, int val) {
		int i, j, k;
		Color change_to;
		int start_id, end_id, num_arcs = 1;

		change_to = Color.black;
		for(i = 0; i < edge_cnt; i++) {
			if (edges[i].Get_Start() == from && edges[i].Get_End() == to) {
				switch(val) {
					case 1: 
						change_to = Color.black; //original
						break;
					case 2:
						change_to = darkGreen; //intermediate
						break;
					case 3:
						change_to = Color.red; //final
						break;
					default:
						break;
					}
				
				start_id = from;
				end_id = to;
				for( k = 0; k<edge_cnt; k++) {
						
					if (start_id == edges[k].Get_End() && end_id == edges[k].Get_Start()) {
						num_arcs = 2;
						}
					}
						
					
				edges[i].Highlight(g, change_to, num_arcs, arrow_status);
				break;
				}
			}
		}
*/	
/*
	public void Ani_EdgeEndPoints( Graphics g, Edge e, int val ) {
		Node s, end;
		s = e.From_Node();
		end = e.To_Node();
		Ani_Edge( g, s.Get_ID(), end.Get_ID(), val );
		Ani_Node( g, s.Get_ID(), val );
		Ani_Node( g, end.Get_ID(), val );
		}
*/
/*
	public void Ani_Node(Graphics g, int n_id, int val) {
		int i;
		Color change_to;
		
		change_to = Color.blue;
		for (i = 0; i < node_cnt; i++) {
			if (nodes[i].Get_ID() == n_id) {
				switch(val) {
					case 1: 
						change_to = Color.blue; //original
						break;
					case 2:
						change_to = darkGreen; //intermediate
						break;
					case 3:
						change_to = Color.red; //final
						break;
					default:
						break;
					}
				nodes[i].Highlight(g, change_to);
				break;
				}
			}
		}
*/	
	public void Add_Node( Node n ) {
		nodes[node_cnt++] = n;
		//line added for testing purposes
		//System.out.println( "" + node_cnt ); 
		}
	
	public int Node_Cnt( ) {
		return node_cnt;
		}

	public  void Add_Edge( Edge edge ) {
		edges[edge_cnt++] = edge;
		}

	public int Edge_Cnt() {
		return edge_cnt;
		}

	public  void Add_Arrow( Arrow arrow ) {
		arrows[arrow_cnt++] = arrow;
		}
	
	public void Draw_Graph( Graphics g, int height, int width ) {
		int i, j, x;
		int start_id, end_id, num_arcs = 1;
		double x_scale, y_scale;
		int max_x = 0, max_y = 0;

	   if (n_nodes != 0)	{
		
			for( i=0; i<node_cnt; i++ ) {
				x = nodes[i].Mid_X();
				if ( x > max_x ) max_x = x;
					x = nodes[i].Mid_Y();
				if ( x > max_y ) max_y = x;
				}
			x_scale = (double)width / ( max_x + padding );
			y_scale = (double)height/ ( max_y + padding );
			nodes[0].Set_Scale( x_scale, y_scale );	
	
			for( i = 0; i< node_cnt; i++ ) {
				nodes[i].Draw_Node( g );
				}
			for( i = 0; i< edge_cnt; i++ ) {
				start_id = edges[i].Get_Start();
				end_id = edges[i].Get_End();
				for( j = 0; j<edge_cnt; j++) {
					if (j != i) {
						if (start_id == edges[j].Get_Start() && end_id == edges[j].Get_End()) {
							num_arcs = 2;
							}
						else {
							if (start_id == edges[j].Get_End() && end_id == edges[j].Get_Start()) {
								num_arcs = 2;
								}
							}
						}
					}
				edges[i].show_cost = true;
				edges[i].Draw_Edge( g, num_arcs, arrow_status );
				num_arcs = 1;
		 		}
		 
		 	for( i = 0; i< node_cnt; i++ ) {  //this is to cover the 
			nodes[i].Redraw_Node( g ); // edges within the nodes
       		}
			}
		}

	public Node[] Node_Array( ) { 
		return nodes; 
		}

	public Edge[] Edge_Array( ) { 
		return edges; 
		}
		
	// Used by Dijkstra
	public Node NodeI(int i ) {
		return nodes[i];
		}

	public Edge EdgeI(int i ) {
		return edges[i];
		}
	
	public Node Source( ) {
		return source_node;
		}

	public void Set_Source( int source_id ) {
		int i;

		for (i = 0; i < node_cnt; i++) {
			if (nodes[i].Get_ID() == source_id)  {
				source_node = nodes[i];
				break;
				}
			}
		}

	public Graph( DataInputStream inStream ) {
		int num = 0;
		
		this.n_nodes = 0;
		this.n_edges = 0;
		this.arrow_status = -1;
		n_arrows = n_edges;
		arrows = new Arrow[n_arrows];
		node_cnt = edge_cnt = arrow_cnt = 0;
		
		num = Load_Nodes( inStream );
		num = Load_Edges( inStream, num );	

		}

	private int Load_Edges( DataInputStream inStream, int num ) {
		String line;
		String item1, item2, item3;
		int source_node;
		int dest_node;
		int value;
		int n_nodes, edge_cnt;
		//Node nodes_array[] = new Node[num]; // Wil
		Edge edge;
		n_nodes = num;
		Node nodes_array[];
		nodes_array = new Node[n_nodes];	
		nodes_array = this.Node_Array(); // Wil
		edge_cnt = 0;

		try {
			while ((line = inStream.readLine()) != null) {
				StringTokenizer Data = new StringTokenizer(line, " ");
				item1 = Data.nextToken();
				item2 = Data.nextToken();
				item3 = Data.nextToken();
				source_node = Integer.parseInt(item1);
				dest_node = Integer.parseInt(item2);
				value = Integer.parseInt(item3);
				edge = new Edge( nodes_array[source_node-1], nodes_array[dest_node-1], value );
				this.Add_Edge( edge );
				edge_cnt++;
				}
			//inFile.close();
			}
		catch (IOException e) {
			System.err.println("Error in accessing URL: "+e.toString());
			System.exit(1);
		} 
		return edge_cnt;
	}

	private int Load_Nodes( DataInputStream inStream ) {
		//need to open file and load data
		int node_id;
		int x_cor;
		int y_cor;
		//int n_nodes, n_edges, node_cnt, arrow_status;
		Node n;
		String line;
		String item1, item2, item3, item4;
		
		node_id = 0;
		x_cor = 0;
		y_cor = 0; 
		//n_nodes = 0;
		//n_edges = 0;
		//arrow_status = -1;
		
		try {

			if ((line = inStream.readLine()) != null) {
				StringTokenizer Data = new StringTokenizer(line, " ");
				item1 = Data.nextToken();
				n_nodes = Integer.parseInt( item1 );
				item2 = Data.nextToken();
				n_edges = Integer.parseInt( item2 );
				item3 = Data.nextToken();
				arrow_status = Integer.parseInt( item3 );
				//item4 = Data.nextToken();
				//type = Integer.parseInt( item4 );
				// graph = new GraphClass( n_nodes, n_edges, arrow_status );
				nodes = new Node[n_nodes];
				edges = new Edge[n_edges];
				
				
				// ???
	
				while(( this.Node_Cnt() < n_nodes ) &&
					((line = inStream.readLine()) != null) ) {
					Data = new StringTokenizer(line, " ");
					item1 = Data.nextToken();
					item2 = Data.nextToken();
					item3 = Data.nextToken();
					node_id = Integer.parseInt(item1);
					x_cor = Integer.parseInt(item2);
					y_cor = Integer.parseInt(item3);
					n = new Node( node_id, x_cor, y_cor );
					this.Add_Node( n );
				}
				if (n_nodes != 0) {  
					source_node = nodes[0];
					}
			}
		}
		catch (IOException e) {
			System.err.println("error in file" +e.toString());
			System.exit(1);
		}			
  return this.Node_Cnt(); 
	}
}
