package homework;

import java.util.Scanner;

/***
 * @author Administrator
 *�ڿ���̨����������ݣ�
	����������
	���䣺22
	�Ա���
	ְҵ��ѧ��
	סַ�������в�ƽ�����߼���긣�Ƽ�԰��������

 */
public class prj02 {
public static void main(String[] args) {
	Scanner input=new Scanner(System.in);
	String name,sex,work,address;
	int age;
	System.out.println("�����������Ϣ��");
	name = input.nextLine();
	age = input.nextInt();
	sex = input.nextLine();
	work = input.nextLine();
	address = input.nextLine();
	System.out.println("���������Ϣ����Ϊ��"+'\n'+
			"������"+name+'\n'+"���䣺"+age+'\n'+"�Ա�"+sex+
			'\n'+"ְҵ��"+work+'\n'+"��ַ��"+address);
	
}
}
