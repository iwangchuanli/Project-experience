/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.util.*;

public class AlgThread extends Thread {
    static int max_data = 10;
    static int radix = 10;
    static String[] dataSets = 
                {"2-Digit Dec", "4-Digit Dec", "5-Digit Dec",
		 "2-Digit Oct", "4-Digit Oct",
		 "2-Digit Hex"};

    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    int delay = 600;
    int[] a;
    IntList[] binA, binB;
    int numDigit = 4;

    public AlgThread(AlgAnimFrame frame) {
        this.frame = frame;

        this.drawingPanel = frame.getDrawingPanel();
        if (frame != null && frame.getAlg() != null && 
            frame.getAlg().drawingPanel != null) {
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            this.a = frame.getAlg().a;
            this.binA = frame.getAlg().binA;
	    this.binB = frame.getAlg().binB;
	    this.numDigit = frame.getAlg().numDigit;
	    this.radix = frame.getAlg().radix;
        }
    }

    public void setDelay(int delay) {
	drawingPanel.setDelay(delay);
    }
    
    public void generateData() {
	frame.setDimension(new Dimension(500,600));
        int choice = frame.getDataChoice();
	drawingPanel = frame.getDrawingPanel();
	frame.getEnableAnim().disable();
	frame.getDisableAnim().disable();
	drawingPanel.setHighlight(true);
        if (choice == 0) {
	    radix = 10;
	    numDigit = 2;
        } else if (choice == 1) {
	    radix = 10;
	    numDigit = 4;
        } else if (choice == 2) {
	    radix = 10;
	    numDigit = 5;
        } else if (choice == 3) {
	    radix = 8;
	    numDigit = 2;
	    drawingPanel.setHighlight(false);
	} else if (choice == 4) {
	    radix = 8;
	    numDigit = 4;
	    drawingPanel.setHighlight(false);
	} else if (choice == 5) {
	    radix = 16;
	    numDigit = 2;
	    drawingPanel.setHighlight(false);
	} 
        generateRandomData(max_data);
        // init bins
        binA = new IntList[radix];
	binB = new IntList[radix];
        for (int i = 0; i < radix; i++) {
            binA[i] = new IntList();
	    binB[i] = new IntList();
	}
        drawingPanel.setData(a, binA, binB);
	if (this.isAlive())
	    this.stop();
    }

    public void generateRandomData(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = new Double(Math.random()*power(radix, numDigit)).intValue();
    }
    
    class IntList extends Vector {
	public int get(int i) {
	    return ((Integer)elementAt(i)).intValue();
	}
	public void put(int i) {
	    addElement(new Integer(i));
	}
    }
    
    public int power(int num, int pow) {
	int result = 1;
	for (int i = 0; i < pow; i++)
	    result *= num;
	    
	return result;
    }
    
    public int getDigit(int num, int bit) {
	return ((int)(num / power(radix, bit)) % radix);
    }
    
/*------------------------------------------------*/
    public void binsort(int[] a, IntList[] binA, IntList[] binB) {
	/*-*/frame.Highlight(0); frame.setText(1, "Emptying all bins...");
        // empty all bins
        for (int i = 0; i < binA.length; i++) {
            binA[i] = new IntList();
            binB[i] = new IntList();
        } 
	/*-*/frame.Highlight(2);
	/*-*/frame.Highlight(3);
	/*-*/frame.Highlight(4);
	/*-*/frame.Highlight(5); drawingPanel.setData(a, binA, binB);
/*-*/frame.waitStep();

	/*-*/drawingPanel.highlight(1);
	/*-*/frame.setText(0, "Moving numbers into bins...");
	/*-*/frame.setText(1, "Processing digit 1...");
        // put a[i] into binA[ a[i] ]
        for (int i = 0; i < a.length; i++) {/*-*/frame.Highlight(8);
            int destBinNum = getDigit(a[i], 0);/*-*/frame.Highlight(9);
            binA[ destBinNum ].put(a[i]); 
	    /*-*/frame.Highlight(10); 
	    /*-*/frame.setText(2, "Moving " + a[i] +  " to bin " + destBinNum + "...");
	    /*-*/drawingPanel.move2bin(i, destBinNum);
/*-*/frame.waitStep();
        }/*-*/frame.Highlight(11);
	
	/*-*/drawingPanel.binUp(); if (numDigit>1) drawingPanel.addBins();
        // toggle between bins for the remaining digits
        IntList[] sourceBin = binA, destBin = binB;
        /*-*/frame.Highlight(14);frame.setText(0, "Moving numbers from bins to bins...");
/*-*/frame.waitStep();
        for (int j = 1; j < numDigit; j++) {
	/*-*/frame.Highlight(15); drawingPanel.highlight(j+1);
	/*-*/frame.setText(1, "Processing digit " + (j+1) + "...");
            for (int i = 0; i < sourceBin.length; i++) {
	        /*-*/frame.Highlight(16);
                for (int k = 0; k < sourceBin[i].size(); k++) {
		    /*-*/frame.Highlight(17);
                    // get the j+1 digit of the number
                    int destBinNum = getDigit( sourceBin[i].get(k), j);/*-*/frame.Highlight(19);
                    destBin[ destBinNum ].put( sourceBin[i].get(k) );/*-*/frame.Highlight(20);
		    /*-*/frame.setText(2, "Moving " + sourceBin[i].get(k) + " from bin " + i + " to bin " + destBinNum + "...");
		    /*-*/ drawingPanel.bin2bin(i, destBinNum);
/*-*/frame.waitStep();
                }/*-*/frame.Highlight(21);
                //empty source bin
                sourceBin[i] = new IntList();/*-*/frame.Highlight(23);
            }/*-*/frame.Highlight(24);
	    
            // toggle bins
            IntList[] tmp = sourceBin; sourceBin = destBin; destBin = tmp;
            /*-*/frame.Highlight(27); drawingPanel.binUp(); 
	    /*-*/if (j != numDigit-1) drawingPanel.addBins();
/*-*/frame.waitStep();
        }/*-*/frame.Highlight(28);
/*-*/frame.waitStep();

        IntList[] lastBin = sourceBin;/*-*/frame.Highlight(30);drawingPanel.highlight(-1);
        // retrieve items from last bin
        int i = 0;/*-*/frame.Highlight(32);
	/*-*/ frame.setText(0, "Extracting number from bin.."); frame.setText(2, "");
        for (int j = 0; j < lastBin.length; j++) {/*-*/frame.Highlight(33);
            for (int k = 0; k < lastBin[j].size(); k++) {/*-*/frame.Highlight(34);
                a[i++] = lastBin[j].get(k);/*-*/frame.Highlight(35);
		/*-*/frame.setText(1, "Extracting " + a[i-1] + " from bin " + j + "...");
		/*-*/drawingPanel.movefrombin(j, i-1);
/*-*/frame.waitStep();
            }/*-*/frame.Highlight(36);
            lastBin[j] = new IntList();/*-*/frame.Highlight(37);
        }/*-*/frame.Highlight(38);
	
	/*-*/frame.setText(0, "Execution completed successfully...");
	/*-*/frame.setText(1, "Re-select data and click on the 'Run' button to restart...");
	/*-*/frame.setText(2, "Close Window to quit...");
    } // binsort()
//----
    public void run() {
	drawingPanel.setData(a, binA, binB);
        binsort(a, binA, binB);

        // finish sorting
        frame.finishAlg();
    }

    public void restoreDrawingPanel() {
        drawingPanel.restoreBinColor();
        frame.repaint();
    }

    /**
     * This method is to be placed after the line where the fast
     * forward function is to be performed.
     */
    public void waitSkip() {
        if (!drawingPanel.getSkip()) return;
        ((ImageButton)frame.getSkipItem()).setEnable();
        ((ImageButton)frame.getRunItem()).setEnable();
        ((ImageButton)frame.getStopItem()).setDisable();
        drawingPanel.setSkip(false);
        frame.setStep(true);
        frame.waitStep();
    }
}
