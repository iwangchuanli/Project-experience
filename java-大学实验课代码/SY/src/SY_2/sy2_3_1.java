package SY_2;

import java.util.Scanner;

public class sy2_3_1 {
	public static void main(String[] args) {
		int n,k;
		double a=1,b=1;
		double gl;
		Scanner reader=new Scanner(System.in);
		System.out.println("自然数范围:");
		n=reader.nextInt();
		System.out.println("抽取整数数量:");
		k=reader.nextInt();
		for(int i=n;i>=(n-k)+1;i--){
			a=a*i;
		}
		for(int j=1;j<=k;j++){
			b=b*j;
		}
		gl=a/b;
		System.out.println("从"+n+"中抽取"+k+"个数字中奖的概率是:"+gl);
		reader.close();
	}

}
