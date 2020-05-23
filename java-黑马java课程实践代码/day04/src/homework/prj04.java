package homework;

public class prj04 {

	public static void main(String[] args) {
		int [] arr ={1,3,-1,5,-2};
		int [] newarr = new int[5];
		int index = 0;
		for (int i = 4; i >= 0 ; i--) {
			if (arr[i] >= 0) {
				newarr[index] = arr[i];
				index ++;
			}else{
				newarr[index] = 0;
				index ++;
			}
		}
		for (int i = 0; i < 5; i++) {
			System.out.print("arr["+i+"]="+arr[i]+'\t');
			System.out.println("newarr["+i+"]="+newarr[i]);
		}
	}
}
