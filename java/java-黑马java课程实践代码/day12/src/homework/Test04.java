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
		hm1.put("���", "����");
		hm1.put("���", "С��");
		HashMap<String, String> hm2 = new HashMap<>();
		hm2.put("����", "����");
		hm2.put("���", "С��Ů");
		HashMap<String, String> hm3 = new HashMap<>();
		hm3.put("�����", "��ӯӯ");
		hm3.put("��ƽ֮", "����ɺ");
		
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
