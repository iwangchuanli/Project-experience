package com.day01;
/**
 * 五、分析以下需求，并用代码实现：
		(1)打印由7，8，9三个数组成的三位数，要求该三位数中任意两位数字不能相同；
		(2)打印格式最后的三位数字以空格分隔，如789 798 879 897 978 987。
    注：要求使用StringBuilder来完成
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
