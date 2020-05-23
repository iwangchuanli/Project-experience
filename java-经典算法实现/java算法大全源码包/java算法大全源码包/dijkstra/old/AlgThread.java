/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;

public class AlgThread extends Thread {
	static int max_data = 10;
   static String[] dataSets = 
               {"Graph 1", "Graph 2", "Graph 3"};

   AlgAnimFrame frame;
   static GraphPanel drawingPanel;
   int delay = 2000;
   Graph graph;

   public AlgThread(AlgAnimFrame frame) {
   	this.frame = frame;
		int first = 0;
		
      if (frame != null && frame.alg != null && 
	      frame.alg.drawingPanel != null) {
         // drawingPanel already created -> this constructor called from
         // clicking the run button -> use the generated data set
		   this.graph = frame.alg.graph;
      	} 
		else {
      	// this constructor called from Frame constructor
         drawingPanel = new GraphPanel(frame.algname);
	    	init(frame.algfile+first); //merv
			System.out.println("first: filename = "+frame.algfile+first); 
         frame.drawingPanel = (Panel)this.drawingPanel; // merv
      	}
    	}

	public void generateData() {
   	int choice = frame.control_panel.getDataChoice();
      String fn;
		
		if (choice == 0) {
	   	fn = frame.algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
        	} 
		else if (choice == 1) {
	   	fn = frame.algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
        	}
        	//drawingPanel.setMax(a.length);
        	//drawingPanel.setSticks(a, a.length);
        	//drawingPanel.repaint();
         //frame.drawingPanel = (Panel)this.drawingPanel; //merv
    	else if (choice == 2) {
			fn = frame.algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
			}
		
		}

	public void init( String fn ) {
		DataInputStream inStream = null;
	   try {
			URL dataURL = new URL(frame.parentApp.getDocumentBase(), fn);
			URL sourceURL = new URL(frame.parentApp.getDocumentBase(), frame.filename);
			inStream = new DataInputStream( dataURL.openConnection().getInputStream());
			frame.tf.ReloadText(sourceURL, sourceURL.getFile());
			drawingPanel.repaint();
	    	} 
		catch (IOException e) {
			System.out.println("file not found");
	    	} 
		catch (NullPointerException e) {}
	   graph = new Graph(inStream);
	   drawingPanel.Set_Graph(graph);
    	}

	public void run() {
		int choice = frame.control_panel.getDataChoice();
		
		if (frame.filename.compareTo("GraphDij.java")==0) {
			new GraphDij(this);
			}
		else if (frame.filename.compareTo("GraphMST.java")==0) {
			new GraphMST(this);
			}
			
			
/*			
		if (choice == 0) {
	   	new GraphMST(this);
			} 
		else if (choice == 1) {
	   	new GraphMST(this);
			}
*/
      // finish sorting
      frame.finishAlg();
		drawingPanel.repaint();
    	}

	public void restoreDrawingPanelColor() {
   	//drawingPanel.restoreStickColor();
    	}
	}
