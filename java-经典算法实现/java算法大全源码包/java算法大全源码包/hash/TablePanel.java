/* TablePanel.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * Commentary panel, which only consists of a few rows of text fields.
 * The number of text fields are set during the class construction.
 */
public class TablePanel extends Panel {
    Scrollbar vScrollbar;
    TableCanvas draw;
    DrawingPanel drawingPanel;

    public TablePanel(int max_elem) {
	setLayout(new BorderLayout());
        int i;
        this.setBackground(Color.white);
	
	draw = new TableCanvas(this);

	vScrollbar = new Scrollbar(Scrollbar.VERTICAL);
	vScrollbar.setValues(0, 1, 0, 0);
	vScrollbar.setPageIncrement(1);
	add("Center", draw);
	add("East", vScrollbar);

	draw.setTableSize(max_elem);
    }

    public void init(int max_size) {
	draw.setTableSize(max_size);
    }

    public boolean full() {
	return draw.full();
    }

    public int numOccupied() {
	return draw.numOccupied();
    }

    public boolean occupied(int i) {
	return draw.occupied(i);
    }

    public void drawClusters(Graphics g, int x, int y) {
	draw.drawClusters(g, x, y);
    }

    public boolean contains(Object obj) {
	return draw.contains(obj);
    }

    public int indexOf(Object obj) {
	return draw.indexOf(obj);
    }

    public void addTableEntry(Object obj, int posn) {
	draw.addTableEntry(obj, posn);
    }

    public void scroll2posn(int posn) {
        int val = vScrollbar.getValue();
	if (posn < val || posn > val + 6) {
	    val = posn - 2;
	    if (val < 0)
		val = 0;
	    else if (val > vScrollbar.getMaximum())
		val = vScrollbar.getMaximum() - 4;
	    vScrollbar.setValue(val);
            draw.setStart(val);
            draw.repaint();
	    delay();
	}
    }

    public Object getTableEntryAt(int posn) {
	return draw.getTableEntryAt(posn);
    }

    public void setTableEntry(Object obj, int posn) {
	draw.setTableEntry(obj, posn);
	scroll2posn(posn);
	draw.repaint(); delay();
    }

    public void setTableSize(int i) {
	draw.setTableSize(i);
	draw.repaint();
	drawingPanel.init();
    }

    public int tableSize() {
	return draw.tableSize();
    }

    public void highlightRow(int i) {
	draw.highlightRow(i);
    }

    public void restoreRow() {
	draw.restoreRow();
    }

    public void setDrawingPanel(DrawingPanel dpanel) {
	this.drawingPanel = dpanel;
    }

    public boolean handleEvent(Event event) {
	if (event.target == vScrollbar) {
	    switch (event.id) {
		case Event.SCROLL_LINE_UP:
                case Event.SCROLL_LINE_DOWN:
                case Event.SCROLL_PAGE_UP:
                case Event.SCROLL_PAGE_DOWN:
                case Event.SCROLL_ABSOLUTE:
                    int val = ((Scrollbar)(event.target)).getValue();
                    draw.setStart(val);
                    draw.repaint();
	    }
	}
	return super.handleEvent(event);
    }

    public void delay() {
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {}
    }
}
