package com.day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * һ������������ɴ���
	1.����һ��Person��
		��Ա������name   age
		���췽����get��set��toSring
	2.��������Person����
		Person("����",23);
		Person("����",24);
		Person("����",23);
	3.������������洢��Set�����С�(ͬ����ͬ�����Ϊ�ظ�ֵ�����洢)
	4.ʹ�õ�������������
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		HashSet<Person> per = new HashSet<>();
		Person per1 = new Person("����",23);
		Person per2 = new Person("����",24);
		Person per3 = new Person("����",23);
		per.add(per1);per.add(per2);per.add(per3);
		
		Iterator<Person> it = per.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			System.out.println(s);
		}
	}
}
