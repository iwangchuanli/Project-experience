public class Example8_3 {
   public static void main(String args[]) {
      String path="c:\\book\\javabook\\Java Programmer.doc";
      int index=path.indexOf("\\");
      index=path.indexOf("\\",index); 
      String sub=path.substring(index);
      System.out.println(sub);   //�������ǣ�\book\javabook\Java Programmer.doc
      index=path.lastIndexOf("\\");
      sub=path.substring(index+1);
      System.out.println(sub);   //�������ǣ�Java Programmer.doc
      System.out.println(sub.contains("Programmer"));//�������ǣ�true
   }
}
