interface  ShowMessage {
   void 显示商标(String s);
}
class TV implements ShowMessage {
   public void 显示商标(String s) {
      System.out.println(s);
   }
}
class PC implements ShowMessage {
   public void 显示商标(String s) { 
     System.out.println(s);
   }
}
public class Example6_3 {
   public static void main(String args[]) {
      ShowMessage sm;                  //声明接口变量
      sm=new TV();                     //接口变量中存放对象的引用
      sm.显示商标("长城牌电视机");      //接口回调。
      sm=new PC();                     //接口变量中存放对象的引用
      sm.显示商标("联想奔月5008PC机"); //接口回调
   } 
}
