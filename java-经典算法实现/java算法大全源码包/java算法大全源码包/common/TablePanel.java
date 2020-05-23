/* TablePanel.java */

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * This class creates a panel with a table canvas and a vertical scrollbar
 * to the east of the canvas.
 * Most of its methods are simply directing the method calls to the table
 * canvas (instance of class <code>TableCanvas</code>) with methods of the
 * same name (polymorphism).
 * <p>
 * Since this class is of type <code>java.awt.Panel</code> and the drawing
 * panel is also of the same type, the layout manager of the drawing panel
 * (<code>DrawingPanel</code>) has to be disabled prior to adding this
 * table panel.
 * <p>
 * The way of adding the table panel to a drawing panel is similar to adding
 * a histogram (they are of similar nature - both inheriting from
 * <code>java.awt.Panel</code>).
 * <p>
 * The following line of codes can be used as an example to add a table panel
 * to a drawing panel: <code><pre>
 *	TablePanel table = new TablePanel(max_table_size);
 *	drawingPanel.setLayout(null);
 *	drawingPanel.add(table);
 *	table.setDrawingPanel(drawingPanel);
 *	table.reshape(x, y, width, height);
 * </pre></code>
 * <code>drawingPanel</code> is an instance of class <code>DrawingPanel</code>
 * which is normally obtained by calling the <code>getDrawingPanel()</code> 
 * method from an instance of the <code>AlgAnimFrame</code> class: <code><pre>
 * 	drawingPanel = frame.getDrawingPanel();
 * </pre></code>
 * where <code>frame</code> is the instance of the <code>AlgAnimFrame</code>
 * class.
 * @see TableCanvas
 * @see Histogram
 */
public class TablePanel extends Panel implements DrawingObj {
    Scrollbar vScrollbar;
    TableCanvas draw;
    DrawingPanel drawingPanel;

    /**
     * Create a panel with a table canvas and a scrollbar to the east of the
     * table canvas. The maximum size of the table is initialized according
     * to the parameter of this constructor.
     * @param max_elem The maximum size of the table.
     */
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

    /**
     * Set the size of the table and rescale the scrollbar governing the table.
     * This method calls the method with the same name in
     * <code>TableCanvas</code>. 
     * @param max_size The maximum size of the table.
     */
    public void init(int max_size) {
	draw.setTableSize(max_size);
    }

    /**
     * Simply call the <code>repaint()</code> method of the table panel.
     */
    public void draw(Graphics g) {
	repaint();
    }

    /**
     * Get the left most position of the table panel.
     * @return The left most position of the table panel.
     */
    public int getX() {
	return location().x;
    }

    /**
     * Get the top most position of the table panel.
     * @return The top most position of the table panel.
     */
    public int getY() {
	return location().y;
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which checks if the table has been fully filled up.
     * @return TRUE if all entries in the corresponding table canvas
     * is non-null object; FALSE otherwise.
     * @see TableCanvas#full
     */
    public boolean full() {
	return draw.full();
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which gets the number of rows in the table where the contents are
     * non-null objects.
     * @return The number of non-null object in the table.
     * @see TableCanvas#numOccupied
     */
    public int numOccupied() {
	return draw.numOccupied();
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which checks if a particular row in the table has a non-null object.
     * @param i The row number of the table to be checked.
     * @return TRUE if the row specified by the parameter is occupied.
     * @see TableCanvas#occupied
     */
    public boolean occupied(int i) {
	return draw.occupied(i);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>.
     * Display a status summary of the table regarding the percentage
     * of the table being occupied, the number of clusters in the table,
     * and a rough idea of the location of the table being occupied.
     * <p>
     * This method should be called from the <code>draw</code> method
     * of one of the drawing object, which has been added to the drawing
     * panel. For example, if an object class <code>Eg</code> has been
     * defined to
     * implement the <code>DrawingObj</code> interface, such that: <code><pre>
     *  class Eg implements DrawingObj {
     *      TablePanel table;
     *      ....
     *
     *      public Eg(TablePanel table, ... ) {
     *          ....
     *          this.table = table;
     *      }
     *
     *      ....
     *
     *      public void draw(Graphics g) {
     *          ....
     *          if (table != null)
     *              table.drawClusters(g, x, y);
     *      }
     *  }
     * </pre></code>
     * Adding an instance of this <code>Eg</code> class into the drawing
     * panel (by using <code>addDrawingObj</code>) should result in
     * drawing of the table status summary in the drawing panel with its
     * top left corner at position <code>(x, y)</code>.
     * @param g Graphical context of the drawing panel where the status
     * information is going to be shown.
     * @param x The left most position of the status information display.
     * @param y The top most position of the status information display.
     * @see TableCanvas@drawClusters
     */
    public void drawClusters(Graphics g, int x, int y) {
	draw.drawClusters(g, x, y);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which checks if the table contains a certain object specified by
     * the parameter.
     * @param obj The object that is going to be checked for existence.
     * @return TRUE if the object exists; FALSE otherwise.
     * @see TableCanvas#contains
     */
    public boolean contains(Object obj) {
	return draw.contains(obj);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which gets the location of an object in the table.
     * @param obj The object which location is being checked.
     * @return The row number of the object passed in as the parameter.
     * This method will return a -1 if the object does not exist.
     * @see TableCanvas#indexOf
     */
    public int indexOf(Object obj) {
	return draw.indexOf(obj);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which add an entry to the table and push the existing entries after
     * this newly added object one position down.
     * @param obj The new object to be added to the table.
     * @param posn The position in the table (row) where the new object is to
     * be added.
     * @see TableCanvas#addTableEntry
     */
    public void addTableEntry(Object obj, int posn) {
	draw.addTableEntry(obj, posn);
    }

    /**
     * Scroll the display window of the table to shown the row specified by
     * the parameter. If the parameter indicates a row that has already
     * been in the current view window, nothing will happen.
     * @param posn The row number of the table that will be displayed.
     */
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

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which obtains the object placed at the table row specified by the
     * parameter.
     * @param posn Table row where the object is to be obtained.
     * @return The table object at the row specified by the parameter.
     * @see TableCanvas#getTableEntryAt
     */
    public Object getTableEntryAt(int posn) {
	return draw.getTableEntryAt(posn);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which sets the object specified by the first parameter to the table
     * row defined by the second parameter. The display window will scroll
     * to include the row that has been newly added.
     * @param obj The object to be added to the table.
     * @param posn The row number where the object is going to be added.
     * @see TableCanvas#setTableEntry
     */
    public void setTableEntry(Object obj, int posn) {
	draw.setTableEntry(obj, posn);
	scroll2posn(posn);
	draw.repaint(); delay();
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which sets the size of the table and hence rescale the scrollbar.
     * @param size The new size of this table.
     * @see TableCanvas#setTableSize
     */
    public void setTableSize(int i) {
	draw.setTableSize(i);
	draw.repaint();
	drawingPanel.init();
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which gets the maximum size of the table.
     * @return The maximum size of the table.
     * @see TableCanvas#tableSize
     */
    public int tableSize() {
	return draw.tableSize();
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which highlights the table entry with the row specified by the
     * parameter. There can only be one row highlighted at any one time.
     * @param i The row to be highlighted.
     * @see TableCanvas#highlightRow
     */
    public void highlightRow(int i) {
	draw.highlightRow(i);
    }

    /**
     * Pass the method call to the corresponding <code>TableCanvas</code>,
     * which restores any highlighted row.
     * @see TableCanvas#restoreRow
     */
    public void restoreRow() {
	draw.restoreRow();
    }

    /**
     * Set the parent panel where this table panel is going to reside.
     * @param dpanel The parent drawing panel which this table panel resides.
     */
    public void setDrawingPanel(DrawingPanel dpanel) {
	this.drawingPanel = dpanel;
    }

    /**
     * The main purpose of this method is to handle the scrollbar event.
     * The position of the scrollbar is obtained and used to position the
     * view window of the table canvas.
     * @param event The event captured.
     */
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

    /**
     * This method causes a delay of 200msec.
     */
    public void delay() {
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {}
    }
}
