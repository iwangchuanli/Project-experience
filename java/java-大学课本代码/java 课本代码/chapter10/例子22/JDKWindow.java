import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class JDKWindow extends JFrame { 
   JTextField javaSourceFileName; //输入Java源文件
   JTextField javaMainClassName;  //输入主类名
   JButton compile,run,edit;
   HandleActionEvent listener;
   public JDKWindow(){
      edit = new JButton("用记事本编辑源文件");
      compile = new JButton("编译");
      run = new JButton("运行");
      javaSourceFileName = new JTextField(10);
      javaMainClassName = new JTextField(10);
      setLayout(new FlowLayout());
      add(edit);
      add(new JLabel("输入源文件名:"));
      add(javaSourceFileName);
      add(compile);
      add(new JLabel("输入主类名:"));
      add(javaMainClassName);
      add(run);
      listener = new HandleActionEvent();
      edit.addActionListener(listener);
      compile.addActionListener(listener);
      run.addActionListener(listener); 
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100,100,750,180); 
   }   
   class  HandleActionEvent implements ActionListener { //内部类实例做监视器
      public void actionPerformed(ActionEvent e) {
         if(e.getSource()==edit) {
           Runtime ce=Runtime.getRuntime();
           String name = javaSourceFileName.getText();
           File file=new File("c:/windows","Notepad.exe "+name);
           try{
               ce.exec(file.getAbsolutePath());
           }
           catch(Exception exp){}
         }
         else if(e.getSource()==compile) {
            CompileDialog compileDialog = new CompileDialog();
            String name = javaSourceFileName.getText();
            compileDialog.compile(name);
            compileDialog.setVisible(true);
         }
         else if(e.getSource()==run) {
            RunDialog runDialog =new RunDialog();
            String name = javaMainClassName.getText();
            runDialog.run(name);
            runDialog.setVisible(true);
         }
      }
   }
}
