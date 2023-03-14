package com.classical;
/***
 * 
 * @author Administrator
 *题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。
 */
public class Prog11{
	public static void main(String[] args){
		int count = 0;
		int n = 0;
		for(int i=1;i<5;i++){//百位数
			for(int j=1;j<5;j++){//十位数
				if(j==i)//保证百位，十位互不相同
				  continue;
				for(int k=1;k<5;k++){//个位数
					if(k!=i && k!=j){//互不相同
						n = i*100+j*10+k;//得到互不相同且无重复数字的三位数n
					  System.out.print(n+" ");
					  if((++count)%5==0)//控制输出格式
					  System.out.println();
					}
				}
			}
		}
		System.out.println();
		System.out.println("符合条件的数共："+count+"个");
	}
}

