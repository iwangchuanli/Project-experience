/*ѧ����¼������*/
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
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //���ô��ڵĹر�ģʽ
    setLocationByPlatform(true);                                
    setSize(800, 600);
    setTitle("ѧ���ɼ�����ϵͳ");
    JMenuBar menuBar = createMenu();                            // ���ô����˵����ķ���
    setJMenuBar(menuBar);                                       //���˵������봰��
    JToolBar toolBar = createToolBar();                    // ���ô����������ķ���
    getContentPane().add(toolBar, BorderLayout.NORTH); //�����������̶����ּ��봰��
    DESKTOP_PANE.setBackground(Color.DARK_GRAY);          //����DESKTOP_PANE�ı���ɫ
    getContentPane().add(DESKTOP_PANE );                //��DESKTOP_PANE���봰��
    setVisible(true);
   }
   /**
   * ����������
   * 
   * @return JToolBar
   */
   private JToolBar createToolBar() {                    // �����������ķ���
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
   
    JButton infomationManageButton=new JButton("������Ϣ����");        //������Ϣ����ť
    ImageIcon icon=new ImageIcon("res/gongjulan5.jpg");//����ͼ�귽�� 
    infomationManageButton.setIcon(icon);
    infomationManageButton.setHideActionText(true);
    infomationManageButton.addActionListener(new ActionListener(){      //Ϊѧ�������Ϣ�����߰�ť��Ӽ�����
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new StudentQueryInfoIFrame());
         }});
    toolBar.add(infomationManageButton);
   
    JButton queryScoresByYearButton=new JButton("ѧ��ɼ���ѯ");     //ѧ��ɼ���ѯ��ť
    ImageIcon queryScoresIcon1=new ImageIcon("res/gongjulan1.jpg");//����ͼ�귽��
    queryScoresByYearButton.setIcon(queryScoresIcon1);
    queryScoresByYearButton.setHideActionText(true);
    queryScoresByYearButton.addActionListener(new ActionListener(){   //Ϊѧ��ɼ���ѯ���߰�ť��Ӽ�����
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new QueryScoreByYearIFrame());
         }});
    toolBar.add(queryScoresByYearButton);
   
   
    JButton queryScoresByTermButton=new JButton("ѧ�ڳɼ���ѯ");    //ѧ�ڳɼ���ѯ��ť
    ImageIcon queryScoresIcon2=new ImageIcon("res/gongjulan3.jpg");//����ͼ�귽��
    queryScoresByTermButton.setIcon(queryScoresIcon2);
    queryScoresByTermButton.setHideActionText(true);
    queryScoresByTermButton.addActionListener(new ActionListener(){ //Ϊѧ�ڳɼ���ѯ���߰�ť��Ӽ�����
     public void actionPerformed(ActionEvent arg0) {
      TeacherMainFrame.addIFame(new QueryScoreByTermIFrame());
         }});
    toolBar.add(queryScoresByTermButton);
   
   
    JButton queryScoresByAllButton=new JButton("�ۺϳɼ���ѯ");         //�ܳɼ���ѯ��ť
    ImageIcon queryScoresIcon3=new ImageIcon("res/gongjulan2.jpg");//����ͼ�귽��
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
   * �����˵���
   */
   private JMenuBar createMenu() { // �����˵����ķ���
    JMenuBar menuBar = new JMenuBar();
    JMenu jibenxinxiMenu = new JMenu();// ��ʼ��������Ϣ�˵�
    jibenxinxiMenu.setIcon(new ImageIcon("res/jibenxinxi.jpg"));
    {
     JMenuItem queryInfoMenuItem = new JMenuItem("������Ϣ��ѯ");
     queryInfoMenuItem.addActionListener(new ActionListener(){         //Ϊѧ�������Ϣ��ѯ�˵��ť��Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentQueryInfoIFrame());
          }});
     JMenuItem modifyInfoMenuItem = new JMenuItem ("������Ϣά��");
     modifyInfoMenuItem.addActionListener(new ActionListener(){         //Ϊѧ�������Ϣ�޸Ĳ˵�����Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentModifyInfoIFrame());
          }});
     JMenuItem modifiPasswordMenuItem = new JMenuItem ("�޸�����");
     modifiPasswordMenuItem.addActionListener(new ActionListener(){         //Ϊ�����޸Ĳ˵�����Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new StudentModifyPasswordIFrame());
          }});
     jibenxinxiMenu.add(queryInfoMenuItem);
     jibenxinxiMenu.add(modifyInfoMenuItem);
     jibenxinxiMenu.add(modifiPasswordMenuItem);
     jibenxinxiMenu.addSeparator();
    }
    JMenu queryScoreMenu = new JMenu(); // ��ʼ���ɼ���ѯ�˵�
    queryScoreMenu.setIcon(new ImageIcon("res/chengjichaxun.jpg"));
    {
     JMenuItem queryByYearMItem = new JMenuItem("ѧ��ɼ���ѯ");
     queryByYearMItem.addActionListener(new ActionListener(){         //Ϊѧ��ɼ���ѯ�˵�����Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByYearIFrame());
          }});
  
     JMenuItem queryByTermMItem = new JMenuItem("ѧ�ڳɼ���ѯ");
     queryByTermMItem.addActionListener(new ActionListener(){         //Ϊѧ�ڳɼ���ѯ�˵�����Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByTermIFrame());
          }});
   
     JMenuItem queryByAllMItem = new JMenuItem("�ۺϳɼ���ѯ");
     queryByAllMItem.addActionListener(new ActionListener(){         //Ϊ�ۺϳɼ���ѯ�˵�����Ӽ�����
      public void actionPerformed(ActionEvent arg0) {
       TeacherMainFrame.addIFame(new QueryScoreByAllIFrame()); 
          }});
   
     queryScoreMenu.add(queryByYearMItem);
     queryScoreMenu.add(queryByTermMItem);
     queryScoreMenu.add(queryByAllMItem);
     queryScoreMenu.addSeparator();
    
       menuBar.add(jibenxinxiMenu); // ��ӻ�����Ϣ�˵����˵���
       menuBar.add(queryScoreMenu); // ��ӳɼ�����˵����˵���
        return menuBar;
      }
     }/*Method createMenu*/
    
   public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
   
    DESKTOP_PANE.add(iframe);
   }
}
