package com.classical;
/**
 * 题目：打印出杨辉三角形（要求打印出10行如下图）
程序分析：
        1 
       1 1 
      1 2 1 
     1 3 3 1 
    1 4 6 4 1 
   1 5 10 10 5 1 

 * @author Administrator
 *
 */

public class Prog33{
	public static void main(String[] args){
		int[][] n = new int[10][21];
		n[0][10] = 1;
		for(int i=1;i<10;i++)
		  for(int j=10-i;j<10+i+1;j++)
		    n[i][j] = n[i-1][j-1]+n[i-1][j+1];
		for(int i=0;i<10;i++){
			for(int j=0;j<21;j++){
				if(n[i][j]==0)
				  System.out.print("   ");
				else{
			    if(n[i][j]<10)
			      System.out.print("  "+n[i][j]);//空格为了美观需要
			    else if(n[i][j]<100)
			      System.out.print(" "+n[i][j]);
			      else
			        System.out.print(n[i][j]);
			  }
			}
			System.out.println();
		}
	}
}


//public class Prog33 {
//
//	public static void main(String[] args) {
//		draw();
//	}
//	public static void draw(){
//		int n = 5;
//		for (int i = 0; i < n; i++) {
//			for (int j = n; j > i; j--) {
//				System.out.print(" ");
//			}
//			
//			System.out.println("");
//		}
//	}
//}
