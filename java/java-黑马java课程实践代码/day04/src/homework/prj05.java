package homework;

import java.util.Random;

public class prj05 {

	public static void main(String[] args) {
		Random ran = new Random();
		int arr [] =new int[10];
		System.out.print("ԭ����Ϊ��   ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ran.nextInt(100)+1;
			System.out.print(arr[i]+" ");
		}
		//int arr [] ={2,1,3,5,4};
		int item,ipos;
		for (int i = 0; i < arr.length; i++) {
			item = arr[i];
			ipos = i;
			for(int n=i+1;n<10;n++)     //�Ӻ����Ԫ����ѡ����Сֵ����¼��ֵ��λ�� 
	        {
	            if(arr[n]<item)
	            {
	                item=arr[n];   //����
	                ipos=n;
	            }           
	        }
	        arr[ipos]=arr[i];   //���m��Ԫ�ؽ�����ֵ 
	        arr[i]=item;
		}
		System.out.print('\n'+"��С�������������飺   ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println('\n'+"�����еĵڶ��������Ϊ:"+arr[(arr.length)-2]);
		System.out.println("�����еĵڶ�С������Ϊ:"+arr[1]);
	}
}
