/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {
    int capacity = 7;
    static String[] dataSets = {"Queue Capacity 7", "Queue Capacity 15", 
				"Queue Capacity 31"};

    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    Node tree;
    Heap heap;

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.drawingPanel = frame.alg.drawingPanel;
	    this.heap = frame.alg.heap;
	    this.capacity = frame.alg.capacity;
        } else {
            // this constructor called from Frame constructor
            drawingPanel = new DrawingPanel();
            frame.drawingPanel = (Panel)this.drawingPanel;
	    /*-*/heap = new Heap(drawingPanel, capacity); 
	    /*-*/drawingPanel.drawHeap(heap);
        }
    }

    public void setDelay(int delay) {
	drawingPanel.setDelay(delay);
    }

    public void generateData() {
        int choice = frame.control_panel.getDataChoice();
        if (choice == 0) {
	    capacity = 7;
	    /*-*/heap = new Heap(drawingPanel, capacity); 
	    /*-*/drawingPanel.drawHeap(heap);
        } else if (choice == 1) {
	    capacity = 15;
	    /*-*/heap = new Heap(drawingPanel, capacity); 
	    /*-*/drawingPanel.drawHeap(heap);
	} else if (choice == 2) {
	    capacity = 31;
	    /*-*/heap = new Heap(drawingPanel, capacity); 
	    /*-*/drawingPanel.drawHeap(heap);
	}
	/*-*/heap.initHeap();
    }

    public int generateRandomData() {
        return (new Double(Math.random()*99).intValue() + 1);
    }

    public boolean isInput() {
	return (generateRandomData() < 70);
    }
 
    public int[] setHeapWithSize(int[] a, int i) {
	int[] old = a;
	a = new int[i];
	for (int j = 0; j < i; j++)
	    a[j] = old[j];
	return a;
    }
/*--------------------------------------------------------------------*/
    public void animatePriorityQueue() { /*-*/int line_num = 0;
	/*-*/frame.Highlight(line_num);
	/*-*/frame.setText(0, "Executing priority Q animation demo..");
	/*-*/frame.setText(0, "This priority Q repeatedly receives random inputs and extracts outputs...");
	int[] Q = new int[0];
	/*-*/frame.Highlight(line_num + 1);
	while (true) {
	    /*-*/frame.Highlight(line_num + 2);
	    /*-*/frame.Highlight(line_num + 3);
	    if (isInput() && Q.length < capacity) {
	    	/*-*/frame.Highlight(line_num + 4);
		/*-*/frame.setText(1, "Inserting into the queue...");
	    	int input = generateRandomData(); 
	    	/*-*/heap.addInput(input); frame.Highlight(line_num + 5);
		Q = addHeap(Q, input);
	    	/*-*/frame.Highlight(line_num + 5);
	    } else if (Q.length > 0) {
	    	/*-*/frame.Highlight(line_num + 6);
		/*-*/frame.setText(1, "Extracting item from queue...");
	    	/*-*/frame.Highlight(line_num + 7);
		Q = delHeap(Q);
	    	/*-*/frame.Highlight(line_num + 8);
	    }
	    /*-*/frame.Highlight(line_num + 8);
	    /*-*/frame.Highlight(line_num + 9); frame.waitStep();
	}
    }

    public int[] addHeap(int[] a, int i) { /*-*/int line_num = 12;
	/*-*/frame.Highlight(line_num);
	/*-*/frame.setText(1, "Addition to the heap...");
	int[] result = new int[a.length + 1];
	/*-*/frame.Highlight(line_num + 1);
	for (int k = 0; k < a.length; k++)
	    result[k] = a[k];
	result[a.length] = i;
	/*-*/frame.Highlight(line_num + 2);
	/*-*/frame.Highlight(line_num + 3);
	/*-*/frame.Highlight(line_num + 4); heap.input2heap();
	/*-*/frame.Highlight(line_num + 5);
	up(result, result.length);
	/*-*/frame.Highlight(line_num + 5);

	/*-*/frame.Highlight(line_num + 6);
	return result;
    }

    public void up(int[] a, int i) { /*-*/int line_num = 21;
	/*-*/frame.Highlight(line_num); heap.highlight(i-1); heap.redraw();
	/*-*/frame.Highlight(line_num + 1);
	/*-*/frame.setText(2, "Moving UP...");
	if (parent(i) > 0) {
	    int p = parent(i);
	    /*-*/frame.Highlight(line_num + 2);
	    /*-*/heap.highlight(p-1); heap.redraw();
	    /*-*/frame.Highlight(line_num + 3);
	    if (a[i-1] > a[p-1]) {
		// swap with parent and up parent
	/*-*/frame.setText(2, "Exchange with parent...");
		int tmp = a[i-1]; a[i-1] = a[p-1]; a[p-1] = tmp;
	    	/*-*/frame.Highlight(line_num + 5); 
		/*-*/heap.exchangeArrow(p-1,i-1);
		/*-*/heap.setNode(i-1, a[i-1]);
		/*-*/heap.setNode(p-1, a[p-1]);
		/*-*/heap.restore(i-1); heap.restore(p-1); heap.redraw();
	    	/*-*/frame.Highlight(line_num + 6);
		up(a, p);
	    	/*-*/frame.Highlight(line_num + 6);
	    }
	    /*-*/frame.Highlight(line_num + 7);
	    /*-*/heap.restore(p-1);
	}
	/*-*/frame.Highlight(line_num + 8);
	/*-*/heap.restore(i-1);
    }

    public int[] delHeap(int[] a) { /*-*/int line_num = 32;
	/*-*/frame.Highlight(line_num);heap.addOutput(a[0]);
	/*-*/frame.setText(1, "Delete from heap...");
	a[0] = a[a.length-1];
	/*-*/frame.Highlight(line_num+1);
	int[] result = setHeapWithSize(a, a.length-1);
	/*-*/frame.Highlight(line_num+2);
	/*-*/if (result.length>0) heap.moveLast2First();
	/*-*/heap.setHeap(result);
	/*-*/frame.Highlight(line_num+3);
	if (result.length > 0) heapify(result, 1);
	/*-*/frame.Highlight(line_num+3);
	/*-*/frame.Highlight(line_num+4);
	return result;
    }

    public void heapify(int[] a, int i) { /*-*/int line_num = 39;
	/*-*/frame.setText(1, "Heapifying ...");
	/*-*/frame.Highlight(line_num);heap.highlight(i-1);heap.redraw();
	int l = left(i);/*-*/frame.Highlight(line_num+1);
	int r = right(i);/*-*/frame.Highlight(line_num+2);
	/*-*/frame.setText(1, "Left node = " + l + "; Right node = " + r);
	int largest;
	/*-*/frame.Highlight(line_num+4);heap.highlight(l-1);heap.redraw();
	if ( l <= heapSize(a) && a[l-1] > a[i-1] ) {
	    largest = l;
	    /*-*/frame.Highlight(line_num+5);
	    /*-*/frame.setText(2, "Left node is the largest...");
	} else {
	    /*-*/frame.Highlight(line_num+6);
	    largest = i;
	    /*-*/frame.setText(2, "Current node is the largest...");
	    /*-*/frame.Highlight(line_num+7);
	    /*-*/frame.Highlight(line_num+8);
	}
	/*-*/heap.restore(l-1);
	/*-*/frame.Highlight(line_num+9);heap.highlight(r-1);heap.redraw();
	if ( r <= heapSize(a) && a[r-1] > a[largest-1] ) {
	    /*-*/frame.Highlight(line_num+10);
	    largest = r;
	    /*-*/frame.setText(2, "Right node is the largest now...");
	}
	/*-*/heap.restore(r-1);heap.redraw();
	/*-*/frame.Highlight(line_num+12);
	if (largest != i) {
	    // exchange a[i], a[largest]
	    /*-*/frame.setText(1, "Current node is not the largest...");
	/*-*/frame.setText(2, "Exchanging current node with the largest...");
	    /*-*/frame.Highlight(line_num + 14);
	    int tmp = a[i-1]; a[i-1] = a[largest-1]; a[largest-1] = tmp;
	    /*-*/frame.Highlight(line_num+15);
	    /*-*/heap.highlight(largest-1);heap.redraw();
	    /*-*/heap.exchangeArrow(i-1, largest-1);
	    /*-*/heap.setNode(i-1, a[i-1]);
	    /*-*/heap.setNode(largest-1, a[largest-1]);
	    /*-*/heap.restore(i-1); heap.restore(largest-1); heap.redraw();
	    /*-*/frame.setText(2, "Heapifying the exchanged node...");
	    heapify(a, largest);
	    /*-*/frame.Highlight(line_num+15);
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
	animatePriorityQueue();

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanelColor() {
        //drawingPanel.restoreBinColor();
        frame.repaint();
    }
}
