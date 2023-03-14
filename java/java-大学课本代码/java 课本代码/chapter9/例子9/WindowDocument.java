import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
public class WindowDocument extends JFrame { 
   JTextArea inputText,showText; 
   JMenuBar menubar;
   JMenu menu;
   JMenuItem  itemCopy,itemCut,itemPaste; 
   TextListener textChangeListener;  //inputText�ļ�����
   HandleListener handleListener;  //itemCopy,itemCut,itemPaste�ļ�����
   WindowDocument() { 
      init();
      setLayout(new FlowLayout());
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
   }
   void init() {
      inputText = new JTextArea(16,28);
      showText = new JTextArea(16,28);
      showText.setLineWrap(true);       //�ı��Զ�����
      showText.setWrapStyleWord(true);  //�ı����Ե���Ϊ���Զ�����
      menubar=new JMenuBar(); 
      menu=new JMenu("�༭");   
      itemCopy=new JMenuItem("����(C)");
      itemCut=new JMenuItem("����(T)");
      itemPaste=new JMenuItem("ճ��(P)");
      itemCopy.setAccelerator(KeyStroke.getKeyStroke('c'));//���ÿ�ݷ�ʽ
      itemCut.setAccelerator(KeyStroke.getKeyStroke('t'));
      itemPaste.setAccelerator(KeyStroke.getKeyStroke('p'));//���ÿ�ݷ�ʽ
      itemCopy.setActionCommand("copy");
      itemCut.setActionCommand("cut");
      itemPaste.setActionCommand("paste");
      menu.add(itemCopy);
      menu.add(itemCut);
      menu.add(itemPaste);
      menubar.add(menu);
      setJMenuBar(menubar);
      add(new JScrollPane(inputText));
      add(new JScrollPane(showText));
      textChangeListener = new TextListener();
      handleListener = new HandleListener(); 
      textChangeListener.setInputText(inputText);
      textChangeListener.setShowText(showText);  
      handleListener.setInputText(inputText);
      handleListener.setShowText(showText);  
      (inputText.getDocument()).addDocumentListener(textChangeListener);//���ĵ�ע�������
      itemCopy.addActionListener(handleListener);                       //��˵���ע�������
      itemCut.addActionListener(handleListener);
      itemPaste.addActionListener(handleListener);
   }
}
