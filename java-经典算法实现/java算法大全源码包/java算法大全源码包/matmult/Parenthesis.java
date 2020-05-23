
import java.awt.*;

class Parenthesis implements DrawingObj {
    int x, y;
    Subscript[] str = null;
    Parenthesis[] parens;
    Font font;
    int right = 0;
    Color col = Color.red;

    public Parenthesis(Subscript[] str, Font font) {
	this.x = 0; this.y = 0;
	this.font = font;
	this.str = str;
	this.parens = null;
    }

    public Parenthesis(Parenthesis[] p, Font font) {
	this.x = 0; this.y = 0;
	this.font = font;
	this.str = null;
	this.parens = p;
    }

    public int getRight() {
	draw(null);
	return right;
    }

    public int getX() {
	return x;
    }

    public Parenthesis getLeftParen() {
	if (parens == null)
	    return null;
	else
	    return parens[0];
    }

    public int getY() {
	return y;
    }

    public void move(int x, int y) {
	this.x = x;
	this.y = y;
	draw(null);
    }

    public void setColor(Color col) {
	this.col = col;
    }

    public void draw(Graphics g) {
	if (str == null) {
	    // parens is not null
	    parens[0].move(x + 9, y);
	    for (int i = 1; i < parens.length; i++)
	        parens[i].move(parens[i-1].getRight(), y);
	    right = parens[parens.length-1].getRight() + 9;
	    if (g == null)
		return;
	    g.setColor(col);
	    g.setFont(font);
	    g.drawString("(", x, y);
	    for (int i = 0; i < parens.length; i++)
		parens[i].draw(g);
	    g.setColor(col);
	    g.setFont(font);
	    g.drawString(")", right - 9, y);
	} else if (str.length == 1) {
	    // only one subscripted num to be enclosed in parenthesis
	    // no need to put parenthesis
	    str[0].move(x, y);
	    right = str[0].getRight();
	    if (g == null) // just to check the right most posn
		return;    
	    str[0].draw(g);
	} else {
	    // parenthesis around more than 1 subscripted nums
	    str[0].move(x + 9, y);
	    for (int i = 1; i < str.length; i++)
	    	str[i].move(str[i-1].getRight(), y);
	    right = str[str.length-1].getRight() + 9;
	    if (g == null)
		return;
	    g.setColor(col);
	    g.setFont(font);
	    g.drawString("(", x, y);
	    for (int i = 0; i < str.length; i++)
		str[i].draw(g);
	    g.setColor(col);
	    g.setFont(font);
	    g.drawString(")", right - 9, y);
	}
    }
}
