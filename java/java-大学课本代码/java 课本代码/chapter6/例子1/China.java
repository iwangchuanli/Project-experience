public class China implements Computable {  //China��ʵ��Computable�ӿ�
   int number;
   public int f(int x) { //��Ҫ����public�ؼ���
      int sum=0;
      for(int i=1;i<=x;i++) {
         sum=sum+i;
      }
      return sum;
   }
}
