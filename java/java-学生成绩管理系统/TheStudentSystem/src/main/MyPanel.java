package main;

import java.awt.*;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public   class   MyPanel   extends   JPanel{   
private   Image   image   =   null;     
     public   MyPanel(Image image)   {   
               this.image   =   image;   
     }   
     //�̶�����ͼƬ���������JPanel������ͼƬ������������
     protected   void   paintComponent(Graphics   g)   {   
    g.drawImage(image,   0,   0,   this.getWidth(),   this.getHeight(),   this);   
     }   
}