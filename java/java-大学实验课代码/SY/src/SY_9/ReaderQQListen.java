package SY_9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReaderQQListen implements ActionListener { 
   public void actionPerformed(ActionEvent e) {
      String str=e.getActionCommand();   //获取封装在事件中的“命令”字符串
      if (str.length()==0) {
    	  System.out.println("昵称不能为空！");
	}

   }
}