package homework;

public class prj03 {

	public static void main(String[] args) {
		int g,s,b,q;
		int count = 0;
		for (q = 9; q > 0; q--) {
			for (b = 9; b > 0; b--) {
				for (s = 9; s > 0; s--) {
					for (g = 9; g > 0; g--) {
						if (g+b == s+q) {
							System.out.print(q*1000+b*100+s*10+g+" ");
							count++;
							if(count % 5 == 0){
								System.out.print('\n');
							}	
						}
					}
				}
			}
		}
		System.out.println("共有"+count+"个数满足条件！");
	}
}
