package com.applet.apply;

/**
 * 事件处理
 * Applet 类从 Container 类继承了许多事件处理方法。Container 类定义了几个方法，
 * 例如：processKeyEvent() 和processMouseEvent()，用来处理特别类型的事件，
 * 还有一个捕获所有事件的方法叫做 processEvent。
 * 为了响应一个事件，Applet 必须重写合适的事件处理方法。
 */
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.applet.Applet;
import java.awt.Graphics;

public class ExampleEventHandling extends Applet
        implements MouseListener {

    StringBuffer strBuffer;

    public void init() {
        addMouseListener(this);
        strBuffer = new StringBuffer();
        addItem("initializing the applet ");
    }

    public void start() {
        addItem("starting the applet ");
    }

    public void stop() {
        addItem("stopping the applet ");
    }

    public void destroy() {
        addItem("unloading the applet");
    }

    void addItem(String word) {
        System.out.println(word);
        strBuffer.append(word);
        repaint();
    }

    public void paint(Graphics g) {
        //Draw a Rectangle around the applet's display area.
        g.drawRect(0, 0,
                getWidth() - 1,
                getHeight() - 1);

        //display the string inside the rectangle.
        g.drawString(strBuffer.toString(), 10, 20);
    }


    public void mouseEntered(MouseEvent event) {
    }
    public void mouseExited(MouseEvent event) {
    }
    public void mousePressed(MouseEvent event) {
    }
    public void mouseReleased(MouseEvent event) {
    }

    public void mouseClicked(MouseEvent event) {
        addItem("mouse clicked! ");
    }
}
