
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * This class creates a panel to display a table. It is only called from
 * the <code>TablePanel</code> class which adds a scrollbar to the east of the 
 * table canvas.
 * <p>
 * This <code>TableCanvas</code> class should not have to be used manually.
 * All its methods are redirected from the <code>TablePanel</code> class.
 */
public class TableCanvas extends Panel {
    int start;
    Vector key;
    Font font;

    int panel_height, panel_width;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

 
    TablePanel panel;
    final int vertSpace = 17;

    int highlight;

    /**
     * Constructor method of the <code>TableCanvas</code> class.
     * @param parent The panel with a scrollbar where this table canvas
     * is going to reside.
     */
    public TableCanvas(TablePanel parent) {
	this.panel = parent;
        this.start = 0;
        this.key = new Vector();
        this.font = new Font("Courier", Font.PLAIN, 12);
        this.fm = this.getFontMetrics(font);
        this.highlight = -1;
    }

    /**
     * Initialize the table and set all entries to null.
     */
    public void init() {
	this.key = new Vector();
    }

    /**
     * Set the starting row, which will be displayed by the table.
     * @param start The first row to be shown at any one time.
     */
    public void setStart(int start) {
	if ((start + 6) < key.size())
            this.start = start;
	else if (key.size() > 6)
	    this.start = key.size() - 7;
	else
	    this.start = 0;
    }

    /**
     * Highlight the table entry with the row specified by the parameter.
     * There can only be one row highlighted at any one time. If a row
     * has already been highlighted and this <code>highlightRow</code>
     * method is called on another different row, the previously highlighted
     * row will be restored and the new row will be highlighted.
     * @param i The row to be highlighted.
     */
    public void highlightRow(int i) {
        this.highlight = i;
    }

    /**
     * This method is used to restore any highlighted row.
     */
    public void restoreRow() {
        this.highlight = -1;
    }

    /**
     * This method is called to set the size of the table and hence
     * rescale the scrollbar on the panel it resides in.
     * @param size The new size of this table.
     */
    public void setTableSize(int size) {
        key = new Vector(size);
        key.setSize(size);
        panel.vScrollbar.setValues(panel.vScrollbar.getValue(),
		1, 0, size-1);
    }

    /**
     * Set the object specified by the first parameter to the table row
     * defined by the second parameter.
     * @param obj The object to be added to the table.
     * @param posn The row number where the object is going to be added.
     */
    public void setTableEntry(Object obj, int posn) {
        if (key.size() > posn) {
            key.removeElementAt(posn);
            key.insertElementAt(obj, posn);
        }
    }

    /**
     * Obtain the object placed at the table row specified by the parameter.
     * @param posn Table row where the object is to be obtained.
     * @return The table object at the row specified by the parameter.
     */
    public Object getTableEntryAt(int posn) {
	Object result = null;
	if (key.size() > posn)
	    result = key.elementAt(posn);
	return result;
    }

    /**
     * Check if the table has been fully filled up.
     * @return TRUE if all entries in the table is non-null object;
     * FALSE otherwise.
     */
    public boolean full() {
	boolean f = true;
	for (int i = 0; i < tableSize(); i++) {
	    if (key.elementAt(i) == null) {
		f = false;
		break;
	    }
	}
	return f;
    }

    /**
     * Get the number of rows in the table where the contents are non-null
     * object.
     * @return The number of non-null object in the table.
     */
    public int numOccupied() {
	int count = 0;
	for (int i = 0; i < tableSize(); i++) {
	    if (key.elementAt(i) != null)
		count++;
	}
	return count;
    }

    /**
     * Check if a particular row in the table has a non-null object.
     * @param i The row number of the table to be checked.
     * @return TRUE if the row specified by the parameter is occupied.
     */
    public boolean occupied(int i) {
	if (i >= 0 && i < tableSize())
	    return (key.elementAt(i) != null);
	else
	    return true;
    }

    /**
     * Display a status summary of the table regarding the percentage
     * of the table being occupied, the number of clusters in the table,
     * and a rough idea of the location of the table being occupied.
     * @param g Graphical context of the drawing panel where the status
     * information is going to be shown.
     * @param x The left most position of the status information display.
     * @param y The top most position of the status information display.
     */
    public void drawClusters(Graphics g, int x, int y) {
	g.setColor(Color.lightGray);
	g.drawRect(x, y, tableSize()*3 + 2, 21);
	g.setColor(Color.red);
	g.setFont(font);
	int numOccupied = 0;
	int numClusters = 0;
	for (int i = 0; i < tableSize(); i++) {
	    if (key.elementAt(i) != null) {
		g.fillRect(x+1+i*3, y+1, 3, 20);
		numOccupied++;
		if (i==0)
		    numClusters++;
		else if (key.elementAt(i-1) == null)
		    numClusters++;
	    }
	    if ( (i%25) == 0 ) {
		g.setColor(Color.black);
		g.drawString("" + i, x+i*3, y - 2);
		g.setColor(Color.red);
	    }
	}
	g.setColor(Color.black);
	g.drawString("Percent", x - 50, y + 30);
	g.drawString("full", x - 40, y + 42);
	g.drawString(""+ (numOccupied*100/tableSize()) + "%",
		x - 30, y + 16);
	g.drawString(""+ numClusters,
		x + tableSize()*3 + 10, y + 16);
	g.drawString("Clusters",
		x + tableSize()*3 + 4, y + 30);

	g.drawString("Address", x + tableSize()*3/2 - 20, y - 15);
	g.drawString("Keys Placed in Table", x + tableSize()*3/2 - 70, y + 35);
    }

    /**
     * Add an entry to the table and push the existing entries after this
     * newly added object one position down.
     * @param obj The new object to be added to the table.
     * @param posn The position in the table (row) where the new object is to
     * be added.
     */
    public void addTableEntry(Object obj, int posn) {
        if (key.size() >= posn) {
            key.insertElementAt(obj, posn);
            panel.vScrollbar.setValues(panel.vScrollbar.getValue(),
		1, 0, key.size()-1);
        }
    }

    /**
     * Get the maximum size of the table (including all null and non-null
     * objects).
     * @return The maximum size of the table.
     */
    public int tableSize() {
        return key.size();
    }

    /**
     * Check if the table contains a certain object specified by the parameter.
     * @param obj The object which is going to be checked for existence.
     * @return TRUE if the object exists; FALSE otherwise.
     */
    public boolean contains(Object obj) {
	boolean contain = false;
	for (int i = 0; i < tableSize(); i++) {
	    if (obj == null || key.elementAt(i) == null)
		continue;
	    if (obj.equals(key.elementAt(i))) {
		contain = true;
		break;
	    }
	}
	return contain;
    }

    /**
     * Get the location of an object in the table.
     * @param obj The object which location is being checked.
     * @return The row number of the object passed in as the parameter. This
     * method will return -1 if the object does not exist within the current
     * table.
     */
    public int indexOf(Object obj) {
	int index = -1;
	for (int i = 0; i < tableSize(); i++) {
	    if (obj == null || key.elementAt(i) == null)
		continue;
	    if (obj.equals(key.elementAt(i))) {
		index = i;
		break;
	    }
	}
	return index;
    }

    /**
     * Define the default size of the table.
     * @return The dimension of the table. By default is: 480x320.
     */
    public Dimension preferredSize() {
        return new Dimension(480, 320);
    }

    /**
     * This method is invoked by the display manager when the
     * <code>repaint()</code> method of the panel is called. This method
     * is overridden here to eliminate the flashing characteristic during
     * the panel update.
     */
    public void update(Graphics g) {
        Dimension d = size();
        if (d.width < 1 || d.height < 1)
            return;

        if ((offscreen == null) || (d.width != offscreensize.width) ||
                    (d.height != offscreensize.height)) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            offGraphics = offscreen.getGraphics();
        }

        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setFont(font);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    /**
     * Method to draw the table canvas.
     */
    public void paint(Graphics g) {
        panel_height = size().height;
        panel_width = size().width;

        int row = 1;
        for (int i = start; i < key.size(); i++) {
            drawBox(g, 0, row, ""+i,
                    Color.black, Color.lightGray, 3);

            String content = (String)key.elementAt(i);
            if (content == null) {
                drawBox(g, 26, row, content,
                    Color.black, Color.pink, 28);
            } else if (highlight == i)
                drawBox(g, 26, row, content,
                    Color.white, Color.blue, 28);
            else
                drawBox(g, 26, row, content,
                    Color.blue, Color.white, 28);
            row += vertSpace + 2;;
        }
    } // paint();
 
    /**
     * This method is only called by the <code>paint()</code> method to draw
     * each entry of the table as a box.
     * @param g Graphical context.
     * @param x The left most position of the box.
     * @param y The top most position of the box.
     * @param str The String object to be drawn inside the box.
     * @param fg Foreground color (i.e. the text color) of the box.
     * @param bg Background color of the box.
     * @param max_length The max_length of the string to be displayed in
     * the box.
     */
    public void drawBox(Graphics g, int x, int y, String str,
                            Color fg, Color bg, int max_length) {
        int horizSpace = max_length * 8;
        g.setColor(bg);
        g.fillRect(x, y, horizSpace, vertSpace);
        g.setColor(Color.black);
        g.drawRect(x, y, horizSpace, vertSpace);
        g.setColor(fg);
        g.setFont(font);
        if (str != null)
            g.drawString(str, x + 2, y + vertSpace - 4);
    }
} // TableCanvas

