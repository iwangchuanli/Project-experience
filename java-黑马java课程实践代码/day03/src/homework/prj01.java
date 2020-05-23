package homework;

public class prj01 {

	public static void main(String[] args) {
		int height = 8848;
		double x = 0.0001;
		int count=0;
		while (true) {
			x = x*2;
			count ++;
			if (x >= height) {
				System.out.println("共折叠"+count+"次，折叠的高度为"+x+"米。");
				break;
			}
			
		}
	}
}
