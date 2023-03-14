
/** Node class **/
import java.awt.*;

class RBNode extends Node{
//class RBNode {
	
	private RBNode left;
	private RBNode right;
	private RBNode parent;
	private boolean color_red;
	private int data;
	int pos_x, pos_y;
/*	
	public RBNode(int d){
		data = d;
		left = right = parent = null;
		color_red = true;
	}
*/	

	public RBNode(int d, int pos_x, int pos_y) {
		super(d, pos_x, pos_y);
		//data = d;
		left = right = parent = null;
		color_red = true;
		this.Set_Highlight( Color.red );
		}
/*
	
	public RBNode(RBNode y){
		data = y.Data();
		left = y.Left();
		right = y.Right();
		parent = y.Parent();
		color_red = y.IsRed();
		}
	
	public int Get_ID() {
		return data;
		}
*/	
	public boolean IsBlack(){
		return (!color_red);
		}

	public boolean IsRed(){
		return (color_red);
		}

	public RBNode Left(){
		return left;
		}

	public Color getColour() {
		if(IsBlack())
			return Color.black;
		else if(IsRed())
			return Color.red;
		else
			System.out.println("Node is neither Red nor Black");
			return Color.blue;
		}

	public RBNode Right(){
      return right;
		}


	public RBNode Parent(){
      return parent;
		}

	public int Data(){
		return data;
		}

	public void SetLeft(RBNode y){
		left = y;
		}

	public void SetRight(RBNode y){
		right = y;
		}

	public void SetParent(RBNode y){
		parent = y;
		}

	public void SetColorBlack(){
		color_red  = false;
		node_colour = Color.black;
		label_colour = Color.black;
		}

	public void SetColorRed(){
		color_red = true;
		node_colour = Color.red;
		label_colour = Color.red;
		}

	public void SetData(int d){
		data = d;
		}
	
	
	}
