import java.util.Scanner;
public class Example3_4{
   public static void main(String args[]) {
        int number;
	System.out.println("����������(�س�ȷ��)");
        Scanner reader=new Scanner(System.in);
	number = reader.nextInt();
	switch(number) {
	    case 9 :
	    case 131 :
	    case 12 :  System.out.println(number+"�����Ƚ�");
		       break;
	    case 209 :
	    case 596 :
	    case 27 :  System.out.println(number+"�Ƕ��Ƚ�");
		           break;
	    case 875 :
	    case 316 :
	    case 59 :   System.out.println(number+"��һ�Ƚ�");
	       	        break;
	    default:    System.out.println(number+"δ�н�");
        }
   }
}





