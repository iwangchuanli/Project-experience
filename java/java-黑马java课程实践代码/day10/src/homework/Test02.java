package homework;
/**2.自己实现冒泡排序的代码把一个数组的元素实现从大到小排序*/
public class Test02 {

	public static void main(String[] args) {
		int []a = {9,7,8,2,5,3,1,4,6};
		System.out.print("数组排序前：");
		print(a);
		array(a);
		System.out.print('\n'+"数组排序后：");
		print(a);
	}
	public static void array(int[] arr){
		int temp;
		int i = 0;
		while (i < arr.length) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] < arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			i++;
		}
	}
	public static void print(int [] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
