package Demo;

import java.util.ArrayList;
import java.util.List;

/*
 * ��ǿfor����forѭ����һ��
 * 
 * ��ʽ��
 * 		for(Ԫ�ص��������� ������ : ����������Collection���϶�����) {
 * 			ʹ�ñ��������ɣ�����������������ʵ�����������Collection�����е�Ԫ��
 * 		}
 * 
 * 		�ô������������Collection���ϵı���
 * 		�׶ˣ�Ŀ�겻��Ϊnull����α�֤��?�ڱ���ǰ�ȶ�Ŀ����в�Ϊnull���жϡ�
 */
public class ForDemo {
	public static void main(String[] args) {
		//����һ��int���͵�����
		int[] arr = {1,2,3,4,5};
		//��ͨfor
		for(int x=0; x<arr.length; x++) {
			System.out.println(arr[x]);
		}
		System.out.println("---------");
		//��ǿfor
		for(int x : arr) {
			System.out.println(x);
		}
		System.out.println("---------");
		//����һ��String���͵�����
		String[] strArray = {"hello","world","java"};
		//��ǿfor
		for(String s : strArray) {
			System.out.println(s);
		}
		System.out.println("---------");
		//�������϶���
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("java");
		//��ǿfor
		for(String s :list) {
			System.out.println(s);
		}
		
//		list = null;
//		//NullPointerException
//		if(list != null) {
//			for(String s :list) {
//				System.out.println(s);
//			}
//		}
		
		//��ǿfor��ʵ�������������������
//		for(String s :list) {
//			if(s.equals("world")) {
//				list.add("javaee");
//			}
//		}
		
	}
}

