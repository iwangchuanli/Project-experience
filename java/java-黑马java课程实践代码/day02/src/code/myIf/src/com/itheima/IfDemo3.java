package code.myIf.src.com.itheima;
/*
 * ��ʽ3��
 * 		if(��ϵ���ʽ1) {
 * 			�����1;
 * 		}else if(��ϵ���ʽ2) {
 * 			�����2;
 * 		}
 * 		...
 * 		else {
 * 			�����n+1;
 * 		}
 * 
 * ִ�����̣�
 * 		A:�����ϵ���ʽ1��ֵ������true����false
 * 		B:�����true����ִ�������1
 * 		C:�����false���ͼ��������ϵ���ʽ2��ֵ������true����false		
 * 		D:�����true����ִ�������2
 * 		E:�����false���ͼ�������...
 * 		F:���еĹ�ϵ���ʽ�Ľ������false��ִ�������n+1
 */
public class IfDemo3 {
	public static void main(String[] args) {
		System.out.println("��ʼ");
		
		//����x��y�������µĹ�ϵ��
		//x>=3	y=2x+1
		//-1<=x<3	y=2x
		//x<-1	y=2x-1
		
		//���ݸ�����x��ֵ�������y��ֵ
		int x = 5;
		x = 0;
		x = -5;
		
		//�������y
		int y;
		
		if(x >= 3) {
			y = 2*x+1;
		}else if(x>=-1 && x<3) {
			y = 2*x;
		}else if(x<-1) {
			y = 2*x-1;
		}else {
			y = 0;
			System.out.println("������������x��ֵ");
		}
		
		System.out.println("y:"+y);
		
		System.out.println("����");
	}
}
