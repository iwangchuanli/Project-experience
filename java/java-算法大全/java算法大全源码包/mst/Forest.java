import java.awt.*;
import java.util.Vector;

class Forest {
	//variables
	Vector set;
	Node rep;
	Tree trees[];
	int cnt;
	
	//constructor
	public Forest( int num ) {
		set = new Vector();
		rep = new Node(-2, 0, 0);
		trees = new Tree[num];
		cnt = 0;
		}
	//methods
	public void New( Edge e ) {
		trees[cnt] = new Tree();
		trees[cnt].Add( e );
		cnt++;
		}

	public void Existing( Edge e ) {
		int i;
		
		for (i = 0; i < cnt; i++) {
			if (e.Rep() == trees[i].Rep()) {
				trees[i].Add( e );
				break;
				}
			}
		}
		
	public void Combine( Edge e ) {
		int i, j = 0, k = 0;
		
		System.out.println("Forest: cnt = "+cnt);
		System.out.println("Forest: from "+e.start.Get_ID()+", to "+e.end.Get_ID()+", "+e.start.Rep().Get_ID()+", "+e.end.Rep().Get_ID());
		
		for (i = 0; i < cnt; i++) {
			if (trees[i].Rep() == e.To_Node().Rep()) {
				System.out.println("yes, i am updating...");
				trees[i].Set_Rep( e.From_Node().Rep() );
				e.Set_Rep(e.From_Node().Rep());
				k = i;
				}
			if (trees[i].Rep() == rep) {
				j = i;
				}
			}
		//trees[j].Combine( trees[k] );
		}
	
	
	public void Add( Edge e ) {
		int i;
		Edge in_set;
		
		//e.Set_Rep( rep );
		set.addElement( (Object)e );
	/*	for (i = 0; i < set.size(); i++) {
			in_set = ((Edge)set).elementAt( i );
			
			}
	*/
		}
	public Node Rep( ) {
		return rep;
		}
		
	public int Num_Edges() {
		return set.size();
		}
	
	public Edge Connect( int i) {
		return (Edge)set.elementAt(i);
		}
	
	public void Set_Rep( Node n ) {
		rep = n;	
		}
	
	public void Print_Val() {
		int i;
		Edge temp;

		for (i = 0; i < set.size(); i++) {
			temp = (Edge)(set.elementAt(i));
			System.out.println("Forest: from "+temp.From_Node().Get_ID()+", to "+temp.To_Node().Get_ID()+", value "+temp.Cost());
			}
		}
	
}
