package homework;

public class test03 {

	public static void main(String[] args) {
		int [] nums = {5,10,15};
		int [] x = new int [3];
		for (int i = 0; i < x.length; i++) {
			x[i] = nums[i] * 2;
			System.out.println("nums["+i+"]="+nums[i]);
			System.out.println("x["+i+"]="+x[i]);
		}
	}
}
