package homework;

import java.util.Scanner;
/***
 * 
 * @author Administrator
 *next() �� nextLine() ����
next():

1��һ��Ҫ��ȡ����Ч�ַ���ſ��Խ������롣
2����������Ч�ַ�֮ǰ�����Ŀհף�next() �������Զ�����ȥ����
3��ֻ��������Ч�ַ���Ž����������Ŀհ���Ϊ�ָ������߽�������
next() ���ܵõ����пո���ַ�����
nextLine()��

1����EnterΪ������,Ҳ����˵ nextLine()�������ص�������س�֮ǰ�������ַ���
2�����Ի�ÿհס�
 */
public class prj11 {

	public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			System.out.print("�������ͬѧ�ĳɼ�(s):");
			double score = input.nextDouble();
			//input.close();
			//Scanner in = new Scanner(System.in);
			if(score >= 10) {
				System.out.println("wuyuan");
			}
			else{
				System.out.print("�������ͬѧ���Ա�:");
				String gender = input.next();
				
				if ("��".equals(gender)) {
					System.out.println("nan");
				}else{
					System.out.println("nv");
				}
			}
	}
}
