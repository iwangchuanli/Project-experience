package SY_3;

import java.util.Arrays;
import java.util.Scanner;

public class sy3_2 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int cloth[];
		cloth=new int[10];
		for(int i=0;i<=9;i++){
			System.out.println("第"+i+"件衣服价格：");
			cloth[i]=input.nextInt();
		}
		Arrays.sort(cloth);
		System.out.println("升序排列：");
		for(int x=0;x<cloth.length;x++){
			System.out.println("第"+(x+1)+"件衣服价格："+cloth[x]);
			
		}
		input.close();
	}

}
