import java.util.*;
public class Example8_24 {
   public static void main(String args[]) {
      String  mess = null;
      Scanner reader=new Scanner(System.in);
      System.out.println("输入格式:姓名 分数1 分数2 分数3");
      mess = reader.nextLine();
      String name="";
      double sum = 0;
      while(!mess.startsWith("end")) {
        Scanner scanner = new Scanner(mess); 
        sum = 0;   
        while(scanner.hasNext()){
           try{  double score=scanner.nextDouble();
                 sum = sum+score;
           } 
           catch(InputMismatchException exp){
                name = scanner.next();
           }   
        }
        System.out.print(name+"总成绩:"+sum);
        System.out.printf("\t(输入end结束成绩统计)\n");
        mess = reader.nextLine();
      }
   }
}


