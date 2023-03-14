/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsCharacters;

import javax.swing.ImageIcon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 *
 * @author SONY
 */
public class AngryBirdsObstacles extends AngryBirdsCharacter {

    PolygonShape obsshape = new PolygonShape();
    ImageIcon woodapp = new ImageIcon("src/AngryBirdsImagePack/wood.png");
    ImageIcon box = new ImageIcon("src/AngryBirdsImagePack/wood.png");
    ImageIcon stone = new ImageIcon("src/AngryBirdsImagePack/wood.png");
    ImageIcon pigs = new ImageIcon("src/AngryBirdsImagePack/pigs.png");
    float hafwidth, hafheight;

    public AngryBirdsObstacles() {
        super();
        hafwidth = 0.6f;
        hafheight = 0.6f;
        obsshape.setAsBox(hafwidth, hafheight);
        super.setCharactershape(obsshape);
        super.getCharacterfixdef().filter.groupIndex = 1;
        //super.getCharacterinfo().setAppearance(woodapp.getImage());
        super.getCharacterinfo().setAppearance(pigs.getImage());
        super.getCharacterdef().angle = (float) (Math.PI / 2);
        super.getCharacterinfo().setHafheight(hafheight);
        super.getCharacterinfo().setHafwidth(hafwidth);
    }

    public AngryBirdsObstacles(float hwidth, float hheight, float iangle) {
        super();
        hafwidth = hwidth;
        hafheight = hheight;
        obsshape.setAsBox(hafwidth, hafheight);
        super.setCharactershape(obsshape);
        super.getCharacterfixdef().filter.groupIndex = 1;
        super.getCharacterdef().angle = iangle;
        super.getCharacterinfo().setHafheight(hafheight);
        super.getCharacterinfo().setHafwidth(hafwidth);
    }

    public Body createObstacles(World mom, int type, Vec2 pos, float angle) {
        this.getCharacterdef().position.set(pos);
        characterdef.angle = angle;
        Body obs = mom.createBody(characterdef);
        switch (type) {
            case 1:
                this.getCharacterinfo().setName("Wood Stick");
                obs.m_userData = this.getCharacterinfo();
                characterfixdef.density = 1.4f;
                obs.createFixture(characterfixdef);
                break;
            case 2:
                hafwidth = 1f;
                hafheight = 1f;
                obsshape.setAsBox(hafwidth, hafwidth);
                this.getCharacterfixdef().shape = obsshape;
                this.getCharacterinfo().setName("stone");
                this.getCharacterinfo().setHafheight(hafheight);
                this.getCharacterinfo().setHafwidth(hafwidth);
                this.getCharacterinfo().setAppearance(stone.getImage());
                this.getCharacterfixdef().userData = this.getCharacterinfo();
                this.getCharacterfixdef().density = 2f;
                this.getCharacterfixdef().restitution = 0.01f;
                this.getCharacterfixdef().friction = 0.5f;
                obs.m_userData = this.getCharacterinfo();
                obs.createFixture(characterfixdef);
                break;
            case 3:
                hafwidth = 0.8f;
                hafheight = 0.8f;
                obsshape.setAsBox(hafwidth, hafwidth);
                this.getCharacterfixdef().shape = obsshape;
                this.getCharacterinfo().setName("box");
                this.getCharacterinfo().setHafheight(hafheight);
                this.getCharacterinfo().setHafwidth(hafwidth);
                this.getCharacterinfo().setAppearance(box.getImage());
                this.getCharacterfixdef().userData = this.getCharacterinfo();
                this.getCharacterfixdef().density = 1.3f;
                this.getCharacterfixdef().restitution = 0.3f;
                this.getCharacterfixdef().friction = 0.3f;
                obs.m_userData = this.getCharacterinfo();
                obs.createFixture(characterfixdef);
                break;
        }
        return obs;
    }
}
