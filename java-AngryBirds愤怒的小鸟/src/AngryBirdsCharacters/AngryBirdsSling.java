/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsCharacters;

import javax.swing.ImageIcon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsSling {
        static PolygonShape slingshape=new PolygonShape();
        static BodyDef sd=new BodyDef();
        static AngryBirdsBodyInfo si=new AngryBirdsBodyInfo();
        static FixtureDef sf=new FixtureDef();
        static ImageIcon slingimage=new ImageIcon("src/AngryBirdsImagePack/slingstick.png");
    
    static public Body createStick(World mom,Vec2 pos) {
        slingshape.setAsBox(0.5f, 2.7f);
        sd.type=sd.type.STATIC;
        sd.position=pos;
        si.setName("stick");si.setHafwidth(0.8f);si.setHafheight(2.7f);si.setAppearance(slingimage.getImage());
        sd.userData=si;
        sf.shape=slingshape;
        sf.density=1f;
        Body stick=mom.createBody(sd);
        slingshape.setAsBox(0.5f,0.1f);
        pos.y=pos.y+1.2f;
        Body plat=mom.createBody(sd);
        plat.createFixture(slingshape,1);
        stick.createFixture(sf);
        return stick;
    }

}
