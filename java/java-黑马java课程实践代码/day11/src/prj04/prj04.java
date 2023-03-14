package prj04;

import java.util.ArrayList;

public class prj04 {

	public static void printf(ArrayList<String> list){
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+"  ");
		}
	}
	public static ArrayList<String> deleteList(ArrayList<String> list) {
		ArrayList<String> deleteList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() > 5) {
				deleteList.add(list.get(i));
			}
		}
		return deleteList;
	}
	//判断是否含有
	public static boolean myContains(ArrayList<String> list,String str){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(str)) {
				return true;
			}
		}
		return false;
	}
	//删除指定元素
	 public static void deleteList(ArrayList<String> list,String str){
		 for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(str)) {
				list.remove(i);
			}
		}
	 }
	public static void main(String[] args) {
		ArrayList<String> arrList = new ArrayList<>();
		arrList.add("ab1564a");arrList.add("bca");arrList.add("abcdef");
		arrList.add("ddds1a");arrList.add("你好啊");arrList.add("我来啦");
		arrList.add("别跑啊");
		printf(arrList);
		System.out.println('\n'+"长度大于5的字符串删除后,需要的集合对象");
		printf(deleteList(arrList));
		System.out.println(" ");
		for (int i = 0; i < 10; i++) {
			String s = String.valueOf(i);
			if (myContains(arrList,s)) {
				deleteList(arrList, s);
				//System.out.println("123456");
			}
		}
		printf(arrList);
	}
}
