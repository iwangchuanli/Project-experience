package com.day01;
/**
 * �塢�����������󣬲��ô���ʵ�֣�
		(1)��ӡ��7��8��9��������ɵ���λ����Ҫ�����λ����������λ���ֲ�����ͬ��
		(2)��ӡ��ʽ������λ�����Կո�ָ�����789 798 879 897 978 987��
    ע��Ҫ��ʹ��StringBuilder�����
 * @author Administrator
 *
 */
public class Test05 {

	public static void main(String[] args) {
		String []s = {"7","8","9"};
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				for (int m = 0; m < s.length; m++) {
					if ((!s[i].equals(s[j])) && (!s[j].equals(s[m])) && (!s[i].equals(s[m]))) {
						str.append(s[i]).append(s[j]).append(s[m]).append(" ");
						System.out.print(str.toString());
						str.delete(0, str.length());
					}
				}
			}
		}
	}
}
