/* AlgThread.java */

import java.awt.*;
import java.io.*;

public class AlgThread extends Thread {
    static int max_data = 10;
    static String[] dataSets = 
                {"Random Data", "Ascending Data", "Descending Data"};

    AlgAnimFrame frame;
    static StickPanel drawingPanel;
    int delay = 2000;
    int[] a;

    int swapCount=0;

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        if (frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
	    this.a = frame.alg.a;
        } else {
System.out.println("frame " + frame);
            // this constructor called from Frame constructor
            drawingPanel = new StickPanel(frame);
            frame.drawingPanel = (Panel)this.drawingPanel;
		//frame.addStatsLabel("Source stats", "pivot");
	// add stats labels
	frame.addStatsLabel("Source stats", "pivot");	
	frame.addStatsLabel("Source stats", "low");
	frame.addStatsLabel("Source stats", "high");
	frame.addStatsLabel("General stats", "swap count");
        }

    }

    public void generateData() {
        int choice = frame.control_panel.getDataChoice();
        if (choice == 0) {
            generateRandomData(max_data);
        } else if (choice == 1) {
            generateAscendingData(max_data);
        } else if (choice == 2) {
            generateDescendingData(max_data);
        }
        drawingPanel.setMax(a.length);
        drawingPanel.setSticks(a, a.length);
        drawingPanel.repaint();
    }

    public void generateRandomData(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = new Double(Math.random()*30).intValue() + 1;
    }

    public void generateAscendingData(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (i+1)*3;
    }

    public void generateDescendingData(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (10-i+1) * 3;
    }
/*------------------------------------------------*/
    public void swap( int[] a, int i, int j ) {
        	int tmp = a[i]; a[i] = a[j]; a[j] = tmp; 
		/*-*/ //int swapCount;
		/*-*/ swapCount++;
        	/*-*/ frame.Highlight( 1 );
        	/*-*/ drawingPanel.swapSticks(i, j);
		/*-*/ frame.setText(4, " ... swapping stick " + i + " with stick " + j);frame.setText(5, "");
	  	/*-*/ System.out.println("SWAP: a["+i+"] is " + a[i] + " and a["+j+"] is " + a[j]);
		/*-*/ frame.updateStatsValue("General stats", "swap count", swapCount);
    }

    public int partition( int[] a, int low, int high ) {
        	int pivot, p_pos, tmp, i, j;
       	p_pos = low;  /*-*/ frame.Highlight( 6 );
        	/*-*/ drawingPanel.setPartition(low, high); drawingPanel.setPos(p_pos);
        	/*-*/ frame.setText(1, "Partition from " + low + " to " + high );
		/*-*/ frame.setText(2, "Assign low to p_pos");frame.setText(3, "");
		/*-*/ frame.setText(4, "");frame.setText(5, "");
		/*-*/ System.out.println("PARTITION low is " + low + "high is " + high);
		/*-*/ System.out.println("PARTITION p_pos=low is " + p_pos);
		/*-*/ frame.waitStep();
        	pivot = a[p_pos]; /*-*/  frame.Highlight( 7 ); 
		/*-*/ drawingPanel.setPivot(p_pos);
		/*-*/ frame.setText(1, "Assign pivot to value pointed at position p_pos");
		/*-*/ frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
        	/*-*/ //frame.setText(5, "Pivot " + pivot ); 
		/*-*/ frame.updateStatsValue("Source stats", "pivot", pivot);
     		/*-*/ frame.updateStatsValue("Source stats", "low", low);
		/*-*/ frame.updateStatsValue("Source stats", "high", high);
		/*-*/ System.out.println("PARTITION pivot=a[p_pos] is " + pivot);
		/*-*/frame.waitStep();
        for(i=low+1;i<=high;i++) { /*-*/ frame.Highlight( 8 );
            /*-*/ drawingPanel.setCurrentLocation(i);
		/*-*/ frame.setText(1, "Examine all values from position at low+1 to high");
		/*-*/ frame.setText(2, "and move sticks with values less than that of pivot");
		/*-*/ frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
		/*-*/ frame.waitStep();
            if ( a[i] < pivot ) { /*-*/ frame.Highlight( 9 );
		/*-*/ frame.setText(2, "Since stick "+i+" has value less than that of pivot,");
		/*-*/ frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");	
		/*-*/ frame.waitStep();
                	p_pos++; /*-*/ frame.Highlight( 10 ); drawingPanel.setPos(p_pos);
			/*-*/ frame.setText(3, "increment p_pos");
			/*-*/ frame.setText(4, "swap p_pos with " + i);frame.setText(5, "");
			/*-*/ frame.waitStep();
                	swap( a, p_pos, i ); /*-*/ frame.Highlight( 11 );/*-*/ System.out.println("IFIFIF");
			/*-*/ frame.waitStep();
            }
        }	/*-*/ System.out.println("PARTITION low: " + low + "p_pos: " + p_pos);
	  /*-*/ //frame.setText(0, "Now,"); 
	  /*-*/ //frame.setText(1, "for values greater than that of pivot,");	
	  /*-*/ //frame.setText(2, "swap it with low");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
        swap( a, low, p_pos ); /*-*/ frame.Highlight( 14 );/*-*/ System.out.println("ELSEELSE");/*-*/ System.out.println("PARTITION low: " + low + "p_pos: " + p_pos);
        /*-*/ frame.waitStep();
	  return p_pos; 
    }

    public void quicksort( int[] a, int low, int high ) {
        int pivot;
	         if ( low < high ) { /*-*/ frame.Highlight( 20 );
			
			/*-*/ //add commentary
		  	/*-*/ frame.setText(0, "Since low is less than high ..."); frame.setText(1, "");
		 	/*-*/ frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
                	/*-*/ frame.waitStep(); 
			pivot = partition( a, low, high ); 
			/*-*/ frame.Highlight( 21 );
                	/*-*/ drawingPanel.setPivot(pivot);
       		/*-*/ frame.setText(0, "Getting pivot ..."); frame.setText(1, "");
			/*-*/ frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
                	/*-*/ frame.waitStep(); 
			quicksort( a, low, pivot-1 ); 
			/*-*/ frame.Highlight( 22 );
			/*-*/ frame.setText(0, "Quicksort the left partition..."); frame.setText(1, "");
			/*-*/ frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
                	/*-*/ frame.waitStep();
                	quicksort( a, pivot+1, high ); 
			/*-*/ frame.Highlight( 23 );
			/*-*/ frame.setText(0, "Quicksort the right partition..."); frame.setText(1, "");
			/*-*/ frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
                	/*-*/ frame.waitStep();

        }
    }
//----
    public void run() {
	
        int low = 0;
        int high = a.length - 1;
       
	//frame.waitStep();

	// add commentary
	frame.setText(0, "The main idea behind QuickSort is ...");
	frame.setText(1, " 1. Choose a data element in the array as the pivot");
	frame.setText(2, " 2. Use the pivot to separate the data array into 2 partitions");
	frame.setText(3, "    so that the left partition contains values less than the pivot");
	frame.setText(4, "    and the right partition contains values greater than or equal to the pivot");
	frame.setText(5, " 3. Apply QuickSort() recursively to sort the left and right partitions");
	frame.waitStep();
	frame.setText(0, "First assign low to the first element of the data array");
	frame.setText(1, "...and assign high to the last element of the data array");
	frame.setText(2, "");frame.setText(3, "");frame.setText(4, "");frame.setText(5, "");
	


        quicksort(a, low, high);

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanelColor() {
        drawingPanel.restoreStickColor();
    }
}
