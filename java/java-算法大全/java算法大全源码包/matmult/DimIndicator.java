
import java.awt.*;

class DimIndicator implements DrawingObj {
    int x, y, left, right;
    FloatingBox box;

    public DimIndicator(int x, int y, int left, int right, 
	String str, Font font) {
	this.x = x;
	this.y = y;
	this.left = left;
	this.right = right;
	this.box = new FloatingBox(str, font);;
	box.move(x - 32, y + 15);
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public void move(int x, int y) {
	this.left += x - this.x;
	this.right += x - this.x;
	this.x = x;
	this.y = y;
    }

    public void draw(Graphics g) {
	g.setColor(Color.black);
	g.drawLine(left, y, left, y+2);
	g.drawLine(right - 2, y, right - 2, y+2);
	g.drawLine(left, y+2, right - 2, y+2);
	g.drawLine(x, y + 2, x, y+15);
	box.draw(g);
    }
}
