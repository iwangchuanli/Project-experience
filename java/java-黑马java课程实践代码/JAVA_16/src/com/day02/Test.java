package com.day02;
/**
 * 
 * @author Administrator
 *��һ�⣺��ϰ���յĴ���

�ڶ��⣺������������ʵ��
	1.���´����Ƿ�������⣿Ϊʲô�� 
	��ȷ��int������4���ֽڣ��ٸ����ӣ�С����ˮ���Ե�����ĺ��С����ˮ��������
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				byte b1 = 4;
				int sum = i1+ b1;
				System.out.println(sum);
			}
		}

�����⣺������������ʵ��
	1.���´����Ƿ�������⣿Ϊʲô��
	���󣬾��ȴ��󣬿��ܵ�����ʧ���ȣ�byte����1���ֽڣ�
	int����4������
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				byte b1 = 4;
				byte sum = i1+ b1;//�˴�����
				System.out.println(sum);
			}
		}

		byte,short,char--int--long--float--double
		��boolean��
		
		
�����⣺������������ʵ��
	1.���´����Ƿ�������⣿Ϊʲô��
	���󣬾��ȴ��󣬿��ܵ�����ʧ���ȣ�5��int����
		public class Test {
			public static void main(String[] args) {
				byte b1 = 5;
				byte sum = b1 + 5;
				System.out.println(sum);
			}
		}

�����⣺������������ʵ��
	1.�鿴���´��룬��д�����
	false
	6 //������һ��++���㣬����Ѿ���false�ұ߲�����,���ڶ�·
	(i1++ > 5)//false ,��++�������к�++
	(++i1 > 4)//true����++����++������
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				boolean result = (i1++ > 5) && (++i1 > 4);
				System.out.println(result);
				System.out.println(i1);
			}
		}

�����⣺������������ʵ��
	1.�鿴���´��룬��д�����
	true
	7 //����������++���㣬����Ѿ���false�ұ���Ҫ�ж��ұ��Ƿ���ȷ
	(i1++ > 5)//false ,��++�������к�++  5>5
	(++i1 > 4)//true����++����++������  6>4	

		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				boolean result = (i1++ > 5) || (++i1 > 4);
				System.out.println(result);
				System.out.println(i1);
			}
		}

�����⣺������������ʵ��
	1.��ʹ����Ԫ�������������������е����ֵ��
		���磺20 40     ��ӡ�����40�����ֵ
		
public class Test {
			public static void main(String[] args) {
				boolean i = (20 > 40 ? true : false);
				//System.out.println(result);
				System.out.println(i);
			}
		}
�ڰ��⣺������������ʵ��
	1.��ʹ����Ԫ�������������������е����ֵ��
		���磺20 40 30     ��ӡ�����40�����ֵ			
		public class Test {
			public static void main(String[] args) {
				int i = (20 > 40)&&( 20 > 30) ? 20 : (40 > 30 ? 40 :30);
				//System.out.println(result);
				System.out.println(i + "�����ֵ");
			}
		}
	
�ھ��⣺������������ʵ��
	1.����һ��������������Ϊ��show()
	2.��show()������������¼��仰��
		�Ұ�����
		�Ұ�java
		��Ҫ�ú�ѧϰ
	3.���������е���show()���������У��������
	public class Test {
			public static void main(String[] args) {
				show();
			}
			public static void show (){
				System.out.print("�Ұ�����"+"\n"+"�Ұ�java"+"\n"+"��Ҫ�ú�ѧϰ");
			}
			
		}
��ʮ�⣺������������ʵ��
	1.����һ��������������Ϊ��show()
	2.��show()������������¼��仰��
		�Ұ�����
		�Ұ�java
		��Ҫ�ú�ѧϰ
	3.�ٶ���һ��������������Ϊ��print()
	4.��print()�����е���show()����
	5.���������е���print()���������У��������

	public class Test {
			public static void main(String[] args) {
				System.out.print("\n"+"print����ִ��");
				print();
				
			}
			public static void show (){
				System.out.print("�Ұ�����"+"\n"+"�Ұ�java"+"\n"+"��Ҫ�ú�ѧϰ");
			}
			public static void print(){
				System.out.print("\n"+"show����ִ��");
				show();
				
			}
			
		}
 */
public class Test {

}
