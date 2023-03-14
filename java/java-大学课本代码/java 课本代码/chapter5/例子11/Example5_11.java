class  动物 {
   void cry() {
   }  
}
class 狗 extends 动物 {
   void cry() {
      System.out.println("汪汪....."); 
   }  
}
class 猫 extends 动物  {
   void cry() {
      System.out.println("喵喵....."); 
   }  
}
public class Example5_11 { 
   public static void main(String args[]) {
      动物 animal;
      animal = new 狗();
      animal.cry(); 
      animal=new 猫();
      animal.cry();
  }
}
