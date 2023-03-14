package homework;

import java.util.HashSet;

public class prj01 {

	public static void main(String[] args) {
		HashSet<String> hm = new HashSet<String>();
		int[] arr = {1,2,3,4,5,6,7,8,9,0,2,5,7,8,9,9,9,9,9,0};
		for(int i = 0;i<arr.length;i++){
			hm.add(arr[i]+"");
		}
		System.out.println(hm);
	}
}
