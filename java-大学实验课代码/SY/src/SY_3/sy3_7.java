package SY_3;

import java.util.Scanner;

public class sy3_7 {
	public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
		 System.out.print("请输入一串数字：");
		 String num = input.next();
		 StringBuffer sb = new StringBuffer(num); //创建一个变量存储字符串
		 for(int i=sb.length()-3;i>0;i=i-3){
		  sb.insert(i, ","); sb.insert (1, "**");  
		 }
		 System.out.println(sb);
		 input.close();
	}
}


