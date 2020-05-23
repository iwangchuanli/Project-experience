/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsCharacters;


import javax.swing.ImageIcon;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author SONY
 */
public class AngryBirdsModel extends AngryBirdsCharacter{
    
   public ImageIcon birds1=new ImageIcon("src/AngryBirdsImagePack/birds.png");
   public ImageIcon birds2=new ImageIcon();
   public ImageIcon birds3=new ImageIcon();
   
  public  AngryBirdsModel() {
     super();
         }
      
  
   public Body createBirds(World mom,int birdtype,Vec2 pos) {
      this.getCharacterdef().position.set(pos);
      this.getCharacterdef().linearDamping=0.01f;
      this.getCharacterfixdef().filter.groupIndex=-1;
      this.charactershape=new CircleShape();
      Body bird=mom.createBody(characterdef);
      switch(birdtype) {
          case 1:
              this.charactershape.m_radius=0.5f;
              this.getCharacterfixdef().shape= this.charactershape;
              this.getCharacterinfo().setName("Lil Bird");
              this.getCharacterinfo().setHafheight(0.5f);
              this.getCharacterinfo().setHafwidth(0.5f);
              this.getCharacterinfo().setAppearance(birds1.getImage());
              bird.m_userData=this.getCharacterinfo();
              bird.createFixture(characterfixdef);
              break;
          case 2:
              this.charactershape.m_radius=0.7f;
              this.getCharacterfixdef().shape= this.charactershape;
              this.getCharacterinfo().setName("Strong Bird");
              this.getCharacterinfo().setHafheight(0.7f);
              this.getCharacterinfo().setHafwidth(0.7f);
              this.getCharacterinfo().setAppearance(birds2.getImage());
              bird.m_userData=this.getCharacterinfo();
              bird.createFixture(characterfixdef);
              break;   
          case 3:
              this.charactershape.m_radius=1f;
              this.getCharacterfixdef().shape= this.charactershape;
              this.getCharacterinfo().setName("Angry Bird");
              this.getCharacterinfo().setHafheight(1f);
              this.getCharacterinfo().setHafwidth(1f);
              this.getCharacterinfo().setAppearance(birds3.getImage());
              bird.m_userData=this.getCharacterinfo();
              bird.createFixture(characterfixdef);
              break;
      }
      return bird;
  }
  

}
   
