package prj05;

import java.util.ArrayList;

public class MyArrays {

	//��list���϶����е�Ԫ�ؽ��з�ת(��һ�������һ���������ڶ����͵����ڶ����������������͵�������������...)
	public static void reverse(ArrayList<Integer> list){
		ArrayList<Integer> newlist = new ArrayList<>();
		if (list.isEmpty()) {
			
		}else{
			for (int i = list.size(); i < 0; i--) {
				newlist.add(list.get(i));
				list.remove(i);
			}
			for (int i = 0; i < newlist.size(); i++) {
				list.add(newlist.get(i));
			}
		}
	}
	
	//���list���϶����е����ֵ������
	public static Integer max(ArrayList<Integer> list){
		Integer max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(max) > 0) {
				max = list.get(i);
			}
		}
		return max;
	}
	
	//���list���϶����е���Сֵ������
	public static Integer min(ArrayList<Integer> list){
		Integer min = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(min) < 0) {
				min = list.get(i);
			}
		}
		return min;
	}
	
	//���Ԫ��i��list�����е�һ�γ��ֵ����������û�з���-1
	public static int indexOf(ArrayList<Integer> list,Integer i){
		//return list.indexOf(i);
		/*int 
		 * indexOf(Object o) 
          	���ش��б����״γ��ֵ�ָ��Ԫ�ص���������������б�����Ԫ�أ��򷵻� -1�� */
		
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).equals(i)) {
				return j;
			}
		}
		return -1;
	}
	
	//��list�����е�����ֵΪoldValue��Ԫ���滻ΪnewValue
	public static void replaceAll(ArrayList<Integer> list,Integer oldValue,Integer newValue){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(oldValue)) {
				list.set(i, newValue);
				/*E 
				 * set(int index, E element) 
          			��ָ����Ԫ��������б���ָ��λ���ϵ�Ԫ�ء� */
			}
		}
	}
}
