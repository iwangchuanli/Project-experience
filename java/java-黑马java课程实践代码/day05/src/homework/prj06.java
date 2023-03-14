package homework;

public class prj06 {

	public static int sum(int a,int b) {
		return a+b;
	}
	public static double sum(double a,double b) {
		return a+b;
	}
	public static boolean isEqueals(int a,int b) {
		if (a == b) {
			return true;
		}
		return false;
	}
	public static boolean isEqueals(double a,double b) {
		if (a == b) {
			return true;
		}
		return false;
	}
	public static int comf(int a,int b,boolean flag) {
		//int max,min;
		if (flag == true) {
			if (a > b) {
				return a;
			}else
				return b;
		}
		if (flag == false) {
			if (a > b) {
				return b;
			}else
				return a;
		}
		
		return 0;
		
	}
}
