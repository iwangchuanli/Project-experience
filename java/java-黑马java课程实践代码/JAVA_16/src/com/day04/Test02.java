package com.day04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
������2�����飬��һ����������Ϊ��[������ʡ,�㽭ʡ,����ʡ,�㶫ʡ,����ʡ]��
               �ڶ�����������Ϊ��[������,����,�ϲ�,����,����]��
			   ����һ������Ԫ����Ϊkey���ڶ�������Ԫ����Ϊvalue�洢��Map�����С�
			   ��{������ʡ=������, �㽭ʡ=����, ��}��
			   ʹ�����ַ�ʽ����map����
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		String sheng[] = {"������ʡ","�㽭ʡ","����ʡ","�㶫ʡ","����ʡ"};
		String shi[] = {"������","����","�ϲ�","����","����"};
		
		HashMap<String, String> china = new HashMap<>();
		for (int i = 0; i < sheng.length; i++) {
			china.put(sheng[i], shi[i]);
		}
		System.out.println(china);
		//entry(��ֵ)ѭ��
		Set<Entry<String, String>> set = china.entrySet();
		for (Entry<String, String> entry : set) {
			String s = entry.getKey();
			String ss = entry.getValue();
			System.out.print(s+"="+ss+" ");
		}
		//set��ֵѭ��
		Set<String> set1 = china.keySet();
		for (String st : set1) {
			String ss = china.get(st);
			System.out.print(st+"="+ss+" ");
		}
	}
}
