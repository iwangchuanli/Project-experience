/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsCharacters;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsBodyInfo extends Object {
    ImageIcon appearance;
    ArrayList<ImageIcon> touching =new ArrayList<ImageIcon>();
    boolean colliding = false;
    float health;
    String name;
    float hafwidth=0;
    float hafheight=0;
    
    AngryBirdsBodyInfo(String image,float hp,String name) {
        appearance=new ImageIcon(image);
        health=hp;
        this.name=name;
    }
    AngryBirdsBodyInfo() {
        appearance=new ImageIcon("src/AngryBirdsImagePack/cross.png");
        this.name="unknown";
        health=10.0f;
    }
    
    public void setAppearance(Image newapp){
        appearance.setImage(newapp);
    }
    
    public void setName(String newname) {
        this.name=newname;
    }
    public void setHealth(float newhp) {
        this.health=newhp;
    }
    
    public Image getAppearance() {
        return appearance.getImage();
    }

    public float getHafwidth() {
        return hafwidth;
    }

    public float getHafheight() {
        return hafheight;
    }

    public void setHafheight(float hafheight) {
        this.hafheight = hafheight;
    }

    public void setHafwidth(float hafwidth) {
        this.hafwidth = hafwidth;
    }

    public String getName() {
        return name;
    }
    
}
