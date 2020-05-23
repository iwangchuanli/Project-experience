public class Example9_7 {
   public static void main(String args[]) {
      WindowActionEvent win=new WindowActionEvent();
      PoliceListen police = new PoliceListen(); //创建监视器
      win.setMyCommandListener(police); //窗口组合监视器
      win.setBounds(100,100,460,360);
      win.setTitle("处理ActionEvent事件");
   }
}

