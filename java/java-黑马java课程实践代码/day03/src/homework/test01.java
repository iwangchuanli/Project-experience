package homework;

public class test01 {

	public static void main(String[] args) {
		int sumJs = 0;
		int count = 0;
		for (int i = 0; i <= 100; i++) {
			if (i % 2 !=0) {
				sumJs += i;
				System.out.print(i+"  ");
				count ++;
				if (count % 10 ==0) {
					System.out.println('\n');
				}
			}
		}
		System.out.println("1-100之间的奇数之和:"+sumJs);
	}
}
