/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsMenuController extends MouseMotionAdapter implements Runnable, MouseListener
{
    private final int PREF_WIDTH;
    private final int PREF_HEIGHT;
    private final AngryBirdsMenu slave;
    Thread menuthread;
    boolean stop=true;
    int k = 0;
    
    AngryBirdsMenuController (AngryBirdsMenu menu) {
        PREF_HEIGHT=menu.getHeight();
        PREF_WIDTH=menu.getWidth();
        menu.addController(this);
        slave=menu;
    }
    
    public void start() {
        menuthread=new Thread(this);
        stop=false;//k++;
        menuthread.start();
    }
    
    public void resume() {
        stop=false;//k=k+2;
    }

    @Override
synchronized  public void run() {
        while(true) {
            try {
                if(!slave.shut||!stop) //keeping painting menu when menu painting operation is not actully set to false .
           if(slave.render())          
               slave.paintscence();
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(AngryBirdsMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
        @Override
    public void mouseMoved(MouseEvent e) {
            slave.cursorpoint.set(e.getX(), e.getY());
            
            if(e.getX()<PREF_WIDTH/2+156&&e.getX()>PREF_WIDTH/2-156&&e.getY()>PREF_HEIGHT/2-100&&e.getY()<PREF_HEIGHT/2+100) {
               
                    if(!slave.inside) {
                       slave.buttonscale=1;
                       slave.tx=164;
                       slave.ty=209/2;
                       slave.inside=true;
                    //   slave.update(slave.getGraphics());
                    }
             }
                    else {
                       slave.inside=false;
                       slave.buttonscale=0;
                       slave.tx=150;slave.ty=191/2;
                    //   slave.update(slave.getGraphics());
                    }
              if(e.getX()<85&&e.getX()>0&&e.getY()>PREF_HEIGHT-85&&e.getY()<PREF_HEIGHT) {
               
                    if(!slave.inside1) {
                       slave.buttonscale1=1;
                       slave.ty1=87/2;
                       slave.inside1=true;
                    //   slave.update(slave.getGraphics());
                    }
             }
                    else {
                       slave.inside1=false;
                       slave.buttonscale1=0;
                       slave.ty1=75/2;
                    //   slave.update(slave.getGraphics());
                    }
    }
       
        public boolean isPainting(){
            return slave.isPainting();
        }
    
        @Override
    public void mouseDragged(MouseEvent me) {
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
               if(slave.inside) {
                   slave.shut=true;
                   stop=true;
               }
               
               if(slave.inside1) {
                   System.exit(0);
               }
    }

    @Override
    public void mousePressed(MouseEvent e) {
          return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
           return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
            return;
    }
    
}
