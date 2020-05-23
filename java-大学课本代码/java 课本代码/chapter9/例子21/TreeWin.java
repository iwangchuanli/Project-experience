import javax.swing.*; 
import javax.swing.tree.*;	
import java.awt.*;
import javax.swing.event.*;
public class TreeWin extends JFrame implements TreeSelectionListener{
    JTree tree;
    JTextArea showText;
    TreeWin(){
       DefaultMutableTreeNode root=new DefaultMutableTreeNode("商品");  //根节点
       DefaultMutableTreeNode nodeTV=new DefaultMutableTreeNode("电视机类");  //节点
       DefaultMutableTreeNode nodePhone=new DefaultMutableTreeNode("手机类");  //节点
       DefaultMutableTreeNode tv1=
       new DefaultMutableTreeNode(new Goods("长虹电视",5699));              //节点
       DefaultMutableTreeNode tv2=
       new DefaultMutableTreeNode(new Goods("海尔电视",7832));              //节点
       DefaultMutableTreeNode phone1=
       new DefaultMutableTreeNode(new Goods("诺基亚手机",3600));            //节点
       DefaultMutableTreeNode phone2=
       new DefaultMutableTreeNode(new Goods("三星手机",2155));              //节点 
       root.add(nodeTV);                                            //确定节点之间的关系
       root.add(nodePhone); 
       nodeTV.add(tv1);                                        //确定节点之间的关系
       nodeTV.add(tv2);
       nodePhone.add(phone1);
       nodePhone.add(phone2);
       tree=new JTree(root);                                   //用root做根的树组件
       tree.addTreeSelectionListener(this);                    //监视树组件上的事件
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
           Goods s=(Goods)node.getUserObject();                //得到节点中存放的对象
           showText.append(s.name+","+s.price+"元\n");  
       }
       else{
           showText.setText(null);
       }   
    }
}
