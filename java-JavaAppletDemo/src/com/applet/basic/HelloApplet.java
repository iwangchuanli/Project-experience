package com.applet.basic;

import java.applet.Applet;
import java.awt.*;

/**
 * applet 简单应用
 */
public class HelloApplet extends Applet {
    @Override
    public void paint(Graphics g) {
        g.drawString("Hello World Applet",25,50);
    }
}
