package SY_2;
import java.math.*;
public class sy2_5 {
	public static void main(String[] args) {
		int a,i;
		long item=1;
		a=(int)(Math.random()*6)+1;
		for(i=1;i<=a;i++){
			item=item*i;
		}
		System.out.println(a+"的阶乘："+item);	
	}
}
