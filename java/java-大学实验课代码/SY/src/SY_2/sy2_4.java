package SY_2;
import java.math.*;
public class sy2_4 {
	public static void main(String[] args) {
		int face=0;
		int i;
		int f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
		for(i=1;i<=500;i++)
		{
			face=(int)(Math.random()*6)+1;
			switch(face)
			{
			case 1:
				f1++;
				break;
			case 2:
				f2++;
				break;
			case 3:
				f3++;
				break;
			case 4:
				f4++;
				break;
			case 5:
				f5++;
				break;
			case 6:
				f6++;
				break;
			}
		}
		System.out.print("第一面的概率:"+f1+'\n'
				        +"第二面的概率:"+f2+'\n'
				        +"第三面的概率:"+f3+'\n'
				        +"第四面的概率:"+f4+'\n'
				        +"第五面的概率:"+f5+'\n'
				        +"第六面的概率:"+f6+'\n');
		
	}

}
