import java.awt.*;
import java.util.Vector;
import java.lang.*;

class PriorityQueue {

	/*Variable Declarations */
	/*Node source;*/
	int max;
	Vector dests;
	/*int cnt;*/
	static final double infinity = 1.0e300; 
	
	/*Constructor*/
	/*public P_Queue( Node source, int max ) {*/
	public PriorityQueue( int max ) {
		/*this.source = source;*/
		this.max = max;
		dests = new Vector(max);
		/*cnt = 0;*/
		}
	

	/*Methods*/
	public void Add ( Object dest ) {
		/*dests[cnt++] = dest;*/ 
		dests.addElement(dest); 
		}
	
	
	
	
	public void Print_Val( ) {
		int i;
		Edge obj;
		
		for (i = 0; i < dests.size(); i++) {
			/*obj = (Edge)dests.elementAt(i);*/
			
			
			/*System.out.println(" "+i+" "+(obj.From_Node()).Get_ID()+" "+(obj.To_Node()).Get_ID()+" "+obj.Cost()+", "+((obj.From_Node()).Rep()).Get_ID()+", "+((obj.To_Node()).Rep()).Get_ID());*/
			
			System.out.println("The cost is "+((ClassInterface)dests.elementAt(i)).Cost());
			}
		System.out.println("P_Queue: number of elements in queue = "+dests.size());
		}
	
	
	public boolean Empty() {
		return dests.isEmpty();	
		}


	public Object Element_At(int i) {
		return dests.elementAt(i);
		}
	
	public Object First_Element() {
		return dests.firstElement();
		}
	
  	public Object Extract_First_Element_MST() {
      Object o;
		int first_position = 0;

    	o = dests.firstElement();
      dests.removeElementAt( first_position );
		return o;
		}

								 
	public Object Extract_First_Element_Dij() {
		Object o;
		int first_position = 0;

		o = dests.firstElement();
		return o;
		}
	
	public boolean Exist_In_PQ( int id ) {
		int i;
		Node n;
		boolean flag = false;
		
		for (i = 0; i < dests.size(); i++) {
			n = (Node)(dests.elementAt(i));
			if ( n.Get_ID() == id ) {
				flag = true;
				break;
				}
			}
		return flag;
		}
	
	
	public Node Adjacent_Node( int id ) {
		int i;
		Node n;

		n = new Node(-1, 0, 0);
		for (i = 0; i < dests.size(); i++) {
			n = (Node)(dests.elementAt(i));
			if ( n.Get_ID() == id ) {
				break;
				}
			}
		return n;
		}
		
	public void Input_New_Cost( double temp_cost, Node adj ) {
		int i;
		Node n;

		for (i = 0; i < dests.size(); i++) {
			n = (Node)(dests.elementAt(i));
			if (n == adj) {
				n.Set_Cost( temp_cost );
				dests.addElement( (Object)n );
				break;
				}
			}
		dests.removeElementAt(i);
		}
				
	public void Remove_Element( Node n ) {
		int i, pivot = 0;
		Node at;
		
		for (i = 0; i < dests.size(); i++) {
			at = (Node)(dests.elementAt(i));
			if (at == n) {
				break;
				}
			}	
		dests.removeElementAt(i);
		}
	
	
	public int Num_Items() {
		return dests.size();
		}
	
	public void Sort( int pivot_pos, int num_items) {
		/*for the moment use a simple sorting algorithm -> quicksort(my version)*/
		int final_pivot_pos, init_pivot_pos;
		int cnt;
		int num_items_small, num_items_large;
		int pivot_pos_large, pivot_pos_small;
		int i, empty;
		Object temp;
		
		final_pivot_pos = pivot_pos;
		cnt = num_items;
		/*
		System.out.println("sort: f_p_p = "+final_pivot_pos+", cnt = "+cnt);
		*/
		i = final_pivot_pos + 1;
		
		if ( dests.size() > 1 ) {	
			do {
				if (((ClassInterface)dests.elementAt(final_pivot_pos)).Cost() >= ((ClassInterface)dests.elementAt(i)).Cost()) {
					//temp = ((Edge)dests.elementAt(i));
					temp = dests.elementAt(i);
					for (empty = i; empty > final_pivot_pos; empty--) {
						dests.setElementAt(dests.elementAt(empty-1), empty);
						}
					dests.setElementAt(temp, final_pivot_pos);
					final_pivot_pos++;
					}
				
				i++;
				if (i > (pivot_pos + cnt - 1)) {
					break;
					}
				} while (true);
		
			/*this is for the smaller partition*/
			num_items_small = final_pivot_pos - pivot_pos;
			pivot_pos_small = pivot_pos;
			
			/*this is for the larger partition*/
			/* problem is with the num_items_small */
		
			num_items_large = cnt - (num_items_small + 1);
			/*num_items_large = max - (final_pivot_pos + 1);*/
			pivot_pos_large = final_pivot_pos + 1;
		
		
			if (num_items_large > 1) {
				Sort(pivot_pos_large, num_items_large);
				}

			if (num_items_small > 1) {
				Sort(pivot_pos_small, num_items_small);
				}

			}
		}
}
