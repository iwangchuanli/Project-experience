
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
	
/*-*/alg.frame.setText(0, "Running Dijkstra's Algorithm... Souce Node = 'A'");
		source = s; /*-*/alg.frame.Highlight(4);
		set = new Vector();
		PQ = new PriorityQueue( graph.node_cnt );
/*-*/alg.drawingPanel.setNodeComment(s, "Initial Graph");
		Initialise( source );/*-*/alg.frame.Highlight(7);
		/*-*/alg.frame.setText(1, "Initialization");
/*-*/alg.drawingPanel.setNodeComment(s,
/*-*/	"All nodes have infinite cost except the source");
		/*-*/alg.frame.waitStep();
		
		while ( !PQ.Empty() ) {
			/*-*/alg.frame.Highlight(9); 
			trial_node = (Node)(PQ.Extract_First_Element_Dij());
			/*-*/alg.frame.Highlight(10); 
/*-*/alg.drawingPanel.setNodeComment(trial_node,
/*-*/	"Choose the node with lowest cost...");
/*-*/alg.drawingPanel.flashNode(trial_node);
/*-*/alg.drawingPanel.Highlight_Node_Final(trial_node); 
/*-*/alg.frame.waitStep();
/*-*/e = Unique_Edge(trial_node); 
/*-*/alg.drawingPanel.Highlight_Edge_Final(e); 
/*-*/alg.drawingPanel.Show_Cost_Node(trial_node);
			set.addElement( (Object)trial_node );
			/*-*/alg.frame.Highlight(11); 
			/*-*/sentence = new String(current+trial_node.Get_ID()+chosen);
			 /*-*/alg.frame.setText(1, sentence);
			/*-*/alg.frame.Highlight(12); 
/*-*/alg.drawingPanel.setNodeComment(trial_node,
/*-*/	"Relax all adjacent nodes...");
			Relax( trial_node );
			/*-*/alg.frame.waitStep();
/*-*/alg.drawingPanel.hideNodeComment();
			}
		/*-*/sentence = new String(empty);
	 	/*-*/alg.frame.setText(2, sentence);
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
			graph.nodes[i].Show_Infinite();
			PQ.Add( (Object)graph.nodes[i]);	
			}
		/*-*/alg.drawingPanel.Highlight_Node_Focus(s);
		s.Set_Cost( zero );
		/*-*/alg.drawingPanel.Show_Cost_Node(s);
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
		
      //for (i = 0; i < set.size(); i++) {
      if (set.size() > 0) {
      	 n = (Node)(set.lastElement());
         id = n.Get_ID();
         //System.out.println("PQ: node in set is "+id);
         for (j = 0; j < CM.Length(); j++) {
      	   //id of end node is j+1
         	if ( PQ.Exist_In_PQ( j+1 ) ) {
		    if ( (cost = CM.Cost(id-1, j)) > 0.00) {
               		temp_cost = cost + n.Cost();
            		//System.out.println("PQ: adjacent node is "+(j+1));
            		p = PQ.Adjacent_Node( j+1 );
			buffer.addElement((Object)p);
               		if (temp_cost <= p.Cost() ) {
			    if (p.Rep() != null) {
				// remove the focus from the edge
/*-*/alg.drawingPanel.Highlight_Edge_Ori(Unique_Edge(p)); 
			    }
			    p.Set_Rep(n);
               		    p.Set_Cost( temp_cost );
               		    PQ.Input_New_Cost( temp_cost, p );
			}
		    }
		}
	 }
       }
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
		n = null;
		for (i = 0; i < buffer.size(); i++) {
			n = (Node)(buffer.elementAt(i));
/*-*/alg.drawingPanel.flashNode(n);
			/*-*/alg.drawingPanel.Highlight_Node_Focus(n);
			/*-*/alg.drawingPanel.Show_Cost_Node(n);
			}
/*-*/alg.drawingPanel.repaint();
/*-*/alg.drawingPanel.delay();
/*-*/alg.drawingPanel.delay();
/*-*/alg.drawingPanel.delay();
/*-*/alg.frame.waitStep();
/*-*/alg.drawingPanel.setNodeComment(n,
/*-*/	"Update predecessors for relaxed nodes...");
		for (i = 0; i < buffer.size(); i++) {
			n = (Node)(buffer.elementAt(i));
        	sentence = new String(adj+n.Get_ID()+distance+n.Cost());
			//System.out.println(sentence+" ");
			e = Unique_Edge(n);
	 		/*-*/alg.frame.setText(2, sentence);
/*-*/alg.drawingPanel.flashEdge(e);
			/*-*/alg.drawingPanel.Highlight_Edge_Focus(e);
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
		if (i==graph.edge_cnt)
		    return null;
	  	else
		    return e;
		}

	private void Print_Ans( ) {
		int i;
		Node n;

		for(i = 0; i < set.size(); i++) {
			n = (Node)(set.elementAt(i));
			}

		}
}

