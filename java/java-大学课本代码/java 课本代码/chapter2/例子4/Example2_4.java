public class Example2_4 {
   public static void main(String args[]) {
      int a[]={1,2,3,4};
      int b[]={100,200,300};
      System.out.println("数组a的元素个数="+a.length);
      System.out.println("数组b的元素个数="+b.length);
      System.out.println("数组a的引用="+a);
      System.out.println("数组b的引用="+b);
      a=b;
      System.out.println("数组a的元素个数="+a.length);
      System.out.println("数组b的元素个数="+b.length);
      System.out.println("a[0]="+a[0]+",a[1]="+a[1]+",a[2]="+a[2]);
      System.out.print("b[0]="+b[0]+",b[1]="+b[1]+",b[2]="+b[2]);
   }
} 




