
import java.applet.*;
import java.awt.*;

public class Stick {
    int x, y;                // position of stick
    int w, h;                // width and height of stick
    int value;               // value represented by stick

    boolean flashing;        // indicator for 1st active stick
    boolean pressed;        // indicator for 2nd active stick
    boolean current_partition;	// indicator for current partition

    char pos;        // indicator for partition divider

    int rr, gg, bb;        // RGB values for the color of the stick
    Color base_colour, cur_colour;

    static int scale = 10; /* Scale factor value -> screen height */

    static int left_offset = 10;
    static int bottom_offset = 10;

    // initialize all the properties of the stick
    public Stick(int x, int y, int stickWidth, int value) {
        this.x = x;
        this.y = y;
        this.w = stickWidth;
        this.h = value;
        this.current_partition = this.flashing = false;
        this.pressed = true;
        this.pos = 'm';
        this.value = value;
        h = value * scale;
        
        this.rr = 0xff;
        this.gg = 0xff;
        this.bb = 0xff;
                
        base_colour = Color.red;
        cur_colour = base_colour;
    } // Stick()

    // change the position of the stick
    void move( int dx, int dy ) {	
        this.x = this.x + dx;
        this.y = this.y + dy;
    } // move()

    void setScaleFactor( int scale_factor ) {
        scale = scale_factor;
    } // setScaleFactor()

    // sets the position and size of the stick
    void setXYWH( int n, int size, int value ) {
        this.x = left_offset + (n*size);
        this.y = bottom_offset;
        this.w = size;
        this.h = scale*value;
        return;
    } // setXYWH()

    void setCurrentColour( Color c ) {
        cur_colour = c;
    } // setCurrentColour()

    void restoreColour( ) {
        cur_colour = base_colour;
    } // restoreColour()

    // sets the x property of the stick
    void setX( int nx ) {
        this.x = nx;
        return;
    } // setX();

    // sets the y property of the stick
    void setY( int ny ) {
        this.y = ny;
        return;
    } // setY()

    // sets the pressed property of the stick
    void setPressed(boolean tf) {
        this.pressed = tf;
        return;
    }

    // sets the flashing property of the stick
    void setFlashing(boolean tf) {
        this.flashing = tf;
        return;
    }

    // sets the pos property of the stick
    void setPosition(char c) {
        this.pos = c;
        return;
    }

    // sets the current_partition property of the stick
    void setCurrentPartition(boolean tf) {
        this.current_partition = tf;
        return;
    }

    // returns the value property of the stick
    int stickValue() {
        return this.value;
    }
	
    // returns the x property of the stick
    int xValue() {
        return this.x;
    }

    // returns the y property of the stick
    int yValue() {
        return this.y;
    }

    // returns the pos property of the stick
    char posValue() {
        return this.pos;
    }

    // draw the current stick
    void paintStick(Graphics g) {
        String lbl = String.valueOf(this.value);
        g.setColor( cur_colour );
        g.fill3DRect( x, y-h, w, h, pressed );
        FontMetrics fm = g.getFontMetrics();
        int sx = x+((w-fm.stringWidth(lbl))/2);
        int sy = y-(h/2);
        g.setFont( new Font("Dialog", Font.BOLD, w/2) );
        if (this.flashing)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        g.drawString(lbl,sx,sy);
    }

} // class Stick
