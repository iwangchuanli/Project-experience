import java.util.*;
public class Example8_24 {
   public static void main(String args[]) {
      String  mess = null;
      Scanner reader=new Scanner(System.in);
      System.out.println("�����ʽ:���� ����1 ����2 ����3");
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
        System.out.print(name+"�ܳɼ�:"+sum);
        System.out.printf("\t(����end�����ɼ�ͳ��)\n");
        mess = reader.nextLine();
      }
   }
}


