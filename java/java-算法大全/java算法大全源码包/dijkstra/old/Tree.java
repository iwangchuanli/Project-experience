import java.awt.*;
import java.util.Vector;

class Tree {
	//variables
	Vector set;
	Node rep;
	
	//constructor
	public Tree( ) {
		set = new Vector();
		}
	
	//methods
	public void Combine( Tree tree ) {
		int i;
		Edge change;
		
		for (i = 0; i < tree.Num_Items(); i++) {
			change = tree.Connect( i );
			set.addElement( (Object)change );
			}
		}
		
	public void Init( Node n ) {
		rep = n;
		}
	
	public void Add( Edge e ) {
		set.addElement( (Object)e );
		rep = e.Rep();
		}
	
	public Node Rep( ) {
		return rep;
		}
	
	public int Num_Items() {
		return set.size();
		}
	
	public Edge Connect( int i) {
		return (Edge)set.elementAt(i);
		}
	
	public void Set_Rep( Node n ) {
		int i;
		Edge temp;
		
		rep = n;
		System.out.println("size of set = "+set.size()+", rep = "+rep.Get_ID());
	
		for ( i = 0; i < set.size(); i++ ) {
			temp = (Edge)set.elementAt(i);
			temp.Set_Rep(n);
			System.out.println("from tree: start = "+(temp.From_Node()).Get_ID()+", rep = "+(temp.Rep()).Get_ID());
			System.out.println("from tree: end = "+(temp.To_Node()).Get_ID()+", rep = "+(temp.Rep()).Get_ID());
			}
			
		}
}
