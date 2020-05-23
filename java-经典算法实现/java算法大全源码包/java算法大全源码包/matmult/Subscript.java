
import java.awt.*;

class Subscript implements DrawingObj {
    private int x, y;
    private String str, sub;
    private Font font, subfont;
    private Color col = Color.black;

    public void setColor(Color col) {
	this.col = col;
    }

    public void setString(String str) {
	this.str = str;
    }

    public void setSub(String sub) {
	this.sub = sub;
    }

    public Subscript(String str, String sub) {
	this.str = str;
	this.sub = sub;
	font = new Font("Courier", Font.PLAIN, 14);
	subfont = new Font("Courier", Font.PLAIN, 10);
    }
	
    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public void move(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public void draw(Graphics g) {
	g.setColor(col);
	g.setFont(font);
	g.drawString(str, x, y);
	g.setFont(subfont);
	g.drawString(sub, x + str.length()*9, y + 2);
    }

    public int getRight() {
	return x + str.length()*9 + 7;
    }
}
