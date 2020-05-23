
import java.awt.*;

class ShadowLabel implements DrawingObj {
    private int x, y;
    private String str;
    private Font font;
    private Color col = Color.black;

    public void setColor(Color col) {
	this.col = col;
    }

    public void setText(String str) {
	this.str = str;
    }

    public String getText() {
	return new String(str);
    }

    public ShadowLabel(String str) {
	this.str = str;
	font = new Font("Helvetica", Font.BOLD, 14);
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
	g.setFont(font);
	g.setColor(Color.lightGray);
	g.drawString(str, x+1, y+1);
	g.setColor(col);
	g.drawString(str, x, y);
    }
}
