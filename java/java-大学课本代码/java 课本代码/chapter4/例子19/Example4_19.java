public class Example4_19 {
   public static void main(String args[]) {
      Student zhang=new Student(); 
      Student geng=new Student();  
      zhang.setAge(23); 
      System.out.println("zhang�����䣺"+zhang.getAge()); 
      geng.setAge(25);
      //zhang.age=23;��geng.age=25;���ǷǷ��ģ���Ϊzhang��geng�Ѿ�����Student���� 
      System.out.println("geng�����䣺"+geng.getAge());  
    }
}




