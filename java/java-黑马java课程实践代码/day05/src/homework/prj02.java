package homework;

import java.util.Scanner;

public class prj02 {

	public static double ca(int l,int w){
		return l*w;
	}
	public static double cz(int l,int w){
		return (l+w)*2;
	}
	
	public static double ya(int r){
		return 3.14*r*r;
	}
	public static double yz(int r){
		return 2*3.14*r;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("�����볤���εĳ��ȣ�");
		int length = input.nextInt();
		System.out.println("�����볤���εĿ�ȣ�");
		int height = input.nextInt();
		System.out.println("������Բ�İ뾶��");
		int r = input.nextInt();
		double ca = ca(length,height);
		double cz = cz(length,height);
		System.out.print("�����ε�������ܳ��ֱ��ǣ�"+ca+","+cz);
		double ya = ya(r);
		double yz = yz(r);
		System.out.print("Բ�ε�������ܳ��ֱ��ǣ�"+ya+","+yz);
	}
}
