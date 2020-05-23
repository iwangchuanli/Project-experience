package homework;

public class test03 {

	public static int min(int []a){
		int min = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min > a[i]) {
				min = a[i];
			}
		}
		return min;
	}
	public static void main(String[] args) {
		int [] arr ={22,2,30,40,50,60};
		int minArr = min(arr);
		System.out.print("数组中元素最小的是："+minArr);
	}
}
