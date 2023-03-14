package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Test05 {

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> hmqtal = new HashMap<>();
		
		ArrayList<String> al1 = new ArrayList<>();
		al1.add("诸葛亮");
		al1.add("赵云");
		ArrayList<String> al2 = new ArrayList<>();
		al2.add("唐僧");
		al2.add("孙悟空");
		ArrayList<String> al3 = new ArrayList<>();
		al3.add("武松");
		al3.add("鲁智深");
		
		hmqtal.put("三国演义", al1);hmqtal.put("西游记", al2);hmqtal.put("水浒传", al3);
		
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
