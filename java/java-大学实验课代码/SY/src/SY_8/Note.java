package SY_8;

/*
 *  记事本界面
 * */
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Note  extends JFrame
{   JMenuBar jmb;//菜单条组件
    JMenu men1,men2,men3,men4,men5;
    JMenuItem item1,item2,item3,item4,item5,item6;
    JMenuItem item7,item8,item9,item10,item11;
    JMenuItem item12,item13,item14,item15,item16,item17;
    JMenuItem item18,item19,item20,item21,item22;
    JMenuItem field,project;//二级菜单
    JTextArea jta1; 
    JScrollPane  js1;
    JToolBar jtb;//工具条
    JButton jb1,jb2,jb3,jb4,jb5,jb6;
public static void main(String[] args) 
{
Note domel=new Note();
}
Note()
   {  
  //创建组件
  jtb=new JToolBar();
  jb1=new JButton("新建文件");
  jb1.setToolTipText("新建");//设置提示信息
  jb2=new JButton("打开文件");
  jb2.setToolTipText("打开");//设置提示信息
  jb3=new JButton("保存文件");
  jb3.setToolTipText("保存");//设置提示信息
  jb4=new JButton("剪切文件");
  jb4.setToolTipText("剪切");//设置提示信息
  jb5=new JButton("打印文件");
  jb5.setToolTipText("打印");//设置提示信息
  jb6=new JButton("删除文件");
  jb6.setToolTipText("删除");//设置提示信息
  jmb=new JMenuBar();
  men1=new JMenu("文件(F)");
  men1.setMnemonic('F');//设置标记符
  men2=new JMenu("编辑(E)");
  men2.setMnemonic('E');//设置标记符
  men3=new JMenu("格式(O)");
  men3.setMnemonic('O');//设置标记符
  men4=new JMenu("查看(V)");
  men4.setMnemonic('V');//设置标记符
  men5=new JMenu("帮助(V)");
  men5.setMnemonic('V');//设置标记符
  item1=new JMenu("新建(N)");
  item1.setMnemonic('N');
  field=new JMenuItem("文件");
  field.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK,false));
  project=new JMenuItem("工程");
  project.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J        ,InputEvent.CTRL_MASK,false));
  item2=new JMenuItem("打开(O)..."); 
  item2.setMnemonic('O');
  //给菜单添加快捷方式
  item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK,false));
  item3=new JMenuItem("保存(S)"); 
  item3.setMnemonic('S');
       item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,false));
  item4=new JMenuItem("另存为(A)..."); 
  item4.setMnemonic('A');
  item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK,false));
  item5=new JMenuItem("页面设置(U)..."); 
  item5.setMnemonic('U');
  item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK,false));
  item6=new JMenuItem("退出（X）"); 
  item6.setMnemonic('X');
  item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK,false));
  item7=new JMenuItem("撤销(U)..."); 
  item7.setMnemonic('U');
  item7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK,false));
  item8=new JMenuItem("剪切（X）"); 
  item8.setMnemonic('X');
  item8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK,false));
  item9=new JMenuItem("复制(C)"); 
  item9.setMnemonic('C');
       item9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK,false));
  item10=new JMenuItem("粘贴(V)..."); 
  item10.setMnemonic('V');
  item10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK,false));
  item11=new JMenuItem("删除（D）"); 
  item11.setMnemonic('D');
  item11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK,false));
  item12=new JMenuItem("打印(F)..."); 
  item12.setMnemonic('F');
  item12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK,false));
  item13=new JMenuItem("查找下一个(N)"); 
  item13.setMnemonic('N');
  item13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,InputEvent.CTRL_MASK,false));
  item14=new JMenuItem("替换（R）"); 
  item14.setMnemonic('R');
       item14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK,false));
  item15=new JMenuItem("转到（R）"); 
  item15.setMnemonic('R');
  item15.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK,false));
  item16=new JMenuItem("全选（A）"); 
  item16.setMnemonic('A');
  item16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK,false));
  item17=new JMenuItem("时间/日期（D）"); 
  item17.setMnemonic('D');
  item17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,InputEvent.CTRL_MASK,false));
  item18=new JMenuItem("自动换行（W）"); 
  item18.setMnemonic('W');
  item18.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK,false));
  item19=new JMenuItem("字体(F)..."); 
  item19.setMnemonic('F');
       item19.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK,false));
  item20=new JMenuItem("状态栏(S)..."); 
  item20.setMnemonic('S');
  item20.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,false));
  item21=new JMenuItem("查看帮助（H）"); 
  item21.setMnemonic('H');
  item21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK,false));
  item22=new JMenuItem("关于记事本(A)"); 
  item22.setMnemonic('A');
  item22.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK,false));
  jta1=new JTextArea(); 
  //设置布局
  jtb.add(jb1);
  jtb.add(jb2);
  jtb.add(jb3);
  jtb.add(jb4);
  jtb.add(jb5);
  jtb.add(jb6);
       item1.add(field);
       item1.add(project);
       men1.add(item1);
       men1.add(item2);
       men1.add(item3);
       men1.add(item4);
       men1.addSeparator();//添加分割线
       men1.add(item5);
       men1.add(item6);
       men2.add(item7); 
       men2.addSeparator();
       men2.add(item8);
       men2.add(item9);
       men2.add(item10);
       men2.add(item11);
       men2.addSeparator(); //添加分割线
       men2.add(item12);
       men2.add(item13);
       men2.add(item14);
       men2.add(item15);
       men2.addSeparator(); //添加分割线
       men2.add(item16);
       men2.add(item17);
       men3.add(item18);
       men3.add(item19);
       men4.add(item20);
       men5.add(item21);
       men5.add(item22);
       jmb.add(men1);
       jmb.add(men2);
       jmb.add(men3);
       jmb.add(men4);
       jmb.add(men5);
       this.setJMenuBar(jmb);//将菜单条添加到窗口
       this.add(jtb,BorderLayout.NORTH);// 将工具条添加到窗口
       js1=new JScrollPane(jta1);
       js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       this.add(js1);
       this.setIconImage((new ImageIcon("imagejs.jpg")).getImage());
       this.setTitle("记事本");
       this.setSize(500,400);
       this.setLocation(400,300);
       //this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
   }
}