package com.day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * 二、根据需求完成代码
	1.定义一个ArrayList集合，存储以下数字：55 45 65 75 35 25 85
	2.使用集合工具类对其排序。
	3.使用增强for遍历集合元素。
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<>();
		Collections.addAll(num, 55,45,65,75,35,25,85);
		Collections.sort(num);//从小到大
		for (Integer i : num) {
			System.out.print(i+" ");
		}
		Collections.sort(num, new Comparator<Integer>() {//从大到小
			@Override
			public int compare(Integer n0, Integer n1) {
				// TODO Auto-generated method stub
				return n1 - n0;
			}
		});
		System.out.println('\n'+"----------");
		for (Integer i : num) {
			System.out.print(i+" ");
		}
	}
}
