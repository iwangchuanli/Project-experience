abstract class A {
   abstract int add(int x,int y);
   int sub(int x,int y) { 
      return x-y;
   }
}
class B extends A {
   int add(int x,int y) {   //���������д�����add����
      return x+y;
   }
}
public class Example5_12 {
   public static void main(String args[]) {
      B b=new B();
      int sum=b.add(30,20);            //����������д��add����
      int sub=b.sub(30,20);            //��������̳е�sub����
      System.out.println("sum="+sum);  //������Ϊsum=50
      System.out.println("sum="+sub);  //������Ϊsum=10
      A a;                             //��������������
      a = b;                           //a��b����ת�Ͷ���
      sum=a.add(30,20);                //����������д�ķ���
      sub=a.sub(30,20);                //��������̳еķ���
      System.out.println("sum="+sum);  //������Ϊsum=50
      System.out.println("sum="+sub);  //������Ϊsum=10
   }
}


