package homework;

public class test05 {

	public static void main(String[] args) {
		int num = 0;
		int count = 0;
		for (int i = 100; i < 1000; i++) {
			int a = i%10;
			int b = (i/10)%10;
			int c = i/100;
			if (a*a*a+b*b*b+c*c*c == i) {
				count++;
				System.out.print(i+" ");
				if (count % 2 == 0) {
					System.out.print('\n');
				}
			}
		}
		System.out.print("在100~1000水仙花的个数为："+count);
	}
}
