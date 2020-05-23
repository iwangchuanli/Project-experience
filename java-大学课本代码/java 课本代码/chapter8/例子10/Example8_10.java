public class Example8_10 {
   public static void main (String args[ ]) {
      String str = "欢迎大家访问http://www.xiaojiang.cn了解、参观公司";
      String regex = "(http://|www)\56?\\w+\56{1}\\w+\56{1}\\p{Alpha}+";
      System.out.printf("替换\n\"%s\"\n中的网站链接信息后得到的字符串:\n",str);
      str = str.replaceAll(regex,"******");
      System.out.println(str);
      String money = "89,235,678￥";
      System.out.print(money+"转化成数字:"); 
      String s = money.replaceAll("[,\\p{Sc}]","") ; //"\\p{Sc}"可匹配任何货币符号
      long  number = Long.parseLong(s);
      System.out.println(number); 
   }
}

