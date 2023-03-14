class Student{
   int number;
}
public class Example4_21 { 
   public static void main(String args[ ]) { 
      Student stu[] = new Student[10];  //创建对象数组
      for(int i=0;i<stu.length;i++) { 
        stu[i]=new Student();     //创建Student对象
        stu[i].number = 101+i;
      }
      for(int i=0;i<stu.length;i++) { 
         System.out.println(stu[i].number);
      }
   } 
}




