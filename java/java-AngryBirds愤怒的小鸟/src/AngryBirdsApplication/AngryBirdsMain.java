/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import AngryBirdsLevel.BirdsLevel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.media.CannotRealizeException;
//import javax.media.NoPlayerException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsMain {

    /**
     * Entrance of This Game.
     */
    public static void main(String[] args) throws InterruptedException, IOException{// NoPlayerException, CannotRealizeException {

            
            BirdsLevel ts=new BirdsLevel();
          
            AngryBirdsMenu menu=new AngryBirdsMenu();
            AngryBirdsPanel game=new AngryBirdsPanel();

            AngryBirdsViewFrame mainframe=new AngryBirdsViewFrame(menu,game);
            //MusicController music=new MusicController();
            AngryBirdsController tc=new AngryBirdsController(ts,game);
            AngryBirdsMenuController mc=new AngryBirdsMenuController(menu);
            AngryBirdsPanelController pc=new AngryBirdsPanelController(mainframe.getLayoutpanel(),mc,tc);        

            
            ts.getWorld().setContactListener(tc);
            //music.start();

            Thread switcher=new Thread(pc);
            
            switcher.start();
            mainframe.setVisible(true);
        
    }
}
