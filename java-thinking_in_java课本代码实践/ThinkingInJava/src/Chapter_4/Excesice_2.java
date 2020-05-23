package Chapter_4;

import java.util.Random;

import javax.security.auth.x500.X500Principal;

public class Excesice_2 {
public static void main(String[] args) {
	int [] x = new int [25];
	for (int i = 0; i <=25; i++) {
		x[25]=(int) (Math.random()*30);
		System.out.print(x[25]+"  ");
	}
	System.out.print("大于后一个值的数:");
	for (int i = 0; i <=25; i++) {
		if (x[i]>x[i+1]) {
			System.out.print(x[i]+"  ");
		}
	}
	System.out.print("小于后一个值的数:");
	for (int i = 0; i <=25; i++) {
		if (x[i]<x[i+1]) {
			System.out.print(x[i]+"  ");
		}
	}
	System.out.print("等于后一个值的数:");
	for (int i = 0; i <=25; i++) {
		if (x[i]==x[i+1]) {
			System.out.print(x[i]+"  ");
		}
	}
}
}
