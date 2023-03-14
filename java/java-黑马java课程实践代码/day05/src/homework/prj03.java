package homework;

public class prj03 {

	public static boolean equals(int[] arr1,int[] arr2){
		if (arr1.length == arr2.length) {
			for (int i = 0; i < arr1.length; i++) {
				if (arr1[i] == arr2[i]) {
					return true;
				}
			}
		}
		return false;
	}
	public static void  fill(int[] arr,int value){
		for (int i = 0; i < arr.length; i++) {
			arr[i] = value;
		}
	}
	
	public static void fill(int[] arr,int fromIndex,int toIndex,int value){
		for (int i = fromIndex; i < toIndex; i++) {
			arr[i] = value;
		}
	}
	
	public static int [] copyOf(int[] arr, int newLength){
		int [] newarr = new int [newLength];
		for (int i = 0; i < newLength; i++) {
			newarr[i] = arr[i];
		}
		return newarr;
	}
	
	public int [] copyOfRange(int[] arr,int from, int to) {
		int [] newarr = null;
		for (int i = from; i < to; i++) {
			newarr[i] = arr[i];
		}
		return newarr;
	}
	
	public static void main(String[] args) {
		
	}
}
