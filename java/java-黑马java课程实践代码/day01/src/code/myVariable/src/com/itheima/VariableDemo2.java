package code.myVariable.src.com.itheima;
/*
 * ������ע�����
 * 		A:����δ��ֵ������ֱ��ʹ��
 * 		B:����ֻ���������ķ�Χ����Ч
 * 			�������������ڵ��ǶԴ�����
 * 		C:һ���Ͽ��Զ��������������ǲ�����
 */
public class VariableDemo2 {
	public static void main(String[] args) {
		//����һ������
		int a = 10;
		System.out.println(a);
		
		int b;
		b = 20;
		System.out.println(b);
		
		{
			//�����
			int c = 30;
			System.out.println(c);
		}
		//System.out.println(c);
		System.out.println(b);
		
		
		/*
		int aa,bb;
		aa = 10;
		bb = 20;
		System.out.println(aa);
		System.out.println(bb);
		*/
		int aa = 10;
		int bb = 20;
		System.out.println(aa);
		System.out.println(bb);
	}
}
