public class China implements Computable {  //China类实现Computable接口
   int number;
   public int f(int x) { //不要忘记public关键字
      int sum=0;
      for(int i=1;i<=x;i++) {
         sum=sum+i;
      }
      return sum;
   }
}
