import java.awt.*;
import javax.swing.*;
public class ComponentInWindow extends JFrame { 
   JTextField text;
   JButton button;
   JCheckBox checkBox1,checkBox2,checkBox3;
   JRadioButton radio1,radio2;
   ButtonGroup group;
   JComboBox comBox; 
   JTextArea area;
   public ComponentInWindow() {
      init();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void init() {
      setLayout(new FlowLayout());
      add(new JLabel("文本框:"));
      text=new JTextField(10); 
      add(text);
      add(new JLabel("按钮:"));
      button=new JButton("确定"); 
      add(button);
      add(new JLabel("选择框:"));
      checkBox1 = new JCheckBox("喜欢音乐"); 
      checkBox2 = new JCheckBox("喜欢旅游");
      checkBox3 = new JCheckBox("喜欢篮球");  
      add(checkBox1);
      add(checkBox2);
      add(checkBox3);
      add(new JLabel("单选按钮:"));
      group = new ButtonGroup(); 
      radio1 = new JRadioButton("男"); 
      radio2 = new JRadioButton("女");
      group.add(radio1); 
      group.add(radio2); 
      add(radio1);
      add(radio2);
      add(new JLabel("下拉列表:"));
      comBox = new JComboBox();
      comBox.addItem("音乐天地"); 
      comBox.addItem("武术天地");
      comBox.addItem("象棋乐园"); 
      add(comBox);
      add(new JLabel("文本区:"));
      area = new JTextArea(6,12);
      add(new JScrollPane(area));
   }
}
