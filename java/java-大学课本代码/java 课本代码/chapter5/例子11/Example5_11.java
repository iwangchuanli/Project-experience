class  ���� {
   void cry() {
   }  
}
class �� extends ���� {
   void cry() {
      System.out.println("����....."); 
   }  
}
class è extends ����  {
   void cry() {
      System.out.println("����....."); 
   }  
}
public class Example5_11 { 
   public static void main(String args[]) {
      ���� animal;
      animal = new ��();
      animal.cry(); 
      animal=new è();
      animal.cry();
  }
}
