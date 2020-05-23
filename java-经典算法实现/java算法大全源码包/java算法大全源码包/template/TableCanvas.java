
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

class TableCanvas extends Panel {
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

    public TableCanvas(TablePanel parent) {
	this.panel = parent;
        this.start = 0;
        this.key = new Vector();
        this.font = new Font("Courier", Font.PLAIN, 12);
        this.fm = this.getFontMetrics(font);
        this.highlight = -1;
    }

    public void init() {
	this.key = new Vector();
    }

    public void setStart(int start) {
	if ((start + 6) < key.size())
            this.start = start;
	else if (key.size() > 6)
	    this.start = key.size() - 7;
	else
	    this.start = 0;
    }

    public void highlightRow(int i) {
        this.highlight = i;
    }

    public void restoreRow() {
        this.highlight = -1;
    }

    public void setTableSize(int size) {
        key = new Vector(size);
        key.setSize(size);
        panel.vScrollbar.setValues(panel.vScrollbar.getValue(),
		1, 0, size-1);
    }

    public void setTableEntry(Object obj, int posn) {
        if (key.size() > posn) {
            key.removeElementAt(posn);
            key.insertElementAt(obj, posn);
        }
    }

    public Object getTableEntryAt(int posn) {
	Object result = null;
	if (key.size() > posn)
	    result = key.elementAt(posn);
	return result;
    }

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

    public int numOccupied() {
	int count = 0;
	for (int i = 0; i < tableSize(); i++) {
	    if (key.elementAt(i) != null)
		count++;
	}
	return count;
    }

    public boolean occupied(int i) {
	if (i >= 0 && i < tableSize())
	    return (key.elementAt(i) != null);
	else
	    return true;
    }

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

    public void addTableEntry(Object obj, int posn) {
        if (key.size() >= posn) {
            key.insertElementAt(obj, posn);
            panel.vScrollbar.setValues(panel.vScrollbar.getValue(),
		1, 0, key.size()-1);
        }
    }

    public int tableSize() {
        return key.size();
    }

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

    public Dimension preferredSize() {
        return new Dimension(480, 320);
    }

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

