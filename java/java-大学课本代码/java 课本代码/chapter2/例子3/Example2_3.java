import java.util.Scanner;
public class Example2_3 {
    public static void main (String args[ ]){
        System.out.println("���������ɸ�����ÿ����һ�����س�ȷ��");
        System.out.println("�����������0�����������");
        Scanner reader=new Scanner(System.in);
        double sum=0;
        double x = reader.nextDouble();
        while(x!=0){
           sum=sum+x;
           x=reader.nextDouble();
        }
        System.out.println("sum="+sum);
    }
}



