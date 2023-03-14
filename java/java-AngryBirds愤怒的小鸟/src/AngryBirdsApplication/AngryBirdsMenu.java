/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jbox2d.common.Vec2;


    
    
    
/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsMenu extends JPanel{
    ImageIcon background=new ImageIcon("src/AngryBirdsImagePack/background1.jpg");
    ArrayList<ImageIcon> animator=new ArrayList<ImageIcon>();
    ArrayList<ImageIcon> start=new ArrayList<ImageIcon>();
    ImageIcon[] exit=new ImageIcon[2];
    ImageIcon exit0=new ImageIcon("src/AngryBirdsImagePack/Menu/exit.png");
    ImageIcon exit1=new ImageIcon("src/AngryBirdsImagePack/Menu/exit2.png");
    ImageIcon cloud=new ImageIcon("src/AngryBirdsImagePack/menu/cloud.png");
    ImageIcon cloud1=new ImageIcon("src/AngryBirdsImagePack/menu/cloud1.png");
    ImageIcon cloud2=new ImageIcon("src/AngryBirdsImagePack/menu/cloud2.png");
    ImageIcon title=new ImageIcon("");  
    
    
    public static final int PREF_WIDTH = 1000;
    public static final int PREF_HEIGHT = 620;
    public int buttonscale=0,buttonscale1;
    public int tx=150;
    public int ty=191/2,ty1=75/2;
    boolean inside=false;
    boolean inside1=false;
    boolean shut=false;
    boolean flag=false;
    Graphics2D dbg;
    Image dbImage;
    ImageIcon cursor=new ImageIcon("src/AngryBirdsImagePack/Finger.png");
    CardLayout layoutmanager=new CardLayout();
    Vec2 cursorpoint=new Vec2();
    RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_RENDERING,
             RenderingHints.VALUE_RENDER_DEFAULT);

    AngryBirdsMenu() {
        super();
        this.setPreferredSize(new Dimension(PREF_WIDTH,PREF_HEIGHT));
        this.setDoubleBuffered(true);
        animator.add(cloud);
        animator.add(cloud1);
        animator.add(cloud2);
        start.add(new ImageIcon("src/AngryBirdsImagePack/startbutton1.png"));
        start.add(new ImageIcon("src/AngryBirdsImagePack/startbutton.png"));
        exit[0]=exit0;exit[1]=exit1;
        rh.add(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
    }

    
    @Override
    public int getHeight() {
        return PREF_HEIGHT;
    }

    @Override
    public int getWidth() {
        return PREF_WIDTH;
    }

    public boolean render( ) {
       if (dbImage == null) {
         dbImage = createImage(PREF_WIDTH, PREF_HEIGHT);
         if (dbImage == null) {
           return false;
        }
         dbg = (Graphics2D) dbImage.getGraphics();
         
      }
         dbg.setColor(Color.DARK_GRAY);
         rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         dbg.drawImage(background.getImage(), 0, 0, 1024,600,null);
         dbg.drawImage(title.getImage(), PREF_WIDTH/2-title.getImage().getWidth(null)/2, 30, this);
         dbg.drawImage(start.get(buttonscale).getImage(), PREF_WIDTH/2-tx, PREF_HEIGHT/2-ty+20,null);
         dbg.drawImage(exit[buttonscale1].getImage(), 0, PREF_HEIGHT-80-ty1,null);
         dbg.drawImage(cursor.getImage(),(int) cursorpoint.x,(int) cursorpoint.y, 25, 35, this);
         dbg.drawString("Copyright@ Xiao Liu", 410, 585);
         return true;
  }


    
    public void paintscence(){
            flag=false;
        
        if(PREF_WIDTH!=0&&PREF_HEIGHT!=0) {
            flag=true;
        }
        if(flag) {
        Graphics2D g=(Graphics2D) this.getGraphics();
        if(g!=null&&dbImage!=null) {
        g.drawImage(dbImage, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        }

    }
    }
    
    public Graphics getMenuDBDraw() {
        return dbImage.getGraphics();
    }
    
    public boolean isPainting() {
        return flag;
    }
    
    public  void addController(AngryBirdsMenuController mc){
        this.addMouseListener(mc);
        this.addMouseMotionListener(mc);
    }
    }
 class GameButton extends JComponent {
     ImageIcon bi;
     GameButton (ImageIcon i) {
     }

}    
    
    
    



