package homework;

import java.util.Scanner;

public class prj01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入小明左手中的纸牌：");
		int left = input.nextInt();
		System.out.print("请输入小明右手中的纸牌：");
		int right = input.nextInt();
		
		System.out.print("互换前小明手中的纸牌："+'\n'+
				"左手中的纸牌："+left+'\n'+"右手中的纸牌："+right+'\n');
		
		int temp = left;
		left = right;right = temp;
		System.out.print("互换后小明手中的纸牌："+'\n'+
				"左手中的纸牌："+left+'\n'+"右手中的纸牌："+right+'\n');
	}
}
