/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.MemoryImageSource;
import javax.swing.*;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsViewFrame extends JFrame{
    
    public int PREF_WIDTH=1000;
    public int PREF_HEIGHT=620;
    private JPanel layoutpanel;
    private CardLayout layout=new CardLayout();
    private final AngryBirdsMenu themenu;
    private final AngryBirdsPanel thegame;
    int[] pixels = new int[16 * 16];

    AngryBirdsViewFrame(AngryBirdsMenu menu,AngryBirdsPanel game){
        super();
        layoutpanel=new JPanel(layout);
        themenu=menu;thegame=game;
        layoutpanel.add(game,"game");layoutpanel.add(menu,"menu");
        PREF_WIDTH=menu.getWidth();
        PREF_HEIGHT=menu.getHeight();
        Image transparent = Toolkit.getDefaultToolkit().createImage(
        new MemoryImageSource(16, 16, pixels, 0, 16));
        this.add(layoutpanel);
        this.setPreferredSize(new Dimension(PREF_WIDTH,PREF_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.setResizable(false);
        this.setSize(new Dimension(PREF_WIDTH,PREF_HEIGHT));
        this.setTitle("编程百科-愤怒的小鸟");
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(transparent,new Point(0,0), "transparent"));
        layout.show(layoutpanel, "menu");
        pack();
      
    }


    public CardLayout getLayout() {
        return layout;
    }

    public JPanel getLayoutpanel() {
        return layoutpanel;
    }
    

    
}

