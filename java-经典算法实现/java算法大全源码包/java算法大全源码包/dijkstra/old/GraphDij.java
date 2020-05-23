
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class GraphDij {
	
	static final double infinity = 1.0e300;
	static final double zero = 0.00;
	static String start = "Source Node = Node ";
	static String adj = "Adjacent Node = Node ";
	static String distance = ", Cost = ";
	static String current = "Node "; 
	static String chosen = " Chosen; Shortest Distance ";
	static String empty = "Priority queue is empty ";
	String sentence;
	AlgThread alg;
	Graph graph;
	Vector set;
	PriorityQueue PQ;
	CostMatrix CM;
	
	public GraphDij( AlgThread alg ) {
		this.alg = alg;
		this.graph = alg.graph;
		Shortest_Paths( graph.Source() ); 
		}

/*------------------------*/
	public void Shortest_Paths( Node s ) {
		Node source, trial_node;
		int i; /*-*/ Edge e;
	
		source = s; /*-*/alg.frame.Highlight(4);
		set = new Vector();
		PQ = new PriorityQueue( graph.node_cnt );
		Initialise( source );/*-*/alg.frame.Highlight(7);
		/*-*/alg.frame.setText(1, "Initialization");
		/*-*/alg.frame.waitStep();
		
		while ( !PQ.Empty() ) {
			/*-*/alg.frame.Highlight(9); 
			trial_node = (Node)(PQ.Extract_First_Element_Dij());
			/*-*/alg.frame.Highlight(10); /*-*/alg.drawingPanel.Highlight_Node_Final(trial_node); /*-*/e = Unique_Edge(trial_node); /*-*/alg.drawingPanel.Highlight_Edge_Final(e); /*-*/alg.drawingPanel.Show_Cost_Node(trial_node);
			set.addElement( (Object)trial_node );
			/*-*/alg.frame.Highlight(11); 
			/*-*/sentence = new String(current+trial_node.Get_ID()+chosen);
			 /*-*/alg.frame.setText(1, sentence);
			/*-*/alg.frame.waitStep();
			Relax( trial_node );
			/*-*/alg.frame.Highlight(12); 
			}
		/*-*/sentence = new String(empty);
	 	/*-*/alg.frame.setText(2, sentence);
		Print_Ans( );
		/*-*/alg.frame.Highlight(14); 
		}
//----------------

	private void Initialise( Node s ) {
		int i, pivot = 0;
		Node neg_one;

		CM = new CostMatrix(graph);
		CM.Fill_Matrix();
		
		neg_one = new Node(-1, 0, 0);
		for (i = 0; i < graph.node_cnt; i++) {
			graph.nodes[i].Set_Rep( neg_one );
			graph.nodes[i].Set_Cost( infinity );
			PQ.Add( (Object)graph.nodes[i]);	
			}
	  	//Put_Vertices( ); 
		/*-*/alg.drawingPanel.Highlight_Node_Focus(s);
		s.Set_Cost( zero );
		set.addElement( (Object)s );
		/*-*/alg.drawingPanel.Highlight_Node_Final(s);
		/*-*/alg.drawingPanel.Show_Cost_Node(s);
		//System.out.println(" "+start+s.Get_ID());	
		Relax( s );
		}

	private void Relax( Node n ) {
		int pivot = 0;
		
		PQ.Remove_Element( n );
		Update();
		PQ.Sort( pivot, PQ.Num_Items() );

		}

	private void Update( ) {
	   int i, j, id, max, pos;
	   Node n, p;
	   double cost, temp_cost;
		Vector buffer = new Vector();
		Vector buffer_1 = new Vector();
		
      for (i = 0; i < set.size(); i++) {
      	n = (Node)(set.elementAt(i));
         id = n.Get_ID();
         //System.out.println("PQ: node in set is "+id);
         for (j = 0; j < CM.Length(); j++) {
      	   //id of end node is j+1
         	if ( PQ.Exist_In_PQ( j+1 ) ) {
            	//System.out.println("PQ: adjacent node is "+(j+1));
            	//p = PQ.Adjacent_Node( j+1 );
					if ( (cost = CM.Cost(id-1, j)) > 0.00) {
               	temp_cost = cost + n.Cost();
            		//System.out.println("PQ: adjacent node is "+(j+1));
            		p = PQ.Adjacent_Node( j+1 );
						p.Set_Rep(n);
						buffer.addElement((Object)p);
						//System.out.println("PQ: temp_cost is "+temp_cost);
               	if (temp_cost <= p.Cost() ) {
               		p.Set_Cost( temp_cost );
               		PQ.Input_New_Cost( temp_cost, p );
            			//System.out.println(" "+adj+" "+p.Get_ID()+" "+distance+" "+temp_cost);
							}
						}
					}
		   	}
			}
		//System.out.println("Graph_dij: buffer size is "+buffer.size());	
		
		Unique(buffer);
		}

	private void Unique(Vector buffer) {
		int i, j;
		Node n, p;
		Edge e;
		boolean flag = false;
		
		i = 0;		
		do {
			flag = false;
			for (j = i; j < buffer.size(); j++) {
				n = (Node)(buffer.elementAt(i));
				p = (Node)(buffer.elementAt(j));
				if (i != j) {
					if (n.Get_ID() == p.Get_ID()) {
						if (n.Cost() < p.Cost() ) {
							buffer.removeElementAt(j);
							flag = false;
							}
						else if (n.Cost() >= p.Cost()) {
							buffer.removeElementAt(i);
							flag = true;
							break;
							}
						}
					}
				}
				if (!flag) {
					i++;
					}
				if (i >= buffer.size()) {
					break;
					}
				//System.out.println("i = "+i+", size = "+buffer.size());
			} while(true);
		
		for (i = 0; i < buffer.size(); i++) {
			n = (Node)(buffer.elementAt(i));
        	sentence = new String(adj+n.Get_ID()+distance+n.Cost());
			//System.out.println(sentence+" ");
			e = Unique_Edge(n);
			/*-*/alg.drawingPanel.Highlight_Node_Focus(n);
	 		/*-*/alg.frame.setText(2, sentence);
			/*-*/alg.drawingPanel.Show_Cost_Node(n);
			/*-*/alg.drawingPanel.Highlight_Edge_Focus(e);
			/*-*/alg.frame.waitStep();
			}
		
		}

	private Edge Unique_Edge(Node n) {
		Node p;
		Edge e;
		int i;
		
		p = n.Rep();
		e = graph.edges[0];
		for (i = 0; i < graph.edge_cnt; i++) {
			e = graph.edges[i];
			if (e.From_Node() == p && e.To_Node() == n) {
				break;
				}
			}
		//System.out.println("here: highlight e.from = "+e.From_Node().Get_ID()+", e.to = "+e.To_Node().Get_ID());
		System.out.println("here: highlight n = "+n.Get_ID()+", p = "+p.Get_ID());
		return e;
		}

	private void Print_Ans( ) {
		int i;
		Node n;

		/*
		for (i = 0; i < 10; i++) {
			//System.out.println(" "+CM.Cost(i,0)+" "+CM.Cost(i,1)+" "+CM.Cost(i,2)+" "+CM.Cost(i,3)+" "+CM.Cost(i,4)+" "+CM.Cost(i,5)+" "+CM.Cost(i,6)+" "+CM.Cost(i,7)+" "+CM.Cost(i,8)+" "+CM.Cost(i,9));
			}			
		*/
		//PQ.Print_Val();	
		//System.out.println("Graph_dij: size of set = "+set.size());	
		for(i = 0; i < set.size(); i++) {
			n = (Node)(set.elementAt(i));
			System.out.println("Graph_dij: node "+n.Get_ID()+", cost "+n.Cost());
			}

		}
}

