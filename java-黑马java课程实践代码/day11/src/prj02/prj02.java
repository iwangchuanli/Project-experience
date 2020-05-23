package prj02;

import java.util.ArrayList;
import java.util.List;

public class prj02 {

	//contains 判断
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
	//小写转换成大写
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
	//集合输出
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
				System.out.println('\n'+"集合中索引为3的元素："+str.get(i));
			}	
		}
		
		if (myContains(str, "def")) {
			System.out.println("集合中包含"+"def");
		}else
			System.out.println("集合中没有包含"+"def");
		
		System.out.println("统计def的个数并将其删除");
		int x = countAndDelete(str, "def");
		System.out.println("原集合共有def:"+x+"个。");
		printf(str);
		
		System.out.println('\n'+"全部转换成大写后：");
		toUpper(str);	//printf(str);
		
		
	}
}
