import javax.swing.*; 
import javax.swing.tree.*;	
import java.awt.*;
import javax.swing.event.*;
public class TreeWin extends JFrame implements TreeSelectionListener{
    JTree tree;
    JTextArea showText;
    TreeWin(){
       DefaultMutableTreeNode root=new DefaultMutableTreeNode("��Ʒ");  //���ڵ�
       DefaultMutableTreeNode nodeTV=new DefaultMutableTreeNode("���ӻ���");  //�ڵ�
       DefaultMutableTreeNode nodePhone=new DefaultMutableTreeNode("�ֻ���");  //�ڵ�
       DefaultMutableTreeNode tv1=
       new DefaultMutableTreeNode(new Goods("�������",5699));              //�ڵ�
       DefaultMutableTreeNode tv2=
       new DefaultMutableTreeNode(new Goods("��������",7832));              //�ڵ�
       DefaultMutableTreeNode phone1=
       new DefaultMutableTreeNode(new Goods("ŵ�����ֻ�",3600));            //�ڵ�
       DefaultMutableTreeNode phone2=
       new DefaultMutableTreeNode(new Goods("�����ֻ�",2155));              //�ڵ� 
       root.add(nodeTV);                                            //ȷ���ڵ�֮��Ĺ�ϵ
       root.add(nodePhone); 
       nodeTV.add(tv1);                                        //ȷ���ڵ�֮��Ĺ�ϵ
       nodeTV.add(tv2);
       nodePhone.add(phone1);
       nodePhone.add(phone2);
       tree=new JTree(root);                                   //��root�����������
       tree.addTreeSelectionListener(this);                    //����������ϵ��¼�
       showText=new JTextArea();
       setLayout(new GridLayout(1,2)); 
       add(new JScrollPane(tree));
       add(new JScrollPane(showText));
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       setBounds(80,80,300,300);
       validate();
    }
    public void valueChanged(TreeSelectionEvent e){
       DefaultMutableTreeNode node=
       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
       if(node.isLeaf()){
           Goods s=(Goods)node.getUserObject();                //�õ��ڵ��д�ŵĶ���
           showText.append(s.name+","+s.price+"Ԫ\n");  
       }
       else{
           showText.setText(null);
       }   
    }
}
