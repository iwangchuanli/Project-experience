package com.day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * ��������������ɴ���
	1.����һ��ArrayList���ϣ��洢�������֣�55 45 65 75 35 25 85
	2.ʹ�ü��Ϲ������������
	3.ʹ����ǿfor��������Ԫ�ء�
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<>();
		Collections.addAll(num, 55,45,65,75,35,25,85);
		Collections.sort(num);//��С����
		for (Integer i : num) {
			System.out.print(i+" ");
		}
		Collections.sort(num, new Comparator<Integer>() {//�Ӵ�С
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
