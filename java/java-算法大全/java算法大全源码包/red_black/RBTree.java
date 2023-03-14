/** RBTree class
	Original: Linda Luo
	Mods:  	Mervyn Ng
 **/
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.lang.*;

class RBTree{
	private	RBNode root;
	public final RBNode sentinel;

	// Counters for coverage checking
	private static int case1ct;
	private static int case2ct;
	private static int case3ct;
	private static int case4ct;
	private static int case5ct;
	private static int case6ct;
	private static int case7ct;
	private static int case8ct;
	private Vector v_rbnodes = new Vector();
	private static final int padding = 100;
	private RBNode buffer[];

	private DrawingPanel dpAfter, dpBefore;
	private Graphics g;

	private Image offscreen = null;
	private Graphics offGraphics = null;
	private Dimension offscreensize = null;
	private Image beforeOffSc = null;
	private Graphics beforeOffGraphics = null;
	private Dimension beforeOffSize = null;

	private static final int x_offset = 50;
	private static final int y_offset = 10;
	
	private static final int labelFontSize = 12;
	
	
	public RBTree(){
	sentinel = new RBNode(0,0,0); 
	sentinel.Set_Cost(0.00);
	//sentinel = new RBNode(0);  
	sentinel.SetColorBlack();
	root = sentinel;
	case1ct = 0;
   	case2ct = 0;
   	case3ct = 0;
   	case4ct = 0;
  	case5ct = 0;
   	case6ct = 0;
	case7ct = 0;
	case8ct = 0;
	}

	/* Find Node containding data */
	public RBNode FindNode(int data){
		RBNode current = root;
		while(current != sentinel) {
		//if (data == current.Data())
		if (data == current.Cost())
				return (current);
			else {
				//if(data < current.Data())
				if(data < current.Cost())
					current = current.Left();
				else 	current = current.Right();
				}
			} 	
		
		return null;
		}

	/* Rotate node x to left */
	private void RotateLeft(RBNode x, Graphics g){
		Color origColour = x.getColour();
		x.Highlight(g, Color.yellow);		
	
		RBNode y = x.Right();
	
	
		/* establish x.right link */
		x.SetRight(y.Left());
		if (y.Left() != sentinel)
			y.Left().SetParent(x);
			
		/* establish y.parent link */
		if (y != sentinel) y.SetParent(x.Parent());
		if (x.Parent() != null){
			if (x == x.Parent().Left())
				x.Parent().SetLeft(y);
			else 
				x.Parent().SetRight(y);
			}
		else root = y;

	
		/* link x and y */
		y.SetLeft(x);
		if (x != sentinel) x.SetParent(y);

		x.Unhighlight_Node(g, origColour);
		}

	/* Rotate node x to right */
	private void RotateRight(RBNode x, Graphics g){
		Color origColour = x.getColour();
		x.Highlight(g, Color.yellow);		

		RBNode y = x.Left();
		
		/* establish x.left link */
		x.SetLeft(y.Right());
		if (y.Right() != sentinel)
			y.Right().SetParent(x);

		/* establish y.parent link */
      		if (y != sentinel) 
			y.SetParent(x.Parent());
	        if (x.Parent() != null){
     			if (x == x.Parent().Right())
         			x.Parent().SetRight(y);
         		else
         			x.Parent().SetLeft(y);
        		}
      		else root = y;


       	/* link x and y */
       	y.SetRight(x);
      	if (x != sentinel) x.SetParent(y);

		x.Unhighlight_Node(g, origColour);
		}

	/* allocate node for data and insert in tree */
	public boolean InsertNode(int data, AlgAnimFrame frame, RBTree tree){
		RBNode parent;
		RBNode current;	
		RBNode x;

		FontMetrics fm;
		int str_width, str_ht;
		int label_x, label_y;

		DrawingPanel dp = frame.getDrawingPanel();
		Graphics g = dp.getGraphics();
		
		System.out.println("RBTree: InsertNode: value " + data );
		/* find where node belongs */
		current = root;
		parent = null;
		while (current != sentinel){
			//System.out.println("root is "+root.Get_ID());
			if (data == current.Cost()) {
				//System.out.println("data == current.Data");
				return true;
				}
			parent = current;
			if (data < current.Cost()) {
				current = current.Left();
				//System.out.println("data < current.Data");
				}
			else {
				current = current.Right();
				//System.out.println("data >= current.Data");
				}
			System.out.println("value of data is "+data+", current = "+current.Cost());	
			}

		/* setup new node */
		//x = new RBNode(data);
		x = new RBNode(0, 0, 0); //merv
		x.Set_Cost((double)data);
		//System.out.println("data of x is "+data);
		if (x == null) {
			//System.out.println("x is null");
			return false;
			}
		x.SetLeft(sentinel);
		x.SetRight(sentinel);
		x.SetParent(parent);
		//System.out.println("new node setup");
		/* insert node in tree */
		if (parent != null) {
			if (data < parent.Cost())
				parent.SetLeft(x);
			else parent.SetRight(x);
			//System.out.println("parent != null");
			}
		else {
			root = x;
			//System.out.println("parent == null");
			}
		//System.out.println("Before DT");
		v_rbnodes.addElement((Object)x);
		/*-*/ drawRBTree(frame);
		if(tree.GetOffscreen() == null) {
			System.out.println("RBTree: offscreen is null");
			}

		ShadowLabel insertLabel = new ShadowLabel("Inserting Data Value: " +data);
		drawLabel(insertLabel, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

		/*insertLabel.setFontSize(labelFontSize);
		fm = frame.getDrawingPanel().getFontMetrics(insertLabel.getFont());
		str_width = fm.stringWidth(insertLabel.getText());
		str_ht = fm.getHeight();
		label_x = x.Mid_X() - str_width/2;
		label_y = x.Mid_Y() + x.radius;// + str_ht + y_offset;
		System.out.println("radius is: " + x.radius);
		insertLabel.move(label_x, label_y);
		insertLabel.draw(offGraphics);
		*/
		
		g.drawImage(tree.GetOffscreen(), 0, 0, null);

		// Set up image for before panel
		drawRBTree(frame);
		insertLabel.setText("Before Rotation");
		insertLabel.draw(offGraphics);

		frame.waitStep();
		InsertFixup(x, frame, tree);
		v_rbnodes.addElement((Object)x);
		return (true);

		}	

	/* delete node z from tree */
	public void DeleteNode(RBNode z, AlgAnimFrame frame){

		RBNode x;
		RBNode y;
		
		if ( z == sentinel) return;
		
		if ( z.Left() == sentinel || z.Right() == sentinel){
			/* y has a sentinel node as a child */
			y = z;
			}
		else{
			/* find tree successor with a sentinel node as a child */
			y = z.Right();
			while ( y.Left() != sentinel) y = y.Left();
			}
	
		/* x is y's only child */
		if (y.Left() != sentinel)
			x = y.Left();
		else x = y.Right();
	
		/* remove y from the parent chain */
		x.SetParent(y.Parent());
		if (y.Parent() != null){
			if (y == y.Parent().Left())
				y.Parent().SetLeft(x);
			else y.Parent().SetRight(x);
			}
		else root = x;
		
		//if (y != z) z.SetData(y.Data());
		if (y != z) z.Set_Cost(y.Cost());
		
		if (y.IsBlack()) DeleteFixup(x, frame);
		
		}

	/* maintain red-black tree balance after inserting node x */
	private void InsertFixup(RBNode x, AlgAnimFrame frame, RBTree tree){
		DrawingPanel dpAfter = frame.getDrawingPanel();
		DrawingPanel dpBefore = frame.getBeforeDp();

		ShadowLabel colorLabel = new ShadowLabel("Restoring Red-Black Property...");
		ShadowLabel bforeLabel = new ShadowLabel("Before Rotation");
		ShadowLabel rotLeftLab = new ShadowLabel("Rotating Left...");
		ShadowLabel rotRightLab = new ShadowLabel("Rotating Right...");

		/* check red-black properties */
		while (x != root && x.Parent().IsRed()){
			System.out.println("In while loop");
			/* we have a violation */
			if (x.Parent() == x.Parent().Parent().Left()){
				RBNode y = x.Parent().Parent().Right();
				case1ct++;	
				//System.out.println("In case 1");
				//System.out.println("Node x data is "+x.Get_ID());
				//System.out.println("Node y data is "+y.Get_ID());
				if (y.IsRed()){
				
					/* uncle is red */
					drawLabel(colorLabel, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);
					x.Parent().SetColorBlack();
					y.SetColorBlack();
					x.Parent().Parent().SetColorRed();
					x = x.Parent().Parent();
					v_rbnodes.addElement((Object)x);
					DrawOffScreen(frame, tree);
					frame.waitStep();			
	
					case2ct++;
					//System.out.println("In case 2");
					//System.out.println("Node x data is "+x.Get_ID());
					//System.out.println("Node y data is "+y.Get_ID());
					}
				else {
					case3ct++;	
					//System.out.println("In case 3");
					//System.out.println("Node x data is "+x.Get_ID());
					//System.out.println("Node y data is "+y.Get_ID());
					/* uncle is black */
					if (x == x.Parent().Right()){
						/* make x a left child */
						x = x.Parent();

						drawBeforeLabel(bforeLabel, x_offset, (tree.dpBefore.getPanelHeight() - y_offset), frame);
						drawLabel(rotLeftLab, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

						RotateLeft(x, g);
						v_rbnodes.addElement((Object)x);
						DrawOffScreen(frame, tree);
						frame.waitStep();
						case4ct++;
						//System.out.println("In case 4");
						//System.out.println("Node x data is "+x.Get_ID());
						//System.out.println("Node y data is "+y.Get_ID());
						}
				
					/* recolor and rotate */
					drawLabel(colorLabel, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

					x.Parent().SetColorBlack();
					x.Parent().Parent().SetColorRed();
					v_rbnodes.addElement((Object)x);
					DrawOffScreen(frame, tree);
					frame.waitStep();

					drawBeforeLabel(bforeLabel, x_offset, (tree.dpBefore.getPanelHeight() - y_offset), frame);
					drawLabel(rotRightLab, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

					RotateRight(x.Parent().Parent(), g);
					v_rbnodes.addElement((Object)x);
					DrawOffScreen(frame, tree);
					frame.waitStep();
					}
				}
			else{
			
				/* mirror image of above code */
				RBNode y = x.Parent().Parent().Left();
				case5ct++;
				//System.out.println("In case 5");
				//System.out.println("Node x data is "+x.Get_ID());
				//System.out.println("Node y data is "+y.Get_ID());
				if (y.IsRed()){
					/* uncle is red */
					drawLabel(colorLabel, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

					x.Parent().SetColorBlack();
         	   			y.SetColorBlack();
			            	x.Parent().Parent().SetColorRed();
		  	                x = x.Parent().Parent();
					v_rbnodes.addElement((Object)x);
					DrawOffScreen(frame, tree);
					frame.waitStep();
					case6ct++;
					//System.out.println("In case 6");
					//System.out.println("Node x data is "+x.Get_ID());
					//System.out.println("Node y data is "+y.Get_ID());
               				}
 		       		else {
					case7ct++;
					//System.out.println("In case 7");
					//System.out.println("Node x data is "+x.Get_ID());
					//System.out.println("Node y data is "+y.Get_ID());
					/* uncle is black */
			                if (x == x.Parent().Left()){
			               	x = x.Parent();

					drawBeforeLabel(bforeLabel, x_offset, (tree.dpBefore.getPanelHeight() - y_offset), frame);
					drawLabel(rotRightLab, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

			                RotateRight(x, g);
					v_rbnodes.addElement((Object)x);
					DrawOffScreen(frame, tree);
					frame.waitStep();
			                case8ct++; 
					//System.out.println("In case 8");
					//System.out.println("Node x data is "+x.Get_ID());
					//System.out.println("Node y data is "+y.Get_ID());
				 	}
                                
				drawLabel(colorLabel, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

		        	x.Parent().SetColorBlack();
		                x.Parent().Parent().SetColorRed();
				v_rbnodes.addElement((Object)x);
				DrawOffScreen(frame, tree);
				frame.waitStep();

				drawBeforeLabel(bforeLabel, x_offset, (tree.dpBefore.getPanelHeight() - y_offset), frame);
				drawLabel(rotLeftLab, x_offset, (tree.dpAfter.getPanelHeight() - y_offset), frame);

            			RotateLeft(x.Parent().Parent(), g);
				v_rbnodes.addElement((Object)x);
				DrawOffScreen(frame, tree);
				frame.waitStep();
            			}
        		}
		}
//	drawRBTree(frame);
	root.SetColorBlack();
	drawRBTree(frame);
	g.drawImage(tree.GetOffscreen(), 0, 0, null);
	frame.waitStep();
	System.out.println("InsertFixup return");
	}





	/* maintain red-black tree balance after deleting node x */
	private void DeleteFixup(RBNode x, AlgAnimFrame frame){
			
		DrawingPanel dp = frame.getDrawingPanel();
		Graphics g = dp.getGraphics();

		
        while (x != root && x.IsBlack()){
       		if (x == x.Parent().Left()){
         		RBNode w = x.Parent().Right();

        		if (w.IsRed()){
				w.SetColorBlack();
               			x.Parent().SetColorRed();
               			RotateLeft(x.Parent(), g);
				w = x.Parent().Right();
				}
        		if (w.Left().IsBlack() && w.Right().IsBlack()){
				w.SetColorRed();
				x = x.Parent();
				}
			else{
						
        			if (w.Right().IsBlack()){
        			w.Left().SetColorBlack();
				w.SetColorRed();
				RotateRight(w, g);
				w = x.Parent().Right();
            			}
            	   			
				if (x.Parent().IsBlack()) 
					w.SetColorBlack();
				else w.SetColorRed();
				x.Parent().SetColorBlack();
		
				w.Right().SetColorBlack();
				RotateLeft(x.Parent(), g);
				x = root;
	      			}
           		}
           	else{
       			RBNode w = x.Parent().Left();

               		if (w.IsRed()){
               			w.SetColorBlack();
                		x.Parent().SetColorRed();
                  		RotateRight(x.Parent(), g);
                  		w = x.Parent().Left();
                  		}
               		if (w.Right().IsBlack() && w.Left().IsBlack()){
               			w.SetColorRed();        
                  		x = x.Parent(); 
                  		}       
               		else{   
                                        
               			if (w.Left().IsBlack()){
                  			w.Right().SetColorBlack();
                     			w.SetColorRed();
                     			RotateLeft(w, g);
			                w = x.Parent().Left();
                     			}
                               
                  		if (x.Parent().IsBlack()) 
					w.SetColorBlack();
                  		else w.SetColorRed();
                  		x.Parent().SetColorBlack();
                  		w.Left().SetColorBlack();
		                RotateRight(x.Parent(), g);
                  		x = root;
                  		}
 
	        	}
        	}
        x.SetColorBlack();

	}

	/* obtain the blackheight of the tree */
	public int BlackHeight(){
		RBNode x;
		RBNode xl, xr;
		int pos_x = 200, pos_y = 20;
		int height = 0;
		
		x = root;
		
		while (x != sentinel){
 			x = x.Right();
			if (x.IsBlack()) {
				height++;
				}
			}
		
		return height; 	
		}

	private void SetPositions(RBNode n, int h, int w) {
		RBNode xl, xr;
		Edge e;
		int dx = 20, dy = 50, y_init, width;
		int bh;

		width = w;
		bh = BlackHeight();
		y_init = h/(2*bh);
		
		if (n.Parent()==null) { //root node, level 1 node
			n.x = width/2;
			n.y = y_init;
			}
		else if (n.Parent().Parent() == null){ //level 2 node
			if (n == n.Parent().Left()) {
				n.x = width/4;
				n.y = y_init*2 ;
				}
				if (n == n.Parent().Right()) {
					n.x = 3*width/4;
					n.y = y_init*2;
					}
			}
		else{
			if (n == n.Parent().Left()) {
				n.x = n.Parent().x - Math.abs(n.Parent().x - n.Parent().Parent().x)/2;
				n.y = n.Parent().y + y_init;
				}
			if (n == n.Parent().Right()) {
				n.x = n.Parent().x + Math.abs(n.Parent().x - n.Parent().Parent().x)/2;
				n.y = n.Parent().y + y_init;
				}															 
			}
		}
	
	private void Reset( RBNode n, int k  ) {
		int pos;

		pos = k - 1;
		buffer[pos] = n;

		System.out.println("RBTree: Reset: (2*k)-1 = " +((2*k)-1));		

		buffer[(2*k)-1] = n.Left();
		System.out.println("RBTree: Reset: after left "+((2*k)-1));
		buffer[2*k] = n.Right();
		System.out.println("RBTree: Reset: after right "+(2*k));
	
		if (n.Left() != sentinel) {
			Reset( n.Left(), 2*k );
			}
		if (n.Right() != sentinel) {
			Reset( n.Right(), (2*k)+1 );
			}
		
		}

	public void setPanel( DrawingPanel dp ) {
		this.dpAfter = dp;
		g = dp.getGraphics();
		}
	
	public void drawRBTree(AlgAnimFrame frame) {
		int i, k, x, max_x = 0, min_x = 0, max_y = 0, cnt;
		int h, w;
		RBNode temp;
		double x_scale, y_scale;
		Edge e;
		int buf_size;

		dpAfter = frame.getDrawingPanel();
		g = dpAfter.getGraphics();
		h = dpAfter.getPanelHeight();
		w = dpAfter.getPanelWidth();
		
				

		Dimension d = dpAfter.size();
		
		System.out.println("In DT");

		if(d.width < 1 || d.height < 1){
			System.out.println("d.height: "+d.height+ ", d.width: " +d.width);
			//return;
			}

		
		if((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
			System.out.println("DrawRBTree: Creating offscreen");
			offscreen = dpAfter.createImage(d.width, d.height);
			offscreensize = d;
			if(offscreen == null) System.out.println("Offscreen still null");
			offGraphics = offscreen.getGraphics();
			}
			if(offscreen == null) System.out.println("Offscreen still null");

		offGraphics.setColor(dpAfter.getBackground());
		offGraphics.fillRect(0, 0, d.width, d.height);

		if (!v_rbnodes.isEmpty()) {
			System.out.println("not empty");
			//get the root node
			temp = Root_RBNode();
			if (SetSubRBTreePos( temp, h, w )) {
				
				for (i = 0; i < v_rbnodes.size(); i++) {
	   	   			temp = (RBNode)(v_rbnodes.elementAt(i));
					x = temp.x;
	   	   			if ( x > max_x ) max_x = x;
					if ( x < min_x ) min_x = x;
			   		x = temp.y;
			   		if ( x > max_y ) max_y = x;
	   				}
				x_scale = (double)w / ( max_x + padding );
		   		y_scale = (double)h/ ( max_y + padding );
			
					
				System.out.println("RBTree: drawRBTree: size is "+v_rbnodes.size());
				buf_size = Buf_Size(v_rbnodes.size());
				buffer = new RBNode[buf_size];
				Init_Buffer(buf_size);
				System.out.println("BRTree: drawRBTree: buf_size is "+buf_size);
				temp = Root_RBNode();
				buffer[0] = temp;
				k = 1;
				Reset( temp, k );
				
				for (i = 0; i < buf_size; i++) {
					temp = buffer[i];
					//if ( temp != sentinel ) {
	  		 			temp.Set_Scale( x_scale, y_scale );
						//System.out.println("RBTree: drawRBTree: node "+temp.Cost()+", "+temp.x+", "+temp.y);
						if (temp.Parent() != null) {
							System.out.println("parent "+temp.Parent().Cost());
							}
						//System.out.println("x scale "+x_scale+", y scale "+y_scale);
						//System.out.println("h "+h+", w "+w);
						temp.show_cost = true;
						temp.ans_colour = Color.yellow;
						temp.show_id = false;
						if (temp != sentinel) {
							temp.Draw_Node(offGraphics, dpAfter); 
							//offscreen = temp.getOffscreen();
							if (temp.Parent() != null ) {
								e = new Edge(temp.Parent(), temp, 0);
								e.show_cost = false;
								e.Draw_Edge(offGraphics, 1, 0);
								temp.Parent().Redraw_Node(offGraphics, dpAfter);
								//offscreen = temp.getOffscreen();
								}
							temp.Redraw_Node(offGraphics, dpAfter);
							//offscreen = temp.getOffscreen();
							}
					}
				}
			}
		}

	private int Buf_Size( int i ) {
		int cnt = 0;
		int two = 2;
		int value;
		int total;
		int temp;
		int same, extra;
		
		value = (int)(Math.pow(two, cnt));
		while ( i >= value ) {
			cnt++;
			value = (int)(Math.pow(two, cnt) - 1);
			}
		/*
		same = value - i;
		extra = i - ((int)(Math.pow(two, cnt-1)) - 1);
		extra = extra*2;
		total = same + extra + i;
		*/
		total = (int)(Math.pow(two, cnt+1)) - 1;
		return total;
		}
	
	private void Init_Buffer(int cnt) {
		int i;

		for (i = 0; i < cnt; i++) {
			buffer[i] = sentinel;
			}
		}
	
/*	private void Draw_From_Root(Graphics g, RBNode n, double xs, double ys, DrawingPanel dp) {
		Edge e;
		
		n.Set_Scale(xs, ys);
		n.show_cost = true;
		n.ans_colour = Color.yellow;
		n.show_id = false;
		n.Draw_Node(g, dp);
		System.out.println("n "+n.Cost()+", "+n.x+", "+n.y);
		if (n.Left() != sentinel) {
			System.out.println("n.Left "+n.Left().Cost()+", "+n.Left().x+", "+n.Left().y);
			n.Left().Set_Scale(xs, ys);
			n.Left().show_cost = true;
			n.Left().ans_colour = Color.yellow;
			n.Left().show_id = false;
			n.Left().Draw_Node(g, dp);
			e = new Edge(n, n.Left(), 0);
			e.show_cost = false;
			e.Draw_Edge(offGraphics, 1, 0);
			n.Redraw_Node(g, dp);
			n.Left().Redraw_Node(g, dp);
			Draw_From_Root(g, n.Left(), xs, ys, dp);
			}
		if (n.Right() != sentinel) {
			System.out.println("n.Right "+n.Right().Cost()+", "+n.Right().x+", "+n.Right().y);
			n.Right().Set_Scale(xs, ys);
			n.Right().show_cost = true;
			n.Right().ans_colour = Color.yellow;
			n.Right().show_id = false;
			n.Right().Draw_Node(g, dp);
			e = new Edge(n, n.Right(), 0);
			e.show_cost = false;
			e.Draw_Edge(g, 1, 0);
			n.Redraw_Node(g, dp);	
			n.Right().Redraw_Node(g, dp);
			Draw_From_Root(g, n.Right(), xs, ys, dp);
			}
		}*/
	
	
	private boolean SetSubRBTreePos( RBNode n, int h, int w ){
		
		SetPositions(n, h , w);
		if (n.Left() != sentinel) {
			SetSubRBTreePos(n.Left(), h, w);
			}
		if (n.Right() != sentinel) {
			SetSubRBTreePos(n.Right(), h, w);
			}
	
		return true;

		}
						  


	private RBNode Root_RBNode() {
		int i;
		RBNode temp_root = new RBNode(-1, -1, -1);;

		temp_root = (RBNode)(v_rbnodes.firstElement());
		for (i = 0; i < v_rbnodes.size(); i++) {
			temp_root = (RBNode)(v_rbnodes.elementAt(i));
			if (temp_root.Parent()==null) {
				break;
				}
			}
		return temp_root;
		}
	
		
	public int RedCount(){
   	RBNode x;
      int redct = 0;

      x = root;
      while (x != sentinel){
      	x = x.Right();
         if (x.IsRed()) redct++;

        }
      return redct;
		}



	public void PrintNode(RBNode x){
   	if (x == sentinel) System.out.println("x is a sentinel node.");
      else{
      	System.out.print("Node has data: "); 
			System.out.println(x.Cost());
			System.out.print("Color: ");
			if(x.IsBlack()) System.out.println("Black");
			else System.out.println("Red");
 
         System.out.print("Left child:  ");
         if (x.Left() == sentinel) System.out.println(" is a sentinel node.");
			else System.out.println(x.Left().Cost());
			System.out.print("Color: ");
         if(x.Left().IsBlack()) System.out.println("Black");
         else System.out.println("Red");		

			System.out.print("Right child:  ");
			if (x.Right() == sentinel) System.out.println(" is a sentinel node.");
         else System.out.println(x.Right().Cost());
			System.out.print("Color: ");            
         if(x.Right().IsBlack()) System.out.println("Black");
         else System.out.println("Red");

			System.out.print("Parent:  ");
			if(x.Parent() != null){
		
      	   if (x.Parent() == sentinel) System.out.println(" is a sentinel node.");
      	   else System.out.println(x.Parent().Cost());
				System.out.print("Color: ");            
         	if(x.Parent().IsBlack()) System.out.println("Black");
         	else System.out.println("Red");
				}	
			else System.out.println("Null parent ie. root reached!");
      		}
		}


	public void PrintCoverage(){
		System.out.println("case1 count: "+case1ct);
		System.out.println("case2 count: "+case2ct);
		System.out.println("case3 count: "+case3ct);
		System.out.println("case4 count: "+case4ct);
		System.out.println("case5 count: "+case5ct);
		System.out.println("case6 count: "+case6ct);
		System.out.println("case7 count: "+case7ct);
		System.out.println("case8 count: "+case8ct);
		}

	public void drawLabel(ShadowLabel label, int x, int y, AlgAnimFrame frame) {
		FontMetrics fm = frame.getDrawingPanel().getFontMetrics(label.getFont());
		int str_width = fm.stringWidth(label.getText());
		int x1 = (frame.getDrawingPanel().getPanelHeight() - str_width)/2;

		drawRBTree(frame);
		label.move(x1, y);
		label.draw(offGraphics);
		Graphics g = dpAfter.getGraphics();
		g.drawImage(GetOffscreen(), 0, 0, null);
		}

	public void drawBeforeLabel(ShadowLabel label, int x, int y, AlgAnimFrame frame) {
		FontMetrics fm = frame.getBeforeDp().getFontMetrics(label.getFont());
		int str_width = fm.stringWidth(label.getText());
		int x1 = (frame.getBeforeDp().getPanelHeight() - str_width)/2;

		drawRBTree(frame);
		beforeOffSc = GetOffscreen();
		beforeOffGraphics = GetOffGraphics();
		label.move(x, y);
		label.draw(beforeOffGraphics);
		Graphics g = dpBefore.getGraphics();
		g.drawImage(beforeOffSc, 0, 0, null);
		}



	public void initBeforeOffsc(AlgAnimFrame frame, RBTree tree) {
		int h, w;
	
		dpBefore = frame.getBeforeDp();
		g = dpBefore.getGraphics();
		h = dpBefore.getPanelHeight();
		w = dpBefore.getPanelWidth();

		Dimension d = dpBefore.size();

		if(d.width < 1 || d.height < 1){
			System.out.println("d.height: "+d.height+ ", d.width: " +d.width);
			//return;
			}

		
		if((beforeOffSc == null) || (d.width != beforeOffSize.width) || (d.height != beforeOffSize.height)) {
			System.out.println("DrawRBTree: Creating offscreen");
			beforeOffSc = dpBefore.createImage(d.width, d.height);
			beforeOffSize = d;
			if(beforeOffSc == null) System.out.println("Offscreen still null");
			beforeOffGraphics = beforeOffSc.getGraphics();
			}
			if(beforeOffSc == null) System.out.println("Offscreen still null");

		beforeOffGraphics.setColor(dpBefore.getBackground());
		beforeOffGraphics.fillRect(0, 0, d.width, d.height);
		g.drawImage(beforeOffSc, 0 ,0, null);
		}
	
	public void DrawOffScreen(AlgAnimFrame frame, RBTree tree) {
		drawRBTree(frame);
		g.drawImage(tree.GetOffscreen(), 0, 0, null);
		}

	public Image GetOffscreen() {
		return offscreen;
		}

	public Graphics GetOffGraphics() {
		return offGraphics;
		}	
	
	public Image GetBeforeOffSc() {
		return beforeOffSc;
		}

	public Graphics GetBefOffGraphics() {
		return beforeOffGraphics;
		}	


	}

	
