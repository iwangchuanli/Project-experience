package homework;

import java.util.Scanner;

public class test06 {

	public static void loca(int []a,int key){
		int count = 0;
		System.out.print("�����е�����ֵ��");
		for (int i = 0; i < a.length; i++) {
			if (a[i] == key) {
				count ++;
				System.out.print(i+" ");
			}
		}
		if (count == 0) {
			System.out.print("������û���������");
		}
		System.out.println('\n'+"����"+key+"���������г����ˣ�"+count+"�Ρ�");
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������0~10֮���һ���ؼ����֣�");
		int k = input.nextInt();
		int []arr = {1,2,3,4,5,6,7,8,9};
		loca(arr, k);
	}
}
