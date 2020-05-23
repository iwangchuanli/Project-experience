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
	private int delay = 2000;
	private Graph graph;
	private String algfile;
	private String filename;

	String[] legends = { "T1", "T2" };
	Color[] colours = { Color.red, Color.blue };
	LFrame legend;

	public AlgThread(AlgAnimFrame frame) {
	 	this.frame = frame;
		int first = 0;
		
		algfile = frame.parentApp.getParameter("algfile");
		filename = frame.parentApp.getParameter("filename");

		System.out.println("MST: AlgThread / algfile " + algfile +
												" / file " + filename );
		if (frame != null && frame.alg != null && 
			frame.alg.drawingPanel != null) {
			 // drawingPanel already created -> this constructor called from
			 // clicking the run button -> use the generated data set
			 this.graph = frame.alg.graph;
			} 
		else {
			// this constructor called from Frame constructor
			drawingPanel = new GraphPanel(frame.algname);
			legend = new LFrame( colours, legends );
			init(algfile+first); //merv
			System.out.println("first: filename = "+algfile+first); 
			frame.drawingPanel = (Panel)this.drawingPanel; // merv
			// generateData();
			}
		}

	public void generateData() {
	 	int choice = frame.control_panel.getDataChoice();
			String fn;
		
		if (choice == 0) {
		 	fn = algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
					} 
		else if (choice == 1) {
		 	fn = algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
					}
					//drawingPanel.setMax(a.length);
					//drawingPanel.setSticks(a, a.length);
					//drawingPanel.repaint();
				 //frame.drawingPanel = (Panel)this.drawingPanel; //merv
			else if (choice == 2) {
			fn = algfile+choice;
			System.out.println("the filename is "+fn);
			init(fn);
			}
		
		}

	public void init( String fn ) {
		DataInputStream inStream = null;
		try {
			URL dataURL = new URL(frame.parentApp.getCodeBase(), fn);
			URL sourceURL = new URL(frame.parentApp.getCodeBase(), filename);
			//inStream = new DataInputStream( dataURL.openConnection().getInputStream());
			inStream = new DataInputStream( dataURL.openStream() );
			// frame.tf.ReloadText(sourceURL, sourceURL.getFile());
			drawingPanel.repaint();
			} 
		catch (IOException e) {
			System.out.println("File [" + fn + "] not found");
			} 
		catch ( SecurityException s ) {
			System.out.println("Your browser can't access the data file ["+fn+"]");
			System.out.println("Exception: " + s.getMessage() );
			}
		catch (NullPointerException e) {}
		if ( inStream != null ) {
			graph = new Graph(inStream);
			drawingPanel.Set_Graph(graph);
			}
		}

	public void run() {
		int choice = frame.control_panel.getDataChoice();
		
		new GraphMST(this, graph);
		// finish sorting
		frame.finishAlg();
		drawingPanel.repaint();
		}

    /**
     * Sets the delay between each animation step. This determines
     * the rate the drawingPanel is updated. The <code>setDelay</code>
     * method is normally called by the action listener of
     * the <code>delay choice button</code> on the control panel.
     * @param delay The delay set in milliseconds.
     */
    public void setDelay(int delay) {
			drawingPanel.setDelay(delay);
    	}


	public void restoreDrawingPanelColor() {
	 	//drawingPanel.restoreStickColor();
			}
	}
