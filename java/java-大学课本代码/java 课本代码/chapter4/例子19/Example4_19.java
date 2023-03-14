public class Example4_19 {
   public static void main(String args[]) {
      Student zhang=new Student(); 
      Student geng=new Student();  
      zhang.setAge(23); 
      System.out.println("zhang的年龄："+zhang.getAge()); 
      geng.setAge(25);
      //zhang.age=23;或geng.age=25;都是非法的，因为zhang和geng已经不在Student类中 
      System.out.println("geng的年龄："+geng.getAge());  
    }
}




