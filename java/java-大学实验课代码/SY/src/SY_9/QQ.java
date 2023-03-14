package SY_9;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class QQ extends JFrame {
 JTextField username;    // 用户名
  JPasswordField password;   // 密码
  
    // 小容器
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
 
    // 小按钮
    private JButton bu1;
    private JButton bu2;
    private JButton bu3;
    // 复选框
    private JCheckBox jc1;
    private JCheckBox jc2;
    // 列表框
    private JComboBox jcb;
 
    public QQ() {
        this.setTitle("QQ2017");
        init();// 窗体组件初始化
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        this.setLayout(null);    // 设置布局方式为绝对定位
         
        this.setBounds(0, 0, 355, 265);
        // 设置窗体的标题图标
        Image image = new ImageIcon("e:/a.gif").getImage();
        this.setIconImage(image);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体可见
        this.setVisible(true);
    }
 
    /*
     * 初始化方法0
     */
    public void init() {
        // 创建一个容器
        Container con = this.getContentPane();
        jl1 = new JLabel();
        // 设置背景图片
        Image image1 = new ImageIcon("background.jpg").getImage();
        jl1.setIcon(new ImageIcon(image1));
        jl1.setBounds(0, 0, 355, 265);       
        // QQ登录头像设定
        jl2 = new JLabel();
        Image image2 = new ImageIcon("e:a.gif").getImage();
        jl2.setIcon(new ImageIcon(image2));
        jl2.setBounds(40, 95, 50, 60);
        // 用户号码登录输入框
        username = new JTextField();
        username.setBounds(100, 100, 150, 20);
        // 用户号码登录输入框旁边的文字
        jl3 = new JLabel("注册账号");
        jl3.setBounds(260, 100, 70, 20);
        // 密码输入框
        password = new JPasswordField();
        password.setBounds(100, 130, 150, 20);
        // 密码输入框旁边的文字
        jl4 = new JLabel("找回密码");
        jl4.setBounds(260, 130, 70, 20);
        // 输入框下方文字
        jc1 = new JCheckBox("记住密码");
        jc1.setBounds(105, 155, 80, 15);
        jc2 = new JCheckBox("自动登录");
        jc2.setBounds(185, 155, 80, 15);
        // 用户登录状态选择
        jcb = new JComboBox();
        jcb.addItem("在线");
        jcb.addItem("隐身");
        jcb.addItem("离开");
        jcb.setBounds(40, 150, 55, 20);
        // 按钮设定
        bu1 = new JButton("登录");
        bu1.setBounds(280, 200, 65, 20);
        // 给按钮添加1个事件
        bu1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if("登录".equals(str)){
                    String getName =username.getText();
//                  String getPwd =password.getText();
                     JOptionPane.showConfirmDialog(null, "您输入的用户名是"+getName);
                }
                 
            }
        });

        bu2 = new JButton("多账号");
        bu2.setBounds(5, 200, 75, 20);
        bu3 = new JButton("设置");
        bu3.setBounds(100, 200, 65, 20);
 
        // 所有组件用容器装载
        jl1.add(jl2);
        jl1.add(jl3);
        jl1.add(jl4);
        jl1.add(jc1);
        jl1.add(jc2);
        jl1.add(jcb);
        jl1.add(bu1);
        jl1.add(bu2);
        jl1.add(bu3);
        con.add(jl1);
        con.add(username);
        con.add(password);
 
    }
 
    public static void main(String[] args) {
        // 实例化对象
        QQ qq = new QQ();
    }
 
}