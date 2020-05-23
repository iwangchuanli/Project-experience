/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {
    int max_data = 10;
    static String[] dataSets = {"Random Data", "Ascending Data", 
				"Descending Data", "Sorting 10 num",
				"Sorting 20 num"};

    Hashtable data, table;
    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    InputStream inFile;
    Vector sortedNodes;
    Node tree;
    Heap heap;
    int[] a;

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.drawingPanel = frame.alg.drawingPanel;
	    this.a = frame.alg.a;
	    this.heap = frame.alg.heap;
        } else {
            // this constructor called from Frame constructor
            drawingPanel = new DrawingPanel();
            frame.drawingPanel = (Panel)this.drawingPanel;
	    /*-*/heap = new Heap(drawingPanel, max_data); 
	    /*-*/drawingPanel.drawHeap(heap);
        }
    }

    public void setDelay(int delay) {
	drawingPanel.setDelay(delay);
    }

    public void generateData() {
	a = new int[max_data];
        int choice = frame.control_panel.getDataChoice();
        if (choice == 0) {
	    generateRandomData(max_data);
        } else if (choice == 1) {
	    generateAscendingData(max_data);
	} else if (choice == 2) {
	    generateDescendingData(max_data);
	} else if (choice == 3) {
	    max_data = 10;
	    a = new int[max_data];
	    /*-*/heap = new Heap(drawingPanel, max_data); 
	    /*-*/drawingPanel.drawHeap(heap);
	    generateRandomData(max_data);
	} else if (choice == 4) {
	    max_data = 20;
	    a = new int[max_data];
	    /*-*/heap = new Heap(drawingPanel, max_data); 
	    /*-*/drawingPanel.drawHeap(heap);
	    generateRandomData(max_data);
	}
	/*-*/heap.initHeap(); heap.setInput(a);
    }

    public void generateRandomData(int n) {
        for (int i = 0; i < n; i++)
            a[i] = new Double(Math.random()*99).intValue() + 1;
    }
 
    public void generateAscendingData(int n) {
        for (int i = 0; i < n; i++)
            a[i] = (i+1)*4;
    }
 
    public void generateDescendingData(int n) {
        for (int i = 0; i < n; i++)
            a[i] = (n-i+1) * 4;
    }
    
    public int[] setHeapWithSize(int[] a, int i) {
	int[] old = a;
	a = new int[i];
	for (int j = 0; j < i; j++)
	    a[j] = old[j];
	return a;
    }
/*--------------------------------------------------------------------*/
    public void heapsort(int[] a) {
	/*-*/frame.Highlight(0); heap.setInput(a);
	int[] A = setHeapWithSize(a, a.length); 
	/*-*/frame.setText(0, "Transferring data to initial tree...");
	/*-*/frame.setText(1, "This is a complete binary tree but not yet a heap...");
	/*-*/frame.Highlight(1); 
	/*-*/for (int i = 0; i < a.length; i++, heap.input2heap()); 
	/*-*/frame.waitStep();
	/*-*/frame.setText(0, "Building Heap...");
	buildHeap(A);
	/*-*/frame.Highlight(2);
	/*-*/frame.setText(0, "Performing heap sort algorithm...");
	for (int i = a.length; i > 1; i--) {
	    /*-*/frame.Highlight(3);
	    a[i-1] = A[0]; A[0] = A[i-1];
	    /*-*/frame.setText(1, "Extracting data from heap...");
	    /*-*/frame.Highlight(4);heap.addOutput(a[i-1]);
	    A = setHeapWithSize(A, A.length - 1);
	    /*-*/frame.setText(1, "Move last node to the first...");
	    /*-*/heap.moveLast2First();heap.setHeap(A);
	    /*-*/frame.Highlight(5);
	    /*-*/frame.setText(1, "Heapify root node...");
	    heapify(A, 1);
	    /*-*/frame.Highlight(6);
	    /*-*/frame.waitStep();
	}
	/*-*/frame.Highlight(7);
	/*-*/frame.setText(1, "Extracting last node from heap...");
	a[0] = A[0]; /*-*/heap.nodeList = new Vector();heap.addOutput(a[0]);
	/*-*/frame.Highlight(8);
	/*-*/frame.Highlight(9);
        /*-*/frame.setText(0, "Execution completed...");
        /*-*/frame.setText(1, "Dispose this frame to exit...");
        /*-*/frame.setText(2, "or click the run button to restart...");
    }

    public void buildHeap(int[] a) {
	/*-*/frame.Highlight(11);
	/*-*/frame.Highlight(12);
	for (int i = a.length/2; i > 0; i--) {
	    /*-*/frame.Highlight(13);
	    /*-*/frame.setText(1, "Heapifying node " + i + 
	    /*-*/	" from buildHeap...");
	    heapify(a, i);
	    /*-*/frame.setText(1, "");
	    /*-*/frame.Highlight(13);frame.waitStep();
	}
    }

    public void heapify(int[] a, int i) {
	/*-*/frame.Highlight(17);heap.highlight(i-1);heap.redraw();
	int l = left(i);/*-*/frame.Highlight(18);
	int r = right(i);/*-*/frame.Highlight(19);
	/*-*/frame.setText(1, "Left node = " + l + "; Right node = " + r);
	int largest;
	/*-*/frame.Highlight(21);heap.highlight(l-1);heap.redraw();
	if ( l <= heapSize(a) && a[l-1] > a[i-1] ) {
	    largest = l;
	    /*-*/frame.Highlight(22);
	    /*-*/frame.setText(2, "Left node is the largest...");
	} else {
	    /*-*/frame.Highlight(23);
	    largest = i;
	    /*-*/frame.setText(2, "Current node is the largest...");
	    /*-*/frame.Highlight(24);
	    /*-*/frame.Highlight(25);
	}
	/*-*/heap.restore(l-1);
	/*-*/frame.Highlight(26);heap.highlight(r-1);heap.redraw();
	if ( r <= heapSize(a) && a[r-1] > a[largest-1] ) {
	    /*-*/frame.Highlight(27);
	    largest = r;
	    /*-*/frame.setText(2, "Right node is the largest now...");
	}
	/*-*/heap.restore(r-1);heap.redraw();
	/*-*/frame.Highlight(29);
	if (largest != i) {
	    // exchange a[i], a[largest]
	    /*-*/frame.setText(1, "Current node is not the largest...");
	/*-*/frame.setText(2, "Exchanging current node with the largest...");
	    /*-*/frame.Highlight(31);
	    int tmp = a[i-1]; a[i-1] = a[largest-1]; a[largest-1] = tmp;
	    /*-*/frame.Highlight(32);
	    /*-*/heap.highlight(largest-1);heap.redraw();
	    /*-*/heap.exchangeArrow(i-1, largest-1);
	    /*-*/heap.setNode(i-1, a[i-1]);
	    /*-*/heap.setNode(largest-1, a[largest-1]);
	    /*-*/heap.restore(i-1); heap.restore(largest-1); heap.redraw();
	    /*-*/frame.setText(2, "Heapifying the exchanged node...");
	    heapify(a, largest);
	    /*-*/frame.Highlight(32);
	}
	/*-*/heap.restore(i-1);heap.redraw();
    }

    public int heapSize(int[] a) {
	return a.length;
    }

    public int parent(int i) {
	return (i/2);
    }

    public int left(int i) {
	return (2*i);
    }

    public int right(int i) {
	return (2*i + 1);
    }
//----
    public void run() {
	heap.initHeap();
	heapsort(a);

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanelColor() {
        //drawingPanel.restoreBinColor();
        frame.repaint();
    }
}
