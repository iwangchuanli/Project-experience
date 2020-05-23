/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {
	static int max_data = 10;
	static String[] dataSets = { "Graph 1", "Graph 2", "Graph 3"};
	static String base_data_file_name = "graph.rb";
	public AlgAnimFrame frame;
	
	public DrawingPanel dpAfter, dpBefore;
	DataPanel datapanel;
	//int delay = 2000;
//	boolean source_loaded = false;
	RBTree tree;
	IntList int_list;
	static final int y_offset = 10;
	static final int x_offset = 50;
	/*public Image beforeOffSc = null;
	Graphics beforeOffGraphics = null;
	Dimension beforeOffSize = null;

	public Image dataOffSc = null;
	Graphics dataOffGraphics = null;
	Dimension dataOffSize = null;
*/

	
	public AlgThread( AlgAnimFrame frame ) {
		this.frame = frame;
		if(frame == null) {System.out.println("null frame");}

		System.out.println("AlgThread cons entry");
		
		if ((frame != null) && (frame.getAlg() != null) && 
				(frame.getAlg().dpAfter != null)) {
			// drawingPanel already created -> this constructor called from
			// clicking the run button -> use the generated data set
			} 
		else {
			// First time .. 
			// this constructor called from Frame constructor
			// We've decided not to do anything here!
			}
		}

	// Added in a setDelay function here
	public void setDelay(int delay) {
		dpAfter.setDelay(delay);
		}

	private boolean loadData( int choice ) {
		String fn;
		DataInputStream inStream;
		String line;

		System.out.println("loadData: choice " + choice );
		//fn = frame.algfile+choice;

		// Can I do this?
		fn = base_data_file_name + choice;

		System.out.println("loadData: filename "+fn);

		try {
			URL dataURL = new URL(frame.getApplet().getCodeBase(), fn);
			int_list = new IntList( dataURL );
			} 
		catch (IOException e) {
			System.out.println("Data file or source file not found");
			} 
		catch (NullPointerException e) {}

		return int_list != null;
		}

	public void generateData() {
		//int choice = frame.control_panel.getDataChoice();
		//added
		this.dpAfter = frame.getDrawingPanel();
		dpAfter.init();

		int choice = frame.getDataChoice();

		if ( loadData( choice ) ) {
			System.out.println("Data loaded OK");
			}
		else {
			System.out.println("Data loading error");
			}
		}

	public void init( String fn ) {
		System.out.println("init: enter - fn " + fn);
		}

	public void run() {
		int k;
		Graphics g1, g2, g3;
		//DrawingPanel dp;
		Image beforeOffSc = null;
		Graphics befOffGraphics = null;

		System.out.println("run: enter");
		dpAfter = frame.getDrawingPanel();
		dpBefore = frame.getBeforeDp();
		if ( dpAfter == null ) {
			System.out.println( "run: null drawingPanel");
			return;
			}
		tree = new RBTree();
		//tree.setPanel( dpAfter );

		//Initialise beforeOffsc
		tree.initBeforeOffsc(frame, tree);

		generateData();
		if ( int_list != null ) {
			for(k=0;k<int_list.size();k++) {
				// Get the next one to insert
				int data = int_list.elementAt( k );

				if(k!=0) {
					ShadowLabel bforeLabel = new ShadowLabel("Before New Insertion");
					bforeLabel.move(50, (dpBefore.getPanelHeight() - y_offset));
					bforeLabel.draw(befOffGraphics);
					g2 = dpBefore.getGraphics();
					g2.drawImage(beforeOffSc, 0, 0, null);		
					}	
					
			
				// Insert into the tree
				tree.InsertNode( data, frame, tree );
				g1 = dpAfter.getGraphics();
				tree.drawRBTree(frame);
				if (tree.GetOffscreen() == null) {
					System.out.println("AlgThread: Run: offscreen is null");
					return;
					}
				else {
					g1.drawImage(tree.GetOffscreen(), 0, 0, null);
					beforeOffSc = tree.GetOffscreen();
					befOffGraphics = tree.GetOffGraphics();
					frame.waitSkip();
					}
				}

			ShadowLabel finalLabel = new ShadowLabel("Final Red-Black Tree");
			finalLabel.move(x_offset, (dpBefore.getPanelHeight() - y_offset));
			finalLabel.draw(befOffGraphics);
			g2 = dpBefore.getGraphics();
			g2.drawImage(beforeOffSc, 0, 0, null);

			// finish creating tree 
			frame.finishAlg();
			dpAfter.repaint();
			
			//g2.drawImage(beforeOffSc, 0, 0, null);
			}
		else {
			System.out.println("Data file not found\n");
			}
		}

	public void restoreDrawingPanel() {
		// This needs to be here because it get called from AlgAnimFrame
		}

	//This method needs to be here for the skip button to be effective
	public void waitSkip() {
		if(!dpAfter.getSkip()) return;
		((ImageButton)frame.getSkipItem()).setEnable();
		((ImageButton)frame.getRunItem()).setEnable();
		((ImageButton)frame.getStopItem()).setDisable();
		dpAfter.setSkip(false);
		frame.setStep(true);
		frame.waitStep();
		}

	/*public void initBeforeOffsc() {
		int g, h, w;

		dpBefore = frame.getBeforeDp();
		//g = dpBefore.getGraphics();
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
		}*/

	

	}
