package code.myIf.src.com.itheima;
/*
 * if��������ָ�ʽ��
 * 
 * ��ʽ1��
 * 		if(��ϵ���ʽ) {
 * 			�����;
 * 		}
 * 
 * ִ�����̣�
 * 		A:���ȼ����ϵ���ʽ��ֵ������true����false
 * 		B:�����true����ִ�������
 * 		C:�����false���Ͳ�ִ�������
 */
public class IfDemo {
	public static void main(String[] args) {
		System.out.println("��ʼ");
		
		//��������int���͵ı���
		int a = 10;
		int b = 20;
		
		//�ж����������Ƿ����
		if(a == b) {
			System.out.println("a����b");
		}
		
		//�������
		int c = 10;
		if(a == c) {
			System.out.println("a����c");
		}
		
		System.out.println("����");
	}
}
