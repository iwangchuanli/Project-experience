/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.geom.AffineTransform;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

/**
 * This class provide Rotation and Translation Position that used for rendering game.
 * @author Sumomoxiao
 */
public class AngryBirdsViewportTransform {
    Transform postrans=new Transform();
    OBBViewportTransform vpt=new OBBViewportTransform();
    float scale=18.0f;
    Vec2 center=new Vec2();
    Vec2 offset=new Vec2();
    public  float bgscale=1f;
    int btx=0,bty=0;
    
    AngryBirdsViewportTransform(AngryBirdsPanel v) {
        vpt.setYFlip(true);
        vpt.setExtents(v.getWidth()/2,v.getHeight()/2);
        center.set(v.getWidth()/2,v.getHeight()/2+170f);
        offset.set(-v.getWidth()/2,170f);
    }
    public void getWorldtoScreen(Vec2 worldpos,Vec2 out) {
        worldpos.x=(worldpos.x)*scale;
        worldpos.y=worldpos.y*scale;
        vpt.getWorldToScreen(worldpos, out);
        out.addLocal(offset);
    }
    
    public void getScreentoWorld(Vec2 screenpos,Vec2 worldpos) {
        screenpos.subLocal(offset);

        vpt.getScreenToWorld(screenpos, worldpos);     
        
        screenpos.x=screenpos.x/scale;
        screenpos.y=screenpos.y/scale;
    }
    
    public AffineTransform rotatePoint(AffineTransform dtrans,float angle,Vec2 anchorp) {

                       //Create AffineTransform Instance
        dtrans.rotate(angle,anchorp.x,anchorp.y);                   //Rotate Round the Anchorpoint.
        
        return dtrans;
    }
    public void setZoomCenter(int xpos) {
        this.center.x=xpos;
        
    }
    
    public Vec2 getOffset(int xpos,int scale) {
           offset.x=offset.x+xpos;
           return offset;
    }
    
    public void mouseWheelTransform(int xoffset,int scaletimes){   
        if(scale <25&&scaletimes>0) {
        scale=scale+scaletimes*2;
        offset.y+=2;
        bgscale+=0.01f;
        }
        if(scale>18&&scaletimes<0) {
            scale=scale+scaletimes*2;
            offset.y-=2;
            bgscale-=0.01f;     
        }
        
    }
    
    public void ScrollLeft() {
        if(offset.x<-512)
        offset.x+=7;
    } 
    
    public void ScrollRight() {
        //if(offset.x>-950)
        {
            if(offset.x>-700)
              offset.x-=7;
            if(offset.x>-800)
                offset.x-=5;
            if(offset.x<-1000)
                offset.x-=3;
        }
            
    }
}
