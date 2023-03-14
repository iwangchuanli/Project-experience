/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * <code>AlgThread</code> is an extension of the provided Java 
 * multi-threading package.
 * It enables the graphical display of the main execution thread to be
 * updated during the execution of the algorithm.
 * <p>
 * A section of this source file is normally displayed on the TextFrame.
 * This section starts with the line after 
 * <code>/*------------------*</code><code>/
 * </code> and finishes before the line <code>//------------
 * </code>.
 * Every line starting with <code>/*-*</code><code>/</code> will be discarded in
 * the source code display area and each line is terminated by the first
 * encounter of <code>/*-*</code><code>/</code>.
 * <p>
 * The source file <code>AlgThread.java</code> is <b>to be
 * modified/completed</b> for separate animation algorithms.
 *
 * @author	Woi L Ang
 * @since	JDK1.0
 */
public class AlgThread extends Thread {
    /**
     * Array of strings used to set the choices of the data choice button
     * in ControlPanel.
     * @see ControlPanel
     */
    public static String[] dataSets = {"Linear Probing", 
		"Quadratic Probing (c=1)",
		"Quadratic Probing (c=2)",
		"Quadratic Probing (c=4)"};
    /**
     * The initial caller of the constructor of this class.
     * It holds the references to all existing panels.
     */ 
    public AlgAnimFrame frame;
    /**
     * A reference to the drawing panel in order to update the animation
     * during the execution of the algorithm.
     * @see DrawingPanel
     */
    public DrawingPanel drawingPanel;
    public TablePanel table;
    
    String datafile = "textfile.txt";
    String[] words = null;
    String allInputWords;
    int c = 1;

    static final int max_table_size = 101;

    /**
     * Creates a new thread using the frame passed in as the parameter.
     * If this constructor is called from the frame constructor,
     * a drawingPanel will be initialized and assigned to the frame
     * class.
     * 
     * @param frame An extended frame where the algorithm is going to 
     * execute in.
     *
     * @see AlgAnimFrame
     */
    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.drawingPanel = frame.alg.drawingPanel;
	    this.words = frame.alg.words;
	    this.c = frame.alg.c;
	    this.allInputWords = frame.alg.allInputWords;
        } else {
            // this constructor called from Frame constructor
            drawingPanel = new DrawingPanel();
            frame.drawingPanel = (Panel)this.drawingPanel;
        }
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

    /**
     * Generate data based on the choice made on the choice button
     * created in ControlPanel. This method is application specific
     * and the contents for the satisfaction of each 'if' statement
     * have to be filled in based on the algorithm.
     * @see ControlPanel
     */
    public void generateData() {
        int choice = frame.control_panel.getDataChoice();
        if (choice == 0) {
	    frame.control_panel.setText(
		"Linear Probing collision handling...");
	    drawingPanel.setRehashFunction("( H() + i )");
        } else if (choice == 1) {
	    c = 1;
	    frame.control_panel.setText(
		"Quadratic Probing collision handling... C = " + c);
	    drawingPanel.setRehashFunction("( H() + i^2 )");
        } else if (choice == 2) {
	    c = 2;
	    frame.control_panel.setText(
		"Quadratic Probing collision handling... C = " + c);
	    drawingPanel.setRehashFunction("( H() + 2 * i^2 )");
        } else if (choice == 3) {
	    c = 4;
	    frame.control_panel.setText(
		"Quadratic Probing collision handling... C = " + c);
	    drawingPanel.setRehashFunction("( H() + 4 * i^2 )");
	}
frame.setText(1, "This demo reads words from a text file and");
frame.setText(2, "stores them into a hash table...");
	if (words == null) {
	    loadFile();
	    allInputWords = new String();
	    for (int i = 0; i < words.length; i++)
    	    	allInputWords = allInputWords.concat(" " + words[i]);
	}
	table = drawingPanel.table;
	table.setTableSize(max_table_size);
	drawingPanel.setInputText(allInputWords.trim());
	drawingPanel.repaint();
    }

    public void loadFile() {
	AlgAnimApp app = frame.parentApp;

	InputStream inFile = null;
	try {
	    URL dataURL =
		new URL(app.getCodeBase(), datafile);
	    inFile = dataURL.openStream();

	    words = new String[700]; int wc = 0;
	    String thisWord = new String();
	    int c = inFile.read();
	    while (c != -1) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c < 'z')) {
		    thisWord = thisWord + (char)c;
		} else {
		    if (thisWord.length() > 0) {
			words[wc] = thisWord.toLowerCase();
			thisWord = new String();
		    	wc++;
		    	if (wc > words.length-1)
			    break;
		    }
		}
		c = inFile.read();
	    }
	    inFile.close();
	} catch (IOException e) {
	    System.out.println("Error opening file: " + e);
	}
    }

/*--------------------------------------------------------------------*/
//----
  public int hash(String str) {
		int sum = 0;

		// hash the word based on the first 8 character of the string
		// so the hash value ranges from 0 to 200
		String word = str.trim().toUpperCase();
		for (int i = 0; i < word.length(); i++) {
	    if (i==8) break;
	    sum += (int)word.charAt(i);
			}
		return (sum % max_table_size);
    }

  public int rehash(int hash, int c, int i) {
    int choice = frame.control_panel.getDataChoice();
		if (choice == 0) // linearing probing
	    if ((hash+1) < max_table_size)
	    	return hash+1;
	    else return 0;
		else {//   ->  quadratic probing
	    int val = hash + c * (i * i - (i-1)*(i-1));
	    if (val < max_table_size)
	    	return val;
	    else
				return (val % max_table_size);
			}
    }

	public void hashTableDemo(String[] words) {
	// insert words into table
	for (int i = 0; i < words.length; i++) {
	    if (words[i] == null || table.full()) 
		break;
/*-*/drawingPanel.setInput(words[i], hash(words[i])); frame.waitStep();
/*-*/drawingPanel.contains(words[i]);
	    if (table.contains(words[i]))
		continue;

	    if (table.occupied(hash(words[i]))) {
/*-*/drawingPanel.collision(words[i], hash(words[i]));
		int k = 1;
		int index = rehash(hash(words[i]), c, k++);
/*-*/drawingPanel.rehash(index);
		boolean found = true;
		while (table.occupied(index)) {
/*-*/drawingPanel.collision(words[i], index);
		    index = rehash(index, c, k++);
/*-*/drawingPanel.rehash(index);
		    if (index == hash(words[i])) {
			// cannot find an unoccupied space using this 
		  	// rehashing scheme
			found = false;
			break;
		    }
		}
		if (found) {
/*-*/drawingPanel.setTableEntry(words[i], index);
/*-*/drawingPanel.incHashStat(k);
		    table.setTableEntry(words[i], index);
/*-*/drawingPanel.setColStat(table.numOccupied(), k-1);
		} else {
/*-*/frame.setText(1, "There is no space to fit in this entry...");
/*-*/frame.setText(2, "Animation terminated...");
		    break;
		}
	    } else {
/*-*/drawingPanel.setTableEntry(words[i], hash(words[i]));
/*-*/drawingPanel.incHashStat(1);
		table.setTableEntry(words[i], hash(words[i]));
	    }
	}
    }

    /**
     * This method is invoked when the <code>Thread</code> class
     * <code>start()</code> method is called.
     * It contains the statements to execute the methods which perform
     * the algorithm. This method is to be completed based on separate
     * animation algorithms.
     * @see java.lang.Thread
     */
    public void run() {
	table = drawingPanel.table;
	table.setTableSize(max_table_size);
	frame.setText(1, "This demo reads words from a text file and");
	frame.setText(2, "stores them into a hash table...");
	drawingPanel.setInputText(allInputWords.trim());
	hashTableDemo(words);

        // finish
        frame.finishAlg();
	frame.control_panel.setText("Execution completed successfully...");
    }

    /**
     * Restore the drawing panel at the beginning or the end of each
     * simulation.
     */
    public void restoreDrawingPanelColor() {
    }
}
