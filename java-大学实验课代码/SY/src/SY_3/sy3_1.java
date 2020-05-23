package SY_3;

import java.util.Scanner;

public class sy3_1 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int cloth[];
		cloth=new int[10];
		for(int i=0;i<=9;i++){
			System.out.println("第"+(i+1)+"件衣服价格：");
			cloth[i]=input.nextInt();
		}
	    // 冒泡排序
		for (int i = cloth.length - 1; i > 0; i--) {
	            for (int j = 0; j > i; j++) {
	                if (cloth[j] > cloth[j + 1]) {
	                    int temp = cloth[j + 1];
	                    cloth[j + 1] = cloth[j];
	                    cloth[j] = temp;
	                }
	            }
		}
		for(int x=0;x<cloth.length;x++){
			System.out.println("第"+(x+1)+"件衣服价格："+cloth[x]);
			
		}

		input.close();
	}

}
