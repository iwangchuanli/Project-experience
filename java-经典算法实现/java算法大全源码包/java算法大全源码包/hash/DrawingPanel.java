
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

/**
 * <code>DrawingPanel</code> is the graphical panel attached to the
 * animation frame <code>AlgAnimFrame</code>. It contains the methods
 * to draw objects for the animation algorithm or instances of another
 * classes, which contain the drawing methods.
 * <p>
 * This class is <b>TO BE MODIFIED/COMPLETED</b>, mainly in the
 * <code>paint(Graphics)</code> method to include the statements
 * draw objects corresponding to the particular animation algorithm.
 * Additional methods are also required to perform the animation of the
 * particular algorithm.
 *
 * @see AlgAnimFrame#drawingPanel
 */
public class DrawingPanel extends Panel {
    /**
     * Height of the drawing panel set during the initialization of the
     * class or after each invoke of the <code>repaint()</code> method.
     */
    public int panel_height = 100;
    /**
     * Width of the drawing panel set during the initialization of the
     * class or after each invoke of the <code>repaint()</code> method.
     */
    public int panel_width = 100;
    /**
     * Inset of the drawing panel, i.e. the edge pads on the four sides of
     * of the panel that are not to be drawn.
     */
    public int offset = 10;
    int pref_height = 200;
    int pref_width = 250;
    int delayDuration = 200;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    TablePanel table;
    Histogram hashStats, collisionStats;

    /**
     * Font objects initialized during the class initialization, which
     * can be readily used during the animation. Initialization fonts
     * on the fly during the animation will slow down the screen update
     * significantly (especially on SGI Irix platform). Therefore,
     * this fonts are only initialized during the creation of the class
     * and used by any other classes that require them to reduce
     * font initialization overhead.
     */
    public Font bigFont;
    /**
     * Font objects initialized during the class initialization, which
     * can be readily used during the animation.
     * @see DrawingPanel#bigFont
     */
    public Font smallFont, tinyFont, hugeFont, fixFont;
    Color darkGreen = new Color(0, 140, 0);

    ComBox tableLabel;
    ComBox fileLabel;

    Color[] tone;

    /**
     * Creates a panel with white background and initializes the fonts
     * to be used during the animation.
     * @see DrawingPanel#bigFont
     * @see DrawingPanel#smallFont
     * @see DrawingPanel#tinyFont
     * @see DrawingPanel#hugeFont
     * @see DrawingPanel#fixFont
     */
    public DrawingPanel() {
	tone = new Color[50];
	for (int i = 0; i < 30; i++)
	    tone[i] = new Color(190 + i*2, 190 + i*2, 190 + i*2);
        smallFont = new Font("Dialog", Font.PLAIN, 10);
        fm = this.getFontMetrics(smallFont);
	bigFont = new Font("Dialog", Font.PLAIN, 12);
	hugeFont = new Font("Dialog", Font.PLAIN, 14);
	fixFont = new Font("Courier", Font.PLAIN, 12);
	tinyFont = new Font("Dialog", Font.PLAIN, 8);
	setBackground(Color.white);
	panel_height = size().height;
	panel_width = size().width;
	tableLabel = new ComBox(0, 0, "Hash Table Contents",
		Color.white, Color.blue, fixFont);

	fileLabel = new ComBox(0, 0, "FILE", 
		Color.yellow, Color.black, fixFont);

	setLayout(null);
	table = new TablePanel(300);
	table.setDrawingPanel(this);
	table.reshape(0, 0, 270, 133);
	add(table);

	hashStats = new Histogram();
	hashStats.setTitle("Hashing Freq Stats");
	hashStats.setXLabel("number of hashes");
	hashStats.setYLabel("number of keys");
	hashStats.setYMax(40); hashStats.setYStep(10);
	hashStats.setXMax(10); hashStats.setXStep(5);
	hashStats.reshape(10, 10, 260, 330);
	add(hashStats);

	collisionStats = new Histogram();
	collisionStats.setTitle("Collision Stats");
	collisionStats.setXLabel("k(th) items");
	collisionStats.setYLabel("number of collisions during insertions");
	collisionStats.setYMax(10); collisionStats.setYStep(5);
	collisionStats.setXMax(20); collisionStats.setXStep(10);
	collisionStats.reshape(500, 255, 300, 180);
	add(collisionStats);
    } // DrawingPanel() 
    
    public void incHashStat(int i) {
	hashStats.incValueX(i);
	hashStats.repaint();
    }

    public void setColStat(int x, int y) {
	collisionStats.setValueXY(x, y);
	collisionStats.repaint();
    }

    /**
     * Causing a short delay of 1/3 of the normal delay duration.
     * This method is normally used during debugging to speed up
     * the achievement of certain system state.
     */
    public void shortDelay() {
	try {
	    Thread.sleep(delayDuration/3);
	} catch (InterruptedException e) {}
    }

    /**
     * Invoke a delay for a certain duration specified by 
     * <code>setDelay(int)</code>.
     * @see DrawingPanel#setDelay
     */
    public void delay() {
	if (noAnim) {
	    shortDelay();
	    return;
	}
	try {
	    Thread.sleep(delayDuration);
	} catch (InterruptedException e) {}
    }

    /**
     * Set the delay duration between animation update.
     * @param delay Delay duration in milliseconds.
     */
    public void setDelay(int delay) {
	this.delayDuration = delay;
    }
    
    //----------- Animation methods and variables --------------------

    boolean noAnim = false;

    ComBox comment1 = null;

    String inputWord = new String();
    int inputX = panel_width - 440, inputY = 10;
    Color inputBG = darkGreen;

    String hashWord = new String();
    int hashX = panel_width - 466;
    Color hashBG;

    String inputText = new String();
    String displayText = new String();
    String rehashString = new String();

    public void init() {
	inputWord = new String();
	hashWord = new String();
	comment1 = null;
	inputText = new String();
	displayText = new String();
	hashStats.init();
	collisionStats.init();
	hashStats.setYMax(40); hashStats.setYStep(10);
	hashStats.setXMax(10); hashStats.setXStep(5);
	collisionStats.setYMax(10); collisionStats.setYStep(5);
	collisionStats.setXMax(20); collisionStats.setXStep(10);
    }

    public void setRehashFunction(String rehashString) {
	this.rehashString = rehashString.trim();
    }

    public void setInputText(String inputText) {
	this.inputText = inputText;
	this.displayText = inputText.substring(0, 38);
	repaint(); delay();
    }

    public void setInput(String word, int hash) {
	hashWord = new String();
    	inputX = panel_width - 310;
	inputBG = darkGreen;
	inputWord = word;
	inputY = 17;

	for (int i = 0; i < word.length()+1; i++) {
	    inputText = inputText.substring(1);
	    displayText = inputText.substring(0,38);
	    inputX -= 8;
	    if (noAnim)
		continue;
	    repaint(); delay();
	}

	for (int i = word.length() + 1; i < 15; i+=2) {
	    if (noAnim)
		break;
	    inputX -= 16;
	    repaint(); delay();
	}
	inputX = panel_width - 452;
	repaint(); delay();

	comment1 = new ComBox(panel_width - 500, 10,
			"Hashing...", fixFont); repaint();

	inputY += 20; repaint(); delay();
	inputY = 60; repaint(); delay(); delay();

	hashBG = tone[0];
	hashWord = ""+hash;
	hashX = inputX - 26;
	for (int i = 0; i < 3; i++) {
	    if (noAnim) {
		inputY = 96; break;
	    }
	    inputY += 12; repaint(); delay();
	}
	comment1 = null; repaint();
    }

    public void rehash(int newIndex) {
	comment1 = new ComBox(inputX - 40, inputY - 20,
			"Collision! Re-Hashing...", fixFont); repaint();
	int srcY = inputY;
	int destY = 270;
	for (int i = 0; i < 5; i++) {
	    if (noAnim) {
		inputY = destY;
		break;
	    }
	    inputY += (destY - srcY)/5;
	    repaint(); delay();
	}

	comment1 = null; repaint();
	hashBG = tone[0];
	hashWord = ""+newIndex;
	hashX = inputX - 26;
	for (int i = 0; i < 3; i++) {
	    if (noAnim) {
		inputY = destY - 36;
		break;
	    }
	    inputY -= 12;
	    repaint(); delay();
	}
	repaint(); delay();
    }

    public void contains(String word) {
	if (noAnim)
	    return;
	int index = table.indexOf(word);
	if (index < 0)
	    return;

	table.scroll2posn(index);

	int srcX = inputX, srcY = inputY;
	int destY = yPosnOf(index);
	for (int i = 0; i < 3; i++) {
	    inputY += (destY - srcY)/3;
	    repaint(); delay();
	}
	inputY = destY; repaint(); delay();
	
	int destX = panel_width - 402;
	for (int i = 0; i < 2; i++) {
	    inputX += (destX - srcX)/3;
	    hashX = inputX - 26;
	    repaint(); delay();
	}
	inputX = destX; hashX = inputX - 26; repaint(); delay();

	comment1 = new ComBox(inputX - 40, inputY + 20,
			"Key already exists...", fixFont); repaint();
	for (int i = 0; i < 5; i++) {
	    hashBG = inputBG = tone[i*6];
	    repaint(); delay();
	}
	inputWord = new String();
	repaint(); delay();
	comment1 = null; repaint();
    }

    public void collision(String word, int index) {
	if (noAnim)
	    return;
        table.scroll2posn(index);
	int srcY = inputY;
        int destY = yPosnOf(index);
	int srcX = inputX;
	int destX = panel_width - 402;

	for (int i = 0; i < 3; i++) {
            inputY += (destY - srcY)/3;
            repaint(); delay();
        }
        inputY = destY; repaint(); delay();

        for (int i = 0; i < 2; i++) {
            inputX += (destX - srcX)/3; 
	    hashX = inputX - 26;
            repaint(); delay();
        }
        inputX = destX; hashX = inputX - 26; repaint(); delay();

	// bounce back
	for (int i = 0; i < 2; i++) {
	    inputX -= (destX - srcX)/3;
	    hashX = inputX - 26;
	    repaint(); delay();
	}
	inputX = srcX; hashX = inputX - 26; repaint(); delay();
    }

    public void setTableEntry(String word, int index) {
	int srcX = inputX, srcY = inputY;
	table.scroll2posn(index);
	int destY = yPosnOf(index);
	for (int i = 0; i < 3; i++) {
	    if (noAnim)
		break;
	    inputY += (destY - srcY)/3;
	    repaint(); delay();
	}
	inputY = destY; repaint(); delay();

	comment1 = new ComBox(inputX - 40, destY + 20,
			"Insert into Hash Table...", fixFont); repaint();
	int destX = panel_width - 280;
	for (int i = 0; i < 5; i++) {
	    if (noAnim)
		break;
	    inputX += (destX - srcX)/5; hashX = inputX - 26;
	    repaint(); delay();
	}
	inputX = destX; hashX = inputX - 26; repaint(); delay();
	inputWord = new String();
	comment1 = null; repaint();
    }

    public void fillBox(Graphics g, int x, int y, String str,
                            Color fg, Color bg, int max_length) {
	if (table == null)
	    return;
	int vertSpace = table.draw.vertSpace;
	int horizSpace = max_length * 8;
        g.setColor(bg);
        g.fillRect(x, y, horizSpace, vertSpace);
        g.setColor(fg);
        g.setFont(fixFont);
        if (str != null)
            g.drawString(str, x + 2, y + vertSpace - 4);
    }

    public void drawBox(Graphics g, int x, int y, String str,
                            Color fg, Color bg, int max_length) {
	if (table == null)
	    return;

	table.draw.drawBox(g, x, y, str, fg, bg, max_length);
    }

    public int yPosnOf(int index) {
	return (121 + (index - table.draw.start) * (table.draw.vertSpace + 2));
    }

    public void setNoAnim(boolean noAnim) {
	this.noAnim = noAnim;
    }

    //---------------------------------------------------------------------

    /**
     * This method is invoked when the <code>repaint()</code> method is called.
     * The <code>update</code> method is override here to eliminate flashing.
     * @param g Graphical context
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
        offGraphics.setFont(smallFont);
        fm = offGraphics.getFontMetrics();
        paint(offGraphics);
        g.drawImage(offscreen, 0, 0, null);
    }

    /**
     * Method to draw objects on the drawing panel. This method is
     * <b>TO BE COMPLETED</b> to incorporate the drawing methods/statements.
     */
    public void paint(Graphics g) {
	if (panel_width != size().width) {
	    table.reshape(size().width - 280, 120, 270, 133);
	}

	panel_height = size().height;
	panel_width = size().width;

	fileLabel.setTopLeft(panel_width - 38, 9);
	fileLabel.draw(g);

	if (inputWord.length() > 0) {
	    if (hashWord.length() > 0) 
		fillBox(g, hashX, inputY, hashWord,
			Color.red, hashBG, 3);
	    fillBox(g, inputX, inputY, inputWord,
			Color.white, inputBG, 15);
	}

        //g.setColor( Color.black );
        //g.drawRect( 1, 1, panel_width-2, panel_height-2 );
	tableLabel.setTopLeft(panel_width - 254, 90);
	tableLabel.draw(g);

	drawBox(g, panel_width - 310, 17, displayText,
		Color.black, Color.white, 34);

	if (table != null) {
	    table.drawClusters(g, offset + 50, panel_height - 50);
	}

	// draw Hash function
	g.setColor(Color.black);
	g.fillRect(panel_width - 486, 55, 160, 30);
	g.setColor(Color.white);
	g.drawRect(panel_width - 485, 56, 157, 27);
	fillBox(g, panel_width - 470, 60, "Hash Function, H()",
		 	Color.cyan, Color.black, 17);

	// draw rehash function
	g.setColor(Color.gray);
	g.fillRect(panel_width - 502, 265, 192, 30);
	g.setColor(Color.white);
	g.drawRect(panel_width - 501, 266, 189, 27);
	fillBox(g, panel_width - 490, 270, "Rehash " + rehashString,
		 	Color.yellow, Color.gray, 21);

	if (comment1 != null)
	    comment1.draw(g);
    } // paint()

    /**
     * Returns the initial preferred size of the drawing panel.
     * This method is called by the layout manager during the creation
     * of the corresponding drawing frame.
     */
    public Dimension getPreferredSize() {
        return new Dimension( pref_width, pref_height );
    } // getPreferredSize()
} // class DrawingPanel
