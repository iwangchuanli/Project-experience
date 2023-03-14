
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class GraphMST {
	
	static final String current_n_s = "From Node ";
	static final String current_n_e = " To Node ";
	static final String current_e = "Edge Cost ";
	static final String focus = " Edge In Focus: ";
	static final String c_cycle = "Causes Cycle ";
	static final String does_not = "Does Not Cause Cycle";
	static final String done = "Minimum Spanning Tree Found";
	static final String cost_e = ", Cost = ";
	String sentence;
	AlgThread alg;
	Graph graph;
	int cnt = 1;
	
	public GraphMST( AlgThread alg ) {
		this.alg = alg;
   	this.graph = alg.graph;
		Minimum_Spanning_Tree();
		}

/*------------------------------------------------*/
	public void Minimum_Spanning_Tree( ) {
   	int pivot = 0; /*-*/ alg.frame.Highlight(1);
      Edge trial_edge;
      Forest mst;
     	PriorityQueue PQ;
 
      PQ = new PriorityQueue( graph.edge_cnt ); /*-*/ alg.frame.Highlight(6);
      PQ = Initialize( PQ ); /*-*/ alg.frame.Highlight(7);
      PQ.Sort( pivot, graph.edge_cnt ); /*-*/ alg.frame.Highlight(8);
      PQ.Print_Val( ); /*-*/ alg.frame.Highlight(9);/*-*/alg.frame.setText(1, "Initialization");
		/*-*/alg.frame.waitStep();
 
      mst = new Forest( graph.edge_cnt ); /*-*/ alg.frame.Highlight(11);
      while ( mst.Num_Edges( )  < graph.node_cnt-1 ) { /*-*/ alg.frame.Highlight(12);
 	   	trial_edge =  (Edge)(PQ.Extract_First_Element_MST( )); /*-*/ alg.drawingPanel.Highlight_Edge_Focus(trial_edge); 
			/*-*/ alg.frame.Highlight(13);
        	if (!Cycle( trial_edge, mst )) { /*-*/ alg.frame.Highlight(14);
        		sentence = new String(focus+does_not); 
            System.out.println(sentence+" ");
				/*-*/alg.frame.setText(2, sentence);
				/*-*/ alg.drawingPanel.Highlight_Edge_Final(trial_edge);
				/*-*/alg.frame.waitStep();
            mst.Add( trial_edge ); /*-*/ alg.frame.Highlight(17);
            }
         }
      mst.Print_Val(); /*-*/ alg.frame.Highlight(20);
      sentence = new String(done); /*-*/ alg.frame.Highlight(21); /*-*/ alg.frame.setText(2, sentence);
      System.out.println(sentence+" ");
      }
//-------
       //private PriorityQueueMST Initialize( PriorityQueueMST PQ ) {
       private PriorityQueue Initialize( PriorityQueue PQ ) {
                int i;
                Node neg_one;
 
                neg_one = new Node(-1, 0, 0);
                for (i = 0; i < graph.edge_cnt; i ++) {
                        graph.edges[i].Set_Rep( neg_one );
                        PQ.Add( (Object)graph.edges[i] );
                        }
                return PQ;
                }
 
        private boolean Cycle( Edge te, Forest T ) {
                sentence = new String(
                focus+current_n_s+te.start.Get_ID()+current_n_e+te.end.Get_ID()+
cost_e+te.Cost());
                System.out.println(sentence+" ");
					 /*-*/alg.frame.setText(1, sentence);
					 /*-*/alg.frame.waitStep();
                if ( te.New( ) ) {
                        //new tree
                        T.New( te );
                        return false;
                        }
                else if ( te.Is_From( ) || te.Is_To( ) ) {
                        //add to existing tree
                        T.Existing( te );
                        return false;
                        }
                else if ( te.Join( ) ) {
                        //combine trees
                        T.Combine( te );
                        return false;
                        }
                else {
                        //exclude this edge
								/*-*/ alg.drawingPanel.Highlight_Edge_Ori(te);
                        sentence = new String(focus+c_cycle);
                        System.out.println(sentence+" ");
								/*-*/alg.frame.setText(2, sentence);
								/*-*/alg.frame.waitStep();
                        return true;
                        }
 
                }
 
}
