
import java.awt.*;

class Legend implements DrawingObj {
    private int x, y;
    private Font font = new Font("Helvetica", Font.BOLD, 18);
    private Font font2 = new Font("Helvetica", Font.BOLD, 14);

    public Legend(int x, int y) {
	this.x = x;
	this.y = y;
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

    public void draw(Graphics g) {
	g.setColor(Color.gray);
	g.setFont(font);
	g.drawString("LEGEND", x + 11, y + 21);
	g.setColor(Color.red);
	g.drawString("LEGEND", x + 10, y + 20);
	g.setColor(Color.blue);
	g.fillRect(x + 10, y + 33, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x, y, 100, 130);
	g.drawLine(x, y + 25, x + 100, y+25);

	g.drawRect(x + 10, y + 33, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Pivot", x + 30, y + 43);

	g.setColor(Color.green);
	g.fillRect(x+10, y+58, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x+10, y+58, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Sorted", x + 30, y + 68);

	g.setColor(Color.lightGray);
	g.fillRect(x+10, y+83, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x+10, y+83, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Inactive", x + 30, y + 86);
	g.drawString("Partition", x + 30, y + 98);

	g.setColor(Color.red);
	g.fillRect(x+10, y+108, 10, 10);
	g.setColor(Color.black);
	g.drawRect(x+10, y+108, 10, 10);
	g.setFont(font2);
	g.setColor(Color.black);
	g.drawString("Active", x+30, y+113);
	g.drawString("Partition", x+30, y+125);
    }
}
