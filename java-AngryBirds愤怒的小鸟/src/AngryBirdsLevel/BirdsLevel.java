/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsLevel;

import AngryBirdsCharacters.AngryBirdsModel;
import AngryBirdsCharacters.AngryBirdsGround;
import AngryBirdsCharacters.AngryBirdsObstacles;
import AngryBirdsCharacters.AngryBirdsPig;
import AngryBirdsCharacters.AngryBirdsSling;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.*;
import AngryBirdsApplication.AngryBirdsArea;

/**
 *
 * @author Sumomoxiao
 */
public class BirdsLevel extends AngryBirdsArea {

    public BirdsLevel() {
        super();
    }

    @Override
    public void initStage() {

        AngryBirdsGround gu = new AngryBirdsGround();
        AngryBirdsModel bu = new AngryBirdsModel();
        AngryBirdsObstacles ou = new AngryBirdsObstacles();
        AngryBirdsPig pig = new AngryBirdsPig();

        ground = gu.createGround(sworld);

        Vec2 pos = new Vec2();
        for (int i = 0; i < 2; i++) {
            pos.set(2 + i / 2, 0.5f);
            this.birdlist.add(bu.createBirds(sworld, 1, pos));

            pos.set(40 + 2 * i, 1f);
            float dheight = 1.2f;
            for (int j = 0; j < 6; j++) {
                
                if (j == 0) {
                    
                    dheight *= 0;
                }
                this.oblist.add(ou.createObstacles(sworld, 1, pos.set(pos.x, pos.y + dheight), (float) Math.PI / 2));

                dheight = 1.2f;
                this.oblist.add(ou.createObstacles(sworld, 1, pos.set(pos.x, pos.y + dheight), 0));

            }

        }




        birdbullets = 0;
        pos.set(5f, 5.4f);
        WeldJointDef wd = new WeldJointDef();
        slingAnchor.set(pos);
        wd.bodyA = ground;
        wd.bodyB = birdlist.get(0);
        wd.localAnchorA.set(pos.sub(ground.getPosition()));
        attachDef = wd;
        attach = (WeldJoint) sworld.createJoint(wd);
        birdlist.get(0).setTransform(pos, 0);



    }
}
