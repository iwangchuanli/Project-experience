package Demo;

import java.util.ArrayList;
import java.util.List;

/*
 * 增强for：是for循环的一种
 * 
 * 格式：
 * 		for(元素的数据类型 变量名 : 数组名或者Collection集合对象名) {
 * 			使用变量名即可，这个变量名代表的其实就是数组或者Collection集合中的元素
 * 		}
 * 
 * 		好处：简化了数组和Collection集合的遍历
 * 		弊端：目标不能为null。如何保证呢?在遍历前先对目标进行不为null的判断。
 */
public class ForDemo {
	public static void main(String[] args) {
		//定义一个int类型的数组
		int[] arr = {1,2,3,4,5};
		//普通for
		for(int x=0; x<arr.length; x++) {
			System.out.println(arr[x]);
		}
		System.out.println("---------");
		//增强for
		for(int x : arr) {
			System.out.println(x);
		}
		System.out.println("---------");
		//定义一个String类型的数组
		String[] strArray = {"hello","world","java"};
		//增强for
		for(String s : strArray) {
			System.out.println(s);
		}
		System.out.println("---------");
		//创建集合对象
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("java");
		//增强for
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
		
		//增强for其实就是用来替代迭代器的
//		for(String s :list) {
//			if(s.equals("world")) {
//				list.add("javaee");
//			}
//		}
		
	}
}

