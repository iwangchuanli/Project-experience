package com.day03;
/**
 * 
 * @author Administrator
 *��һ�⣺��ϰ���յĴ���

�ڶ��⣺������������ʵ��
	1.��������:�����·ݣ������Ӧ�ļ���
	2.Ҫ��:
		(1)����һ���·ݣ����磺int month = 5;
		(2)������·ݶ�Ӧ�ļ���
			3,4,5����
			6,7,8�ļ�
			9,10,11�＾
			12,1,2����
		(3)��ʾ��ʽ����:
			������·�:5
			����̨���:5�·��Ǵ���
		public class Test2 {
		public static void main(String[] args) {
			season(8);
		}
		
		public static void season(int a){
		switch(a){
			case 3:
				System.out.print(a+"�·��Ǵ���");
				break;
			case 4:
				System.out.print(a+"�·��Ǵ���");
				break;
			case 5:
				System.out.print(a+"�·��Ǵ���");
				break;
			case 6:
				System.out.print(a+"�·����ļ�");
				break;
			case 7:
				System.out.print(a+"�·����ļ�");
				break;
			case 8:
				System.out.print(a+"�·����ļ�");
				break;
			case 9:
				System.out.print(a+"�·����＾");
				break;
			case 10:
				System.out.print(a+"�·����＾");
				break;
			case 11:
				System.out.print(a+"�·����＾");
				break;
			case 12:
				System.out.print(a+"�·��Ƕ���");
				break;
			case 1:
				System.out.print(a+"�·��Ƕ���");
				break;
			case 2:
				System.out.print(a+"�·��Ƕ���");
				break;
			default:
				System.out.print("��������");
				break;
			}
		}
	}

�����⣺������������ʵ��
	1.��ӡ1��100֮�ڵ��������������а���9��Ҫ����
	2.ÿ�����5����������������֮���ÿո�ָ�
	3.�磺1 2 3 4 5 
	public class Test2 {
		public static void main(String[] args) {
			print();
		}
		
		public static void print(){
			/*1.��ӡ1��100֮�ڵ��������������а���9��Ҫ����
			2.ÿ�����5����������������֮���ÿո�ָ�
			3.�磺1 2 3 4 5 
			public class Test2{
		public static void main (String []args){
				int count =0;
        for (int i = 1; i<=100; i++){
            if(i/10!=9&&(i%10!=9)){
                System.out.print(i+" ");
                count++;
                if(count%5==0){
                    System.out.println();
                }
            }
        }
		}
	}
	
�����⣺������������ʵ��
	public class Test11 {
		public static void main(String[] args) {
			for(int x=1; x<=10; x++) {
				if(x%3==0) {	3  6	9
					//()�ڴ˴���д����
				}
				System.out.println("Java������");
			}
		}
	}
	����:
		1.���ڿ���̨���2��:"Java������"   ����()����Ӧ����дʲô?  break;
		2.���ڿ���̨���7��:"Java������"   ����()����Ӧ����дʲô? continue;
		3.���ڿ���̨���13��:"Java������"   ����()����Ӧ����дʲô? System.out.println("Java������");

�����⣺������������ʵ��
	1.ʹ��Ƕ��ѭ�����99�˷���Ĵ�ӡ
		1*1=1
		1*2=2	2*2=4
		1*3=3	2*3=6	3*3=9
		...
	public class Test2 {
		public static void main(String[] args) {
			//������
			for(int i=1; i<=9; i++) {
				//for(int j=1; j<=9; j++) { ȫ��99��
				for(int j=1; j<=i; j++) {
					System.out.print(i+"*"+j+"="+(i*j)+"\t");
				}
				System.out.println();
				}
				
			}
		}
	
	public class Test_9x9 {  
  
    public static void main(String[] args) {  
        //������  
        for (int i=1; i<=9; i++) {  
            for (int j=i; j<=9; j++) {  
                System.out.print(j+"*"+i+"="+(i*j)+"\t");  
            }  
            System.out.println();  
        }  
    }  
      
}  
	
	
 */
public class Test {

}
