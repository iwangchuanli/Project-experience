package SY_2;
import java.util.Scanner;
public class sy2_1 {
	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	System.out.println("请输入一个年份：");
	int year = scan.nextInt();
	if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
	System.out.println(year + "年是闰年！");
	} 
	else {
	System.out.println(year + "年是平年！");
	}
	scan.close();
	}
}




