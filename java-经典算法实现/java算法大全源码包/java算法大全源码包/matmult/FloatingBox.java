
import java.awt.*;

class FloatingBox implements DrawingObj {
    int x, y;
    String str;
    Font font;

    public FloatingBox(String str, Font font) {
	this.x = 0; this.y = 0;
	this.font = font;
	setText(str);
    }

    public void setText(String str) {
	String blank = new String();
	if (str.length() < 8) {
	    for (int i = 0; i < 8-str.length(); i++)
		blank = blank.concat(" ");
	}
	this.str = blank + str;
    }

    public String getText() {
	return str.trim();
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
	g.setColor(Color.black);
	g.fillRect(x, y, 64, 17);
	g.drawRect(x, y, 64, 17);
	g.setColor(Color.white);
	g.setFont(font);
	g.drawString(str, x + 2, y + 13);
    }
}
