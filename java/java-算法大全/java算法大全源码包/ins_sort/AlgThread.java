/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {
    static int max_data = 20;
    static String[] dataSets = {"Random Data", "Ascending Data",
		"Descending Data"};

    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    int[] a;

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.drawingPanel = frame.alg.drawingPanel;
	    this.a = frame.alg.a;
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
            generateRandomData(max_data);
        } else if (choice == 1) {
            generateAscendingData(max_data);
	} else if (choice == 2)
            generateDescendingData(max_data);
	/*-*/drawingPanel.setProcFreq(false);
	/*-*/drawingPanel.initFreq();setData(a);drawingPanel.repaint();
    }
    
    public void generateRandomData(int n) {
	a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = new Double(Math.random()*1000).intValue() + 1;
    }
 
    public void generateAscendingData(int n) {
	a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (i+1)*37;
    }

    public void generateDescendingData(int n) {
	a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (n-i+1) * 37;
    }

    public void setData(int[] a) {
	for (int i = 0; i < a.length; i++) {
	    drawingPanel.setFreq(i, a[i]);
	}
    }

    public Node formNode(int i, int weight) {
	char[] c = {(char)(i + 'A')};
	return new Node(new String(c), weight);
    }

/*-------------------------------------------------------*/
    public void insertionSort(int[] a) {
	/*-*/frame.Highlight(0);
        int n = a.length; // a contains n items to be sorted
	/*-*/frame.Highlight(1); Node node;
	int j, v;
	// initially, the first item is considered to be sorted
	// i divides a into a sorted region, x < i, and an
	// unsorted one, x >= i
	/*-*/ drawingPanel.initSortedNodes();
	/*-*/ drawingPanel.insertSortedNode(formNode(0, a[0]), 0);
	/*-*/ frame.setText(1, "Move node to sorted region...");
	for (int i = 1; i < n; i++) {
	    /*-*/frame.Highlight(6);
	    // select the item at the beginning of the as yet
	    // unsorted section
	    v = a[i];
	    /*-*/frame.Highlight(9);
	    // work backwards through the array, finding where v should go
	    j = i;
	    /*-*/frame.Highlight(11);
	    /*-*/frame.setText(1, "Checking where to insert node...");
	    // If this element is greater than v, move it up one
	    while (a[j-1] > v) {
	    	/*-*/frame.Highlight(13);
		a[j] = a[j-1];j--;
	    	/*-*/frame.Highlight(14);
		/*-*/drawingPanel.flashNode(j);
		/*-*/drawingPanel.moveNodeRight(j);
		/*-*/frame.setText(1, "Checking node " + j + "...");
		if (j <= 0) break;
	    }
	    /*-*/frame.Highlight(16);
	    /*-*/if (j>0) drawingPanel.flashNode(j-1);
	    /*-*/frame.setText(1, "Checking node " + (j-1) + "...");
	    // stopped when a[j-1] <= v, so put v at position j
	    /*-*/frame.setText(1, "Location found..");
	    a[j] = v;
	    /*-*/frame.Highlight(18);
	    /*-*/frame.setText(2, "Insert node at location " + j + "..");
	    /*-*/drawingPanel.insertSortedNode(formNode(i, v), j);
	    /*-*/frame.waitStep();
	}
	/*-*/frame.Highlight(19);
    }
//----
    public void run() {
	/*-*/drawingPanel.initFreq();setData(a);drawingPanel.repaint();
	insertionSort(a);

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanelColor() {
        //drawingPanel.restoreBinColor();
        frame.repaint();
    }
}
