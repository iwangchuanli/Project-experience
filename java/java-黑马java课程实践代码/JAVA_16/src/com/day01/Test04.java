package com.day01;

/**
 * �ġ������������󣬲��ô���ʵ�֣� (1)���������ַ�������{"010","3223","666","7890987","123123"}��
 * (2)�жϸ������ַ��������е������ַ����Ƿ��ǶԳ�(��һ�����ֺ����һ��������ȣ��ڶ������ֺ͵����ڶ�����������ȵģ���������)�ģ�����������
 * (3)�磺010 �ǶԳƵģ�3223 �ǶԳƵģ�123123 ���ǶԳƵģ� (4)���մ�ӡ�������жԳ��ַ����ĸ�����
 * ע���ж϶Գƿ���StringBuilder�е�reverse(),�����ַ��������䷴ת��ʽȡ����
 * 
 * @author Administrator
 *
 */
public class Test04 {
	public static void main(String[] args) {
		StringBuilder str = new StringBuilder();
		String[] ss = { "010", "3223", "666", "7890987", "123123" };
		for (int i = 0; i < ss.length; i++) {
			str.delete(0, str.length());
			str.append(ss[i]);
			String s = str.reverse().toString();
			if (s.equals(ss[i])) {
				System.out.println(ss[i] + "�ǶԳƵġ�");
			} else {
				System.out.println(ss[i] + "���ǶԳƵġ�");
			}
		}
	}
}
