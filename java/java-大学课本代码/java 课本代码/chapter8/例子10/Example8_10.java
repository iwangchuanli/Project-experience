public class Example8_10 {
   public static void main (String args[ ]) {
      String str = "��ӭ��ҷ���http://www.xiaojiang.cn�˽⡢�ι۹�˾";
      String regex = "(http://|www)\56?\\w+\56{1}\\w+\56{1}\\p{Alpha}+";
      System.out.printf("�滻\n\"%s\"\n�е���վ������Ϣ��õ����ַ���:\n",str);
      str = str.replaceAll(regex,"******");
      System.out.println(str);
      String money = "89,235,678��";
      System.out.print(money+"ת��������:"); 
      String s = money.replaceAll("[,\\p{Sc}]","") ; //"\\p{Sc}"��ƥ���κλ��ҷ���
      long  number = Long.parseLong(s);
      System.out.println(number); 
   }
}

