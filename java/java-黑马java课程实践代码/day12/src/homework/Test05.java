package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Test05 {

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> hmqtal = new HashMap<>();
		
		ArrayList<String> al1 = new ArrayList<>();
		al1.add("�����");
		al1.add("����");
		ArrayList<String> al2 = new ArrayList<>();
		al2.add("��ɮ");
		al2.add("�����");
		ArrayList<String> al3 = new ArrayList<>();
		al3.add("����");
		al3.add("³����");
		
		hmqtal.put("��������", al1);hmqtal.put("���μ�", al2);hmqtal.put("ˮ䰴�", al3);
		
		Set<String> set1 = hmqtal.keySet();
		for (String key : set1) {
			System.out.println('\n'+key);
			ArrayList<String> al = hmqtal.get(key);
			for (String str : al) {
				System.out.println(str);
			}
		}
	}
}
