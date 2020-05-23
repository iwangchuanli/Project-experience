package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 * @author Administrator
 *
 */
public class Prog37 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> per = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			per.add(i);
		}
		int count = 0;
		int len = per.size();
		
	}
	
}
