package homework;

public class prj01 {

	public static int max(int []a){
		int index = 0;
		int max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
				index = i;
			}
		}
		return index;
	}
	public static void main(String[] args) {
		int []arr = {10,20,30,40,50,60};
		int maxIndex = max(arr);
		System.out.println("数组中最大元素的索引是："+maxIndex);
	}
}
