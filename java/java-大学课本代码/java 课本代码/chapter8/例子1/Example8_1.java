public class Example8_1 {
  public static void main(String args[]) {
       String s1,s2;
       s1=new String("�������");
       s2=new String("�������");
       System.out.println(s1.equals(s2));     //�������ǣ�true
       System.out.println(s1==s2);            //�������ǣ�false
       String s3,s4; 
       s3="�����޵�";
       s4="�����޵�"; 
       System.out.println(s3.equals(s4));     //�������ǣ�true
       System.out.println(s3==s4);            //�������ǣ�true
    }
}
