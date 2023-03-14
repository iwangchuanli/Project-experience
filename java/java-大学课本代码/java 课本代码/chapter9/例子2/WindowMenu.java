import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
public class WindowMenu extends JFrame {
   JMenuBar menubar;
    JMenu menu,subMenu;
    JMenuItem  itemLiterature,itemCooking;
    public WindowMenu(){} 
    public WindowMenu(String s,int x,int y,int w,int h) {
       init(s);
       setLocation(x,y);
       setSize(w,h);
       setVisible(true);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
    void init(String s){
       setTitle(s);             //设置窗口的标题   
       menubar=new JMenuBar(); 
       menu=new JMenu("菜单"); 
       subMenu=new JMenu("体育话题");  
       itemLiterature=new JMenuItem("文学话题",new ImageIcon("a.gif"));
       itemCooking=new JMenuItem("烹饪话题",new ImageIcon("b.gif"));
       itemLiterature.setAccelerator(KeyStroke.getKeyStroke('A')); 
       itemCooking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK)); 
       menu.add(itemLiterature); 
       menu.addSeparator();   //在菜单之间增加分隔线
       menu.add(itemCooking); 
       menu.add(subMenu);  
       subMenu.add(new JMenuItem("足球",new ImageIcon("c.gif"))); 
       subMenu.add(new JMenuItem("篮球",new ImageIcon("d.gif")));
       menubar.add(menu); 
       setJMenuBar(menubar);
    } 
}
