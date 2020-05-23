import java.util.regex.*;
public class Example8_21 {
   public static void main(String args[ ]) { 
      Pattern p;          //模式对象
      Matcher m;         //匹配对象
      String regex="(http://|www)\56?\\w+\56{1}\\w+\56{1}\\p{Alpha}+";
      p=Pattern.compile(regex);  //初试化模式对象
      String s="新浪:www.sina.cn,央视:http://www.cctv.com";   
      m=p.matcher(s);  //得到检索s的匹配对象m
      while(m.find()) {
         String str=m.group();
         System.out.println(str);
      } 
      System.out.println("剔除字符串中的网站地址后得到的字符串:");
      String result=m.replaceAll("");
      System.out.println(result);
   }
}

