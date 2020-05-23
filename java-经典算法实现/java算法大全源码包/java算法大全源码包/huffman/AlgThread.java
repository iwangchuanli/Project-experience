/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {
    static String[] dataSets = {"File 1", "File 2"};

    Hashtable data, table;
    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    InputStream inFile;
    Vector sortedNodes;
    Node tree;
    String datafile = "plds210.txt";

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.drawingPanel = frame.alg.drawingPanel;
	    this.datafile = frame.alg.datafile;
        } else {
            // this constructor called from Frame constructor
            drawingPanel = new DrawingPanel();
            frame.drawingPanel = (Panel)this.drawingPanel;
        }
    }

    public void setDelay(int delay) {
	drawingPanel.setDelay(delay);
    }

    public void generateData() {
        int choice = frame.control_panel.getDataChoice();
        if (choice == 0) {
	    datafile = new String("plds210.txt");
        } else if (choice == 1) {
	    datafile = new String("hard.txt");
	}
    }
    
    public void openStream() {
	AlgAnimApp app = frame.parentApp;

	inFile = null;
	try {
	    URL dataURL = 
		new URL(app.getCodeBase(), datafile);
	    inFile = dataURL.openStream();
	} catch (IOException e) {
	    System.out.println("Error opening file: " + e);
	} 

	// intialize hashtable
	data = new Hashtable();
    } // openStream()

    public void addFreqInHashtable(int c) {
	Integer key = new Integer(c);
	Integer freq;
	if (data.containsKey(key))
	    freq = (Integer)data.get(key);
	else
	    freq = new Integer(0);
	data.put(key, new Integer(freq.intValue() + 1));

	if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c < 'z')) {
	    if (c >= 'A' && c <= 'Z') {
	    	drawingPanel.setFreq(c - 'A', 
			freq.intValue() + 1);
	    } else
	    	drawingPanel.setFreq(c - 'a', freq.intValue() + 1);
	}
	drawingPanel.addMovingText((char)c);
    }

    public void filterHashtable() {
	Hashtable oldTable = data;
	data = new Hashtable();
	
	for (int i = (int)'A'; i < 1+(int)'Z'; i++) {
	    Integer key = new Integer(i);
	    Integer value, lowKey = new Integer(i + 32); 
	    int upperValue, lowerValue;
	    if (oldTable.containsKey(key))
		upperValue = ((Integer)oldTable.get(key)).intValue();
	    else
		upperValue = 0;
	    // check lower case
	    if (oldTable.containsKey(lowKey))
		lowerValue = ((Integer)oldTable.get(lowKey)).intValue();
	    else
		lowerValue = 0;
	    value = new Integer(upperValue + lowerValue);
	    data.put(key, value);
	    drawingPanel.setFreq(i - 'A', value.intValue());
	}
	drawingPanel.repaint();
    }

    public void sortByFrequency() {
	// precondition: only A to Z left in hashtable data
	sortedNodes = new Vector(); /*-*/drawingPanel.initSortedNodes();
	for (int i = (int)'A'; i < 1+(int)'Z'; i++) {
	    Integer key = new Integer(i);
	    Integer value = (Integer)data.get(key);

	    char[] c = {(char)i};
	    Node node = new Node(new String(c), value.intValue());
	    // insert into sortedNodes in order
	    boolean inserted = false;
	    for (int j = 0; j < sortedNodes.size(); j++) {
		if ( value.intValue() < 
			 ((Node)(sortedNodes.elementAt(j))).getWeight() ) {
		    sortedNodes.insertElementAt(node, j);
		    /*-*/ drawingPanel.insertSortedNode(node, j);
		    inserted = true;
		    break;
		}
	    }
	    if (!inserted) {
		sortedNodes.addElement(node);
		drawingPanel.insertSortedNode(node, sortedNodes.size()-1);
	    }
	}
    }

    public void restoreAllNodes(Node node) {
	drawingPanel.restoreNode(node);
	if (!node.isLeaf()) {
	    drawingPanel.restoreLeft(node);
	    drawingPanel.restoreRight(node);
	    restoreAllNodes(node.getLeftNode());
	    restoreAllNodes(node.getRightNode());
	}
    }
/*------------------------------------------------*/
    public void readData() {
	/*-*/frame.Highlight(0);
	openStream();
	/*-*/frame.Highlight(1);drawingPanel.initFreq();
	try {
	    /*-*/frame.Highlight(2);
	    int c = inFile.read();
	    /*-*/frame.Highlight(3);
	    	/*-*/frame.Highlight(4);
	    	/*-*/frame.Highlight(5);
	    	/*-*/frame.Highlight(6);
	    	/*-*/frame.Highlight(7);
	    	/*-*/frame.Highlight(9);
	    while (c != -1) {
		addFreqInHashtable(c);
		c = inFile.read();
	    }
	} catch (IOException e) {}
	filterHashtable(); // eliminate character outside A-Z
			   // and merge lowercases with uppercases
	/*-*/drawingPanel.clearMovingText();
	/*-*/frame.waitStep();
	/*-*/frame.Highlight(11);
	/*-*/frame.setText(0, "Finished reading from file...");
	/*-*/drawingPanel.setProcFreq(false);drawingPanel.moveOrigNodesUp();
	/*-*/frame.setText(1, "Sorting initial data based on frequency...");
	sortByFrequency();
	/*-*/frame.setText(1, "Finish sorting...");
	/*-*/frame.Highlight(12);drawingPanel.hideOrigNodes();
	/*-*/frame.waitStep();drawingPanel.moveSortedNodesUp();
    }

    public void formHuffmanTree() { /*-*/frame.Highlight(14);
	/*-*/frame.Highlight(15); frame.setText(0, "Processing text file...");
	readData();
	tree = new Node(); // empty the tree
	/*-*/frame.Highlight(16);

	// perform greedy algorithm
	/*-*/drawingPanel.setNodes(sortedNodes);
	/*-*/frame.setText(0, "Perform Greedy Algorithm");
	while (sortedNodes.size() > 1) {/*-*/frame.Highlight(19);
	    // form a node from the first two nodes
	    Node comNode = new Node((Node)sortedNodes.firstElement(), 
					(Node)sortedNodes.elementAt(1));
	    /*-*/frame.Highlight(21);
	    /*-*/frame.setText(1, "Combining the two lowest frequency nodes..");
	    /*-*/drawingPanel.combine((Node)sortedNodes.firstElement(), 
	    /*-*/	(Node)sortedNodes.elementAt(1), comNode); 
	    /*-*/frame.waitStep();
	    // remove the first two nodes from sortedNodes
	    sortedNodes.removeElementAt(0);
	    /*-*/frame.Highlight(24);
	    sortedNodes.removeElementAt(0);
	    /*-*/frame.Highlight(25);

	    // place the combined node at the appropriate posn
	    boolean inserted = false;
	    /*-*/frame.Highlight(28);frame.Highlight(29);
	    /*-*/frame.Highlight(30);frame.Highlight(32);
	    /*-*/frame.Highlight(33);frame.Highlight(34);
	    /*-*/frame.Highlight(35);frame.Highlight(36);
	    /*-*/frame.Highlight(37);
	    /*-*/frame.setText(2, "Moving subtree into its correct place...");
	    for (int i = 0; i < sortedNodes.size(); i++) {
		if (comNode.getWeight() <=
			((Node)sortedNodes.elementAt(i)).getWeight()) {
		    sortedNodes.insertElementAt(comNode, i);
		    /*-*/drawingPanel.moveComNode(comNode, i);
		    inserted = true;
		    break;
		}
	    }
	    if (!inserted) {
		sortedNodes.addElement(comNode);
	    	/*-*/frame.Highlight(38);
		/*-*/drawingPanel.moveComNode(comNode, sortedNodes.size()-1);
	    }
	    /*-*/frame.Highlight(39);
	    /*-*/frame.setText(2, "Subtree movement completed...");
	    /*-*/frame.waitStep();
	} /*-*/frame.Highlight(40);

	// assign tree
	/*-*/frame.Highlight(43);
	if (sortedNodes.size() < 1)
	    return;
	tree = (Node)sortedNodes.firstElement();
	/*-*/frame.setText(0, "Greedy Algorithm completed...");
	/*-*/frame.setText(1, "Final tree produced...");
	/*-*/frame.setText(2, "");
	/*-*/frame.Highlight(45); drawingPanel.centralize(tree);
	/*-*/frame.waitStep();

	// encoding table
	/*-*/frame.setText(0, "Form encoding lookup table...");
	table = new Hashtable(); /*-*/frame.Highlight(48); 
	/*-*/drawingPanel.initTable();
	String code = new String();/*-*/frame.Highlight(49);
	/*-*/frame.Highlight(50);
	encodingHashtable(tree, table, code);
	/*-*/drawingPanel.repaint();frame.waitStep();
	/*-*/frame.Highlight(51);
	encoding_decoding_example("HELLO");
	/*-*/frame.Highlight(52);
	/*-*/frame.setText(0, "Execution completed...");
	/*-*/frame.setText(1, "Dispose this frame to exit...");
	/*-*/frame.setText(2, "or click the run button to restart...");
    }

    public void encodingHashtable(Node tree, Hashtable table, String code) {
	/*-*/frame.Highlight(54);drawingPanel.highlightNode(tree);
	/*-*/frame.setText(1, "Current code is " + code + "...");
	if (tree.isLeaf()) {
	    /*-*/frame.Highlight(55);
	    // insert into hash table
	    Character c = new Character(tree.getLabel().charAt(0));
	    /*-*/frame.Highlight(57);
	    /*-*/frame.setText(2, "Leaf node -> insert into table...");
	    table.put(c, code);
	    /*-*/frame.Highlight(58);drawingPanel.addTable(c, code);
	    /*-*/frame.waitStep();
	} else {
	    /*-*/frame.setText(2, 
	    /*-*/	"Recursively called encodingHashtable method...");
	    /*-*/frame.Highlight(59);
	    /*-*/frame.Highlight(60);drawingPanel.highlightLeft(tree);
	    encodingHashtable(tree.getLeftNode(), table, code + "0");
	    /*-*/drawingPanel.restoreLeft(tree);
	    /*-*/frame.Highlight(61);drawingPanel.highlightRight(tree);
	    encodingHashtable(tree.getRightNode(), table, code + "1");
	    /*-*/drawingPanel.restoreRight(tree);
	}
	/*-*/frame.Highlight(62);
	/*-*/frame.Highlight(54);drawingPanel.restoreNode(tree);
    } // encodingHashtable()

    public void encoding_decoding_example(String str) {
	/*-*/drawingPanel.moveTreeUp();frame.setText(0, "Test encoding...");
	/*-*/drawingPanel.moveTableRight();
	/*-*/frame.Highlight(65); drawingPanel.setTestString(str);
	String code = new String();
	/*-*/frame.Highlight(66);
	for (int i = 0; i < str.length(); i++) {
	    /*-*/frame.Highlight(67);
	    Character c = new Character(str.charAt(i));
	    /*-*/frame.Highlight(68); frame.setText(1, "Encoding " + c + "...");
	    code = code.concat((String)table.get(c) + " ");
	    /*-*/frame.Highlight(69); drawingPanel.setHighlightBit(0);
	    /*-*/drawingPanel.highlightTable(c.charValue());
	    /*-*/drawingPanel.removeFirstChar();
	    /*-*/drawingPanel.setCode(code);
	    /*-*/drawingPanel.setHighlightBit(-1);
	    /*-*/frame.setText(2, "Resulting code = " + code + "...");
	    /*-*/frame.waitStep();
	}
	
	/*-*/drawingPanel.restoreTable();frame.setText(0, "Test Decoding...");
	/*-*/frame.setText(1, ""); frame.setText(2, "");
	/*-*/drawingPanel.moveTreeDown(tree);
	// decoding from code without space
	StringTokenizer st = new StringTokenizer(code);
	/*-*/frame.Highlight(73);
	code = new String();
	/*-*/frame.Highlight(74);
	while (st.hasMoreTokens()) code = code.concat(st.nextToken());
	/*-*/frame.Highlight(75);
	String result = new String();
	/*-*/frame.Highlight(76);
	/*-*/frame.Highlight(77);
	/*-*/drawingPanel.decode(true);
	decode(code, tree, tree, result);
	/*-*/frame.Highlight(77);
    }

    public void decode(String code, Node branch, 
				Node tree, String result) {
	/*-*/drawingPanel.setCode(code);drawingPanel.highlightNode(branch);
	/*-*/frame.Highlight(82);
	if (branch.isLeaf()) {
	    /*-*/frame.Highlight(83);
	    if (code.length() > 1) {
	    	/*-*/frame.Highlight(84);
		result = result.concat(branch.getLabel());
		/*-*/drawingPanel.setTestString(result);
	    	/*-*/frame.Highlight(85);restoreAllNodes(tree);
		/*-*/frame.setText(1, "Decoded " + result + "...");
		/*-*/drawingPanel.repaint();
	    	/*-*/frame.waitStep();
		decode(code, tree, tree, result);
	    	/*-*/frame.Highlight(85);
	    } else {
	    	/*-*/frame.Highlight(86);
		result = new String(result + branch.getLabel());
	    	/*-*/frame.Highlight(87);
		/*-*/drawingPanel.setTestString(result);
	    	/*-*/frame.Highlight(88);restoreAllNodes(tree);
		/*-*/frame.setText(1, "Decoded " + result + "...");
		/*-*/drawingPanel.repaint();
	    	/*-*/frame.waitStep();
	    }
	} else {
	    /*-*/frame.Highlight(89);
	    if (code.startsWith("0")) {
	    	/*-*/frame.Highlight(90);
		/*-*/drawingPanel.highlightLeft(branch);
	    	/*-*/frame.Highlight(91);
	    	/*-*/frame.Highlight(92);
		decode(code.substring(1), 
			branch.getLeftNode(), tree, result);
	    } else { // (code.startsWith("1"))
	    	/*-*/frame.Highlight(93);
		/*-*/drawingPanel.highlightRight(branch);
	    	/*-*/frame.Highlight(94);
	    	/*-*/frame.Highlight(95);
		decode(code.substring(1), 
			branch.getRightNode(), tree, result);
	    }
	}
	/*-*/frame.Highlight(97);
	/*-*/drawingPanel.restoreNode(branch);
    }
//----
    public void run() {
	formHuffmanTree();

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanelColor() {
        //drawingPanel.restoreBinColor();
        frame.repaint();
    }
}
