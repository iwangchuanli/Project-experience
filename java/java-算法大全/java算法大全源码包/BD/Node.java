// Node - generic nodes in block diagrams
//

public class Node {

	private Object node_object;
	private int node_type, nt;
	FunctionBlock Fbk;	

	public static final int SPtype = 1;
	public static final int FBtype = 2;
	public static final int IOtype = 3;

	public Node( Object no, int nt ) {
		node_object = no;
		node_type = nt;
		}

	public int NodeType() {
		return nt;
		}

	public Object getObject( ) {
		return node_object;
		}
	
}
