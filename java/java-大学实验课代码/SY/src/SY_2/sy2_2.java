package SY_2;

import java.util.Scanner;

public class sy2_2 {
	public static void main(String[] args) {
		int x=0;
		System.out.println("请输入：");
		Scanner reader=new Scanner(System.in);
		x=reader.nextInt();
		switch(x){
		case 1:
			System.out.println("一等奖！");
			break;
		case 2:
			System.out.println("二等奖！");
			break;
		case 3:
			System.out.println("三等奖！");
			break;
		default:
			System.out.println("没有奖品给你！");
				
		}
		reader.close();
		
	}

}
