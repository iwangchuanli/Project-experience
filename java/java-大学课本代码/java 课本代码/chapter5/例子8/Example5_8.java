class Student {
   int number;String name;
   Student() {
   }
   Student(int number,String name) {
      this.number=number;
      this.name=name;
      System.out.println("�ҵ�������:"+name+ "ѧ����:"+number);
   }
 }
class UniverStudent extends Student {
   boolean ���;
   UniverStudent(int number,String name,boolean b) {
      super(number,name);
      ���=b;
      System.out.println("���="+���);
   }
}
public class Example5_8 {
   public static void main(String args[]) {
      UniverStudent zhang=new UniverStudent(9901,"������",false);
   }
}


