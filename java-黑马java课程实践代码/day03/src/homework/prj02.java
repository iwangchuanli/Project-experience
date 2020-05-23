package homework;

public class prj02 {

	public static void main(String[] args) {
		int count = 0;
		for (int i = 1; i < 100; i++) {
			if (count % 5 ==0) {
				System.out.print('\n');
			}
			if(i < 10 && i != 9){
				System.out.print(i+" ");
				count ++;
			}
			else{
				int a = i % 10;
				int b = i/10;
				if (a != 9 && b != 9) {
					System.out.print(i+" ");
					count ++;
				}
			
			}
				
		}
	}
}
