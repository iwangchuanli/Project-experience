import java.util.*;
class Example2_5 {
   public static void main(String args[]) {
      int start=0,end,middle;
      int a[] = {12,45,67,89,123,-45,67};
      int N = a.length;
      for(int i=0; i<N; i++) {     //ѡ����������
	 for(int j = i+1; j < N;j++){
           if(a[j] < a[i]){
             int t = a[j];
             a[j] = a[i];
             a[i] = t;
           }
         }  
      }
      System.out.println(Arrays. toString(a));
      Scanner scanner = new Scanner(System.in);
      System.out.println("���������������жϸ������Ƿ���������:"); 
      int number = scanner.nextInt();
      int count =0 ;
      end = N;
      middle=(start+end)/2;
      while(number!=a[middle]){
           if(number>a[middle])
              start=middle;
           else if(number<a[middle])
              end=middle;
           middle=(start+end)/2;
           count++;
          if(count>N/2)
             break;
      }
      if(count>N/2)
         System.out.printf("%d����������.\n",number);
      else
         System.out.printf("%d��������.\n",number);
   }
}