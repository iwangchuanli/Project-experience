/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsCharacters;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 *
 * @author SONY
 */
public class AngryBirdsGround {

    public AngryBirdsBodyInfo ginfo = new AngryBirdsBodyInfo();
    public BodyDef gdef = new BodyDef();
    public FixtureDef gfix = new FixtureDef();
    public PolygonShape gshape = new PolygonShape();
    ImageIcon gimage = new ImageIcon("src/AngryBirdsImagePack/ground.png");

    public AngryBirdsGround() {
        gdef.position.set(32f, -0.5f);
        gfix.friction = 0.7f;
        gfix.density = 0f;

        gshape.setAsBox(60f, 0.5f);

        gfix.shape = gshape;
        gfix.filter.groupIndex = 0;
        ginfo.setAppearance(gimage.getImage());
    }

    public Body createGround(World dad) {
        Body ground = dad.createBody(gdef);
        ground.m_userData = ginfo;
        ground.createFixture(gfix);
        return ground;
    }
}
