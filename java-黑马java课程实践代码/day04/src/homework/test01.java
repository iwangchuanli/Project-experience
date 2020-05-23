package homework;

public class test01 {

	public static void main(String[] args) {
		int []a = new int [5];
		int []b =  {1,2,3,4,5};
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		for (int i = 0; i < 5; i++) {
			System.out.println("a数组"+a[i]+"b数组"+b[i]);
		}
	}
}
