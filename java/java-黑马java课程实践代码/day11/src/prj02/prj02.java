package prj02;

import java.util.ArrayList;
import java.util.List;

public class prj02 {

	//contains �ж�
	public static boolean myContains(ArrayList list,String str){
		return list.contains(str);
	}
	
	public static int countAndDelete(ArrayList list,String str){
		int count = 0;
		while (list.contains(str)) {
			count ++;
			list.remove(str);
		}
		return count;
	}
	//Сдת���ɴ�д
	public static  void toUpper(ArrayList<String> list){
		String str;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).toUpperCase();
//			str = list.get(i).toString();
//			str.toUpperCase();
//			list.add(str);
			System.out.print(list.get(i).toUpperCase()+" ");
		}
		
	}
	//�������
	public static void printf(ArrayList list){
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+"  ");
		}
	}
	//main
	public static void main(String[] args) {
		ArrayList<String> str = new ArrayList<>();
		str.add("abc");str.add("def");str.add("efg");str.add("hij");
		str.add("def");str.add("swd");str.add("def");str.add("www");
		
		printf(str);
		
		for (int i = 0; i < str.size(); i++) {
			if (i == 3) {
				System.out.println('\n'+"����������Ϊ3��Ԫ�أ�"+str.get(i));
			}	
		}
		
		if (myContains(str, "def")) {
			System.out.println("�����а���"+"def");
		}else
			System.out.println("������û�а���"+"def");
		
		System.out.println("ͳ��def�ĸ���������ɾ��");
		int x = countAndDelete(str, "def");
		System.out.println("ԭ���Ϲ���def:"+x+"����");
		printf(str);
		
		System.out.println('\n'+"ȫ��ת���ɴ�д��");
		toUpper(str);	//printf(str);
		
		
	}
}
