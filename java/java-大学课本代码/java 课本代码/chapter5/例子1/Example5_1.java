public class Example5_1 {
   public static void main(String args[]) {
      Student zhang = new Student();
      zhang.age = 17;           //���ʼ̳еĳ�Ա����
      zhang.number=100101;
      zhang.showPeopleMess();  //���ü̳еķ���
      zhang.tellNumber();        
      int x=9,y=29;
      System.out.print("�����ӷ�:");
      int result=zhang.add(x,y);
      System.out.printf("%d+%d=%d\n",x,y,result);
      UniverStudent geng = new UniverStudent();
      geng.age = 21;          //���ʼ̳еĳ�Ա����
      geng.number=6609;
      geng.showPeopleMess();  //���ü̳еķ���
      geng.tellNumber();        //���ü̳еķ���
      System.out.print("�����ӷ�:");
      result=geng.add(x,y);      //���ü̳еķ���
      System.out.printf("%d+%d=%d\t",x,y,result);
      System.out.print("�����˷�:");
      result=geng.multi(x,y);
      System.out.printf("%d��%d=%d\n",x,y,result); 
   }
} 

