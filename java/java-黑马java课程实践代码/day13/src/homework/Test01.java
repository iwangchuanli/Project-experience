package homework;

public class Test01 {

	public static void main(String[] args) {
		int a =5,b = 0;
		try {
			System.out.println(a/b);
		} catch (ArithmeticException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
