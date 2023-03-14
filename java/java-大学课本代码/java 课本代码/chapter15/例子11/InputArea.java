import java.io.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class InputArea extends JPanel implements ActionListener {
   File f=null;                   //���������ļ�
   Box baseBox ,boxV1,boxV2; 
   JTextField name,mount,price;  //ΪGoods�����ṩ����ͼ
   JButton button;               //������
   LinkedList<Goods> goodsList; //���Goods���������
   InputArea(File f) {
     this.f=f;
     goodsList=new LinkedList<Goods>();
     name=new JTextField(12);
     mount=new JTextField(12); 
     price=new JTextField(12);
     button=new JButton("¼��");
     button.addActionListener(this);
     boxV1=Box.createVerticalBox();
     boxV1.add(new JLabel("��������"));
     boxV1.add(Box.createVerticalStrut(8));
     boxV1.add(new JLabel("������"));
     boxV1.add(Box.createVerticalStrut(8));
     boxV1.add(new JLabel("���뵥��"));
     boxV1.add(Box.createVerticalStrut(8));
     boxV1.add(new JLabel("����¼��"));
     boxV2=Box.createVerticalBox();
     boxV2.add(name);
     boxV2.add(Box.createVerticalStrut(8));
     boxV2.add(mount);
     boxV2.add(Box.createVerticalStrut(8));
     boxV2.add(price);
     boxV2.add(Box.createVerticalStrut(8));
     boxV2.add(button);
     baseBox=Box.createHorizontalBox();
     baseBox.add(boxV1);
     baseBox.add(Box.createHorizontalStrut(10));
     baseBox.add(boxV2);
     add(baseBox); 
   }
   public void actionPerformed(ActionEvent e) {
     if(f.exists()) {
       try{
          FileInputStream  fi=new FileInputStream(f);
          ObjectInputStream oi=new ObjectInputStream(fi);
          goodsList= (LinkedList<Goods>)oi.readObject();
          fi.close();
          oi.close();
          Goods goods=new Goods();
          goods.setName(name.getText());
          goods.setMount(mount.getText()); 
          goods.setPrice(price.getText());
          goodsList.add(goods);
          FileOutputStream fo=new FileOutputStream(f);
          ObjectOutputStream out=new ObjectOutputStream(fo);
          out.writeObject(goodsList);
          out.close();
       }
       catch(Exception ee) {}
     }
     else{
       try{ 
          f.createNewFile();
          Goods goods=new Goods();
          goods.setName(name.getText());
          goods.setMount(mount.getText()); 
          goods.setPrice(price.getText());
          goodsList.add(goods);
          FileOutputStream fo=new  FileOutputStream(f);
          ObjectOutputStream out=new ObjectOutputStream(fo);
          out.writeObject(goodsList);
          out.close();
       }
       catch(Exception ee) {}
     }
   }
}

