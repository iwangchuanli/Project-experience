package code.myTypeConversion.src.com.itheima;
/*
 * +:���ӷ��ķ��š�
 * 
 * ����ת����
 * 		��ʽת��
 * 		ǿ��ת��
 * 
 * ��ʽת����
 * 		byte,short,char -- int -- long -- float -- double
 * 
 * 		boolean���Ͳ��������������㡣
 */
public class ConversionDemo {
	public static void main(String[] args) {
		//��������int���͵ı���
		int a = 10;
		int b = 20;
		System.out.println(a+b);
		//�ҿ��԰�a+b�Ľ�����������˵���������Ľ����û�������
		//��ô����Ӧ��Ҳ���԰�����������һ�¡�
		int c = a + b;
		System.out.println(c);
		System.out.println("-----------------");
		
		//��������������һ��int���ͣ�һ��byte����
		int aa = 10;
		byte bb = 20;
		System.out.println(aa+bb);
		//��������д��������
		//byte cc = aa+bb;
		int cc = aa + bb;
		System.out.println(cc);
	}
}
