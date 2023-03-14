package Demo;


import java.util.ArrayList;
import java.util.Collection;
/*
* Collection:�ǵ��м��ϵĶ���ӿڡ�
 * Collection ��ʾһ�������Щ����Ҳ��Ϊ collection ��Ԫ�ء�
 * һЩ collection �������ظ���Ԫ�أ�����һЩ������
 * һЩ collection ������ģ�����һЩ��������ġ�
 * JDK ���ṩ�˽ӿڵ��κ�ֱ�� ʵ�֣����ṩ��������ӽӿڣ��� Set �� List��ʵ�֡�
 * 
 * ����Collection���ϵĶ������ǲ��õ��Ƕ�̬�ķ�ʽ��ʹ�õ��Ǿ����ArrayList�ࡣ
 * ��Ϊ���������õļ����ࡣ
 * ArrayList() 
 * 
 * Collection<E>��
 * 		<E>:��һ��������������ͣ����͡��������ǻ�ʹ�þͿ����ˡ�
 * 		���ʹ����?
 * 			�ڳ���E�ĵط����������������滻���ɡ�
 * 			������Collection<String>,Collection<Student>
 */
public class CollectionDemo {
	public static void main(String[] args) {
		//����Collection���϶���
		//JDK7�������ԣ������Ϳ���
		//Collection<String> c = new ArrayList<>(); //��̬�ķ�ʽ
		Collection<String> c = new ArrayList<String>(); //��̬�ķ�ʽ
		
		//boolean add(E e):���Ԫ��
		c.add("hello");
		c.add("world");
		c.add("java");
		
		//������϶���
		System.out.println(c);
		//����˼����е�Ԫ�ذ���ָ����ʽƴ�ӵ����ݣ�˵��ArrayList��д��toString()����
	}
}
