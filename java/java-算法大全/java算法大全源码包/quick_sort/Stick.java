
import java.awt.*;

class Stick implements DrawingObj {
    private int x, y;
    private int height;
    private int width = 16;
    private Color col = Color.red;
    private Font font = new Font("Helvetica", Font.BOLD, 10);

    public Stick(int height, int x, int y) {
	this.height = height*3;
	this.x = x;
	this.y = y;
    }

    public Stick(Stick stick) {
	this.height = stick.getHeight()*3;
	this.x = stick.getX(); this.y = stick.getY();
	this.width = stick.getWidth();
	this.col = stick.getColor();
    }

    public int getHeight() {
	return height/3;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getWidth() {
	return width;
    }

    public void setColor(Color col) {
	this.col = col;
    }

    public Color getColor() {
	return col;
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
	g.fillRect(x, y, width, height); 
	g.setColor(Color.black);
	g.drawRect(x, y, width, height); 
	if (col == Color.red || col == Color.blue)
	    g.setColor(Color.white);
	else
	    g.setColor(Color.black);
	g.setFont(font);
	g.drawString(""+(height/3), x + 3, y + 15);
    }
}
