package myOperator;

/*
 * ��ֵ�������
 * 		�����ĸ�ֵ�������=
 * 		��չ�ĸ�ֵ�������+=,-=,...
 */
public class demo4 {
	public static void main(String[] args) {
		//�������
		int a = 10; //��10��ֵ��int���͵ı���a
		System.out.println("a:"+a);
		
		//��չ�ĸ�ֵ�������+=
		//���������ߵ����ݺ��ұߵ����ݽ������㣬Ȼ��ѽ����ֵ�����
		//a = a + 20;
		a += 20;
		System.out.println("a:"+a);
		
		//short s = 1;
		//s = s + 1;
		
		//��չ�ĸ�ֵ�����������ǿ������ת����
		//a+=20
		//�ȼ���
		//a =(a����������)(a+20);
		short s = 1;
		s += 1;
		System.out.println("s:"+s);
	}
}

