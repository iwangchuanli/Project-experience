package myOperator;

/*
 * �����Լ��������++,--
 * 
 * ���ã������Լ�+1����-1
 * 
 * ++��--���Է��ڱ�����ǰ�棬Ҳ���Է��ڱ����ĺ��档
 * ����ʹ��һ��������ʱ�򣬷��ڱ�����ǰ����ߺ��棬Ч��һ����
 * ��������������ʱ��
 * 		++�ڱ����ĺ��棬�Ȱѱ�����������Ȼ�������++
 * 		++�ڱ�����ǰ�棬�ȱ���++��Ȼ����������
 */
public class demo3 {
	public static void main(String[] args) {
		//����һ��int���͵ı���
		int a = 10;
		System.out.println("a:"+a);
		
		//a++;
		//a--;
		//++a;
		//--a;
		//System.out.println("a:"+a);
		
		//int b = a++;
		int b = ++a;
		System.out.println("a:"+a);
		System.out.println("b:"+b);
	}
}

