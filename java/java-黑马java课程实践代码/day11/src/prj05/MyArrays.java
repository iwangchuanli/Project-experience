package prj05;

import java.util.ArrayList;

public class MyArrays {

	//对list集合对象中的元素进行反转(第一个和最后一个交换，第二个和倒数第二个交换，第三个和倒数第三个交换...)
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
	
	//求出list集合对象中的最大值并返回
	public static Integer max(ArrayList<Integer> list){
		Integer max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(max) > 0) {
				max = list.get(i);
			}
		}
		return max;
	}
	
	//求出list集合对象中的最小值并返回
	public static Integer min(ArrayList<Integer> list){
		Integer min = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(min) < 0) {
				min = list.get(i);
			}
		}
		return min;
	}
	
	//求出元素i在list集合中第一次出现的索引，如果没有返回-1
	public static int indexOf(ArrayList<Integer> list,Integer i){
		//return list.indexOf(i);
		/*int 
		 * indexOf(Object o) 
          	返回此列表中首次出现的指定元素的索引，或如果此列表不包含元素，则返回 -1。 */
		
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).equals(i)) {
				return j;
			}
		}
		return -1;
	}
	
	//将list集合中的所有值为oldValue的元素替换为newValue
	public static void replaceAll(ArrayList<Integer> list,Integer oldValue,Integer newValue){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(oldValue)) {
				list.set(i, newValue);
				/*E 
				 * set(int index, E element) 
          			用指定的元素替代此列表中指定位置上的元素。 */
			}
		}
	}
}
