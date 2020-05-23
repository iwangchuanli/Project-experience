/* IntMatrix.java */

import java.io.*;
import java.awt.*;

class IntMatrix implements DrawingObj {
    int rows, columns;
    int[][] elems;
    /* Drawing characteristics */
    int cell_size;
    Font font = new Font("Courier", Font.PLAIN, 12);
    //Font boxFont = new Font("Courier", Font.PLAIN, 12);
    String[] rowLabels, colLabels;
    String title = null;
    boolean[][] highlight;
    boolean[][] highlight2;
    static final int horizSpace = 32;
    static final int vertSpace = 17;

    private Color fg, bg;
    private int x, y;
    
    public IntMatrix( int rows, int columns ) {
	int j, k;
	this.rows = rows;
	this.columns = columns;
	elems = new int[rows][columns];
	highlight = new boolean[rows][columns];
	highlight2 = new boolean[rows][columns];
	for(j=0; j<rows; j++) 
	    for(k=0; k<columns; k++) {
		elems[j][k] = Integer.MAX_VALUE;
		highlight[j][k] = false;
		highlight2[j][k] = false;
	    }
	x = y = 0;
    }

    /* Construct a square matrix */
    public IntMatrix( int rows ) {
	int j, k;
	this.columns = this.rows = rows;
	elems = new int[rows][columns];
	highlight = new boolean[rows][columns];
	highlight2 = new boolean[rows][columns];
	for(j=0;j<rows;j++) 
	    for(k=0;k<columns;k++) {
		elems[j][k] = Integer.MAX_VALUE;
		highlight[j][k] = false;
		highlight2[j][k] = false;
	    }
	x = y = 0;
    }


    public int elem( int i, int j ) {
	return elems[i][j];
    }
    
    public void setElem( int i, int j, int value ) {
	elems[i][j] = value;
    }

    public void incElem( int i, int j, int value ) {
	elems[i][j] = elems[i][j] + value;
    }

    public void setDiag( int[] value ) {
	int j;
	for(j=0;j<columns;j++) 
	    elems[j][j] = value[j];
    }

    public void setLT( int value ) {
	int j, k;
	for(j=0;j<rows;j++)
	    for(k=0; k<j; k++) 
		elems[k][j] = value;
    }

    public void printMatrix() {
	int j, k, x;
	for(j=0;j<rows;j++) {
	    for(k=0;k<columns;k++) {
		x = elems[k][j];
		if( x == Integer.MAX_VALUE ) 
		    System.out.print(" #");
		else
		    System.out.print( x );
		System.out.print( ' ' );
	    }
	    System.out.println();
	}
    }

    public void setHighlight(int j, int k) {
	this.highlight[j][k] = true;
	this.highlight2[j][k] = false;
    }

    public void setHighlight2(int j, int k) {
	this.highlight2[j][k] = true;
	this.highlight[j][k] = false;
    }

    public void restoreHighlight(int j, int k) {
	this.highlight[j][k] = false;
    }

    public void restoreHighlight2(int j, int k) {
	this.highlight2[j][k] = false;
    }

    public void restoreAll() {
	for (int i = 0; i < columns; i++)
	    for (int j = 0; j < rows; j++) {
		this.highlight[i][j] = false;
		this.highlight2[i][j] = false;
	    }
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setRowLabels(String[] strs) {
	if (strs.length != rows) {
	    System.out.println("Row labels do no match the number of rows!");
	    return;
	}

	rowLabels = new String[rows];
	for (int i = 0; i < rows; i++) {
	    if (strs[i].length() > 4)
		strs[i] = strs[i].substring(0, 4);
	    if (strs[i].length() < 4) {
		String blank = new String();
		for (int j = 0; j < 4-strs[i].length(); j++)
		    blank = blank.concat(" ");
		strs[i] = blank + strs[i];
	    }
	    rowLabels[i] = new String(strs[i]);
	}
    }

    public void setColLabels(String[] strs) {
	if (strs.length != columns) {
	    System.out.println(
		"Column labels do no match the number of columns!");
	    return;
	}

	colLabels = new String[columns];
	for (int i = 0; i < columns; i++) {
	    if (strs[i].length() > 4)
		strs[i] = strs[i].substring(0, 4);
	    if (strs[i].length() < 4) {
		String blank = new String();
		for (int j = 0; j < 4-strs[i].length(); j++)
		    blank = blank.concat(" ");
		strs[i] = blank + strs[i];
	    }
	    colLabels[i] = new String(strs[i]);
	}
    }

    public void drawBox(Graphics g, int x, int y, String str,
				Color fg, Color bg, Font font) {
	g.setColor(bg);
	g.fillRect(x, y, horizSpace, vertSpace);
	g.setColor(Color.black);
	g.drawRect(x, y, horizSpace, vertSpace);
	g.setColor(fg);
	g.setFont(font);
	g.drawString(str, x + 2, y + vertSpace - 4);
    }

    public void move(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public void setColor(Color fg, Color bg) {
	this.fg = fg;
	this.bg = bg;
    }

    public void draw(Graphics g) {
	drawMatrix(g, x, y, fg, bg);
    }

    public void drawMatrix(Graphics g, int x, int y, Color fg, Color bg) {
	int j, k, elem;
	int posnX = x, posnY = y;

	// draw colLabels
	if (colLabels != null && colLabels.length == columns) {
	    posnX += horizSpace + 2;
	    for (int i = 0; i < columns; i++) {
		drawBox(g, posnX, posnY, colLabels[i], bg, fg, font);
		posnX += horizSpace + 2;
	    }
	}

	posnX = x;
	// draw rowLabels
	if (rowLabels != null && rowLabels.length == rows) {
	    posnY += vertSpace + 2;
	    for (int i = 0; i < rows; i++) {
		drawBox(g, posnX, posnY, rowLabels[i], bg, fg, font);
		posnY += vertSpace + 2;
	    }
	}

	posnY = y + vertSpace + 2;
	for(j=0;j<rows;j++) {
		posnX = x + horizSpace + 2;
		for(k=0;k<columns;k++) {
		    elem = elems[k][j];
		    if (j < k)
			drawBox(g, posnX, posnY, "    ", fg,
				Color.lightGray, font);
		    else if( elem == Integer.MAX_VALUE )
			drawBox(g, posnX, posnY, "    ", fg, bg, font);
		    else {
		        String blank = new String();
			if (elem < 1000)
			    blank = new String(" ");
			if (elem < 100)
			    blank = new String("  ");
			if (elem < 10)
			    blank = new String("   ");

			if (highlight[k][j])
			    drawBox(g, posnX, posnY, blank+elem,
				Color.white, Color.black, font);
			else if (highlight2[k][j])
			    drawBox(g, posnX, posnY, blank+elem,
				Color.white, Color.gray, font);
			else
			    drawBox(g, posnX, posnY, blank+elem,
				fg, bg, font);
		    }
		    posnX += horizSpace + 2;
		}
		posnY += vertSpace + 2;
	}
	if (title != null && title.length() > 0) {
	    posnY += 5; posnX = x + (columns/2 - 1)*(horizSpace + 2);
	    new ComBox(posnX, posnY, title, Color.black, Color.green,
			font).draw(g);
	}
    }
} // class IntMatrix
