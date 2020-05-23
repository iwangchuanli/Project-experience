public class Example7_4 {
   public static void main(String args[ ]) {
      int n = 0,m = 0,t = 1000;
      try{  m = Integer.parseInt("8888");
            n = Integer.parseInt("ab89"); //发生异常,转向catch
            t = 7777;  //t没有机会被赋值
      }
      catch(NumberFormatException e) {
            System.out.println("发生异常:"+e.getMessage());
      }
      System.out.println("n="+n+",m="+m+",t="+t);
      try{  System.out.println("故意抛出I/O异常！");
            throw new java.io.IOException("我是故意的");
            //System.out.println("这个输出语句肯定没有机会执行,所以必须注释掉，否则编译出错");
      }
      catch(java.io.IOException e) {
            System.out.println("发生异常:"+e.getMessage());
      }
   }
}
