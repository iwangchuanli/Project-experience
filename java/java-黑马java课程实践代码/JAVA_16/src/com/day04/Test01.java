package com.day04;

import java.util.HashMap;
import java.util.Set;

/**
 * һ��������һ��map�������£�
	HashMap<Integer,String> map = new HashMap<Integer, String>();
			map.put(1, "������");
			map.put(2, "������");
			map.put(3, "����");
			map.put(4, "���ʦ̫");
	Ҫ��
	1.�������ϣ�����������Ӧ������ӡ��
	2.���map�����в���һ������Ϊ5����Ϊ���������Ϣ
		3.�Ƴ���map�еı��Ϊ1����Ϣ 
		4.��map�����б��Ϊ2��������Ϣ�޸�Ϊ"����"
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		map.put(1, "������");
		map.put(2, "������");
		map.put(3, "����");
		map.put(4, "���ʦ̫");
		
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			String s = map.get(i);
			System.out.println("��ţ�"+i+"��������"+s);
		}
		System.out.println("-----------------------");
		map.put(5, "������");
		map.remove(1);
		map.put(2,"����");
		for (Integer i1 : set) {
			String s = map.get(i1);
			System.out.println("��ţ�"+i1+"��������"+s);
		}
	}
}
