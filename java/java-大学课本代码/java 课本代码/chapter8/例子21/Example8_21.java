import java.util.regex.*;
public class Example8_21 {
   public static void main(String args[ ]) { 
      Pattern p;          //ģʽ����
      Matcher m;         //ƥ�����
      String regex="(http://|www)\56?\\w+\56{1}\\w+\56{1}\\p{Alpha}+";
      p=Pattern.compile(regex);  //���Ի�ģʽ����
      String s="����:www.sina.cn,����:http://www.cctv.com";   
      m=p.matcher(s);  //�õ�����s��ƥ�����m
      while(m.find()) {
         String str=m.group();
         System.out.println(str);
      } 
      System.out.println("�޳��ַ����е���վ��ַ��õ����ַ���:");
      String result=m.replaceAll("");
      System.out.println(result);
   }
}

