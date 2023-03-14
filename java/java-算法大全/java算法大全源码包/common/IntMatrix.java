/* IntMatrix.java */

import java.io.*;
import java.awt.*;

/**
 * An example implementation of the <code>DrawingObj</code> interface
 * that can be added to the drawingPanel (an instance of
 * <code>DrawingPanel</code>). Similar to <code>ComBox</code>, an instance
 * of this object class is added to the drawing panel by using the
 * <code>addDrawingObj</code> method from <code>DrawingPanel</code>.
 * This drawing obj can be removed from the drawing panel by invoking
 * the <code>removeObj</code> method.
 * <p>
 * As an example, an instance of IntMatrix class can be added to
 * an instance of <code>DrawingPanel</code>, namely <code>drawingPanel</code>
 * by using the following: <code><pre>
 *	IntMatrix matrix1 = new IntMatrix(4);
 *	drawingPanel.addDrawingObj(matrix1);
 *	matrix1.setColor(....);
 *	matrix1.move(x, y);
 *	....
 * </pre></code>
 * It can then be removed from the drawing panel by: <code><pre>
 *	drawingPanel.removeObj(matrix1);
 * </pre></code>
 * <p>
 * This class implements a matrix representation with all integer entries.
 * @see ComBox
 * @see DrawingPanel#addDrawingObj
 * @see DrawingPanel#removeObj
 */
public class IntMatrix implements DrawingObj {
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
    
    /**
     * Construct a matrix with the number of rows and columns specified by the
     * parameters of the constructor.
     * @param rows The number of rows of the new matrix.
     * @param columns The number of columns of the new matrix.
     */
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

    /**
     * Construct a square matrix with number of rows = number of columns
     * as specified by the single parameter of the constructor.
     * @param rows Number of rows/columns of the new matrix.
     */
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


    /**
     * Get a particular element of the matrix specified by the position
     * given in the parameters of the method.
     * @param i The column number of the element of interest.
     * @param j The row number of the element to be extracted.
     * @return The integer element at row and column specified by the two
     *	parameter.
     */
    public int elem( int i, int j ) {
	return elems[i][j];
    }
    
    /**
     * Assign a new value to the entry specified by the parameters.
     * @param i The column number of the element that is being assigned a new
     * 	value.
     * @param j The row number of the element that is being assigned a new
     *	value.
     * @param value The new value of the element.
     */
    public void setElem( int i, int j, int value ) {
	elems[i][j] = value;
    }

    /**
     * Increment the element specified by the first two parameters by the
     * value specified by the third parameter.
     * @param i The column number of the element to be incremented.
     * @param j The row number of the element to be incremented.
     * @param value The amount of the element incrementation.
     */
    public void incElem( int i, int j, int value ) {
	elems[i][j] = elems[i][j] + value;
    }

    /**
     * Set the value for each element on the main diagonal based on the
     * array passed in as the parameter. The length of the array must
     * be at least equal to the longest dimension of the matrix and the
     * matrix must be a square matrix. Otherwise, an exception might be
     * raised during execution.
     * @param value An array of value to be assigned to each of the element
     *	on the main diagonal of the matrix.
     */
    public void setDiag( int[] value ) {
	int j;
	for(j=0;j<columns;j++) 
	    elems[j][j] = value[j];
    }

    /**
     * Assign a value to all elements to the left hand side of the main
     * diagonal (exclusive).
     * @param value The value to be assigned to the elements.
     */
    public void setLT( int value ) {
	int j, k;
	for(j=0;j<rows;j++)
	    for(k=0; k<j; k++) 
		elems[k][j] = value;
    }

    /**
     * Print out the contents of the matrix to the stdout.
     * MAX_VALUE is represented by #. This method is only used during the
     * diagnostic of the class.
     */
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

    /**
     * Highlight an element of the matrix, colouring it to black.
     * @param j The column of the element to be highlighted.
     * @param k The row of the element to be highlighted.
     */
    public void setHighlight(int j, int k) {
	this.highlight[j][k] = true;
	this.highlight2[j][k] = false;
    }

    /**
     * Highlight an element of the matrix, colouring it to gray.
     * @param j The column of the element to be highlighted.
     * @param k The row of the element to be highlighted.
     */
    public void setHighlight2(int j, int k) {
	this.highlight2[j][k] = true;
	this.highlight[j][k] = false;
    }

    /**
     * Restore the element which was highlighted using Color.black.
     * @param j The column of the element to be restored.
     * @param k The row of the element to be restored.
     */
    public void restoreHighlight(int j, int k) {
	this.highlight[j][k] = false;
    }

    /**
     * Restore the element which was highlighted using Color.gray.
     * @param j The column of the element to be restored.
     * @param k The row of the element to be restored.
     */
    public void restoreHighlight2(int j, int k) {
	this.highlight2[j][k] = false;
    }

    /**
     * Restore all previously highlighted element of the matrix (black and 
     * gray).
     */
    public void restoreAll() {
	for (int i = 0; i < columns; i++)
	    for (int j = 0; j < rows; j++) {
		this.highlight[i][j] = false;
		this.highlight2[i][j] = false;
	    }
    }

    /**
     * Set a title to the matrix.
     * @param title The new title of the matrix.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Set a label to each row of the matrix based on the array of String
     * passed in as the parameter.
     * @param strs The array of String which holds the new label for each
     * 	row of the matrix. The length of the array must be equal to the
     *	number of rows of the matrix.
     */
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

    /**
     * Set a label to each column of the matrix based on the array of String
     * passed in as the parameter.
     * @param strs The array of String which holds the new label for each
     * 	column of the matrix. The length of the array must be equal to the
     *	number of columns of the matrix.
     */
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

    /**
     * This method is used to draw each element of the matrix. It is only
     * called from the <code>draw</code> method of this class.
     * @param g Graphical context.
     * @param x The left most position of the box.
     * @param y The top most position of the box.
     * @param str The text to be displayed in the box.
     * @param fg Foreground color (text color) of the box.
     * @param bg Background color of the box.
     * @param font A fixed font, in particular size 12 plain courier.
     *	This font instance is passed in to avoid font initialization every
     *	repaint.
     */
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

    /**
     * Move the matrix to the position specified with reference to the top left
     * corner.
     * @param x The left most position of the matrix.
     * @param y The top most position of the matrix.
     */
    public void move(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Get the left most position of the matrix.
     * @return The x-coordinate of the topleft corner of the matrix.
     */
    public int getX() {
	return x;
    }

    /**
     * Get the top most position of the matrix.
     * @return The y-coordinate of the topleft corner of the matrix.
     */
    public int getY() {
	return y;
    }

    /**
     * Set the foreground and background color for each element of the matrix.
     * The row and column labels are displayed in the inverse color mode.
     * @param fg The new foreground color.
     * @param bg The new background color.
     */
    public void setColor(Color fg, Color bg) {
	this.fg = fg;
	this.bg = bg;
    }

    /**
     * This method has to be defined to fully implement the DrawingObj
     * interface. It calls the <code>drawMatrix</code> method which
     * draws the matrix based on the position and color previously set.
     * @see IntMatrix#drawMatrix
     */
    public void draw(Graphics g) {
	drawMatrix(g, x, y, fg, bg);
    }

    /**
     * This method is only called from the <code>draw</code> method which
     * in turn is called from the drawing panel to draw the matrix based
     * on the position and color previously set.
     * @param g graphical context.
     * @param x The left most position of the matrix.
     * @param y The top most position of the matrix.
     * @param fg Foreground (text) color for each element.
     * @param bg Background color for each element.
     */
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
