/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sumomoxiao
 */


public class AngryBirdsPanelController extends ComponentAdapter implements Runnable{
    AngryBirdsMenuController m;
    AngryBirdsController s;
    JPanel showpanel;
    CardLayout layout;
    
    int mainthread=0;
    
    AngryBirdsPanelController(JPanel show,AngryBirdsMenuController mc,AngryBirdsController st) {
        m=mc;
        s=st;
        showpanel=show;
        layout=(CardLayout) show.getLayout();
        
    }

    @Override
  synchronized public void run() {
        whoShow();
        while(true) {
            whoShow();
         //   System.out.print("who's running "+mainthread+"and stop is "+m.stop+" and the k is "+m.k+"\n");
            switch(mainthread) {
                
                case 0:
                    displayMenu();break;
                case 1:
                    displayGame();break;
                    
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AngryBirdsPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
  synchronized public void whoShow() {
      
      //initiallize menupanel.
        if(m.menuthread==null&&s.gamethread==null) {
         //   System.out.print("menu initiallize !!!! \n");
            mainthread=0;
        }
      //initiallize gamepanel..
        if(m.menuthread!=null)
        if(m.stop&&m.menuthread.isAlive()&&s.gamethread==null) {
         //   System.out.print("game initiallize !!!! \n");
            mainthread=1;
        }
       
      // switch between menu and game.
        if(m.menuthread!=null&&s.gamethread!=null) {
        if(!m.menuthread.isAlive()&&!s.gamethread.isAlive()) {
         //   System.out.print("menu resume !!!! \n");
            mainthread=0;
        }
        if(m.menuthread.isAlive()&&s.gamethread.isAlive())  {
           // System.out.print("game  resume !!!! \n");
            mainthread=1;
        }
        }
    }
    
    public void displayMenu() {
        if(m.menuthread==null) {
            m.start();
            layout.show(showpanel, "menu");
         }
        else if(!m.isPainting()){                                  
            m.resume();
            layout.show(showpanel, "menu");
        }
    }
    
    public void displayGame() {
        if(s.gamethread==null) {
        s.start();
        layout.show(showpanel, "game");
        }
        else {
            s.resume();
            layout.show(showpanel, "game");
        }
    }
}
