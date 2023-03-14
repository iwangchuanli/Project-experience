package Chapter_4;

import java.util.Random;

public class ForEachInt {
public static void main(String[] args) {
	Random rand=new Random(47);
	float f[]=new float[10];
	for (int i = 0; i < 10; i++) {
		f[i]=rand.nextFloat();
		for(float x:f)
			System.out.println(x);
	}
	for (char c:"An African Swallow".toCharArray()) {
		System.out.print(c+"  ");
	}
	}
}
