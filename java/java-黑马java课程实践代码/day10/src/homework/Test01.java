package homework;
/**1.自己实现冒泡排序的代码把一个数组的元素实现从小到大排序*/

public class Test01 {

	public static void print(int []arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	public static void array(int []arr){
		int temp = 0;
		System.out.println(" ");
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 -i; j++) {
				if (arr[j] > arr[j+1]) {
				temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int []a ={9,5,8,4,1,3,2,6,7};
		System.out.print("数组排序前：");
		print(a);
		array(a);
		System.out.print("数组排序后：");
		print(a);
	}
}
