package homework;

import java.util.Random;
import java.util.Scanner;

public class prj05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("请输入您的4位会员号：");
		int id = input.nextInt();
		int num = (id/100)%10;
		int lucky = ran.nextInt(10);
		if (num == lucky) {
			System.out.print("幸运数字是："+lucky+'\n'+"这位会员的百位数是："+num+"，成为幸运会员！！");
		}
		else
			System.out.print("幸运数字是："+lucky+'\n'+"这位会员的百位数是："+num+"，不是幸运会员！！！");
	}
}
