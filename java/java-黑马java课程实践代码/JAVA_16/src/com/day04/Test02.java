package com.day04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
二、有2个数组，第一个数组内容为：[黑龙江省,浙江省,江西省,广东省,福建省]，
               第二个数组内容为：[哈尔滨,杭州,南昌,广州,福州]，
			   将第一个数组元素作为key，第二个数组元素作为value存储到Map集合中。
			   如{黑龙江省=哈尔滨, 浙江省=杭州, …}。
			   使用两种方式遍历map集合
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		String sheng[] = {"黑龙江省","浙江省","江西省","广东省","福建省"};
		String shi[] = {"哈尔滨","杭州","南昌","广州","福州"};
		
		HashMap<String, String> china = new HashMap<>();
		for (int i = 0; i < sheng.length; i++) {
			china.put(sheng[i], shi[i]);
		}
		System.out.println(china);
		//entry(项值)循环
		Set<Entry<String, String>> set = china.entrySet();
		for (Entry<String, String> entry : set) {
			String s = entry.getKey();
			String ss = entry.getValue();
			System.out.print(s+"="+ss+" ");
		}
		//set键值循环
		Set<String> set1 = china.keySet();
		for (String st : set1) {
			String ss = china.get(st);
			System.out.print(st+"="+ss+" ");
		}
	}
}
