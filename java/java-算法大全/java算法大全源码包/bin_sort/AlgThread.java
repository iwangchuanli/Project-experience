/* AlgThread.java */

import java.awt.*;
import java.io.*;
import java.util.*;

public class AlgThread extends Thread {
		static int max_data = 10;
		static String[] dataSets = 
								{"Random Data", "Ascending Data", "Descending Data"};

		AlgAnimFrame frame;
		DrawingPanel drawingPanel;
		int delay = 2000;
		int[] a;
		IntList[] bin;

		public AlgThread(AlgAnimFrame frame) {
				this.frame = frame;

				this.drawingPanel = frame.getDrawingPanel();
				if (frame != null && frame.getAlg() != null && 
						frame.getAlg().drawingPanel != null) {
						// drawingPanel already created -> this constructor called from
						// clicking the run button -> use the generated data set
						this.a = frame.getAlg().a;
						this.bin = frame.getAlg().bin;
				}
		}

		public void generateData() {
				int choice = frame.getDataChoice();
				drawingPanel = frame.getDrawingPanel();
				frame.getEnableAnim().disable();
				frame.getDisableAnim().disable();
				frame.setDimension(new Dimension(500, 600));
				if (choice == 0) {
						generateRandomData(max_data);
				} else if (choice == 1) {
						generateAscendingData(max_data);
				} else if (choice == 2) {
						generateDescendingData(max_data);
				}
				// init bins
				bin = new IntList[max_data];
				for (int i = 0; i < max_data; i++)
						bin[i] = new IntList();
				drawingPanel.setData(a, bin);
				if (this.isAlive())
						this.stop();
				}

		public void generateRandomData(int n) {
				a = new int[n];
				for (int i = 0; i < n; i++)
						a[i] = new Double(Math.random()*10).intValue();
		}

		public void generateAscendingData(int n) {
				a = new int[n];
				for (int i = 0; i < n; i++)
						a[i] = i;
		}

		public void generateDescendingData(int n) {
				a = new int[n];
				for (int i = 0; i < n; i++)
						a[i] = 9-i;
		}
		
		public void setDelay(int delay) {
				drawingPanel.setDelay(delay);
		}
		
		class IntList extends Vector {
			public int get(int i) {
				return ((Integer)elementAt(i)).intValue();
			}
		public void put(int i) {
			addElement(new Integer(i));
			}
		}
		
/*------------------------------------------------*/
		public void binsort(int[] a, IntList[] bin) {
				/*-*/ frame.Highlight(0);
				/*-*/ frame.setText(0, "Emptying all bins...");
				// empty all bins
				for (int i = 0; i < bin.length; i++) {
						bin[i] = new IntList();
				} /*-*/ frame.Highlight(2); frame.Highlight(3); frame.Highlight(4); drawingPanel.setData(a, bin);
				/*-*/frame.waitStep();

				/*-*/ frame.setText(0, "Moving balls into bins...");
				// put a[i] into bin[ a[i] ]
				for (int i = 0; i < a.length; i++) {
						/*-*/ frame.Highlight(7);
						bin[ a[i] ].put(a[i]);
						/*-*/ frame.setText(1, "Moving ball with value " + a[i] + " to bin " + a[i] + "...");
						/*-*/ frame.Highlight(8); drawingPanel.move2bin(i, a[i]);
				/*-*/frame.waitStep();
				}

				// retrieve items from bin
				/*-*/ frame.setText(0, "Removing balls from bins...");
				int i = 0;
				for (int j = 0; j < bin.length; j++) {
						/*-*/ frame.Highlight(13);
						for (int k = 0; k < bin[j].size(); k++) {
								/*-*/ frame.Highlight(14);
								a[i++] = bin[j].get(k);
								/*-*/ frame.setText(1, "Extracting ball with value " + a[i-1] + " from bin " + j + "...");
								/*-*/ frame.Highlight(15); drawingPanel.movefrombin(j, i-1);
/*-*/frame.waitStep();
						}
					bin[j] = new IntList();
					}
				/*-*/frame.setText(0, "Execution completed successfully...");
				/*-*/frame.setText(1, "Re-select data and click on the 'Run' button to restart...");
				/*-*/frame.setText(2, "Close Window to quit...");
				} // binsort()

//----
		public void run() {
				drawingPanel.setData(a, bin);
				binsort(a, bin);
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
