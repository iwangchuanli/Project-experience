package homework;

public class test02 {

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			if (i%3==0&&i%5==0) {
				System.out.print(i+" ");
				sum += i;
			}
		}
		System.out.println('\n'+"1到100之间的既是3的倍数又是5倍数的数字之和:"+sum);
	}
}
