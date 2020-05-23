package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test04 {

	public static void main(String[] args) {
		
		ArrayList<HashMap<String, String>> alqthm = new ArrayList<>();
		
		HashMap<String, String> hm1 = new HashMap<>();
		hm1.put("孙策", "大乔");
		hm1.put("周瑜", "小乔");
		HashMap<String, String> hm2 = new HashMap<>();
		hm2.put("郭靖", "黄蓉");
		hm2.put("杨过", "小龙女");
		HashMap<String, String> hm3 = new HashMap<>();
		hm3.put("令狐冲", "任盈盈");
		hm3.put("林平之", "岳灵珊");
		
		alqthm.add(hm1);alqthm.add(hm2);alqthm.add(hm3);
		
		for (HashMap<String, String> hm : alqthm) {
			Set<String> set = hm.keySet();
			for (String str : set) {
				String value = hm.get(str);
				System.out.println(str+"   "+value);
			}
			System.out.println("-------------");
		}
		System.out.println("----------------------------------------");
		
		for (HashMap<String, String> hm : alqthm) {
			Set<Map.Entry<String, String>> set2 = hm.entrySet();
			for (Entry<String, String> entry : set2) {
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println(key+"  "+value);
			}
		}
	}
}
