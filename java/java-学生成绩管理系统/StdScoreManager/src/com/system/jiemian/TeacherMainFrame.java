/*学生登录主界面*/
package com.system.jiemian;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
public class TeacherMainFrame extends JFrame{

   private static final long serialVersionUID = 1696099952059929396L;
   private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
  
   public TeacherMainFrame() {
    super();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //设置窗口的关闭模式
    setLocationByPlatform(true);                                
    setSize(800, 600);
    setTitle("学生成绩管理系统");
    JMenuBar menuBar = createMenu();                            // 调用创建菜单栏的方法
    setJMenuBar(menuBar);                                       //将菜单栏加入窗口
    JToolBar toolBar = createToolBar();                    // 调用创建工具栏的方法
    getContentPane().add(toolBar, BorderLayout.NORTH); //将工具栏按固定布局加入窗口
    DESKTOP_PANE.setBackground(Color.DARK_GRAY);          //设置DESKTOP_PANE的背景色
    getContentPane().add(DESKTOP_PANE );                //将DESKTOP_PANE加入窗口
    setVisible(true);
   }
   /**
   * 创建工具栏
   * 
   * @return JToolBar
   */
   private JToolBar createToolBar() {                    // 创建工具栏的方法
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
   
    JButton infomationManageButton=new JButton("个人信息管理");        //个人信息管理按钮
    ImageIcon icon=new ImageIcon("res/gongjulan5.jpg");//创建图标方法 
    infomationManageButton.setIcon(icon);
    infomationManageButton.setHideActionText(true);
    infomationManageButton.addActionListener(new ActionListener(){      //为学年个人信息管理工具按钮添加监听器
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new StudentQueryInfoIFrame());
         }});
    toolBar.add(infomationManageButton);
   
    JButton queryScoresByYearButton=new JButton("学年成绩查询");     //学年成绩查询按钮
    ImageIcon queryScoresIcon1=new ImageIcon("res/gongjulan1.jpg");//创建图标方法
    queryScoresByYearButton.setIcon(queryScoresIcon1);
    queryScoresByYearButton.setHideActionText(true);
    queryScoresByYearButton.addActionListener(new ActionListener(){   //为学年成绩查询工具按钮添加监听器
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new QueryScoreByYearIFrame());
         }});
    toolBar.add(queryScoresByYearButton);
   
   
    JButton queryScoresByTermButton=new JButton("学期成绩查询");    //学期成绩查询按钮
    ImageIcon queryScoresIcon2=new ImageIcon("res/gongjulan3.jpg");//创建图标方法
    queryScoresByTermButton.setIcon(queryScoresIcon2);
    queryScoresByTermButton.setHideActionText(true);
    queryScoresByTermButton.addActionListener(new ActionListener(){ //为学期成绩查询工具按钮添加监听器
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new QueryScoreByTermIFrame());
         }});
    toolBar.add(queryScoresByTermButton);
   
   
    JButton queryScoresByAllButton=new JButton("综合成绩查询");         //总成绩查询按钮
    ImageIcon queryScoresIcon3=new ImageIcon("res/gongjulan2.jpg");//创建图标方法
    queryScoresByAllButton.setIcon(queryScoresIcon3);
    queryScoresByAllButton.setHideActionText(true);
    queryScoresByAllButton.addActionListener(new ActionListener(){         
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByAllIFrame());   
          }});
    toolBar.add( queryScoresByAllButton);
   
    return toolBar; 
   }/*Method createToolBar*/
   /**
   * 创建菜单栏
   */
   private JMenuBar createMenu() { // 创建菜单栏的方法
    JMenuBar menuBar = new JMenuBar();
    JMenu jibenxinxiMenu = new JMenu();// 初始化基本信息菜单
    jibenxinxiMenu.setIcon(new ImageIcon("res/jibenxinxi.jpg"));
    {
     JMenuItem queryInfoMenuItem = new JMenuItem("个人信息查询");
     queryInfoMenuItem.addActionListener(new ActionListener(){         //为学年个人信息查询菜单项按钮添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentQueryInfoIFrame());
          }});
     JMenuItem modifyInfoMenuItem = new JMenuItem ("个人信息维护");
     modifyInfoMenuItem.addActionListener(new ActionListener(){         //为学年个人信息修改菜单项添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentModifyInfoIFrame());
          }});
     JMenuItem modifiPasswordMenuItem = new JMenuItem ("修改密码");
     modifiPasswordMenuItem.addActionListener(new ActionListener(){         //为密码修改菜单项添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentModifyPasswordIFrame());
          }});
     jibenxinxiMenu.add(queryInfoMenuItem);
     jibenxinxiMenu.add(modifyInfoMenuItem);
     jibenxinxiMenu.add(modifiPasswordMenuItem);
     jibenxinxiMenu.addSeparator();
    }
    JMenu queryScoreMenu = new JMenu(); // 初始化成绩查询菜单
    queryScoreMenu.setIcon(new ImageIcon("res/chengjichaxun.jpg"));
    {
     JMenuItem queryByYearMItem = new JMenuItem("学年成绩查询");
     queryByYearMItem.addActionListener(new ActionListener(){         //为学年成绩查询菜单项添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByYearIFrame());
          }});
  
     JMenuItem queryByTermMItem = new JMenuItem("学期成绩查询");
     queryByTermMItem.addActionListener(new ActionListener(){         //为学期成绩查询菜单项添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByTermIFrame());
          }});
   
     JMenuItem queryByAllMItem = new JMenuItem("综合成绩查询");
     queryByAllMItem.addActionListener(new ActionListener(){         //为综合成绩查询菜单项添加监听器
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByAllIFrame()); 
          }});
   
     queryScoreMenu.add(queryByYearMItem);
     queryScoreMenu.add(queryByTermMItem);
     queryScoreMenu.add(queryByAllMItem);
     queryScoreMenu.addSeparator();
    
       menuBar.add(jibenxinxiMenu); // 添加基本信息菜单到菜单栏
       menuBar.add(queryScoreMenu); // 添加成绩管理菜单到菜单栏
        return menuBar;
      }
     }/*Method createMenu*/
    
   public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
   
    DESKTOP_PANE.add(iframe);
   }
}
