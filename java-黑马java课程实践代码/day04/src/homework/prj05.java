package homework;

import java.util.Random;

public class prj05 {

	public static void main(String[] args) {
		Random ran = new Random();
		int arr [] =new int[10];
		System.out.print("原数组为：   ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ran.nextInt(100)+1;
			System.out.print(arr[i]+" ");
		}
		//int arr [] ={2,1,3,5,4};
		int item,ipos;
		for (int i = 0; i < arr.length; i++) {
			item = arr[i];
			ipos = i;
			for(int n=i+1;n<10;n++)     //从后面的元素中选出最小值，记录数值和位置 
	        {
	            if(arr[n]<item)
	            {
	                item=arr[n];   //交换
	                ipos=n;
	            }           
	        }
	        arr[ipos]=arr[i];   //与第m个元素交换数值 
	        arr[i]=item;
		}
		System.out.print('\n'+"从小到大排序后的数组：   ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println('\n'+"数组中的第二大的数字为:"+arr[(arr.length)-2]);
		System.out.println("数组中的第二小的数字为:"+arr[1]);
	}
}
